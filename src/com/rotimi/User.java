package com.rotimi;

public class User {

//    Define the fields
//    Private fields (Encapsulation)
    private int id;
    private String name;
    private String email;

//    This is the constructor to initialize the object
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // 3. Getters (To read data)
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    // 4. Setters (To read data)
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }


//    Data Serialization to JSON format
}
