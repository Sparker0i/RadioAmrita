package in.ac.amrita.radioamrita.app;

import android.app.Application;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import in.ac.amrita.radioamrita.activity.MainActivity;
import in.ac.amrita.radioamrita.utils.Constants;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MC extends Application {
    MediaPlayer mediaPlayer = new MediaPlayer();
    boolean prepared = false;

    public void init() throws InterruptedException , ExecutionException , TimeoutException {
        new Task().execute(Constants.STREAM_URL).get(5 , TimeUnit.SECONDS);
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
                System.out.println("URL : " + strings[0]);
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
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            MC.this.startActivity(intent);
        }
    }
}