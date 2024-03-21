package com.example.myunitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> unit1;
    ArrayAdapter<String> unit2;
    Spinner unitTypeSelector;
    Spinner units1;
    Spinner units2;
    EditText inputUnits;
    TextView resultUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Spinners
        unitTypeSelector = findViewById(R.id.unitType);
        units1 = findViewById(R.id.measurements1);
        units2 = findViewById(R.id.measurements2);

        //Inputs and results
        inputUnits = (EditText) findViewById(R.id.inputUnits);
        resultUnits = (TextView) findViewById(R.id.resultUnits);

        //Contains all array info
        ArrayList<String> unitTypes = new ArrayList<>();
        unitTypes.add("Weight");
        unitTypes.add("Length");
        unitTypes.add("Temperature");

        //Contains all units
        ArrayList<String> lengths = new ArrayList<>();
        lengths.add("Inches");
        lengths.add("Feet");
        lengths.add("Yards");
        lengths.add("Miles");

        ArrayList<String> weights = new ArrayList<>();
        weights.add("Pounds");
        weights.add("Ounces");
        weights.add("Tonnes");

        ArrayList<String> temperatures = new ArrayList<>();
        temperatures.add("Celsius");
        temperatures.add("Fahrenheit");
        temperatures.add("Kelvin");

        //Setting up spinner which selects the unit type we are converting like weight or length
        unitTypeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected " + item, Toast.LENGTH_SHORT).show();

                if(position == 0)
                {
                    unit1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, weights);
                    unit2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, weights);
                }

                if(position == 1)
                {
                    unit1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, lengths);
                    unit2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, lengths);
                }

                if(position == 2)
                {
                    unit1 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, temperatures);
                    unit2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, temperatures);
                }
                units1.setAdapter(unit1);
                units2.setAdapter(unit2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Completes the unit type selection spinner with a reactive pop-up on selection
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitTypes);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        unitTypeSelector.setAdapter(adapter);

    }

    public void handleButtonSubmit(View view) {

        int sp1 = unitTypeSelector.getSelectedItemPosition();
        int sp2 = units1.getSelectedItemPosition();
        int sp3 = units2.getSelectedItemPosition();

        if(sp1 == 0)
        {
            if(sp2 == 0)
            {
                if(sp3 == 0) //Pounds
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 1) //Pounds into Ounces
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 16;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Pounds into Tonnes
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 2000;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 1)
            {
                if(sp3 == 0) //Ounces into Pounds
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 16;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Ounces
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 2) //Ounces into Tonnes
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 32000;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 2)
            {
                if(sp3 == 0) //Tonnes to Pounds
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 2000;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Tonnes into Ounces
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 32000;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Tonnes
                {
                    resultUnits.setText(inputUnits.getText());
                }
            }

        }
        if(sp1 == 1)
        {
            if(sp2 == 0)
            {
                if(sp3 == 0) //Inches
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 1) //Feet
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 12;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Yards
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 36;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 3) //Miles
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 63360;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 1)
            {
                if(sp3 == 0) //Inches
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 12;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Feet
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 2) //Yards
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 3;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 3) //Miles
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 5280;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 2)
            {
                if(sp3 == 0) //Inches
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 36;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Feet
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 3;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Yards
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 3) //Miles
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting / 1760;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 3)
            {
                if(sp3 == 0) //Inches
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 63360;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Feet
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 5280;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Yards
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting * 1760;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 3) //Miles
                {
                    resultUnits.setText(inputUnits.getText());
                }
            }
        }
        if(sp1 == 2)
        {
            if(sp2 == 0)
            {
                if(sp3 == 0) //Celcius
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 1) //Fahrenheit
                {
                    Double converting = Double.parseDouble(inputUnits.getText().toString());
                    Double converted = (converting * 1.8) + 32;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Kelvin
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting + 273;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 1)
            {
                if(sp3 == 0) //Celcius
                {
                    Double converting = Double.parseDouble(inputUnits.getText().toString());
                    Double converted = (converting - 32) * 0.55;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Fahrenheit
                {
                    resultUnits.setText(inputUnits.getText());
                }
                if(sp3 == 2) //Kelvin
                {
                    Double converting = Double.parseDouble(inputUnits.getText().toString());
                    Double converted = ((converting - 32) * 5/9) + 273;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
            }
            if(sp2 == 2)
            {
                if(sp3 == 0) //Celcius
                {
                    int converting = Integer.parseInt(inputUnits.getText().toString());
                    int converted = converting - 273;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 1) //Fahrenheit
                {
                    Double converting = Double.parseDouble(inputUnits.getText().toString());
                    Double converted = ((converting - 273) * 9/5) + 32;
                    String results = String.valueOf(converted);
                    resultUnits.setText(results);
                }
                if(sp3 == 2) //Kelvin
                {
                    resultUnits.setText(inputUnits.getText());
                }
            }
        }

    }
}