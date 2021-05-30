package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;


import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Calculation c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        c = new Calculation();
        setContentsOfTextView(R.id.textview_first, "0.00");
        setContentsOfTextView(R.id.textview_second, "");
    }

    //----------Helper Method to Display Output------------
    private void setContentsOfTextView(int id, String newContents){
        View view = findViewById(id);
        TextView textView = (TextView)view;
        textView.setText(newContents);
    }

    public void checkAndResetCalculator(){
        if (c.clickedEqualLast){
            c = new Calculation();
            setContentsOfTextView(R.id.textview_first, "");
            setContentsOfTextView(R.id.textview_second, "");
        }
    }

    //----------Operator Button Actions------------
    public void operatorButtonHelper(char operator){
        //If 1st operand is not empty and last button was equal OR the operator counter greater than 0
        if ((c.firstOP.toString().length() != 0) && (c.clickedEqualLast || c.operatorCounter > 0)) {
            setContentsOfTextView(R.id.textview_second, c.getFirstOP() + " " + c.operator + " " + c.getSecondOP() + " = " + c.solveEquationHelper()+ " " + operator);
            c.setOperator(operator);
            setContentsOfTextView(R.id.textview_first, c.firstOP.toString());
        }
        else{
            c.setOperator(operator);
            setContentsOfTextView(R.id.textview_second, c.getFirstOP() + " " + operator);
            setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
        }
    }

    public void buttonMultiplyClick(View view){
        operatorButtonHelper('*');
    }
    public void buttonAddClick(View view){
        operatorButtonHelper('+');
    }
    public void buttonSubtractClick(View view){
        operatorButtonHelper('-');
    }
    public void buttonDivideClick(View view){
        operatorButtonHelper('/');
    }

    public void buttonModuloClick(View view){
        operatorButtonHelper('%');
    }

    public void buttonUndoClick(View view){
        c.undo();
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonClearClick(View view){
        c = new Calculation();
        setContentsOfTextView(R.id.textview_first, "0.00");
        setContentsOfTextView(R.id.textview_second, "");
    }

    public void buttonPlusMinClick(View view){
        c.negateOperand();
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonEqualsClick(View view){
        String result = Float.toString(c.solveEquation());
        setContentsOfTextView(R.id.textview_first, result);
        if (c.secondOP.toString().length() == 0){
            setContentsOfTextView(R.id.textview_second, c.getFirstOP());
        }
        else{
            setContentsOfTextView(R.id.textview_second, c.getFirstOP() + " " + c.operator + " " + c.getSecondOP() + " = " +result);
        }
    }


    //----------Operand Button Actions------------
    public void buttonDecimalClick(View view){
        c.concatToCurrentOP(".");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonOneClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("1");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonTwoClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("2");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonThreeClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("3");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonFourClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("4");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonFiveClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("5");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonSixClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("6");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonSevenClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("7");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonEightClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("8");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonNineClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("9");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }

    public void buttonZeroClick(View view){
        checkAndResetCalculator();
        c.concatToCurrentOP("0");
        setContentsOfTextView(R.id.textview_first, c.currentOP.toString());
    }



}