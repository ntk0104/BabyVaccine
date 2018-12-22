package com.dmt.sannguyen.lgwfb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiemTiemChungFragment extends Fragment {

    ListView listViewTramXa;
    ArrayList<TramXa> tramXaArrayList;
    TramXaAdapter adapter;


    public DiemTiemChungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tramXaArrayList = new ArrayList<>();
        adapter = new TramXaAdapter(getActivity().getApplicationContext(), R.layout.row_tram_xa_info, tramXaArrayList);
        getTramXa();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diem_tiem_chung, container, false);
    }


    private void getTramXa() {
        String url = "https://babyvacxin.azurewebsites.net/api/GetTramXa?code=0xvjtUsljVl/Pdqv8P2Tp6WVve/EWXs7zvJpBQtdX2/F/bYwk43kGg==";
        Log.d("Track", "Getting list Tram Xa...");
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
                            JSONArray listTramXa = response.getJSONArray("ListTramXa");
                            for (int i = 0; i < listTramXa.length(); ++i){
                                JSONObject tramxa = listTramXa.getJSONObject (i);
                                TramXa tramXa = new TramXa(tramxa.getInt("IDDiemTiem"), tramxa.getString("Ten"), tramxa.getString("DiaChi"), tramxa.getString("Street"), tramxa.getString("Ward"), tramxa.getString("District"), tramxa.getString("City"), tramxa.getLong("Xvalue"), tramxa.getLong("Yvalue"));
                                tramXaArrayList.add(tramXa);
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
        );
        //set to avoid Volley send request multiple times
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewTramXa = view.findViewById(R.id.listViewTramXa);
        listViewTramXa.setAdapter(adapter);
    }
}
