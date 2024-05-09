package com.atrium.plantcare;

import static com.atrium.plantcare.R.drawable.back_greenlight;
import static com.atrium.plantcare.R.drawable.back_grey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewPlantActivity extends AppCompatActivity {
    CardView mWaterCard, mFeedCard;

    String waterAmount, waterFrequency, plantName, feedAmount, feedFrequency, currentDate, currentDateFeed;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    Switch mWaterSwitch, mFeedSwitch;

    ImageButton mSaveBtn, mCancelBtn;

    TextView mPlantNameLabel,
            mWaterAmountLabel,
            mWaterFrequencyLabel,
            mFeedFrequencyLabel,
            mFeedAmountLabel;

    EditText mPlantNameText,
            mWaterFrequencyText,
            mWaterAmountText,
            mFeedFrequencyText,
            mFeedAmountText;

    DBHandler dbHandler;

    int feedTag = 0;
    int waterTag = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plant);

        dbHandler = new DBHandler(this);


        mWaterCard = findViewById(R.id.waterCard);
        mWaterSwitch = findViewById(R.id.waterSwitch);

        mFeedCard = findViewById(R.id.feedcard);
        mFeedSwitch = findViewById(R.id.feedSwitch);

        mPlantNameText = findViewById(R.id.plantNameText);

        mWaterFrequencyText = findViewById(R.id.waterFrequencyText);
        mWaterAmountText = findViewById(R.id.waterAmountText);

        mWaterFrequencyLabel = findViewById(R.id.waterFrequencyLabel);
        mWaterAmountLabel = findViewById(R.id.waterAmountLabel);

        mFeedFrequencyText = findViewById(R.id.feedFrequencyText);
        mFeedAmountText = findViewById(R.id.feedAmountText);

        mFeedFrequencyLabel = findViewById(R.id.feedFrequencyLabel);
        mFeedAmountLabel = findViewById(R.id.feedAmountLabel);

        mSaveBtn = findViewById(R.id.saveButton);
        mCancelBtn = findViewById(R.id.cancelButton);

        mFeedFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
        mWaterAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
        mWaterFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
        mFeedAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));

        mFeedCard.setVisibility(View.GONE);


        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = dateFormat.format(calendar.getTime());
        currentDateFeed = dateFormat.format(calendar.getTime());


        mWaterSwitch.setChecked(true);
        mWaterSwitch.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#0E4DA4")));
        mWaterSwitch.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#0E4DA4")));

        mFeedSwitch.setChecked(false);
        mFeedSwitch.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#979797")));
        mFeedSwitch.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#979797")));

        // change the label color and border color of edit text on focus

        mWaterFrequencyText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mWaterFrequencyText.setBackground(getDrawable(back_greenlight));

                mWaterAmountText.setBackground(getDrawable(back_grey));
                mFeedAmountText.setBackground(getDrawable(back_grey));
                mFeedFrequencyText.setBackground(getDrawable(back_grey));

                mWaterFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));
                mWaterAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mFeedAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mFeedFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));

            }
        });

        mWaterFrequencyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mWaterFrequencyText.getText().toString().trim().length() == 0) {
                    // mWaterFrequencyText.setBackground(getDrawable(back_grey));
                } else {
                    // mWaterFrequencyText.setBackground(getDrawable(back_greenlight));

                }
            }
        });

