package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PreviewpageEnd12b extends AppCompatActivity {
    TextView tvedit, tvp1, tvp2, tvp3, tvp4, tvp5, tvp6, tvp7, tvp8, tvp9, tvp10, tvp11, tvp12, tvp13, tvp14, tvp15,
            tvp16, tvp17, tvp18, tvp19, tvp20, tvp21, tvp22, tvp23, tvp24, tvp25, tvp26, tvp27, tvp28, tvp29, tvp30, tvp31,
            tvpp1, tvpp2, tvpp3, tvpp4, tvpp5, tvpp6, tvpp7, tvpp8, tvpp9, tvpp10, tvpp11, tvpp12, tvpp13, tvpp14,
            tvpp15, tvpp16, tvpp17, tvpp18,
            tvpp19, tvpp20, tvpp21, tvpp22, tvpp23, tvpp24, tvpp25, tvpp26, tvpp27, tvpp28, tvpp29, tvpp30, tvpp31, tvAdd,
            tvCName, tvCId, tvCAddress, tvDate;
    LinearLayout ll1, ll2, ll3;
    RelativeLayout rledit, rlsubmitform, rlnext;
    ImageView imgedit, imgUser;
    String selectedValue, selectedValue1, selectedValue2, selectedValue3, selectedValue4, selectedValue5, selectedValue6,
            selectedValue7, selectedValue8, selectedValue9, selectedValue10, selectedValue11, selectedValue12,
            selectedValue13, selectedValue14, selectedValue15, selectedValue16, selectedValue17, selectedValue18,
            selectedValue19, selectedValue20, selectedValue21, selectedValue22, selectedValue23, selectedValue24,
            selectedValue25, selectedValue26, selectedValue27, tvv1, tvv2, tvv3, tvv4, tvv5, tvv6, tvv7, tvv8, tvv9, tvv10, tvv11,
            tvv12, tvv13, tvv14, tvv15, tvv16, tvv17, tvv18, tvv19, tvv20, tvv21, tvv22, tvv23, tvv24, tvv25,
            tvv26, tvv27, et1, et2, et3, et4, etAdd1, etAdd2, etAdd3, sp_sirname, sp_applicant, sp_registration_no,
            sp_mobile, sp_state, sp_address, sp_pin, sp_village, image_path, check, sp_taluk;
    String fullName, fullName1, registrationnumber, registrationnumber1, fullAddress, fullAddress1, sr_sirname, sr_applicant, sr_registration_no, sr_mobile, sr_state, sr_address, sr_pin, sr_village,
            sr_district, sr_taluk,combinedString1,combinedString;

    String
            nameOfTheRsp,
            registration_no,
            registered_address,
            contact_no,
            DisplayedRegistrationCertificateProminently,
            mulberry_area_acre,
            regularity_of_soil_testing_once_in_2_years,
            recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop,
            is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum,
            mulberry_variety,
            pest_disease_freeness_in_garden,
            nutrient_deficiency_symptoms_in_leaves,
            average_single_leaf_weight_g,
            irrigation_facility,
            overall_status,
            incubation_facility,
            whether_rearing_house_is_as_per_recommended_plan,
            whether_equipments_appliances_as_per_seed_act_guidelines_available,
            visual_health_of_chawki_worms,
            wt_of_100_ii_instar_larvae_g,
            bed_spacing,
            maintenance_of_temp_rh,
            no_of_batches_detected,
            lot_details_of_source_dfls,
            skilled_person_for_testing,
            disinfection_register,
            dfl_procurement_register,
            rearing_performance_register,
            pebrine_testing_register,
            chawki_supply_register,
            farm_management_register,
            temp_rh_maintenance_register,
            bill_book,
            whether_following_self_certification_for_each_supply_lot,
            image,
            extra1,
            extra2, extra4,
            extra3;
    int verification_id,sr_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previewpage_end12b);
        Utils.blackIconStatusBar(PreviewpageEnd12b.this, R.color.white);

        init();


        Intent intent = getIntent();
        combinedString = intent.getStringExtra("combinedString");

        verification_id = intent.getIntExtra("verification_id", verification_id);
        sr_status = intent.getIntExtra("verification_id", sr_status);
        registrationnumber = intent.getStringExtra("registrationnumber");
        fullName1 = intent.getStringExtra("fullName1");
        fullAddress1 = intent.getStringExtra("fullAddress1");
         nameOfTheRsp = intent.getStringExtra("nameOfTheRsp");
        registration_no = intent.getStringExtra("registration_no");
        registered_address = intent.getStringExtra("registered_address");
        contact_no = intent.getStringExtra("contact_no");
        DisplayedRegistrationCertificateProminently = intent.getStringExtra("DisplayedRegistrationCertificateProminently");
        mulberry_area_acre = intent.getStringExtra("mulberry_area_acre");
        regularity_of_soil_testing_once_in_2_years = intent.getStringExtra("regularity_of_soil_testing_once_in_2_years");
        recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop = intent.getStringExtra("recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop");
        is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum = intent.getStringExtra("is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum");
        mulberry_variety = intent.getStringExtra("mulberry_variety");
        pest_disease_freeness_in_garden = intent.getStringExtra("pest_disease_freeness_in_garden");
        nutrient_deficiency_symptoms_in_leaves = intent.getStringExtra("nutrient_deficiency_symptoms_in_leaves");
        average_single_leaf_weight_g = intent.getStringExtra("average_single_leaf_weight_g");
        irrigation_facility = intent.getStringExtra("irrigation_facility");
        overall_status = intent.getStringExtra("overall_status");
        incubation_facility = intent.getStringExtra("incubation_facility");
        whether_rearing_house_is_as_per_recommended_plan = intent.getStringExtra("whether_rearing_house_is_as_per_recommended_plan");
        whether_equipments_appliances_as_per_seed_act_guidelines_available = intent.getStringExtra("whether_equipments_appliances_as_per_seed_act_guidelines_available");
        visual_health_of_chawki_worms = intent.getStringExtra("visual_health_of_chawki_worms");
        wt_of_100_ii_instar_larvae_g = intent.getStringExtra("wt_of_100_ii_instar_larvae_g");
        bed_spacing = intent.getStringExtra("bed_spacing");
        maintenance_of_temp_rh = intent.getStringExtra("maintenance_of_temp_rh");
        no_of_batches_detected = intent.getStringExtra("no_of_batches_detected");
        lot_details_of_source_dfls = intent.getStringExtra("lot_details_of_source_dfls");
        skilled_person_for_testing = intent.getStringExtra("skilled_person_for_testing");
        disinfection_register = intent.getStringExtra("disinfection_register");
        dfl_procurement_register = intent.getStringExtra("dfl_procurement_register");
        rearing_performance_register = intent.getStringExtra("rearing_performance_register");
        pebrine_testing_register = intent.getStringExtra("pebrine_testing_register");
        chawki_supply_register = intent.getStringExtra("chawki_supply_register");
        farm_management_register = intent.getStringExtra("farm_management_register");
        temp_rh_maintenance_register = intent.getStringExtra("temp_rh_maintenance_register");
        bill_book = intent.getStringExtra("bill_book");
        whether_following_self_certification_for_each_supply_lot = intent.getStringExtra("whether_following_self_certification_for_each_supply_lot");
        image = intent.getStringExtra("image");
        extra1 = intent.getStringExtra("extra1");
        extra2 = intent.getStringExtra("extra2");
        extra3 = intent.getStringExtra("extra3");
        extra4 = intent.getStringExtra("extra4");
        check = intent.getStringExtra("check");
        sp_taluk = intent.getStringExtra("sp_taluk");
        image_path = intent.getStringExtra("image_path");
        selectedValue = intent.getStringExtra("selectedValue");
        selectedValue1 = intent.getStringExtra("selectedValue1");
        selectedValue2 = intent.getStringExtra("selectedValue2");
        selectedValue3 = intent.getStringExtra("selectedValue3");
        selectedValue4 = intent.getStringExtra("selectedValue4");
        selectedValue5 = intent.getStringExtra("selectedValue5");
        selectedValue6 = intent.getStringExtra("selectedValue6");
        selectedValue7 = intent.getStringExtra("selectedValue7");
        selectedValue8 = intent.getStringExtra("selectedValue8");
        selectedValue9 = intent.getStringExtra("selectedValue9");
        selectedValue10 = intent.getStringExtra("selectedValue10");
        selectedValue11 = intent.getStringExtra("selectedValue11");
        selectedValue12 = intent.getStringExtra("selectedValue12");
        selectedValue13 = intent.getStringExtra("selectedValue13");
        selectedValue14 = intent.getStringExtra("selectedValue14");
        selectedValue15 = intent.getStringExtra("selectedValue15");
        selectedValue16 = intent.getStringExtra("selectedValue16");
        selectedValue17 = intent.getStringExtra("selectedValue17");
        selectedValue18 = intent.getStringExtra("selectedValue18");
        selectedValue19 = intent.getStringExtra("selectedValue19");
        selectedValue20 = intent.getStringExtra("selectedValue20");
        selectedValue21 = intent.getStringExtra("selectedValue21");
        selectedValue22 = intent.getStringExtra("selectedValue22");
        selectedValue23 = intent.getStringExtra("selectedValue23");
        selectedValue24 = intent.getStringExtra("selectedValue24");
        selectedValue25 = intent.getStringExtra("selectedValue25");
        selectedValue27 = intent.getStringExtra("selectedValue26");
        tvv1 = intent.getStringExtra("tv1");
        tvv2 = intent.getStringExtra("tv2");
        tvv3 = intent.getStringExtra("tv3");
        tvv4 = intent.getStringExtra("tv4");
        tvv5 = intent.getStringExtra("tv5");
        tvv6 = intent.getStringExtra("tv6");
        tvv7 = intent.getStringExtra("tv7");
        tvv8 = intent.getStringExtra("tv8");
        tvv9 = intent.getStringExtra("tv9");
        tvv10 = intent.getStringExtra("tv10");
        tvv11 = intent.getStringExtra("tv11");
        tvv12 = intent.getStringExtra("tv12");
        tvv13 = intent.getStringExtra("tv13");
        tvv14 = intent.getStringExtra("tv14");
        tvv15 = intent.getStringExtra("tv15");
        tvv16 = intent.getStringExtra("tv16");
        tvv17 = intent.getStringExtra("tv17");
        tvv18 = intent.getStringExtra("tv18");
        tvv19 = intent.getStringExtra("tv19");
        tvv20 = intent.getStringExtra("tv20");
        tvv21 = intent.getStringExtra("tv21");
        tvv22 = intent.getStringExtra("tv22");
        tvv23 = intent.getStringExtra("tv23");
        tvv24 = intent.getStringExtra("tv24");
        tvv25 = intent.getStringExtra("tv25");
        tvv26 = intent.getStringExtra("tv26");
        tvv27 = intent.getStringExtra("tv27");
        et1 = intent.getStringExtra("et1");
        et2 = intent.getStringExtra("et2");
        et3 = intent.getStringExtra("et3");
        et4 = intent.getStringExtra("et4");
        etAdd1 = intent.getStringExtra("etAdd1");
        etAdd2 = intent.getStringExtra("etAdd2");
        etAdd3 = intent.getStringExtra("etAdd3");
        sr_sirname = intent.getStringExtra("sr_sirname");
        sr_applicant = intent.getStringExtra("sr_applicant");
        sr_registration_no = intent.getStringExtra("sr_registration_no");
        sr_mobile = intent.getStringExtra("sr_mobile");
        sr_state = intent.getStringExtra("name");
        sr_address = intent.getStringExtra("sr_address");
        sr_pin = intent.getStringExtra("sr_pin");
        sr_village = intent.getStringExtra("sr_village");
        sr_district = intent.getStringExtra("sr_district");
        sr_taluk = intent.getStringExtra("sr_taluk");
         registrationnumber = sr_registration_no;

        Log.e("extradata ",extra1+" "+extra2+" "+extra3);

