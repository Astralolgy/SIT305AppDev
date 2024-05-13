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

import java.util.UUID;

public class AddItem extends AppCompatActivity {

    RadioButton lostButton, foundButton;
    EditText itemName, contact, itemDesc, itemWhen, itemWhere;
    Button backButton;
    Button saveButton;
    LostFoundItemDB lostFoundItemDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);

        //DATA
        lostButton = (RadioButton)findViewById(R.id.radioButtonLost);
        foundButton = (RadioButton)findViewById(R.id.radioButtonFound);

        itemName = findViewById(R.id.itemNameText);
        contact = findViewById(R.id.editTextPhone);
        itemDesc = findViewById(R.id.descriptionText);
        itemWhen = findViewById(R.id.dateText);
        itemWhere = findViewById(R.id.locationText);

        //BUTTONS
        backButton = (Button)findViewById(R.id.backButton);
        saveButton = (Button)findViewById(R.id.saveButton);

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
                Intent intent = new Intent(AddItem.this, MainActivity.class);
                startActivity(intent);
                //Saves NO DATA, just goes back to the main pages!
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItem.this, MainActivity.class);

                String type = null;
                if(lostButton.isChecked()) {
                    type = "LOST";
                }

                if(foundButton.isChecked()) {
                    type = "FOUND";
                }
                if(lostButton == null && foundButton == null) {
                    Toast.makeText(AddItem.this, "Please select one type!", Toast.LENGTH_SHORT).show();
                }

                String name = itemName.getText().toString();
                String details = contact.getText().toString();
                String desc = itemDesc.getText().toString();
                String when = itemWhen.getText().toString();
                String where = itemWhere.getText().toString();

                Log.i("DES", desc);

                if (name.isEmpty() && details.isEmpty() && desc.isEmpty() && when.isEmpty() && where.isEmpty())
                {
                    Toast.makeText(AddItem.this, "Please enter all the information", Toast.LENGTH_SHORT).show();
                    return;
                }
                UUID uuid = UUID.randomUUID();
                String randomID = uuid.toString();

                LostFoundItem lostFoundItem = new LostFoundItem(randomID, type, name, details, desc, when, where);
                lostFoundItemDB.addItem(lostFoundItem);

                startActivity(intent);
            }
        });
    }
}