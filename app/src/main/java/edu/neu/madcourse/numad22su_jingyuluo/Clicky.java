package edu.neu.madcourse.numad22su_jingyuluo;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Clicky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        Button A = findViewById(R.id.buttonA);
        A.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: A");
            }
        });

        Button B = findViewById(R.id.buttonB);
        B.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: B");
            }
        });

        Button C = findViewById(R.id.buttonC);
        C.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: C");
            }
        });

        Button D = findViewById(R.id.buttonD);
        D.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: D");
            }
        });

        Button E = findViewById(R.id.buttonE);
        E.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: E");
            }
        });

        Button F = findViewById(R.id.buttonF);
        F.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: F");
            }
        });
    }






}
