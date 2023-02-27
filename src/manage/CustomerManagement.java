package manage;

import datamodel.CustomerM;
import model.Customer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import validation.Validation;
/**
 *
 * @author DELL
 */
public class CustomerManagement implements CustomerM{
    private String ID, name, address, phone;
    private static final Map<String, String> map = new HashMap<>();
    
    public CustomerManagement(List<Customer> customers) {
        loadData(customers);
    } 
    
    public static String getCustomerName(String id) {
        return map.get(id);
    }
    
    public void createCustomer(List<Customer> customers) {
        ID = getCustomerID(customers, 1, true);
        name = Validation.getString("Enter customer's name: ", "name must not null!!!", "[a-zA-Z ]{1,30}", 1);
        address = Validation.getString("Enter customer's address: ", "Address must not null!!!", "[a-z A-Z]{2,30}", 1);
        phone = Validation.getString("Enter customer's phone number: ", "Phone number must have 10 -> 12 digits", "[0-9]{10,12}", 1);
        customers.add(new Customer(ID, name, address, phone));
        if(saveToFile(customers)) {
            map.put(ID, name);
            System.out.println("SUCCESS");
        } else {
            customers.remove(this);
        }
    }
    
    public void printCustomersList(List<Customer> customers) {
        if(customers.isEmpty()) {
            System.out.println("List customer is empty!!");
            return;
        }
        for (Customer o : customers) {
            System.out.println(o);
        }
    }
    
    public void searchCustomer(List<Customer> customers) {
        if(customers.isEmpty()) {
            System.out.println("List customer is empty!!");
            return;
        }
        ID = getCustomerID(customers, 2, false);
        for (Customer o : customers) {
            if (o.getID().equals(ID)) {
                System.out.println(o);
            }
        }
    }
   
    public void updateCustomer(List<Customer> customers) {
        if(customers.isEmpty()) {
            System.out.println("List customer is empty!!");
            return;
        }
        ID = getCustomerID(customers, 2, false);
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getID().equals(ID)) {
                name = Validation.getString("Enter customer's name: ", "name must not null!!!", "[a-zA-Z ]{1,30}", 2);
                if(name.isEmpty()) name = customers.get(i).getName();
                
                address = Validation.getString("Enter customer's address: ", "Address must not null!!!", "[a-z A-Z]{2,30}", 2);
                if(address.isEmpty()) address = customers.get(i).getAddress();
                
                phone = Validation.getString("Enter customer's phone number: ", "Phone number must have 10 -> 12 digits", "[0-9]{10,12}", 2);
                if(phone.isEmpty()) phone = customers.get(i).getPhone();
                Customer c = new Customer(ID, name, address, phone);
                Customer temp = customers.get(i);
                if(!customers.get(i).equals(c)) {
                    customers.set(i, c);
                    if(saveToFile(customers)) {
                        map.remove(ID);
                        map.put(ID, name);
                        System.out.println("SUCCESS");
                    } else {
                        customers.set(i, temp);
                    }
                    return;    
                } else {
                    System.out.println("UPDATE FAIL because there are not thing be updated!!");
                    return;
                }
            }            
        }
        System.out.println("UPDATE FAIL");
    }
    
    
    
    
    //private method
    private boolean saveToFile(List<Customer> customers) {
        try {
            File f = new File("customers.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Customer o : customers) {
                bw.write(o.toString());
                bw.newLine();
            }      

            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }
    }
    
    private static void loadData(List<Customer> customers) {
        try{
            FileReader fr = new FileReader("customers.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {            
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] words = line.split("[,]");
                String ID = words[0].trim(),
                name = words[1].trim(),
                address = words[2].trim(),
                phone = words[3].trim();               
                customers.add(new Customer(ID, name, address, phone));
                map.put(ID, name);
            }        
            br.close();
            fr.close();
        } catch (IOException e){
            System.out.println(e);
        }                 
    }
  
    private String getCustomerID(List<Customer> customers, int mode, boolean isContinue) {
        while (true) { 
            String tempID = Validation.getString("Enter customer's ID: ", "ID must had format Cxxx!!!", "^C\\d{3}", 1);
            //Mode:
            //1 - create
            //2 - delete or update or search
            if ((!checkDuplicateID(tempID, customers) && mode == 1) || (checkDuplicateID(tempID, customers) && mode == 2)) {
                return tempID;
            }
            if (mode == 1) {
                System.out.println("ID must not duplicate!!!");
            } else if(mode == 2 && isContinue == true){
                System.out.println("ID is not exist!!!");
            }       
            //this is use went you want user input only one time
            //and show the mess for user
            //(search, update, delete)
            if (!isContinue) {
                System.out.println("This customer does not exist");
                return tempID;
            }
        }
    }
    
    private boolean checkDuplicateID(String ID, List<Customer> customers) {
        for (Customer o : customers) {
            if (o.getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }
}
