package com.example.notificationweatherapp.ViewModel;

import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationViewModel  extends AndroidViewModel {
    FusedLocationProviderClient fusedLocationProviderClient;



    private MutableLiveData<String> liveAddress = new MutableLiveData<>();
    private boolean locationUpdatesEnabled = false;

    public LiveData<String> getMyAddress() {
        return liveAddress;
    }

    public LocationViewModel(@NonNull Application application) {
        super(application);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application);
    }

    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            assert location != null;
            processLocation(location);
        }
    };

    public void toggleLocationUpdates() {
        if (!locationUpdatesEnabled) {
            startLocationUpdates();
        } else {
            stopLocationUpdates();
        }
        locationUpdatesEnabled = !locationUpdatesEnabled;
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }


    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2000); // Update interval in milliseconds

        if (ContextCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult != null && locationResult.getLastLocation() != null) {
                        Location location = locationResult.getLastLocation();
                        processLocation(location);
                    }
                }
            }, null);
        }
    }

    private void processLocation(Location location) {
        Geocoder geocoder = new Geocoder(getApplication(), Locale.getDefault());

        try {
            List<Address> addresses;
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            liveAddress.setValue(addresses.get(0).getLocality());
        } catch (IOException e) {
            e.printStackTrace();
            liveAddress.setValue("Error getting Address:");
        }
    }

}
