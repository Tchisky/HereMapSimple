package ecoride.fstt.ac.ma.Repository.Modals;

public class StationBuilder {
    private int id;
    private String name;
    private int availableBicyclesCount;
    private float positionLng;
    private float positionLat;

    public StationBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public StationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StationBuilder setAvailableBicyclesCount(int availableBicyclesCount) {
        this.availableBicyclesCount = availableBicyclesCount;
        return this;
    }

    public StationBuilder setPositionLng(float positionLng) {
        this.positionLng = positionLng;
        return this;
    }

    public StationBuilder setPositionLat(float positionLat) {
        this.positionLat = positionLat;
        return this;
    }

    public Station createBicycleStation() {
        return new Station(id, name, availableBicyclesCount, positionLng, positionLat);
    }
}