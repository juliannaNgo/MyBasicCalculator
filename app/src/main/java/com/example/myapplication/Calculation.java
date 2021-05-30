package com.example.myapplication;

public class Calculation {
    StringBuilder currentOP;
    StringBuilder firstOP;
    StringBuilder secondOP;
    char operator;
    boolean canUndo;
    int operatorCounter;
    boolean clickedOperatorLast;
    boolean clickedEqualLast;


    Calculation(){
        firstOP = new StringBuilder("");
        secondOP = new StringBuilder("");
        currentOP = firstOP;
        operator = Character.MIN_VALUE;
        canUndo = true;
        clickedEqualLast = false;
        clickedOperatorLast = false;
        operatorCounter = 0;
    }

    public void undo(){
        if (canUndo && currentOP.length() > 0){
            currentOP = currentOP.deleteCharAt(currentOP.length()-1);
        }
    }

    public void concatToCurrentOP(String number){
        this.currentOP = this.currentOP.append(number);
        this.canUndo = true;
        this.clickedOperatorLast = false;
        this.clickedEqualLast = false;
    }

    public void negateOperand(){
        // If the current operand is empty, append negative
        if (this.currentOP.length() == 0){
            this.currentOP = this.currentOP.append('-');
        }
        // If current operand is already negative, remove negative
        else if (this.currentOP.charAt(0) == '-'){
            this.currentOP = this.currentOP.replace(0, 1, "");
            if (this.currentOP == firstOP) System.out.println("First OP = " + firstOP.toString());
            else if (this.currentOP == secondOP) System.out.println("Second = " + secondOP.toString());

        }
        // Otherwise, add negative symbol to the current operand String
        else{
            this.currentOP = this.currentOP.insert(0, '-');
        }
        this.clickedOperatorLast = false;
        this.clickedEqualLast = false;
    }

    public String getFirstOP(){
        return this.firstOP.toString();
    }

    public String getSecondOP(){
        return this.secondOP.toString();
    }

    public void setOperator(char op) {
        // If clicking operator before 1st operand, do nothing
        if (this.firstOP.toString().length() == 0) {
            return;
        }
        //If clicking Operator after an equal sign, set first OP as prev result, clear 2nd OP
        if (this.clickedEqualLast || operatorCounter > 0){
            this.firstOP = new StringBuilder(Float.toString(solveEquationHelper()));
            this.secondOP = new StringBuilder("");
            this.currentOP = secondOP;
        }
        switch (op) {
            case '+':
                this.operator = '+';
                break;
            case '-':
                this.operator = '-';
                break;
            case '*':
                this.operator = '*';
                break;
            case '%':
                this.operator = '%';
                break;
            default:
                this.operator = '/';
                break;
        }
        this.operatorCounter++;
        this.canUndo = false;
        this.currentOP = secondOP;
        this.clickedEqualLast = false;
        this.clickedOperatorLast = true;
    }

    public float solveEquation(){
        // If last click was equal, operand 1 = old result, operand 2 remains the same, return new result
        if (this.clickedEqualLast){
            float tempOP1 = solveEquationHelper();
            this.firstOP = new StringBuilder(Float.toString(tempOP1));
            this.clickedOperatorLast = false;
            return solveEquationHelper();
        }
        // If both first and second operands are empty, return 0.00
        else if (this.firstOP.toString().length() == 0 && this.secondOP.toString().length() == 0){
            return 0.00f;
        }
        //If second operand is empty, return first operand
        else if (this.secondOP.toString().length() == 0){
            this.clickedOperatorLast = false;
            return Float.parseFloat(this.firstOP.toString());
        }
        //Otherwise, return result of the equation
        else{
            this.clickedOperatorLast = false;
            this.clickedEqualLast = true;
            return solveEquationHelper();
        }
    }

    public float solveEquationHelper(){
        float operand1 = Float.parseFloat(firstOP.toString());
        float operand2 = Float.parseFloat(secondOP.toString());
        float result;
        switch(this.operator){
            case '+':
                result = operand1+operand2;
                break;
            case '-':
                result = operand1-operand2;
                break;
            case '*':
                result = operand1*operand2;
                break;
            case '%':
                result = operand1%operand2;
                break;
            default:
                result = operand1/operand2;
                break;
        }
        return result;
    }
}