//        Toast.makeText(this, ""+extra1+" "+extra2+" "+extra3, Toast.LENGTH_SHORT).show();

        Log.e("checkproblem", selectedValue + " " + selectedValue1 + " " + selectedValue2 + " " + selectedValue3 + " " + selectedValue4 + " " + selectedValue5
                + " " + selectedValue6 + " " + selectedValue7 + " " + selectedValue8 + " " + selectedValue9 + " " + selectedValue10 + " " + selectedValue11 + " " +
                selectedValue12 + " " + selectedValue13 + " " + selectedValue14 + " " + selectedValue15 + " " + selectedValue16 + " " + selectedValue17 + " " +
                selectedValue18 + " " + selectedValue19 + " " + selectedValue20 + " " + selectedValue21 + " " + selectedValue22 + " " + tvv1 + " " + tvv2 + " " + tvv3 + " " + tvv4
                + " " + tvv5 + " " + tvv6 + " " + tvv7 + " " + tvv8 + " " + tvv9 + " " + tvv10 + " " + tvv11 + " " + tvv12 + " " + tvv13 + " " + tvv14 + " " + tvv15 + " " + tvv16 + " " + tvv17 + " " + tvv18
                + " " + tvv19 + " " + tvv20 + " " + tvv21 + " " + tvv22 + " " + tvv23 + " " + tvv24 + " " + tvv25 + " " + tvv26 + " " + tvv27 + " " + et1 + " " + et2 + " " + et3 + " " + et4 + " " + etAdd1
                + " " + etAdd2 + " " + etAdd3);
        Log.e("checkbynumber", et1 + " " + et2 + " " + et3 + " " + et4 + " " + etAdd1
                + " " + etAdd2 + " " + etAdd3+" "+DisplayedRegistrationCertificateProminently);
