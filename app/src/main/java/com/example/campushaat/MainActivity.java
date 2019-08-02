package com.example.campushaat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Places> placeList;
    private String apartment="apartment";
    private String pg="pg";
    private String hostel="hostel";
    private ArrayList<Places> backupPlace;
    private RecyclerAdapter mPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializePlaces();
        backupPlaces();
        displayPlaces();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        if(i!=null) {
            Bundle extra = i.getExtras();
            if(extra!=null) {
                getValuesFromIntent(extra);
                filterDataSet();
            }
            }
        }

    private void filterDataSet() {
        placeList.clear();
        for(Places place : backupPlace){
            String type = place.type.toLowerCase();
            if(     type.equals(apartment.toLowerCase())||
                    type.equals(pg.toLowerCase())||
                    type.equals(hostel.toLowerCase())){
                        placeList.add(place);
            }
        }
        mPlaceAdapter.notifyDataSetChanged();
    }

    private void getValuesFromIntent(Bundle extra) {
        apartment = extra.getString("Main_apartment", "not found");
        pg = extra.getString("Main_pg", "not found");
        hostel = extra.getString("Main_hostel", "not found");
    }

    private void backupPlaces() {
        backupPlace = new ArrayList<>();
        for(Places places : placeList){
            backupPlace.add(places);
        }
    }

    private void displayPlaces() {
        RecyclerView mrecyclerPlace = (RecyclerView) findViewById(R.id.place_list_recycler);
        LinearLayoutManager mlinearPlaceManager = new LinearLayoutManager(this);

        mPlaceAdapter = new RecyclerAdapter(this,placeList);

        mrecyclerPlace.setAdapter(mPlaceAdapter);
        mrecyclerPlace.setLayoutManager(mlinearPlaceManager);
    }

    private void initializePlaces() {
        ArrayList<String> nameList = new ArrayList<>();
        placeList = new ArrayList<>();
        Places place1 = new Places("15 Block", "hostel","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.798070");
        place1.hostels = place1.new Hostels("double","attached");

        Places place2 = new Places("ramanHouse", "apartment","True Value Falnir, Bendoor, Mangaluru, Karnataka 575001","13.3499858","74.7980704");
        place2.apartments = place2.new Apartments("Sharing", "2 BHK");

        Places place3 = new Places("16 Block", "PG","Hotel Laxmi Mahal, Hampankatta, Mangaluru, Karnataka","12.3499858","74.798170");
        place3.pg = place3.new PG("20000");

        Places place4 = new Places("Mukesh apartments", "apartment","J & J De Chane Agency & Medicals, Opp. Milagres Church 2, Milagres Mansion, Hampankatta, Mangaluru, Karnataka 575001","13.3499858","74.098070");
        place4.apartments = place4.new Apartments("Single","1 BHK");

        Places place5 = new Places("aaj bhi wahi", "PG", "Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3495358","74.798070");
        place5.pg = place5.new PG("10000");

        Places place6 = new Places("mvs site", "apartment","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.798079");
        place6.apartments = place6.new Apartments("Sharing","5 BHK");

        Places place7 = new Places("onstars hostel", "hostel","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.498070");
        place7.hostels = place7.new Hostels("Triple" ,"common");

        Places place8 = new Places("Sharing is Caring", "PG","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.798070");
        place8.pg = place8.new PG("30000");

        Places place9 = new Places("Topless", "apartment","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.798070");
        place9.apartments = place9.new Apartments("Single" , "1 BHK");

        Places place10 = new Places("19 Block", "hostel","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.798070");
        place10.hostels = place10.new Hostels("single","attached");

        Places place11 = new Places("Standing forever", "pg","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3099858","71.798070");
        place11.pg = place11.new PG("4000");

        Places place12 = new Places("Whitehouse", "apartment","Brick House, New Balmatta Road, Mangaluru, Karnataka","13.3499858","74.798070");
        place12.apartments = place12.new Apartments("Sharing", "10 BHK");

        placeList.add(place1);
        placeList.add(place2);
        placeList.add(place3);
        placeList.add(place4);
        placeList.add(place5);
        placeList.add(place6);
        placeList.add(place7);
        placeList.add(place8);
        placeList.add(place9);
        placeList.add(place10);
        placeList.add(place11);
        placeList.add(place12);

        for(int i=0 ; i<placeList.size(); i++){
            placeList.get(i).initializeImage(getResources().getIdentifier("mipmap/img" + i ,"drawable", getPackageName()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_filter) {
            Intent i = new Intent(this,Filter.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
