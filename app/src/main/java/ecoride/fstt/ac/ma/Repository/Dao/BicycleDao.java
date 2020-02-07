package ecoride.fstt.ac.ma.Repository.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;

@Dao
public interface BicycleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Bicycle> bicycles);

    @Query("SELECT * FROM bicycle WHERE isAvailable = 1 AND stationId = :stationId")
    List<Bicycle> getAllAvailableBicycles(int stationId);

    @Update
    void update(Bicycle bicycle);

    @Query("SELECT * FROM bicycle WHERE stationId = :stationId")
    List<Bicycle> getAllBicycles(int stationId);

    @Query("SELECT * FROM bicycle WHERE isAvailable = 1 AND stationId = :stationId")
    LiveData<List<Bicycle>> watchAvailableBicycles(int stationId);

    @Query("SELECT * FROM bicycle WHERE stationId = :stationId")
    LiveData<List<Bicycle>> watchBicycles(int stationId);

}
