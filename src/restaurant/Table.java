package restaurant;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Table {
    private String order[];
    private int orderNum[];
    private Map<String, MenuItem> menu = new HashMap<>();
    
    public Table(Map<String, MenuItem> restaurantMenu) {
        menu = restaurantMenu;
        order = new String[menu.size()];
        orderNum = new int[menu.size()];
        IterateMenu();
        SetZeroOrder();
        
        for(int i = 0; i < menu.size(); i++) {
            System.out.println(order[i]);
            System.out.println(orderNum[i]);
        }
    }
    
    private void IterateMenu() {
        Iterator<HashMap.Entry<String, MenuItem>> entries = menu.entrySet().iterator();
        int i = 0;
        
        while (entries.hasNext()) {
            HashMap.Entry<String, MenuItem> entry = entries.next();
            order[i] = entry.getKey();
            i++;
        }
    }
    
    private void SetZeroOrder() {
        for(int i = 0; i < menu.size(); i++)
            orderNum[i] = 0;
    }
    
    public void AddOrderItem() {
        String search;
        int value;
        Scanner kbd = new Scanner(System.in);
        MenuItem compare;
        
        System.out.println("Avaiable Menu");
        for(int i = 0; i < menu.size(); i++) {
            System.out.println(order[i]);
        }
        System.out.println();
        System.out.print("Which Item? ");
        search = kbd.nextLine();
        System.out.println();
        System.out.print("Number: ");
        value = kbd.nextInt();
        for(int i = 0; i < menu.size(); i++) {
            if(search.toLowerCase().compareTo(order[i].toLowerCase()) == 0) {
                compare = menu.get(search);
                if(compare.getInventory() > value) {
                    orderNum[i] += value;
                    value = value - (2 * value);
                    compare.setInventory(value);
                } else
                    System.out.println("Not Enough Inventory");
            }
        }
    }
    
    public void DeleteOrderItem() {
        Scanner kbd = new Scanner(System.in);
        String search;
        
        System.out.println("Choose Item to Remove");
        for (int i = 0; i < menu.size(); i++) {
            if(orderNum[i] > 0)
                System.out.println(order[i]);
        }
        System.out.print("Choose Item: ");
        search = kbd.nextLine();
        
        for (int i = 0; i < menu.size(); i++) {
            if(search.toLowerCase().compareTo(order[i]) == 0)
                orderNum[i] -=1;
        }
    }
    
    private void CalculateBill() {
        double extPrice[] = new double[menu.size()];
        MenuItem value;
        
        for(int i = 0; i < menu.size(); i++) {
            if(orderNum[i] > 0) {
                value = menu.get(order[i]);
                extPrice[i] = (value.getPrice() * orderNum[i]);
            }
        }
        
        for(int i = 0; i < menu.size(); i++) {
            
        }
    }
    
    private void StoreBill(double billTotal) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("billsummary.txt", true));
        
        out.println("current date");
        out.println(billTotal);
        out.close();
    }
    
    private Map<String, MenuItem> UpdateMenu() {
        return menu;
    }	

    public Map<String, MenuItem> FinishTable() {
        CalculateBill();
        StoreBill();
        UpdateMenu();
        return menu;
    }
}
