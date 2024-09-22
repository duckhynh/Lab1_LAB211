package ProductOject;

public class catagory {
    
    private String catagoryid;
    private String catagoryName;

    public catagory(String catagoryid, String catagoryName) {
        this.catagoryid = catagoryid;
        this.catagoryName = catagoryName;
    }

    public String getCatagoryid() {
        return catagoryid;
    }

    public void setCatagoryid(String catagoryid) {
        this.catagoryid = catagoryid;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }


    @Override
    public String toString() {
        String str =  String.format("| %-4s | %-15s |", catagoryid, catagoryName);
        return str;
    }

}
