package com.dmt.sannguyen.lgwfb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BeYeuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<BeYeu> beYeuArrayList;

    public BeYeuAdapter(Context context, int layout, ArrayList<BeYeu> beYeuArrayList) {
        this.context = context;
        this.layout = layout;
        this.beYeuArrayList = beYeuArrayList;
    }

    @Override
    public int getCount() {
        return beYeuArrayList.size();
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
        ImageView baby_avatar;
        TextView baby_Name;
        TextView baby_birthday;
        TextView baby_age;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.baby_avatar = convertView.findViewById(R.id.ImgBabyAvatar);
            holder.baby_Name = convertView.findViewById(R.id.TxtBabyName);
            holder.baby_birthday = convertView.findViewById(R.id.TxtBabyBirthday);
            holder.baby_age = convertView.findViewById(R.id.TxtBabyAge);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        BeYeu beYeu = beYeuArrayList.get(position);

        holder.baby_avatar.setImageResource(R.drawable.baby_avatar);
        holder.baby_Name.setText("Bé: " + beYeu.getName());
        String ngaysinh = beYeu.getDateOfBirth();
        // Cat ngay, thang, nam
        String year = ngaysinh.substring(0, 4);
        String month = ngaysinh.substring(5, 7);
        String date = ngaysinh.substring(8, 10);
        // Set ngay, thang, nam
        holder.baby_birthday.setText("Ngày sinh: " + date +"/" + month + "/" + year);
        holder.baby_age.setText("Tuổi: " + beYeu.getAge_Display());

        return convertView;
    }
}
