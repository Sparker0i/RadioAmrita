package in.ac.amrita.radioamrita.activity;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import in.ac.amrita.radioamrita.app.MC;
import in.ac.amrita.radioamrita.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final MC global = (MC) getApplicationContext();
        final Context context = this;
        HandlerThread handlerThread = new HandlerThread("background-handler");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();

        new Handler(looper).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    global.init();
                } catch (TimeoutException tex) {
                    new MaterialDialog.Builder(context)
                            .title(getString(R.string.check_connection))
                            .content(getString(R.string.check_connection_content))
                            .positiveText(getString(R.string.ok))
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                    System.exit(0);
                                }
                            })
                            .show();
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        } , 1000);
    }
}

