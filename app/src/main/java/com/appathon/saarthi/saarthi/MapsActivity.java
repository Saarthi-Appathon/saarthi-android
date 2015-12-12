package com.appathon.saarthi.saarthi;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends Activity {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    ArrayList<GooglePlace> busStopsList;

    // the google key

    // ============== YOU SHOULD MAKE NEW KEYS ====================//
    final String GOOGLE_KEY = "AIzaSyBDZHhd5xd84kNMiQPuJpAOOOebviAp5OI";
    //final String GOOGLE_KEY = "AIzaSyDL4sIVQOjM1r2qXDEVPhmTTqMzXhOjbqk";

    // we will need to take the latitude and the longitude from a certain point
    // this is the center of New York
    private double latitude;
    private double longitude;
    private String place;
//    private String radius;

    ArrayAdapter<String> myAdapter;

    String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra(LoginActivity.CURRENT_LATITUDE, 0.0);
        longitude = intent.getDoubleExtra(LoginActivity.CURRENT_LONGITUDE, 0.0);
        place = intent.getStringExtra(LoginActivity.SELECTED_PLACE);

        //setUpMapIfNeeded();
        // start the AsyncTask that makes the call for the venus search.
        //getPlaces();
        FragmentManager myFragmentManager = getFragmentManager();
        MapFragment myMapFragment
                = (MapFragment)myFragmentManager.findFragmentById(R.id.googleMap);

        mMap = myMapFragment.getMap();
        mMap.setMyLocationEnabled(true);
        setUpMap();
        new GooglePlaces().execute();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded()
    {
        if (mMap == null)
        {
            /*mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap))
                    .getMap();*/

            if (mMap != null)
            {
                setUpMap();
            }
        }
    }

    private void setUpMap()
    {
        /*mMap.addMarker(new MarkerOptions().position(new LatLng(18.591096, 73.733018)).title("Office"));
        LatLng latLng = new LatLng(18.591096, 73.733018);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);*/
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18);
        mMap.animateCamera(cameraUpdate);
    }
    public void onLocationChanged(Location location)
    {
        //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng latLng = new LatLng(18.591096, 73.733018);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        //locationManager.removeUpdates(this);
    }

    private class GooglePlaces extends AsyncTask<View, Void, String>
    {
        String temp;

        @Override
        protected String doInBackground(View... urls)
        {
            // make Call to the url
            temp = BackendConnect.makeCall("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longitude + "&radius=500&sensor=true&type=" + place + "&key=" + GOOGLE_KEY);

            System.out.println(temp);

            //print the call in the console
            System.out.println("Console call :" + "https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longitude + "&radius=500&sensor=true&type=" + place +"&key=" + GOOGLE_KEY);
            return "";
        }

        @Override
        protected void onPreExecute()
        {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result)
        {
            if (temp == null)
            {
                // we have an error to the call
                // we can also stop the progress bar
            }
            else
            {
                // all things went right

                FragmentManager myFragmentManager = getFragmentManager();
                MapFragment myMapFragment
                        = (MapFragment)myFragmentManager.findFragmentById(R.id.googleMap);

                GoogleMap map = myMapFragment.getMap();
                map.setMyLocationEnabled(true);

                // parse Google places search result
                busStopsList = (ArrayList<GooglePlace>) parseGoogleParse(temp);

                for (int i = 0; i < busStopsList.size(); i++) {
                    LatLng latLng = new LatLng(Double.parseDouble(busStopsList.get(i).getLatitude()), Double.parseDouble(busStopsList.get(i).getLongitude()));
                    map.addMarker(new MarkerOptions().position(latLng)
                            .title(busStopsList.get(i).getName())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));;

                    //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 19);
                    //mMap.animateCamera(cameraUpdate);
                }
            }
        }
    }

    private static ArrayList<GooglePlace> parseGoogleParse(final String response)
    {
        ArrayList<GooglePlace> temp = new ArrayList<GooglePlace>();
        try
        {
            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results"))
            {
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    GooglePlace poi = new GooglePlace();

                    if (jsonArray.getJSONObject(i).has("geometry"))
                    {
                        if (jsonArray.getJSONObject(i).getJSONObject("geometry").has("location"))
                        {
                            if (jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").has("lat"))
                            {
                                poi.setLatitude(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optString("lat"));
                            }
                            if (jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").has("lng"))
                            {
                                poi.setLongitude(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optString("lng"));
                            }
                        }
                    }

                    if (jsonArray.getJSONObject(i).has("name"))
                    {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                    }

                    if (jsonArray.getJSONObject(i).has("id"))
                    {
                        poi.setUUID(jsonArray.getJSONObject(i).optString("id"));
                    }

                    if (jsonArray.getJSONObject(i).has("place_id"))
                    {
                        poi.setPlaceId(jsonArray.getJSONObject(i).optString("place_id"));
                    }

                    if (jsonArray.getJSONObject(i).has("icon"))
                    {
                        poi.setIcon(jsonArray.getJSONObject(i).optString("icon"));
                    }

                    temp.add(poi);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<GooglePlace>();
        }
        return temp;
    }
}