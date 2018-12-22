package com.dmt.sannguyen.lgwfb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoTiemFragment extends Fragment {

    private String userID;

    public SoTiemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Receive the bundle data of user logged in from MainActivity
        Bundle bundle = getArguments();
        //Receive the ID of user logged in
        userID = bundle.getString("userid");
        Log.d("Track", "UserID at SoTiemChungFragment:" + userID);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_so_tiem, container, false);
    }

}
