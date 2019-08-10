package com.example.anuj.ccetpaperbank;

public class User {
    public String Name,Roll,Email;

    public User(){}

    public User(String Name, String Roll, String Email) {
        this.Name = Name;
        this.Roll = Roll;
        this.Email = Email;
    }

    public String getName1() {
        return Name;
    }

    public void setName1(String name1) {
        Name = name1;
    }

    public String getRoll1() {
        return Roll;
    }

    public void setRoll1(String roll1) {
        Roll = roll1;
    }

    public String getEmail1() {
        return Email;
    }

    public void setEmail1(String email1) {
        Email = email1;
    }
}

