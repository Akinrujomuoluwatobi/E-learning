package com.example.progtobi.e_learning;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class UserRegistration extends AppCompatActivity {
    EditText Surname, Othername, Phone, Email, Username, Password, Cpassword;
    Button register;
    String serverUrl = "http://elearningapp.eu5.org/registration.php";
    ProgressBar regpb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Surname = (EditText) findViewById(R.id.user_surname);
        Othername = (EditText) findViewById(R.id.user_othername);
        Phone = (EditText) findViewById(R.id.user_phone);
        Email = (EditText) findViewById(R.id.user_email);
        Username = (EditText) findViewById(R.id.user_username);
        Password = (EditText) findViewById(R.id.user_password);
        Cpassword = (EditText) findViewById(R.id.user_cpassword);
        register = (Button) findViewById(R.id.registerbtn);
        regpb = (ProgressBar) findViewById(R.id.registerpb);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String surname, othername, phone, email, username, password, cpassword;
                surname = Surname.getText().toString();
                othername = Othername.getText().toString();
                phone = Phone.getText().toString();
                email = Email.getText().toString();
                username = Username.getText().toString();
                password = Password.getText().toString();
                cpassword = Cpassword.getText().toString();

                if (!validate()) {
                    return;
                }

                if (!isOnline(UserRegistration.this)) {
                    Toast.makeText(UserRegistration.this, "No network connection", Toast.LENGTH_LONG).show();
                    return;
                }

                regpb.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("reg_failed")) {
                                String message = jsonObject.getString("message");
                                regpb.setVisibility(View.GONE);
                                Toast.makeText(UserRegistration.this, message, Toast.LENGTH_LONG).show();
                            } else if (code.equals("reg_success")) {
                                String message = jsonObject.getString("message");
                                regpb.setVisibility(View.GONE);
                                Toast.makeText(UserRegistration.this, message, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UserRegistration.this, LoginActivity.class);
                                startActivity(intent);
                                UserRegistration.this.finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserRegistration.this, "Error Registering", Toast.LENGTH_SHORT).show();
                        regpb.setVisibility(View.GONE);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("surname", surname);
                        params.put("othername", othername);
                        params.put("phoneno", phone);
                        params.put("email", email);
                        params.put("username", username);
                        params.put("password", password);
                        return params;
                    }
                };

                Mysingleton.getInstance(UserRegistration.this).addtorequestque(stringRequest);


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

        String surnamestr = Surname.getText().toString();
        String othernamestr = Othername.getText().toString();
        String phonestr = Phone.getText().toString();
        String emailstr = Email.getText().toString();
        String usernamestr = Username.getText().toString();
        String passwordstr = Password.getText().toString();
        String cpasswordstr = Cpassword.getText().toString();

        if (surnamestr.isEmpty()) {
            Surname.setError("Fill Surnmae");
            valid = false;
        } else {
            Surname.setError(null);
        }

        if (othernamestr.isEmpty()) {
            Othername.setError("Fill Othernames");
            valid = false;
        } else {
            Othername.setError(null);
        }

        if (usernamestr.isEmpty()) { //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())
            Username.setError("Fill Username");
            valid = false;
        } else {
            Username.setError(null);
        }

        if (emailstr.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailstr).matches()) {
            Email.setError("Invalid Email");
            valid = false;
        } else {
            Email.setError(null);
        }

        if (phonestr.isEmpty() || !Patterns.PHONE.matcher(phonestr).matches()) {
            Phone.setError("Invalid Number");
            valid = false;
        } else {
            Phone.setError(null);
        }

        if (passwordstr.isEmpty()) {
            Password.setError("Enter Password");
            valid = false;
        } else {
            Password.setError(null);
        }

        if (cpasswordstr.isEmpty()) {
            Cpassword.setError("Confirm Password");
            valid = false;
        } else if (!cpasswordstr.matches(passwordstr)) {
            Cpassword.setError("Password not match");
            Password.setError("Password not match");
            valid = false;
        } else {
            Cpassword.setError(null);
            Password.setError(null);

        }

        return valid;
    }

}
