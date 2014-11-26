package com.muhardin.endy.belajar.android.interprocess;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class KalkulatorIntentService extends IntentService {

    private static final String KALKULATOR_SERVICE_TAG = "KalkulatorService";


    public KalkulatorIntentService() {
        super("KalkulatorIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(KALKULATOR_SERVICE_TAG, "Menerima intent");


        if (intent != null) {
            String action = intent.getAction();
            Log.v(KALKULATOR_SERVICE_TAG, "Action : "+action);

            Integer num1 = intent.getIntExtra(KonstantaAplikasi.EXTRA_NUM1, 0);
            Integer num2 = intent.getIntExtra(KonstantaAplikasi.EXTRA_NUM2, 0);

            Log.v(KALKULATOR_SERVICE_TAG, "Num 1 : "+num1);
            Log.v(KALKULATOR_SERVICE_TAG, "Num 2 : "+num2);


            if(KonstantaAplikasi.ACTION_TAMBAH.equals(action)){
                Integer hasil = num1 + num2;

                ResultReceiver penampunganHasil = intent.getParcelableExtra(KonstantaAplikasi.EXTRA_HASIL);

                Log.d(KALKULATOR_SERVICE_TAG, "Menyimpan hasil di ResultReceiver");

                Bundle b = new Bundle();
                b.putInt(KonstantaAplikasi.EXTRA_HASIL, hasil);
                penampunganHasil.send(0, b);

                Log.d(KALKULATOR_SERVICE_TAG, "Menjalankan method send di ResultReceiver");
            }
        }
    }
}
