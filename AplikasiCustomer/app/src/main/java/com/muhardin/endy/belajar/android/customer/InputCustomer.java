package com.muhardin.endy.belajar.android.customer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class InputCustomer extends Activity
        implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private String pilihanDomisili;
    private String pilihanJenisKelamin;
    private SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMMM yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_customer);
        Spinner spDomisili = (Spinner) findViewById(R.id.spDomisili);
        ArrayAdapter<CharSequence> adapter
                = ArrayAdapter.createFromResource(this, R.array.domisili, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDomisili.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_input_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.simpan_customer) {
            CustomerSqliteHelper dbCustomer
                    = new CustomerSqliteHelper(this);

            String nama = ((EditText)findViewById(R.id.txtNama)).getText().toString();
            String tanggalLahir = ((TextView)findViewById(R.id.dtTanggalLahir)).getText().toString();
            Log.v("InputCustomer", "Tanggal Lahir : " + tanggalLahir);
            String jenisKelamin = pilihanJenisKelamin != null ? pilihanJenisKelamin : "Tidak diisi";
            String domisili = pilihanDomisili != null ? pilihanDomisili : "Tidak diisi";
            String alamat = ((EditText)findViewById(R.id.txtAlamat)).getText().toString();

            ContentValues dataCustomer = new ContentValues();
            dataCustomer.put("nama", nama);
            try {
                dataCustomer.put("tgl_lahir", formatter.parse(tanggalLahir).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dataCustomer.put("jenis_kelamin", jenisKelamin);
            dataCustomer.put("domisili", domisili);
            dataCustomer.put("alamat", alamat);

            SQLiteDatabase db = dbCustomer.getWritableDatabase();
            Long newId = db.insert("customer", null, dataCustomer);
            Log.v("InputCustomer", "Insert record baru dengan ID " + newId);
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

        Calendar tanggalDipilih = new GregorianCalendar(year,monthOfYear,dayOfMonth);
        String output = formatter.format(tanggalDipilih.getTime());
        Log.v("InputCustomer", "Tanggal : " + output);
        dtTanggalLahir.setText(output);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pilihanDomisili = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void rgJenisKelaminClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbPria:
                if (checked)
                    pilihanJenisKelamin = "P";
                    break;
            case R.id.rbWanita:
                if (checked)
                    pilihanJenisKelamin = "W";
                    break;
        }
    }
}