//Water Amount

        mWaterAmountText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                mWaterAmountText.setBackground(getDrawable(back_greenlight));
                mWaterAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));


                mWaterFrequencyText.setBackground(getDrawable(back_grey));
                mFeedAmountText.setBackground(getDrawable(back_grey));
                mFeedFrequencyText.setBackground(getDrawable(back_grey));

                mWaterAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));
                mWaterFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mFeedAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mFeedFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));


            }
        });

        mFeedFrequencyText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                mFeedFrequencyText.setBackground(getDrawable(back_greenlight));
                mFeedFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));


                mFeedAmountText.setBackground(getDrawable(back_grey));
                mWaterAmountText.setBackground(getDrawable(back_grey));
                mWaterFrequencyText.setBackground(getDrawable(back_grey));

                mFeedFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));
                mWaterAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mWaterFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mFeedAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));


            }
        });

        mFeedAmountText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                mFeedAmountText.setBackground(getDrawable(back_greenlight));
                mFeedAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));


                mWaterAmountText.setBackground(getDrawable(back_grey));
                mWaterFrequencyText.setBackground(getDrawable(back_grey));
                mFeedFrequencyText.setBackground(getDrawable(back_grey));

                mFeedAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#00994D")));
                mFeedFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mWaterAmountLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));
                mWaterFrequencyLabel.setTextColor(ColorStateList.valueOf(Color.parseColor("#263238")));


            }
        });


        mWaterAmountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mWaterAmountText.getText().toString().trim().length() == 0) {
                    mWaterAmountText.setBackground(getDrawable(back_grey));
                } else {
                    mWaterAmountText.setBackground(getDrawable(back_greenlight));

                }
            }
        });


        mWaterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (waterTag == 0) {
                    waterTag = 1;
                    mWaterCard.setVisibility(View.VISIBLE);
                    mWaterSwitch.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#0E4DA4")));
                    mWaterSwitch.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#0E4DA4")));

                } else {
                    waterTag = 0;
                    mWaterCard.setVisibility(View.GONE);
                    mWaterSwitch.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#979797")));
                    mWaterSwitch.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#979797")));

                }
            }
        });

        mFeedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (feedTag == 0) {
                    feedTag = 1;
                    mFeedCard.setVisibility(View.VISIBLE);
                    mFeedSwitch.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#0E4DA4")));
                    mFeedSwitch.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#0E4DA4")));
                } else {
                    feedTag = 0;
                    mFeedCard.setVisibility(View.GONE);
                    mFeedSwitch.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#979797")));
                    mFeedSwitch.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#979797")));

                }
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (mWaterSwitch.isChecked() && !mFeedSwitch.isChecked()) {
                            if (mPlantNameText.getText().toString().trim().length() != 0 && mWaterAmountText.getText().toString().trim().length() != 0 && mWaterFrequencyText.getText().toString().trim().length() != 0) {
                                waterFrequency = mWaterFrequencyText.getText().toString();
                                waterAmount = mWaterAmountText.getText().toString();
                                waterFrequency = mWaterFrequencyText.getText().toString();
                                plantName = mPlantNameText.getText().toString();
                                feedFrequency = null;
                                feedAmount = null;

                                for (int i = 1; i <= 365 / Integer.parseInt(waterFrequency); i++) {
                                    Log.i("FAAAA", currentDate.toString());
                                    callWaterDb();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Reminder Set!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } else if (mFeedSwitch.isChecked() && !mWaterSwitch.isChecked()) {
                            // Similar logic for feed switch
                        } else if (mFeedSwitch.isChecked() && mWaterSwitch.isChecked()) {
                            // Similar logic for both switches
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void callWaterDb() {
        try {

            dbHandler.addNewPlant(plantName, currentDate, waterFrequency, waterAmount, feedFrequency, feedAmount, "new");
            calendar.add(Calendar.DATE, Integer.parseInt(waterFrequency));
            currentDate = dateFormat.format(calendar.getTime());
            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callFeedDb() {
        try {

            dbHandler.addNewPlant(plantName, currentDateFeed, waterFrequency, waterAmount, feedFrequency, feedAmount, "new");
            calendar.add(Calendar.DATE, Integer.parseInt(feedFrequency));
            currentDateFeed = dateFormat.format(calendar.getTime());
            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callFeedDb2() {
        try {
            dbHandler.addNewPlant(plantName+"`feed`", currentDateFeed, null, null, feedFrequency, feedAmount, "new");
            calendar.add(Calendar.DATE, Integer.parseInt(feedFrequency));
            currentDateFeed = dateFormat.format(calendar.getTime());
            Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callWaterFeedDb() {
        try {
            dbHandler.addNewPlant(plantName, currentDate, waterFrequency, waterAmount, null, feedAmount, "new");
            calendar.add(Calendar.DATE, Integer.parseInt(waterFrequency));
            currentDate = dateFormat.format(calendar.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}