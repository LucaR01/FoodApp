package com.example.foodapp.model.Databases.FoodDatabase;

import android.content.Context;
import android.os.Build;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodapp.model.Food.Food;

import java.util.Objects;

@Database(entities = {Food.class}, version = 2)
public abstract class FoodDatabase extends RoomDatabase {

    public abstract FoodDAO foodDAO();

    private static FoodDatabase INSTANCE;
    private static final String DATABASE_NAME = "food";

    public static FoodDatabase getDatabaseInstance(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(Objects.isNull(FoodDatabase.INSTANCE)) {
                FoodDatabase.INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FoodDatabase.class, FoodDatabase.DATABASE_NAME)
                        .allowMainThreadQueries() //TODO: remove .allowMainThreadQueries()?
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return FoodDatabase.INSTANCE;
    }

}
