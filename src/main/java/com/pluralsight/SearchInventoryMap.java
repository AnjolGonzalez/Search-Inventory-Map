package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {
    public static void main(String[] args) throws IOException {
        HashMap<String, Inventory> inventory = getInventory();
        BufferedReader buffRead = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));

        String input;
        int prodID;
        String prodName;
        float prodPrice;

        while ((input = buffRead.readLine()) != null) {

            String[] invent = input.split("\\|");
            prodID = Integer.parseInt(invent[0]);
            prodName = invent[1];
            prodPrice = Float.parseFloat(invent[2]);
            inventory.put(prodName, new Inventory(prodID, prodName, prodPrice));
        }
        buffRead.close();

        Scanner scan = new Scanner(System.in);

        System.out.println("The following items we have in inventory are: ");

        for (Inventory produce : inventory.values()) {
            System.out.printf("id: %d %s - Price: $%.2f\n", produce.getId(), produce.getName(), produce.getPrice());
        }
        while (true) {

            System.out.print("What item would you like to see?: ");
            String name = scan.nextLine().trim();
            Inventory produceChosen = inventory.get(name);
            if (produceChosen == null) {
                System.out.println("Sorry, that product is currently unavailable");
                return;
            }
            System.out.printf("We currently have %s", produceChosen.getName(), produceChosen.getPrice());
            System.out.print("Would you like to take another look?: ");
            String tryAgain = scan.nextLine().trim();

            if ("no".equalsIgnoreCase(tryAgain)) {
                break;
            }
        }
        scan.close();
    }

    public static HashMap<String, Inventory> getInventory() {
        HashMap<String, Inventory> inventory = new HashMap<>();
        return inventory;
    }
}
