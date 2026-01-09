package com.rotimi;

import java.util.List;

public class Main {
    public static void main (String[] args) {

        //Instantiate the repository to save/read data
        UserRepository dataBank = new UserRepository();

        //1. Create a new User instance (using the constructor)
        User myUser = new User(1,"Rotimi", "owo@owo.com");

        //2. Try to access the name (using the public getter)
//        System.out.println("User Name: " + myUser.getName());

        // 3. Try to update the name (using the public setter)
//        myUser.setName("Jane Doe");
//        System.out.println("Updated Name: " + myUser.getName());

        // UNCOMMENT THE LINE BELOW TO SEE AN ERROR:
        // myUser.name = "Test"; // This fails because 'name' is private!

        //Save the users to file
        dataBank.save(myUser);

        //Read information from file and save to allUsers
        List<User> allUsers = dataBank.findAll();

    }
}
