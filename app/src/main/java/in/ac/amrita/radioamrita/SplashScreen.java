package in.ac.amrita.radioamrita;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final MC global = (MC) getApplicationContext();
        final Context context = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    global.init();
                }
                catch (TimeoutException tex) {
                    new MaterialDialog.Builder(context)
                            .title("Check Internet Connection")
                            .content("Please check whether you are connected to AMRITA Networks or not, and then restart the app")
                            .positiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                    System.exit(0);
                                }
                            })
                            .show();
                }
                catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        } , 500);
    }
}

