/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opi.rosokha.authorization;

/**
 *
 * @author Maksim
 */
public class User {
    private static final String PATH_TO_USER_STATUS = "Data\\User\\status.txt";
    
    public static final String ADMINISTRATOR = "administrator";
    public static final String REGISTERED_USER = "Registered user";
    public static final String NOT_REGISTERED_USER = "Not registered user";
    
    private String status;
    
    private int id;
    private String name = null;
    private String login = null;
    private String password = null;

    public User(){
        status = NOT_REGISTERED_USER;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
