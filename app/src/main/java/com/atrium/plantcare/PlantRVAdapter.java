package com.atrium.plantcare;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PlantRVAdapter extends RecyclerView.Adapter<PlantRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<PlantModal> plantModalArrayList;
    private Context context;
    

    // constructor
    public PlantRVAdapter(ArrayList<PlantModal> plantModalArrayList, Context context) {
        this.plantModalArrayList = plantModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklist_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        PlantModal modal = plantModalArrayList.get(position);

        Calendar calendar;
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String currentDate = dateFormat.format(calendar.getTime());

        Animation aniFade = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.anim);

        if (modal != null) {

            if (modal.getWaterFrequency() != null) {

                holder.waterPlantNameText.setText(modal.getPlantName());
                holder.waterPeriodicText.setText(modal.getWaterFrequency() + " day(s) . " + modal.getWaterAmount() + " ml");
               // calendar.add(Calendar.DATE ,Integer.parseInt(modal.getWaterFrequency()));

                Log.i("FindDate",modal.getCurrentDate() +currentDate );

                if (currentDate.equals(modal.getCurrentDate())) {

                    if (modal.getStatus().equals("new")){
                        holder.waterCard.setEnabled(true);
                        holder.waterNotifImage.setImageResource(R.mipmap.noti);

                        
                    }
                    else{
                        holder.waterCard.setEnabled(false);
                        holder.waterNotifImage.setImageResource(R.mipmap.done);


                    }

                    holder.waterCard.setVisibility(View.VISIBLE);
                    holder.itemView.setVisibility(View.VISIBLE);


                } else {
                    holder.waterCard.setVisibility(View.GONE);
                    holder.itemView.setVisibility(View.GONE);
                }
            } else {
                holder.waterCard.setVisibility(View.GONE);
                holder.itemView.setVisibility(View.GONE);
            }

            if (modal.getFeedFrequency() != null) {
                holder.feedCard.setVisibility(View.VISIBLE);
                holder.feedPlantNameText.setText(modal.getPlantName().replaceAll("`feed`",""));
                holder.feedPeriodicText.setText(modal.getFeedFrequency() + " day(s) . " + modal.getFeedAmount() + " gram");
                if (currentDate.equals(modal.getCurrentDate())) {

                    if (modal.getStatus().equals("new")){
                        holder.feedCard.setEnabled(true);
                        holder.feedNotifImage.setImageResource(R.mipmap.noti);


                    }
                    else{
                        holder.feedCard.setEnabled(false);
                        holder.feedNotifImage.setImageResource(R.mipmap.done);
                    }
                    holder.feedCard.setVisibility(View.VISIBLE);
                    holder.itemView.setVisibility(View.VISIBLE);


                } else {

                    holder.feedNotifImage.setVisibility(View.INVISIBLE);
                    holder.feedCard.setVisibility(View.GONE);
                    holder.itemView.setVisibility(View.GONE);
                }

            } else {
                holder.feedCard.setVisibility(View.GONE);
            }

            holder.waterNotifImage.startAnimation(aniFade);
            holder.feedNotifImage.startAnimation(aniFade);
        }

        holder.waterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),TaskDetailsActivity.class);
                i.putExtra("NAME",holder.waterPlantNameText.getText().toString());
                i.putExtra("TYPE","Watering");
                i.putExtra("PERIOD",holder.waterPeriodicText.getText().toString());
                i.putExtra("DATE",currentDate);
                i.putExtra("STATUS",modal.getStatus());
                v.getContext().startActivity(i);
            }
        });

        holder.feedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),TaskDetailsActivity.class);
                i.putExtra("NAME",holder.feedPlantNameText.getText().toString());
                i.putExtra("TYPE","Feeding");
                i.putExtra("PERIOD",holder.feedPeriodicText.getText().toString());
                i.putExtra("DATE",currentDate);
                i.putExtra("STATUS",modal.getStatus());
                v.getContext().startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        // returning the size of our array list
        return plantModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView waterPlantNameText, waterPeriodicText, feedPlantNameText, feedPeriodicText;
        private CardView waterCard, feedCard;
        private ImageView waterNotifImage, feedNotifImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            waterPlantNameText = itemView.findViewById(R.id.waterPlantName);
            waterPeriodicText = itemView.findViewById(R.id.waterPlantPeriodic);
            feedPlantNameText = itemView.findViewById(R.id.feedPlantName);
            feedPeriodicText = itemView.findViewById(R.id.feedPlantPeriodic);
            waterCard = itemView.findViewById(R.id.taskWaterCard);
            feedCard = itemView.findViewById(R.id.taskFeedCard);
            waterNotifImage = itemView.findViewById(R.id.waterNotifImage);
            feedNotifImage = itemView.findViewById(R.id.feedNotifImage);

        }
    }
}

