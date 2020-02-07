package ecoride.fstt.ac.ma.UI.Activities.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ecoride.fstt.ac.ma.R;

public class ScanQR extends AppCompatActivity {

    private int stationId;
    private int bicycleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        if (getIntent().getExtras() != null) {
            stationId = getIntent().getIntExtra("stationId", 1);
            bicycleId = getIntent().getIntExtra("bicycleId", 1);
        }

    }
}
