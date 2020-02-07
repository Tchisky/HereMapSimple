package ecoride.fstt.ac.ma.Repository.Modals;

public class BicycleBuilder {
    private int id;
    private String title;
    private String availabilityTimeRange;
    private String price;
    private int stationId;
    private boolean isAvailable;

    public BicycleBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BicycleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BicycleBuilder setAvailabilityTimeRange(String availabilityTimeRange) {
        this.availabilityTimeRange = availabilityTimeRange;
        return this;
    }

    public BicycleBuilder setPrice(String price) {
        this.price = price;
        return this;
    }

    public BicycleBuilder setStationId(int stationId) {
        this.stationId = stationId;
        return this;
    }

    public BicycleBuilder setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    public Bicycle createBicycle() {
        return new Bicycle(id, title, availabilityTimeRange, price, stationId, isAvailable);
    }
}