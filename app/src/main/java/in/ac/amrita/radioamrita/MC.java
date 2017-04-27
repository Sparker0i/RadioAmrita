package in.ac.amrita.radioamrita;

import android.app.Application;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by root on 27/4/17.
 */

public class MC extends Application {
    MediaPlayer mediaPlayer = new MediaPlayer();
    boolean prepared = false;
    private final static String stream = "http://192.168.0.160:8000/;";

    public void init(){

     new Task().execute(stream);

    }

    public void start()
    {
        mediaPlayer.start();
    }

    public void pause()
    {
        mediaPlayer.pause();
    }

    public void audiostream()
    {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }



    class Task extends AsyncTask<String, Void, Boolean> {

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
            Intent intent = new Intent(MC.this, MainActivity.class);
            MC.this.startActivity(intent);

        }
    }
}