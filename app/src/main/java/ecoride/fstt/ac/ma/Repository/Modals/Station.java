package ecoride.fstt.ac.ma.Repository.Modals;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Station {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int availableBicyclesCount;
    private float positionLng;
    private float positionLat;

    public Station(int id, String name, int availableBicyclesCount, float positionLng, float positionLat) {
        this.id = id;
        this.name = name;
        this.availableBicyclesCount = availableBicyclesCount;
        this.positionLng = positionLng;
        this.positionLat = positionLat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableBicyclesCount() {
        return availableBicyclesCount;
    }

    public void setAvailableBicyclesCount(int availableBicyclesCount) {
        this.availableBicyclesCount = availableBicyclesCount;
    }

    public float getPositionLng() {
        return positionLng;
    }

    public void setPositionLng(float positionLng) {
        this.positionLng = positionLng;
    }

    public float getPositionLat() {
        return positionLat;
    }

    public void setPositionLat(float positionLat) {
        this.positionLat = positionLat;
    }
}
