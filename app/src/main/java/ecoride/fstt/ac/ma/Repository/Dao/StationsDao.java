package ecoride.fstt.ac.ma.Repository.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ecoride.fstt.ac.ma.Repository.Modals.Station;

@Dao
public interface StationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Station> stations);

    @Update
    void update(Station station);

    @Query("SELECT * FROM station")
    List<Station> getAllStations();

    @Query("SELECT * FROM station")
    LiveData<List<Station>> watchStations();

    @Query("SELECT * FROM station WHERE positionLat = :lat AND positionLng = :lng")
    Station search(float lat, float lng);

    @Query("SELECT * FROM station WHERE id = :id")
    Station search(int id);
}
