package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.Math;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<RowItem> history_list = new ArrayList<>();
    private EditText display;
    private static String saved_display = "0";
    private double first;
    private double second;
    private double result;
    private String equation;
    private boolean onFirstValue = true;
    private boolean overwrite = false;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);
        display.setText(saved_display);
//        display.setText(String.format("%6.3e",223.45654543434));
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        if (oldStr.length() < 11) {
            if (oldStr.equals("0")) {
                display.setText(strToAdd);
            } else {
                if (overwrite) {
                    overwrite = false;
                    display.setText(strToAdd);
                } else {
                    display.setText(oldStr + strToAdd);
                }
            }
        }
    }

    private String formatValue(double value) {
        String str = String.valueOf(value);
        if (str.length() > 10) {
            str = str.substring(0,10);
        }
        if ((value != 0) && (str.endsWith(".0"))) {
            return str.substring(0,str.length()-2);
        } else if (value == 0) {
            return "0";
        } else {
            return str;
        }
    }

    public void historyButton(View view) {
        saved_display = display.getText().toString();
        Intent intent = new Intent (this, HistoryActivity.class);
        startActivity(intent);
    }

    public void zeroButton(View view) {
        updateText("0");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void percentButton(View view) {
        String savedValue = display.getText().toString();
        double value = Double.parseDouble(savedValue)/100;
        display.setText(formatValue(value));
    }

    public void squareRootButton(View view) {
        String savedValue = display.getText().toString();
        double value = Math.sqrt(Double.parseDouble(savedValue));
        display.setText(formatValue(value));
    }

    public void powerButton(View view) {
        String savedValue = display.getText().toString();
        double value = Double.parseDouble(savedValue);
        value *= value;
        display.setText(formatValue(value));
    }

    public void oneXButton(View view) {
        String savedValue = display.getText().toString();
        double value = Double.parseDouble(savedValue);
        value = 1/value;
        display.setText(formatValue(value));
    }

    public void clearButton(View view) {
        display.setText("0");
    }

    public void clearAllButton(View view) {
        display.setText("0");
        onFirstValue = true;
        first = 0;
        second = 0;
        result = 0;
    }

    public void backspaceButton(View view) {
        String original = display.getText().toString();
        if (original.length() == 1) {
            display.setText("0");
        } else {
            String updated = original.substring(0, original.length()-1);
            display.setText(updated);
        }
    }

    public void divideButton(View view) {
        String savedValue = display.getText().toString();
        if (onFirstValue) {
            operation = "divide";
            onFirstValue = false;
            first = Double.parseDouble(savedValue);
            display.setText(savedValue);
        } else {
            evaluate(savedValue);
            operation = "divide";
        }
        overwrite = true;
    }

    public void multiplyButton(View view) {
        String savedValue = display.getText().toString();
        if (onFirstValue) {
            operation = "multiply";
            onFirstValue = false;
            first = Double.parseDouble(savedValue);
            display.setText(savedValue);
        } else {
            evaluate(savedValue);
            operation = "multiply";
        }
        overwrite = true;
    }

    public void addButton(View view) {
        String savedValue = display.getText().toString();
        if (onFirstValue) {
            operation = "add";
            onFirstValue = false;
            first = Double.parseDouble(savedValue);
            display.setText(savedValue);
        } else {
            evaluate(savedValue);
            operation = "add";
        }
        overwrite = true;
    }

    public void subtractButton(View view) {
        String savedValue = display.getText().toString();
        if (onFirstValue) {
            operation = "subtract";
            onFirstValue = false;
            first = Double.parseDouble(savedValue);
            display.setText(savedValue);
        } else {
            evaluate(savedValue);
            operation = "subtract";
        }
        overwrite = true;
    }

    public void equalButton(View view) {
        String savedValue = display.getText().toString();
        evaluate(savedValue);
        onFirstValue = true;
    }

    private void evaluate(String value) {
        String savedValue = value;
        switch (operation) {
            case "add":
                second = Double.parseDouble(savedValue);
                equation = formatValue(first) + "+" + formatValue(second);
                result = first+second;
                first = result;
                overwrite = true;
                history_list.add(new RowItem(equation, formatValue(result)));
                display.setText(formatValue(result));
                operation = "";
                break;
            case "subtract":
                second = Double.parseDouble(savedValue);
                equation = formatValue(first) + "-" + formatValue(second);
                result = first-second;
                first = result;
                overwrite = true;
                history_list.add(new RowItem(equation, formatValue(result)));
                display.setText(formatValue(result));
                operation = "";
                break;
            case "multiply":
                second = Double.parseDouble(savedValue);
                equation = formatValue(first) + "*" + formatValue(second);
                result = first*second;
                first = result;
                overwrite = true;
                history_list.add(new RowItem(equation, formatValue(result)));
                display.setText(formatValue(result));
                operation = "";
                break;
            case "divide":
                second = Double.parseDouble(savedValue);
                equation = formatValue(first) + "/" + formatValue(second);
                result = first/second;
                first = result;
                overwrite = true;
                history_list.add(new RowItem(equation, formatValue(result)));
                display.setText(formatValue(result));
                operation = "";
                break;
            default:
                overwrite = true;
                display.setText(value);
                break;
        }
    }

    public void decimalButton(View view) {
        updateText(".");
    }

    public void signButton(View view) {
        String string = display.getText().toString();
        if (!string.equals("0")) {
            if (string.charAt(0) == '-') {
                display.setText(string.substring(1));
            } else {
                display.setText("-" + string);
            }
        }
        overwrite = true;
    }
}