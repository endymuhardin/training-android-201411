package com.muhardin.endy.belajar.android.rest.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by endy on 27/11/14.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.customer_view, parent, false);
        }

        // ambil data untuk posisi row
        Customer c = getItem(position);

        TextView txtNamaCustomer = (TextView) convertView.findViewById(R.id.txtNamaCustomer);
        txtNamaCustomer.setText(c.getNama());

        TextView txtAlamat = (TextView) convertView.findViewById(R.id.txtAlamat);
        txtAlamat.setText(c.getAlamat());

        return convertView;
    }

    public CustomerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CustomerAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public CustomerAdapter(Context context, int resource, Customer[] objects) {
        super(context, resource, objects);
    }

    public CustomerAdapter(Context context, int resource, int textViewResourceId, Customer[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public CustomerAdapter(Context context, int resource, List<Customer> objects) {
        super(context, resource, objects);
    }

    public CustomerAdapter(Context context, int resource, int textViewResourceId, List<Customer> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
