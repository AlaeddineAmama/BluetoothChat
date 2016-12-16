package com.example.android.bluetoothchat;

/**
 * Created by alaeddine on 12/04/16.
 */
public class Users{
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    public Users()
    {
    }
    public Users(int id,String name,String surname, String email,String password )
    {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;

    }
    public Users(String name,String surname, String email,String password )
    {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;

    }
    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return id+"\n"+name+"\n"+surname+"\n"+email+"\n"+password ;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}