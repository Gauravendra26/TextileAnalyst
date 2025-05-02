package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Forgot_Mobile_Activity extends AppCompatActivity {
    ImageView btn_back;
    EditText etMobile;
    RelativeLayout rlgetotp, rlcan;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_mobile);
        Utils.blackIconStatusBar(Forgot_Mobile_Activity.this, R.color.white);

        init();
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                hideDefaultKeyboard();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                btn_back.startAnimation(myAnim);

            }
        });
        rlcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("mobile",etMobile.getText().toString().trim());
                startActivity(i);
                finish();
                hideDefaultKeyboard();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlcan.startAnimation(myAnim);

            }
        });
        rlgetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobile.getText().toString().length()>=10)
                {
                    forgotpassword();
//                    Intent i = new Intent(getApplicationContext(), Forgot_OtpSubmit_Activity.class);
//                    i.putExtra("mobile",etMobile.getText().toString().trim());
//                    startActivity(i);
//                    finish();
                    hideDefaultKeyboard();

                }else{
                    Toast.makeText(Forgot_Mobile_Activity.this, "Please Enter Full Mobile Number", Toast.LENGTH_SHORT).show();
                }
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlgetotp.startAnimation(myAnim);

            }
        });

    }

    public void init() {
        btn_back = findViewById(R.id.btn_back);
        etMobile = findViewById(R.id.etMobile);
        rlgetotp = findViewById(R.id.rlgetotp);
        rlcan = findViewById(R.id.rlcan);
    }

    private void hideDefaultKeyboard() {
        //  MainActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        you have got lot of methods here
        if (getCurrentFocus() != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
    private void forgotpassword() {

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.new_progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", etMobile.getText().toString().trim());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                ApiData.SendOtp,jsonObject,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();

                        try {
                            if (response.getBoolean("status") == true) {
                                Toast.makeText(Forgot_Mobile_Activity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Forgot_OtpSubmit_Activity.class);
                                i.putExtra("mobile",etMobile.getText().toString().trim());
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Forgot_Mobile_Activity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

}