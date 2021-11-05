package com.example.plantcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    ImageButton mAddPlantButton;
    ImageView mPlantImage, mLabelImage;
    ConstraintLayout homeConstraint;
    ImageView tasksLabel;
    CardView buttonCard;

    private ArrayList<PlantModal> plantModalArrayList, plantModalDateList;
    private DBHandler dbHandler;
    private PlantRVAdapter plantRVAdapter;
    private RecyclerView plantRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAddPlantButton = findViewById(R.id.newPlantButton);
        mPlantImage = findViewById(R.id.plantImage);
        mLabelImage = findViewById(R.id.labelImage);
        homeConstraint = findViewById(R.id.homeConstraint);
        tasksLabel = findViewById(R.id.tasksLabel);
        buttonCard = findViewById(R.id.addplantCard);

        Handler handler = new Handler();

        CustomTimerTask task = new CustomTimerTask(handler);

        new Timer().scheduleAtFixedRate(task, 0, 2000);


        plantModalArrayList = new ArrayList<>();
        plantModalDateList = new ArrayList<>();
        dbHandler = new DBHandler(HomeActivity.this);


        plantModalArrayList = dbHandler.readPlants();

        plantRVAdapter = new PlantRVAdapter(plantModalArrayList, HomeActivity.this);
        plantRV = findViewById(R.id.idRVPlants);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, RecyclerView.VERTICAL, false);
        plantRV.setLayoutManager(linearLayoutManager);


        plantRV.setAdapter(plantRVAdapter);

        int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        // Toast.makeText(getApplicationContext(),String.valueOf(plantRV.get()),Toast.LENGTH_LONG).show();

        if (plantRVAdapter.getItemCount() != 0) {
            mPlantImage.setVisibility(View.GONE);
            mLabelImage.setVisibility(View.GONE);
            homeConstraint.setBackgroundColor(Color.parseColor("#F0EFF4"));
            tasksLabel.setImageResource(R.mipmap.tasksgreen);
//            buttonCard.setBackgroundColor(Color.parseColor("#00994D"));


            mAddPlantButton.setBackgroundColor(Color.parseColor("#00994D"));
            mAddPlantButton.setImageResource(R.mipmap.pluswhite);

        } else {

            mPlantImage.setVisibility(View.VISIBLE);
            mLabelImage.setVisibility(View.VISIBLE);

        }

        mAddPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPlantIntent = new Intent(getApplicationContext(), NewPlantActivity.class);
                startActivity(newPlantIntent);
            }
        });

    }
    @Override
    public void onBackPressed() {

    }

    public class CustomTimerTask extends TimerTask {
        private Handler handler;

        public CustomTimerTask(Handler h) {
            this.handler = h;
        }


        @Override
        public void run() {
            //run the code you want to run
            Calendar calendar;
            calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = dateFormat.format(calendar.getTime());

            //update UI using the handler
            handler.post(new Runnable() {
                public void run() {
                    //update UI here.
                }
            });

        }
    }
}