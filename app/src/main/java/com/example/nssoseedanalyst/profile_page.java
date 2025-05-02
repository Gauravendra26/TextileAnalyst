package com.example.nssoseedanalyst;

import static com.example.nssoseedanalyst.ApiData.Image_BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class profile_page extends AppCompatActivity {

    RelativeLayout rlView, rlLogout, radioPresentSelect, radioPresentNotSelect, radioLeaveSelect,
            rlSubmitStatus, radioLeaveNotSelect, rlproceed;

    ImageView imgSelect, imgSelect1, imgNotSelect, imgNotSelect1;
    TextView tvRegSeedPro, tvRegChawki, tvname, tvid, tvEvaluationDone, tvUnderPro, tvNotEvaluated, tvNewAdd ,tvDay;
    int  user_role,assigned,draft,complete,notComplete,notCompleteRenew,today,renewassigned,renewdraft,renewcomplete,renewtoday;
    String check = "12a", dayName, formattedDate, formattedTime, combinedString;
    ImageView img_userimage;
    private long downloadId;
    private DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Utils.blackIconStatusBar(profile_page.this, R.color.white);
        init();
        Status();
        Verification();
        rlSubmitStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlLogout.startAnimation(myAnim);

            }
        });

        rlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Seed_Officers.class);
                intent.putExtra("check", check);
                intent.putExtra("combinedString", combinedString);
                startActivity(intent);
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlView.startAnimation(myAnim);

            }
        });

        getProfileData();

        dataFormat();
    }

    void init() {

        rlView = findViewById(R.id.rlView);
        rlLogout = findViewById(R.id.rlLogout);
        imgSelect = findViewById(R.id.imgSelect);
        imgSelect1 = findViewById(R.id.imgSelect1);
        imgNotSelect = findViewById(R.id.imgNotSelect);
        imgNotSelect1 = findViewById(R.id.imgNotSelect1);
        tvRegChawki = findViewById(R.id.tvRegChawki);
        tvRegSeedPro = findViewById(R.id.tvRegSeedPro);
        tvname = findViewById(R.id.tvname);
        tvid = findViewById(R.id.tvid);
        tvDay = findViewById(R.id.tvDay);
        tvEvaluationDone = findViewById(R.id.tvEvaluationDone);
        tvUnderPro = findViewById(R.id.tvUnderPro);
        tvNotEvaluated = findViewById(R.id.tvNotEvaluated);
        tvNewAdd = findViewById(R.id.tvNewAdd);
         radioPresentSelect = findViewById(R.id.radioPresentSelect);
        radioPresentNotSelect = findViewById(R.id.radioPresentNotSelect);
        radioLeaveSelect = findViewById(R.id.radioLeaveSelect);
        radioLeaveNotSelect = findViewById(R.id.radioLeaveNotSelect);
        rlSubmitStatus = findViewById(R.id.rlSubmitStatus);
        img_userimage = findViewById(R.id.img_userimage);


    }

    void dataFormat() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Format the day name (abbreviated)
        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        String dayNameAbbreviated = dayNameFormat.format(currentDate);

        // Format the day of the month
        SimpleDateFormat dayOfMonthFormat = new SimpleDateFormat("dd", Locale.getDefault());
        String dayOfMonth = dayOfMonthFormat.format(currentDate);

        // Format the month name
        SimpleDateFormat monthNameFormat = new SimpleDateFormat("MMM", Locale.getDefault());
        String monthName = monthNameFormat.format(currentDate);

        // Format the year
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String year = yearFormat.format(currentDate);

        // Format the time
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String formattedTime = timeFormat.format(currentDate);

        // Combine the formatted components
        combinedString = "Last login:"+dayNameAbbreviated + ", " + dayOfMonth + " " + monthName + " " + year + ", " + formattedTime;

        // Display the formatted date and time

        tvDay.setText(combinedString);
    }
    public void Status() {
        radioPresentSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioPresentSelect.setVisibility(View.VISIBLE);
                radioPresentNotSelect.setVisibility(View.GONE);
                radioLeaveSelect.setVisibility(View.GONE);
                radioLeaveNotSelect.setVisibility(View.VISIBLE);

            }
        });
        radioPresentNotSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioPresentSelect.setVisibility(View.VISIBLE);
                radioPresentNotSelect.setVisibility(View.GONE);
                radioLeaveSelect.setVisibility(View.GONE);
                radioLeaveNotSelect.setVisibility(View.VISIBLE);

            }
        });

        radioLeaveNotSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioPresentSelect.setVisibility(View.GONE);
                radioPresentNotSelect.setVisibility(View.VISIBLE);
                radioLeaveSelect.setVisibility(View.VISIBLE);
                radioLeaveNotSelect.setVisibility(View.GONE);

            }
        });
        radioLeaveSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioPresentSelect.setVisibility(View.GONE);
                radioPresentNotSelect.setVisibility(View.VISIBLE);
                radioLeaveSelect.setVisibility(View.VISIBLE);
                radioLeaveNotSelect.setVisibility(View.GONE);

            }
        });

    }

    void getProfileData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.new_progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

