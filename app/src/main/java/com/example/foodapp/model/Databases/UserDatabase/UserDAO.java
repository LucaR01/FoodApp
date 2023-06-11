package com.example.foodapp.model.Databases.UserDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.model.Users.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertList(User... users);

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM user WHERE userId = :id")
    void deleteUser(int id);

    @Delete
    void deleteUsers(User... users);

    //@Update
    @Query("UPDATE user SET username = :username, email = :email, password = :password WHERE userId = :id")
    void updateUser(final int id, final String username, final String email, final String password);

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("DELETE FROM user")
    void deleteAll();
}
