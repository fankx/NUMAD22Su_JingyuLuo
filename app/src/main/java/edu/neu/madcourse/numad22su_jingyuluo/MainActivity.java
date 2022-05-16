package edu.neu.madcourse.numad22su_jingyuluo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button_Panel = findViewById(R.id.buttonPanel);

        button_Panel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Jingyu Luo - luo.jingy@northeastern.edu",Toast.LENGTH_SHORT).show();
            }
        });

    }
}