package com.example.a100779984_assingment2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayLocations extends AppCompatActivity
{

    private Database databaseHelper;
    private TextView tvLocationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_details);

        tvLocationsList = findViewById(R.id.tv_locations_list);  //list of database
        databaseHelper = new Database(this);

        displayAllLocations();
    }

    private void displayAllLocations() {
        Cursor cursor = databaseHelper.getAllLocations();
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder locations = new StringBuilder();
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
                @SuppressLint("Range") double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
                locations.append("Address: ").append(address)
                        .append("\nLatitude: ").append(latitude)
                        .append(", Longitude: ").append(longitude)
                        .append("\n\n");
            }
            tvLocationsList.setText(locations.toString());
            cursor.close();
        } else {
            tvLocationsList.setText("No locations found."); // will give an optin that no locatuon found
        }
    }
}
