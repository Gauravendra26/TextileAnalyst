package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seed_Officers extends AppCompatActivity implements Adapter_Offcers.ProductClick {

    RelativeLayout rlView1, rlView2, rlView3, rlView4, rlView5, rlView6, rlView7, rlView8, rlBack;
    String check,combinedString;
    TextView tvday;
    Adapter_Offcers adapter_offcers;
    SwipeRefreshLayout refreshLayout;
    List<Model_Officers> model_officers;
    RecyclerView rvOfficers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_officers);
        Utils.blackIconStatusBar(Seed_Officers.this, R.color.white);
        init();

        Intent intent = getIntent();
        check = intent.getStringExtra("check");
        combinedString = intent.getStringExtra("combinedString");
        tvday.setText(combinedString);
//        Toast.makeText(this, "" + check, Toast.LENGTH_SHORT).show();
        getOfficersData();
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getOfficersData();
                        refreshLayout.setRefreshing(false);
                    }
                }
        );
        rlView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profile_page.class);
                intent.putExtra("check", check);

                startActivity(intent);
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlBack.startAnimation(myAnim);

            }
        });


    }

    void init() {

        tvday = findViewById(R.id.tvday);
        rlView1 = findViewById(R.id.rlView1);
        rlView2 = findViewById(R.id.rlView2);
        rlView3 = findViewById(R.id.rlView3);
        rlView4 = findViewById(R.id.rlView4);
        rlView5 = findViewById(R.id.rlView5);
        rlView6 = findViewById(R.id.rlView6);
        rlView7 = findViewById(R.id.rlView7);
        rlView8 = findViewById(R.id.rlView8);
        rlBack = findViewById(R.id.rlBack);
        rvOfficers = findViewById(R.id.rvOfficers);
        refreshLayout = findViewById(R.id.refreshLayout);
    }

    void getOfficersData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.new_progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


// Create a new request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                ApiData.analystOfficers, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            if (response.getBoolean("status") == true) {
                                model_officers = new ArrayList<Model_Officers>();

                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    Log.e("apiresponse", String.valueOf(user));

                                    int seed_analyst_seed_office_map_id = user.optInt("seed_analyst_seed_office_map_id", 0);
                                    int seedanalyst_id = user.optInt("seedanalyst_id", 0);
                                    int seedofficer_id = user.optInt("seedofficer_id", 0);
                                    int user_id = user.optInt("user_id", 0);
                                    int user_role = user.optInt("user_role", 0);
                                    int user_status = user.optInt("user_status", 0);
                                    String user_name = user.optString("user_name");
                                    String user_phone = user.optString("user_phone");
                                    String email = user.optString("email");
                                    String user_empid = user.optString("user_empid");
                                    String user_image = user.optString("user_image");
                                    String created_at = user.optString("created_at");
                                    String updated_at = user.optString("updated_at");
                                    model_officers.add(new Model_Officers(seed_analyst_seed_office_map_id, seedanalyst_id,
                                            seedofficer_id, user_id, user_role, user_status, user_name,
                                            user_phone, email, user_empid, user_image, created_at, updated_at));

                                }


                                Collections.reverse(model_officers);

                                adapter_offcers = new Adapter_Offcers(getApplicationContext(), model_officers);
                                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.VERTICAL, true);
                                layoutManager1.setStackFromEnd(true);
                                layoutManager1.setReverseLayout(true);

                                rvOfficers.setLayoutManager(layoutManager1);

                                rvOfficers.setHasFixedSize(true);
                                rvOfficers.setItemAnimator(new DefaultItemAnimator());
                                rvOfficers.setAdapter(adapter_offcers);
                                adapter_offcers.set(Seed_Officers.this);

                            } else {
                                Toast.makeText(Seed_Officers.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

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



    void getOfficersDataOnresume() {


// Create a new request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                ApiData.analystOfficers, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             if (response.getBoolean("status") == true) {
                                model_officers = new ArrayList<Model_Officers>();

                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    Log.e("apiresponse", String.valueOf(user));

                                    int seed_analyst_seed_office_map_id = user.optInt("seed_analyst_seed_office_map_id", 0);
                                    int seedanalyst_id = user.optInt("seedanalyst_id", 0);
                                    int seedofficer_id = user.optInt("seedofficer_id", 0);
                                    int user_id = user.optInt("user_id", 0);
                                    int user_role = user.optInt("user_role", 0);
                                    int user_status = user.optInt("user_status", 0);
                                    String user_name = user.optString("user_name");
                                    String user_phone = user.optString("user_phone");
                                    String email = user.optString("email");
                                    String user_empid = user.optString("user_empid");
                                    String user_image = user.optString("user_image");
                                    String created_at = user.optString("created_at");
                                    String updated_at = user.optString("updated_at");
                                    model_officers.add(new Model_Officers(seed_analyst_seed_office_map_id, seedanalyst_id,
                                            seedofficer_id, user_id, user_role, user_status, user_name,
                                            user_phone, email, user_empid, user_image, created_at, updated_at));

                                }


                                Collections.reverse(model_officers);

                                adapter_offcers = new Adapter_Offcers(getApplicationContext(), model_officers);
                                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.VERTICAL, true);
                                layoutManager1.setStackFromEnd(true);

                                rvOfficers.setLayoutManager(layoutManager1);

                                rvOfficers.setHasFixedSize(true);
                                rvOfficers.setItemAnimator(new DefaultItemAnimator());
                                rvOfficers.setAdapter(adapter_offcers);
                                adapter_offcers.set(Seed_Officers.this);

                            } else {
                                Toast.makeText(Seed_Officers.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

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


    @Override
    public void officersClick(int positionint, int seed_analyst_seed_office_map_id, int seedanalyst_id,
                              int seedofficer_id, int user_id, int user_role, int user_status, String user_name,
                              String user_phone, String email, String user_empid, String user_image,
                              String created_at, String updated_at) {
        Intent intent = new Intent(getApplicationContext(), SelectRCRList.class);
        intent.putExtra("check", check);
        intent.putExtra("seed_officer_id", seedofficer_id);
        intent.putExtra("combinedString", combinedString);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOfficersDataOnresume();
    }

}