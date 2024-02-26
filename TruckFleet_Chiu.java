import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class TruckFleet_Chiu {
    private String truckFleetName;
    private String[] packageStatus = { "At warehouse", "Storage", "Loaded on truck", "En route", "Delivered" };
    private String[] truckLocation = { "At warehouse", "Making deliveries" };
    boolean finishedLoading;
    private ArrayList<Truck_Chiu> trucksList = new ArrayList<>();
    private static LinkedTransferQueue<Package_Chiu> warehouseQueue = new LinkedTransferQueue<>();
    private static LinkedTransferQueue<Package_Chiu> storageQueue = new LinkedTransferQueue<>();
    private static LinkedTransferQueue<Package_Chiu> deliveredQueue = new LinkedTransferQueue<>();

    public TruckFleet_Chiu(String name) {
        this.truckFleetName = name;
    }

    public void addTruck(String truckName, double maxWeight, int maxCount, String area, String location) {
        trucksList.add(new Truck_Chiu(truckName, maxWeight, maxCount, area, location));
    }

    public static void addPackage(Package_Chiu box) {
        warehouseQueue.add(box);
    }

    public Truck_Chiu getTrucksList(int i) {
        return trucksList.get(i);
    }

    public int getTrucksListSize() {
        return trucksList.size();
    }

    public int getWarehouseQueueSize() {
        return warehouseQueue.size();
    }

    public int getStorageQueueSize() {
        return storageQueue.size();
    }

    public void loadTrucks() {
        Truck_Chiu truck;

        //Move all packages from warehouse queue to storage queue
        Package_Chiu tempBox;
        while (!warehouseQueue.isEmpty()) {
            tempBox = warehouseQueue.remove();
            tempBox.setStatus(packageStatus[1]);
            storageQueue.add(tempBox);
        }
        System.out.println("***** Start loading trucks.");
        for (int i = 0; i < trucksList.size(); ++i) {
            if (storageQueue.isEmpty()) {
                break;
            }
            truck = trucksList.get(i);
            boolean isPackageLoaded;
            finishedLoading = false;
            truck.setLoadWeight(0.0);

            printStorageQueue();
            int qSize = storageQueue.size();
            int qPosition = 0;

            System.out.println(truck.getTruckName() + " to " + truck.getArea());
            while (!finishedLoading && !storageQueue.isEmpty()) {
                tempBox = storageQueue.peek();
                if (truck.getArea().equals(tempBox.getDArea())) {
                    isPackageLoaded = truck.loadPackage(tempBox);
                    if (isPackageLoaded) {
                        storageQueue.remove();
                    } else {
                        for (int j =  0; j < qSize - qPosition; ++j) {
                            storageQueue.add(storageQueue.remove());
                        }
                        finishedLoading = true;
                    }
                } else {
                    storageQueue.add(storageQueue.remove());
                }
                ++qPosition;
                if (qPosition == qSize) {
                    finishedLoading = true;
                }
            }
            System.out.println("Loading complete: Load weight " + truck.getLoadWeight()+ "lbs/" +
                    truck.numOfPackages() + " packages");
            truck.printOnTruckStack();
        }
        if (storageQueue.isEmpty()) {
            System.out.println("***** No more packages to load. Storage queue is empty.");
        } else {
            System.out.println("***** Trucks are full. Remaining storage queue");
            printStorageQueue();
        }
        System.out.println("***** Trucks are ready for delivery.\n");
    }

    public void releaseTrucks() {
        Truck_Chiu truck;
        System.out.println("***** Release trucks for delivery.\n");
        for (int i = 0; i < trucksList.size(); ++i) {
            truck = trucksList.get(i);
            if (truck.isOnTruckStackEmpty()) {
                System.out.println(truck.getTruckName() + " to " + truck.getArea());
                System.out.println("No packages loaded. Truck at warehouse.\n");
            } else {
                truck.updateStatusEnRoute();
                System.out.println(truck.getTruckName() + " to " + truck.getArea() + " en route");
                truck.printOnTruckStack();
                while (!truck.isOnTruckStackEmpty()) {
                    System.out.println("Next delivery address: " + truck.nextLocation());
                    deliveredQueue.add(truck.unloadPackage());
                    System.out.println("Delivered");
                }
                System.out.println("All deliveries completed. Returning to warehouse.\n");
            }
        }
    }

    public String truckInfo(int i) {
        return trucksList.get(i).getTruckName() + " can carry " + trucksList.get(i).getMaxWeight() + "lbs and "
                + trucksList.get(i).getMaxCount() + " packages, serves " +
                trucksList.get(i).getArea() + ". Current location: " + trucksList.get(i).getLocation() + ".";
    }

    public void printTrucksList() {
        for (Truck_Chiu truck : trucksList) {
            System.out.println(truck.getTruckName() + " can carry " + truck.getMaxWeight() +
                    "lbs and " + truck.getMaxCount() + " packages, serves " + truck.getArea() +
                    ". Current location: " + truck.getLocation() + ".");
        }
        System.out.println("\n");
    }

    public void printWarehouseQueue() {
        System.out.println("Current warehouse queue");
        Iterator<Package_Chiu> box = warehouseQueue.iterator();
        while (box.hasNext()) {
            System.out.println(box.next().toString());
        }
        System.out.println("\n");
    }

    public void printStorageQueue() {
        System.out.println("Current storage queue");
        Iterator<Package_Chiu> box = storageQueue.iterator();
        while (box.hasNext()) {
            System.out.println(box.next().toString());
        }
        System.out.println("\n");
    }

    public void printDeliveredQueue() {
        System.out.println("Delivered Queue");
        Iterator<Package_Chiu> box = deliveredQueue.iterator();
        while (box.hasNext()) {
            System.out.println(box.next().toString());
        }
        System.out.println("\n");
    }

    public Package_Chiu searchForPackage(String searchType, String searchString) {
        Package_Chiu tempBox = new Package_Chiu();
        if (searchType.equals("Name")) {
            Iterator<Package_Chiu> wQ = warehouseQueue.iterator();
            while(wQ.hasNext()) {
                tempBox = wQ.next();
                if(tempBox.getPackageName().equals(searchString)) {
                    return tempBox;
                }
            }
            Iterator<Package_Chiu> sQ = storageQueue.iterator();
            while(sQ.hasNext()) {
                tempBox = sQ.next();
                if(tempBox.getPackageName().equals(searchString)) {
                    return tempBox;
                }
            }
            Iterator<Package_Chiu> dQ = deliveredQueue.iterator();
            while(dQ.hasNext()) {
                tempBox = dQ.next();
                if(tempBox.getPackageName().equals(searchString)) {
                    return tempBox;
                }
            }
            for (int i =  0; i < trucksList.size(); ++i) {
                Iterator<Package_Chiu> tS = trucksList.get(i).getOnTruck().iterator();
                while (tS.hasNext()) {
                    tempBox = tS.next();
                    if(tempBox.getPackageName().equals(searchString)) {
                        return tempBox;
                    }
                }
            }
        } else if(searchType.equals("Address")) {
            Iterator<Package_Chiu> wQ = warehouseQueue.iterator();
            while(wQ.hasNext()) {
                tempBox = wQ.next();
                if(tempBox.getDAddress().equals(searchString)) {
                    return tempBox;
                }
            }
            Iterator<Package_Chiu> sQ = storageQueue.iterator();
            while(sQ.hasNext()) {
                tempBox = sQ.next();
                if(tempBox.getDAddress().equals(searchString)) {
                    return tempBox;
                }
            }
            Iterator<Package_Chiu> dQ = deliveredQueue.iterator();
            while(dQ.hasNext()) {
                tempBox = dQ.next();
                if(tempBox.getDAddress().equals(searchString)) {
                    return tempBox;
                }
            }
            for (int i =  0; i < trucksList.size(); ++i) {
                Iterator<Package_Chiu> tS = trucksList.get(i).getOnTruck().iterator();
                while (tS.hasNext()) {
                    tempBox = tS.next();
                    if(tempBox.getDAddress().equals(searchString)) {
                        return tempBox;
                    }
                }
            }
        }
        return null;
    }

    public int searchForTruck(String truckName) {
        Truck_Chiu tempTruck = new Truck_Chiu();
        for(int i = 0; i < trucksList.size(); i++) {
            if(trucksList.get(i).getTruckName().equals(truckName)){
                return i;
            }
        }
        return -1;
    }

    public String test() {
        return null;
    }
}