package com.dmt.sannguyen.lgwfb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BabyInfomation extends AppCompatActivity {
    EditText edtBabyName;
    RadioGroup radioGroupGioiTinh;
    RadioButton radioBtn;
    EditText edtHienThiNgaySinh;
    ImageButton imgCalendar;
    Button btnLuu, btnHuy, btnXoa;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_infomation);
    }
    private void AnhXa() {
        edtBabyName = findViewById(R.id.edtBabyName1);
        radioGroupGioiTinh = findViewById(R.id.radioGrouptGioiTinh1);
        edtHienThiNgaySinh = findViewById(R.id.edtHienThiNgaySinh1);
        imgCalendar = findViewById(R.id.imgButtonCalendar);
        btnLuu = findViewById(R.id.btnSaveEditBaby);
        btnHuy = findViewById(R.id.btnCancelEditBaby);
        btnXoa = findViewById(R.id.btnDeleteBaby);
    }
}
