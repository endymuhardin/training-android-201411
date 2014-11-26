package com.muhardin.endy.belajar.android.customer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener
        handlerKetikaTanggalDipilih;

    public DatePickerFragment() {
    }

    public DatePickerFragment(DatePickerDialog.OnDateSetListener handlerKetikaTanggalDipilih) {
        this.handlerKetikaTanggalDipilih = handlerKetikaTanggalDipilih;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        Integer tahun = c.get(Calendar.YEAR);
        Integer bulan = c.get(Calendar.MONTH);
        Integer tanggal = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),
                handlerKetikaTanggalDipilih,
                tahun,
                bulan,
                tanggal);
    }
}
