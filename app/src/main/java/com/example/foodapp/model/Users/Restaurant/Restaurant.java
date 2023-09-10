package com.example.foodapp.model.Users.Restaurant;

import com.example.foodapp.model.Users.User;

//TODO: remove?
public class Restaurant extends User {

    /***
     * It represents a Restaurant as an instance of a {@link com.example.foodapp.model.Users.User}.
     * @param username: the username of the restaurant.
     * @param email: the email of the restaurant.
     * @param password: the password of the restaurant's account.
     * @param balance: the balance of the restaurant's account.
     */
    public Restaurant(final String username, final String email, final String password, final double balance) {
        super(username, email, password, balance);
    }
}
