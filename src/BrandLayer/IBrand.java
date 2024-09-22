package BrandLayer;

import java.util.List;
import ProductOject.brand;

public interface IBrand {
    brand getBrandId(String s);
    
    void printAllBrand();

    void loadBrands(List<brand> brandList);

    brand selection();
    
    brand newSelection();
}
