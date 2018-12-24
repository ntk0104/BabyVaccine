package com.dmt.sannguyen.lgwfb;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HenGio extends AppCompatActivity {
    EditText edTenMuiTiem, edNgayDuKien, edNgayHen,edGioHen,edGhiChu;
    CheckBox checkDaTiem;
    Button btHuy,btLuu;
    ImageButton imgNgayHen,imgGioHen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hen_gio);
        anhxa();

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        final String userID = bundle.getString("userid");
        final int STTMuiTiem = bundle.getInt("STTMuiTiem");
        int BabyID = bundle.getInt("BabyID");
        int MuiTiemID = bundle.getInt("MuiTiemID");
        String ThoiGianDuKien = bundle.getString("ThoiGianDuKien");
        String ThoiGianTiem = bundle.getString("ThoiGianTiem");
        String ThoiGianHenGio = bundle.getString("ThoiGianHenGio");
        String GhiChu = bundle.getString("GhiChu");
        String BabyName = bundle.getString("BabyName");
        final String MuiTiemName = bundle.getString("MuiTiemName");
        String StatusMuiTiem = bundle.getString("StatusMuiTiem");

        Log.d("Track", "Got info of MuiTiem");
        Log.d("Track", "userID : " + userID);
        Log.d("Track", "STTMuiTiem : " + STTMuiTiem);
        Log.d("Track", "BabyID : " + BabyID);
        Log.d("Track", "MuiTiemID : " + MuiTiemID);
        Log.d("Track", "ThoiGianDuKien : " + ThoiGianDuKien);
        Log.d("Track", "ThoiGianTiem : " + ThoiGianTiem);
        Log.d("Track", "ThoiGianHenGio : " + ThoiGianHenGio);
        Log.d("Track", "GhiChu : " + GhiChu);
        Log.d("Track", "BabyName : " + BabyName);
        Log.d("Track", "MuiTiemName : " + MuiTiemName);
        Log.d("Track", "StatusMuiTiem : " + StatusMuiTiem);

        edTenMuiTiem.setText(MuiTiemName);
        String[] temp = ThoiGianDuKien.split(":");
        final String ThoiGianDuKien_value = temp[1];
        edNgayDuKien.setText(ThoiGianDuKien_value);
        edNgayHen.setText(ThoiGianHenGio);
        edGioHen.setText(ThoiGianHenGio);
        if(StatusMuiTiem.equals("false")){
                checkDaTiem.setChecked(false);
        }else {
            checkDaTiem.setChecked(true);
        }
        edGhiChu.setText(GhiChu);

        imgNgayHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HenGio.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edNgayHen.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        imgGioHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(calendar.HOUR_OF_DAY);
                int minute = calendar.get(calendar.MINUTE);
                TimePickerDialog timepickerdialog = new TimePickerDialog(HenGio.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(0, 0, 0, hourOfDay,minute, 0);
                        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
                        edGioHen.setText(timeformat.format(calendar.getTime()));
                    }
                }, hour, minute, true);

                timepickerdialog.show();
            }
        });

        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Track", "Before Update MuiTiem");
                Log.d("Track","STTMuiTiem : " + STTMuiTiem);
                Log.d("Track","MuiTiemName : " + MuiTiemName);
                Log.d("Track","NgayDuKien : " + ThoiGianDuKien_value);
                Log.d("Track","NgayHen : " + edNgayHen.getText());
                Log.d("Track","GioHen : " + edGioHen.getText());

                int checkDaTiem_value;
                if(checkDaTiem.isChecked()){
//                    Toast.makeText(HenGio.this, "Chkb selected", Toast.LENGTH_LONG).show();
//                    Log.d("Track","Chkb selected : ");
                    checkDaTiem_value = 1;
                }else {
//                    Toast.makeText(HenGio.this, "Chkb unselected", Toast.LENGTH_LONG).show();
                    checkDaTiem_value = 0;
//                    Log.d("Track","Chkb unselected : ");
                }
                Log.d("Track","CheckDaTiem : " + String.valueOf(checkDaTiem_value));
                Log.d("Track","GhiChu : " + edGhiChu.getText());



                String thoigianhengio = edNgayHen.getText() + " " + edGioHen.getText();

                String thoigianhengio_value = "";
                if(thoigianhengio.length() < 20){
                    thoigianhengio_value = thoigianhengio.toString();
                }


                Log.d("Track", "thoigianhengio_value = " + thoigianhengio_value);
                String ghichu = edGhiChu.getText().toString();
                int statusmuitiem = checkDaTiem_value;
                int sttmuitiem = STTMuiTiem;

                UpdateMuiTiem(ghichu, statusmuitiem, sttmuitiem);
                Bundle bundle = new Bundle();
                bundle.putString("userid", userID);
                //send bundle data to BeYeuActivity activity
                Intent intent1 = new Intent(HenGio.this, MainActivity.class);
                intent1.putExtras(bundle);
                startActivity(intent1);

            }
        });


    }
    public void anhxa()
    {
        edTenMuiTiem=(EditText)findViewById(R.id.headerTenMuiTiem);
        edNgayDuKien=(EditText)findViewById(R.id.edtNgayDuKien);
        edNgayHen=(EditText)findViewById(R.id.edtNgayHen);
        edGioHen=(EditText)findViewById(R.id.edtGioHen);
        edGhiChu=(EditText)findViewById(R.id.edtGhiChu);

        checkDaTiem=(CheckBox)findViewById(R.id.chkbDaTiem);

        imgGioHen=findViewById(R.id.btnGioHen);
        imgNgayHen=findViewById(R.id.btnNgayHen);

        btHuy=findViewById(R.id.btnHuy);
        btLuu=findViewById(R.id.btnLuu);


    }

    private void UpdateMuiTiem(final String ghichu, final int statusmuitiem, final int sttmuitiem){
        String url = "https://babyvacxin.azurewebsites.net/api/UpdateMuiTiemInfo?code=9jHI6jxNtnT2bfVnxu3lPnZQQQazKOJqX38WxHIjq0zJhr6fdyIlIQ==";
        Log.d("Track", "UpdateMuiTiem");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from RegisterNewUser :" + response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from RegisterNewUser :"+error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("ghichu", ghichu);
                params.put("statusmuitiem", String.valueOf(statusmuitiem));
                params.put("sttmuitiem", String.valueOf(sttmuitiem));
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
}
