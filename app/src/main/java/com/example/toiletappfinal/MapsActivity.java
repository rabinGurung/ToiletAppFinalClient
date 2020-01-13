package com.example.toiletappfinal;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnClickListener {

    private GoogleMap mMap;
    LatLng ktm = new LatLng(27.70, 85.30);
    public static Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    public static HashMap<String, Double[]> TOILETS = new HashMap<>();
    public static HashMap<String,Integer> TOILETS_IMAGE = new HashMap<>();
    private Button btnear;
    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    private static String[] listNames= {"Bus Park Public Toilet","Pashupati Public Toilet","Kantipath Public Toilet","Public Toilet Tapinta","Tourist Rest Room","Public Toilet सार्वजनिक शौचालय","Public Toilet at Purano Buspark","Sauchalaya (Toilet)","Public Paid Toilet","Mangal Bazar Tourist Toilet",
            "Public Toilet"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnear = findViewById(R.id.btnnear);
        btnear.setOnClickListener(this);
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    private void getToiletData() {
        TOILETS.put("Bus Park Public Toilet",new Double[]{27.7327326,85.308393});
        TOILETS.put("Pashupati Public Toilet",new Double[]{27.7089851,85.3477752});
        TOILETS.put("Kantipath Public Toilet",new Double[]{27.7143059,85.3172708});
        TOILETS.put("Public Toilet Tapinta",new Double[]{27.7095046,85.3029746});
        TOILETS.put("Tourist Rest Room",new Double[]{27.7327326,85.308393});
        TOILETS.put("Public Toilet सार्वजनिक शौचालय",new Double[]{27.7067494,85.3145114});
        TOILETS.put("Public Toilet at Purano Buspark",new Double[]{27.7055579,85.3149071});
        TOILETS.put("Sauchalaya (Toilet)",new Double[]{27.6987548,85.3131013});
        TOILETS.put("Public Paid Toilet",new Double[]{27.7020968,85.3179574});
        TOILETS.put("Mangal Bazar Tourist Toilet",new Double[]{27.673065,85.3251269});
        TOILETS.put("Public Toilet",new Double[]{27.67559,85.3451657});

        TOILETS_IMAGE.put("Bus Park Public Toilet",R.drawable.busparktoilet);
        TOILETS_IMAGE.put("Pashupati Public Toilet",R.drawable.toilet);
        TOILETS_IMAGE.put("Kantipath Public Toilet",R.drawable.toilet1);
        TOILETS_IMAGE.put("Public Toilet Tapinta",R.drawable.toilet2);
        TOILETS_IMAGE.put("Tourist Rest Room",R.drawable.touristrestroom);
        TOILETS_IMAGE.put("Public Toilet सार्वजनिक शौचालय",R.drawable.toilet3);
        TOILETS_IMAGE.put("Public Toilet at Purano Buspark",R.drawable.toilet4);
        TOILETS_IMAGE.put("Sauchalaya (Toilet)",R.drawable.toilet5);
        TOILETS_IMAGE.put("Public Paid Toilet",R.drawable.toilet);
        TOILETS_IMAGE.put("Mangal Bazar Tourist Toilet",R.drawable.toilet3);
        TOILETS_IMAGE.put("Public Toilet",R.drawable.toilet);



        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Bus Park Public Toilet")[0], TOILETS.get("Bus Park Public Toilet")[1]))
                .title("Bus Park Public Toilet")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))))
                ;

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Pashupati Public Toilet")[0], TOILETS.get("Pashupati Public Toilet")[1]))
                .title("Pashupati Public Toilet")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Kantipath Public Toilet")[0], TOILETS.get("Kantipath Public Toilet")[1]))
                .title("Kantipath Public Toilet")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Public Toilet Tapinta")[0], TOILETS.get("Public Toilet Tapinta")[1]))
                .title("Public Toilet Tapinta")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Tourist Rest Room")[0],TOILETS.get("Tourist Rest Room")[1]))
                .title("Tourist Rest Room")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Public Toilet सार्वजनिक शौचालय")[0],TOILETS.get("Public Toilet सार्वजनिक शौचालय")[1]))
                .title("Public Toilet सार्वजनिक शौचालय")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Public Toilet at Purano Buspark")[0],TOILETS.get("Public Toilet at Purano Buspark")[1]))
                .title("Public Toilet at Purano Buspark")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Sauchalaya (Toilet)")[0],TOILETS.get("Sauchalaya (Toilet)")[1]))
                .title("Sauchalaya (Toilet)")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Public Paid Toilet")[0],TOILETS.get("Public Paid Toilet")[1]))
                .title("Public Paid Toilet")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Mangal Bazar Tourist Toilet")[0],TOILETS.get("Mangal Bazar Tourist Toilet")[1]))
                .title("Sauchalaya (Toilet)")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(TOILETS.get("Public Toilet")[0],TOILETS.get("Public Toilet")[1]))
                .title("Public Toilet")
                .icon(BitmapDescriptorFactory.fromBitmap(getResizedBitmap(getBitmapFromVectorDrawable(MapsActivity.this,R.drawable.toileticon),40,40))));

    }
    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    private void fetchCurrentLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), "Your current Location : "+currentLocation.getLatitude()+", "+currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    ktm = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                    mMap.addCircle(new CircleOptions().center(ktm).radius(20).strokeColor(Color.BLACK).fillColor(Color.WHITE));
                }
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getToiletData();
        fetchCurrentLocation();
        CustomInfoWindow customInfoWindow = new CustomInfoWindow(this);
        mMap.setInfoWindowAdapter(customInfoWindow);
        mMap.setOnInfoWindowClickListener(customInfoWindow);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnnear){
            if(currentLocation != null){
                Double Latitude = 0.0,Longitude = 0.0;
                Double shortDistance = 0.0;
                for(int i=0; i < listNames.length;i++){
                    if(i == 0){
                        shortDistance = meters(currentLocation.getLatitude(),currentLocation.getLongitude(), TOILETS.get(listNames[i])[0],TOILETS.get(listNames[i])[1]);
                        Latitude = TOILETS.get(listNames[i])[0];
                        Longitude = TOILETS.get(listNames[i])[1];
                    }
                    Double distance = meters(currentLocation.getLatitude(),currentLocation.getLongitude(), TOILETS.get(listNames[i])[0],TOILETS.get(listNames[i])[1]);
                    if(distance < shortDistance){
                        shortDistance = shortDistance;
                        Latitude = TOILETS.get(listNames[i])[0];
                        Longitude = TOILETS.get(listNames[i])[1];
                    }
                }
                if(Latitude!= 0.0 && Longitude != 0.0){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Latitude,Longitude)));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                }
            }else {
                Toast.makeText(this, "Please activate location feature and restart the application", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static double meters(double lt1, double ln1, double lt2, double ln2) {
        final double x = lt1 * d2r;
        final double y = lt2 * d2r;
        return Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (ln1 - ln2))) * d2km;
    }
}
