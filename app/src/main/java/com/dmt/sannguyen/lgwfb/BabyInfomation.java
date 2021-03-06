package com.dmt.sannguyen.lgwfb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BabyInfomation extends AppCompatActivity {
    EditText edtBabyName;
    RadioGroup radioGroupGioiTinh;
    RadioButton radioBtnNam;
    RadioButton radioBtnNu;
    EditText edtHienThiNgaySinh;
    ImageButton imgCalendar;
    Button btnLuu, btnHuy, btnXoa;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_infomation);

        AnhXa();

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        userID = bundle.getString("userid");
        final int babyid = bundle.getInt("babyid");
        String babyName = bundle.getString("tenbaby");
        String gioitinhBaby = bundle.getString("gioitinh");
        String ngaysinhBaby = bundle.getString("ngaysinh");

        Log.d("Track", "userid " + userID);
        Log.d("Track", "BaybyID: " + babyid);
        Log.d("Track", "BaybyName: " + babyName);
        Log.d("Track", "Gioi tinh BABY:  " + gioitinhBaby);
        Log.d("Track", "Ngay sinh: " + ngaysinhBaby);

        edtBabyName.setText(babyName);
        if (gioitinhBaby.equals("true")){
            Log.d("Track", "la nu");
            radioBtnNam.setSelected(false);
            radioBtnNu.setSelected(true);
        }else {
            Log.d("Track", "la nam");
            radioBtnNam.isSelected();
        }
        ngaysinhBaby = ngaysinhBaby.substring(0, 10);
        edtHienThiNgaySinh.setText(ngaysinhBaby);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //please add loading
                XoaBaBy(babyid);

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void XoaBaBy(final int babyid){
        String url = "https://babyvacxin.azurewebsites.net/api/XoaBaBy?code=vnoIPD5dTAMK0y70l1ty6vJPcgqQrEngR/D3ZlA3rOMass22KJ6ilQ==";
        Log.d("Track", "Registering new user");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from XoaBaBy :" + response);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("userid", userID);
                        Intent intent1 = new Intent(BabyInfomation.this, MainActivity.class);
                        Log.d("Track", "Before go to Main in XoaBaby: userid = " + userID);
                        intent1.putExtras(bundle1);
                        startActivity(intent1);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from XoaBaBy :"+error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("babyid", String.valueOf(babyid));
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


    private void AnhXa() {
        edtBabyName = findViewById(R.id.edtBabyName1);
        radioGroupGioiTinh = findViewById(R.id.radioGrouptGioiTinh1);
        edtHienThiNgaySinh = findViewById(R.id.edtHienThiNgaySinh1);
        imgCalendar = findViewById(R.id.imgButtonCalendar);
        btnLuu = findViewById(R.id.btnSaveEditBaby);
        btnHuy = findViewById(R.id.btnCancelEditBaby);
        btnXoa = findViewById(R.id.btnDeleteBaby);
        radioBtnNam = findViewById(R.id.ratioGioiTinhNam);
        radioBtnNu = findViewById(R.id.ratioGioiTinhNu);
    }
}
