package com.dmt.sannguyen.lgwfb;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoTiemFragment extends Fragment {

    private String userID;
    private int ID_Selected_Baby;

    Spinner Babyspinner;
    Button btnDaTiem, btnQuaHan, btnTatCa;
    ListView listViewMuiTiem;

    // ArrayList of babies of user
    ArrayList<BeYeu> beYeuArrayList;
    // ArrayList name of babies of user -> spinner
    ArrayList<String> tenBeYeuArrayList;
    // ArrayList include Displaying MuiTiem of current Baby
    ArrayList<MuiTiem> muiTiemArrayList;
    //Adapter for spinner choose baby
    ArrayAdapter<String> spinnerAdapter;
    //Adapter for list of mui tiem will be displayed
    SoTiemAdapter soTiemAdapter;

    public SoTiemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tenBeYeuArrayList = new ArrayList<>();
        beYeuArrayList = new ArrayList<>();
        muiTiemArrayList = new ArrayList<>();
        //Receive the bundle data of user logged in from MainActivity
        Bundle bundle = getArguments();
        //Receive the ID of user logged in
        userID = bundle.getString("userid");
        Log.d("Track", "UserID at SoTiemChungFragment:" + userID);
        GetChildren(userID);



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


        soTiemAdapter = new SoTiemAdapter(getActivity().getApplicationContext(), R.layout.row_mui_tiem_info, muiTiemArrayList);
        listViewMuiTiem.setAdapter(soTiemAdapter);

        //Change color for text in button
        btnTatCa.setTextColor(Color.rgb(255, 0, 0));
        getAllVaccines(ID_Selected_Baby);


        // set event for btn Tat ca
        btnTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                getAllVaccines(ID_Selected_Baby);
                //Change color for text in button
                btnTatCa.setTextColor(Color.rgb(255, 0, 0));
                btnDaTiem.setTextColor(Color.rgb(255,255,255));
                btnQuaHan.setTextColor(Color.rgb(255,255,255));
            }
        });

        // set event for btn Da Tiem
        btnDaTiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                getAllVaccinesDaTiem(ID_Selected_Baby);
                //Change color for text in button
                btnDaTiem.setTextColor(Color.rgb(255, 0, 0));
                btnTatCa.setTextColor(Color.rgb(255,255,255));
                btnQuaHan.setTextColor(Color.rgb(255,255,255));
            }
        });

        // set event for btn Da Qua
        btnQuaHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                getAllVaccinesQuaHan(ID_Selected_Baby);
                //Change color for text in button
                btnQuaHan.setTextColor(Color.rgb(255, 0, 0));
                btnTatCa.setTextColor(Color.rgb(255,255,255));
                btnDaTiem.setTextColor(Color.rgb(255,255,255));
            }
        });




        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, tenBeYeuArrayList);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), return_likes,  android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Babyspinner.setAdapter(spinnerAdapter);
        Babyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // selected item in spinner
                Object item = parent.getItemAtPosition(position);
                // position of selected item: start by 0
                int selectedPosition = parent.getSelectedItemPosition();
                Log.d("Track", "Item selected:" + item.toString() + " position: " + selectedPosition);
                ID_Selected_Baby = beYeuArrayList.get(selectedPosition).getBabyID();
                Log.d("Track", "Show ID selected Item: " + ID_Selected_Baby);
                // Set to Tat Ca cho baby moi
                loading();
                getAllVaccines(ID_Selected_Baby);
                //Change color for text in button
                btnTatCa.setTextColor(Color.rgb(255, 0, 0));
                btnDaTiem.setTextColor(Color.rgb(255,255,255));
                btnQuaHan.setTextColor(Color.rgb(255,255,255));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }


    // Get list children based userid
    private void GetChildren(final String userid){
        String url = "https://babyvacxin.azurewebsites.net/api/GetUserChildren?code=Hv47n04faQgtp0eJdr5cmTjE7Af6aJJUAdmOKxqI1WyYmM8bPYwDdA==";
        Log.d("Track", "Getting user children");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from GetChildren :" + response);
                        try {
                            JSONArray listchildren = response.getJSONArray("ListBaby");
                            for (int i = 0; i < listchildren.length(); ++i){
                                JSONObject baby = listchildren.getJSONObject (i);
                                BeYeu beYeu = new BeYeu(baby.getInt("BabyID"), baby.getString("Name"), baby.getString("DateOfBirth"), baby.getString("Avatar"), baby.getString("Gender"), baby.getInt("Age_Hour"), baby.getString("Age_Display"));
                                beYeuArrayList.add(beYeu);
                                tenBeYeuArrayList.add(baby.getString("Name"));
                                ID_Selected_Baby = beYeuArrayList.get(0).getBabyID();
                            }

                            Log.d("Track", "Inital value for ID_Selected_Baby : " + ID_Selected_Baby);
                            spinnerAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from GetChildren :"+error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("userid", userid);
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    private void getAllVaccines(final int babyID) {
        muiTiemArrayList.clear();
        Log.d("Track", "babyID got in getAllVaccines : " + babyID);
        String url = "https://babyvacxin.azurewebsites.net/api/GetAllVaccines?code=3deL5N8DI0R9HBVTZfsEJy8S7/DUMWBGY0TayIliXEUJdZLB1jD4fQ==";
        Log.d("Track", "Getting list all vaccines");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from getAllVaccines :" + response);
                        try {
                            JSONArray listAllVaccines = response.getJSONArray("AllVaccines");
                            for (int i = 0; i < listAllVaccines.length(); ++i){
                                JSONObject muitiem = listAllVaccines.getJSONObject(i);
//                                Log.d("Track", "Import Mui Tiem to muiTiemArrayList");
                                int STTMuiTiem = muitiem.getInt("STTMuiTiem");
//                                Log.d("Track", "STTMuiTiem : " + STTMuiTiem);
                                int BabyID = muitiem.getInt("BabyID");
//                                Log.d("Track", "BabyID : " + BabyID);
                                int MuiTiemID = muitiem.getInt("MuiTiemID");
//                                Log.d("Track", "MuiTiemID : " + MuiTiemID);
                                String ThoiGianDuKien = muitiem.getString("ThoiGianDuKien");
//                                Log.d("Track", "ThoiGianDuKien : " + ThoiGianDuKien);
                                String ThoiGianTiem = muitiem.getString("ThoiGianTiem");
//                                Log.d("Track", "ThoiGianTiem : " + ThoiGianTiem);
                                String ThoiGianHenGio = muitiem.getString("ThoiGianHenGio");
//                                Log.d("Track", "ThoiGianHenGio : " + ThoiGianHenGio);
                                String GhiChu = muitiem.getString("GhiChu");
//                                Log.d("Track", "GhiChu : " + GhiChu);
                                String BabyName = muitiem.getString("BabyName");
//                                Log.d("Track", "BabyName : " + BabyName);
                                String MuiTiemName = muitiem.getString("MuiTiemName");
//                                Log.d("Track", "MuiTiemName : " + MuiTiemName);
                                String StatusMuiTiem = muitiem.getString("StatusMuiTiem");
//                                Log.d("Track", "StatusMuiTiem : " + StatusMuiTiem);
                                MuiTiem muiTiem = new MuiTiem(STTMuiTiem, BabyID, MuiTiemID, ThoiGianDuKien, ThoiGianTiem, ThoiGianHenGio, GhiChu, BabyName, MuiTiemName, StatusMuiTiem);
//                                Log.d("Track", muiTiem.toString());
                                muiTiemArrayList.add(muiTiem);
                            }
                            Log.d("Track", "muiTiemArrayList size value: "+ muiTiemArrayList.size());
                            soTiemAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from getAllVaccines :"+error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("babyid", String.valueOf(babyID));
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    //Get All Mui Tiem Qua Han and set to List View
    private void getAllVaccinesQuaHan(final int babyID) {
        muiTiemArrayList.clear();
        Log.d("Track", "babyID got in getAllVaccinesQuaHan : " + babyID);
        String url = "https://babyvacxin.azurewebsites.net/api/GetAllVaccinesQuaHan?code=pfCoLKkf5TJnB0Wjag7aoOqlgn0rVwkcfRJI/GMoenICEOw/JNpiRQ==";
        Log.d("Track", "Getting list all QuaHan");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from getAllVaccinesQuaHan :" + response);
                        try {
                            JSONArray listAllVaccines = response.getJSONArray("ListQuaHan");
                            for (int i = 0; i < listAllVaccines.length(); ++i){
                                JSONObject muitiem = listAllVaccines.getJSONObject(i);
//                                Log.d("Track", "Import Mui Tiem to muiTiemArrayList");
                                int STTMuiTiem = muitiem.getInt("STTMuiTiem");
//                                Log.d("Track", "STTMuiTiem : " + STTMuiTiem);
                                int BabyID = muitiem.getInt("BabyID");
//                                Log.d("Track", "BabyID : " + BabyID);
                                int MuiTiemID = muitiem.getInt("MuiTiemID");
//                                Log.d("Track", "MuiTiemID : " + MuiTiemID);
                                String ThoiGianDuKien = muitiem.getString("ThoiGianDuKien");
//                                Log.d("Track", "ThoiGianDuKien : " + ThoiGianDuKien);
                                String ThoiGianTiem = muitiem.getString("ThoiGianTiem");
//                                Log.d("Track", "ThoiGianTiem : " + ThoiGianTiem);
                                String ThoiGianHenGio = muitiem.getString("ThoiGianHenGio");
//                                Log.d("Track", "ThoiGianHenGio : " + ThoiGianHenGio);
                                String GhiChu = muitiem.getString("GhiChu");
//                                Log.d("Track", "GhiChu : " + GhiChu);
                                String BabyName = muitiem.getString("BabyName");
//                                Log.d("Track", "BabyName : " + BabyName);
                                String MuiTiemName = muitiem.getString("MuiTiemName");
//                                Log.d("Track", "MuiTiemName : " + MuiTiemName);
                                String StatusMuiTiem = muitiem.getString("StatusMuiTiem");
//                                Log.d("Track", "StatusMuiTiem : " + StatusMuiTiem);
                                MuiTiem muiTiem = new MuiTiem(STTMuiTiem, BabyID, MuiTiemID, ThoiGianDuKien, ThoiGianTiem, ThoiGianHenGio, GhiChu, BabyName, MuiTiemName, StatusMuiTiem);
//                                Log.d("Track", muiTiem.toString());
                                muiTiemArrayList.add(muiTiem);
                            }
                            Log.d("Track", "muiTiemArrayList size value: "+ muiTiemArrayList.size());
                            soTiemAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from getAllVaccinesQuaHan :"+error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("babyid", String.valueOf(babyID));
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    //Get All Vaccines Da Tiem
    //Get All Mui Tiem Qua Han and set to List View
    private void getAllVaccinesDaTiem(final int babyID) {
        muiTiemArrayList.clear();
        Log.d("Track", "babyID got in getAllVaccinesDaTiem : " + babyID);
        String url = "https://babyvacxin.azurewebsites.net/api/GetListDaTiem?code=bYRaHwsapits6tSntuOgJMPyrqYVqQr7RrYE/LiZSl785NZhw2gbjw==";
        Log.d("Track", "Getting list all DaTiem");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from getAllVaccinesDaTiem :" + response);
                        try {
                            JSONArray listAllVaccines = response.getJSONArray("ListDaTiem");
                            for (int i = 0; i < listAllVaccines.length(); ++i){
                                JSONObject muitiem = listAllVaccines.getJSONObject(i);
//                                Log.d("Track", "Import Mui Tiem to muiTiemArrayList");
                                int STTMuiTiem = muitiem.getInt("STTMuiTiem");
//                                Log.d("Track", "STTMuiTiem : " + STTMuiTiem);
                                int BabyID = muitiem.getInt("BabyID");
//                                Log.d("Track", "BabyID : " + BabyID);
                                int MuiTiemID = muitiem.getInt("MuiTiemID");
//                                Log.d("Track", "MuiTiemID : " + MuiTiemID);
                                String ThoiGianDuKien = muitiem.getString("ThoiGianDuKien");
//                                Log.d("Track", "ThoiGianDuKien : " + ThoiGianDuKien);
                                String ThoiGianTiem = muitiem.getString("ThoiGianTiem");
//                                Log.d("Track", "ThoiGianTiem : " + ThoiGianTiem);
                                String ThoiGianHenGio = muitiem.getString("ThoiGianHenGio");
//                                Log.d("Track", "ThoiGianHenGio : " + ThoiGianHenGio);
                                String GhiChu = muitiem.getString("GhiChu");
//                                Log.d("Track", "GhiChu : " + GhiChu);
                                String BabyName = muitiem.getString("BabyName");
//                                Log.d("Track", "BabyName : " + BabyName);
                                String MuiTiemName = muitiem.getString("MuiTiemName");
//                                Log.d("Track", "MuiTiemName : " + MuiTiemName);
                                String StatusMuiTiem = muitiem.getString("StatusMuiTiem");
//                                Log.d("Track", "StatusMuiTiem : " + StatusMuiTiem);
                                MuiTiem muiTiem = new MuiTiem(STTMuiTiem, BabyID, MuiTiemID, ThoiGianDuKien, ThoiGianTiem, ThoiGianHenGio, GhiChu, BabyName, MuiTiemName, StatusMuiTiem);
//                                Log.d("Track", muiTiem.toString());
                                muiTiemArrayList.add(muiTiem);
                            }
                            Log.d("Track", "muiTiemArrayList size value: "+ muiTiemArrayList.size());
                            soTiemAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from getAllVaccinesDaTiem :"+error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("babyid", String.valueOf(babyID));
                return params;
            }


        };
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    //Get All Mui Tiem of user
//    private void Get
    private void loading()
    {
        final ProgressDialog progress = new ProgressDialog(getActivity());
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
        pdCanceller.postDelayed(progressRunnable, 2000);
    }

}
