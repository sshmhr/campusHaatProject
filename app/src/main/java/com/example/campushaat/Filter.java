package com.example.campushaat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Filter extends AppCompatActivity {
    private static String hostel = "hostel" ;
    private static String apartment = "apartment" ;
    private static String pg = "pg" ;
    private static String ohostel = "hostel" ;
    private static String oapartment = "apartment" ;
    private static String opg = "pg" ;
    private static Intent i;
    private CheckBox apartment_check;
    private CheckBox hostel_check;
    private CheckBox pg_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        backupofValues();
        initializations();
        populateCheckboxValue();
    }

    private void backupofValues() {
        oapartment = apartment;
        ohostel = hostel ;
        opg = pg ;
    }

    private void initializations() {
        i = new Intent(this, MainActivity.class);
        apartment_check = findViewById(R.id.filter_apartment);
        hostel_check = findViewById(R.id.filter_hostel);
        pg_check = findViewById(R.id.filter_pg);
    }

    private void populateCheckboxValue() {
        if(hostel.equals("hostel")){
            hostel_check.setChecked(true);
        }else{
            hostel_check.setChecked(false);
        }

        if(apartment.equals("apartment")){
            apartment_check.setChecked(true);
        }else{
            apartment_check.setChecked(false);
        }

        if(pg.equals("pg")){
            pg_check.setChecked(true);
        }else{
            pg_check.setChecked(false);
        }
    }

    public void cancel(View view){
        i.putExtra("Main_apartment",oapartment);
        i.putExtra("Main_hostel",ohostel);
        i.putExtra("Main_pg",opg);
        startActivity(i);
    }

    public void apply(View view){
        testIfChecked();
        i.putExtra("Main_apartment",apartment);
        i.putExtra("Main_hostel",hostel);
        i.putExtra("Main_pg",pg);
        startActivity(i);
    }

    private void testIfChecked() {
        if(apartment_check.isChecked()){
            apartment = "apartment";
        }else{
            apartment = "none";
        }

        if(hostel_check.isChecked()){
            hostel = "hostel";
        }else{
            hostel = "none";
        }

        if(pg_check.isChecked()){
            pg = "pg";
        }else{
            pg = "none";
        }
    }
}
