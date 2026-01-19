package com.rotimi;

public class User {

    /**
     * User fields (Encapsulation)
     */
    private int id;
    private String name;
    private String email;

    /**
     * Constructor to initialize User object
     */
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Getters (read-only access)
     */
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    /**
     * Setters (update fields)
     */
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Serialize User to JSON string (using Gson)
     */
    public String toJson() {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        return gson.toJson(this);
    }
}
