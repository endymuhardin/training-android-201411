package com.muhardin.endy.belajar.android.customer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class InputCustomer extends Activity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_customer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_input_customer, menu);
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

    public void tampilkanDatePicker(View v){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment dialogDatePicker = new DatePickerFragment(this);
        dialogDatePicker.show(ft, "date_dialog");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        TextView dtTanggalLahir = (TextView) findViewById(R.id.dtTanggalLahir);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");

        Calendar tanggalDipilih = new GregorianCalendar(year,monthOfYear,dayOfMonth);
        String output = formatter.format(tanggalDipilih.getTime());
        Log.v("InputCustomer", "Tanggal : " + output);
        dtTanggalLahir.setText(output);
    }
}
