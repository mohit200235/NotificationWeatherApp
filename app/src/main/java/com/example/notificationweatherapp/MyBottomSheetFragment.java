package com.example.notificationweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.notificationweatherapp.ViewModel.LocationViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {

    private LocationViewModel locationViewModel;
    private final static int REQUEST_CODE = 100;
    private EditText editText;
    private Button button;
    private TextView find_location;

    private String pin;
    private LocationListener locationListener;

    private ProgressBar progressBar;

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_location_bottom_sheet, container, false);

        editText = rootView.findViewById(R.id.get_location);
        button = rootView.findViewById(R.id.add_location);
        find_location = rootView.findViewById(R.id.find_location);
        progressBar = rootView.findViewById(R.id.progress);

        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        find_location.setOnClickListener(view -> {

            locationViewModel.getMyAddress().observe(this, s -> {
                Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
                pin = s;
            });
            if (locationListener != null) {
                Handler handler = new Handler();
                progressBar.setVisibility(View.VISIBLE);

                //run my code for location here
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                    LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        // Location services are disabled, show a dialog or prompt the user to enable them.
                        Alert();
                    } else {
                        locationViewModel.toggleLocationUpdates();
                        Runnable runnable = () -> {
                            if (!TextUtils.isEmpty(pin)) {
                                locationListener.onLocationReceived(pin);
                            } else {
                                Toast.makeText(requireContext(), "Can't find the location ", Toast.LENGTH_SHORT).show();
                            }
                            dismiss();
                            progressBar.setVisibility(View.GONE);
                        };

                        handler.postDelayed(runnable, 10000);
                        Toast.makeText(requireContext(), "Getting your location,just wait...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        // Location services are disabled, show a dialog or prompt the user to enable them.
                        Alert();
                    } else {

                        locationViewModel.toggleLocationUpdates();
                        Runnable runnable = () -> {
                            if (!TextUtils.isEmpty(pin)) {
                                locationListener.onLocationReceived(pin);
                            } else {
                                Toast.makeText(requireContext(), "Can't find the location ", Toast.LENGTH_SHORT).show();
                            }

                            dismiss();
                            progressBar.setVisibility(View.GONE);
                        };
                        handler.postDelayed(runnable, 10000);
                        Toast.makeText(requireContext(), "Getting your location,just wait... ", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                Toast.makeText(requireContext(), "error while finding the location\n make sure your location is turned on", Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(view -> {
            String input_location = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(input_location)) {
                if (locationListener != null) {
                    locationListener.onLocationReceived(input_location);
                }
                dismiss();
            } else {
                Log.d("TAG", "error while saving the location ");
            }
        });
        return rootView;
    }

    private void Alert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setMessage("Location services are disabled. Do you want to enable them?");
        dialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
            Intent enableLocationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(enableLocationIntent);
        });
        dialogBuilder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(requireContext(), "Turn on your device location :", Toast.LENGTH_SHORT).show();
            dismiss();
            // Handle if the user chooses not to enable location services.
        });
        dialogBuilder.setCancelable(false);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationViewModel.toggleLocationUpdates();
            } else {
                Toast.makeText(requireContext(), "No address : ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.INVISIBLE);
    }

    public interface LocationListener {
        void onLocationReceived(String location);
    }
}