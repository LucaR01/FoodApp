package com.example.foodapp.model.Users.Restaurant;

import com.example.foodapp.model.Users.User;

import java.util.Optional;

public class Restaurant extends User {

    public Restaurant(final String username, final String email, final String password, final double balance) {
        super(username, email, password, balance);
    }
}
