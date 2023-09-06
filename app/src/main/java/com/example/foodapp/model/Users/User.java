package com.example.foodapp.model.Users;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Optional;

@Entity(tableName = "user")
public class User {

    private final static String EMPTY_STRING = "";

    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "balance")
    private double balance;

    /*@ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;*/

    public User(final String username, final String email, final String password, final double balance) { //TODO: final Optional<String> name, final Optional<String> surname
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.name = name.orElse(User.EMPTY_STRING);
            this.surname = surname.orElse(User.EMPTY_STRING);
        }*/
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public double getBalance() {
        return this.balance;
    }

    /*public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }*/
}
