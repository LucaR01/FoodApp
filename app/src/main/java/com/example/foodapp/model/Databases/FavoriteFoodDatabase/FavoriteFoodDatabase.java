package com.example.foodapp.model.Databases.FavoriteFoodDatabase;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.foodapp.model.Databases.FoodDatabase.FoodDatabase;
import com.example.foodapp.model.Food.Food;

import java.util.Objects;

@Database(entities = {Food.class}, version = 2)
public abstract class FavoriteFoodDatabase extends RoomDatabase {

    public abstract FavoriteFoodDAO favoriteFoodDAO();

    private static FavoriteFoodDatabase INSTANCE;
    private static final String DATABASE_NAME = "favoriteFoods";

    public static FavoriteFoodDatabase getDatabaseInstance(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(Objects.isNull(FavoriteFoodDatabase.INSTANCE)) {
                FavoriteFoodDatabase.INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FavoriteFoodDatabase.class, FavoriteFoodDatabase.DATABASE_NAME)
                        .allowMainThreadQueries() //TODO: remove .allowMainThreadQueries()?
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return FavoriteFoodDatabase.INSTANCE;
    }
}
