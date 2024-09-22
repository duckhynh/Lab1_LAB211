package data_Object;

import java.util.List;

import ProductOject.Product;
import ProductOject.brand;
import ProductOject.catagory;

public interface IProductDao {
    // tạo sản phẩm 
    void createProduct(Product product);
    // update sản phẩm
    boolean updateProduct(Product product);
    //xóa sản phẩm
    void deleteProduct(String productId);
    // tìm sản phẩm theo id
    List<Product> findProductByNameProduct(String productname);
    Product findProductById(String productId);
    // in ra sản phẩm
    void printAllProducts();

    //check giống nhau
    boolean doesIdExist(String id);
    //boolean doesCategoryExist(String categoryId);

    // Các thao tác với tệp
    // void readFromFile(String fileName);
    // void writeToFile(String fileName);

    List<Product> getAllProducts();
    
}
