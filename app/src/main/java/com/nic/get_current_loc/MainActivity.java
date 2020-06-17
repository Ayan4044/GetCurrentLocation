package com.nic.get_current_loc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    StatusBarColor statusBarColor;
    Button buttonlocation;
    TextView settextviewloc;
    Geocoder geocoder;
    List<Address> addressList;
    Button buttonaddress;
    //List<Add>
    EditText editextloc;
    Double latitdue, longitude;
    private FusedLocationProviderClient fusedLocationClient;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    String lat;
    String provider;
    //protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    int PLACE_PICKER_REQUEST = 1;
    private FusedLocationProviderClient client;
    private int ACCESS_FINE_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusBarColor=new StatusBarColor(this);
        statusBarColor.Status_Color(this);
        buttonlocation = (Button) findViewById(R.id.buttonlocation);
        editextloc = (EditText) findViewById(R.id.editextloc);
        buttonaddress = (Button) findViewById(R.id.buttonaddress);
        //     MapUtility.apiKey = getResources().getString(R.string.api_key);
        geocoder = new Geocoder(this, Locale.getDefault());
        Check_Permmission();
        ///code to hide the title bar in the tabbed acitivity
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //getLastLocation();
       /* locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);*/
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        buttonlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    // settextviewloc.setText("Lat= "+location.getLatitude()+" Long="+location.getLongitude());
                                    System.out.println("Location= " + location.getLatitude());
                                    latitdue = location.getLatitude();
                                    longitude = location.getLongitude();

                                    try {
                                        addressList = geocoder.getFromLocation(latitdue, longitude, 1);
                                        String address = addressList.get(0).getAddressLine(0);
                                        String area = addressList.get(0).getLocality();
                                        String locality = addressList.get(0).getSubLocality();
                                        String countryname = addressList.get(0).getCountryName();
                                        String postalCode = addressList.get(0).getPostalCode();

                                        String address_details = address + " | " + area + " | " + locality + " | " + countryname + " | " + postalCode;
                                        //editextloc.setText(address_details);

                                        //String address_details = address + " | " + area + " | " + locality + " | " + countryname + " | " + postalCode;
                                        editextloc.setText(address_details);
                                    } catch (IOException e) {

                                    }

                                }
                            }
                        });
            }
        });

        buttonaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* try {
                    addressList = geocoder.getFromLocation(latitdue, longitude, 1);
                    String address = addressList.get(0).getAddressLine(0);
                    String area = addressList.get(0).getLocality();
                    String locality = addressList.get(0).getSubLocality();
                    String countryname = addressList.get(0).getCountryName();
                    String postalCode = addressList.get(0).getPostalCode();

                    String address_details = address + " | " + area + " | " + locality + " | " + countryname + " | " + postalCode;
                    editextloc.setText(address);
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Location", editextloc.getText().toString().trim());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Location Copied", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {

                }*/




                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Location", editextloc.getText().toString().trim());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), "Location Copied", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void Request_Permission() {
        //ActivityCompat.requestPermissions(this, new String[]{ACCESS_F});
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void getLastLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            // settextviewloc.setText("Lat= "+location.getLatitude()+" Long="+location.getLongitude());
                            System.out.println("Location= " + location.getLatitude());
                            latitdue = location.getLatitude();
                            longitude = location.getLongitude();

                            try {
                                addressList = geocoder.getFromLocation(latitdue, longitude, 1);
                                String address = addressList.get(0).getAddressLine(0);
                                String area = addressList.get(0).getLocality();
                                String locality = addressList.get(0).getSubLocality();
                                String countryname = addressList.get(0).getCountryName();
                                String postalCode = addressList.get(0).getPostalCode();

                                String address_details = address + " | " + area + " | " + locality + " | " + countryname + " | " + postalCode;
                                editextloc.setText(address_details);
                            } catch (IOException e) {

                            }

                        }
                    }
                });
    }

    /*
        @Override
        public void onLocationChanged(Location location) {
            //txtLat = (TextView) findViewById(R.id.textview1);
            settextviewloc=(TextView) findViewById(R.id.textViewSetloc);

            settextviewloc.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Latitude","disable");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Latitude","enable");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Latitude","status");
        }*/
    public void copy_address(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Location", editextloc.getText().toString().trim());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Location Copied", Toast.LENGTH_SHORT).show();
    }

    private void Check_Permmission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("Camera permission is needed")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_PERMISSION);
                            }
                        })

                        .create().show();


            } else {
                ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_PERMISSION);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_FINE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

            } else {
                this.finish();
                System.exit(0);
                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, ACCESS_FINE_LOCATION_PERMISSION);

            }
        }

    }
}

