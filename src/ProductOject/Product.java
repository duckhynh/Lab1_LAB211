package ProductOject;


public class Product {
    private String  id;
    private String name;
    private brand brandId;
    private catagory categoryId;
    private int modelYear;
    private double listPrice;

    
    // contructor
    public Product() {
    }


    public Product(String id, String name, brand brandId, catagory categoryId, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    
    //getter and setter

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public brand getBrandId() {
        return brandId;
    }


    public void setBrandId(brand brandId) {
        this.brandId = brandId;
    }


    public catagory getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(catagory categoryId) {
        this.categoryId = categoryId;
    }


    public int getModelYear() {
        return modelYear;
    }


    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }


    public double getListPrice() {
        return listPrice;
    }


    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    
    }

    // về sửa nên dùng %s 
    @Override
    public String toString() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-12s | %-12s | %-7s | %-15s | %-15s | %-10s | %-10s |\n", 
                      "ID", "Name", "Brand ID", "Brand Name", "Origin", "Category ID", "Category Name", "Model Year", "List Price");

    // Format string
    String str = String.format("| %-12s | %-20s | %-12s | %-12s | %-7s | %-15s | %-15s | %-10s | %-10.2f |", 
        id, 
        brandId.getBrandId(),         // String
        name,                         // String
        categoryId.getCatagoryid(),    // String
        brandId.getBrandName(),        // String
        brandId.getOrigin(),           // String
        categoryId.getCatagoryName(),  // String
        modelYear,                     // Integer (use %d for modelYear)
        listPrice);                    // Change %d to %f for Double
        
        return str;
    }
    
}
