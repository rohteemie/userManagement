package com.rotimi;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserRepository {

    //This is the path to our database file.
    private final String FILE_PATH = "user.txt";

    //Instantiate the Logger class
    public static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    public void save(User user) {

        // 1. Format the data (JSON style)
        String objectInLine = user.getId() + "," + user.getName() + "," + user.getEmail() + "\n";

        // 2. The "Try-with-resources" block
        // This automatically closes the file even if an error occurs
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(objectInLine);
            System.out.println("User saved to file!");

        } catch (IOException e) {
            // Java forces us to handle the possibility that the disk is full
            // or the file is read-only
            LOGGER.log(Level.SEVERE, "Failed to save user to file", e);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        // Try-with-resources (automatically closes the reader)
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            // Read until the end of the file
            while ((line = br.readLine()) != null) {

                // 1. Split the CSV line
                String[] parts = line.split(",");

                // 2. Validate we have all parts (optional but safer)
                if (parts.length == 3) {

                    // 3. Parse the data
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];

                    // 4. Create User and add to the List
                    users.add(new User(id, name, email));
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.warning("Database file not found. Returning empty list.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading users from file", e);
        }

        return users;
    }
}
