package com.example.abconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

         Button btb;
         Button dtb;
        Button otb;
        Button htb;

        btb = (Button) findViewById(R.id.button2);
        dtb = (Button) findViewById(R.id.button3);
        otb = (Button) findViewById(R.id.button4);
        htb = (Button) findViewById(R.id.button5);

        btb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binary_to_base();
            }
        });
        dtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decimal_to_base();
            }
        });
        otb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                octal_to_base();
            }
        });
        htb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hexadecimal_to_base();
            }
        });


    }
    public void binary_to_base(){
        Intent intent=new Intent(this, binary_to_anybase.class);
        startActivity(intent);
    }
    public void decimal_to_base(){
        Intent intent=new Intent(this, decimal_to_anybase.class);
        startActivity(intent);
    }
    public void octal_to_base(){
        Intent intent=new Intent(this, octal_to_anybase.class);
        startActivity(intent);
    }
    public void hexadecimal_to_base(){
        Intent intent=new Intent(this, hexab.class);
        startActivity(intent);
    }
}