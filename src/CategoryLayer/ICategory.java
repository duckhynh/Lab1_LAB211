package CategoryLayer;

import java.util.ArrayList;
import java.util.List;

import ProductOject.brand;
import ProductOject.catagory;

public interface ICategory {
    
    catagory getCategoryID(String s);
    
    void loadCategory(List<catagory> catagoryList); 

    void printAllCategory();

    catagory selectionCategory();

    catagory newSelectionCategory();
}
