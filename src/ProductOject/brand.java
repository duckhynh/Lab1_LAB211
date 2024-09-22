package ProductOject;

public class brand {
    
    private String brandId;
    private String brandName;
    private String origin;


    public brand() {
    }


    public brand(String brandId, String brandName, String origin) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.origin = origin;
    }


    public String getBrandId() {
        return brandId;
    }


    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }


    public String getBrandName() {
        return brandName;
    }


    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    @Override
    public String toString() {
        String str = String.format("| %-4s | %-10s | %-10s |", brandId, brandName, origin);
        return str;
    }

    
}
