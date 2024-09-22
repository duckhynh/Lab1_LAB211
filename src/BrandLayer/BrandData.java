package BrandLayer;

import java.util.ArrayList;
import java.util.List;
import ProductOject.brand;
import data_Object.Menu;
import tool.GetInput;

public class BrandData implements IBrand {
    private List<brand> brands = new ArrayList<>();
    private int lastValidChoice = 1;

    @Override
    public brand getBrandId(String s) {
        for (brand b : brands) {
            if (b.getBrandId().equals(s)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void printAllBrand() {
        for (brand b : brands) {
            System.out.println(b);
        }
    }

    @Override
    public void loadBrands(List<brand> brandList) {
        this.brands = brandList;
    }

    @Override
    public brand selection() {
        Menu brandMenu = new Menu();
        for (int i = 0; i < brands.size(); i++) {
            brand b = brands.get(i);
            brandMenu.addItem(b.getBrandId() + ", " + b.getBrandName() + ", " + b.getOrigin());
        }
        brandMenu.showMenu();
        int choice = brandMenu.getChoice();
        if (choice > 0 && choice <= brands.size()) {
            lastValidChoice = choice;
            return brands.get(choice - 1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            return selection(); 
        }
    }

    @Override
    public brand newSelection() {
        Menu brandMenu = new Menu();
        for (int i = 0; i < brands.size(); i++) {
            brand b = brands.get(i);
            brandMenu.addItem(b.getBrandId() + ", " + b.getBrandName() + ", " + b.getOrigin());
        }
        brandMenu.showMenu();

        String prompt = "Enter your choice (1-" + brands.size() + ") or press Enter to keep " + lastValidChoice + ": ";
        String input = GetInput.getNewString(prompt, Integer.toString(lastValidChoice));

        int choice = -1;
        try {
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > brands.size()) {
                System.out.println("Invalid choice. Please try again.");
                return newSelection(); 
            }
        } catch (NumberFormatException e) {
            
            choice = lastValidChoice;
        }

        lastValidChoice = choice; 
        return brands.get(choice - 1);
    }
} 

