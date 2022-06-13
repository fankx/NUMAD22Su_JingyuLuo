package edu.neu.madcourse.numad22su_jingyuluo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PrimePage extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final Handler textHandler = new Handler();
    TextView curNum;
    TextView primeNumbers;
    Thread thread;
    boolean flag;
    int num;
    int curPrime = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_page);
        curNum = findViewById(R.id.tv_curNum);
        primeNumbers = findViewById(R.id.tv_primeNumber);
    }

    public void startFindPrime(View view) {
        flag = true;
        num = 2;
        RunnableThread runnableThread = new RunnableThread();
        thread = new Thread(runnableThread);
        thread.start();
    }

    public void stopFindPrime(View view) {
        flag = false;
    }

    public class RunnableThread implements Runnable {

        @Override
        public void run() {
            while (flag) {
                if (checkPrime(num)) {
                    curPrime = num;
                }
                //The handler changes the TextView running in the UI thread.
                int finalCurPrime = curPrime;
                final int finalI = num;
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        curNum.setText("The current search number is: " + finalI);
                        primeNumbers.setText("The latest prime number is " + finalCurPrime);
                    }
                });
                Log.d(TAG, "Running on a different thread using Runnable Interface: " + num);
                try {
                    Thread.sleep(500); //Makes the thread sleep or be inactive for 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                num++;
            }
        }

        public boolean checkPrime(int num) {
            if (num == 1) return false;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
