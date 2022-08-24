package com.example.timeandfuelandcalc;

import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;

import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;


public class Calculator extends AppCompatActivity  {
    Button ZeroBtn;
    EditText EditTextCalculator;
    TextView previousCalcTxt,textView3;
    boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_calculator);
        previousCalcTxt=findViewById(R.id.previousCalcTxt);
        textView3=findViewById(R.id.textView3);
        EditTextCalculator = findViewById(R.id.EditTextCalculator);
        EditTextCalculator.setShowSoftInputOnFocus(false);
        EditTextCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(EditTextCalculator.getText().toString())) {

                    EditTextCalculator.setText("");
                }

            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = EditTextCalculator.getText().toString();
        int cursorPos = EditTextCalculator.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr =oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(EditTextCalculator.getText().toString())){

            EditTextCalculator.setText(strToAdd);
            EditTextCalculator.setSelection(cursorPos+1);
        }
        else{
            EditTextCalculator.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
            EditTextCalculator.setSelection(cursorPos+1);
        }

    }

    public void zero_BTN_click(View v){
    //stuff
      //  EditTextCalculator.setText("Hello");
         updateText("0");
}
    public void One_BTN(View v){
        updateText("1");

    }
    public void Point_BTN(View v){
        updateText(".");

    }
    public void Two_BTN(View v){
        updateText("2");
    }
    public void Three_BTN(View v){
        updateText("3");
    }
    public void Four_BTN(View v){
        updateText("4");
    }
    public void Five_BTN(View v){
        updateText("5");
    }
    public void Six_BTN(View v){
        updateText("6");
    }
    public void Seven_BTN(View v){
        updateText("7");
    }
    public void Eight_BTN(View v){
        updateText("8");
    }
    public void Nine_BTN(View v){
        updateText("9");
    }
    public void Multiply_BTN(View v){
        updateText("×");

    }
    public void Exponent_BTN(View v){
        updateText("^");

    }
    public void PlusMin_BTN(View v){
        updateText("-");

    }
    public void Par_BTN(View v) {
        int cursorPos = EditTextCalculator.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = EditTextCalculator.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (EditTextCalculator.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;

            }
            if (EditTextCalculator.getText().toString().substring(i, i + 1).equals(")")) {
                closedPar += 1;
            }
        }
        if (openPar == closedPar || EditTextCalculator.getText().toString().substring(textLen - 1, textLen).equals("(")) {


            updateText("(");

        }
        else if (closedPar < openPar && !EditTextCalculator.getText().toString().substring(textLen - 1, textLen).equals("(")){
            ;


            updateText(")");

        }
        EditTextCalculator.setSelection(cursorPos + 1);
    }
    public void Divide_BTN(View v){
        updateText("÷");

    }
    public void Add_BTN(View v){
        updateText("+");

    }
    public void Subtract_BTN(View v){
        updateText("-");

    }
    public void Equals_BTN(View v){

        String userExp =EditTextCalculator.getText().toString();
        String oldAnswer=userExp;
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×", "*");
        Expression exp=new Expression(userExp);
        String result=String.valueOf(exp.calculate());
        EditTextCalculator.setText(result);
        EditTextCalculator.setSelection(result.length());
        BigDecimal improvedAnswer = new BigDecimal(result).stripTrailingZeros(); //gets rid of zeros!
        String improvedAnswerString = improvedAnswer.toPlainString();
        previousCalcTxt.setText(improvedAnswerString);
        textView3.setText(oldAnswer);
        EditTextCalculator.setText("");
         answered=true;
         if (oldAnswer.isEmpty()){
             previousCalcTxt.setText("");
             textView3.setText("");
         }
    }
    public void Backspace_BTN(View v){
        int cursorPos=EditTextCalculator.getSelectionStart();
        int textLen=EditTextCalculator.getText().length();

        if(cursorPos !=0 && textLen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) EditTextCalculator.getText();
            selection.replace(cursorPos -1,cursorPos,"");
            EditTextCalculator.setText(selection);
            EditTextCalculator.setSelection(cursorPos -1);
        }

    }
    public void Clear_BTN(View v){
        //updateText("");
        EditTextCalculator.setText("");
    }


}
