package com.romanceramic.mrp.controller;

import com.romanceramic.mrp.dao.UserDAO;
import com.romanceramic.mrp.model.User;

public class AuthController {
    private final UserDAO userDAO;
    private User currentUser;
    
    public AuthController() {
        this.userDAO = new UserDAO();
    }
    
    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        
        if (user != null && verifyPassword(password, user.getPassword())) {
            this.currentUser = user;
            return user;
        }
        
        return null;
    }
    
    private boolean verifyPassword(String inputPassword, String storedPassword) {
        return inputPassword.equals(storedPassword);
    }
    
    public void logout() {
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isAuthenticated() {
        return currentUser != null;
    }
    
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = userDAO.getUserById(userId);
        
        if (user != null && verifyPassword(oldPassword, user.getPassword())) {
            user.setPassword(newPassword);
            return userDAO.updateUser(user);
        }
        
        return false;
    }
}
