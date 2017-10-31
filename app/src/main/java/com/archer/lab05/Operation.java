package com.archer.lab05;

import android.content.Context;

/**
 * Created by archer on 2017-10-24.
 */

public class Operation {
    public double first;
    public double second;
    public char operator;
    public double result;

    public boolean isFirstSet;
    public boolean isOpSet;
    public boolean isSecondSet;


    public Operation() {
        this.first = 0;
        this.second = 0;
        this.operator = 0;
        this.result = 0;

        this.isFirstSet = false;
        this.isOpSet = false;
        this.isSecondSet = false;
    }


    public double calculateResult(DatabaseHelper mydb) {

        switch (this.operator){
            case '*' :
                this.result = this.first * this.second;
                break;
            case '/' :
                this.result = this.first / this.second;
                break;
            case '+' :
                this.result = this.first + this.second;
                break;
            case '-' :
                this.result = this.first - this.second;
        }

        mydb.insertData(this.first, this.second, Character.toString(this.operator), this.result);

        return this.result;
    }
}
