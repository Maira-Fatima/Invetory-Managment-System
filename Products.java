
import java.io.*;
import java.util.*;

public class Products {
    public static void main(String[] args) {
        InventoryPage();
    }
    static void InventoryPage() {
        boolean flag = true;
        do { 
            try {
                System.out.println("Navigating to Product Page...");
                System.out.println("1. View");
                System.out.println("2. Modify");
                System.out.println("3. Stock level check");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt(); 
                input.nextLine(); 
                String[] filePaths = { "cakes.txt", "breads.txt", "pastries.txt", "snacks.txt" };
                switch (choice) {
                    case 1:
                        viewProducts(filePaths);  
                        break;
                    case 2:
                        modifyProducts(filePaths);  
                        break;
                    case 3:
                        checkStockLevel(filePaths); 
                        break;
                    case 4:
                        System.out.println("Exiting the product page...");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, 3 or 4.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch(Exception ex){
                System.out.println("An error occurred: " + ex.getMessage());
            }
        } while (flag);
    }





    static void viewProducts(String[] filePaths) {
        boolean flag = true;
        do { 
            try {
            Scanner input = new Scanner(System.in);
            System.out.println("View Page");
            System.out.println("1. View all files");
            System.out.println("2. View a specific item");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");
            int viewChoice = input.nextInt();
            input.nextLine();
            
            if (viewChoice == 1) {
                               while (true){
                    try {
                        System.out.println("Viewing all files...");
                        System.out.println("1. All details");
                        System.out.println("2. Specific detail");
                        System.out.println("3. Back to view Page");
                        System.out.print("Enter your choice: ");
                        int detailChoice = input.nextInt();
                        input.nextLine();
                        if (detailChoice == 1) {
                            System.out.printf("%-10s %-20s %-15s %-15s %-10s%n", "Product ID", "Product Name", "Quantity", "Sales Price", "Cost");
                            System.out.println("---------------------------------------------------------------------------");
                            for (String filePath : filePaths) {
                                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                                    String line;
                                    while ((line = reader.readLine()) != null) {
                                        String[] details = line.split(",");
                                        if (details.length >= 5) {
                                            System.out.printf("%-10s %-20s %-15s %-15s %-10s%n",
                                                    details[0], details[1], details[2], details[3], details[4]);
                                        }
                                    }
                                } catch (FileNotFoundException ex) {
                                    System.out.println("File not found: " + filePath);
                                } catch (IOException ex) {
                                    System.out.println("Error reading file: " + filePath);
                                }
                            }
                        } else if (detailChoice == 2) {
                            System.out.println("Which detail would you like to view?");
                            System.out.println("1. Quantity");
                            System.out.println("2. Sales Price");
                            System.out.println("3. Cost");
                            System.out.print("Enter your choice: ");
                            int specificDetailChoice = input.nextInt();
                            input.nextLine(); 
                            String header;
                            switch (specificDetailChoice) {
                                case 1: header = "Quantity"; break;
                                case 2: header = "Sales Price"; break;
                                case 3: header = "Cost"; break;
                                default: header = ""; break;
                            }
                            System.out.printf("%-10s %-20s %-15s%n", "Product ID", "Product Name", header);
                            System.out.println("-------------------------------------------------------");
                            for (String filePath : filePaths) {
                                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                                    String line;
                                    while ((line = reader.readLine()) != null) {
                                        String[] details = line.split(",");
                                        if (details.length >= 5) {
                                            switch (specificDetailChoice) {
                                                case 1: System.out.printf("%-10s %-20s %-15s%n", details[0], details[1], details[2]); break;
                                                case 2: System.out.printf("%-10s %-20s %-15s%n", details[0], details[1], details[3]); break;
                                                case 3: System.out.printf("%-10s %-20s %-15s%n", details[0], details[1], details[4]); break;
                                                default: System.out.println("Invalid choice."); break;
                                            }
                                        }
                                    }
                                } catch (FileNotFoundException ex) {
                                    System.out.println("File not found: " + filePath);
                                } catch (IOException ex) {
                                    System.out.println("Error reading file: " + filePath);
                                }
                            }
                        }
                            else if (detailChoice == 3){
                                break;
                            }
                         else {
                            System.out.println("Invalid choice for specific detail.");
                        }
                    }catch (InputMismatchException ex) {
                    System.out.println("Invalid input.\n please try again");
                    input.next();
                }catch(Exception ex){
                    System.out.println("An error occurred: " + ex.getMessage());
                }
            }
            }


            



            else if (viewChoice == 2) {
               while (true){
    try {
            System.out.println("Select the file to view:\n1. Cake\n2. Breads\n3. Pastries\n4. Snacks\n5. Back to View page");
                System.out.print("Enter your choice: ");
                int specificItemChoice = input.nextInt();
                input.nextLine(); 
                String filePath = null;
                switch (specificItemChoice) {
                    case 1: filePath = "cakes.txt"; break;
                    case 2: filePath = "breads.txt"; break;
                    case 3: filePath = "pastries.txt"; break;
                    case 4: filePath = "snacks.txt"; break;
                    case 5: return;
                    default: System.out.println("Invalid choice."); 
                }
                    System.out.println("1. All details");
                    System.out.println("2. Specific detail");
                    System.out.print("Enter your choice: ");
                    int detailChoice = input.nextInt();
                    input.nextLine();
                    if (detailChoice == 1) {
                        System.out.printf("%-10s %-20s %-15s %-15s %-10s%n", "Product ID", "Product Name", "Quantity", "Sales Price", "Cost");
                        System.out.println("---------------------------------------------------------------------------");
                        try{
                            BufferedReader reader = new BufferedReader(new FileReader(filePath));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] details = line.split(",");
                                if (details.length >= 5) {
                                    System.out.printf("%-10s %-20s %-15s %-15s %-10s%n",
                                            details[0], details[1], details[2], details[3], details[4]);
                                }
                            }
                        } 
                        catch(InputMismatchException ex){
                            System.out.println("Invalid input. Please enter a number.");
                            input.nextLine();
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found: " + filePath);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + filePath);
                        }
                        catch(Exception ex){
                            System.out.println("An error occurred: " + ex.getMessage());
                        }
                    } else if (detailChoice == 2) {
                        System.out.println("Which detail would you like to view?");
                        System.out.println("1. Quantity");
                        System.out.println("2. Sales Price");
                        System.out.println("3. Cost");
                        System.out.print("Enter your choice: ");
                        int specificDetailChoice = input.nextInt();
                        input.nextLine();
                        String header;
                        switch (specificDetailChoice) {
                            case 1: header = "Quantity"; break;
                            case 2: header = "Sales Price"; break;
                            case 3: header = "Cost"; break;
                            default: header = ""; break;
                        }
                        System.out.printf("%-10s %-20s %-15s%n", "Product ID", "Product Name", header);
                        System.out.println("-------------------------------------------------------");
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] details = line.split(",");
                                if (details.length >= 5) {
                                    switch (specificDetailChoice) {
                                        case 1: System.out.printf("%-10s %-20s %-15s%n", details[0], details[1], details[2]); break;
                                        case 2: System.out.printf("%-10s %-20s %-15s%n", details[0], details[1], details[3]); break;
                                        case 3: System.out.printf("%-10s %-20s %-15s%n", details[0], details[1], details[4]); break;
                                        default: System.out.println("Invalid choice."); break;
                                    }
                                }
                            }
                        }
                        catch(InputMismatchException ex){
                            System.out.println("Invalid input. Please enter a number.");
                            input.nextLine();
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found: " + filePath);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + filePath);
                        }
                        catch(Exception ex){
                            System.out.println("An error occurred: " + ex.getMessage());
                        }
                    } else {
                        System.out.println("Invalid choice for specific detail.");
                    }
                }
                    catch(InputMismatchException ex){
                        System.out.println("Invalid input. Please enter a number.");
                        input.nextLine();
                    }
                    catch(Exception ex){
                        System.out.println("An error occurred: " + ex.getMessage());
                    }
                }







                
    } else if (viewChoice == 3) {
    System.out.println("Taking you back to the Inventory page........");
    flag = false;
    }
    else {
        System.out.println("Invalid file choice.");
    }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch(Exception ex){
            System.out.println("An error occurred: " + ex.getMessage());
        }
    } while (flag);

}

    static void modifyProducts(String[] filePaths) {
        boolean flag = true;
        do {
        try{
            Scanner input = new Scanner(System.in); 
            System.out.println("Welcome to inventory modification page");
            System.out.println("What would you like to do?\n1. Add\n2. Delete\n3. Update\n4.Exit");
            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("Welcome to the Add page");
                System.out.println("Enter the product category(make sure to put an 's' at the end)");
                String category = input.next();
                input.nextLine();
                category.toLowerCase();
                String path = String.format(category+".txt");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
                    String id = category.substring(0, 3).toUpperCase();
                    for (int i = 0; i < 3; i++) {
                        id += (int) (Math.random() * 10);
                        }
                    System.out.println("Enter the product name");
                    String name = input.nextLine().toUpperCase();
                    System.out.println("Enter the product quantity");
                    int quantity = input.nextInt();
                    System.out.println("Enter the product price");
                    int price = input.nextInt();
                    System.out.println("Enter the product cost");
                    int cost = input.nextInt(); 
                    String item = String.format("%s,%s,%d,%d,%d",id,name,quantity,price,cost);
                    writer.newLine();
                    writer.write(item);
                        System.out.println("New Item data has been added to the file");
                    writer.close();
                    System.out.println("Would you like to read the file just updated?\n1.Yes\n2.No");
                    int readChoice = input.nextInt();
                    if(readChoice == 1) {
                    while(reader.ready()){
                        System.out.println(reader.readLine());
                    }
                    } else{
                        System.out.println("File has been updated\nExiting the add page");
                    }
                    reader.close();
                }catch (InputMismatchException ex){
                    System.out.println("Invalid input");
                } catch (FileNotFoundException ex) {
                    System.out.println("Invalid file name ");
                    System.out.println("File not found: " + path);
                }catch (IOException ex) {
                    System.out.println("Error reading file: " + path);
                }
} else if (choice == 2) {
                System.out.println("Welcome to the Delete page");
                System.out.println("Enter the item category you want to delete? (make sure to add 's' at the end)");
                String category = input.next();
                input.nextLine();
                category = category.toLowerCase();
                String path = String.format("%s.txt", category);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    String[] lines = new String[100]; // Assuming a maximum of 100 lines
                    int lineCount = 0;
                    String line;
                    boolean itemFound = false;
                    System.out.println("Enter the ID of the item you want to delete:");
                    String idToDelete = input.nextLine();
                    while ((line = reader.readLine()) != null) {
                        String[] del = line.split(",");
                        if (del[0].equals(idToDelete)) {
                            itemFound = true;
                            System.out.println("Product found: " + line);
                            System.out.println("Do you want to delete this item?\n1. Yes\n2. No");
                            int deleteConfirm = input.nextInt();
                            input.nextLine(); 
                            if (deleteConfirm == 1) {
                                System.out.println("Item deleted.");
                                continue; 
                            }
                        }
                        lines[lineCount++] = line;
                    }
                    reader.close();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                    for (int i = 0; i < lineCount; i++) {
                        if (lines[i] != null) {
                            writer.write(lines[i]);
                            writer.newLine();
                        }
                    }
                    writer.close(); 
                    if (!itemFound) {
                        System.out.println("No item found with ID: " + idToDelete);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found: " + path);
                } catch (IOException ex) {
                    System.out.println("Error reading or writing file: " + path);
                }  
            } else if (choice == 3) {
                System.out.println("Welcome to the Update page");
                System.out.println("Enter the item category (e.g., 'breads'):");
                input.nextLine();
                String category = input.nextLine();
                String path = String.format("%s.txt", category);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(path));
                    String[] lines = new String[100]; 
                    int lineCount = 0;
                    String line;
                    boolean itemFound = false;
                    System.out.println("Enter the ID of the item you want to update:");
                    String idToUpdate = input.nextLine();
                    while ((line = reader.readLine()) != null) {
                        String[] details = line.split(",");
                        if (details[0].equals(idToUpdate)) {
                            itemFound = true;
                            System.out.println("Product found: " + line);
                            System.out.println("Current Quantity: " + details[2]);
                            System.out.println("Current Sales Price: " + details[3]);
                            System.out.println("Current Cost: " + details[4]);
                            System.out.println("Enter the new quantity:");
                            int newQuantity = input.nextInt();
                            input.nextLine(); 
                            System.out.println("Enter the new sales price:");
                            int newSalesPrice = input.nextInt();
                            input.nextLine();
                            System.out.println("Enter the new cost:");
                            int newCost = input.nextInt();
                            input.nextLine(); 
                            details[2] = String.valueOf(newQuantity);
                            details[3] = String.valueOf(newSalesPrice);
                            details[4] = String.valueOf(newCost);
                            String updatedLine = String.join(",", details);
                            lines[lineCount++] = updatedLine; 
                            System.out.println("Product updated successfully.");
                        } else {
                            lines[lineCount++] = line; 
                        }
                    }
                    reader.close(); 
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                    for (int i = 0; i < lineCount; i++) {
                        if (lines[i] != null) { 
                            writer.write(lines[i]);
                            writer.newLine();
                        }
                    }
                    writer.close();
                    if (!itemFound) {
                        System.out.println("No item found with ID: " + idToUpdate);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found: " + path);
                } catch (IOException ex) {
                    System.out.println("Error reading or writing file: " + path);
                }
        } else if (choice == 4) {
                System.out.println("Exiting the program");
                flag = false;
            } else {
                System.out.println("Invalid choice");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch(Exception ex){
            System.out.println("An error occurred: " + ex.getMessage());
        }
        } while (flag);
    }
