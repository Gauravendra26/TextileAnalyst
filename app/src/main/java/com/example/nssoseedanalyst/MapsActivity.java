package com.example.nssoseedanalyst;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


import com.example.nssoseedanalyst.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context context;
     ActivityMapsBinding binding;
    private final int FINE_PERMISSION_CODE = 1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    String country,city,address,longitude1,latitude1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         Utils.blackIconStatusBar(MapsActivity.this, R.color.white);

        Intent intent = getIntent();
        latitude1 = intent.getStringExtra("latitude");
        longitude1 = intent.getStringExtra("longitude");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        getLastLocation();

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);



    }

//    private void getLastLocation() {
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.
//                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
//                checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED) {
//
////        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE_PERMISSION_CODE);
//            return;
//        }
//        Task<Location> task = fusedLocationProviderClient.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//
//                if (location!=null){
//                  currentLocation = location;
//
//                }
//            }
//        });
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng agra = new LatLng(Double.valueOf(latitude1), Double.valueOf(longitude1));
        mMap.addMarker(new MarkerOptions().position(agra).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(agra));
    }

    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode==FINE_PERMISSION_CODE){
//            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                getLastLocation();
//            }else {
//                Toast.makeText(this, "Location permission is denied,please allow the permission to access the permission", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }

//    @Override

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mapsmenu, menu);
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

// Change the map type based on the user's selection.
                 if(R.id.normal_map==item.getItemId()){
                     mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                     return true;
                 }
                 else if(R.id.hybrid_map==item.getItemId()){
                     mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                     return true;
                 }
                 else if(R.id.satellite_map==item.getItemId()){
                     mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                     return true;
                 }

                 else if(R.id.terrain_map==item.getItemId()){
                     mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                     return true;
                 }
                 return super.onOptionsItemSelected(item);

        }


}
