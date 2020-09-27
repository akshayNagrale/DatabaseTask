package com.andro.databasetask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,city;
    Button add;
    private ArrayList<DataObject> arrayList = new ArrayList<DataObject>();

    private  DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        name =  findViewById(R.id.name);
        city =  findViewById(R.id.city);

        add =  findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sData();
            }
        });
    }
    private void sData() {
        DataObject data = new DataObject();
        data.setName(name.getText().toString());
        data.setCity(city.getText().toString());

        databaseHelper.insert(name.getText().toString(),city.getText().toString());
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

}

