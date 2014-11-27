package com.muhardin.endy.belajar.android.rest.client;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class DaftarCustomerActivity extends Activity {

    private static final String LOGGER_TAG = "DaftarCustomerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_customer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daftar_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sync) {
            sinkronisasiData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sinkronisasiData() {
        Log.d(LOGGER_TAG, "Menjalankan sync");

        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "http://192.168.1.144:8080/customer-server/customer/list";

        JsonArrayRequest requestDataCustomer
                = new JsonArrayRequest(
                    url,
                    new CustomerResponseListener(),
                    new CustomerResponseErrorListener()
                );

        rq.add(requestDataCustomer);
    }

    private class CustomerResponseListener implements Response.Listener<JSONArray> {

        @Override
        public void onResponse(JSONArray response) {
            Log.v(LOGGER_TAG, "Response dari server : " + response.toString());
            Log.v(LOGGER_TAG, "Jumlah data : " + response.length() + " record");
        }
    }

    private class CustomerResponseErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError err) {
            Log.d(LOGGER_TAG, "Error request ke server : " + err.getMessage());
        }
    }
}
