package edu.neu.madcourse.numad22su_jingyuluo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button_Panel = findViewById(R.id.buttonPanel);

        button_Panel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AboutMe.class);

                startActivity(intent);
            }
        });

        Button linkCollector = findViewById(R.id.btn_linkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,LinkCollector.class);

                startActivity(intent);
            }
        });

        Button Clicky = findViewById(R.id.btn_clicky);
        Clicky.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivityClicky();
            }
        });

        Button Prime = findViewById(R.id.btn_prime);
        Prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrimePage.class);
                startActivity(intent);
            }
        });

        Button Locator = findViewById(R.id.btn_locator);
        Locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocatorPage.class);
                startActivity(intent);
            }
        });

    }
    public void openActivityClicky(){
        Intent intent = new Intent(this,Clicky.class);
        startActivity(intent);
    }
}