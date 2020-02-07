package ecoride.fstt.ac.ma.Repository.Modals;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(
        entity = Station.class,
        parentColumns = "id",
        childColumns = "stationId",
        onDelete = CASCADE,
        onUpdate = CASCADE
))
public class Bicycle {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String availabilityTimeRange;
    private String price;
    private int stationId;
    private boolean isAvailable;

    public Bicycle(int id, String title, String availabilityTimeRange, String price, int stationId, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.availabilityTimeRange = availabilityTimeRange;
        this.price = price;
        this.stationId = stationId;
        this.isAvailable = isAvailable;
    }

    public int getStationId() {
        return stationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvailabilityTimeRange() {
        return availabilityTimeRange;
    }

    public void setAvailabilityTimeRange(String availabilityTimeRange) {
        this.availabilityTimeRange = availabilityTimeRange;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
