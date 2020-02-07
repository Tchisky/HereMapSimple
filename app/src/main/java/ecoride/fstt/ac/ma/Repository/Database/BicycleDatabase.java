package ecoride.fstt.ac.ma.Repository.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ecoride.fstt.ac.ma.Repository.Dao.BicycleDao;
import ecoride.fstt.ac.ma.Repository.Dao.StationsDao;
import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;
import ecoride.fstt.ac.ma.Repository.Modals.Station;

@Database(entities = {Bicycle.class, Station.class}, version = 1, exportSchema = false)
public abstract class BicycleDatabase extends RoomDatabase {

    private static BicycleDatabase instance;

    public abstract StationsDao stationsDao();

    public abstract BicycleDao bicycleDao();

    public static synchronized BicycleDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BicycleDatabase.class, "bicycles_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
