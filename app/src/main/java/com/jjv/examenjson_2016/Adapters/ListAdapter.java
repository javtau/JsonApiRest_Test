package com.jjv.examenjson_2016.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjv.examenjson_2016.Objects.Filmografia;
import com.jjv.examenjson_2016.R;

import java.util.ArrayList;

/**
 * Created by javi0 on 01/02/2017.
 */

public class ListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Filmografia> filmografia;

    public ListAdapter(Context context, ArrayList<Filmografia> filmografia) {
        this.context = context;
        this.filmografia = filmografia;
    }

    @Override
    public int getCount() {
        return filmografia.size();
    }

    @Override
    public Object getItem(int position) {
        return filmografia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.filmitem,null);
            holder = new ViewHolder();
            holder.lbl_nombre = (TextView) convertView.findViewById(R.id.lbl_nombre);
            holder.lbl_anyo = (TextView) convertView.findViewById(R.id.lbl_anyo);
            convertView.setTag(holder);

        } else {
            holder =(ViewHolder) convertView.getTag();
        }
        holder.lbl_anyo.setText(filmografia.get(position).getFecha());
        holder.lbl_nombre.setText(filmografia.get(position).getNombre());
        return convertView;
    }

    private class ViewHolder {
        TextView lbl_nombre;
        TextView lbl_anyo;
    }
}
