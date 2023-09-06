package com.example.foodapp.model.Users.Client;

import com.example.foodapp.model.Users.User;

import java.util.Optional;

public class Client extends User {

    public Client(final String username, final String email, final String password, final double balance) {
        super(username, email, password, balance);
    }
}
