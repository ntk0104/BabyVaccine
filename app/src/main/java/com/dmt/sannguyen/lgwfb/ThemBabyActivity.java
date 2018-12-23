package com.dmt.sannguyen.lgwfb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThemBabyActivity extends AppCompatActivity {

    EditText edtBabyName;
    RadioGroup radioGroupGioiTinh;
    RadioButton radioBtn;
    EditText edtHienThiNgaySinh;
    ImageButton imgCalendar;
    Button btnThem, btnHuy;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_baby);
        final Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        Log.d("Track", "Add baby got: " + userid);
        AnhXa();

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemBabyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtHienThiNgaySinh.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baby_name = edtBabyName.getText().toString().trim();
                int selectedGT = radioGroupGioiTinh.getCheckedRadioButtonId();
                radioBtn = findViewById(selectedGT);
                String gioitinh = (String) radioBtn.getText();
                String ngaysinh = edtHienThiNgaySinh.getText().toString().trim();
                int gender;
                if(gioitinh.equals("Nam")){
                    Log.d("Track", "Adding new baby with gioi tinh..."+ gioitinh);
                    gender = 1;
                }else{
                    Log.d("Track", "Adding new baby with gioi tinh..."+ gioitinh);
                    gender = 0;
                }
                if(baby_name.length() > 0 && ngaysinh.length() > 0 ){
                    Log.d("Track", "Adding new baby with data..." + baby_name + " " + gender + " " + ngaysinh);
                    AddNewBaby(baby_name, ngaysinh, gender, userid);
                    Bundle bundle = new Bundle();
                    bundle.putString("userid", userid);
                    //send bundle data to BeYeuActivity activity
                    Intent intent1 = new Intent(ThemBabyActivity.this, MainActivity.class);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                }else {
                    Toast.makeText(ThemBabyActivity.this, "thieu data", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        edtBabyName = findViewById(R.id.edtBabyName);
        radioGroupGioiTinh = findViewById(R.id.radioGrouptGioiTinh);
        edtHienThiNgaySinh = findViewById(R.id.edtHienThiNgaySinh);
        imgCalendar = findViewById(R.id.imgButtonCalendar);
        btnThem = findViewById(R.id.btnThemBaby);
        btnHuy = findViewById(R.id.btnCancelAddBaby);
    }

    private void AddNewBaby(final String name, final String dateofbirth, final int gender, final String userid){
        String url = "https://babyvacxin.azurewebsites.net/api/AddBaby?code=Z791vFkebPlUlUy55cJzNsx6py6SWLs5Ay8ncnd7M8wrp9I0gbngfw==";
        Log.d("Track", "Registering new user");
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
                params.put("name", name);
                params.put("dateofbirth", dateofbirth);
                params.put("gender", String.valueOf(gender));
                params.put("userid", userid);
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

}
