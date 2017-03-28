/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

/**
 *
 * @author RandyNguyen
 */
public class AdministratorTest {

    public AdministratorTest() {
    }

    /**
     * Test of addUser method, of class Administrator.
     */
    Administrator adm = new Administrator();
    File userLoginFile = new File("userLogin.txt");
    File adminLoginFile = new File("adminLogin.txt");

    @Test
    public void testAddUser() throws Exception {

        adm.addUser("Test", "1234");

        ArrayList<String> arr = new ArrayList<>();
        Scanner scanner = new Scanner(userLoginFile);

        String currentLine;

        while (scanner.hasNextLine() != false) {
            currentLine = scanner.nextLine();
            arr.add(currentLine);
        }

        //tests to see if user login and pin was added 
        assertTrue(arr.contains("Test1234"));

    }

    /**
     * Test of deleteUser method, of class Administrator.
     */
    @Test
    public void testDeleteUser() throws Exception {

        adm.deleteUser("Test", "1234");
        ArrayList<String> arr = new ArrayList<>();
        Scanner scanner = new Scanner(userLoginFile);

        String currentLine;

        while (scanner.hasNextLine() != false) {
            currentLine = scanner.nextLine();
            arr.add(currentLine);
        }
        //test to verify arr no longer contains Test1234
        assertFalse(arr.contains("Test1234"));

    }

    /**
     * Test of changePrivilegeFromUserToAdmin method, of class Administrator.
     */
    @Test
    public void testChangePrivilegeFromUserToAdmin() throws Exception {
        //adds user to userfile for testing
        adm.addUser("Test", "1234");

        adm.changePrivilegeFromUserToAdmin("Test", "1234");

        //user file > array
        ArrayList<String> userArr = new ArrayList<>();
        Scanner scanner = new Scanner(userLoginFile);

        String currentLine;

        while (scanner.hasNextLine() != false) {
            currentLine = scanner.nextLine();
            userArr.add(currentLine);
        }

        //admin file > array
        ArrayList<String> adminArr = new ArrayList<>();
        Scanner scanner2 = new Scanner(adminLoginFile);

        String currentLine2;

        while (scanner2.hasNextLine() != false) {
            currentLine2 = scanner2.nextLine();
            adminArr.add(currentLine2);
        }

        //test that login is no longer in user file and is now in admin file by checking array list in which they are put in
        assertTrue(adminArr.contains("Test1234"));
        assertFalse(userArr.contains("Test1234"));
    }

    /**
     * Test of changePrivilegeFromAdminToUser method, of class Administrator.
     */
    @Test
    public void testChangePrivilegeFromAdminToUser() throws Exception {
        //from previous test, admin file currently contains login.
        
        adm.changePrivilegeFromAdminToUser("Test", "1234");

        //user file > array
        ArrayList<String> userArr = new ArrayList<>();
        Scanner scanner = new Scanner(userLoginFile);

        String currentLine;

        while (scanner.hasNextLine() != false) {
            currentLine = scanner.nextLine();
            userArr.add(currentLine);
        }

        //admin file > array
        ArrayList<String> adminArr = new ArrayList<>();
        Scanner scanner2 = new Scanner(adminLoginFile);

        String currentLine2;

        while (scanner2.hasNextLine() != false) {
            currentLine2 = scanner2.nextLine();
            adminArr.add(currentLine2);
        }
        
        //test that user file has the login now and admin no longer does
        assertTrue(userArr.contains("Test1234"));
        assertFalse(adminArr.contains("Test1234"));
        
        //testing is completed so remove test login:
        adm.deleteUser("Test", "1234");
    }
    
    @Test
    public void removeFromMenu()throws IOException{
                Map<String, MenuItem> menu = new HashMap<String, MenuItem>();
                /*menu.put("Hamburger", new MenuItem("Hamburger", 10.50, 50));
                menu.put("Hotdog", new MenuItem("Hotdog", 3.50, 50));
                menu.put("Corndog", new MenuItem("Corndog", 4.00, 0));
                menu.put("Steak", new MenuItem("Steak", 1200.00, 3000));
                menu.put("Small Children", new MenuItem("Small Children", 3.50, 1));*/
                
                Map<String, MenuItem> menu2 = new HashMap<String, MenuItem>();
        
        File inFile = new File("menu.txt");
        Scanner inFileSC = new Scanner(inFile);
        
        while(inFileSC.hasNext()){
            String name = inFileSC.nextLine();
            double price = Double.parseDouble(inFileSC.nextLine());
            int inventory = Integer.parseInt(inFileSC.nextLine());
            MenuItem item = new MenuItem(name, price ,inventory);
            menu2.put(item.getName(), item);
        }
        
        Administrator temp = new Administrator();
        temp.deleteFromMenu("Hamburger");
        inFileSC.close();
        
        inFileSC = new Scanner(new File("menuTest.txt"));
        
        while(inFileSC.hasNext()){
            String name = inFileSC.nextLine();
            double price = Double.parseDouble(inFileSC.nextLine());
            int inventory = Integer.parseInt(inFileSC.nextLine());
            MenuItem item = new MenuItem(name, price ,inventory);
            menu.put(item.getName(), item);
        }
        
        inFileSC.close();
        
        assertEquals(menu.get("Hamburger"), null);
        assertEquals(menu2.get("Hamburger").getName(), "Hamburger");
    }
    
    @Test
    public void addToMenu()throws IOException{
        Map<String, MenuItem> menu = new HashMap<String, MenuItem>();
        File inFile = new File("menu.txt");
        Scanner inFileSC = new Scanner(inFile);
        
        while(inFileSC.hasNext()){
            String name = inFileSC.nextLine();
            double price = Double.parseDouble(inFileSC.nextLine());
            int inventory = Integer.parseInt(inFileSC.nextLine());
            MenuItem item = new MenuItem(name, price ,inventory);
            menu.put(item.getName(), item);
        }
        
        Administrator temp = new Administrator();
        menu.put("Hamburger", temp.addToMenu("Hamburger", 10.5, 50));
        assertEquals(menu.get("Hamburger").getName(), "Hamburger");
        assertEquals(menu.get("Hamburger").getPrice(), 10.5, 0);
        assertEquals(menu.get("Hamburger").getInventory(), 50, 0);
    }
    
    
}