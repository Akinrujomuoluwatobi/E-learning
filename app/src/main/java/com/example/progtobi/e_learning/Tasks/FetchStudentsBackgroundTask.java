package com.example.progtobi.e_learning.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.progtobi.e_learning.Adapters.StudentAdapter;
import com.example.progtobi.e_learning.R;
import com.example.progtobi.e_learning.model._User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 07-Jul-16.
 */
public class FetchStudentsBackgroundTask extends AsyncTask<String, _User, String> {
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ArrayList<_User> arrayList = new ArrayList<>();

    public FetchStudentsBackgroundTask(Activity ctx) {
        this.activity = ctx;

    }


    String json_string = "http://elearningapp.eu5.org/fetchstudents.php";

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.flush();
            bufferedWriter.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            httpURLConnection.disconnect();
            String json_string = stringBuilder.toString().trim();
            Log.d("JSON STRING", json_string);
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject json, jsondetails;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsondetails = jsonArray.getJSONObject(i);
                _User user = new _User();
                user.setName(jsondetails.getString("name"));
                user.setPhone(jsondetails.getString("phone"));
                arrayList.add(user);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Fetching Students");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onProgressUpdate(_User... values) {
        arrayList.add(values[0]);
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(String o) {
        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerstud);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new StudentAdapter(activity, arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();
    }
}

