package in.ac.amrita.radioamrita.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import in.ac.amrita.radioamrita.app.MC;
import in.ac.amrita.radioamrita.R;

public class MainActivity extends AppCompatActivity {

    Button play;
    boolean started = false;
    Chronometer mChronometer;
    long mLastStopTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        final MC global = (MC) getApplicationContext();
        play = (Button) findViewById(R.id.play);
        global.start();
        chronoStart();
        started = true;
        play.setText(getString(R.string.pause));
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (started) {
                    global.pause();
                    started = false;
                    chronoPause();
                    play.setText(getString(R.string.play));
                } else {
                    global.start();
                    chronoStart();
                    started = true;
                    play.setText(getString(R.string.pause));
                }
            }
        });
    }

    private void chronoStart()
    {
        // on first start
        if ( mLastStopTime == 0 )
            mChronometer.setBase( SystemClock.elapsedRealtime() );
        // on resume after pause
        else
        {
            long intervalOnPause = (SystemClock.elapsedRealtime() - mLastStopTime);
            mChronometer.setBase( mChronometer.getBase() + intervalOnPause );
        }
        mChronometer.start();
    }

    private void chronoPause()
    {
        mChronometer.stop();
        mLastStopTime = SystemClock.elapsedRealtime();
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}