//        Toast.makeText(this, "" + image_path, Toast.LENGTH_SHORT).show();
        Glide.with(this)
                .load(extra4)
                .placeholder(R.drawable.img_user) // Optional placeholder image while loading
//                .error(R.drawable.error) // Optional error image if loading fails
                .into(imgUser);

        tvp1.setText(mulberry_area_acre);
        tvp2.setText(regularity_of_soil_testing_once_in_2_years);
        tvp3.setText(recommended_application_of_soil_inputs_fertilizers_fym_after_each_crop);
        tvp4.setText(is_pruning_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum);
        tvp5.setText(mulberry_variety);
        tvp6.setText(pest_disease_freeness_in_garden);
        tvp7.setText(nutrient_deficiency_symptoms_in_leaves);
        tvp8.setText(average_single_leaf_weight_g);
        tvp9.setText(irrigation_facility);
        tvp10.setText(overall_status);
        tvp11.setText(incubation_facility);
        tvp12.setText(whether_rearing_house_is_as_per_recommended_plan);
        tvp13.setText(whether_equipments_appliances_as_per_seed_act_guidelines_available);
        tvp15.setText(visual_health_of_chawki_worms);

        tvp16.setText(wt_of_100_ii_instar_larvae_g);
        tvp17.setText(bed_spacing);
        tvp18.setText(maintenance_of_temp_rh);
        tvp19.setText(no_of_batches_detected);
        tvp20.setText(lot_details_of_source_dfls);
        tvp21.setText(skilled_person_for_testing);
        tvp22.setText(disinfection_register);
        tvp23.setText(dfl_procurement_register);
        tvp24.setText(rearing_performance_register);
        tvp25.setText(pebrine_testing_register);
        tvp26.setText(chawki_supply_register);
        tvp27.setText(farm_management_register);
        tvp28.setText(temp_rh_maintenance_register);
        tvp29.setText(bill_book);
        tvp30.setText(whether_following_self_certification_for_each_supply_lot);
        tvp31.setText(DisplayedRegistrationCertificateProminently);
        tvpp28.setText(extra1);
        tvpp29.setText(extra2);
        tvpp30.setText(extra3);
        tvCName.setText(nameOfTheRsp);
        tvCId.setText(registration_no);
        tvCAddress.setText(registered_address);


         if (extra1.equals("null")   || extra1.trim().isEmpty()) {
            ll1.setVisibility(View.GONE);
        } else {
            ll1.setVisibility(View.VISIBLE);
            tvAdd.setVisibility(View.VISIBLE);
        }

        if (extra2.equals("null") || extra2.trim().isEmpty()) {
            ll2.setVisibility(View.GONE);
        } else {
            ll2.setVisibility(View.VISIBLE);
            tvAdd.setVisibility(View.VISIBLE);
        }

        if (extra3.equals("null") || extra3.trim().isEmpty()) {
            ll3.setVisibility(View.GONE);
        } else {
            ll3.setVisibility(View.VISIBLE);
            tvAdd.setVisibility(View.VISIBLE);
        }

