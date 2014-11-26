package com.muhardin.endy.belajar.android.interprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class KalkulatorActivity extends Activity {

    private static final String KALKULATOR_ACTIVITY_TAG = "KalkulatorActivity";

    private class UpdateHasil implements Runnable {

        private Integer hasil;

        private UpdateHasil(Integer hasil) {
            this.hasil = hasil;
        }

        @Override
        public void run() {
            Log.d(KALKULATOR_ACTIVITY_TAG, "Ingin menampilkan hasil di EditText");
            EditText txtHasil = (EditText) findViewById(R.id.txtHasil);
            txtHasil.setText(hasil.toString());
            Log.d(KALKULATOR_ACTIVITY_TAG, "Hasil tampil di EditText");
        }
    }

    private class PenampunganHasil extends ResultReceiver{

        public PenampunganHasil(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            Log.d(KALKULATOR_ACTIVITY_TAG, "Menerima hasil dari ResultReceiver");

            Integer hasil = resultData.getInt(KonstantaAplikasi.EXTRA_HASIL);
            Log.v(KALKULATOR_ACTIVITY_TAG, "Hasil dari ResultReceiver : "+hasil);

            runOnUiThread(new UpdateHasil(hasil));
        }
    }

    public void btnTambahClicked(View v){
        EditText txtNum1 = (EditText) findViewById(R.id.num1);
        Integer num1 = Integer.valueOf(txtNum1.getText().toString());


        EditText txtNum2 = (EditText) findViewById(R.id.num2);
        Integer num2 = Integer.valueOf(txtNum2.getText().toString());

        Log.v(KALKULATOR_ACTIVITY_TAG, "Num 1 : "+num1);
        Log.v(KALKULATOR_ACTIVITY_TAG, "Num 2 : "+num2);

        Intent i = new Intent(this, KalkulatorIntentService.class);
        i.setAction(KonstantaAplikasi.ACTION_TAMBAH);
        i.putExtra(KonstantaAplikasi.EXTRA_NUM1, num1);
        i.putExtra(KonstantaAplikasi.EXTRA_NUM2, num2);

        PenampunganHasil p = new PenampunganHasil(null);
        i.putExtra(KonstantaAplikasi.EXTRA_HASIL, p);

        Log.d(KALKULATOR_ACTIVITY_TAG, "Mengirim intent ke KalkulatorIntentService");

        startService(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kalkulator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
