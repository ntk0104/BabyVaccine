package com.dmt.sannguyen.lgwfb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SoTiemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MuiTiem> muiTiemArrayList;

    public SoTiemAdapter(Context context, int layout, ArrayList<MuiTiem> muiTiemArrayList) {
        this.context = context;
        this.layout = layout;
        this.muiTiemArrayList = muiTiemArrayList;
    }

    @Override
    public int getCount() {
        return muiTiemArrayList.size();
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
        TextView txtTenMuiTiem;
        TextView txtThoiGianTiem;
        TextView txtThoiGianHenGio;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTenMuiTiem = convertView.findViewById(R.id.TxtTenMuiTiem);
            holder.txtThoiGianTiem = convertView.findViewById(R.id.TxtThoiGianTiem);
            holder.txtThoiGianHenGio = convertView.findViewById(R.id.TxtHenGio);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        MuiTiem muiTiem = muiTiemArrayList.get(position);
        holder.txtTenMuiTiem.setText(muiTiem.getMuiTiemName());
        holder.txtThoiGianTiem.setText("" + muiTiem.getThoiGianDuKien());
        holder.txtThoiGianHenGio.setText("" + muiTiem.getThoiGianHenGio());

        return convertView;
    }
}
