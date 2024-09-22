package filemananget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ProductOject.Product;
import ProductOject.brand;
import ProductOject.catagory;

public class Productfile implements IFileManget<Product> {
    
    private String fileName; 
    
    public Productfile(String fileName) {
        this.fileName = fileName;
    }

    
    @Override
    public boolean saveToFile(List<Product> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : list) {
                writer.write(product.getId() + "," 
                           + product.getName() + "," 
                           + product.getBrandId().getBrandId() + "," 
                           + product.getBrandId().getBrandName() + ","   
                           + product.getBrandId().getOrigin() + ","       
                           + product.getCategoryId().getCatagoryid() + "," 
                           + product.getCategoryId().getCatagoryName() + "," 
                           + product.getModelYear() + "," 
                           + product.getListPrice());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> readFromFile() {
        List<Product> productList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) {  // Adjusted for 9 fields
                    try {
                        String id = data[0].trim();
                        String name = data[1].trim();
                        String brandId = data[2].trim();
                        String brandName = data[3].trim();
                        String origin = data[4].trim();
                        String categoryId = data[5].trim();
                        String categoryName = data[6].trim();
                        int modelYear = Integer.parseInt(data[7].trim());
                        double listPrice = Double.parseDouble(data[8].trim());

                        brand brand = new brand(brandId, brandName, origin);
                        catagory category = new catagory(categoryId, categoryName);

                        productList.add(new Product(id, name, brand, category, modelYear, listPrice));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing product data: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return productList;
    }

}
