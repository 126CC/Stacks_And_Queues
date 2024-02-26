public class Main {
    public static void main(String[] args) {
        String[] packageStatus = { "At warehouse", "Storage", "Loaded on truck", "En route", "Delivered" };

        TruckFleet_Chiu warehouse = new TruckFleet_Chiu("Warehouse");

        warehouse.addTruck("Truck1", 200.0, 15, "Freehold", "Warehouse");
        warehouse.addTruck("Truck2", 100.0, 7, "Manalapan", "Warehouse");
        warehouse.addTruck("Truck3", 75.0, 2, "Colts Neck", "Warehouse");
        warehouse.addTruck("Truck4", 300.0, 25, "Howell", "Warehouse");
        warehouse.addTruck("Truck5", 250.0, 21, "Marlboro", "Warehouse");
        warehouse.printTrucksList();

        warehouse.addPackage(new Package_Chiu("Package1", 20.0, "Manalapan", "101 Road St"));
        warehouse.addPackage(new Package_Chiu("Package2", 100.0, "Manalapan", "300 Street Rd"));
        warehouse.addPackage(new Package_Chiu("Package3", 150.0, "Freehold", "32 Avenue Rd"));
        warehouse.addPackage(new Package_Chiu("Package4", 10.0, "Freehold", "1 Main St"));
        warehouse.addPackage(new Package_Chiu("Package5", 15.0, "Freehold", "15 Temple Rd"));
        warehouse.addPackage(new Package_Chiu("Package6", 16.0, "Freehold", "3 My Rd"));
        warehouse.addPackage(new Package_Chiu("Package7", 1.0, "Marlboro", "4 Goldstein Way"));
        warehouse.addPackage(new Package_Chiu("Package8", 15.0, "Marlboro", "15 Goldstein Way"));
        warehouse.addPackage(new Package_Chiu("Package9", 35.0, "Freehold", "53 CompSci Ct"));
        warehouse.addPackage(new Package_Chiu("Package10", 54.0, "Marlboro", "32 ColtsNeck Rd"));
        warehouse.printWarehouseQueue();

        System.out.println(warehouse.test());

        Package_Chiu searchPackage = new Package_Chiu();

        while (warehouse.getWarehouseQueueSize() > 0 || warehouse.getStorageQueueSize() > 0) {
            //Load the trucks
            warehouse.loadTrucks();

            searchPackage = warehouse.searchForPackage("Address", "15 Temple Rd");
            if (searchPackage != null) {
                System.out.println("Package found:");
                System.out.println(searchPackage.toString() + "\n");
            } else {
                System.out.println("Package not found.\n");
            }

            //Delivery
            warehouse.releaseTrucks();
        }
        System.out.println("\n***** All packages delivered. *****\n");
        warehouse.printDeliveredQueue();

        System.out.println("\n" + warehouse.test() + "\n");

        for(int i = 0; i < warehouse.getTrucksListSize(); i++) {
            if(warehouse.getTrucksList(i).getLocation().equals("Warehouse")) {
                System.out.println(warehouse.getTrucksList(i).getTruckName() + " is at warehouse.");
            }
        }

        System.out.println("\n" + warehouse.test());


    }
}
