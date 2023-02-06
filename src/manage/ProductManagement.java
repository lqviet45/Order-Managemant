package manage;

import model.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductManagement {

    public ProductManagement(List<Product> products) {
        loadData(products);
    }
    
    public void printProductsList(List<Product> products) {
        for (Product o : products) {
            System.out.println(o);
        }
    }
    
    
    public void saveToFile(List<Product> products){
        try {
            File f = new File("products.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Product o : products) {
                bw.write(o.toString());
                bw.newLine();
            }      

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    private static void loadData(List<Product> products) {
        try{
            FileReader fr = new FileReader("products.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {            
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] words = line.split("[,]");
                String ID = words[0].trim(),
                name = words[1].trim(),
                unit = words[2].trim(),
                origin = words[3].trim();
                double price = Double.parseDouble(words[4].trim());
                products.add(new Product(ID, name, unit, origin, price));
            }        
            br.close();
            fr.close();
        } catch (IOException e){
            System.err.println(e);
        }                 
    }
}
