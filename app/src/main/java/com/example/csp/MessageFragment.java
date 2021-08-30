package com.example.csp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;


//actually for maps not messaging
public class MessageFragment extends Fragment{
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_message, container, false);
        //initialize view
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        //initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.maps);

        //async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
                //when map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull @NotNull LatLng latLng) {
                        //when clicked on map, initialize marker

                        MarkerOptions markerOptions = new MarkerOptions();
                        // set position
                        markerOptions.position(latLng);
                        //set title of marker
                        markerOptions.title(latLng.latitude + ":" + latLng.latitude);
                        //remove marker
                        googleMap.clear();

                        // zoom marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        //marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });

            }
        });

        //return view
        return view;



    }
}
