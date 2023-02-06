package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Validation {
    private static final Scanner sc = new Scanner(System.in);
    
    public static String getString(String msg, String err, String pattern, int mode) {
        while (true) {            
            System.out.print(msg);
            String s = sc.nextLine();
            //MODE:
            //1 - not allow to input null
            //2 - allow to input null
            if((s.matches(pattern) && mode == 1) 
                    || ((s.trim().isEmpty() || s.matches(pattern)) && mode == 2)) 
                return s;  
            System.err.println(err);
            System.out.println("Enter again!!!");
        }
    }
    
    public static String inputDate(int mode) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print("Enter date: ");
                //MODE:
                //1 - not allow to input null
                //2 - allow to input null
                String s = sc.nextLine();
                if(s.trim().isEmpty() && mode == 2) return s;
                Date date = df.parse(s);
                return df.format(date);
            } catch (ParseException e) {           
                System.out.println("Please input date format dd/MM/yyyy!!!");
            }        
        }
    }
    
     public static int getInt(String msg, int min ,int max, int mode){
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        
        while (true) {            
            try {
                System.out.print(msg);
                //MODE:
                //1 - not allow to input null 
                //2 - allow to input null
                String s = sc.nextLine();
                if(s.trim().isEmpty() && mode == 2) {
                    return -1;
                }
                int n = Integer.parseInt(s);
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println("PLEASE INPUT A NUMBER IN RANGE " + min + " -> " + max);
            } catch (NumberFormatException e) {
                System.err.println("WRONG FORMAT!!");
                System.out.println("Enter again!!!");
            }
        }
    }
     
    public static String inputStatus(int mode) {
        //Mode :
        //1 - not allow to input null
        //2 - allow to input null
        while (true) {    
            System.out.print("Enter status: ");
            String s = sc.nextLine();
            if(s.equalsIgnoreCase("Y")) return "true";
            if(s.equalsIgnoreCase("N")) return "false";
            if(mode == 2 && s.trim().isEmpty()) return s; 
            System.err.println("Please input only Y-N or y-n!!!");
            System.out.println("Enter again!!");
        }
    }
    
        public static boolean inputYN(String msg){
        while (true) {            
            System.out.print(msg);
            String s = sc.nextLine();
            if (s.trim().equalsIgnoreCase("y")) {
                return true;
            }else if (s.trim().equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("PLEASE INPUT ONLY Y-N!!");
            System.out.println("Enter again!!");
        }
    }
}
