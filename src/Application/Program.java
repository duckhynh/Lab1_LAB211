package Application;

import service.IService;
import service.Service;
import data_Object.Menu;

public class Program {
    public static void main(String[] args) {
        IService service = new Service();
        String productFile = "Product.txt";
        String brandFile = "01_Brand.txt";
        String categoryFile = "01_Category.txt";

        service.readFile(productFile);
        service.loadBrandData(brandFile);
        service.loadCategoryData(categoryFile);

        Menu menu = new Menu();
        menu.addItem("Create a new product");
        menu.addItem("Update a product");
        menu.addItem("Delete a product");
        menu.addItem("Search by name");
        menu.addItem("Print all lists from file");
        menu.addItem("Save changes to file");
        menu.addItem("Exit");

        boolean exit = false;
        while (!exit) {
            menu.showMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    do {
                        service.createProduct();
                    } while (!menu.confirmYesNo("Do you want to go back to Menu? (Y/N): "));
                    break;
                case 2:
                    do {
                        service.updateProduct();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 3:
                    do {
                        service.deleteProduct();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 4:
                    do {
                        service.findProductbyname();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                case 5:
                    do {
                        service.printAllProducts();
                    } while (!menu.confirmYesNo(" Do you want to go back to Menu? (Y/N): "));
                    break;
                    case 6:
                    service.writeFile(productFile);
                    System.out.println("Changes have been successfully saved.");
                    break;
                case 7:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        }
    }
}
