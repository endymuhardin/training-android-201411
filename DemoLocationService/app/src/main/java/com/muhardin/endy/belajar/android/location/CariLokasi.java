package com.muhardin.endy.belajar.android.location;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class CariLokasi extends Activity implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    private LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_lokasi);

        locationClient = new LocationClient(this, this, this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        locationClient.disconnect();
        super.onStop();
    }

    public void updatePosisi(View v){
        locationClient.connect();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cari_lokasi, menu);
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

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("CariLokasi", "GPS Connected");
        Location posisi = locationClient.getLastLocation();
        Double lat = posisi.getLatitude();
        Double lon = posisi.getLongitude();

        EditText txtKoordinat = (EditText) findViewById(R.id.txtKoordinat);
        txtKoordinat.setText(lat + "," + lon);

        CariAlamatTask task = new CariAlamatTask(this);
        task.execute(posisi);
    }

    private class CariAlamatTask extends AsyncTask<Location, Void, String>{
        Context ctx;

        private CariAlamatTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(Location... params) {
            Location loc = params[0];

            Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
            try {
                List<Address> hasil = geocoder.getFromLocation(loc.getLatitude(), loc.getLatitude(), 1);
                Address alamat = hasil.get(0);
                return alamat.toString();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            EditText alamat = (EditText) findViewById(R.id.txtAlamat);
            alamat.setText(s);
        }
    }

    @Override
    public void onDisconnected() {
        Log.d("CariLokasi", "GPS DisConnected");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("CariLokasi", "GPS Gagal Connect : "+ connectionResult.toString());

    }
}
