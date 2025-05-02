package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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

public class SelectRCRList extends AppCompatActivity implements Adapter_RCR.ProductClick, Adapter_RCR_12a.ProductClick {
    RelativeLayout rlView1, rlView2, rlView3, rlView4, rlView6, rlView8, rlView5, rlView7, rlBack;
    int seed_officer_id;
    String check,combinedString;
    TextView tvday,tvProfile;
    RecyclerView rvRCRlist;
    SwipeRefreshLayout refreshLayout;
    List<Model_RCR> model_rcr;
    List<Model_RCR_12a> model_rcr_12a;
    Adapter_RCR adapter_rcr;
    Adapter_RCR_12a adapter_rcr_12a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rcrlist);
        Utils.blackIconStatusBar(SelectRCRList.this, R.color.white);

        init();

        Intent intent = getIntent();
        check = intent.getStringExtra("check");
        combinedString = intent.getStringExtra("combinedString");
        seed_officer_id = intent.getIntExtra("seed_officer_id", seed_officer_id);
//        Toast.makeText(this, "" + check + " " + seed_officer_id, Toast.LENGTH_SHORT).show();
        tvday.setText(combinedString);
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (check.equals("12a")) {
                            getOfficersJobfor12a(seed_officer_id);
                        } else {
                            getOfficersJob( );
                        }
                        refreshLayout.setRefreshing(false);
                    }
                }
        );

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlBack.startAnimation(myAnim);

            }
        });
        rlView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Details_page.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Details_page.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Details_page.class);
                intent.putExtra("check", check);

                startActivity(intent);

            }
        });
        rlView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == "12b") {
                    Intent intent = new Intent(getApplicationContext(), Account_details_ChawkiRearer_Activity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getApplicationContext(), Account_Details.class);
                    startActivity(intent);
                }


            }
        });

        rlView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == "12b") {
                    Intent intent = new Intent(getApplicationContext(), Account_details_ChawkiRearer_Activity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getApplicationContext(), Account_Details.class);
                    startActivity(intent);
                }
            }
        });

        rlView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Preview_page.class);
                startActivity(intent);

            }
        });

        rlView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Preview_page.class);
                startActivity(intent);

            }
        });

        rlView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Preview_page.class);
                startActivity(intent);

            }
        });

        if (check.equals("12a")) {
            getOfficersJobfor12a(seed_officer_id);
//            Toast.makeText(this, "12a", Toast.LENGTH_SHORT).show();

            tvProfile.setText("Select RSP List");

        } else {
            getOfficersJob( );
//            Toast.makeText(this, "12b", Toast.LENGTH_SHORT).show();

            tvProfile.setText("Select RCR List");

        }


    }

    void init() {

        tvday = findViewById(R.id.tvday);
        tvProfile = findViewById(R.id.tvProfile);
        rlBack = findViewById(R.id.rlBack);
        rlView1 = findViewById(R.id.rlView1);
        rlView2 = findViewById(R.id.rlView2);
        rlView3 = findViewById(R.id.rlView3);
        rlView4 = findViewById(R.id.rlView4);
        rlView5 = findViewById(R.id.rlView5);
        rlView6 = findViewById(R.id.rlView6);
        rlView7 = findViewById(R.id.rlView7);
        rlView8 = findViewById(R.id.rlView8);
        rvRCRlist = findViewById(R.id.rvRCRlist);
        refreshLayout = findViewById(R.id.refreshLayout);

    }

    void getOfficersJob( ) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.new_progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        JSONObject requestBody = new JSONObject();
        try {

            requestBody.put("form_type", "12b");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiData.analystjobsbyofficers, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            if (response.getBoolean("status") == true) {
                                Log.e("apirespons1", String.valueOf(response));
                                model_rcr = new ArrayList<Model_RCR>();
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    Log.e("apirespons", String.valueOf(user));

                                    int sr_id = user.optInt("sr_id", 0);
                                    int sr_status = user.optInt("sr_status", 0);
                                    int status = user.optInt("status", 0);
                                    int verification_id = user.optInt("verification_id", 0);
                                    int verification_status = user.optInt("verification_status", 0);
                                    String sr_registration_no = user.optString("sr_registration_no");
                                    String sr_sirname = user.optString("sr_sirname");
                                    String sr_applicant = user.optString("sr_applicant");
                                    String sr_behalf_of = user.optString("sr_behalf_of");
                                    String sr_parent_sirname = user.optString("sr_parent_sirname");
                                    String sr_parentsname = user.optString("sr_parentsname");
                                    String sr_gender = user.optString("sr_gender");
                                    String sr_social_status = user.optString("sr_social_status");
                                    String sr_training_certificate = user.optString("sr_training_certificate");
                                    String sr_training_place = user.optString("sr_training_place");
                                    String sr_training_from = user.optString("sr_training_from");
                                    String sr_training_to = user.optString("sp_training_to");
                                    String sr_educational_certificate = user.optString("sr_educational_certificate");
                                    String sr_passport_photo = user.optString("sr_passport_photo");
                                    String sr_aadhaar_number = user.optString("sr_aadhaar_number");
                                    String sr_country_code = user.optString("sr_country_code");
                                    String sr_mobile = user.optString("sr_mobile");
                                    String sr_applicant_type = user.optString("sr_applicant_type");
                                    String sr_unit_name = user.optString("sr_unit_name");
                                    String sr_village = user.optString("sr_village");
                                    String sr_taluk = user.optString("sr_taluk");
                                    String sr_state = user.optString("sr_state");
                                    String sr_district = user.optString("sr_district");
                                    String sr_pin = user.optString("sr_pin");
                                    String sr_address = user.optString("sr_address");
                                    String sr_production_center = user.optString("sr_production_center");
                                    String sr_production_center_taluk = user.optString("sr_production_center_taluk");
                                    String sr_production_center_state = user.optString("sr_production_center_state");
                                    String sr_production_center_district = user.optString("sr_production_center_district");
                                    String sr_production_center_pin = user.optString("sr_production_center_pin");
                                    String sr_production_center_sector = user.optString("sr_production_center_sector");
                                    String sr_production_center_address = user.optString("sr_production_center_address");
                                    String sr_produced = user.optString("sr_produced");
                                    String sr_unit = user.optString("sr_unit");
                                    String sr_production_level = user.optString("sr_production_level");
                                    String sr_ownership_type = user.optString("sr_ownership_type");
                                    String sr_establishment = user.optString("sr_establishment");
                                    String sr_other_details = user.optString("sr_other_details");
                                    String sr_quantity_silkworm = user.optString("sr_quantity_silkworm");
                                    String sr_quantity_silkworm2 = user.optString("sr_quantity_silkworm2");
                                    String sr_workers = user.optString("sr_workers");
                                    String sr_unit_two = user.optString("sr_unit_two");
                                    String sr_page1 = user.optString("sr_page1");
                                    String sr_page2 = user.optString("sr_page2");
                                    String sr_page3 = user.optString("sr_page3");
                                    String sr_page4 = user.optString("sr_page4");
                                    String sr_page5 = user.optString("sr_page5");
                                    String sr_page6 = user.optString("sr_page6");
                                    String sr_sign = user.optString("sr_sign");
                                    String sr_remark = user.optString("sr_remark");
                                    String created_at = user.optString("created_at");
                                    String updated_at = user.optString("updated_at");
                                    String name = user.optString("name");
                                    String
                                            nameOfTheRsp = null,
                                            registration_no = null,
                                            registered_address = null,
                                            contact_no = null,
                                            DisplayedRegistrationCertificateProminently = null,
                                            mulberry_area_acre = null,
                                            regularity_of_soil_testing_once_in_2_years = null,
                                            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = null,
                                            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = null,
                                            mulberry_variety = null,
                                            pest_disease_freeness_in_garden = null,
                                            nutrient_deficiency_symptoms_in_leaves = null,
                                            average_single_leaf_weight_g = null,
                                            irrigation_facility = null,
                                            overall_status = null,
                                            incubation_facility = null,
                                            whether_rearing_house_is_as_per_recommended_plan = null,
                                            whether_equipments_appliances_as_per_seed_act_guidelines_available = null,
                                            visual_health_of_chawki_worms = null,
                                            wt_of_100_ii_instar_larvae_g = null,
                                            bed_spacing = null,
                                            maintenance_of_temp_rh = null,
                                            no_of_batches_detected = null,
                                            lot_details_of_source_dfls = null,
                                            skilled_person_for_testing = null,
                                            disinfection_register = null,
                                            dfl_procurement_register = null,
                                            rearing_performance_register = null,
                                            pebrine_testing_register = null,
                                            chawki_supply_register = null,
                                            farm_management_register = null,
                                            temp_rh_maintenance_register = null,
                                            bill_book = null,
                                            whether_following_self_certification_for_each_supply_lot = null,
                                            image = null,
                                            extra1 = null,
                                            extra2 = null,
                                            extra3 = null,
                                            extra4 = null;

                                    Object verificationDataObj = user.get("verification_data");
                                    if (verificationDataObj instanceof Boolean) {
                                        boolean verificationData = (boolean) verificationDataObj;


                                        nameOfTheRsp = null;
                                        registration_no = null;
                                        registered_address = null;
                                        contact_no = null;
                                        DisplayedRegistrationCertificateProminently = null;
                                        mulberry_area_acre = null;
                                        regularity_of_soil_testing_once_in_2_years = null;
                                        recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = null;
                                        is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = null;
                                        mulberry_variety = null;
                                        pest_disease_freeness_in_garden = null;
                                        nutrient_deficiency_symptoms_in_leaves = null;
                                        average_single_leaf_weight_g = null;
                                        irrigation_facility = null;
                                        overall_status = null;
                                        incubation_facility = null;
                                        whether_rearing_house_is_as_per_recommended_plan = null;
                                        whether_equipments_appliances_as_per_seed_act_guidelines_available = null;
                                        visual_health_of_chawki_worms = null;
                                        wt_of_100_ii_instar_larvae_g = null;
                                        bed_spacing = null;
                                        maintenance_of_temp_rh = null;
                                        no_of_batches_detected = null;
                                        lot_details_of_source_dfls = null;
                                        skilled_person_for_testing = null;
                                        disinfection_register = null;
                                        dfl_procurement_register = null;
                                        rearing_performance_register = null;
                                        pebrine_testing_register = null;
                                        chawki_supply_register = null;
                                        farm_management_register = null;
                                        temp_rh_maintenance_register = null;
                                        bill_book = null;
                                        whether_following_self_certification_for_each_supply_lot = null;
                                        image = null;
                                        extra1 = null;
                                        extra2 = null;
                                        extra3 = null;
                                        extra4 = null;

                                        // ... (set default values or handle other fields related to verification_data)
                                    } else if (verificationDataObj instanceof JSONArray) {
                                        // Handle the case where verification_data is an array of data
                                        JSONArray verificationDataArray = (JSONArray) verificationDataObj;
                                        // Parse the data in the array as needed
                                        if (verificationDataArray.length() > 0) {
                                            JSONObject verificationData = verificationDataArray.getJSONObject(0);
                                            nameOfTheRsp = verificationData.optString("name_of_the_rcr");
                                            registration_no = verificationData.optString("registration_no");
                                            registered_address = verificationData.optString("registered_address");
                                            contact_no = verificationData.optString("contact_no");
                                            DisplayedRegistrationCertificateProminently = verificationData.optString("displayed_registration_certificate_prominently");
                                            mulberry_area_acre = verificationData.optString("mulberry_area_(acre)");
                                            regularity_of_soil_testing_once_in_2_years = verificationData.optString("regularity_of_soil_testing_once_in_2_years");
                                            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = verificationData.optString("recommended_application_of_soil_inputs_(fertilizers_&_fym)_after_each_crop");
                                            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = verificationData.optString("is_pruning_&_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum");
                                            mulberry_variety = verificationData.optString("mulberry_variety");
                                            pest_disease_freeness_in_garden = verificationData.optString("pest_&_disease_freeness_in_garden");
                                            nutrient_deficiency_symptoms_in_leaves = verificationData.optString("nutrient_deficiency_symptoms_in_leaves");
                                            average_single_leaf_weight_g = verificationData.optString("average_single_leaf_weight_(g)");
                                            irrigation_facility = verificationData.optString("irrigation_facility");
                                            overall_status = verificationData.optString("overall_status");
                                            incubation_facility = verificationData.optString("incubation_facility");
                                            whether_rearing_house_is_as_per_recommended_plan = verificationData.optString("whether_rearing_house_is_as_per_recommended_plan");
                                            whether_equipments_appliances_as_per_seed_act_guidelines_available = verificationData.optString("whether_equipments_appliances_as_per_seed_act_guidelines_available");
                                            visual_health_of_chawki_worms = verificationData.optString("visual_health_of_chawki_worms");
                                            wt_of_100_ii_instar_larvae_g = verificationData.optString("wt_of_100_ii_instar_larvae_(g)");
                                            bed_spacing = verificationData.optString("bed_spacing");
                                            maintenance_of_temp_rh = verificationData.optString("maintenance_of_temp_&_rh");
                                            no_of_batches_detected = verificationData.optString("no._of_batches_detected");
                                            lot_details_of_source_dfls = verificationData.optString("lot_details_of_source_dfls");
                                            skilled_person_for_testing = verificationData.optString("skilled_person_for_testing");
                                            disinfection_register = verificationData.optString("disinfection_register");
                                            dfl_procurement_register = verificationData.optString("dfl_procurement_register");
                                            rearing_performance_register = verificationData.optString("rearing_performance_register");
                                            pebrine_testing_register = verificationData.optString("pebrine_testing_register");
                                            chawki_supply_register = verificationData.optString("chawki_supply_register");
                                            farm_management_register = verificationData.optString("farm_management_register");
                                            temp_rh_maintenance_register = verificationData.optString("temp_&_rh_maintenance_register");
                                            bill_book = verificationData.optString("bill_book");
                                            whether_following_self_certification_for_each_supply_lot = verificationData.optString("whether_following_self-certification_for_each_supply_lot");
                                            image = verificationData.optString("image");
                                            extra1 = verificationData.optString("extra1");
                                            extra2 = verificationData.optString("extra2");
                                            extra3 = verificationData.optString("extra3");
                                            extra4 = verificationData.optString("extra4");
                                            Log.e("checloooo",registration_no);
                                        }
                                    }




                                    model_rcr.add(new Model_RCR(sr_id, sr_status, status, verification_id,
                                            verification_status,
                                            sr_registration_no, sr_sirname, sr_applicant, sr_behalf_of,
                                            sr_parent_sirname,
                                            sr_parentsname, sr_gender, sr_social_status, sr_training_certificate,
                                            sr_training_place, sr_training_from, sr_training_to,
                                            sr_educational_certificate,
                                            sr_passport_photo, sr_aadhaar_number, sr_country_code, sr_mobile,
                                            sr_applicant_type, sr_unit_name, sr_village, sr_taluk, sr_state,
                                            sr_district, sr_pin, sr_address, sr_production_center,
                                            sr_production_center_taluk, sr_production_center_state,
                                            sr_production_center_district, sr_production_center_pin,
                                            sr_production_center_sector, sr_production_center_address, sr_produced,
                                            sr_unit, sr_production_level, sr_ownership_type, sr_establishment,
                                            sr_other_details, sr_quantity_silkworm, sr_quantity_silkworm2, sr_workers,
                                            sr_unit_two, sr_page1, sr_page2, sr_page3, sr_page4,
                                            sr_page5, sr_page6, sr_sign, sr_remark, created_at, updated_at,name
                                            , nameOfTheRsp,
                                            registration_no ,
                                            registered_address ,
                                            contact_no ,
                                            DisplayedRegistrationCertificateProminently ,
                                            mulberry_area_acre ,
                                            regularity_of_soil_testing_once_in_2_years,
                                            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop ,
                                            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum ,
                                            mulberry_variety  ,
                                            pest_disease_freeness_in_garden  ,
                                            nutrient_deficiency_symptoms_in_leaves  ,
                                            average_single_leaf_weight_g  ,
                                            irrigation_facility ,
                                            overall_status  ,
                                            incubation_facility  ,
                                            whether_rearing_house_is_as_per_recommended_plan  ,
                                            whether_equipments_appliances_as_per_seed_act_guidelines_available ,
                                            visual_health_of_chawki_worms  ,
                                            wt_of_100_ii_instar_larvae_g  ,
                                            bed_spacing  ,
                                            maintenance_of_temp_rh ,
                                            no_of_batches_detected  ,
                                            lot_details_of_source_dfls  ,
                                            skilled_person_for_testing ,
                                            disinfection_register  ,
                                            dfl_procurement_register  ,
                                            rearing_performance_register  ,
                                            pebrine_testing_register  ,
                                            chawki_supply_register  ,
                                            farm_management_register  ,
                                            temp_rh_maintenance_register  ,
                                            bill_book ,
                                            whether_following_self_certification_for_each_supply_lot  ,
                                            image  ,
                                            extra1 ,
                                            extra2 ,
                                            extra3,extra4 ));

                                    Log.e("modelData", String.valueOf(model_rcr));
                                }
                                Collections.reverse(model_rcr);

                                adapter_rcr = new Adapter_RCR(getApplicationContext(), model_rcr);
                                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.VERTICAL, true);
                                layoutManager1.setStackFromEnd(true);
                                 layoutManager1.setReverseLayout(true);

                                rvRCRlist.setLayoutManager(layoutManager1);
                                rvRCRlist.setHasFixedSize(true);
                                rvRCRlist.setItemAnimator(new DefaultItemAnimator());
                                rvRCRlist.setAdapter(adapter_rcr);
                                adapter_rcr.set(SelectRCRList.this);

                            } else {
                                Toast.makeText(SelectRCRList.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    void getOfficersJobfor12a(int seed_officer_id) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.new_progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("seed_officer_id", seed_officer_id);
            requestBody.put("form_type", "12a");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiData.analystjobsbyofficers, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            if (response.getBoolean("status") == true) {
                                Log.e("apirespons1", String.valueOf(response));
                                model_rcr_12a = new ArrayList<Model_RCR_12a>();
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    Log.e("apirespons", String.valueOf(user));

                                    int sp_id = user.optInt("sp_id", 0);
                                    int sp_status = user.optInt("sp_status", 0);
                                    int status = user.optInt("status", 0);
                                    int verification_id = user.optInt("verification_id", 0);
                                    int verification_status = user.optInt("verification_status", 0);
                                    String sp_registration_no = user.optString("sp_registration_no");
                                    String sp_sirname = user.optString("sp_sirname");
                                    String sp_applicant = user.optString("sp_applicant");
                                    String sp_behalf_of = user.optString("sp_behalf_of");
                                    String sp_parent_sirname = user.optString("sp_parent_sirname");
                                    String sp_parentsname = user.optString("sp_parentsname");
                                    String sp_gender = user.optString("sp_gender");
                                    String sp_social_status = user.optString("sp_social_status");
                                    String sp_training_certificate = user.optString("sp_training_certificate");
                                    String sp_training_place = user.optString("sp_training_place");
                                    String sp_training_from = user.optString("sp_training_from");
                                    String sp_training_to = user.optString("sp_training_to");
                                    String sp_educational_certificate = user.optString("sp_educational_certificate");
                                    String sp_passport_photo = user.optString("sp_passport_photo");
                                    String sp_aadhaar_number = user.optString("sp_aadhaar_number");
                                    String sp_country_code = user.optString("sp_country_code");
                                    String sp_mobile = user.optString("sp_mobile");
                                    String sp_applicant_type = user.optString("sp_applicant_type");
                                    String sp_unit_name = user.optString("sp_unit_name");
                                    String sp_village = user.optString("sp_village");
                                    String sp_taluk = user.optString("sp_taluk");
                                    String sp_state = user.optString("sp_state");
                                    String sp_district = user.optString("sp_district");
                                    String sp_pin = user.optString("sp_pin");
                                    String sp_address = user.optString("sp_address");
                                    String sp_production_center = user.optString("sp_production_center");
                                    String sp_production_center_taluk = user.optString("sp_production_center_taluk");
                                    String sp_production_center_state = user.optString("sp_production_center_state");
                                    String sp_production_center_district = user.optString("sp_production_center_district");
                                    String sp_production_center_pin = user.optString("sp_production_center_pin");
                                    String sp_production_center_sector = user.optString("sp_production_center_sector");
                                    String sp_production_center_address = user.optString("sp_production_center_address");
                                    String sp_produced = user.optString("sp_produced");
                                    String sp_unit = user.optString("sp_unit");
                                    String sp_production_level = user.optString("sp_production_level");
                                    String sp_ownership_type = user.optString("sp_ownership_type");
                                    String sp_establishment = user.optString("sp_establishment");
                                    String sp_other_details = user.optString("sp_other_details");
                                    String sp_quantity_silkworm = user.optString("sp_quantity_silkworm");
                                    String sp_quantity_silkworm2 = user.optString("sp_quantity_silkworm2");
                                    String sp_workers = user.optString("sp_workers");
                                    String sp_unit_two = user.optString("sp_unit_two");
                                    String sp_page1 = user.optString("sp_page1");
                                    String sp_page2 = user.optString("sp_page2");
                                    String sp_page3 = user.optString("sp_page3");
                                    String sp_page4 = user.optString("sp_page4");
                                    String sp_page5 = user.optString("sp_page5");
                                    String sp_page6 = user.optString("sp_page6");
                                    String sp_sign = user.optString("sp_sign");
                                    String sp_remark = user.optString("sp_remark");
                                    String created_at = user.optString("created_at");
                                    String updated_at = user.optString("updated_at");
                                    String name = user.optString("name");
                                    String   nameOfTheRsp = null, registration_no = null, registered_address = null,
                                            contact_no = null, displayed_ther_registration_certificate_prominently = null,
                                            number_of_regd_seed_cocoon_producers_with_rsp = null, whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = null,
                                            whether_seed_cocoons_are_purchased_as_per_norms = null,
                                            whether_seed_cocoons_are_properly_sorted_and_sex_separated = null,
                                            whether_pupal_testing_is_done_for_pebrine = null, sex_separation_efficiency = null,
                                            male_female_ratio = null, whether_each_parental_pupae_are_kept_in_separate_room = null,
                                            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = null, whether_seeds_are_properly_washed_dried_and_packed_as_recommended = null, whether_male_moths_are_preserved_in_cold_room = null, whether_cold_rooms_have_uninterrupted_power_supply = null, whether_proper_temp_humidity_are_maintained_for_oviposition = null, whether_incubation_chamber_available = null, whether_seeds_are_properly_labeled_for_race_lot_no_dol = null, no_of_batches_detected = null, lot_details_of_source_seed_cocoon = null, skilled_person_for_testing = null, disinfection_register = null, seed_cocoon_procurement_register = null, cocoon_processing_register = null, pebrine_testing_register = null, dfl_supply_register = null, test_hatching_register_for_dfls_supplied = null,
                                            temp_rh_maintenance_register = null, bill_book_for_dfl_supply = null, whether_following_self_certification_for_each_supply_lot = null, whether_quarterly_reports_are_being_regularly_submitted_to_nsso = null, image = null, extra1 = null, extra2 = null, extra3 = null, extra4 = null;
                                    Object verificationDataObj = user.get("verification_data");
                                    if (verificationDataObj instanceof Boolean) {
                                        boolean verificationData = (boolean) verificationDataObj;
                                        Log.e("cgechoutput1",   nameOfTheRsp + " " + registration_no + " " + registered_address + " " + contact_no
                                                + " " + displayed_ther_registration_certificate_prominently + " " + number_of_regd_seed_cocoon_producers_with_rsp
                                                + " " + whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop
                                                + " " + whether_seed_cocoons_are_purchased_as_per_norms + " " + whether_seed_cocoons_are_properly_sorted_and_sex_separated
                                                + " " + whether_pupal_testing_is_done_for_pebrine + " " + sex_separation_efficiency + " " + male_female_ratio
                                                + " " + whether_each_parental_pupae_are_kept_in_separate_room + " " + whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms
                                                + " " + whether_seeds_are_properly_washed_dried_and_packed_as_recommended + " " + whether_male_moths_are_preserved_in_cold_room
                                                + " " + whether_cold_rooms_have_uninterrupted_power_supply + " " + whether_proper_temp_humidity_are_maintained_for_oviposition
                                                + " " + whether_incubation_chamber_available + " " + whether_seeds_are_properly_labeled_for_race_lot_no_dol
                                                + " " + no_of_batches_detected + " " + lot_details_of_source_seed_cocoon + " " + skilled_person_for_testing
                                                + " " + disinfection_register + " " + seed_cocoon_procurement_register + " " + cocoon_processing_register
                                                + " " + pebrine_testing_register + " " + dfl_supply_register + " " + test_hatching_register_for_dfls_supplied
                                                + " " + temp_rh_maintenance_register + " " + bill_book_for_dfl_supply + " " + whether_following_self_certification_for_each_supply_lot
                                                + " " + whether_quarterly_reports_are_being_regularly_submitted_to_nsso + " " + image + " " + extra1
                                                + " " + extra2 + " " + extra3+ " " + extra4);
                                         nameOfTheRsp = null;
                                        registration_no = null;
                                        registered_address = null;
                                        contact_no = null;
                                        displayed_ther_registration_certificate_prominently = null;
                                        number_of_regd_seed_cocoon_producers_with_rsp = null;
                                        whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = null;
                                        whether_seed_cocoons_are_purchased_as_per_norms = null;
                                        whether_seed_cocoons_are_properly_sorted_and_sex_separated = null;
                                        whether_pupal_testing_is_done_for_pebrine = null;
                                        sex_separation_efficiency = null;
                                        male_female_ratio = null;
                                        whether_each_parental_pupae_are_kept_in_separate_room = null;
                                        whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = null;
                                        whether_seeds_are_properly_washed_dried_and_packed_as_recommended = null;
                                        whether_male_moths_are_preserved_in_cold_room = null;
                                        whether_cold_rooms_have_uninterrupted_power_supply = null;
                                        whether_proper_temp_humidity_are_maintained_for_oviposition = null;
                                        whether_incubation_chamber_available = null;
                                        whether_seeds_are_properly_labeled_for_race_lot_no_dol = null;
                                        no_of_batches_detected = null;
                                        lot_details_of_source_seed_cocoon = null;
                                        skilled_person_for_testing = null;
                                        disinfection_register = null;
                                        seed_cocoon_procurement_register = null;
                                        cocoon_processing_register = null;
                                        pebrine_testing_register = null;
                                        dfl_supply_register = null;
                                        test_hatching_register_for_dfls_supplied = null;
                                        temp_rh_maintenance_register = null;
                                        bill_book_for_dfl_supply = null;
                                        whether_following_self_certification_for_each_supply_lot = null;
                                        whether_quarterly_reports_are_being_regularly_submitted_to_nsso = null;
                                        image = null;
                                        extra1 = null;
                                        extra2 = null;
                                        extra3 = null;
                                        extra4 = null;
                                        // ... (set default values or handle other fields related to verification_data)
                                    } else if (verificationDataObj instanceof JSONArray) {
                                        // Handle the case where verification_data is an array of data
                                        JSONArray verificationDataArray = (JSONArray) verificationDataObj;
                                        // Parse the data in the array as needed
                                        if (verificationDataArray.length() > 0) {
                                            JSONObject verificationData = verificationDataArray.getJSONObject(0);
                                             nameOfTheRsp = verificationData.optString("name_of_the_rsp");
                                            registration_no = verificationData.optString("registration_no.");
                                            registered_address = verificationData.optString("registered_address");
                                            contact_no = verificationData.optString("contact_no");
                                            displayed_ther_registration_certificate_prominently = verificationData.optString("displayed_registration_certificate_prominently");
                                            number_of_regd_seed_cocoon_producers_with_rsp = verificationData.optString("number_of_regd._seed_cocoon_producers_with_rsp");
                                            whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = verificationData.optString("whether_seed_cocoons_are_purchased_from_regd._seed_cocoon_producers_for_every_crop");
                                            whether_seed_cocoons_are_purchased_as_per_norms = verificationData.optString("whether_seed_cocoons_are_purchased_as_per_norms");
                                            whether_seed_cocoons_are_properly_sorted_and_sex_separated = verificationData.optString("whether_seed_cocoons_are_properly_sorted_and_sex_separated");
                                            whether_pupal_testing_is_done_for_pebrine = verificationData.optString("whether_pupal_testing_is_done_for_pebrine");
                                            sex_separation_efficiency = verificationData.optString("sex-separation_efficiency");
                                            male_female_ratio = verificationData.optString("male:_female_ratio");
                                            whether_each_parental_pupae_are_kept_in_separate_room = verificationData.optString("whether_each_parental_pupae_are_kept_in_separate_room");
                                            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = verificationData.optString("whether_proper_temp_&_rh_maintained_in_cocoon_pupae_&_oviposition_rooms");
                                            whether_seeds_are_properly_washed_dried_and_packed_as_recommended = verificationData.optString("whether_seeds_are_properly_washed,_dried_and_packed_as_recommended");
                                            whether_male_moths_are_preserved_in_cold_room = verificationData.optString("whether_male_moths_are_preserved_in_5-7_cold_room");
                                            whether_cold_rooms_have_uninterrupted_power_supply = verificationData.optString("whether_cold_rooms_have_uninterrupted_power_supply");
                                            whether_proper_temp_humidity_are_maintained_for_oviposition = verificationData.optString("whether_proper_temp_&_humidity_are_maintained_for_oviposition");
                                            whether_incubation_chamber_available = verificationData.optString("whether_incubation_chamber_available");
                                            whether_seeds_are_properly_labeled_for_race_lot_no_dol = verificationData.optString("whether_seeds_are_properly_labeled_for_race,_lot_no,_dol");
                                            no_of_batches_detected = verificationData.optString("no._of_batches_detected");
                                            lot_details_of_source_seed_cocoon = verificationData.optString("lot_details_of_source_seed_cocoon");
                                            skilled_person_for_testing = verificationData.optString("skilled_person_for_testing");
                                            disinfection_register = verificationData.optString("disinfection_register");
                                            seed_cocoon_procurement_register = verificationData.optString("seed_cocoon_procurement_register");
                                            cocoon_processing_register = verificationData.optString("cocoon_processing_register");
                                            pebrine_testing_register = verificationData.optString("pebrine_testing_register");
                                            dfl_supply_register = verificationData.optString("dfl_supply_register");
                                            test_hatching_register_for_dfls_supplied = verificationData.optString("test_hatching_register_for_dfls_supplied");
                                            temp_rh_maintenance_register = verificationData.optString("temp._&_rh_maintenance_register");
                                            bill_book_for_dfl_supply = verificationData.optString("bill_book_for_dfl_supply");
                                            whether_following_self_certification_for_each_supply_lot = verificationData.optString("whether_following_self-certification_for_each_supply_lot");
                                            whether_quarterly_reports_are_being_regularly_submitted_to_nsso = verificationData.optString("whether_quarterly_reports_are_being_regularly_submitted_to_nsso");
                                            image = verificationData.optString("image");
                                            extra1 = verificationData.optString("extra1");
                                            extra2 = verificationData.optString("extra2");
                                            extra3 = verificationData.optString("extra3");
                                            extra4 = verificationData.optString("extra4");
                                            Log.e("cgechoutput",  nameOfTheRsp + " " + registration_no + "re " + registered_address + " " + contact_no
                                                    + " " + displayed_ther_registration_certificate_prominently + " " + number_of_regd_seed_cocoon_producers_with_rsp
                                                    + " " + whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop
                                                    + " " + whether_seed_cocoons_are_purchased_as_per_norms + " " + whether_seed_cocoons_are_properly_sorted_and_sex_separated
                                                    + " " + whether_pupal_testing_is_done_for_pebrine + " " + sex_separation_efficiency + " " + male_female_ratio
                                                    + " " + whether_each_parental_pupae_are_kept_in_separate_room + " " + whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms
                                                    + " " + whether_seeds_are_properly_washed_dried_and_packed_as_recommended + " " + whether_male_moths_are_preserved_in_cold_room
                                                    + " " + whether_cold_rooms_have_uninterrupted_power_supply + " " + whether_proper_temp_humidity_are_maintained_for_oviposition
                                                    + " " + whether_incubation_chamber_available + " " + whether_seeds_are_properly_labeled_for_race_lot_no_dol
                                                    + " " + no_of_batches_detected + " " + lot_details_of_source_seed_cocoon + " " + skilled_person_for_testing
                                                    + " " + disinfection_register + " " + seed_cocoon_procurement_register + " " + cocoon_processing_register
                                                    + " " + pebrine_testing_register + " " + dfl_supply_register + " " + test_hatching_register_for_dfls_supplied
                                                    + " " + temp_rh_maintenance_register + " " + bill_book_for_dfl_supply + " " + whether_following_self_certification_for_each_supply_lot
                                                    + " " + whether_quarterly_reports_are_being_regularly_submitted_to_nsso + " " + image + " " + extra1
                                                    + " " + extra2 + " " + extra3+ " " + extra4);
                                            Log.e("checloooo",registration_no);
                                        }
                                    }

                                    model_rcr_12a.add(new Model_RCR_12a(sp_id, sp_status, status, verification_id, verification_status,
                                            sp_registration_no, sp_sirname,
                                            sp_applicant, sp_behalf_of, sp_parent_sirname, sp_parentsname, sp_gender,
                                            sp_social_status, sp_training_certificate, sp_training_place,
                                            sp_training_from, sp_training_to, sp_educational_certificate,
                                            sp_passport_photo, sp_aadhaar_number, sp_country_code, sp_mobile,
                                            sp_applicant_type, sp_unit_name, sp_village, sp_taluk, sp_state,
                                            sp_district, sp_pin, sp_address, sp_production_center,
                                            sp_production_center_taluk, sp_production_center_state,
                                            sp_production_center_district, sp_production_center_pin,
                                            sp_production_center_sector, sp_production_center_address, sp_produced,
                                            sp_unit, sp_production_level, sp_ownership_type, sp_establishment,
                                            sp_other_details, sp_quantity_silkworm, sp_quantity_silkworm2, sp_workers,
                                            sp_unit_two, sp_page1, sp_page2, sp_page3, sp_page4,
                                            sp_page5, sp_page6, sp_sign, sp_remark, created_at, updated_at,name,
                                              nameOfTheRsp, registration_no, registered_address, contact_no
                                            , displayed_ther_registration_certificate_prominently, number_of_regd_seed_cocoon_producers_with_rsp
                                            , whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop
                                            , whether_seed_cocoons_are_purchased_as_per_norms, whether_seed_cocoons_are_properly_sorted_and_sex_separated
                                            , whether_pupal_testing_is_done_for_pebrine, sex_separation_efficiency, male_female_ratio
                                            , whether_each_parental_pupae_are_kept_in_separate_room,
                                            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms
                                            , whether_seeds_are_properly_washed_dried_and_packed_as_recommended
                                            , whether_male_moths_are_preserved_in_cold_room
                                            , whether_cold_rooms_have_uninterrupted_power_supply
                                            , whether_proper_temp_humidity_are_maintained_for_oviposition
                                            , whether_incubation_chamber_available, whether_seeds_are_properly_labeled_for_race_lot_no_dol
                                            , no_of_batches_detected, lot_details_of_source_seed_cocoon
                                            , skilled_person_for_testing, disinfection_register
                                            , seed_cocoon_procurement_register, cocoon_processing_register
                                            , pebrine_testing_register, dfl_supply_register
                                            , test_hatching_register_for_dfls_supplied,
                                            temp_rh_maintenance_register, bill_book_for_dfl_supply
                                            , whether_following_self_certification_for_each_supply_lot
                                            , whether_quarterly_reports_are_being_regularly_submitted_to_nsso
                                            , image, extra1, extra2, extra3, extra4));
                                }
                                Collections.reverse(model_rcr_12a);

                                adapter_rcr_12a = new Adapter_RCR_12a(getApplicationContext(), model_rcr_12a);
                                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.VERTICAL, true);
                                layoutManager1.setStackFromEnd(true);
                                 layoutManager1.setReverseLayout(true);

                                rvRCRlist.setLayoutManager(layoutManager1);
                                rvRCRlist.setHasFixedSize(true);
                                rvRCRlist.setItemAnimator(new DefaultItemAnimator());
                                rvRCRlist.setAdapter(adapter_rcr_12a);
                                adapter_rcr_12a.set(SelectRCRList.this);

                            } else {
                                Toast.makeText(SelectRCRList.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

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
    public void RCRClick(int position, int sr_id, int sr_status, int status, int verification_id, int verification_status,
                         String sr_registration_no, String sr_sirname, String sr_applicant, String sr_behalf_of,
                         String sr_parent_sirname, String sr_parentsname, String sr_gender, String sr_social_status,
                         String sr_training_certificate, String sr_training_place, String sr_training_from,
                         String sr_training_to, String sr_educational_certificate, String sr_passport_photo,
                         String sr_aadhaar_number, String sr_country_code, String sr_mobile, String sr_applicant_type,
                         String sr_unit_name, String sr_village, String sr_taluk, String sr_state, String sr_district,
                         String sr_pin, String sr_address, String sr_production_center, String sr_production_center_taluk,
                         String sr_production_center_state, String sr_production_center_district,
                         String sr_production_center_pin, String sr_production_center_sector,
                         String sr_production_center_address, String sr_produced, String sr_unit,
                         String sr_production_level, String sr_ownership_type, String sr_establishment,
                         String sr_other_details, String sr_quantity_silkworm, String sr_quantity_silkworm2,
                         String sr_workers, String sr_unit_two, String sr_page1, String sr_page2, String sr_page3,
                         String sr_page4, String sr_page5, String sr_page6, String sr_sign, String sr_remark,
                         String created_at, String updated_at, String name) {

        Intent intent = new Intent(getApplicationContext(), Details_page.class);
        intent.putExtra("check", check);
        intent.putExtra("combinedString", combinedString);
        intent.putExtra("sr_sirname", sr_sirname);
        intent.putExtra("sr_applicant", sr_applicant);
        intent.putExtra("sr_registration_no", sr_registration_no);
        intent.putExtra("sr_mobile", sr_mobile);
        intent.putExtra("sr_state", sr_state);
        intent.putExtra("sr_address", sr_address);
        intent.putExtra("sr_pin", sr_pin);
        intent.putExtra("sr_village", sr_village);
        intent.putExtra("sr_district", sr_district);
        intent.putExtra("sr_taluk", sr_taluk);
        intent.putExtra("sr_status", sr_status);
         intent.putExtra("verification_id", verification_id);
         intent.putExtra("name", name);
// Log.e("checkplease",sp_sirname+" "+sp_applicant+" "+sp_registration_no+" "+sp_mobile+" "+sp_state
//        +" "+sp_address+" "+sp_pin+" "+sp_village);
        startActivity(intent);

    }

    @Override
    public void RCRClick_12bUnder(int position, int sr_id, int sr_status, int status, int verification_id,
                                  int verification_status, String sr_registration_no, String sr_sirname, String sr_applicant, String sr_behalf_of, String sr_parent_sirname, String sr_parentsname, String sr_gender, String sr_social_status, String sr_training_certificate, String sr_training_place, String sr_training_from, String sr_training_to, String sr_educational_certificate, String sr_passport_photo, String sr_aadhaar_number, String sr_country_code, String sr_mobile, String sr_applicant_type, String sr_unit_name, String sr_village, String sr_taluk, String sr_state, String sr_district, String sr_pin, String sr_address, String sr_production_center, String sr_production_center_taluk, String sr_production_center_state, String sr_production_center_district, String sr_production_center_pin, String sr_production_center_sector, String sr_production_center_address, String sr_produced, String sr_unit, String sr_production_level, String sr_ownership_type, String sr_establishment, String sr_other_details, String sr_quantity_silkworm, String sr_quantity_silkworm2, String sr_workers, String sr_unit_two, String sr_page1, String sr_page2, String sr_page3, String sr_page4, String sr_page5, String sr_page6, String sr_sign, String sr_remark, String created_at, String updated_at, String name) {
        Intent intent = new Intent(getApplicationContext(), Details_page.class);
        intent.putExtra("check", check);
        intent.putExtra("combinedString", combinedString);
        intent.putExtra("sr_sirname", sr_sirname);
        intent.putExtra("sr_applicant", sr_applicant);
        intent.putExtra("sr_registration_no", sr_registration_no);
        intent.putExtra("sr_mobile", sr_mobile);
        intent.putExtra("sr_state", sr_state);
        intent.putExtra("sr_address", sr_address);
        intent.putExtra("sr_pin", sr_pin);
        intent.putExtra("sr_village", sr_village);
        intent.putExtra("sr_district", sr_district);
        intent.putExtra("sr_taluk", sr_taluk);
        intent.putExtra("sr_status", sr_status);
        intent.putExtra("verification_id", verification_id);
        intent.putExtra("name", name);
// Log.e("checkplease",sp_sirname+" "+sp_applicant+" "+sp_registration_no+" "+sp_mobile+" "+sp_state
//        +" "+sp_address+" "+sp_pin+" "+sp_village);
        startActivity(intent);
    }

    @Override
    public void RCRClick_12b(int position,int sr_id, int sr_status, int status, int verification_id, int verification_status,
                             String sr_registration_no, String sr_sirname, String sr_applicant, String sr_behalf_of,
                             String sr_parent_sirname, String sr_parentsname, String sr_gender, String sr_social_status,
                             String sr_training_certificate, String sr_training_place, String sr_training_from,
                             String sr_training_to, String sr_educational_certificate, String sr_passport_photo,
                             String sr_aadhaar_number, String sr_country_code, String sr_mobile, String sr_applicant_type,
                             String sr_unit_name, String sr_village, String sr_taluk, String sr_state, String sr_district,
                             String sr_pin, String sr_address, String sr_production_center, String sr_production_center_taluk,
                             String sr_production_center_state, String sr_production_center_district,
                             String sr_production_center_pin, String sr_production_center_sector,
                             String sr_production_center_address, String sr_produced, String sr_unit,
                             String sr_production_level, String sr_ownership_type, String sr_establishment,
                             String sr_other_details, String sr_quantity_silkworm, String sr_quantity_silkworm2,
                             String sr_workers, String sr_unit_two, String sr_page1, String sr_page2, String sr_page3,
                             String sr_page4, String sr_page5, String sr_page6, String sr_sign, String sr_remark,
                             String created_at, String updated_at, String name,   String nameOfTheRsp,
                             String registration_no, String registered_address, String contact_no,
                             String DisplayedRegistrationCertificateProminently, String mulberry_area_acre,
                             String regularity_of_soil_testing_once_in_2_years,
                             String recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop,
                             String is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum,
                             String mulberry_variety, String pest_disease_freeness_in_garden,
                             String nutrient_deficiency_symptoms_in_leaves, String average_single_leaf_weight_g,
                             String irrigation_facility, String overall_status, String incubation_facility,
                             String whether_rearing_house_is_as_per_recommended_plan,
                             String whether_equipments_appliances_as_per_seed_act_guidelines_available,
                             String visual_health_of_chawki_worms, String wt_of_100_ii_instar_larvae_g,
                             String bed_spacing, String maintenance_of_temp_rh, String no_of_batches_detected,
                             String lot_details_of_source_dfls, String skilled_person_for_testing,
                             String disinfection_register, String dfl_procurement_register,
                             String rearing_performance_register, String pebrine_testing_register,
                             String chawki_supply_register, String farm_management_register,
                             String temp_rh_maintenance_register, String bill_book,
                             String whether_following_self_certification_for_each_supply_lot, String image,
                             String extra1, String extra2, String extra3, String extra4)
    {
    Intent intent = new Intent(getApplicationContext(), PreviewpageEnd12b.class);
        intent.putExtra("check", check);
        intent.putExtra("verification_id", verification_id);
        intent.putExtra("combinedString", combinedString);
        intent.putExtra("sr_sirname", sr_sirname);
        intent.putExtra("sr_applicant", sr_applicant);
        intent.putExtra("sr_registration_no", sr_registration_no);
//        Toast.makeText(this, ""+registration_no, Toast.LENGTH_SHORT).show();
        intent.putExtra("sr_mobile", sr_mobile);
        intent.putExtra("sr_state", sr_state);
        intent.putExtra("sr_address", sr_address);
        intent.putExtra("sr_pin", sr_pin);
        intent.putExtra("sr_village", sr_village);
        intent.putExtra("sr_district", sr_district);
        intent.putExtra("sr_taluk", sr_taluk);
        intent.putExtra("sr_status", sr_status);
        intent.putExtra("name", name);
        intent.putExtra("verification_id", verification_id);
        intent.putExtra("nameOfTheRsp", nameOfTheRsp);
         intent.putExtra("registration_no", registration_no);
         intent.putExtra("registered_address", registered_address);
         intent.putExtra("contact_no", contact_no);
         intent.putExtra("DisplayedRegistrationCertificateProminently", DisplayedRegistrationCertificateProminently);
         intent.putExtra("mulberry_area_acre", mulberry_area_acre);
         intent.putExtra("regularity_of_soil_testing_once_in_2_years", regularity_of_soil_testing_once_in_2_years);
         intent.putExtra("recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop", recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop);
         intent.putExtra("is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum", is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum);
         intent.putExtra("mulberry_variety", mulberry_variety);
         intent.putExtra("pest_disease_freeness_in_garden", pest_disease_freeness_in_garden);
         intent.putExtra("nutrient_deficiency_symptoms_in_leaves", nutrient_deficiency_symptoms_in_leaves);
         intent.putExtra("average_single_leaf_weight_g", average_single_leaf_weight_g);
         intent.putExtra("irrigation_facility", irrigation_facility);
         intent.putExtra("overall_status", overall_status);
         intent.putExtra("incubation_facility", incubation_facility);
         intent.putExtra("whether_rearing_house_is_as_per_recommended_plan", whether_rearing_house_is_as_per_recommended_plan);
         intent.putExtra("whether_equipments_appliances_as_per_seed_act_guidelines_available", whether_equipments_appliances_as_per_seed_act_guidelines_available);
         intent.putExtra("visual_health_of_chawki_worms", visual_health_of_chawki_worms);
         intent.putExtra("wt_of_100_ii_instar_larvae_g", wt_of_100_ii_instar_larvae_g);
         intent.putExtra("bed_spacing", bed_spacing);
         intent.putExtra("maintenance_of_temp_rh", maintenance_of_temp_rh);
         intent.putExtra("no_of_batches_detected", no_of_batches_detected);
         intent.putExtra("lot_details_of_source_dfls", lot_details_of_source_dfls);
         intent.putExtra("skilled_person_for_testing", skilled_person_for_testing);
         intent.putExtra("disinfection_register", disinfection_register);
         intent.putExtra("dfl_procurement_register", dfl_procurement_register);
         intent.putExtra("rearing_performance_register", rearing_performance_register);
         intent.putExtra("pebrine_testing_register", pebrine_testing_register);
         intent.putExtra("chawki_supply_register", chawki_supply_register);
         intent.putExtra("farm_management_register", farm_management_register);
         intent.putExtra("temp_rh_maintenance_register", temp_rh_maintenance_register);
         intent.putExtra("bill_book", bill_book);
         intent.putExtra("whether_following_self_certification_for_each_supply_lot", whether_following_self_certification_for_each_supply_lot);
        intent.putExtra("image", image);
        intent.putExtra("extra1", extra1);
        intent.putExtra("extra2", extra2);
        intent.putExtra("extra3", extra3);
        intent.putExtra("extra4", extra4);
Log.e("checkmyoputput",nameOfTheRsp+" "+registration_no+" "+registered_address+" "+contact_no
        +" "+DisplayedRegistrationCertificateProminently+" "+mulberry_area_acre+" "+regularity_of_soil_testing_once_in_2_years
        +" "+recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop+" "+is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum
        +" "+mulberry_variety+" "+pest_disease_freeness_in_garden+" "+nutrient_deficiency_symptoms_in_leaves
        +" "+average_single_leaf_weight_g+" "+irrigation_facility+" "+overall_status+" "+incubation_facility
        +" "+whether_rearing_house_is_as_per_recommended_plan+" "+whether_equipments_appliances_as_per_seed_act_guidelines_available
        +" "+visual_health_of_chawki_worms+" "+wt_of_100_ii_instar_larvae_g+" "+bed_spacing+" "+maintenance_of_temp_rh
        +" "+no_of_batches_detected+" "+lot_details_of_source_dfls+" "+skilled_person_for_testing+" "+disinfection_register
        +" "+dfl_procurement_register+" "+rearing_performance_register+" "+pebrine_testing_register
        +" "+chawki_supply_register+" "+farm_management_register+" "+temp_rh_maintenance_register
        +" "+bill_book+" "+whether_following_self_certification_for_each_supply_lot+" "+image+" "+extra1
        +" "+extra2+" "+extra3);

    // Log.e("checkplease",sp_sirname+" "+sp_applicant+" "+sp_registration_no+" "+sp_mobile+" "+sp_state
//        +" "+sp_address+" "+sp_pin+" "+sp_village);
    startActivity(intent);
}

    @Override
    public void RCRClick12a_complete(int position, int sp_id, int sp_status, int status, int verification_id, int verification_status, String sp_registration_no, String sp_sirname, String sp_applicant, String sp_behalf_of, String sp_parent_sirname, String sp_parentsname, String sp_gender, String sp_social_status, String sp_training_certificate, String sp_training_place, String sp_training_from, String sp_training_to, String sp_educational_certificate, String sp_passport_photo, String sp_aadhaar_number, String sp_country_code, String sp_mobile, String sp_applicant_type, String sp_unit_name, String sp_village, String sp_taluk, String sp_state, String sp_district, String sp_pin, String sp_address, String sp_production_center, String sp_production_center_taluk, String sp_production_center_state, String sp_production_center_district, String sp_production_center_pin, String sp_production_center_sector, String sp_production_center_address, String sp_produced, String sp_unit, String sp_production_level, String sp_ownership_type, String sp_establishment, String sp_other_details, String sp_quantity_silkworm, String sp_quantity_silkworm2, String sp_workers, String sp_unit_two, String sp_page1, String sp_page2, String sp_page3, String sp_page4, String sp_page5, String sp_page6, String sp_sign, String sp_remark, String created_at, String updated_at,String name,      String nameOfTheRsp, String registration_no, String registered_address, String contact_no, String displayed_ther_registration_certificate_prominently, String number_of_regd_seed_cocoon_producers_with_rsp, String whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop, String whether_seed_cocoons_are_purchased_as_per_norms, String whether_seed_cocoons_are_properly_sorted_and_sex_separated, String whether_pupal_testing_is_done_for_pebrine, String sex_separation_efficiency, String male_female_ratio, String whether_each_parental_pupae_are_kept_in_separate_room, String whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms, String whether_seeds_are_properly_washed_dried_and_packed_as_recommended, String whether_male_moths_are_preserved_in_cold_room, String whether_cold_rooms_have_uninterrupted_power_supply, String whether_proper_temp_humidity_are_maintained_for_oviposition, String whether_incubation_chamber_available, String whether_seeds_are_properly_labeled_for_race_lot_no_dol, String no_of_batches_detected, String lot_details_of_source_seed_cocoon, String skilled_person_for_testing, String disinfection_register, String seed_cocoon_procurement_register, String cocoon_processing_register, String pebrine_testing_register, String dfl_supply_register, String test_hatching_register_for_dfls_supplied, String temp_rh_maintenance_register, String bill_book_for_dfl_supply, String whether_following_self_certification_for_each_supply_lot, String whether_quarterly_reports_are_being_regularly_submitted_to_nsso, String image, String extra1, String extra2, String extra3, String extra4) {
        Intent intent = new Intent(getApplicationContext(), Preview_pageEnd.class);
        intent.putExtra("check", check);
        intent.putExtra("combinedString", combinedString);
         intent.putExtra("sp_sirname", sp_sirname);
        intent.putExtra("sp_applicant", sp_applicant);
        intent.putExtra("sp_registration_no", sp_registration_no);
        intent.putExtra("sp_mobile", sp_mobile);
        intent.putExtra("sp_state", sp_state);
        intent.putExtra("sp_address", sp_address);
//        Toast.makeText(this, ""+registration_no, Toast.LENGTH_SHORT).show();

        intent.putExtra("sp_pin", sp_pin);
        intent.putExtra("sp_village", sp_village);
        intent.putExtra("sp_district", sp_district);
        intent.putExtra("sp_taluk", sp_taluk);
        intent.putExtra("sp_status", sp_status);
        intent.putExtra("name", name);
         intent.putExtra("verification_id", verification_id);
         intent.putExtra("nameOfTheRsp", nameOfTheRsp);
        intent.putExtra("registration_no", registration_no);
        intent.putExtra("registered_address", registered_address);
        intent.putExtra("contact_no", contact_no);
        intent.putExtra("displayed_ther_registration_certificate_prominently", displayed_ther_registration_certificate_prominently);
        intent.putExtra("number_of_regd_seed_cocoon_producers_with_rsp", number_of_regd_seed_cocoon_producers_with_rsp);
        intent.putExtra("whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop", whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop);
        intent.putExtra("whether_seed_cocoons_are_purchased_as_per_norms", whether_seed_cocoons_are_purchased_as_per_norms);
        intent.putExtra("whether_seed_cocoons_are_properly_sorted_and_sex_separated", whether_seed_cocoons_are_properly_sorted_and_sex_separated);
        intent.putExtra("whether_pupal_testing_is_done_for_pebrine", whether_pupal_testing_is_done_for_pebrine);
        intent.putExtra("sex_separation_efficiency", sex_separation_efficiency);
        intent.putExtra("male_female_ratio", male_female_ratio);
        intent.putExtra("whether_each_parental_pupae_are_kept_in_separate_room", whether_each_parental_pupae_are_kept_in_separate_room);
        intent.putExtra("whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms", whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms);
        intent.putExtra("whether_seeds_are_properly_washed_dried_and_packed_as_recommended", whether_seeds_are_properly_washed_dried_and_packed_as_recommended);
        intent.putExtra("whether_male_moths_are_preserved_in_cold_room", whether_male_moths_are_preserved_in_cold_room);
        intent.putExtra("whether_cold_rooms_have_uninterrupted_power_supply", whether_cold_rooms_have_uninterrupted_power_supply);
        intent.putExtra("whether_proper_temp_humidity_are_maintained_for_oviposition", whether_proper_temp_humidity_are_maintained_for_oviposition);
        intent.putExtra("whether_incubation_chamber_available", whether_incubation_chamber_available);
        intent.putExtra("whether_seeds_are_properly_labeled_for_race_lot_no_dol", whether_seeds_are_properly_labeled_for_race_lot_no_dol);
        intent.putExtra("no_of_batches_detected", no_of_batches_detected);
        intent.putExtra("lot_details_of_source_seed_cocoon", lot_details_of_source_seed_cocoon);
        intent.putExtra("skilled_person_for_testing", skilled_person_for_testing);
        intent.putExtra("disinfection_register", disinfection_register);
        intent.putExtra("seed_cocoon_procurement_register", seed_cocoon_procurement_register);
        intent.putExtra("cocoon_processing_register", cocoon_processing_register);
        intent.putExtra("pebrine_testing_register", pebrine_testing_register);
        intent.putExtra("dfl_supply_register", dfl_supply_register);
        intent.putExtra("test_hatching_register_for_dfls_supplied", test_hatching_register_for_dfls_supplied);
        intent.putExtra("temp_rh_maintenance_register", temp_rh_maintenance_register);
        intent.putExtra("bill_book_for_dfl_supply", bill_book_for_dfl_supply);
        intent.putExtra("whether_following_self_certification_for_each_supply_lot", whether_following_self_certification_for_each_supply_lot);
        intent.putExtra("whether_quarterly_reports_are_being_regularly_submitted_to_nsso", whether_quarterly_reports_are_being_regularly_submitted_to_nsso);
        intent.putExtra("image", image);
        intent.putExtra("extra1", extra1);
        intent.putExtra("extra2", extra2);
        intent.putExtra("extra3", extra3);
        intent.putExtra("extra4", extra4);
// Log.e("checkplease",sp_sirname+" "+sp_applicant+" "+sp_registration_no+" "+sp_mobile+" "+sp_state
//        +" "+sp_address+" "+sp_pin+" "+sp_village);
        startActivity(intent);
    }

    @Override
    public void RCRClick12a_underprocess(int position,int sp_id, int sp_status, int status, int verification_id, int verification_status,
                            String sp_registration_no, String sp_sirname, String sp_applicant, String sp_behalf_of,
                            String sp_parent_sirname, String sp_parentsname, String sp_gender, String sp_social_status,
                            String sp_training_certificate, String sp_training_place, String sp_training_from,
                            String sp_training_to, String sp_educational_certificate, String sp_passport_photo,
                            String sp_aadhaar_number, String sp_country_code, String sp_mobile, String sp_applicant_type,
                            String sp_unit_name, String sp_village, String sp_taluk, String sp_state, String sp_district,
                            String sp_pin, String sp_address, String sp_production_center, String sp_production_center_taluk,
                            String sp_production_center_state, String sp_production_center_district,
                            String sp_production_center_pin, String sp_production_center_sector,
                            String sp_production_center_address, String sp_produced, String sp_unit,
                            String sp_production_level, String sp_ownership_type, String sp_establishment,
                            String sp_other_details, String sp_quantity_silkworm, String sp_quantity_silkworm2,
                            String sp_workers, String sp_unit_two, String sp_page1, String sp_page2, String sp_page3,
                            String sp_page4, String sp_page5, String sp_page6, String sp_sign, String sp_remark,
                            String created_at, String updated_at, String name,      String nameOfTheRsp,
                            String registration_no, String registered_address, String contact_no,
                            String displayed_ther_registration_certificate_prominently,
                            String number_of_regd_seed_cocoon_producers_with_rsp,
                            String whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop,
                            String whether_seed_cocoons_are_purchased_as_per_norms,
                            String whether_seed_cocoons_are_properly_sorted_and_sex_separated,
                            String whether_pupal_testing_is_done_for_pebrine, String sex_separation_efficiency,
                            String male_female_ratio, String whether_each_parental_pupae_are_kept_in_separate_room,
                            String whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms,
                            String whether_seeds_are_properly_washed_dried_and_packed_as_recommended,
                            String whether_male_moths_are_preserved_in_cold_room,
                            String whether_cold_rooms_have_uninterrupted_power_supply,
                            String whether_proper_temp_humidity_are_maintained_for_oviposition,
                            String whether_incubation_chamber_available,
                            String whether_seeds_are_properly_labeled_for_race_lot_no_dol,
                            String no_of_batches_detected, String lot_details_of_source_seed_cocoon,
                            String skilled_person_for_testing, String disinfection_register,
                            String seed_cocoon_procurement_register, String cocoon_processing_register,
                            String pebrine_testing_register, String dfl_supply_register,
                            String test_hatching_register_for_dfls_supplied, String temp_rh_maintenance_register,
                            String bill_book_for_dfl_supply, String whether_following_self_certification_for_each_supply_lot,
                            String whether_quarterly_reports_are_being_regularly_submitted_to_nsso, String image,
                            String extra1, String extra2, String extra3, String extra4) {
        Intent intent = new Intent(getApplicationContext(), Details_page.class);
        intent.putExtra("check", check);
        intent.putExtra("combinedString", combinedString);
        intent.putExtra("sp_sirname", sp_sirname);
        intent.putExtra("sp_applicant", sp_applicant);
        intent.putExtra("sp_registration_no", sp_registration_no);
        intent.putExtra("sp_mobile", sp_mobile);
        intent.putExtra("sp_state", sp_state);
        intent.putExtra("sp_address", sp_address);
        intent.putExtra("sp_pin", sp_pin);
        intent.putExtra("sp_village", sp_village);
        intent.putExtra("sp_district", sp_district);
        intent.putExtra("sp_taluk", sp_taluk);
        intent.putExtra("sp_status", sp_status);
        intent.putExtra("name", name);
         intent.putExtra("verification_id", verification_id);
         intent.putExtra("nameOfTheRsp", nameOfTheRsp);
        intent.putExtra("registration_no", registration_no);
        intent.putExtra("registered_address", registered_address);
        intent.putExtra("contact_no", contact_no);
        intent.putExtra("displayed_ther_registration_certificate_prominently", displayed_ther_registration_certificate_prominently);
        intent.putExtra("number_of_regd_seed_cocoon_producers_with_rsp", number_of_regd_seed_cocoon_producers_with_rsp);
        intent.putExtra("whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop", whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop);
        intent.putExtra("whether_seed_cocoons_are_purchased_as_per_norms", whether_seed_cocoons_are_purchased_as_per_norms);
        intent.putExtra("whether_seed_cocoons_are_properly_sorted_and_sex_separated", whether_seed_cocoons_are_properly_sorted_and_sex_separated);
        intent.putExtra("whether_pupal_testing_is_done_for_pebrine", whether_pupal_testing_is_done_for_pebrine);
        intent.putExtra("sex_separation_efficiency", sex_separation_efficiency);
        intent.putExtra("male_female_ratio", male_female_ratio);
        intent.putExtra("whether_each_parental_pupae_are_kept_in_separate_room", whether_each_parental_pupae_are_kept_in_separate_room);
        intent.putExtra("whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms", whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms);
        intent.putExtra("whether_seeds_are_properly_washed_dried_and_packed_as_recommended", whether_seeds_are_properly_washed_dried_and_packed_as_recommended);
        intent.putExtra("whether_male_moths_are_preserved_in_cold_room", whether_male_moths_are_preserved_in_cold_room);
        intent.putExtra("whether_cold_rooms_have_uninterrupted_power_supply", whether_cold_rooms_have_uninterrupted_power_supply);
        intent.putExtra("whether_proper_temp_humidity_are_maintained_for_oviposition", whether_proper_temp_humidity_are_maintained_for_oviposition);
        intent.putExtra("whether_incubation_chamber_available", whether_incubation_chamber_available);
        intent.putExtra("whether_seeds_are_properly_labeled_for_race_lot_no_dol", whether_seeds_are_properly_labeled_for_race_lot_no_dol);
        intent.putExtra("no_of_batches_detected", no_of_batches_detected);
        intent.putExtra("lot_details_of_source_seed_cocoon", lot_details_of_source_seed_cocoon);
        intent.putExtra("skilled_person_for_testing", skilled_person_for_testing);
        intent.putExtra("disinfection_register", disinfection_register);
        intent.putExtra("seed_cocoon_procurement_register", seed_cocoon_procurement_register);
        intent.putExtra("cocoon_processing_register", cocoon_processing_register);
        intent.putExtra("pebrine_testing_register", pebrine_testing_register);
        intent.putExtra("dfl_supply_register", dfl_supply_register);
        intent.putExtra("test_hatching_register_for_dfls_supplied", test_hatching_register_for_dfls_supplied);
        intent.putExtra("temp_rh_maintenance_register", temp_rh_maintenance_register);
        intent.putExtra("bill_book_for_dfl_supply", bill_book_for_dfl_supply);
        intent.putExtra("whether_following_self_certification_for_each_supply_lot", whether_following_self_certification_for_each_supply_lot);
        intent.putExtra("whether_quarterly_reports_are_being_regularly_submitted_to_nsso", whether_quarterly_reports_are_being_regularly_submitted_to_nsso);
        intent.putExtra("image", image);
        intent.putExtra("extra1", extra1);
        intent.putExtra("extra2", extra2);
        intent.putExtra("extra3", extra3);
        intent.putExtra("extra4", extra4);
// Log.e("checkplease",sp_sirname+" "+sp_applicant+" "+sp_registration_no+" "+sp_mobile+" "+sp_state
//        +" "+sp_address+" "+sp_pin+" "+sp_village);
        startActivity(intent);
    }

    @Override
    public void RCRClick12a_data(int position, int sp_id, int sp_status, int status, int verification_id,
                                 int verification_status, String sp_registration_no, String sp_sirname,
                                 String sp_applicant, String sp_behalf_of, String sp_parent_sirname,
                                 String sp_parentsname, String sp_gender, String sp_social_status, String sp_training_certificate, String sp_training_place, String sp_training_from, String sp_training_to, String sp_educational_certificate, String sp_passport_photo, String sp_aadhaar_number, String sp_country_code, String sp_mobile, String sp_applicant_type, String sp_unit_name, String sp_village, String sp_taluk, String sp_state, String sp_district, String sp_pin, String sp_address, String sp_production_center, String sp_production_center_taluk, String sp_production_center_state, String sp_production_center_district, String sp_production_center_pin, String sp_production_center_sector, String sp_production_center_address, String sp_produced, String sp_unit, String sp_production_level, String sp_ownership_type, String sp_establishment, String sp_other_details, String sp_quantity_silkworm, String sp_quantity_silkworm2, String sp_workers, String sp_unit_two, String sp_page1, String sp_page2, String sp_page3, String sp_page4, String sp_page5, String sp_page6, String sp_sign, String sp_remark, String created_at, String updated_at, String name) {
        Intent intent = new Intent(getApplicationContext(), Details_page.class);
        intent.putExtra("check", check);
        intent.putExtra("combinedString", combinedString);
        intent.putExtra("sp_sirname", sp_sirname);
        intent.putExtra("sp_applicant", sp_applicant);
        intent.putExtra("sp_registration_no", sp_registration_no);
        intent.putExtra("sp_mobile", sp_mobile);
        intent.putExtra("sp_state", sp_state);
        intent.putExtra("sp_address", sp_address);
        intent.putExtra("sp_pin", sp_pin);
        intent.putExtra("sp_village", sp_village);
        intent.putExtra("sp_district", sp_district);
        intent.putExtra("sp_taluk", sp_taluk);
        intent.putExtra("sp_status", sp_status);
//        Toast.makeText(this, "" + sp_address + " " + sp_village, Toast.LENGTH_SHORT).show();
        intent.putExtra("verification_id", verification_id);
        intent.putExtra("name", name);
// Log.e("checkplease",sp_sirname+" "+sp_applicant+" "+sp_registration_no+" "+sp_mobile+" "+sp_state
//        +" "+sp_address+" "+sp_pin+" "+sp_village);
        startActivity(intent);
    }

    void getOfficersJobfor12aOnresume( ) {



        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("seed_officer_id", seed_officer_id);
            requestBody.put("form_type", "12a");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiData.analystjobsbyofficers, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("status") == true) {
                                Log.e("apirespons1", String.valueOf(response));
                                model_rcr_12a = new ArrayList<Model_RCR_12a>();
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    Log.e("apirespons", String.valueOf(user));

                                    int sp_id = user.optInt("sp_id", 0);
                                    int sp_status = user.optInt("sp_status", 0);
                                    int status = user.optInt("status", 0);
                                    int verification_id = user.optInt("verification_id", 0);
                                    int verification_status = user.optInt("verification_status", 0);
                                    String sp_registration_no = user.optString("sp_registration_no");
                                    String sp_sirname = user.optString("sp_sirname");
                                    String sp_applicant = user.optString("sp_applicant");
                                    String sp_behalf_of = user.optString("sp_behalf_of");
                                    String sp_parent_sirname = user.optString("sp_parent_sirname");
                                    String sp_parentsname = user.optString("sp_parentsname");
                                    String sp_gender = user.optString("sp_gender");
                                    String sp_social_status = user.optString("sp_social_status");
                                    String sp_training_certificate = user.optString("sp_training_certificate");
                                    String sp_training_place = user.optString("sp_training_place");
                                    String sp_training_from = user.optString("sp_training_from");
                                    String sp_training_to = user.optString("sp_training_to");
                                    String sp_educational_certificate = user.optString("sp_educational_certificate");
                                    String sp_passport_photo = user.optString("sp_passport_photo");
                                    String sp_aadhaar_number = user.optString("sp_aadhaar_number");
                                    String sp_country_code = user.optString("sp_country_code");
                                    String sp_mobile = user.optString("sp_mobile");
                                    String sp_applicant_type = user.optString("sp_applicant_type");
                                    String sp_unit_name = user.optString("sp_unit_name");
                                    String sp_village = user.optString("sp_village");
                                    String sp_taluk = user.optString("sp_taluk");
                                    String sp_state = user.optString("sp_state");
                                    String sp_district = user.optString("sp_district");
                                    String sp_pin = user.optString("sp_pin");
                                    String sp_address = user.optString("sp_address");
                                    String sp_production_center = user.optString("sp_production_center");
                                    String sp_production_center_taluk = user.optString("sp_production_center_taluk");
                                    String sp_production_center_state = user.optString("sp_production_center_state");
                                    String sp_production_center_district = user.optString("sp_production_center_district");
                                    String sp_production_center_pin = user.optString("sp_production_center_pin");
                                    String sp_production_center_sector = user.optString("sp_production_center_sector");
                                    String sp_production_center_address = user.optString("sp_production_center_address");
                                    String sp_produced = user.optString("sp_produced");
                                    String sp_unit = user.optString("sp_unit");
                                    String sp_production_level = user.optString("sp_production_level");
                                    String sp_ownership_type = user.optString("sp_ownership_type");
                                    String sp_establishment = user.optString("sp_establishment");
                                    String sp_other_details = user.optString("sp_other_details");
                                    String sp_quantity_silkworm = user.optString("sp_quantity_silkworm");
                                    String sp_quantity_silkworm2 = user.optString("sp_quantity_silkworm2");
                                    String sp_workers = user.optString("sp_workers");
                                    String sp_unit_two = user.optString("sp_unit_two");
                                    String sp_page1 = user.optString("sp_page1");
                                    String sp_page2 = user.optString("sp_page2");
                                    String sp_page3 = user.optString("sp_page3");
                                    String sp_page4 = user.optString("sp_page4");
                                    String sp_page5 = user.optString("sp_page5");
                                    String sp_page6 = user.optString("sp_page6");
                                    String sp_sign = user.optString("sp_sign");
                                    String sp_remark = user.optString("sp_remark");
                                    String created_at = user.optString("created_at");
                                    String updated_at = user.optString("updated_at");
                                    String name = user.optString("name");
                                    String   nameOfTheRsp = null, registration_no = null, registered_address = null,
                                            contact_no = null, displayed_ther_registration_certificate_prominently = null,
                                            number_of_regd_seed_cocoon_producers_with_rsp = null, whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = null,
                                            whether_seed_cocoons_are_purchased_as_per_norms = null,
                                            whether_seed_cocoons_are_properly_sorted_and_sex_separated = null,
                                            whether_pupal_testing_is_done_for_pebrine = null, sex_separation_efficiency = null,
                                            male_female_ratio = null, whether_each_parental_pupae_are_kept_in_separate_room = null,
                                            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = null, whether_seeds_are_properly_washed_dried_and_packed_as_recommended = null, whether_male_moths_are_preserved_in_cold_room = null, whether_cold_rooms_have_uninterrupted_power_supply = null, whether_proper_temp_humidity_are_maintained_for_oviposition = null, whether_incubation_chamber_available = null, whether_seeds_are_properly_labeled_for_race_lot_no_dol = null, no_of_batches_detected = null, lot_details_of_source_seed_cocoon = null, skilled_person_for_testing = null, disinfection_register = null, seed_cocoon_procurement_register = null, cocoon_processing_register = null, pebrine_testing_register = null, dfl_supply_register = null, test_hatching_register_for_dfls_supplied = null,
                                            temp_rh_maintenance_register = null, bill_book_for_dfl_supply =
                                            null, whether_following_self_certification_for_each_supply_lot = null, whether_quarterly_reports_are_being_regularly_submitted_to_nsso = null, image = null, extra1 = null, extra2 = null, extra3 = null, extra4 = null;
                                    Object verificationDataObj = user.get("verification_data");
                                    if (verificationDataObj instanceof Boolean) {
                                        boolean verificationData = (boolean) verificationDataObj;
                                        Log.e("cgechoutput1",   nameOfTheRsp + " " + registration_no + " " + registered_address + " " + contact_no
                                                + " " + displayed_ther_registration_certificate_prominently + " " + number_of_regd_seed_cocoon_producers_with_rsp
                                                + " " + whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop
                                                + " " + whether_seed_cocoons_are_purchased_as_per_norms + " " + whether_seed_cocoons_are_properly_sorted_and_sex_separated
                                                + " " + whether_pupal_testing_is_done_for_pebrine + " " + sex_separation_efficiency + " " + male_female_ratio
                                                + " " + whether_each_parental_pupae_are_kept_in_separate_room + " " + whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms
                                                + " " + whether_seeds_are_properly_washed_dried_and_packed_as_recommended + " " + whether_male_moths_are_preserved_in_cold_room
                                                + " " + whether_cold_rooms_have_uninterrupted_power_supply + " " + whether_proper_temp_humidity_are_maintained_for_oviposition
                                                + " " + whether_incubation_chamber_available + " " + whether_seeds_are_properly_labeled_for_race_lot_no_dol
                                                + " " + no_of_batches_detected + " " + lot_details_of_source_seed_cocoon + " " + skilled_person_for_testing
                                                + " " + disinfection_register + " " + seed_cocoon_procurement_register + " " + cocoon_processing_register
                                                + " " + pebrine_testing_register + " " + dfl_supply_register + " " + test_hatching_register_for_dfls_supplied
                                                + " " + temp_rh_maintenance_register + " " + bill_book_for_dfl_supply + " " + whether_following_self_certification_for_each_supply_lot
                                                + " " + whether_quarterly_reports_are_being_regularly_submitted_to_nsso + " " + image + " " + extra1
                                                + " " + extra2 + " " + extra3+ " " + extra4);

                                        nameOfTheRsp = null;
                                        registration_no = null;
                                        registered_address = null;
                                        contact_no = null;
                                        displayed_ther_registration_certificate_prominently = null;
                                        number_of_regd_seed_cocoon_producers_with_rsp = null;
                                        whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = null;
                                        whether_seed_cocoons_are_purchased_as_per_norms = null;
                                        whether_seed_cocoons_are_properly_sorted_and_sex_separated = null;
                                        whether_pupal_testing_is_done_for_pebrine = null;
                                        sex_separation_efficiency = null;
                                        male_female_ratio = null;
                                        whether_each_parental_pupae_are_kept_in_separate_room = null;
                                        whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = null;
                                        whether_seeds_are_properly_washed_dried_and_packed_as_recommended = null;
                                        whether_male_moths_are_preserved_in_cold_room = null;
                                        whether_cold_rooms_have_uninterrupted_power_supply = null;
                                        whether_proper_temp_humidity_are_maintained_for_oviposition = null;
                                        whether_incubation_chamber_available = null;
                                        whether_seeds_are_properly_labeled_for_race_lot_no_dol = null;
                                        no_of_batches_detected = null;
                                        lot_details_of_source_seed_cocoon = null;
                                        skilled_person_for_testing = null;
                                        disinfection_register = null;
                                        seed_cocoon_procurement_register = null;
                                        cocoon_processing_register = null;
                                        pebrine_testing_register = null;
                                        dfl_supply_register = null;
                                        test_hatching_register_for_dfls_supplied = null;
                                        temp_rh_maintenance_register = null;
                                        bill_book_for_dfl_supply = null;
                                        whether_following_self_certification_for_each_supply_lot = null;
                                        whether_quarterly_reports_are_being_regularly_submitted_to_nsso = null;
                                        image = null;
                                        extra1 = null;
                                        extra2 = null;
                                        extra3 = null;
                                        extra4 = null;
                                        // ... (set default values or handle other fields related to verification_data)
                                    } else if (verificationDataObj instanceof JSONArray) {
                                        // Handle the case where verification_data is an array of data
                                        JSONArray verificationDataArray = (JSONArray) verificationDataObj;
                                        // Parse the data in the array as needed
                                        if (verificationDataArray.length() > 0) {
                                            JSONObject verificationData = verificationDataArray.getJSONObject(0);
                                            nameOfTheRsp = verificationData.optString("name_of_the_rsp");
                                            registration_no = verificationData.optString("registration_no.");
                                            registered_address = verificationData.optString("registered_address");
                                            contact_no = verificationData.optString("contact_no");
                                            displayed_ther_registration_certificate_prominently = verificationData.optString("displayed_registration_certificate_prominently");
                                            number_of_regd_seed_cocoon_producers_with_rsp = verificationData.optString("number_of_regd._seed_cocoon_producers_with_rsp");
                                            whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = verificationData.optString("whether_seed_cocoons_are_purchased_from_regd._seed_cocoon_producers_for_every_crop");
                                            whether_seed_cocoons_are_purchased_as_per_norms = verificationData.optString("whether_seed_cocoons_are_purchased_as_per_norms");
                                            whether_seed_cocoons_are_properly_sorted_and_sex_separated = verificationData.optString("whether_seed_cocoons_are_properly_sorted_and_sex_separated");
                                            whether_pupal_testing_is_done_for_pebrine = verificationData.optString("whether_pupal_testing_is_done_for_pebrine");
                                            sex_separation_efficiency = verificationData.optString("sex-separation_efficiency");
                                            male_female_ratio = verificationData.optString("male:_female_ratio");
                                            whether_each_parental_pupae_are_kept_in_separate_room = verificationData.optString("whether_each_parental_pupae_are_kept_in_separate_room");
                                            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = verificationData.optString("whether_proper_temp_&_rh_maintained_in_cocoon_pupae_&_oviposition_rooms");
                                            whether_seeds_are_properly_washed_dried_and_packed_as_recommended = verificationData.optString("whether_seeds_are_properly_washed,_dried_and_packed_as_recommended");
                                            whether_male_moths_are_preserved_in_cold_room = verificationData.optString("whether_male_moths_are_preserved_in_5-7_cold_room");
                                            whether_cold_rooms_have_uninterrupted_power_supply = verificationData.optString("whether_cold_rooms_have_uninterrupted_power_supply");
                                            whether_proper_temp_humidity_are_maintained_for_oviposition = verificationData.optString("whether_proper_temp_&_humidity_are_maintained_for_oviposition");
                                            whether_incubation_chamber_available = verificationData.optString("whether_incubation_chamber_available");
                                            whether_seeds_are_properly_labeled_for_race_lot_no_dol = verificationData.optString("whether_seeds_are_properly_labeled_for_race,_lot_no,_dol");
                                            no_of_batches_detected = verificationData.optString("no._of_batches_detected");
                                            lot_details_of_source_seed_cocoon = verificationData.optString("lot_details_of_source_seed_cocoon");
                                            skilled_person_for_testing = verificationData.optString("skilled_person_for_testing");
                                            disinfection_register = verificationData.optString("disinfection_register");
                                            seed_cocoon_procurement_register = verificationData.optString("seed_cocoon_procurement_register");
                                            cocoon_processing_register = verificationData.optString("cocoon_processing_register");
                                            pebrine_testing_register = verificationData.optString("pebrine_testing_register");
                                            dfl_supply_register = verificationData.optString("dfl_supply_register");
                                            test_hatching_register_for_dfls_supplied = verificationData.optString("test_hatching_register_for_dfls_supplied");
                                            temp_rh_maintenance_register = verificationData.optString("temp._&_rh_maintenance_register");
                                            bill_book_for_dfl_supply = verificationData.optString("bill_book_for_dfl_supply");
                                            whether_following_self_certification_for_each_supply_lot = verificationData.optString("whether_following_self-certification_for_each_supply_lot");
                                            whether_quarterly_reports_are_being_regularly_submitted_to_nsso = verificationData.optString("whether_quarterly_reports_are_being_regularly_submitted_to_nsso");
                                            image = verificationData.optString("image");
                                            extra1 = verificationData.optString("extra1");
                                            extra2 = verificationData.optString("extra2");
                                            extra3 = verificationData.optString("extra3");
                                            extra4 = verificationData.optString("extra4");
                                            Log.e("cgechoutput",   nameOfTheRsp + " " + registration_no + "re " + registered_address + " " + contact_no
                                                    + " " + displayed_ther_registration_certificate_prominently + " " + number_of_regd_seed_cocoon_producers_with_rsp
                                                    + " " + whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop
                                                    + " " + whether_seed_cocoons_are_purchased_as_per_norms + " " + whether_seed_cocoons_are_properly_sorted_and_sex_separated
                                                    + " " + whether_pupal_testing_is_done_for_pebrine + " " + sex_separation_efficiency + " " + male_female_ratio
                                                    + " " + whether_each_parental_pupae_are_kept_in_separate_room + " " + whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms
                                                    + " " + whether_seeds_are_properly_washed_dried_and_packed_as_recommended + " " + whether_male_moths_are_preserved_in_cold_room
                                                    + " " + whether_cold_rooms_have_uninterrupted_power_supply + " " + whether_proper_temp_humidity_are_maintained_for_oviposition
                                                    + " " + whether_incubation_chamber_available + " " + whether_seeds_are_properly_labeled_for_race_lot_no_dol
                                                    + " " + no_of_batches_detected + " " + lot_details_of_source_seed_cocoon + " " + skilled_person_for_testing
                                                    + " " + disinfection_register + " " + seed_cocoon_procurement_register + " " + cocoon_processing_register
                                                    + " " + pebrine_testing_register + " " + dfl_supply_register + " " + test_hatching_register_for_dfls_supplied
                                                    + " " + temp_rh_maintenance_register + " " + bill_book_for_dfl_supply + " " + whether_following_self_certification_for_each_supply_lot
                                                    + " " + whether_quarterly_reports_are_being_regularly_submitted_to_nsso + " " + image + " " + extra1
                                                    + " " + extra2 + " " + extra3+ " " + extra4);
                                            Log.e("checloooo",registration_no);
                                        }
                                    }

                                    model_rcr_12a.add(new Model_RCR_12a(sp_id, sp_status, status, verification_id, verification_status,
                                            sp_registration_no, sp_sirname,
                                            sp_applicant, sp_behalf_of, sp_parent_sirname, sp_parentsname, sp_gender,
                                            sp_social_status, sp_training_certificate, sp_training_place,
                                            sp_training_from, sp_training_to, sp_educational_certificate,
                                            sp_passport_photo, sp_aadhaar_number, sp_country_code, sp_mobile,
                                            sp_applicant_type, sp_unit_name, sp_village, sp_taluk, sp_state,
                                            sp_district, sp_pin, sp_address, sp_production_center,
                                            sp_production_center_taluk, sp_production_center_state,
                                            sp_production_center_district, sp_production_center_pin,
                                            sp_production_center_sector, sp_production_center_address, sp_produced,
                                            sp_unit, sp_production_level, sp_ownership_type, sp_establishment,
                                            sp_other_details, sp_quantity_silkworm, sp_quantity_silkworm2, sp_workers,
                                            sp_unit_two, sp_page1, sp_page2, sp_page3, sp_page4,
                                            sp_page5, sp_page6, sp_sign, sp_remark, created_at, updated_at,name,
                                            nameOfTheRsp, registration_no, registered_address, contact_no
                                            , displayed_ther_registration_certificate_prominently, number_of_regd_seed_cocoon_producers_with_rsp
                                            , whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop
                                            , whether_seed_cocoons_are_purchased_as_per_norms, whether_seed_cocoons_are_properly_sorted_and_sex_separated
                                            , whether_pupal_testing_is_done_for_pebrine, sex_separation_efficiency, male_female_ratio
                                            , whether_each_parental_pupae_are_kept_in_separate_room,
                                            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms
                                            , whether_seeds_are_properly_washed_dried_and_packed_as_recommended
                                            , whether_male_moths_are_preserved_in_cold_room
                                            , whether_cold_rooms_have_uninterrupted_power_supply
                                            , whether_proper_temp_humidity_are_maintained_for_oviposition
                                            , whether_incubation_chamber_available, whether_seeds_are_properly_labeled_for_race_lot_no_dol
                                            , no_of_batches_detected, lot_details_of_source_seed_cocoon
                                            , skilled_person_for_testing, disinfection_register
                                            , seed_cocoon_procurement_register, cocoon_processing_register
                                            , pebrine_testing_register, dfl_supply_register
                                            , test_hatching_register_for_dfls_supplied,
                                            temp_rh_maintenance_register, bill_book_for_dfl_supply
                                            , whether_following_self_certification_for_each_supply_lot
                                            , whether_quarterly_reports_are_being_regularly_submitted_to_nsso
                                            , image, extra1, extra2, extra3, extra4));
                                }
                                Collections.reverse(model_rcr_12a);

                                adapter_rcr_12a = new Adapter_RCR_12a(getApplicationContext(), model_rcr_12a);
                                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.VERTICAL, true);
                                layoutManager1.setStackFromEnd(true);
                                 layoutManager1.setReverseLayout(true);

                                rvRCRlist.setLayoutManager(layoutManager1);
                                rvRCRlist.setHasFixedSize(true);
                                rvRCRlist.setItemAnimator(new DefaultItemAnimator());
                                rvRCRlist.setAdapter(adapter_rcr_12a);
                                adapter_rcr_12a.set(SelectRCRList.this);

                            } else {
                                Toast.makeText(SelectRCRList.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

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
    void getOfficersJobOnresume( ) {


        JSONObject requestBody = new JSONObject();
        try {

            requestBody.put("seed_officer_id", seed_officer_id);
            requestBody.put("form_type", "12b");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiData.analystjobsbyofficers, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("status") == true) {
                                Log.e("apirespons1", String.valueOf(response));
                                model_rcr = new ArrayList<Model_RCR>();
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject user = jsonArray.getJSONObject(i);
                                    Log.e("apirespons", String.valueOf(user));

                                    int sr_id = user.optInt("sr_id", 0);
                                    int sr_status = user.optInt("sr_status", 0);
                                    int status = user.optInt("status", 0);
                                    int verification_id = user.optInt("verification_id", 0);
                                    int verification_status = user.optInt("verification_status", 0);
                                    String sr_registration_no = user.optString("sr_registration_no");
                                    String sr_sirname = user.optString("sr_sirname");
                                    String sr_applicant = user.optString("sr_applicant");
                                    String sr_behalf_of = user.optString("sr_behalf_of");
                                    String sr_parent_sirname = user.optString("sr_parent_sirname");
                                    String sr_parentsname = user.optString("sr_parentsname");
                                    String sr_gender = user.optString("sr_gender");
                                    String sr_social_status = user.optString("sr_social_status");
                                    String sr_training_certificate = user.optString("sr_training_certificate");
                                    String sr_training_place = user.optString("sr_training_place");
                                    String sr_training_from = user.optString("sr_training_from");
                                    String sr_training_to = user.optString("sp_training_to");
                                    String sr_educational_certificate = user.optString("sr_educational_certificate");
                                    String sr_passport_photo = user.optString("sr_passport_photo");
                                    String sr_aadhaar_number = user.optString("sr_aadhaar_number");
                                    String sr_country_code = user.optString("sr_country_code");
                                    String sr_mobile = user.optString("sr_mobile");
                                    String sr_applicant_type = user.optString("sr_applicant_type");
                                    String sr_unit_name = user.optString("sr_unit_name");
                                    String sr_village = user.optString("sr_village");
                                    String sr_taluk = user.optString("sr_taluk");
                                    String sr_state = user.optString("sr_state");
                                    String sr_district = user.optString("sr_district");
                                    String sr_pin = user.optString("sr_pin");
                                    String sr_address = user.optString("sr_address");
                                    String sr_production_center = user.optString("sr_production_center");
                                    String sr_production_center_taluk = user.optString("sr_production_center_taluk");
                                    String sr_production_center_state = user.optString("sr_production_center_state");
                                    String sr_production_center_district = user.optString("sr_production_center_district");
                                    String sr_production_center_pin = user.optString("sr_production_center_pin");
                                    String sr_production_center_sector = user.optString("sr_production_center_sector");
                                    String sr_production_center_address = user.optString("sr_production_center_address");
                                    String sr_produced = user.optString("sr_produced");
                                    String sr_unit = user.optString("sr_unit");
                                    String sr_production_level = user.optString("sr_production_level");
                                    String sr_ownership_type = user.optString("sr_ownership_type");
                                    String sr_establishment = user.optString("sr_establishment");
                                    String sr_other_details = user.optString("sr_other_details");
                                    String sr_quantity_silkworm = user.optString("sr_quantity_silkworm");
                                    String sr_quantity_silkworm2 = user.optString("sr_quantity_silkworm2");
                                    String sr_workers = user.optString("sr_workers");
                                    String sr_unit_two = user.optString("sr_unit_two");
                                    String sr_page1 = user.optString("sr_page1");
                                    String sr_page2 = user.optString("sr_page2");
                                    String sr_page3 = user.optString("sr_page3");
                                    String sr_page4 = user.optString("sr_page4");
                                    String sr_page5 = user.optString("sr_page5");
                                    String sr_page6 = user.optString("sr_page6");
                                    String sr_sign = user.optString("sr_sign");
                                    String sr_remark = user.optString("sr_remark");
                                    String created_at = user.optString("created_at");
                                    String updated_at = user.optString("updated_at");
                                    String name = user.optString("name");
                                    String
                                            nameOfTheRsp = null,
                                            registration_no = null,
                                            registered_address = null,
                                            contact_no = null,
                                            DisplayedRegistrationCertificateProminently = null,
                                            mulberry_area_acre = null,
                                            regularity_of_soil_testing_once_in_2_years = null,
                                            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = null,
                                            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = null,
                                            mulberry_variety = null,
                                            pest_disease_freeness_in_garden = null,
                                            nutrient_deficiency_symptoms_in_leaves = null,
                                            average_single_leaf_weight_g = null,
                                            irrigation_facility = null,
                                            overall_status = null,
                                            incubation_facility = null,
                                            whether_rearing_house_is_as_per_recommended_plan = null,
                                            whether_equipments_appliances_as_per_seed_act_guidelines_available = null,
                                            visual_health_of_chawki_worms = null,
                                            wt_of_100_ii_instar_larvae_g = null,
                                            bed_spacing = null,
                                            maintenance_of_temp_rh = null,
                                            no_of_batches_detected = null,
                                            lot_details_of_source_dfls = null,
                                            skilled_person_for_testing = null,
                                            disinfection_register = null,
                                            dfl_procurement_register = null,
                                            rearing_performance_register = null,
                                            pebrine_testing_register = null,
                                            chawki_supply_register = null,
                                            farm_management_register = null,
                                            temp_rh_maintenance_register = null,
                                            bill_book = null,
                                            whether_following_self_certification_for_each_supply_lot = null,
                                            image = null,
                                            extra1 = null,
                                            extra2 = null,
                                            extra3 = null,
                                            extra4 = null;

                                    Object verificationDataObj = user.get("verification_data");
                                    if (verificationDataObj instanceof Boolean) {
                                        boolean verificationData = (boolean) verificationDataObj;


                                        nameOfTheRsp = null;
                                        registration_no = null;
                                        registered_address = null;
                                        contact_no = null;
                                        DisplayedRegistrationCertificateProminently = null;
                                        mulberry_area_acre = null;
                                        regularity_of_soil_testing_once_in_2_years = null;
                                        recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = null;
                                        is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = null;
                                        mulberry_variety = null;
                                        pest_disease_freeness_in_garden = null;
                                        nutrient_deficiency_symptoms_in_leaves = null;
                                        average_single_leaf_weight_g = null;
                                        irrigation_facility = null;
                                        overall_status = null;
                                        incubation_facility = null;
                                        whether_rearing_house_is_as_per_recommended_plan = null;
                                        whether_equipments_appliances_as_per_seed_act_guidelines_available = null;
                                        visual_health_of_chawki_worms = null;
                                        wt_of_100_ii_instar_larvae_g = null;
                                        bed_spacing = null;
                                        maintenance_of_temp_rh = null;
                                        no_of_batches_detected = null;
                                        lot_details_of_source_dfls = null;
                                        skilled_person_for_testing = null;
                                        disinfection_register = null;
                                        dfl_procurement_register = null;
                                        rearing_performance_register = null;
                                        pebrine_testing_register = null;
                                        chawki_supply_register = null;
                                        farm_management_register = null;
                                        temp_rh_maintenance_register = null;
                                        bill_book = null;
                                        whether_following_self_certification_for_each_supply_lot = null;
                                        image = null;
                                        extra1 = null;
                                        extra2 = null;
                                        extra3 = null;
                                        extra4 = null;

                                        // ... (set default values or handle other fields related to verification_data)
                                    } else if (verificationDataObj instanceof JSONArray) {
                                        // Handle the case where verification_data is an array of data
                                        JSONArray verificationDataArray = (JSONArray) verificationDataObj;
                                        // Parse the data in the array as needed
                                        if (verificationDataArray.length() > 0) {
                                            JSONObject verificationData = verificationDataArray.getJSONObject(0);
                                            nameOfTheRsp = verificationData.optString("name_of_the_rcr");
                                            registration_no = verificationData.optString("registration_no");
                                            registered_address = verificationData.optString("registered_address");
                                            contact_no = verificationData.optString("contact_no");
                                            DisplayedRegistrationCertificateProminently = verificationData.optString("Displayed Registration Certificate Prominently");
                                            mulberry_area_acre = verificationData.optString("mulberry_area_(acre)");
                                            regularity_of_soil_testing_once_in_2_years = verificationData.optString("regularity_of_soil_testing_once_in_2_years");
                                            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = verificationData.optString("recommended_application_of_soil_inputs_(fertilizers_&_fym)_after_each_crop");
                                            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = verificationData.optString("is_pruning_&_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum");
                                            mulberry_variety = verificationData.optString("mulberry_variety");
                                            pest_disease_freeness_in_garden = verificationData.optString("pest_&_disease_freeness_in_garden");
                                            nutrient_deficiency_symptoms_in_leaves = verificationData.optString("nutrient_deficiency_symptoms_in_leaves");
                                            average_single_leaf_weight_g = verificationData.optString("average_single_leaf_weight_(g)");
                                            irrigation_facility = verificationData.optString("irrigation_facility");
                                            overall_status = verificationData.optString("overall_status");
                                            incubation_facility = verificationData.optString("incubation_facility");
                                            whether_rearing_house_is_as_per_recommended_plan = verificationData.optString("whether_rearing_house_is_as_per_recommended_plan");
                                            whether_equipments_appliances_as_per_seed_act_guidelines_available = verificationData.optString("whether_equipments_appliances_as_per_seed_act_guidelines_available");
                                            visual_health_of_chawki_worms = verificationData.optString("visual_health_of_chawki_worms");
                                            wt_of_100_ii_instar_larvae_g = verificationData.optString("wt_of_100_ii_instar_larvae_(g)");
                                            bed_spacing = verificationData.optString("bed_spacing");
                                            maintenance_of_temp_rh = verificationData.optString("maintenance_of_temp_&_rh");
                                            no_of_batches_detected = verificationData.optString("no._of_batches_detected");
                                            lot_details_of_source_dfls = verificationData.optString("lot_details_of_source_dfls");
                                            skilled_person_for_testing = verificationData.optString("skilled_person_for_testing");
                                            disinfection_register = verificationData.optString("disinfection_register");
                                            dfl_procurement_register = verificationData.optString("dfl_procurement_register");
                                            rearing_performance_register = verificationData.optString("rearing_performance_register");
                                            pebrine_testing_register = verificationData.optString("pebrine_testing_register");
                                            chawki_supply_register = verificationData.optString("chawki_supply_register");
                                            farm_management_register = verificationData.optString("farm_management_register");
                                            temp_rh_maintenance_register = verificationData.optString("temp_&_rh_maintenance_register");
                                            bill_book = verificationData.optString("bill_book");
                                            whether_following_self_certification_for_each_supply_lot = verificationData.optString("whether_following_self-certification_for_each_supply_lot");
                                            image = verificationData.optString("image");
                                            extra1 = verificationData.optString("extra1");
                                            extra2 = verificationData.optString("extra2");
                                            extra3 = verificationData.optString("extra3");
                                            extra4 = verificationData.optString("extra4");
                                            Log.e("checloooo",registration_no);
                                        }
                                    }




                                    model_rcr.add(new Model_RCR(sr_id, sr_status, status, verification_id,
                                            verification_status,
                                            sr_registration_no, sr_sirname, sr_applicant, sr_behalf_of,
                                            sr_parent_sirname,
                                            sr_parentsname, sr_gender, sr_social_status, sr_training_certificate,
                                            sr_training_place, sr_training_from, sr_training_to,
                                            sr_educational_certificate,
                                            sr_passport_photo, sr_aadhaar_number, sr_country_code, sr_mobile,
                                            sr_applicant_type, sr_unit_name, sr_village, sr_taluk, sr_state,
                                            sr_district, sr_pin, sr_address, sr_production_center,
                                            sr_production_center_taluk, sr_production_center_state,
                                            sr_production_center_district, sr_production_center_pin,
                                            sr_production_center_sector, sr_production_center_address, sr_produced,
                                            sr_unit, sr_production_level, sr_ownership_type, sr_establishment,
                                            sr_other_details, sr_quantity_silkworm, sr_quantity_silkworm2, sr_workers,
                                            sr_unit_two, sr_page1, sr_page2, sr_page3, sr_page4,
                                            sr_page5, sr_page6, sr_sign, sr_remark, created_at, updated_at, name
                                            , nameOfTheRsp,
                                            registration_no ,
                                            registered_address ,
                                            contact_no ,
                                            DisplayedRegistrationCertificateProminently ,
                                            mulberry_area_acre ,
                                            regularity_of_soil_testing_once_in_2_years,
                                            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop ,
                                            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum ,
                                            mulberry_variety  ,
                                            pest_disease_freeness_in_garden  ,
                                            nutrient_deficiency_symptoms_in_leaves  ,
                                            average_single_leaf_weight_g  ,
                                            irrigation_facility ,
                                            overall_status  ,
                                            incubation_facility  ,
                                            whether_rearing_house_is_as_per_recommended_plan  ,
                                            whether_equipments_appliances_as_per_seed_act_guidelines_available ,
                                            visual_health_of_chawki_worms  ,
                                            wt_of_100_ii_instar_larvae_g  ,
                                            bed_spacing  ,
                                            maintenance_of_temp_rh ,
                                            no_of_batches_detected  ,
                                            lot_details_of_source_dfls  ,
                                            skilled_person_for_testing ,
                                            disinfection_register  ,
                                            dfl_procurement_register  ,
                                            rearing_performance_register  ,
                                            pebrine_testing_register  ,
                                            chawki_supply_register  ,
                                            farm_management_register  ,
                                            temp_rh_maintenance_register  ,
                                            bill_book ,
                                            whether_following_self_certification_for_each_supply_lot  ,
                                            image  ,
                                            extra1 ,
                                            extra2 ,
                                            extra3,extra4 ));

                                    Log.e("modelData", String.valueOf(model_rcr));
                                }
                                Collections.reverse(model_rcr);

                                adapter_rcr = new Adapter_RCR(getApplicationContext(), model_rcr);
                                LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),
                                        LinearLayoutManager.VERTICAL, true);
                                layoutManager1.setStackFromEnd(true);
                                 layoutManager1.setReverseLayout(true);

                                rvRCRlist.setLayoutManager(layoutManager1);
                                rvRCRlist.setHasFixedSize(true);
                                rvRCRlist.setItemAnimator(new DefaultItemAnimator());
                                rvRCRlist.setAdapter(adapter_rcr);
                                adapter_rcr.set(SelectRCRList.this);

                            } else {
                                Toast.makeText(SelectRCRList.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();

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
    protected void onResume() {
        super.onResume();
        if (check.equals("12a")) {
            getOfficersJobfor12aOnresume();

//            Toast.makeText(this, "Resume12a", Toast.LENGTH_SHORT).show();
        } else {
            getOfficersJobOnresume();

//            Toast.makeText(this, "Resume12b", Toast.LENGTH_SHORT).show();

        }
    }

}