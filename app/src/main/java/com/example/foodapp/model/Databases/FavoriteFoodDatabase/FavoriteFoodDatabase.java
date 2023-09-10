package com.example.foodapp.model.Databases.FavoriteFoodDatabase;

import android.content.Context;
import android.os.Build;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapp.model.Food.Food;

import java.util.Objects;

@Database(entities = {Food.class}, version = 3)
public abstract class FavoriteFoodDatabase extends RoomDatabase {

    public abstract FavoriteFoodDAO favoriteFoodDAO();

    private static FavoriteFoodDatabase INSTANCE;
    private static final String DATABASE_NAME = "favoriteFoods";

    public static FavoriteFoodDatabase getDatabaseInstance(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(Objects.isNull(FavoriteFoodDatabase.INSTANCE)) {
                FavoriteFoodDatabase.INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FavoriteFoodDatabase.class, FavoriteFoodDatabase.DATABASE_NAME)
                        .allowMainThreadQueries() //TODO: remove .allowMainThreadQueries()?
                        .fallbackToDestructiveMigration() //TODO: remove when the app will be released.
                        .build();
            }
        }

        return FavoriteFoodDatabase.INSTANCE;
    }
}
