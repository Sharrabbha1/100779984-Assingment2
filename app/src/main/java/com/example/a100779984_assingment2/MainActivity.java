package com.example.a100779984_assingment2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    private EditText etQueryAddress, etAddress, etLatitude, etLongitude;
    private TextView tvQueryResult;
    private Database databaseHelper;

    private static final String MESSAGE_FILL_FIELDS = "Please fill all fields";
    private static final String MESSAGE_INVALID_COORDINATES = "Invalid latitude or longitude";
    private static final String MESSAGE_LOCATION_ADDED = "Location added successfully";
    private static final String MESSAGE_LOCATION_NOT_FOUND = "Location not found";
    //added like shortcuts like when i enter the one of the message fills in the for loop it will allow to make sure the messagw associated will be printed asd form of toast

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Database Helper
        databaseHelper = new Database(this);

        etQueryAddress = findViewById(R.id.et_query_address);
        etAddress = findViewById(R.id.et_address);
        etLatitude = findViewById(R.id.et_latitude);
        etLongitude = findViewById(R.id.et_longitude);
        tvQueryResult = findViewById(R.id.tv_query_result);

        //buttons for the xml page
        Button btnQueryLocation = findViewById(R.id.btn_query_location);
        Button btnAddLocation = findViewById(R.id.btn_add_location);
        Button btnUpdateLocation = findViewById(R.id.btn_update_location);
        Button btnDeleteLocation = findViewById(R.id.btn_delete_location);

        //on click allowing for that button to work
        btnQueryLocation.setOnClickListener(view -> queryLocationByAddress());
        btnAddLocation.setOnClickListener(view -> addLocation());
        btnUpdateLocation.setOnClickListener(view -> updateLocation());
        btnDeleteLocation.setOnClickListener(view -> deleteLocation());
    }

    private void queryLocationByAddress()
    {
        String address = etQueryAddress.getText().toString().trim();
        if (address.isEmpty()) {
            showToast(MESSAGE_FILL_FIELDS);
            return;
        }

        Cursor cursor = databaseHelper.getLocationByAddress(address);
        if (cursor != null && cursor.moveToFirst())
        {
            @SuppressLint("Range") String latitude = cursor.getString(cursor.getColumnIndex("latitude"));
            @SuppressLint("Range") String longitude = cursor.getString(cursor.getColumnIndex("longitude"));
            tvQueryResult.setText("Latitude: " + latitude + ", Longitude: " + longitude);
            cursor.close();
        } else {
            tvQueryResult.setText(MESSAGE_LOCATION_NOT_FOUND);
        }
    }
    private void addLocation()
    { // adding the location
        String address = etAddress.getText().toString().trim();
        String latitude = etLatitude.getText().toString().trim();
        String longitude = etLongitude.getText().toString().trim();

        if (address.isEmpty() || latitude.isEmpty() || longitude.isEmpty())
        {
            showToast(MESSAGE_FILL_FIELDS);
            return;
        }

        if (!validateCoordinates(latitude, longitude))
        {
            showToast(MESSAGE_INVALID_COORDINATES);
            return;
        }

        boolean isAdded = databaseHelper.addLocation(address, Double.parseDouble(latitude), Double.parseDouble(longitude));
        if (isAdded)
        {
            showToast(MESSAGE_LOCATION_ADDED);
            clearInputFields();
            clearQueryResult();
        } else
        {
            showToast("Failed to add location");
        }
    }

    private void updateLocation()
    { // updating the lcoations there are added or were addedd
        String address = etAddress.getText().toString().trim();
        String latitude = etLatitude.getText().toString().trim();
        String longitude = etLongitude.getText().toString().trim();

        if (address.isEmpty() || latitude.isEmpty() || longitude.isEmpty()) {
            showToast(MESSAGE_FILL_FIELDS);
            return;
        }

        if (!validateCoordinates(latitude, longitude))
        {
            showToast(MESSAGE_INVALID_COORDINATES);
            return;
        }

        boolean isUpdated = databaseHelper.updateLocation(address, Double.parseDouble(latitude), Double.parseDouble(longitude));
        if (isUpdated)
        {
            showToast("Location updated successfully");
            clearInputFields();
            clearQueryResult();
        } else {
            showToast(MESSAGE_LOCATION_NOT_FOUND);
        }
    }

    private void deleteLocation()
    { // deleting the location or the changes
        String address = etQueryAddress.getText().toString().trim();

        if (address.isEmpty())
        {
            showToast("Please enter an address to delete");
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Delete Confirmation")
                .setMessage("do you wanna delete this location?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    boolean isDeleted = databaseHelper.deleteLocation(address);
                    if (isDeleted) {
                        showToast("Location deleted successfully");
                        etQueryAddress.setText("");
                        clearQueryResult();
                    } else {
                        showToast(MESSAGE_LOCATION_NOT_FOUND);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private boolean validateCoordinates(String latitudeStr, String longitudeStr)
    {
        try {
            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);
            return latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    private void clearInputFields()
    {
        etAddress.setText("");
        etLatitude.setText("");
        etLongitude.setText("");
    }

    private void clearQueryResult()
    {
        tvQueryResult.setText("");
    }

    private void showToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
