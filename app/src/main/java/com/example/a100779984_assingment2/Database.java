package com.example.a100779984_assingment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LocationFinder.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_LOCATIONS = "locations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_LOCATIONS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL)";
        db.execSQL(createTable);

        populateSampleLocations(db);
    }

    private void populateSampleLocations(SQLiteDatabase db)   //added locaitons like 100
    {
        String[][] gtaLocations = {
            {"Downtown Toronto 1", "43.651070", "-79.347015"},
            {"Downtown Toronto 2", "43.652070", "-79.348015"},
            {"Scarborough 1", "43.773077", "-79.257774"},
            {"Scarborough 2", "43.774077", "-79.258774"},
            {"Mississauga 1", "43.589045", "-79.644120"},
            {"Mississauga 2", "43.590045", "-79.645120"},
            {"Brampton 1", "43.731548", "-79.762418"},
            {"Brampton 2", "43.732548", "-79.763418"},
            {"Markham 1", "43.856100", "-79.337019"},
            {"Markham 2", "43.857100", "-79.338019"},
            {"Vaughan 1", "43.837208", "-79.508278"},
            {"Vaughan 2", "43.838208", "-79.509278"},
            {"Richmond Hill 1", "43.882840", "-79.440280"},
            {"Richmond Hill 2", "43.883840", "-79.441280"},
            {"Oakville 1", "43.467517", "-79.687666"},
            {"Oakville 2", "43.468517", "-79.688666"},
            {"Pickering 1", "43.838412", "-79.086757"},
            {"Pickering 2", "43.839412", "-79.087757"},
            {"Ajax 1", "43.850857", "-79.020374"},
            {"Ajax 2", "43.851857", "-79.021374"},
            {"Whitby 1", "43.897093", "-78.942039"},
            {"Whitby 2", "43.898093", "-78.943039"},
            {"Oshawa 1", "43.897545", "-78.865791"},
            {"Oshawa 2", "43.898545", "-78.866791"},
            {"Downtown Toronto 3", "43.653070", "-79.349015"},
            {"Scarborough 3", "43.775077", "-79.259774"},
            {"Mississauga 3", "43.591045", "-79.646120"},
            {"Brampton 3", "43.733548", "-79.764418"},
            {"Markham 3", "43.858100", "-79.339019"},
            {"Vaughan 3", "43.839208", "-79.510278"},
            {"Richmond Hill 3", "43.884840", "-79.442280"},
            {"Oakville 3", "43.469517", "-79.689666"},
            {"Pickering 3", "43.840412", "-79.088757"},
            {"Ajax 3", "43.852857", "-79.022374"},
            {"Whitby 3", "43.899093", "-78.944039"},
            {"Oshawa 3", "43.899545", "-78.867791"},
            {"Downtown Toronto 4", "43.654070", "-79.350015"},
            {"Scarborough 4", "43.776077", "-79.260774"},
            {"Mississauga 4", "43.592045", "-79.647120"},
            {"Brampton 4", "43.734548", "-79.765418"},
            {"Markham 4", "43.859100", "-79.340019"},
            {"Vaughan 4", "43.840208", "-79.511278"},
            {"Richmond Hill 4", "43.885840", "-79.443280"},
            {"Oakville 4", "43.470517", "-79.690666"},
            {"Pickering 4", "43.841412", "-79.089757"},
            {"Ajax 4", "43.853857", "-79.023374"},
            {"Whitby 4", "43.900093", "-78.945039"},
            {"Oshawa 4", "43.900545", "-78.868791"},
            {"Downtown Toronto 5", "43.655070", "-79.351015"},
            {"Scarborough 5", "43.777077", "-79.261774"},
            {"Mississauga 5", "43.593045", "-79.648120"},
            {"Brampton 5", "43.735548", "-79.766418"},
            {"Markham 5", "43.860100", "-79.341019"},
            {"Vaughan 5", "43.841208", "-79.512278"},
            {"Richmond Hill 5", "43.886840", "-79.444280"},
            {"Oakville 5", "43.471517", "-79.691666"},
            {"Pickering 5", "43.842412", "-79.090757"},
            {"Ajax 5", "43.854857", "-79.024374"},
            {"Whitby 5", "43.901093", "-78.946039"},
            {"Oshawa 5", "43.901545", "-78.869791"},
            {"Downtown Toronto 6", "43.656070", "-79.352015"},
            {"Scarborough 6", "43.778077", "-79.262774"},
            {"Mississauga 6", "43.594045", "-79.649120"},
            {"Brampton 6", "43.736548", "-79.767418"},
            {"Markham 6", "43.861100", "-79.342019"},
            {"Vaughan 6", "43.842208", "-79.513278"},
            {"Richmond Hill 6", "43.887840", "-79.445280"},
            {"Oakville 6", "43.472517", "-79.692666"},
            {"Pickering 6", "43.843412", "-79.091757"},
            {"Ajax 6", "43.855857", "-79.025374"},
            {"Whitby 6", "43.902093", "-78.947039"},
            {"Oshawa 6", "43.902545", "-78.870791"},
            {"Downtown Toronto 7", "43.657070", "-79.353015"},
            {"Scarborough 7", "43.779077", "-79.263774"},
            {"Mississauga 7", "43.595045", "-79.650120"},
            {"Brampton 7", "43.737548", "-79.768418"},
            {"Markham 7", "43.862100", "-79.343019"},
            {"Vaughan 7", "43.843208", "-79.514278"},
            {"Richmond Hill 7", "43.888840", "-79.446280"},
            {"Oakville 7", "43.473517", "-79.693666"},
            {"Pickering 7", "43.844412", "-79.092757"},
            {"Ajax 7", "43.856857", "-79.026374"},
            {"Whitby 7", "43.903093", "-78.948039"},
            {"Oshawa 7", "43.903545", "-78.871791"},
            {"Downtown Toronto 8", "43.658070", "-79.354015"},
            {"Scarborough 8", "43.780077", "-79.264774"},
            {"Mississauga 8", "43.596045", "-79.651120"},
            {"Brampton 8", "43.738548", "-79.769418"},
            {"Markham 8", "43.863100", "-79.344019"},
            {"Vaughan 8", "43.844208", "-79.515278"},
            {"Richmond Hill 8", "43.889840", "-79.447280"},
            {"Oakville 8", "43.474517", "-79.694666"},
            {"Pickering 8", "43.845412", "-79.093757"},
            {"Ajax 8", "43.857857", "-79.027374"},
            {"Whitby 8", "43.904093", "-78.949039"},
            {"Oshawa 8", "43.904545", "-78.872791"}
        };

        for (int i = 0; i < gtaLocations.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ADDRESS, gtaLocations[i][0] + " " + (i + 1)); // Unique address for each entry
            values.put(COLUMN_LATITUDE, Double.parseDouble(gtaLocations[i][1]) + (i * 0.001));
            values.put(COLUMN_LONGITUDE, Double.parseDouble(gtaLocations[i][2]) + (i * 0.001));
            db.insert(TABLE_LOCATIONS, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    public boolean addLocation(String address, double latitude, double longitude) {
        // alows to add locaiton
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);

        long result = db.insert(TABLE_LOCATIONS, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getLocationByAddress(String address) {
        // get lcoaiuton by address
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOCATIONS, new String[]{COLUMN_LATITUDE, COLUMN_LONGITUDE},
                COLUMN_ADDRESS + "=?", new String[]{address}, null, null, null);
    }

    public boolean updateLocation(String address, double latitude, double longitude) {
        // allows to ypdate hte lcoautons
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);

        int rowsAffected = db.update(TABLE_LOCATIONS, values, COLUMN_ADDRESS + "=?", new String[]{address});
        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteLocation(String address) {
        // delete thge locastion
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_LOCATIONS, COLUMN_ADDRESS + "=?", new String[]{address});
        db.close();
        return rowsDeleted > 0;
    }

    public Cursor getAllLocations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOCATIONS, null, null, null, null, null, COLUMN_ADDRESS + " ASC");
    }
}
