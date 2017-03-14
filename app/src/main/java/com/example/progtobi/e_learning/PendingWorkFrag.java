package com.example.progtobi.e_learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.progtobi.e_learning.Adapters.PendingWorkAdapter;
import com.example.progtobi.e_learning.Tasks.FetchPendingWork;
import com.example.progtobi.e_learning.Tasks.Mysingleton;
import com.example.progtobi.e_learning.model.PWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 20-Oct-16.
 */
public class PendingWorkFrag extends Fragment {
    private RecyclerView recyclerView;
    private PendingWorkAdapter pendingWorkAdapter;
    //String fetchstud_url = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pendinglayout, container, false);

        FetchPendingWork fetchPendingWork = new FetchPendingWork(getActivity());
        fetchPendingWork.execute();

        /*recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerstud);
        pendingWorkAdapter = new PendingWorkAdapter(getActivity(), getData());
        recyclerView.setAdapter(pendingWorkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
*/
        return rootView;
    }

   /* public ArrayList<PWork> getData() {
        final ArrayList<PWork> pendingwork = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, fetchstud_url, (String) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                //while (count < response.length()) {
                try {
                    //JSONObject jsonObject = response.getJSONObject;
                    //Log.d("students", jsonObject.getString("name")+jsonObject.getString("phone"));
                        *//*_User user = new _User(jsonObject.getString("name"), jsonObject.getString("phone"));
                        studentdata.add(user);*//*
                    for (int i = 0; i < response.length(); i++) {
                        //jsondetails = jsonarr.getJSONObject(i);
                        JSONObject jsonObject = response.getJSONObject(i);
                        PWork book = new PWork();
                        book.setId(jsonObject.getInt("bookId"));
                        book.setWorktitle(jsonObject.getString("worktitle"));
                        book.setTeachername(jsonObject.getString("teachername"));
                        book.setImgUrl(jsonObject.getString("bookImg"));
                        pendingwork.add(book);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error.....", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        Mysingleton.getInstance(getActivity()).addtorequestque(jsonArrayRequest);

        return pendingwork;
    }*/
}