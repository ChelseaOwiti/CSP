package com.example.csp;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class DriverActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.app_bar_main);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }


//        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//        @Override
//        public void onMapReady(@NonNull @NotNull GoogleMap mMap) {
//            //when map is loaded
//            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                @Override
//                public void onMapClick(@NonNull @NotNull LatLng latLng) {
//                    //when clicked on map, initialize marker
//
//                    MarkerOptions markerOptions = new MarkerOptions();
//                    // set position
//                    markerOptions.position(latLng);
//                    //set title of marker
//                    markerOptions.title(latLng.latitude + ":" + latLng.latitude);
//                    //remove marker
//                    mMap.clear();
//
//                    // zoom marker
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                            latLng, 10
//                    ));
//                    //marker on map
//                    mMap.addMarker(markerOptions);
//
//
//
//
//                }
//            });
//
//        }
//    });


    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull @NotNull LatLng latLng) {
                //when clicked on map, initialize marker

                MarkerOptions markerOptions = new MarkerOptions();
                // set position
                markerOptions.position(latLng);
                //set title of marker
                markerOptions.title(latLng.latitude + ":" + latLng.latitude);
                //remove marker
                mMap.clear();

                // zoom marker
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng, 10
                ));
                //marker on map
                mMap.addMarker(markerOptions);
            }
        });

        LatLng nairobi = new LatLng(1, 36);
        mMap.addMarker(new MarkerOptions().position(nairobi).title("Nairobi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nairobi));

    }
}
