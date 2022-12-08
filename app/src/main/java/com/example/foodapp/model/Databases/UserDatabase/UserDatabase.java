package com.example.foodapp.model.Databases.UserDatabase;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapp.model.Users.Client.Client;
import com.example.foodapp.model.Users.Restaurant.Restaurant;
import com.example.foodapp.model.Users.User;

import java.util.Objects;

@Database(entities = {User.class}, version = 1) //TODO: , Client.class, Restaurant.class
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();

    private static UserDatabase INSTANCE;
    private static final String databaseName = "user";

    //@RequiresApi(api = Build.VERSION_CODES.N)
    public static UserDatabase getDatabaseInstance(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(Objects.isNull(UserDatabase.INSTANCE)) {
                UserDatabase.INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, UserDatabase.databaseName)
                        .allowMainThreadQueries() //TODO: remove .allowMainThreadQueries()?
                        .build();
            }
        }
        return UserDatabase.INSTANCE;
    }
}
