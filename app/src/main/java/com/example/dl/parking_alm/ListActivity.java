package com.example.dl.parking_alm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dl.parking_alm.jsonTools.ParkingBuilder;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String p1= new ParkingBuilder().getParkings().toString();

        TextView text= (TextView) findViewById(R.id.text);
        text.setText(p1);


    }
}