// Check if all three are empty

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.equals("12a")) {
                    Intent i = new Intent(getApplicationContext(), Account_Details.class);
                    int status = 2,checkForm=1;
                    i.putExtra("checkForm",checkForm);
                    i.putExtra("fullName1", nameOfTheRsp);
                    i.putExtra("verification_id", verification_id);

                    i.putExtra("fullAddress1", registered_address);
                    i.putExtra("registrationnumber", registration_no);

                    getOfficersJob(verification_id, status);
                    startActivity( i);
                } else {
                    Intent i = new Intent(getApplicationContext(), Account_details_ChawkiRearer_Activity.class);
                    int status = 2,checkForm=1;
                    i.putExtra("checkForm",checkForm);
                    i.putExtra("fullName1", nameOfTheRsp);
                    i.putExtra("verification_id", verification_id);

                    i.putExtra("fullAddress1", registered_address);
                    i.putExtra("registrationnumber", registration_no);

                    getOfficersJob(verification_id, status);
                    startActivity( i);
                }

                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                imgedit.startAnimation(myAnim);

            }
        });
        tvedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.equals("12a")) {
                    Intent i = new Intent(getApplicationContext(), Account_Details.class);
                    int status = 2,checkForm=1;
                    i.putExtra("checkForm",checkForm);
                    i.putExtra("fullName1", nameOfTheRsp);
                    i.putExtra("verification_id", verification_id);

                    i.putExtra("fullAddress1", registered_address);
                    i.putExtra("registrationnumber", registration_no);

                    getOfficersJob(verification_id, status);
                    startActivity( i);
                } else {
                    Intent i = new Intent(getApplicationContext(), Account_details_ChawkiRearer_Activity.class);
                    int status = 2,checkForm=1;
                    i.putExtra("checkForm",checkForm);
                    i.putExtra("fullName1", nameOfTheRsp);
                    i.putExtra("verification_id", verification_id);

                    i.putExtra("fullAddress1", registered_address);
                    i.putExtra("registrationnumber", registration_no);

                    getOfficersJob(verification_id, status);
                    startActivity( i);
                }
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                tvedit.startAnimation(myAnim);


            }
        });
        rlsubmitform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                getPDF(verification_id);
                Intent intent = new Intent(getApplicationContext(), PDf_Activity.class);
                intent.putExtra("verification_id",verification_id);
                intent.putExtra("combinedString",combinedString);
                intent.putExtra("sr_status",sr_status);

                startActivity(intent);
                finish();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlsubmitform.startAnimation(myAnim);

            }
        });

