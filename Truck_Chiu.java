import java.util.*;

public class Truck_Chiu {
    private String truckName;
    private double maxWeight;
    private double loadWeight;
    private int maxCount;
    private String area;
    private String location;
    private String[] packageStatus = { "At warehouse", "Storage", "Loaded on truck", "En route", "Delivered" };
    private boolean finishedLoading;
    private Stack<Package_Chiu> onTruck = new Stack<>();

    public Truck_Chiu() {
        truckName = null;
        maxWeight = 0.0;
        maxCount = 0;
        area = null;
        location = null;
    }

    public Truck_Chiu(String truckName, double maxWeight, int maxCount, String area, String location) {
        this.truckName = truckName;
        this.maxWeight = maxWeight;
        this.maxCount = maxCount;
        this.area = area;
        this.location = location;
    }

    public void setLoadWeight(double loadWeight) {
        this.loadWeight = loadWeight;
    }

    public String getTruckName() {
        return truckName;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getLoadWeight() {
        return loadWeight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public String getArea() {
        return area;
    }

    public String getLocation() {
        return location;
    }

    public Stack<Package_Chiu> getOnTruck() {
        return onTruck;
    }

    public int numOfPackages() {
        return onTruck.size();
    }

    public boolean isOnTruckStackEmpty() {
        return onTruck.isEmpty();
    }

    public boolean loadPackage(Package_Chiu tempBox) {
        double tempLoadWeight = loadWeight + tempBox.getWeight();
        if (tempLoadWeight > maxWeight || onTruck.size() == maxCount) {
            System.out.println("Can't load package: " + tempBox.getPackageName() +
                    "\nReached truck capacity: " + tempLoadWeight + "lbs/max " + maxWeight + "lbs, " +
                    onTruck.size() + "/" + maxCount + " packages");
            return false;
        } else {
            loadWeight = tempLoadWeight;
            tempBox.setStatus(packageStatus[2]);
            onTruck.push(tempBox);
            return true;
        }
    }

    public void updateStatusEnRoute() {
        Stack<Package_Chiu> tempStack = new Stack<>();
        Package_Chiu tempBox;
        while (!onTruck.isEmpty()) {
            tempBox = onTruck.pop();
            tempBox.setStatus(packageStatus[3]);
            tempStack.push(tempBox);
        }
        while (!tempStack.isEmpty()) {
            onTruck.push(tempStack.pop());
        }
    }

    public void deliverPackages() {
        while (!onTruck.isEmpty()) {
            System.out.println("Next delivery address: " + nextLocation());
            unloadPackage();
            System.out.println("Delivered");
        }
        System.out.println("All deliveries completed. Returning to warehouse.");
    }

    public String nextLocation() {
        return onTruck.peek().getDAddress();
    }

    public Package_Chiu unloadPackage() {
        Package_Chiu tempBox = onTruck.pop();
        loadWeight -= tempBox.getWeight();
        tempBox.setStatus(packageStatus[4]);
        return tempBox;
    }

    public void printOnTruckStack() {
        if (onTruck.isEmpty()) {
            System.out.println("No packages loaded.");
        } else {
            Iterator<Package_Chiu> box = onTruck.iterator();
            while (box.hasNext()) {
                System.out.println(box.next().toString());
            }
        }
        System.out.println("\n");
    }
}