package ecoride.fstt.ac.ma.Utils;

import java.util.ArrayList;
import java.util.List;

import ecoride.fstt.ac.ma.Repository.Dao.BicycleDao;
import ecoride.fstt.ac.ma.Repository.Dao.StationsDao;
import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;
import ecoride.fstt.ac.ma.Repository.Modals.BicycleBuilder;
import ecoride.fstt.ac.ma.Repository.Modals.Station;
import ecoride.fstt.ac.ma.Repository.Modals.StationBuilder;

public class Utils {

    private static List<Bicycle> getBicycles() {
        List<Bicycle> list = new ArrayList<>();

        list.add(
                new BicycleBuilder()
                        .setId(1)
                        .setTitle("Bicycle title 1")
                        .setAvailabilityTimeRange("From 8am to 19pm")
                        .setPrice("$1.1/hour")
                        .setIsAvailable(true)
                        .setStationId(1)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(2)
                        .setTitle("Bicycle title 2")
                        .setAvailabilityTimeRange("From 9am to 18pm")
                        .setPrice("$1.2/hour")
                        .setStationId(1)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(3)
                        .setTitle("Bicycle title 3")
                        .setAvailabilityTimeRange("From 10am to 17pm")
                        .setPrice("$1.3/hour")
                        .setStationId(1)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(4)
                        .setTitle("Bicycle title 4")
                        .setAvailabilityTimeRange("From 9am to 20pm")
                        .setPrice("$1.4/hour")
                        .setStationId(2)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(5)
                        .setTitle("Bicycle title 5")
                        .setAvailabilityTimeRange("From 15pm to 21pm")
                        .setPrice("$1.5/hour")
                        .setStationId(2)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(6)
                        .setTitle("Bicycle title 6")
                        .setAvailabilityTimeRange("From 20pm to 00am")
                        .setPrice("$1.5/hour")
                        .setStationId(3)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(7)
                        .setTitle("Bicycle title 7")
                        .setAvailabilityTimeRange("From 19pm to 00am")
                        .setPrice("$1.55/hour")
                        .setStationId(3)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(8)
                        .setTitle("Bicycle title 8")
                        .setAvailabilityTimeRange("From 18pm to 00am")
                        .setPrice("$1.6/hour")
                        .setStationId(4)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(9)
                        .setTitle("Bicycle title 9")
                        .setAvailabilityTimeRange("From 20pm to 23pm")
                        .setPrice("$1.2/hour")
                        .setStationId(4)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        list.add(
                new BicycleBuilder()
                        .setId(10)
                        .setTitle("Bicycle title 10")
                        .setAvailabilityTimeRange("From 20pm to 21:30am")
                        .setPrice("$1.5/hour")
                        .setStationId(4)
                        .setIsAvailable(true)
                        .createBicycle()
        );
        return list;
    }

    private static List<Station> getStations() {
        List<Station> list = new ArrayList<>();
        list.add(
                new StationBuilder()
                        .setId(1)
                        .setName("Station 1")
                        .setAvailableBicyclesCount(3)
                        .setPositionLat(35.732323f)
                        .setPositionLng(-5.836865f)
                        .createBicycleStation()
        );
        list.add(
                new StationBuilder()
                        .setId(2)
                        .setName("Station 2")
                        .setAvailableBicyclesCount(2)
                        .setPositionLat(35.743882f)
                        .setPositionLng(-5.865659f)
                        .createBicycleStation()
        );
        list.add(
                new StationBuilder()
                        .setId(3)
                        .setName("Station 3")
                        .setAvailableBicyclesCount(2)
                        .setPositionLat(35.752033f)
                        .setPositionLng(-5.853002f)
                        .createBicycleStation()
        );
        list.add(
                new StationBuilder()
                        .setId(4)
                        .setName("Station 4")
                        .setAvailableBicyclesCount(3)
                        .setPositionLat(35.748175f)
                        .setPositionLng(-5.839932f)
                        .createBicycleStation()
        );

        return list;
    }

    public static void insertBicyclesIntoDatabase(BicycleDao dao) {
        dao.insert(getBicycles());
    }

    public static void insertBicyclesStations(StationsDao stationsDao) {
        stationsDao.insert(getStations());
    }

}
