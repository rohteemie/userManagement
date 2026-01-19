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
    /**
     * Path to the user data file (CSV format)
     */
    private final String FILE_PATH = "user.txt";

    //Instantiate the Logger class
    public static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    /**
     * Save a new user to the file
     */
    public void save(User user) {
        String objectInLine = user.getId() + "," + user.getName() + "," + user.getEmail() + "\n";
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(objectInLine);
            System.out.println("User saved to file!");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save user to file", e);
        }
    }

    /**
     * Find all users in the file
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];
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

    /**
     * Find a user by ID
     */
    public User findById(int id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update a user by ID
     */
    public boolean update(int id, String name, String email) {
        List<User> users = findAll();
        boolean updated = false;
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(name);
                user.setEmail(email);
                updated = true;
                break;
            }
        }
        if (updated) {
            writeAll(users);
        }
        return updated;
    }

    /**
     * Delete a user by ID
     */
    public boolean delete(int id) {
        List<User> users = findAll();
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) {
            writeAll(users);
        }
        return removed;
    }

    /**
     * Write all users to the file (overwrite)
     */
    private void writeAll(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (User user : users) {
                String line = user.getId() + "," + user.getName() + "," + user.getEmail() + "\n";
                bw.write(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write users to file", e);
        }
    }
}
