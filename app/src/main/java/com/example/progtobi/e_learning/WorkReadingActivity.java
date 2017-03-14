package com.example.progtobi.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkReadingActivity extends AppCompatActivity {
    TextView notetxtview, exampletxtview, hinttxtview;
    Button answerquestn;
    String workfetch = "http://elearningapp.eu5.org/fetchbook";
    int workID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_reading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        String worktitle, worknote, workexample, workhint, workquest, workquestex;

        worktitle = i.getStringExtra("worktitle");
        worknote = i.getStringExtra("worknote");
        workexample = i.getStringExtra("workexample");
        workhint = i.getStringExtra("workhint");
        workquest = i.getStringExtra("workquest");
        workquestex = i.getStringExtra("workquestex");
        toolbar.setTitle(worktitle);


        notetxtview = (TextView) findViewById(R.id.note);
        exampletxtview = (TextView) findViewById(R.id.example);
        hinttxtview = (TextView) findViewById(R.id.hint);
        answerquestn = (Button) findViewById(R.id.proceed);

        notetxtview.setText(worknote);
        exampletxtview.setText(workexample);
        hinttxtview.setText(workhint);
        answerquestn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, workfetch, (String) null,  new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    //jsondetails = jsonarr.getJSONObject(i);
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        workID = jsonObject.getInt("id");
                        notetxtview.setText(jsonObject.getString("note"));
                        exampletxtview.setText(jsonObject.getString("example"));
                        hinttxtview.setText(jsonObject.getString("hint"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WorkReadingActivity.this, "Error Fetching Work", Toast.LENGTH_LONG).show();
            }
        });*/
    }

}