static void checkStockLevel(String[] filePaths) {
    boolean flag = true;
    do {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Stock Level Check Page");
            System.out.println("1. Check Low Stocks");
            System.out.println("2. Check High Stocks");
            System.out.println("3. Check Stock Alerts");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int checkChoice = input.nextInt();
            input.nextLine();
            int lowStockThreshold = 10; 
            int highStockThreshold = 50; 
            switch (checkChoice) {
                case 1: 
                    System.out.println("Checking for low stocks (less than " + lowStockThreshold + ")...");
                    for (String filePath : filePaths) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] details = line.split(",");
                                if (details.length >= 5) {
                                    int quantity = Integer.parseInt(details[2]);
                                    if (quantity < lowStockThreshold) {
                                        System.out.printf("Low Stock Alert: %s - %s (Quantity: %d)%n", details[0], details[1], quantity);
                                    }
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found: " + filePath);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + filePath);
                        }
                    }
                    break;
                case 2: 
                    System.out.println("Checking for high stocks (more than " + highStockThreshold + ")...");
                    for (String filePath : filePaths) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] details = line.split(",");
                                if (details.length >= 5) {
                                    int quantity = Integer.parseInt(details[2]);
                                    if (quantity > highStockThreshold) {
                                        System.out.printf("High Stock Alert: %s - %s (Quantity: %d)%n", details[0], details[1], quantity);
                                    }
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found: " + filePath);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + filePath);
                        }
                    }
                    break;
                case 3: 
                    System.out.println("Checking for stock alerts (low and high stocks)...");
                    for (String filePath : filePaths) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] details = line.split(",");
                                if (details.length >= 5) {
                                    int quantity = Integer.parseInt(details[2]);
                                    if (quantity < lowStockThreshold) {
                                        System.out.printf("Low Stock Alert: %s - %s (Quantity: %d)%n", details[0], details[1], quantity);
                                    } else if (quantity > highStockThreshold) {
                                        System.out.printf("High Stock Alert: %s - %s (Quantity: %d)%n", details[0], details[1], quantity);
                                    }
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found: " + filePath);
                        } catch (IOException ex) {
                            System.out.println("Error reading file: " + filePath);
                        }
                    }
                    break;
                case 4: 
                    System.out.println("Taking you back to the main menu...");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    } while (flag);
}
}
