package com.dmt.sannguyen.lgwfb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoTiemFragment extends Fragment {

    private String userID;

    Spinner Babyspinner;
    Button btnDaTiem, btnQuaHan, btnTatCa;
    ListView listViewMuiTiem;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Babyspinner = view.findViewById(R.id.spinnerChonBe);
        btnDaTiem = view.findViewById(R.id.btnVaccineDaTiem);
        btnQuaHan = view.findViewById(R.id.btnVaccineDaLo);
        btnTatCa = view.findViewById(R.id.btnVaccineTatCa);
        listViewMuiTiem = view.findViewById(R.id.listviewMuiTiem);

        Babyspinner = view.findViewById(R.id.spinnerChonBe);

        ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Kiet");
        spinnerArray.add("Truc");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), return_likes,  android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Babyspinner.setAdapter(adapter);

        super.onViewCreated(view, savedInstanceState);
    }
}
