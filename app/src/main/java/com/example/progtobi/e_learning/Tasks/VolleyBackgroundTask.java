package com.example.progtobi.e_learning.Tasks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.progtobi.e_learning.model._User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 24-Nov-16.
 */

public class VolleyBackgroundTask {
    String fetchstud_url = "http://elearningapp.eu5.org/fetchstudents.php";
    Context context;
    ArrayList<_User> users = new ArrayList<>();

    public VolleyBackgroundTask(Context context) {
        this.context = context;
    }

    public ArrayList<_User> getData() {
        final ArrayList<_User> studentdata = new ArrayList<>();
        /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, fetchstud_url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("server_response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        _User user = new _User();
                        user.setName(jsonObject.getString("name"));
                        user.setPhone(jsonObject.getString("phone"));
                        studentdata.add(user);

                        Log.d("students", jsonObject.getString("name") + jsonObject.getString("phone"));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error.....", Toast.LENGTH_LONG).show();
                error.printStackTrace();

            }
        });
        Mysingleton.getInstance(getActivity()).addtorequestque(jsonObjectRequest);*/

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, fetchstud_url, (String) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(count);
                        _User user = new _User();
                        user.setName(jsonObject.getString("name"));
                        user.setPhone(jsonObject.getString("phone"));
                        studentdata.add(user);

                        Log.d("students", jsonObject.getString("name") + jsonObject.getString("phone"));
                        count++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }

        }

                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error.....", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }

        );
        Mysingleton.getInstance(context).addtorequestque(jsonArrayRequest);

        return studentdata;
    }
}
