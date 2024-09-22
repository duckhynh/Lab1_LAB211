package data_Object;

import java.util.List;

import CategoryLayer.CategoryData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ProductOject.Product;
import ProductOject.brand;
import ProductOject.catagory;
import filemananget.Productfile;
import tool.GetInput;


public class ProductDao implements IProductDao {
    
    ArrayList<Product> productList = new ArrayList<>();
    List<brand> brands = new ArrayList<>();
    List<catagory> categories = new ArrayList<>();

//--------------------------------------------------------------------------------------------------------
    @Override
    public void createProduct(Product product) {
            productList.add(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equalsIgnoreCase(product.getId())) {
                productList.set(i, product);
                System.out.println("Product has been successfully updated.");
                return true;
            }
        }
        System.out.println("Product not found.");
        return false;
    }

    @Override
    public void deleteProduct(String productId) {
        productList.removeIf(product -> product.getId().equals(productId));
        System.out.println("Product has been successfully deleted.");
    }

    @Override
    public List<Product> findProductByNameProduct(String namePart) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().contains(namePart)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    @Override
    public Product findProductById(String productId) {
        for (Product product : productList) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
    
        Productfile productFile = new Productfile("Product.txt");
        List<Product> productsFromFile = productFile.readFromFile();
    
        for (Product product : productsFromFile) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
    
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void printAllProducts() {
        if (productList.isEmpty()) {
        System.out.println("No products available.");
        return;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-12s | %-20s | %-12s | %-12s | %-7s | %-15s | %-15s | %-10s | %-10s |\n", 
                  "No.", "ID", "Name", "Brand ID", "Brand Name", "Origin", "Category ID", "Category Name", "Model Year", "List Price");

    System.out.println("|------|--------------|----------------------|--------------|--------------|---------|-----------------|-----------------|------------|------------|");
    int index = 1;
    for (Product product : productList) {
        brand brand = product.getBrandId(); // Assuming getBrand() returns Brand object
        catagory category = product.getCategoryId(); // Assuming getCategory() returns Category object

        System.out.printf("| %-4d | %-12s | %-20s | %-12s | %-12s | %-7s | %-15s | %-15s | %-10d | %-10.2f |\n",
                          index++,
                          product.getId(),
                          product.getName(),
                          brand != null ? brand.getBrandId() : "",
                          brand != null ? brand.getBrandName() : "",
                          brand != null ? brand.getOrigin() : "",
                          category != null ? category.getCatagoryid() : "",
                          category != null ? category.getCatagoryName() : "",
                          product.getModelYear(),
                          product.getListPrice());
    }
    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }

//--------------------------------------------------------------------------------------------------------
    @Override
    public boolean doesIdExist(String id) {
        for (Product product : productList) {
            if (product.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
    
        // Check in the file if not found in the current list
        Productfile productFile = new Productfile("Product.txt");
        List<Product> productsFromFile = productFile.readFromFile();
    
        for (Product product : productsFromFile) {
            if (product.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
    
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    
}
