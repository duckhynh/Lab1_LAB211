package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale.Category;

import BrandLayer.BrandData;
import CategoryLayer.CategoryData;
import ProductOject.Product;
import ProductOject.brand;
import ProductOject.catagory;
import data_Object.IProductDao;
import data_Object.ProductDao;
import filemananget.Brandfile;
import filemananget.Categoryfile;
import filemananget.Productfile;
import tool.GetInput;
import BrandLayer.IBrand;
import CategoryLayer.ICategory;

public class Service implements IService {
    private IProductDao productDao = new ProductDao();
    private IBrand brandData = new BrandData();
    private ICategory categoryData = new CategoryData();
    private List<Product> productList = new ArrayList<>(); // Initialize productList

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void createProduct() {
        String id;
        do {
            id = GetInput.getValidId("Enter product ID: ");
            if (productDao.doesIdExist(id)) {
                System.out.println("Product ID already exists. Please enter a different ID.");
            }
        } while (productDao.doesIdExist(id));

        String name = GetInput.getString("Enter product name: ");
        

        brand brand = brandData.selection();
        catagory category = categoryData.selectionCategory();

        int modelYear = GetInput.getInt("Enter model year: ", 1900, 2100);
        double listPrice = GetInput.getDouble("Enter product price: ", 0.0, Double.MAX_VALUE);

        Product product = new Product(id, name, brand, category, modelYear, listPrice);
        productDao.createProduct(product);
        System.out.println("Product created successfully.");
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void updateProduct() {
        String id = GetInput.getNewString("Enter product ID to update: ", "");
        Product existingProduct = productDao.findProductById(id);
        if (existingProduct == null) {
            System.out.println("Product does not exist.");
            return;
        }
    
        String name = GetInput.getNewString("Enter new product name (leave empty to keep current): ", existingProduct.getName());
    
        System.out.println("Select a new brand (leave empty to keep current):");
        brand newBrand = brandData.newSelection();  
        if (newBrand == null) {
            newBrand = existingProduct.getBrandId(); 
        }
    
        System.out.println("Select a new category (leave empty to keep current):");
        catagory newCategory = categoryData.newSelectionCategory();  
        if (newCategory == null) {
            newCategory = existingProduct.getCategoryId(); 
        }
    
        int modelYear = GetInput.getNewInt("Enter new model year: ", 1900, 2100, existingProduct.getModelYear());
    
        double listPrice = GetInput.getNewDouble("Enter new product price: ", 0.0, Double.MAX_VALUE, existingProduct.getListPrice());
    
        if (!name.isEmpty()) existingProduct.setName(name);
        if (newBrand != null) existingProduct.setBrandId(newBrand);
        if (newCategory != null) existingProduct.setCategoryId(newCategory);
        if (modelYear > 0) existingProduct.setModelYear(modelYear);
        if (listPrice > 0) existingProduct.setListPrice(listPrice);

        productDao.updateProduct(existingProduct);
        System.out.println("Product updated successfully.");
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void deleteProduct() {
        String id = GetInput.getValidId("Enter product ID to delete: ");
        if (productDao.findProductById(id) != null) {
            productDao.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void findProductbyname() {
        String nameString = GetInput.getString("Enter product name to find: ");
        List<Product> products = productDao.findProductByNameProduct(nameString);
        if (products != null && !products.isEmpty()) {
            products.sort((p1, p2) -> Integer.compare(p1.getModelYear(), p2.getModelYear()));
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
        System.out.println("Have no any Product.");
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void findProductById() {
        String id = GetInput.getValidId("Enter product ID to find: ");
        Product product = productDao.findProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("No product found with the given ID.");
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void printAllProducts() {
        productDao.printAllProducts();
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void readFile(String fileName) {
        Productfile productFile = new Productfile(fileName);
        List<Product> products = productFile.readFromFile();
        for (Product product : products) {
            productDao.createProduct(product); 
        }
    }

    @Override
    public void writeFile(String fileName) {
        Productfile productFile = new Productfile(fileName);
        productFile.saveToFile(productDao.getAllProducts());
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void loadBrandData(String brandFileName) {
        try {
            System.out.println("Loading brand data from: " + brandFileName);
            List<brand> brands = new Brandfile(brandFileName).readFromFile();
            brandData.loadBrands(brands);
        } catch (Exception e) {
            System.out.println("Error loading brand data from file: " + brandFileName);
            e.printStackTrace();
        }
    }

    @Override
    public void loadCategoryData(String categoryFileName) {
        try {
            System.out.println("Loading category data from: " + categoryFileName);
            List<catagory> categories = new Categoryfile(categoryFileName).readFromFile();
            categoryData.loadCategory(categories);
        } catch (Exception e) {
            System.out.println("Error loading category data from file: " + categoryFileName);
            e.printStackTrace();
        }
    }

    
    
}