// Create a new request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                ApiData.Profile, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            if (response.getBoolean("status") == true) {

                                JSONObject jsonObject = response.getJSONObject("data");
                                Log.e("Profilecheck", String.valueOf(jsonObject));
                                int user_id = jsonObject.optInt("user_id");
                                user_role = jsonObject.optInt("user_role");
                                assigned = jsonObject.optInt("assigned");
                                renewassigned = jsonObject.optInt("renewassigned");
                                draft = jsonObject.optInt("draft");
                                renewdraft = jsonObject.optInt("renewdraft");
                                complete = jsonObject.optInt("complete");
                                renewcomplete = jsonObject.optInt("renewcomplete");
                                today = jsonObject.optInt("today");
                                renewtoday = jsonObject.optInt("renewtoday");
                                int user_asign_seed_officers = jsonObject.optInt("user_asign_seed_officers");
                                int user_status = jsonObject.optInt("user_status");
                                String user_name = jsonObject.optString("user_name");
                                String user_phone = jsonObject.optString("user_phone");
                                String email = jsonObject.optString("email");
                                String user_empid = jsonObject.optString("user_empid");
                                String user_image = Image_BASE_URL + jsonObject.optString("user_image");
                                String user_application = jsonObject.optString("user_application");
                                String created_at = jsonObject.optString("created_at");
                                String updated_at = jsonObject.optString("updated_at");
                                Log.e("checkImage", user_image);
                                if (user_role == 1) {
                                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyData",
                                            MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.clear();
                                    myEdit.apply();
                                    Toast.makeText(profile_page.this, "You are not a Seed Analyst", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                notComplete=assigned-complete;
                                notCompleteRenew=renewassigned-renewcomplete;

                                tvname.setText(user_name);
                                tvid.setText(user_empid);
                                tvNotEvaluated.setText(String.valueOf(notComplete+notCompleteRenew));
                                tvEvaluationDone.setText(String.valueOf(complete+renewcomplete));
                                tvUnderPro.setText(String.valueOf(draft+renewdraft));
                                tvNewAdd.setText(String.valueOf(today+renewtoday));

                                Glide.with(profile_page.this)
                                        .load(user_image)
                                        .placeholder(R.drawable.img_user) // Optional placeholder image while loading
//                .error(R.drawable.error) // Optional error image if loading fails
                                        .into(img_userimage);
                            } else {
                                Toast.makeText(profile_page.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {

                        }
//
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        // Display an error message or retry request
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Set the token in the headers
                SharedPreferences sharedPreferences =
                        getSharedPreferences("MyData", MODE_PRIVATE);
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + sharedPreferences.getString("Login_Token", ""));
                return headers;
            }
        };

// Add the request to the Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    void getProfileDataOnResume() {


// Create a new request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                ApiData.Profile, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("status") == true) {

                                JSONObject jsonObject = response.getJSONObject("data");
                                Log.e("Profilecheck", String.valueOf(jsonObject));
                                int user_id = jsonObject.optInt("user_id");
                                user_role = jsonObject.optInt("user_role");
                                assigned = jsonObject.optInt("assigned");
                                renewassigned = jsonObject.optInt("renewassigned");
                                draft = jsonObject.optInt("draft");
                                renewdraft = jsonObject.optInt("renewdraft");
                                complete = jsonObject.optInt("complete");
                                renewcomplete = jsonObject.optInt("renewcomplete");
                                today = jsonObject.optInt("today");
                                renewtoday = jsonObject.optInt("renewtoday");
                                int user_asign_seed_officers = jsonObject.optInt("user_asign_seed_officers");
                                int user_status = jsonObject.optInt("user_status");
                                String user_name = jsonObject.optString("user_name");
                                String user_phone = jsonObject.optString("user_phone");
                                String email = jsonObject.optString("email");
                                String user_empid = jsonObject.optString("user_empid");
                                String user_image = Image_BASE_URL + jsonObject.optString("user_image");
                                String user_application = jsonObject.optString("user_application");
                                String created_at = jsonObject.optString("created_at");
                                String updated_at = jsonObject.optString("updated_at");
                                Log.e("checkImage", user_image);
                                if (user_role == 1) {
                                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyData",
                                            MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.clear();
                                    myEdit.apply();
                                    Toast.makeText(profile_page.this, "You are not a Seed Analyst", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
                                }
                                notComplete=assigned-complete;
                                notCompleteRenew=renewassigned-renewcomplete;

                                tvname.setText(user_name);
                                tvid.setText(user_empid);
                                tvNotEvaluated.setText(String.valueOf(notComplete+notCompleteRenew));
                                tvEvaluationDone.setText(String.valueOf(complete+renewcomplete));
                                tvUnderPro.setText(String.valueOf(draft+renewdraft));
                                tvNewAdd.setText(String.valueOf(today+renewtoday));
                                Glide.with(getApplicationContext())
                                        .load(user_image)
                                        .placeholder(R.drawable.img_user) // Optional placeholder image while loading
//                .error(R.drawable.error) // Optional error image if loading fails
                                        .into(img_userimage);
                            } else {
                                Toast.makeText(profile_page.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {

                        }
//
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        // Display an error message or retry request
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Set the token in the headers
                SharedPreferences sharedPreferences =
                        getSharedPreferences("MyData", MODE_PRIVATE);
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + sharedPreferences.getString("Login_Token", ""));
                return headers;
            }
        };

// Add the request to the Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void Verification() {


        tvRegSeedPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSelect.setVisibility(View.VISIBLE);
                imgSelect1.setVisibility(View.GONE);
                imgNotSelect.setVisibility(View.GONE);
                imgNotSelect1.setVisibility(View.VISIBLE);
                check = "12a";
            }
        });
        tvRegChawki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgNotSelect.setVisibility(View.VISIBLE);
                imgNotSelect1.setVisibility(View.GONE);
                imgSelect.setVisibility(View.GONE);
                imgSelect1.setVisibility(View.VISIBLE);
                check = "12b";
            }
        });

    }

    public void AlertDialog() {


        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(R.drawable.logo)
//set title
                .setTitle("Exit")
//set message
                .setMessage("Do you want to Exit?")

//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                        getApplicationContext();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyData",
                                MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.clear();
                        myEdit.apply();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })

//set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                    }
                })
                .show();
    }




    @Override
    protected void onResume() {
        super.onResume();
        getProfileDataOnResume();
    }


}


