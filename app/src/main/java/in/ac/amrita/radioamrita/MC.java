package in.ac.amrita.radioamrita;

import android.app.Application;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;

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

    private class Task extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                prepared = true;
            }
            catch (IOException e) {
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