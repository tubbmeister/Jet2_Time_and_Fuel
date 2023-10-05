package com.example.timeandfuelandcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    double value1 =5;
    double value2 = 10;
    double first,second;
    int i =0;
    int minuteFirstFromString;
    int hourFirstFromString,hourFirstFromString1,minuteFirstFromString1;
    EditText numberEntry;
    TextView SavedNumber;
    String SavedTime;
    EditText Takeoff, Landing,addFltTime;
    TextView Result,TotalHoursView;
    Button Calculate_Flight_Time;
    LocalTime First_Time,Second_Time;

    LocalTime difference;


    public static final String EXTRA_MESSAGE = "com.example.totaltime.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView resultView = findViewById(R.id.resultView);
        TextView Number1 = findViewById(R.id.hours1);
       // Number1.setText("" + value1);
        TextView Number2 = findViewById(R.id.hours2);


       // Number2.setText("" + value2);
        numberEntry = findViewById(R.id.numberEntry);
        addFltTime=findViewById(R.id.addFltTime);
         SavedNumber = findViewById(R.id.SavedNumber);
        Takeoff=findViewById(R.id.Takeoff);
        Landing=findViewById(R.id.Landing);
        Result=findViewById(R.id.Result);
        Takeoff.setOnClickListener(this);
        Landing.setOnClickListener(this);

        numberEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberEntry.setText("");
            }

        });
    }

    public void Calculate_Duration (View view) throws ParseException {


        String TakeoffTime=Takeoff.getText().toString();
        if(TextUtils.isEmpty(TakeoffTime)){
            Takeoff.setError("Add Time!");
        }
        String LandingTime=Landing.getText().toString();
        if(TextUtils.isEmpty(LandingTime)){
            Landing.setError("Add Time!");
            return;
        }

        int i = LandingTime.length(); // NB time must have $ digits ie 03:30 not 3:30
        if (i<5){
            LandingTime=("0"+LandingTime);

        }
        int j = TakeoffTime.length();
        if (j<5){
            TakeoffTime=("0"+TakeoffTime);
        }
        First_Time=LocalTime.parse(TakeoffTime);
        Second_Time=LocalTime.parse(LandingTime);
        int value=Second_Time.compareTo(First_Time);
        if (value<0){

            Second_Time=Second_Time.plusHours(24); //copes if landing after midnight
        }

        difference=Second_Time.minusHours( First_Time.getHour()).minusMinutes(First_Time.getMinute());

        Result.setText(difference.toString());

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Takeoff:
                Takeoff.getText().clear(); //nb must add ""android.nonFinalResIds=false" to gradle.properties
                break;
            case R.id.Landing:
                Landing.getText().clear();
                break;
        }

    }







    /** called when user taps send button */

    public void sendMessage(View view){

        Intent intent = new Intent (this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity (intent);

    }
    public void StartCalculator(View view){

        Intent intent = new Intent (this, Calculator.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity (intent);

    }

    // do something in response to button

    public void onSubmitClick (View view)throws ParseException{ //hours calc cannot use "time" for numbers bigger than 24:00!

        String dateTime;

        SimpleDateFormat simpleDateFormat;
        simpleDateFormat=new SimpleDateFormat("HH:mm");

        TextView TotalHoursView=findViewById(R.id.TotalHoursView);

        String TotalFlightTime=numberEntry.getText().toString();



        if(TextUtils.isEmpty(TotalFlightTime)){
            numberEntry.setError("Add Time!");
            return;
        }
        String newFltTime=addFltTime.getText().toString();
        if(TextUtils.isEmpty(newFltTime)){
            addFltTime.setError("Add Time!");
            return;
        }

        int i = newFltTime.length(); // NB time must have $ digits ie 03:30 not 3:30
        if (i<5){
            newFltTime=("0"+newFltTime);

        }
      //  int j = TakeoffTime.length();
     //   if (j<5){
      //      TakeoffTime=("0"+TakeoffTime);
      //  }
      //  Duration initialDuration= java.time.Duration.ofHours(2).plusMinutes(34);
       // String timeString = "01:02";
        String isoDurationString = TotalFlightTime.replaceFirst("(\\d+):(\\d{2})", "PT$1H$2M");

        Duration dur = Duration.parse(isoDurationString);
       // First_Time=LocalTime.parse(TotalFlightTime);
        String isoDurationString1 = newFltTime.replaceFirst("(\\d+):(\\d{2})", "PT$1H$2M");

        Duration dur1 = Duration.parse(isoDurationString1);
       // Second_Time=LocalTime.parse(newFltTime);
        //int value=Second_Time.compareTo(First_Time);
       // if (value<0){

      //      Second_Time=Second_Time.plusHours(24); //copes if landing after midnight
     //   }
        dur=dur.plus(dur1);

      //  difference=Second_Time.plusHours( dur.getHour()).plusMinutes(First_Time.getMinute());
        long totalMinutes=dur.toMinutes(); //use the Duration property to convert to minutes to actually do the calculation
        int totalMinutesInt= (int) totalMinutes; //convert long to int
        int totalHours=(totalMinutesInt/60); //number of hours
        int totalMinutesResult=(totalMinutesInt-(totalHours*60));//remainder of minutes
        String HoursFinal=Integer.toString(totalHours);
        String MinutesFinal=Integer.toString(totalMinutesResult);

        TotalHoursView.setText(HoursFinal+":"+MinutesFinal); //display total time as a string
        if (totalMinutesResult<10) {
            TotalHoursView.setText(HoursFinal+":0"+MinutesFinal); //make answer look like a time if less than 10 mins
        }
        SavedNumber.setText(TotalHoursView.getText());




         }
    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("storedTime", "");
       // int a = sh.getInt("age", 0);

        // Setting the fetched data
        // in the EditTexts
        SavedNumber.setText(s1);
        //age.setText(String.valueOf(a));
    }

        @Override
    protected void onPause(){

        super.onPause();

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("storedTime", SavedNumber.getText().toString());
           // myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
            myEdit.apply();
        }

        }




