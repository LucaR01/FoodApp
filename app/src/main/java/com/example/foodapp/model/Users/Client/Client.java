package com.example.foodapp.model.Users.Client;

import com.example.foodapp.model.Users.User;

public class Client extends User {

    /***
     * This class represents a {@link User} of type Client.
     * @param username the username of the client.
     * @param email the email of the client.
     * @param password the password of his/her account.
     * @param balance his/her account's balance.
     */
    public Client(final String username, final String email, final String password, final double balance) {
        super(username, email, password, balance);
    }
}
