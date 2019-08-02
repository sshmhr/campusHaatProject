package com.example.campushaat;

import java.security.PublicKey;

public class Places {
    public String name;
    public String type;
    public int imageId;
    public String location;
    public String latitude;
    public String longitude;
    public Apartments apartments ;
    public PG pg ;
    public Hostels hostels ;

    public Places(String name, String type,String location,String latitude,String longitude ) {
        this.name = name;
        this.type = type;
        this.location = location ;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public class Apartments{
        public String apartment_type ;
        public String apartment_size;

        public Apartments(String apartment_type , String apartment_size){
            this.apartment_size = apartment_size ;
            this.apartment_type = apartment_type ;
        }

    }

    public class Hostels{
        public String occupancy;
        public String type;

        public Hostels(String occupancy , String type ){
            this.occupancy = occupancy ;
            this.type = type ;
        }
    }

    public class PG{
        public String price ;
        public PG(String price){
            this.price = price ;
        }
    }

    public void initializeImage(int imageId){
        this.imageId = imageId;
    }
}
