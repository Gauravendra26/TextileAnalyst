package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nssoseedanalyst.ApiData;
import com.example.nssoseedanalyst.R;
import com.example.nssoseedanalyst.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rllogin;
    ImageView imgshow,imghide;
    EditText etuserid,etPass;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    TextView tvForgot;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.blackIconStatusBar(MainActivity.this, R.color.LoginPage);
        init();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        SharedPreferences sh = getSharedPreferences("MyData", MODE_PRIVATE);
        Boolean Login_Status = sh.getBoolean("Login_Status", false);
        if (Login_Status) {
            Intent i = new Intent(getApplicationContext(), profile_page.class);
            startActivity(i);
            finish();
        }


        rllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()){
                    getLogIn(email,password);
//                    Intent i = new Intent(getApplicationContext(), Mobile_Activity.class);
//                    startActivity(i);
//                    finish();
                }
                hideDefaultKeyboard();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rllogin.startAnimation(myAnim);

            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Forgot_Mobile_Activity.class);
                startActivity(i);
                finish();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                tvForgot.startAnimation(myAnim);

            }
        });
        imgshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etPass.setTransformationMethod(null);
                imgshow.setVisibility(View.INVISIBLE);
                imghide.setVisibility(View.VISIBLE);
                hideDefaultKeyboard();
                imgshow.startAnimation(clickAnimation());
            }
        });
        imghide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPass.setTransformationMethod(new PasswordTransformationMethod());

                imghide.setVisibility(View.INVISIBLE);
                imgshow.setVisibility(View.VISIBLE);
                hideDefaultKeyboard();
                imghide.startAnimation(clickAnimation());
            }
        });
    }

    void init() {

        rllogin = findViewById(R.id.rllogin);
        etuserid = findViewById(R.id.etuserid);
        etPass = findViewById(R.id.etPass);
        tvForgot = findViewById(R.id.tvForgot);
        imgshow = findViewById(R.id.imgshow);
        imghide = findViewById(R.id.imghide);
    }

    public AlphaAnimation clickAnimation() {
        return new AlphaAnimation(1F, 0.1F); // Change "0.4F" as per your recruitment.
    }

    private void hideDefaultKeyboard() {
        //  MainActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //you have got lot of methods here
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    private void getLogIn(String email, String password) {
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.new_progress);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", email);
                jsonObject.put("password", password);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiData.Login, jsonObject,
                    new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();

                            try {
                                if (response.getBoolean("status") == true) {

//                                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                                    myEdit.putBoolean("Login_Status", true);
//                                    myEdit.putString("Login_Token",""+response.getString("token"));
//                                    myEdit.apply();
//                                    myEdit.commit();
                                    Toast.makeText(MainActivity.this, "" + response.getString("message"),
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), otp_page.class);
                                    i.putExtra("email",etuserid.getText().toString().trim());
                                    i.putExtra("password",etPass.getText().toString());
                                    startActivity(i);
finish();


                                } else {
//                                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                                    myEdit.putBoolean("Login_Status", false);
//                                    myEdit.apply();
//                                    myEdit.commit();
                                    Toast.makeText(MainActivity.this, "Please Enter Correct Email and Password", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            requestQueue.add(jsonObjectRequest);

        }


    }

    boolean isValid() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        email= etuserid.getText().toString().trim();
        password= etPass.getText().toString();

        if (email.isEmpty()) {
            etuserid.setError("Please enter Userid");
            return false;
        }
        if (password.isEmpty()) {
            etPass.setError("Enter Password");
            return false;
        }
        return true;
    }
}