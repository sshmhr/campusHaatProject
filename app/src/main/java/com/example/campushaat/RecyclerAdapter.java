package com.example.campushaat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class    RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final List<Places> mplace;
    private LayoutInflater mlayoutInflater;

    public RecyclerAdapter(Context mContext, List<Places> mplace) {
        this.mContext = mContext;
        this.mplace = mplace;
        mlayoutInflater = LayoutInflater.from(mContext);
    }


//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = mlayoutInflater.inflate(R.layout.place_card, viewGroup, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        Places place = mplace.get(i);
//        viewHolder.placeName.setText(place.name);
//        viewHolder.placeType.setText(place.type);
//        viewHolder.location.setText(place.location);
//        viewHolder.placeImage.setImageResource(place.imageId);
//        viewHolder.mposition = i;
//    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        String type = mplace.get(position).type ;
        if(type.toLowerCase().equals("pg")){
            return 0 ;
        }else if(type.toLowerCase().equals("hostel")){
            return 1 ;
        }else{
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View pgView = mlayoutInflater.inflate(R.layout.pg_card, viewGroup, false);
        View hostelView = mlayoutInflater.inflate(R.layout.hostel_card, viewGroup, false);
        View apartmentView = mlayoutInflater.inflate(R.layout.apartment_card, viewGroup, false);
        switch (i){
            case 0 : return new ViewHolderPG(pgView);
            case 1 : return new ViewHolderHostel(hostelView);
            case 2 : return new ViewHolderApartment(apartmentView);
        }
        return null ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Places place = mplace.get(i);
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewHolderPG viewHolderpg = (ViewHolderPG)viewHolder;
                defaultPopulatePG(viewHolderpg,i,place);
//                Log.e("e")
                viewHolderpg.price.setText(place.pg.price);
//                viewHolderpg.price.setText(place.type);
                break;

            case 1:
                ViewHolderHostel viewHolderHostel = (ViewHolderHostel) viewHolder;
                defaultPopulateHostel(viewHolderHostel,i,place);
//                viewHolderHostel.occupancy.setText(place.type);
                viewHolderHostel.occupancy.setText(place.hostels.occupancy);
//                viewHolderHostel.type.setText(place.type);
                viewHolderHostel.type.setText(place.hostels.type);
                break;

            case 2:
                ViewHolderApartment viewHolderApartment = (ViewHolderApartment) viewHolder;
                defaultPopulateApartment(viewHolderApartment,i,place);
//                viewHolderApartment.type.setText(place.type);
                viewHolderApartment.type.setText(place.apartments.apartment_type);
//                viewHolderApartment.size.setText(place.type);
                viewHolderApartment.size.setText(place.apartments.apartment_size);
                break;
        }
    }

    public void defaultPopulatePG(ViewHolderPG viewHolder,int i,Places place) {
        viewHolder.placeName.setText(place.name);
        viewHolder.placeType.setText(place.type);
        viewHolder.location.setText(place.location);
        viewHolder.placeImage.setImageResource(place.imageId);
        viewHolder.mposition = i;
    }

    public void defaultPopulateHostel(ViewHolderHostel viewHolder,int i, Places place) {
        viewHolder.placeName.setText(place.name);
        viewHolder.placeType.setText(place.type);
        viewHolder.location.setText(place.location);
        viewHolder.placeImage.setImageResource(place.imageId);
        viewHolder.mposition = i;
    }

    public void defaultPopulateApartment(ViewHolderApartment viewHolder,int i,Places place) {
        viewHolder.placeName.setText(place.name);
        viewHolder.placeType.setText(place.type);
        viewHolder.location.setText(place.location);
        viewHolder.placeImage.setImageResource(place.imageId);
        viewHolder.mposition = i;
    }


    @Override
    public int getItemCount() {
        return mplace.size();
    }

//    ----------------------------------------------------------------------------------------------

    public class ViewHolderPG extends RecyclerView.ViewHolder {
        public final TextView placeType;
        public final TextView placeName;
        public final TextView location;
        public final ImageView placeImage;
        public final ImageView placeLocation;
        public final TextView price ;
        public int mposition;

        public ViewHolderPG(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.pg_card_price);
            placeName = itemView.findViewById(R.id.card_place_name);
            placeType = itemView.findViewById(R.id.card_place_type);
            placeImage = itemView.findViewById(R.id.card_place_image);
            placeLocation = itemView.findViewById(R.id.card_location);
            location = itemView.findViewById(R.id.location_text);

            placeLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String latitude = mplace.get(mposition).latitude;
                    String longitude = mplace.get(mposition).longitude;

                    Uri mapUri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    mContext.startActivity(mapIntent);
                }
            });
        }
    }



    class ViewHolderApartment extends RecyclerView.ViewHolder {
        public final TextView placeType;
        public final TextView placeName;
        public final TextView location;
        public final ImageView placeImage;
        public final ImageView placeLocation;
        public final TextView size ;
        public final TextView type ;
        public int mposition;

        public ViewHolderApartment(@NonNull View itemView) {
            super(itemView);

            size = itemView.findViewById(R.id.apartment_card_size);
            type = itemView.findViewById(R.id.apartment_card_type);
            placeName = itemView.findViewById(R.id.card_place_name);
            placeType = itemView.findViewById(R.id.card_place_type);
            placeImage = itemView.findViewById(R.id.card_place_image);
            placeLocation = itemView.findViewById(R.id.card_location);
            location = itemView.findViewById(R.id.location_text);

            placeLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String latitude = mplace.get(mposition).latitude;
                    String longitude = mplace.get(mposition).longitude;

                    Uri mapUri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    mContext.startActivity(mapIntent);
                }
            });
        }
    }

    class ViewHolderHostel extends RecyclerView.ViewHolder {
        public final TextView placeType;
        public final TextView placeName;
        public final TextView location;
        public final ImageView placeImage;
        public final ImageView placeLocation;
        public final TextView occupancy ;
        public final TextView type ;
        public int mposition;

        public ViewHolderHostel(View itemView) {
            super(itemView);

            occupancy = itemView.findViewById(R.id.hostel_card_occupancy);
            type = itemView.findViewById(R.id.hostel_card_type);
            placeName = itemView.findViewById(R.id.card_place_name);
            placeType = itemView.findViewById(R.id.card_place_type);
            placeImage = itemView.findViewById(R.id.card_place_image);
            placeLocation = itemView.findViewById(R.id.card_location);
            location = itemView.findViewById(R.id.location_text);

            placeLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String latitude = mplace.get(mposition).latitude;
                    String longitude = mplace.get(mposition).longitude;

                    Uri mapUri = Uri.parse("geo:0,0?q=" + latitude + "," + longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    mContext.startActivity(mapIntent);
                }
            });
        }
    }
}
