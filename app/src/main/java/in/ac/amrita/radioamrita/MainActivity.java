package in.ac.amrita.radioamrita;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import in.ac.amrita.radioamrita.utils.Constants;

public class MainActivity extends AppCompatActivity {

    Button play;
    MediaPlayer mediaPlayer;
    boolean started = false;
    boolean prepared = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        play.setEnabled(false);
        play.setText(getString(R.string.loading));
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (started) {
                    mediaPlayer.pause();
                    started = false;
                    play.setText(getString(R.string.play));
                } else {
                    mediaPlayer.start();
                    started = true;
                    play.setText(getString(R.string.pause));
                }

            }
        });

        new PlayTask().execute(Constants.STREAM_URL);
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

    private class PlayTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            play.setEnabled(true);
            play.setText(getString(R.string.play));
        }
    }
}