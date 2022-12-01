package com.example.foodapp.model.Databases.UserDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodapp.model.Users.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertList(User... users);

    @Query("SELECT * FROM user")
    List<User> getUsers();
}
