package com.dmt.sannguyen.lgwfb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TramXaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<TramXa> tramXaArrayList;

    public TramXaAdapter(Context context, int layout, ArrayList<TramXa> tramXaArrayList) {
        this.context = context;
        this.layout = layout;
        this.tramXaArrayList = tramXaArrayList;
    }

    @Override
    public int getCount() {
        return tramXaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView txtTenTramXa;
        TextView txtDuongVaPhuong;
        TextView txtQuan;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTenTramXa = convertView.findViewById(R.id.TxtTenTramXa);
            holder.txtDuongVaPhuong = convertView.findViewById(R.id.TxtDuongVaPhuong);
            holder.txtQuan = convertView.findViewById(R.id.TxtQuan);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        TramXa tramXa = tramXaArrayList.get(position);

        holder.txtTenTramXa.setText(tramXa.getTen());
        holder.txtDuongVaPhuong.setText("Đường: " + tramXa.getStreet() + "-" + tramXa.getWard());
        holder.txtQuan.setText("Quận: " + tramXa.getDistrict());

        return convertView;
    }
}
