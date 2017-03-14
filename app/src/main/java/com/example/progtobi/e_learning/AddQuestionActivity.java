package com.example.progtobi.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.progtobi.e_learning.Tasks.Mysingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddQuestionActivity extends AppCompatActivity {
    String administer_url = "http://elearningapp.eu5.org/uploadcourse.php";

    String topic_name, topic_note, topic_example, topic_hint, teacher;
    EditText question, questnexample;
    Button addmore, administer;
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> examples = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        question = (EditText) findViewById(R.id.questionedittxt);
        questnexample = (EditText) findViewById(R.id.exampleedittxt);
        addmore = (Button) findViewById(R.id.addmorebtn);
        administer = (Button) findViewById(R.id.adminsterquestn);
        /*addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionstr = question.getText().toString();
                String qexample = questnexample.getText().toString();
                questions.add(questionstr);
                examples.add(qexample);
            }
        });*/


        administer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                topic_name = i.getStringExtra("topic_name");
                topic_note = i.getStringExtra("topic_note");
                topic_example = i.getStringExtra("topic_example");
                topic_hint = i.getStringExtra("topic_hint");
                teacher = i.getStringExtra("teacher");

                if (!validate()){
                    return;
                }
                final String questionstr = question.getText().toString();
                final String questnexamplestr = questnexample.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, administer_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("upload_error")) {
                                Toast.makeText(AddQuestionActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            } else if (code.equals("upload_success")) {
                                Toast.makeText(AddQuestionActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            }

                            Intent intent = new Intent(AddQuestionActivity.this, AdministerCourse.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(AddQuestionActivity.this, "Course Uploaded Successfull...", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddQuestionActivity.this, "Error Uploading Course...", Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("topic_name", topic_name);
                        params.put("topic_note", topic_note);
                        params.put("topic_example", topic_example);
                        params.put("topic_hint", topic_hint);
                        params.put("question", questionstr);
                        params.put("example", questnexamplestr);
                        params.put("teacher", teacher);


                        return params;
                    }
                };

                Mysingleton.getInstance(AddQuestionActivity.this).addtorequestque(stringRequest);
            }
        });


    }

    public boolean validate() {
        boolean valid = true;

        String questn = question.getText().toString();
        String questexamp = questnexample.getText().toString();

        if (questn.isEmpty()) { //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())
            question.setError("Enter Question");
            valid = false;
        } else {
            question.setError(null);
        }

        if (questexamp.isEmpty()) {
            questnexample.setError("Enter Example");
            valid = false;
        } else {
            questnexample.setError(null);
        }

        return valid;
    }

}
