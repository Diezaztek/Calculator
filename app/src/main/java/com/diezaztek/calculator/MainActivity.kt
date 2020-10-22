/**
 * @author: Francisco Torres Castillo
 * @since: 19/10/2020
 */

package com.diezaztek.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        var resultRegex = "\\-?[0-9]+(.[0-9]+)?[+,\\-,/,*][0-9]+(.[0-9])?".toRegex()

        if(tvInput.text.matches(resultRegex)){

            tvInputResult.text = calculateExpression(tvInput.text.toString())
        }
    }

    fun onClear(view: View){
        tvInput.text = ""
        tvInputResult.text = ""
    }


    fun onDecimalPoint(view: View){
        var currentText = tvInput.text
        var validPattern = "".toRegex();

        if(currentText.indexOf('.') < -1){
            validPattern = ".*[0-9]+".toRegex()
        }else{
            validPattern = ".*[+,\\-,*,/][0-9]+".toRegex()
        }

        if( validPattern.matches(tvInput.text)){
            tvInput.append(".")
        }

    }

    fun onOperator(view:View){
        var validPattern = "".toRegex();
        var keyPressed = (view as Button).text.toString()

        if(keyPressed == "-"){
            validPattern = "(-?[0-9]+(\\.[0-9]+)?[+,\\-,*,/]?)?".toRegex()
        }else{
            validPattern = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        }


        if( validPattern.matches(tvInput.text) ){
            tvInput.append((view as Button).text)
        }

    }

    fun calculateExpression(expression: String): String{
        var prefix = ""
        var tvValue = expression
        try{
            if(tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }

            if(tvValue.contains("-")){
                val splitValue = tvValue.split("-")
                //Example 99-1
                var one = splitValue[0]
                var two = splitValue[1]

                if(!prefix.isEmpty()){
                    one = prefix + one
                }
                return (one.toDouble() - two.toDouble()).toString()
            }else if(tvValue.contains("+")){
                val splitValue = tvValue.split("+")
                //Example 99-1
                var one = splitValue[0]
                var two = splitValue[1]
                return (one.toDouble() + two.toDouble()).toString()
            }else if(tvValue.contains("*")){
                val splitValue = tvValue.split("*")
                //Example 99-1
                var one = splitValue[0]
                var two = splitValue[1]
                return (one.toDouble() * two.toDouble()).toString()
            }else if(tvValue.contains("/")){
                val splitValue = tvValue.split("/")
                //Example 99-1
                var one = splitValue[0]
                var two = splitValue[1]
                return (one.toDouble() / two.toDouble()).toString()
            }
        }catch(e:ArithmeticException){
            return "Syntax error"
        }
        return ""
    }

    fun onEqual(view:View){
        var tvValue = tvInput.text.toString()
        tvInput.text = tvInputResult.text.toString()
        tvInputResult.text = ""


    }
}