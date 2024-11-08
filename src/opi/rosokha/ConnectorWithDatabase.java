/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opi.rosokha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import opi.rosokha.authorization.User;
import opi.rosokha.elements.Element;

/**
 *
 * @author Maksim
 */
public class ConnectorWithDatabase {
    private static Connection conn = null; //обєкт що представляє зєднання з БД
    
    
    public static void doConnect(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:Data\\information_system_on_the_elements_of_workout.db");
        } catch (Exception ex){
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static User login(String login, String password){
        String SQL= "SELECT * FROM `users` WHERE `login`=? AND `password`=?";
        PreparedStatement st = null;
        User user = new User();
        try {
            st=conn.prepareStatement(SQL);
            st.setString(1, login);
            st.setString(2, password);
        } catch (SQLException ex) {
            return null;
        }
        try {
            ResultSet rez=st.executeQuery();
            if(!rez.isBeforeFirst()) {
                return null;
            } 
            if(rez.next()){
                user.setId(Integer.parseInt(rez.getString("id")));
                user.setName(rez.getString("name"));
                user.setLogin(rez.getString("login"));
                user.setPassword(rez.getString("password"));
            }
            return user;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    public static boolean CheckLoginForUniqueness(String login){
        String SQL= "SELECT login FROM `users` WHERE `login`=?";
        PreparedStatement st = null;
        try {
            st=conn.prepareStatement(SQL);
            st.setString(1, login);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            ResultSet rez=st.executeQuery();
            if(!rez.isBeforeFirst()) {
                return true;
            } 
            if(!rez.next()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean addNewUser(String name, String login, String password){
        String SQL= "INSERT INTO users (name,login,password) VALUES (?,?,?);";
        PreparedStatement st = null;
        try {
            st=conn.prepareStatement(SQL);
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, password);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    public static String getGeneralInformation(String table){
        String SQL= "SELECT information FROM " + table + ";";
        PreparedStatement st = null;
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            return "Немає доступу до інформації";
        }
        try {
            ResultSet rez=st.executeQuery();
            if(!rez.next()){
               return "Немає інформації"; 
            }
            return rez.getString("information");
        } catch (SQLException ex) {
            return "Немає інформації";
        }
    }
    
    /*
    *Поки що не працює
    */
    public static boolean setInformationAboutElement(String name, Element element){
        String SQL= "SELECT name, type, description FROM elements WHERE name = '" + element.getName() + "';";
        PreparedStatement st = null;
        element.setName("");
        element.setType("");
        element.setDescription("");
        
        
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            ResultSet rez=st.executeQuery();
            if(!rez.isBeforeFirst()) {
                element.setName("Такого елементу не було знайдено");
                element.setType("Тип елементу: невідомо");
                return false;
            } 
            element.setName(rez.getString("name"));
            element.setType(rez.getString("type"));
            element.setDescription(rez.getString("description"));
            return true;
        } catch (SQLException ex) {
            element.setName("");
            element.setType("");
            element.setDescription("");
            return false;
        }
    }
    
    public static List<Element> getListOfElements(){
        String SQL= "SELECT * FROM elements;";
        PreparedStatement st = null;
        List<Element> elements = new ArrayList<>();
             
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return null;
        }
        try {
            ResultSet rez=st.executeQuery();
            if(!rez.isBeforeFirst()) {
                showMessageDialog(null, "У базі даних не було знайдено елементів" ,"Error",JOptionPane.ERROR_MESSAGE);
                return null;
            } 
            while(rez.next()){
                elements.add(new Element(Integer.parseInt(rez.getString("id")), rez.getString("name"), rez.getString("type"),rez.getString("description")));
            }
            return elements;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    public static boolean addElementToFavorites(Element element){
        String SQL= "INSERT INTO favorite_elements (id_user, id_element) VALUES ('"+FrmMain.getUser().getId()+"','"+element.getId()+"');";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static List<Element> getListOfFavoriteElements(){
        String SQL= "SELECT DISTINCT elements.* "
                + "FROM favorite_elements, elements, users "
                + "WHERE users.id = " + FrmMain.getUser().getId() +" "
                + "AND elements.id = favorite_elements.id_element "
                + "AND users.id = favorite_elements.id_user;";
        PreparedStatement st = null;
        List<Element> elements = new ArrayList<>();
             
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return null;
        }
        try {
            ResultSet rez=st.executeQuery();
            while(rez.next()){
                elements.add(new Element(Integer.parseInt(rez.getString("id")), rez.getString("name"), rez.getString("type"),rez.getString("description")));
            }
            return elements;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static boolean deleteElementFromFavorites(Element element){
        String SQL= "DELETE FROM favorite_elements WHERE id_user = " + FrmMain.getUser().getId() + " AND id_element = " + element.getId() + ";";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean addElementToStudied(Element element){
        String SQL= "INSERT INTO studied_elements (id_user, id_element) VALUES ('"+FrmMain.getUser().getId()+"','"+element.getId()+"');";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static List<Element> getListOfStudiedElements(){
        String SQL= "SELECT DISTINCT elements.* "
                + "FROM studied_elements, elements, users "
                + "WHERE users.id = " + FrmMain.getUser().getId() +" "
                + "AND elements.id = studied_elements.id_element "
                + "AND users.id = studied_elements.id_user;";
        PreparedStatement st = null;
        List<Element> elements = new ArrayList<>();
             
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return null;
        }
        try {
            ResultSet rez=st.executeQuery();
            while(rez.next()){
                elements.add(new Element(Integer.parseInt(rez.getString("id")), rez.getString("name"), rez.getString("type"),rez.getString("description")));
            }
            return elements;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static boolean deleteElementFromStudied(Element element){
        String SQL= "DELETE FROM studied_elements WHERE id_user = " + FrmMain.getUser().getId() + " AND id_element = " + element.getId() + ";";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean addNewElement(String name, String type, String description){
        String SQL= "INSERT INTO elements(name,type,description) VALUES (?,?,?);";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
            st.setString(1, name);
            st.setString(2, type);
            st.setString(3, description);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean editElement(int idElement, String name, String type, String description){
        String SQL= "UPDATE elements SET name = ?, type = ?, description = ? WHERE id = ?;";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
            st.setString(1, name);
            st.setString(2, type);
            st.setString(3, description);
            st.setString(4, idElement+"");
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean deleteElement(Element element){
        if(!deleteElementFromFavorite(element.getId()) && !deleteElementFromStudied(element.getId())){
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        String SQL= "DELETE FROM elements WHERE id = "+element.getId()+";";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private static boolean deleteElementFromFavorite(int id){
        String SQL= "DELETE FROM favorite_elements "
                + "WHERE id_element = "+id+";";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private static boolean deleteElementFromStudied(int id){
        String SQL= "DELETE FROM studied_elements "
                + "WHERE id_element = "+id+";";
        PreparedStatement st = null;
  
        try {
            st=conn.prepareStatement(SQL);
        } catch (SQLException ex) {
            return false;
        }
        try {
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static int getIdUserByLogin(String login){
        String SQL= "SELECT id "
                + "FROM users "
                + "WHERE login = ?;";
        PreparedStatement st = null;
             
        try {
            st=conn.prepareStatement(SQL);
            st.setString(1, login);
        } catch (SQLException ex) {
            showMessageDialog(null, "Помилка під час встановлення з’єднання з базою даних" ,"Error",JOptionPane.QUESTION_MESSAGE);
            return 0;
        }
        try {
            ResultSet rez=st.executeQuery();
            if(rez.next()){
                return rez.getInt("id");
            }
        } catch (SQLException ex) {
            return 0;
        }
        return 0;
    }
}
