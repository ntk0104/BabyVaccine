package com.dmt.sannguyen.lgwfb;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

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
public class BeYeuFragment extends Fragment {

    ImageView btnAddBaby;
    ListView listviewBaby;

    private String userID;
    ArrayList<BeYeu> beYeuArrayList;
    public BeYeuAdapter adapter;


    public BeYeuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        beYeuArrayList = new ArrayList<>();
        adapter = new BeYeuAdapter(getActivity().getApplicationContext(), R.layout.row_baby_info, beYeuArrayList);

        //Receive the bundle data of user logged in from MainActivity
        Bundle bundle = getArguments();
        //Receive the ID of user logged in
        userID = bundle.getString("userid");
        GetChildren(userID);
        ImportVaccinForNewBaby();
        Log.d("Track", "UserID at BeYeuFragment:" + userID);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_be_yeu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnAddBaby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Track", "BtnAddBaby is clicked in created");
                Intent intent = new Intent(getActivity().getApplicationContext(), ThemBabyActivity.class);
                intent.putExtra("userid", userID);
                startActivity(intent);
            }
        });
        ListView listviewBaby = view.findViewById(R.id.listviewBaby);
        listviewBaby.setAdapter(adapter);
    }

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
                            }
                            adapter.notifyDataSetChanged();

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

    private void ImportVaccinForNewBaby(){
        String url = "https://babyvacxin.azurewebsites.net/api/InsertVaccineNewBaby?code=68auRUCgV6IdIdSIOvAnxsQasNkDpiYupYtH3QbsOH/dFaiwuYBflQ==";
        Log.d("Track", "Registering new user");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Track", "Response from ImportVaccinForNewBaby :" + response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Track", "Response from ImportVaccinForNewBaby :"+error);
                    }
                }
        );
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

}
