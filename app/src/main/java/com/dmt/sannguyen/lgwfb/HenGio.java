package com.dmt.sannguyen.lgwfb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

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
}
