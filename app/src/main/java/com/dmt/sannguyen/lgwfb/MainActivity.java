package com.dmt.sannguyen.lgwfb;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private BeYeuFragment beYeuFragment;
    private SoTiemFragment soTiemFragment;
    private DiemTiemChungFragment diemTiemChungFragment;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading();
        bottomNavigationView = findViewById(R.id.main_nav);
        frameLayout = findViewById(R.id.main_frame);
        beYeuFragment = new BeYeuFragment();
        soTiemFragment = new SoTiemFragment();
        diemTiemChungFragment = new DiemTiemChungFragment();
        //Receive Bundle data of user logged in
        Bundle bundle = this.getIntent().getExtras();
        // Receive the ID of user logged in
        userID = bundle.getString("userid");
        Log.d("Track", "UserID at mainActivity:" + userID);

        setFragment(beYeuFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_beyeu :
                        loading();
                        setFragment(beYeuFragment);
                        return true;

                    case R.id.nav_sotiem :
                        loading();
                        setFragment(soTiemFragment);
                        return true;

                    case R.id.nav_tramtiemchung :
                        loading();
                        setFragment(diemTiemChungFragment);
                        return true;

                    default:
                        return false;

                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("userid", userID);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
    private void loading()
    {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);
    }
}
