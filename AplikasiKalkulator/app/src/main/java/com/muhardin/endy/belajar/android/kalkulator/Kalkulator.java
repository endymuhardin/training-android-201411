package com.muhardin.endy.belajar.android.kalkulator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.muhardin.endy.belajar.android.kalkulator.R;

public class Kalkulator extends Activity {

    public void btnTambahDiklik(View v){
        EditText txtNum1 = (EditText) findViewById(R.id.num1);
        String strNum1 = txtNum1.getText().toString();
        Integer num1 = Integer.parseInt(strNum1);

        EditText txtNum2 = (EditText) findViewById(R.id.num2);
        String strNum2 = txtNum2.getText().toString();
        Integer num2 = Integer.parseInt(strNum2);

        Integer hasil = num1 + num2;

        EditText txtHasil = (EditText) findViewById(R.id.hasil);
        txtHasil.setText(String.valueOf(hasil), TextView.BufferType.EDITABLE);

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
