package CategoryLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ProductOject.brand;
import ProductOject.catagory;
import data_Object.Menu;
import filemananget.Categoryfile;
import tool.GetInput;


public class CategoryData implements ICategory {
    private List<catagory> categories = new ArrayList<>();
    private int lastValidChoice = 1;

    @Override
    public catagory getCategoryID(String s) {
        for (catagory c : categories) {
            if (c.getCatagoryid().equals(s)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void printAllCategory() {
        for (catagory c : categories) {
            System.out.println(c);
        }
    }

    @Override
    public void loadCategory(List<catagory> catagoryList) {
       this.categories = catagoryList;
    }

    @Override
    public catagory selectionCategory() {
        Menu categoryMenu = new Menu();
        for (int i = 0; i < categories.size(); i++) {
            catagory c = categories.get(i);
            categoryMenu.addItem(c.getCatagoryid() + ", " + c.getCatagoryName());
        }

        categoryMenu.showMenu();

        int choice = categoryMenu.getChoice();

        if (choice > 0 && choice <= categories.size()) {
            lastValidChoice = choice;
            return  categories.get(choice - 1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            return selectionCategory(); // Recursively call selection if invalid
        }
    }

    @Override
    public catagory newSelectionCategory() {
        Menu categoryMenu = new Menu();
        for (int i = 0; i < categories.size(); i++) {
            catagory c = categories.get(i);
            categoryMenu.addItem(c.getCatagoryid() + ", " + c.getCatagoryName());
        }
        categoryMenu.showMenu();

        String prompt = "Enter your choice (1-" + categories.size() + ") or press Enter to keep " + lastValidChoice + ": ";
        String input = GetInput.getNewString(prompt, Integer.toString(lastValidChoice));

        int choice = -1;
        try {
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > categories.size()) {
                System.out.println("Invalid choice. Please try again.");
                return newSelectionCategory(); 
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using last valid choice.");
            choice = lastValidChoice;
        }
        lastValidChoice = choice;
        return categories.get(choice - 1);
    }
}
