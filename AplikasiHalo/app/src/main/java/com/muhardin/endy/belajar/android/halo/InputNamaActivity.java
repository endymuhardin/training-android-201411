package com.muhardin.endy.belajar.android.halo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class InputNamaActivity extends Activity {

    private static final String LOG_TAG = "AplikasiHalo";

    public void btnKirimClicked(View v){
        EditText txtNama = (EditText) findViewById(R.id.txtNama);
        String nama = txtNama.getText().toString();

        Log.v(LOG_TAG, "Nama : "+nama);

        Intent intentHalo = new Intent(this, HaloActivity.class);
        intentHalo.putExtra("x", nama);

        startActivity(intentHalo);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nama);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_nama, menu);
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
