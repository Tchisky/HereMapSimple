package ecoride.fstt.ac.ma.UI.Activities.Activities;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.here.android.mpa.cluster.ClusterLayer;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ecoride.fstt.ac.ma.R;
import ecoride.fstt.ac.ma.Repository.Dao.BicycleDao;
import ecoride.fstt.ac.ma.Repository.Dao.StationsDao;
import ecoride.fstt.ac.ma.Repository.Database.BicycleDatabase;
import ecoride.fstt.ac.ma.Repository.Modals.Bicycle;
import ecoride.fstt.ac.ma.Repository.Modals.Station;
import ecoride.fstt.ac.ma.UI.Activities.Others.OnBicycleClick;
import ecoride.fstt.ac.ma.UI.Activities.Others.PromptBottomSheet;
import ecoride.fstt.ac.ma.Utils.Utils;

public class MainActivity extends AppCompatActivity implements OnBicycleClick {

    private Map map = null;
    private AndroidXMapFragment mapFragment = null;
    private BicycleDao bicycleDao;
    private StationsDao stationsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init dao
        bicycleDao = BicycleDatabase.getInstance(this).bicycleDao();
        stationsDao = BicycleDatabase.getInstance(this).stationsDao();

        // first thing first insert dummy data int db
        Utils.insertBicyclesStations(stationsDao);
        Utils.insertBicyclesIntoDatabase(bicycleDao);

        mapFragment = (AndroidXMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);

    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean success = com.here.android.mpa.common.MapSettings.setIsolatedDiskCacheRootPath(
                getApplicationContext().getExternalFilesDir(null) + File.separator + ".here-maps",
                "{YOUR_INTENT_NAME}"); /* ATTENTION! Do not forget to update {YOUR_INTENT_NAME} */

        if (!success) {
            Toast.makeText(getApplicationContext(), "Unable to set isolated disk cache path.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Loading Map, Please be patient", Toast.LENGTH_LONG).show();
            mapFragment.init(new OnEngineInitListener() {
                @Override
                public void onEngineInitializationCompleted(Error error) {
                    if (error == OnEngineInitListener.Error.NONE) {
                        map = mapFragment.getMap();
                        map.setCenter(new GeoCoordinate(35.7672700, -5.7997500, 0.0), Map.Animation.NONE);
                        map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);

                        // watch available bicycles data in database and load them on top of the map
                        stationsDao.watchStations().observe(MainActivity.this, new Observer<List<Station>>() {
                            @Override
                            public void onChanged(List<Station> stations) {
                                displayMarkers(stations);
                            }
                        });

                        // listen on map events
                        if (mapFragment.getMapGesture() != null)
                            mapFragment.getMapGesture().addOnGestureListener(mMyGestureHandler, Integer.MAX_VALUE, true);

                    } else {
                        System.out.println("ERROR: Cannot initialize Map Fragment");
                    }
                }
            });
        }

    }

    private MapGesture.OnGestureListener mMyGestureHandler = new MapGesture.OnGestureListener() {
        @Override
        public void onPanStart() {

        }

        @Override
        public void onPanEnd() {

        }

        @Override
        public void onMultiFingerManipulationStart() {

        }

        @Override
        public void onMultiFingerManipulationEnd() {

        }

        @Override
        public boolean onMapObjectsSelected(List<ViewObject> objects) {
            for (ViewObject viewObj : objects) {
                if (viewObj.getBaseType() == ViewObject.Type.USER_OBJECT) {
                    if (((MapObject) viewObj).getType() == MapObject.Type.MARKER) {
                        MapMarker selectedMarker = ((MapMarker) viewObj);
                        GeoCoordinate geoCoordinate = selectedMarker.getCoordinate();
                        Station selectedStation = stationsDao.search(
                                (float) geoCoordinate.getLatitude(),
                                (float) geoCoordinate.getLongitude()
                        );
                        // we found a station where the user has just clicked
                        if (selectedStation != null) {
                            PromptBottomSheet bottomSheet = new PromptBottomSheet(MainActivity.this);
                            bottomSheet.setStationId(selectedStation.getId());
                            bottomSheet.show(getSupportFragmentManager(), "");
                            return true;
                        }
                        // no station found
                        else return false;
                    }
                }
            }
            // return false to allow the map to handle this callback also
            return false;
        }

        @Override
        public boolean onTapEvent(PointF pointF) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(PointF pointF) {
            return false;
        }

        @Override
        public void onPinchLocked() {

        }

        @Override
        public boolean onPinchZoomEvent(float v, PointF pointF) {
            return false;
        }

        @Override
        public void onRotateLocked() {

        }

        @Override
        public boolean onRotateEvent(float v) {
            return false;
        }

        @Override
        public boolean onTiltEvent(float v) {
            return false;
        }

        @Override
        public boolean onLongPressEvent(PointF pointF) {
            return false;
        }

        @Override
        public void onLongPressRelease() {

        }

        @Override
        public boolean onTwoFingerTapEvent(PointF pointF) {
            return false;
        }
    };

    /**
     * loads incoming list of stations on top of the map
     *
     * @param bicycles
     */
    private void displayMarkers(List<Station> bicycles) {
        ClusterLayer clusterLayer = new ClusterLayer();

        for (Station b : bicycles)
            clusterLayer.addMarker(createMarker(b.getPositionLng(), b.getPositionLat()));
        map.addClusterLayer(clusterLayer);
    }

    /**
     * creates and return a marker from the incoming lat and lng
     *
     * @param lat
     * @param lng
     * @return
     */
    private MapMarker createMarker(float lat, float lng) {
        Image markerIcon = new Image();
        try {
            markerIcon.setImageResource(R.drawable.bicycle_marker_72dp);
        } catch (IOException ex) {
            Toast.makeText(this, "Error: cannot get marker icon", Toast.LENGTH_SHORT).show();
        }
        MapMarker marker = new MapMarker();
        marker.setIcon(markerIcon);
        marker.setCoordinate(
                new GeoCoordinate(lng, lat)
        );
        return marker;
    }

    @Override
    public void onBicycleClick(Bicycle bicycle) {
        Intent intent = new Intent(this, ScanQR.class);
        intent.putExtra("stationId", bicycle.getStationId());
        intent.putExtra("bicycleId", bicycle.getId());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
