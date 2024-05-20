package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteItem extends AppCompatActivity {

    RadioButton lostButton, foundButton;
    Button backButton;
    Button deleteButton;
    LostFoundItemDB lostFoundItemDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_item);

        //DATA
        lostButton = (RadioButton)findViewById(R.id.radioButtonLost);
        foundButton = (RadioButton)findViewById(R.id.radioButtonFound);

        String id = getIntent().getStringExtra("id");
        String type = getIntent().getStringExtra("type"); //aw fuck (fixed)
        String itemName = getIntent().getStringExtra("itemName");
        String contact = getIntent().getStringExtra("contact"); //turns into item name (fixed)
        String description = getIntent().getStringExtra("description"); //DOESN'T SAVE?? (fixed)
        String time = getIntent().getStringExtra("time");
        String lng = getIntent().getStringExtra("lng");
        String lat = getIntent().getStringExtra("lat");

        if(type == "LOST") {
            lostButton.setChecked(true);
        }
        if (type == "FOUND") {
            foundButton.setChecked(true);
        }

        EditText delName = (EditText) findViewById(R.id.itemNameText);
        EditText delContact = (EditText) findViewById(R.id.editTextPhone);
        EditText delDesc = (EditText) findViewById(R.id.descriptionText);
        EditText delTime = (EditText) findViewById(R.id.dateText);
        EditText delLng = (EditText) findViewById(R.id.lngText);
        EditText delLat = (EditText) findViewById(R.id.latText);

        delName.setText(itemName);
        delContact.setText(contact);
        delDesc.setText(description);
        delTime.setText(time);
        delLng.setText(lng);
        delLat.setText(lat);

        //BUTTONS
        backButton = (Button)findViewById(R.id.backButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        //DATABASE
        lostFoundItemDB = new LostFoundItemDB(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //All the exciting shit goes here
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteItem.this, MainActivity.class);
                startActivity(intent);
                //Saves NO DATA, just goes back to the main pages!
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteItem.this, MainActivity.class);

                String type = null;
                if(lostButton != null && foundButton == null) {
                    type = "LOST";
                }
                if(lostButton == null && foundButton != null) {
                    type = "FOUND";
                }
                if(lostButton == null && foundButton == null) {
                    Toast.makeText(DeleteItem.this, "Please select a type!", Toast.LENGTH_SHORT).show();
                }

                String name = delName.getText().toString();
                String details = delContact.getText().toString();
                String desc = delDesc.getText().toString();
                String when = delTime.getText().toString();
                String lng = delLng.getText().toString();
                String lat = delLat.getText().toString();

                LostFoundItem lostFoundItem = new LostFoundItem(id, type, name, details, desc, when, lng, lat);
                lostFoundItemDB.deleteTask(lostFoundItem);

                startActivity(intent);
            }
        });
    }
}