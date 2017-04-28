package in.ac.amrita.radioamrita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MC global = (MC) getApplicationContext();
        try {
            global.init();
        }
        catch (InterruptedException | ExecutionException | TimeoutException ex) {

        }
    }
}

