package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;

import org.json.JSONException;
import org.json.JSONObject;

public class otp_page extends AppCompatActivity {

    ImageView imgbtn_back;
    RelativeLayout rlsubmit;
    PinView pinview;

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String email,password;
    TextView tvRese,tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        Utils.blackIconStatusBar(otp_page.this, R.color.white);

        init();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        tvEmail.setText(email);

        rlsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinview.getText().toString().length() >= 4) {
                    verifyOtp();
//                    Intent intent = new Intent(getApplicationContext(), profile_page.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(otp_page.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                }
                hideDefaultKeyboard();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlsubmit.startAnimation(myAnim);

            }
        });
        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                imgbtn_back.startAnimation(myAnim);

            }
        });
        tvRese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOtp();
                Toast.makeText(otp_page.this, "OTP has been sent", Toast.LENGTH_SHORT).show();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                tvRese.startAnimation(myAnim);

            }
        });
    }
    void init(){

        rlsubmit=findViewById(R.id.rlsubmit);
        pinview=findViewById(R.id.pinview);
        imgbtn_back=findViewById(R.id.imgbtn_back);
        tvRese = findViewById(R.id.tvRese);
        tvEmail=findViewById(R.id.tvEmail);

    }

    private void hideDefaultKeyboard() {
        //  MainActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //you have got lot of methods here
        if (getCurrentFocus() != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
    private void getOtp() {
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

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiData.Login,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();
                            try {
                                if (response.getBoolean("status") == true) {

                                    Toast.makeText(otp_page.this, "" + response.getString("message"),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(otp_page.this, "" + response.getString("message"),
                                            Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
    }

    private void verifyOtp() {
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.new_progress);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", email);
                jsonObject.put("otp", pinview.getText().toString().trim());

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiData.verify_otp,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                progressDialog.dismiss();

                                if (response.getBoolean("status") == true) {

                                    Toast.makeText(otp_page.this,
                                            "" + response.getString("message"), Toast.LENGTH_SHORT).show();

                                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.putBoolean("Login_Status", true);
                                    myEdit.putString("Login_Token",""+response.getString("token"));
                                    myEdit.apply();
                                    myEdit.commit();

                                    Intent intent = new Intent(getApplicationContext(), profile_page.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(otp_page.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.putBoolean("Login_Status", false);
                                    myEdit.apply();
                                    myEdit.commit();
                                }
                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(jsonObjectRequest);
        }
    }
}