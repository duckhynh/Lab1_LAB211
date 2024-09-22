package filemananget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ProductOject.brand;

public class Brandfile implements IFileManget<brand> {
    private String fileName;

    public Brandfile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<brand> readFromFile() {
        List<brand> brands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String origin = data.length > 2 ? data[2].trim() : "";
                    brands.add(new brand(id, name, origin));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + fileName);
            e.printStackTrace();
        }
        return brands;
    }

    @Override
    public boolean saveToFile(List<brand> brandList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (brand b : brandList) {
                writer.write(b.getBrandId() + "," + b.getBrandName() + "," + b.getOrigin());
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