//        displayCurrentDateAsToast(this);
dataFormat();
    }

    private void displayCurrentDateAsToast(Context context) {
        // Get the current date
        Date currentDate = new Date();

        // Define a date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Format the current date as a string
        String formattedDate = dateFormat.format(currentDate);
        tvDate.setText(formattedDate);
        // Display the formatted date as a Toast
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
        combinedString1 = dayNameAbbreviated + ", " + dayOfMonth + " " + monthName + " " + year + ", " + formattedTime;

        // Display the formatted date and time
        TextView tvDay = findViewById(R.id.tvday);
        tvDate.setText(combinedString1);
    }


    void init() {

        rledit = findViewById(R.id.rledit);
        rlsubmitform = findViewById(R.id.rlsubmitform);
        imgedit = findViewById(R.id.imgedit);
        imgUser = findViewById(R.id.imgUser);
        tvedit = findViewById(R.id.tvedit);
        tvp1 = findViewById(R.id.tvp1);
        tvp2 = findViewById(R.id.tvp2);
        tvp3 = findViewById(R.id.tvp3);
        tvp4 = findViewById(R.id.tvp4);
        tvp5 = findViewById(R.id.tvp5);
        tvp6 = findViewById(R.id.tvp6);
        tvp7 = findViewById(R.id.tvp7);
        tvp8 = findViewById(R.id.tvp8);
        tvp9 = findViewById(R.id.tvp9);
        tvp10 = findViewById(R.id.tvp10);
        tvp11 = findViewById(R.id.tvp11);
        tvp12 = findViewById(R.id.tvp12);
        tvp13 = findViewById(R.id.tvp13);
        tvp14 = findViewById(R.id.tvp14);
        tvp15 = findViewById(R.id.tvp15);
        tvp16 = findViewById(R.id.tvp16);
        tvp17 = findViewById(R.id.tvp17);
        tvp18 = findViewById(R.id.tvp18);
        tvp19 = findViewById(R.id.tvp19);
        tvp20 = findViewById(R.id.tvp20);
        tvp21 = findViewById(R.id.tvp21);
        tvp22 = findViewById(R.id.tvp22);
        tvp23 = findViewById(R.id.tvp23);
        tvp24 = findViewById(R.id.tvp24);
        tvp25 = findViewById(R.id.tvp25);
        tvp26 = findViewById(R.id.tvp26);
        tvp27 = findViewById(R.id.tvp27);
        tvp28 = findViewById(R.id.tvp28);
        tvp29 = findViewById(R.id.tvp29);
        tvp30 = findViewById(R.id.tvp30);
        tvp31 = findViewById(R.id.tvp31);
        tvAdd = findViewById(R.id.tvAdd);
        tvCName = findViewById(R.id.tvCName);
        tvCId = findViewById(R.id.tvCId);
        tvCAddress = findViewById(R.id.tvCAddress);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        tvpp28 = findViewById(R.id.tvpp28);
        tvpp29 = findViewById(R.id.tvpp29);
        tvpp30 = findViewById(R.id.tvpp30);
        tvDate = findViewById(R.id.tvDate);
    }


    void getOfficersJob(int verification_id, int status) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.new_progress);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("verification_id", verification_id);
            requestBody.put("status", status);

            JSONArray verificationDataArray = new JSONArray();
            JSONObject verificationDataObject = new JSONObject();



            verificationDataObject.put("name_of_the_rsp", "");
            verificationDataObject.put("registration_no.", "");
            verificationDataObject.put("registered_address", "");
            verificationDataObject.put("contact_no.", "");
            verificationDataObject.put("Displayed Registration Certificate Prominently", "");
            verificationDataObject.put("mulberry_area_(acre)", "");
            verificationDataObject.put("regularity_of_soil_testing_once_in_2_years", "");
            verificationDataObject.put("recommended_application_of_soil_inputs_(fertilizers_&_fym)_after_each_crop", "");
            verificationDataObject.put("is_pruning_&_harvest_schedule_followed_to_enable_a_minimum_of_32_crops_annum", "");
            verificationDataObject.put("mulberry_variety", "");
            verificationDataObject.put("pest_&_disease_freeness_in_garden", "");
            verificationDataObject.put("nutrient_deficiency_symptoms_in_leaves", "");
            verificationDataObject.put("average_single_leaf_weight_(g)", "");
            verificationDataObject.put("irrigation_facility", "");
            verificationDataObject.put("overall_status", "");
            verificationDataObject.put("incubation_facility", "");
            verificationDataObject.put("whether_rearing_house_is_as_per_recommended_plan", "");
            verificationDataObject.put("whether_equipments_appliances_as_per_seed_act_guidelines_available", "");
            verificationDataObject.put("incubation_facility", "");
            verificationDataObject.put("visual_health_of_chawki_worms", "");
            verificationDataObject.put("wt_of_100_ii_instar_larvae_(g)", "");
            verificationDataObject.put("bed_spacing", "");
            verificationDataObject.put("maintenance_of_temp_&_rh", "");
            verificationDataObject.put("no._of_batches_detected", "");
            verificationDataObject.put("lot_details_of_source_dfls", "");
            verificationDataObject.put("skilled_person_for_testing", "");
            verificationDataObject.put("disinfection_register", "");
            verificationDataObject.put("dfl_procurement_register", "");
            verificationDataObject.put("rearing_performance_register", "");
            verificationDataObject.put("pebrine_testing_register", "");
            verificationDataObject.put("chawki_supply_register", "");
            verificationDataObject.put("farm_management_register", "");
            verificationDataObject.put("temp_&_rh_maintenance_register", "");
            verificationDataObject.put("bill_book", "");
            verificationDataObject.put("whether_following_self-certification_for_each_supply_lot", "");
            verificationDataObject.put("image", "");
            verificationDataObject.put("extra1", "");
            verificationDataObject.put("extra2", "");
            verificationDataObject.put("extra3", "");

            verificationDataArray.put(verificationDataObject);
            requestBody.put("verifcation_data", verificationDataArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("responseofApi", String.valueOf(requestBody));
        // Create a new request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiData.saveVerification, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();
                        try {
                            if (response.getBoolean("status") == true) {

                                Toast.makeText(PreviewpageEnd12b.this, "" + response.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(PreviewpageEnd12b.this, "" + response.getString("message"),
                                        Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        // Display an error message or retry the request
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

}