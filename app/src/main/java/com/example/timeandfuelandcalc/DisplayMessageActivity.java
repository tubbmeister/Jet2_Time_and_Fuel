package com.example.timeandfuelandcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity implements View.OnClickListener {

    int DoSums,i=0;
    TextView test;
    EditText LeftTank;
    EditText CentreTank;
    EditText RightTank;
    EditText TankTotal;
    EditText NeededFuel;
    EditText ActualLitres;
    EditText Temperature;
    EditText ExpectedLitres;
    EditText SpecificGravity;
    EditText CalcUplift;
    Button Reset;

    public static final String EXTRA_MESSAGE = "com.example.totaltime.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //TextView textView2 = findViewById(R.id.textView2);
        //textView2.setText(message);
         test = findViewById(R.id.DifferenceText);
         LeftTank = findViewById(R.id.LeftTank);
        CentreTank = findViewById(R.id.CentreTank);
        RightTank = findViewById(R.id.RightTank);
        TankTotal = findViewById(R.id.TankTotal);
        NeededFuel = findViewById(R.id.NeededFuel);
        ActualLitres = findViewById(R.id.ActualLitres);
        Temperature = findViewById(R.id.Temperature);
        CalcUplift = findViewById(R.id.CalcUplift);
        ExpectedLitres = findViewById(R.id.ExpectedLitres);
        SpecificGravity = findViewById(R.id.SpecificGravity);
        LeftTank.setOnClickListener(this);
        CentreTank.setOnClickListener(this);
        RightTank.setOnClickListener(this);
        NeededFuel.setOnClickListener(this);
        ActualLitres.setOnClickListener(this);
        Temperature.setOnClickListener(this);
        //CalcUplift.setOnClickListener(this);
       // Reset.setOnClickListener(this);
    }


        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.LeftTank:
                    LeftTank.getText().clear(); //or you can use editText.setText("");
                    break;
                case    R.id.CentreTank:
                    CentreTank.getText().clear(); //or you can use editText.setText("");
                    break;
                case     R.id.RightTank:
                    RightTank.getText().clear(); //or you can use editText.setText("");
                    break;
                case R.id.NeededFuel:  //must be same as id above!
                    NeededFuel.getText().clear(); //or you can use editText.setText("");
                    break;
                case R.id.ActualLitres:
                    ActualLitres.getText().clear(); //or you can use editText.setText("");
                    break;
                 case R.id.Temperature:
                     Temperature.getText().clear(); //or you can use editText.setText("");
                     break;
            }

        }

        public void Reset(View view){
        LeftTank.setText("");
        RightTank.setText("");
        CentreTank.setText("");
        NeededFuel.setText("");
        Temperature.setText("");
        TankTotal.setText("");
        CalcUplift.setText("");
        SpecificGravity.setText("");
        ExpectedLitres.setText("");






        }

    public void StartCalculator(View view){

        Intent intent = new Intent (this, Calculator.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
       // String message = editText.getText().toString();
       // intent.putExtra(EXTRA_MESSAGE,message);
        startActivity (intent);

    }




    public void DoCalculation (View view) {




        DoSums=(1+2+5);

                //Integer.parseInt(LeftTank.getText().toString())+
       //  Integer.parseInt(CentreTank.getText().toString())
     //   +Integer.parseInt(RightTank.getText().toString()));
       //TankTotal.setText(String.valueOf(DoSums));
       // TankTotal.setText(String.valueOf(12345));
        TankTotal.setText("Hello again");
    }

    public void ButtonTest(View view){



        String LeftTankString=LeftTank.getText().toString();
        if(TextUtils.isEmpty(LeftTankString)) {
            LeftTank.setError("Add Fuel!");
            return;
        }
        String RightTankString=RightTank.getText().toString();
        if(TextUtils.isEmpty(RightTankString)) {
            RightTank.setError("Add Fuel!");
            return;
        }
        String CentreTankString=CentreTank.getText().toString();
        if(TextUtils.isEmpty(CentreTankString)) {
            CentreTank.setText("0");

        }

        int LeftTank1=(Integer.parseInt(LeftTank.getText().toString()));

        int CentreTank1=((Integer.parseInt(CentreTank.getText().toString())));
        int RightTank1=(Integer.parseInt(RightTank.getText().toString()));
        DoSums=(LeftTank1+CentreTank1+RightTank1);
       // RightTank.setText("24");

        //test.setText("Hello");
       // test.setText(String.valueOf(DoSums));
        TankTotal.setText(String.valueOf(DoSums)+" kg");



       // if(RequiredFuel)
        String TotalRequiredString=NeededFuel.getText().toString();
        String TemperatureString=Temperature.getText().toString();

        if(TextUtils.isEmpty(TotalRequiredString)) {
            //RequiredFuel.setError("Add Total!");
            return;
        }
        else {if (TextUtils.isEmpty(TemperatureString)) {
            Temperature.setError("Add Temperature!");
            return;
        }

        int FuelNeededTotal1=(Integer.parseInt(NeededFuel.getText().toString()));
        double Temperature1=(Integer.parseInt(Temperature.getText().toString()));

        if(Temperature1<5){
                Temperature1=(0.806);
            }
            else if(Temperature1>=5 && Temperature1<=18){
                Temperature1=(0.8);


            }
            else if (Temperature1>=19 && Temperature1<=30){
                Temperature1=(0.787);
        }
            else if (Temperature1>30){
                Temperature1=(0.781);

        }
        double fuelcalculation=((FuelNeededTotal1-DoSums)/Temperature1);
            int calcFuel=(FuelNeededTotal1-DoSums);
            CalcUplift.setText((String.valueOf(calcFuel))+" kg");
            int output=(int) Math.round(fuelcalculation);
            ExpectedLitres.setText((String.valueOf(output))+" ltr");
            String TotalActualLitresString=ActualLitres.getText().toString();
            SpecificGravity.setText(String.valueOf(Temperature1)+" SG");

        if(TextUtils.isEmpty(TotalActualLitresString)) {
            //RequiredFuel.setError("Add Total!");
            return;
        }
        else {
            int actualLitres1=(Integer.parseInt(TotalActualLitresString));
            int ActualDifference=(actualLitres1-output);
            test.setText((String.valueOf(ActualDifference))+" litre difference.");


            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

                if (ActualDifference>625 || ActualDifference<-625){
                    test.setText((String.valueOf(ActualDifference))+" litre  Discrepancy!");
                    test.setTextColor(Color.RED);
                }
        }
        }
    }
}