package com.example.foodapp.model.Users;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "username")
    private final String username;

    @ColumnInfo(name = "email")
    private final String email;

    @ColumnInfo(name = "password")
    private final String password;

    @ColumnInfo(name = "balance")
    private final double balance;

    /*@ColumnInfo(name = "name") //TODO: remove?
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;*/

    /***
     * It represents an account.
     * @param username: the username of the account.
     * @param email: the email of the account.
     * @param password: the password of the account.
     * @param balance: the balance of the account as a double.
     */
    public User(final String username, final String email, final String password, final double balance) { //TODO: final Optional<String> name, final Optional<String> surname?
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //TODO: remove?
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

    /*public String getName() { //TODO: remove?
        return name;
    }

    public String getSurname() {
        return surname;
    }*/
}
