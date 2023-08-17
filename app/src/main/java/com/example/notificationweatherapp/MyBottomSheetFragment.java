package com.example.notificationweatherapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {

    private EditText editText;
    private Button button;
    private LocationListener locationListener;
    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_location_bottom_sheet, container, false);

        editText = rootView.findViewById(R.id.get_location);
        button = rootView.findViewById(R.id.add_location);


        button.setOnClickListener(view -> {
            String input_location = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(input_location)) {
                if (locationListener != null) {
                    locationListener.onLocationReceived(input_location);
                }
                dismiss();
            }else{
                Log.d("TAG","error while saving the location ");
            }
        });
        return rootView;
    }

    public interface LocationListener {
        void onLocationReceived(String location);
    }
}