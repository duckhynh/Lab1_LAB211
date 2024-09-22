package filemananget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ProductOject.catagory;

public class Categoryfile implements IFileManget<catagory> {
    private String fileName;

    public Categoryfile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<catagory> readFromFile() {
        List<catagory> categories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    categories.add(new catagory(id, name));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + fileName);
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean saveToFile(List<catagory> categoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (catagory c : categoryList) {
                writer.write(c.getCatagoryid() + "," + c.getCatagoryName());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
            e.printStackTrace();
            return false;
        }
    }
}
