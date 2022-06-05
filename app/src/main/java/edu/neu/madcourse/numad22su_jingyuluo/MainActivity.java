package edu.neu.madcourse.numad22su_jingyuluo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
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

        Button linkCollector = findViewById(R.id.LinkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,LinkCollector.class);

                startActivity(intent);
            }
        });

        Button Clicky = findViewById(R.id.Clicky_Clicky);

        Clicky.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivityClicky();
            }
        });

    }
    public void openActivityClicky(){
        Intent intent = new Intent(this,Clicky.class);
        startActivity(intent);
    }
}