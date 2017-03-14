package com.example.progtobi.e_learning;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginbtn;
    TextView registertxtview;
    String login_url = "http://elearningapp.eu5.org";
    AlertDialog.Builder builder;
    ProgressBar pb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        registertxtview = (TextView) findViewById(R.id.tv_register);
        builder = new AlertDialog.Builder(LoginActivity.this);
        pb = (ProgressBar) findViewById(R.id.pb);

        loginbtn = (Button) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String uname = username.getText().toString();
                final String upass = password.getText().toString();
                if (!isOnline(LoginActivity.this)) {
                    Toast.makeText(LoginActivity.this, "No network connection", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!validate()) {
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("login_failed")) {
                                builder.setTitle("Login Error");
                                displayAlert(jsonObject.getString("message"));
                                pb.setVisibility(View.GONE);
                            } else if (code.equals("login_success")) {
                                String usertype = jsonObject.getString("usertype");
                                if (usertype.equals("staff")) {
                                    Intent intent = new Intent(getApplication(), ElearningStaff.class);
                                    intent.putExtra("name",jsonObject.getString("name"));
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                } else if (usertype.equals("student")) {
                                    Intent intent = new Intent(getApplication(), ElearningStudent.class);
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        pb.setVisibility(View.GONE);

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", uname);
                        params.put("password", upass);
                        return params;
                    }
                };
                Mysingleton.getInstance(LoginActivity.this).addtorequestque(stringRequest);
            }

            //code to check online details
            private boolean isOnline(Context mContext) {
                ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    return true;
                }
                return false;
            }
            //Close code that check online details
        });

        registertxtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnline(LoginActivity.this)) {
                    Toast.makeText(LoginActivity.this, "No network connection", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getApplication(), UserRegistration.class);
                startActivity(intent);


            }

            //code to check online details
            private boolean isOnline(Context mContext) {
                ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    return true;
                }
                return false;
            }
            //Close code that check online details
        });


    }

    public boolean validate() {
        boolean valid = true;

        String usernamestr = username.getText().toString();
        String passwordstr = password.getText().toString();

        if (usernamestr.isEmpty()) { //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())
            username.setError("enter username");
            valid = false;
        } else {
            username.setError(null);
        }

        if (passwordstr.isEmpty()) {
            password.setError("Enter Password");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                username.setText("");
                password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
