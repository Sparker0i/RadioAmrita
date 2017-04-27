package in.ac.amrita.radioamrita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Button play;
    Chronometer chronometer;
    boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MC global = (MC) getApplicationContext();
        play = (Button) findViewById(R.id.play);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (started) {
                    global.pause();
                    chronometer.stop();
                    started = false;
                    play.setText(getString(R.string.play));
                } else {
                    global.start();
                    chronometer.start();
                    started = true;
                    play.setText(getString(R.string.pause));
                }

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
       /* if(started)
            mediaPlayer.pause();*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if(started)
            mediaPlayer.start();*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




    }





