package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class Preview_page extends AppCompatActivity {
    RelativeLayout rledit, rlsubmitform;
    TextView tvedit, tvp1, tvp2, tvp3, tvp4, tvp5, tvp6, tvp7, tvp8, tvp9, tvp10, tvp11, tvp12, tvp13, tvp14, tvp15,
            tvp16, tvp17, tvp18, tvp19, tvp20, tvp21, tvp22, tvp23, tvp24, tvp25, tvp26, tvp27, tvpp1, tvpp2, tvpp3, tvpp4,
            tvpp5, tvpp6, tvpp7, tvpp8, tvpp9, tvpp10, tvpp11, tvpp12, tvpp13, tvpp14, tvpp15, tvpp16, tvpp17, tvpp18,
            tvpp19, tvpp20, tvpp21, tvpp22, tvpp23, tvpp24, tvpp25, tvpp26, tvpp27, tvpp28, tvpp29, tvpp30, tvpp31,tvpp32,
            tvAdd, tvCName, tvCId, tvCAddress,tvDate;
    LinearLayout ll1, ll2, ll3;
    ImageView imgedit,imgUser;
    String selectedValue, selectedValue1, selectedValue2, selectedValue3, selectedValue4, selectedValue5, selectedValue6,
            selectedValue7, selectedValue8, selectedValue9, selectedValue10, selectedValue11, selectedValue12,
            selectedValue13, selectedValue14, selectedValue15, selectedValue16, selectedValue17, selectedValue18,
            selectedValue19, selectedValue20, selectedValue21, selectedValue22, selectedValue23, tvv1, tvv2, tvv3,
            tvv4, tvv5, tvv6, tvv7, tvv8, tvv9, tvv10, tvv11, tvv12, tvv13, tvv14, tvv15, tvv16, tvv17, tvv18, tvv19,
            tvv20, tvv21, tvv22, tvv23, tvv24, tvv25, tvv26, tvv27, et1, et2, et3, et4,et5, etAdd1, etAdd2, etAdd3,
            sp_sirname, sp_applicant, sp_registration_no, sp_mobile, sp_state, sp_address, sp_pin, sp_village,
            sp_taluk,combinedString,combinedString1, image_path, image_path1;
    String fullName,fullName1,registrationnumber,registrationnumber2,fullAddress,fullAddress1;
    String   nameOfTheRsp  , registration_no , registered_address,
            contact_no , displayed_ther_registration_certificate_prominently ,
            number_of_regd_seed_cocoon_producers_with_rsp ,
            whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop,
            whether_seed_cocoons_are_purchased_as_per_norms ,
            whether_seed_cocoons_are_properly_sorted_and_sex_separated ,
            whether_pupal_testing_is_done_for_pebrine, sex_separation_efficiency ,
            male_female_ratio, whether_each_parental_pupae_are_kept_in_separate_room,
            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms,
            whether_seeds_are_properly_washed_dried_and_packed_as_recommended ,
            whether_male_moths_are_preserved_in_cold_room, whether_cold_rooms_have_uninterrupted_power_supply
            , whether_proper_temp_humidity_are_maintained_for_oviposition, whether_incubation_chamber_available,
            whether_seeds_are_properly_labeled_for_race_lot_no_dol, no_of_batches_detected,
            lot_details_of_source_seed_cocoon,
            skilled_person_for_testing
            , disinfection_register, seed_cocoon_procurement_register, cocoon_processing_register,
            pebrine_testing_register , dfl_supply_register, test_hatching_register_for_dfls_supplied,
            temp_rh_maintenance_register, bill_book_for_dfl_supply,
            whether_following_self_certification_for_each_supply_lot,
            whether_quarterly_reports_are_being_regularly_submitted_to_nsso, image , extra1, extra2, extra3;
    int verification_id,checkForm,sp_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_page);
        Utils.blackIconStatusBar(Preview_page.this, R.color.white);

        init();

        Intent intent = getIntent();
        registrationnumber2=intent.getStringExtra("registrationnumber" );
        fullName1=intent.getStringExtra("fullName1" );
        fullName=intent.getStringExtra("fullName" );
        checkForm=intent.getIntExtra("checkForm" ,0);
        sp_status=intent.getIntExtra("sp_status" ,0);
//        Toast.makeText(this, ""+sp_status, Toast.LENGTH_SHORT).show();
        fullAddress1=intent.getStringExtra("fullAddress1" );
        fullAddress=intent.getStringExtra("fullAddress" );
         nameOfTheRsp = intent.getStringExtra("nameOfTheRsp");
        registration_no = intent.getStringExtra("registration_no");
        registered_address = intent.getStringExtra("registered_address");
        contact_no = intent.getStringExtra("contact_no");
        displayed_ther_registration_certificate_prominently = intent.getStringExtra("displayed_ther_registration_certificate_prominently");
        number_of_regd_seed_cocoon_producers_with_rsp = intent.getStringExtra("number_of_regd_seed_cocoon_producers_with_rsp");
        whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop = intent.getStringExtra("whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop");
        whether_seed_cocoons_are_purchased_as_per_norms = intent.getStringExtra("whether_seed_cocoons_are_purchased_as_per_norms");
        whether_seed_cocoons_are_properly_sorted_and_sex_separated = intent.getStringExtra("whether_seed_cocoons_are_properly_sorted_and_sex_separated");
        whether_pupal_testing_is_done_for_pebrine = intent.getStringExtra("whether_pupal_testing_is_done_for_pebrine");
        sex_separation_efficiency = intent.getStringExtra("sex_separation_efficiency");
        male_female_ratio = intent.getStringExtra("male_female_ratio");
        whether_each_parental_pupae_are_kept_in_separate_room = intent.getStringExtra("whether_each_parental_pupae_are_kept_in_separate_room");
        whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms = intent.getStringExtra("whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms");
        whether_seeds_are_properly_washed_dried_and_packed_as_recommended = intent.getStringExtra("whether_seeds_are_properly_washed_dried_and_packed_as_recommended");
        whether_male_moths_are_preserved_in_cold_room = intent.getStringExtra("whether_male_moths_are_preserved_in_cold_room");
        whether_cold_rooms_have_uninterrupted_power_supply = intent.getStringExtra("whether_cold_rooms_have_uninterrupted_power_supply");
        whether_proper_temp_humidity_are_maintained_for_oviposition = intent.getStringExtra("whether_proper_temp_humidity_are_maintained_for_oviposition");
        whether_incubation_chamber_available = intent.getStringExtra("whether_incubation_chamber_available");
        whether_seeds_are_properly_labeled_for_race_lot_no_dol = intent.getStringExtra("whether_seeds_are_properly_labeled_for_race_lot_no_dol");
        no_of_batches_detected = intent.getStringExtra("no_of_batches_detected");
        lot_details_of_source_seed_cocoon = intent.getStringExtra("lot_details_of_source_seed_cocoon");
        skilled_person_for_testing = intent.getStringExtra("skilled_person_for_testing");
        disinfection_register = intent.getStringExtra("disinfection_register");
        seed_cocoon_procurement_register = intent.getStringExtra("seed_cocoon_procurement_register");
        cocoon_processing_register = intent.getStringExtra("cocoon_processing_register");
        pebrine_testing_register = intent.getStringExtra("pebrine_testing_register");
        dfl_supply_register = intent.getStringExtra("dfl_supply_register");
        test_hatching_register_for_dfls_supplied = intent.getStringExtra("test_hatching_register_for_dfls_supplied");
        temp_rh_maintenance_register = intent.getStringExtra("temp_rh_maintenance_register");
        bill_book_for_dfl_supply = intent.getStringExtra("bill_book_for_dfl_supply");
        whether_following_self_certification_for_each_supply_lot = intent.getStringExtra("whether_following_self_certification_for_each_supply_lot");
        whether_quarterly_reports_are_being_regularly_submitted_to_nsso = intent.getStringExtra("whether_quarterly_reports_are_being_regularly_submitted_to_nsso");
        image = intent.getStringExtra("image");
        extra1 = intent.getStringExtra("extra1");
        extra2 = intent.getStringExtra("extra2");
        extra3 = intent.getStringExtra("extra3");
        image_path = intent.getStringExtra("image_path");
        image_path1 = intent.getStringExtra("image_path1");
        combinedString = intent.getStringExtra("combinedString");
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
        et1 = intent.getStringExtra("et1");
        et2 = intent.getStringExtra("et2");
        et3 = intent.getStringExtra("et3");
        et4 = intent.getStringExtra("et4");
        et5 = intent.getStringExtra("et5");
        etAdd1 = intent.getStringExtra("etAdd1");
        etAdd2 = intent.getStringExtra("etAdd2");
        etAdd3 = intent.getStringExtra("etAdd3");
        Log.e("checkNAmeLast",fullName+" "+fullAddress+" "+sp_registration_no);

        sp_registration_no = intent.getStringExtra("sp_registration_no");
        verification_id = intent.getIntExtra("verification_id",0);



        if ((etAdd1 == null || etAdd1.trim().isEmpty()) &&
                (etAdd2 == null || etAdd2.trim().isEmpty()) &&
                (etAdd3 == null || etAdd3.trim().isEmpty())) {
            // All three are empty
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.GONE);
            ll3.setVisibility(View.GONE);
            tvAdd.setVisibility(View.GONE);
        } else {
            // At least one is not empty

            if (etAdd1 != null && !etAdd1.trim().isEmpty()) {
                ll1.setVisibility(View.VISIBLE);
                tvAdd.setVisibility(View.VISIBLE);
            }

            if (etAdd2 != null && !etAdd2.trim().isEmpty()) {
                ll2.setVisibility(View.VISIBLE);
                tvAdd.setVisibility(View.VISIBLE);
            }

            if (etAdd3 != null && !etAdd3.trim().isEmpty()) {
                ll3.setVisibility(View.VISIBLE);
                tvAdd.setVisibility(View.VISIBLE);
            }

        }


        Log.e("checkproblem", selectedValue + " " + selectedValue1 + " " + selectedValue2 + " " + selectedValue3 + " " + selectedValue4 + " " + selectedValue5
                + " " + selectedValue6 + " " + selectedValue7 + " " + selectedValue8 + " " + selectedValue9 + " " + selectedValue10 + " " + selectedValue11 + " " +
                selectedValue12 + " " + selectedValue13 + " " + selectedValue14 + " " + selectedValue15 + " " + selectedValue16 + " " + selectedValue17 + " " +
                selectedValue18 + " " + selectedValue19 + " " + selectedValue20 + " " + selectedValue21 + " " + selectedValue22 + " " + tvv1 + " " + tvv2 + " " + tvv3 + " " + tvv4
                + " " + tvv5 + " " + tvv6 + " " + tvv7 + " " + tvv8 + " " + tvv9 + " " + tvv10 + " " + tvv11 + " " + tvv12 + " " + tvv13 + " " + tvv14 + " " + tvv15 + " " + tvv16 + " " + tvv17 + " " + tvv18
                + " " + tvv19 + " " + tvv20 + " " + tvv21 + " " + tvv22 + " " + tvv23 + " " + tvv24 + " " + tvv25 + " " + tvv26 + " " + tvv27 + " " + et1 + " " + et2 + " " + et3 + " " + et4 + " " + etAdd1
                + " " + etAdd2 + " " + etAdd3);

        Log.e("checkbynumber", et1 + " " + et2 + " " + et3 + " " + et4 + " " + etAdd1
                + " " + etAdd2 + " " + etAdd3);



        Glide.with(this)
                .load(image_path)
                .placeholder(R.drawable.img_user) // Optional placeholder image while loading
//                .error(R.drawable.error) // Optional error image if loading fails
                .into(imgUser);

        tvpp1.setText(selectedValue);
        tvpp2.setText(selectedValue1);
        tvpp3.setText(selectedValue2);
        tvpp4.setText(selectedValue3);
        tvpp5.setText(et2);
        tvpp6.setText(et3);
        tvpp7.setText(selectedValue4);
        tvpp8.setText(selectedValue5);
        tvpp9.setText(selectedValue6);
        tvpp10.setText(selectedValue7);
        tvpp11.setText(selectedValue8);
        tvpp12.setText(selectedValue9);
        tvpp13.setText(selectedValue10);
        tvpp14.setText(selectedValue11);
        tvpp15.setText(et4);
        tvpp16.setText(et5);
        tvpp17.setText(selectedValue12);
        tvpp18.setText(selectedValue13);
        tvpp19.setText(selectedValue14);
        tvpp20.setText(selectedValue15);
        tvpp21.setText(selectedValue16);
        tvpp22.setText(selectedValue17);
        tvpp23.setText(selectedValue18);
        tvpp24.setText(selectedValue19);
        tvpp25.setText(selectedValue20);
        tvpp26.setText(selectedValue21);
        tvpp27.setText(selectedValue22);
        tvpp28.setText(etAdd1);
        tvpp29.setText(etAdd2);
        tvpp30.setText(etAdd3);
        tvpp31.setText(selectedValue23);
        tvpp32.setText(et1);
        if (checkForm==1) {
            // At least one of the strings is null, start Activity2
            tvCName.setText(fullName);
            tvCId.setText(sp_registration_no);
            tvCAddress.setText(fullAddress);

//            Toast.makeText(this, "NEW", Toast.LENGTH_SHORT).show();

        } else {
            // All strings are not null, proceed with setting the text views
            tvCName.setText(fullName);
            tvCId.setText(sp_registration_no);
            tvCAddress.setText(fullAddress);
//            Toast.makeText(this, "OLD", Toast.LENGTH_SHORT).show();
        }


        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Account_Details.class);
                int checkForm=1;
                i.putExtra("checkForm",checkForm);
                i.putExtra("sp_status",sp_status);
                i.putExtra("fullName", fullName);
                i.putExtra("verification_id", verification_id);
                i.putExtra("fullAddress", fullAddress);
                i.putExtra("sp_registration_no", sp_registration_no);
                int status = 2;
//                getOfficersJob(verification_id, status);
                startActivity( i);
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                imgedit.startAnimation(myAnim);
                finish();
            }
        });
        tvedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Account_Details.class);
                int checkForm=1;
                i.putExtra("checkForm",checkForm);
                i.putExtra("fullName", fullName);
                i.putExtra("sp_status", sp_status);
                i.putExtra("fullAddress", fullAddress);
                i.putExtra("verification_id", verification_id);
                i.putExtra("sp_registration_no", sp_registration_no);
                int status = 2;
//                getOfficersJob(verification_id, status);
                startActivity( i);
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                tvedit.startAnimation(myAnim);
                finish();
            }
        });
        rlsubmitform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getPDF(verification_id);
              if (sp_status==1){
                  AlertDialog();
              } else {
                  AlertDialogRenew();
              }
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
        tvpp1 = findViewById(R.id.tvpp1);
        tvpp2 = findViewById(R.id.tvpp2);
        tvpp3 = findViewById(R.id.tvpp3);
        tvpp4 = findViewById(R.id.tvpp4);
        tvpp5 = findViewById(R.id.tvpp5);
        tvpp6 = findViewById(R.id.tvpp6);
        tvpp7 = findViewById(R.id.tvpp7);
        tvpp8 = findViewById(R.id.tvpp8);
        tvpp9 = findViewById(R.id.tvpp9);
        tvpp10 = findViewById(R.id.tvpp10);
        tvpp11 = findViewById(R.id.tvpp11);
        tvpp12 = findViewById(R.id.tvpp12);
        tvpp13 = findViewById(R.id.tvpp13);
        tvpp14 = findViewById(R.id.tvpp14);
        tvpp15 = findViewById(R.id.tvpp15);
        tvpp16 = findViewById(R.id.tvpp16);
        tvpp17 = findViewById(R.id.tvpp17);
        tvpp18 = findViewById(R.id.tvpp18);
        tvpp19 = findViewById(R.id.tvpp19);
        tvpp20 = findViewById(R.id.tvpp20);
        tvpp21 = findViewById(R.id.tvpp21);
        tvpp22 = findViewById(R.id.tvpp22);
        tvpp23 = findViewById(R.id.tvpp23);
        tvpp24 = findViewById(R.id.tvpp24);
        tvpp25 = findViewById(R.id.tvpp25);
        tvpp26 = findViewById(R.id.tvpp26);
        tvpp27 = findViewById(R.id.tvpp27);
        tvpp28 = findViewById(R.id.tvpp28);
        tvpp29 = findViewById(R.id.tvpp29);
        tvpp30 = findViewById(R.id.tvpp30);
        tvpp31 = findViewById(R.id.tvpp31);
        tvpp32 = findViewById(R.id.tvpp32);
        tvAdd = findViewById(R.id.tvAdd);
        tvCName = findViewById(R.id.tvCName);
        tvCId = findViewById(R.id.tvCId);
        tvCAddress = findViewById(R.id.tvCAddress);
        tvDate = findViewById(R.id.tvDate);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
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



            if (checkForm==1){
                verificationDataObject.put("name_of_the_rsp", fullName);
                verificationDataObject.put("registration_no.", sp_registration_no);
                verificationDataObject.put("registered_address", fullAddress);
//                Toast.makeText(this, "final", Toast.LENGTH_SHORT).show();
            } else {
                verificationDataObject.put("name_of_the_rsp", fullName);
                verificationDataObject.put("registration_no.", sp_registration_no);
                verificationDataObject.put("registered_address", fullAddress);
//                Toast.makeText(this, "incomplete", Toast.LENGTH_SHORT).show();

            }
//       Toast.makeText(this, "incomplete", Toast.LENGTH_SHORT).show();



            verificationDataObject.put("contact_no.", sp_mobile);
            verificationDataObject.put("displayed_registration_certificate_prominently", selectedValue23);
            verificationDataObject.put("number_of_regd._seed_cocoon_producers_with_rsp", et1);
            verificationDataObject.put("whether_seed_cocoons_are_purchased_from_regd._seed_cocoon_producers_for_every_crop", selectedValue);
            verificationDataObject.put("whether_seed_cocoons_are_purchased_as_per_norms", selectedValue1);
            verificationDataObject.put("whether_seed_cocoons_are_properly_sorted_and_sex_separated", selectedValue2);
            verificationDataObject.put("whether_pupal_testing_is_done_for_pebrine", selectedValue3);
            verificationDataObject.put("sex-separation_efficiency", et2);
            verificationDataObject.put("male:_female_ratio", et3);
            verificationDataObject.put("whether_each_parental_pupae_are_kept_in_separate_room", selectedValue4);
            verificationDataObject.put("whether_proper_temp_&_rh_maintained_in_cocoon_pupae_&_oviposition_rooms", selectedValue5);
            verificationDataObject.put("whether_seeds_are_properly_washed,_dried_and_packed_as_recommended", selectedValue6);
            verificationDataObject.put("whether_male_moths_are_preserved_in_5-7_cold_room", selectedValue7);
            verificationDataObject.put("whether_cold_rooms_have_uninterrupted_power_supply", selectedValue8);
            verificationDataObject.put("whether_proper_temp_&_humidity_are_maintained_for_oviposition", selectedValue9);
            verificationDataObject.put("whether_incubation_chamber_available", selectedValue10);
            verificationDataObject.put("whether_seeds_are_properly_labeled_for_race,_lot_no,_dol", selectedValue11);
            verificationDataObject.put("no._of_batches_detected", et4);

            verificationDataObject.put("lot_details_of_source_seed_cocoon", et5);
            verificationDataObject.put("skilled_person_for_testing", selectedValue12);
            verificationDataObject.put("disinfection_register", selectedValue13);
            verificationDataObject.put("seed_cocoon_procurement_register", selectedValue14);
            verificationDataObject.put("cocoon_processing_register", selectedValue15);
            verificationDataObject.put("pebrine_testing_register", selectedValue16);
            verificationDataObject.put("dfl_supply_register", selectedValue17);
            verificationDataObject.put("test_hatching_register_for_dfls_supplied", selectedValue18);
            verificationDataObject.put("temp._&_rh_maintenance_register", selectedValue19);
            verificationDataObject.put("bill_book_for_dfl_supply", selectedValue20);
            verificationDataObject.put("whether_following_self-certification_for_each_supply_lot", selectedValue21);
            verificationDataObject.put("whether_quarterly_reports_are_being_regularly_submitted_to_nsso", selectedValue22);
            verificationDataObject.put("image", image_path1);
            verificationDataObject.put("extra1", etAdd1 );
            verificationDataObject.put("extra2", etAdd2);
            verificationDataObject.put("extra3", etAdd3);
            verificationDataObject.put("extra4", image_path);

            verificationDataArray.put(verificationDataObject);
            requestBody.put("verification_data", verificationDataArray);

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

                                Toast.makeText(Preview_page.this, "" + response.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Preview_page.this, "" + response.getString("message"),
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
    void getOfficersJobRenew(int verification_id, int status) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("verification_id", verification_id);
            requestBody.put("status", status);

            JSONArray verificationDataArray = new JSONArray();
            JSONObject verificationDataObject = new JSONObject();



            if (checkForm==1){
                verificationDataObject.put("name_of_the_rsp", fullName);
                verificationDataObject.put("registration_no.", sp_registration_no);
                verificationDataObject.put("registered_address", fullAddress);
//                Toast.makeText(this, "final", Toast.LENGTH_SHORT).show();
            } else {
                verificationDataObject.put("name_of_the_rsp", fullName);
                verificationDataObject.put("registration_no.", sp_registration_no);
                verificationDataObject.put("registered_address", fullAddress);
//                Toast.makeText(this, "incomplete", Toast.LENGTH_SHORT).show();

            }
//       Toast.makeText(this, "incomplete", Toast.LENGTH_SHORT).show();



            verificationDataObject.put("contact_no.", sp_mobile);
            verificationDataObject.put("displayed_registration_certificate_prominently", selectedValue23);
            verificationDataObject.put("number_of_regd._seed_cocoon_producers_with_rsp", et1);
            verificationDataObject.put("whether_seed_cocoons_are_purchased_from_regd._seed_cocoon_producers_for_every_crop", selectedValue);
            verificationDataObject.put("whether_seed_cocoons_are_purchased_as_per_norms", selectedValue1);
            verificationDataObject.put("whether_seed_cocoons_are_properly_sorted_and_sex_separated", selectedValue2);
            verificationDataObject.put("whether_pupal_testing_is_done_for_pebrine", selectedValue3);
            verificationDataObject.put("sex-separation_efficiency", et2);
            verificationDataObject.put("male:_female_ratio", et3);
            verificationDataObject.put("whether_each_parental_pupae_are_kept_in_separate_room", selectedValue4);
            verificationDataObject.put("whether_proper_temp_&_rh_maintained_in_cocoon_pupae_&_oviposition_rooms", selectedValue5);
            verificationDataObject.put("whether_seeds_are_properly_washed,_dried_and_packed_as_recommended", selectedValue6);
            verificationDataObject.put("whether_male_moths_are_preserved_in_5-7_cold_room", selectedValue7);
            verificationDataObject.put("whether_cold_rooms_have_uninterrupted_power_supply", selectedValue8);
            verificationDataObject.put("whether_proper_temp_&_humidity_are_maintained_for_oviposition", selectedValue9);
            verificationDataObject.put("whether_incubation_chamber_available", selectedValue10);
            verificationDataObject.put("whether_seeds_are_properly_labeled_for_race,_lot_no,_dol", selectedValue11);
            verificationDataObject.put("no._of_batches_detected", et4);

            verificationDataObject.put("lot_details_of_source_seed_cocoon", et5);
            verificationDataObject.put("skilled_person_for_testing", selectedValue12);
            verificationDataObject.put("disinfection_register", selectedValue13);
            verificationDataObject.put("seed_cocoon_procurement_register", selectedValue14);
            verificationDataObject.put("cocoon_processing_register", selectedValue15);
            verificationDataObject.put("pebrine_testing_register", selectedValue16);
            verificationDataObject.put("dfl_supply_register", selectedValue17);
            verificationDataObject.put("test_hatching_register_for_dfls_supplied", selectedValue18);
            verificationDataObject.put("temp._&_rh_maintenance_register", selectedValue19);
            verificationDataObject.put("bill_book_for_dfl_supply", selectedValue20);
            verificationDataObject.put("whether_following_self-certification_for_each_supply_lot", selectedValue21);
            verificationDataObject.put("whether_quarterly_reports_are_being_regularly_submitted_to_nsso", selectedValue22);
            verificationDataObject.put("image", image_path1);
            verificationDataObject.put("extra1", etAdd1 );
            verificationDataObject.put("extra2", etAdd2);
            verificationDataObject.put("extra3", etAdd3);
            verificationDataObject.put("extra4", image_path);

            verificationDataArray.put(verificationDataObject);
            requestBody.put("verification_data", verificationDataArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("responseofApi", String.valueOf(requestBody));
        // Create a new request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                ApiData.renewsaveVerification, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            if (response.getBoolean("status") == true) {

                                Toast.makeText(Preview_page.this, "" + response.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Preview_page.this, "" + response.getString("message"),
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


    public void AlertDialog() {


        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(R.drawable.logo)
//set title
                .setTitle("Submit")
//set message
                .setMessage("Do you want to Submit?")

//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                        int status = 1,checkForm=1;
                        getOfficersJob(verification_id, status);
                        Intent intent = new Intent(getApplicationContext(), PDf_Activity.class);
                        intent.putExtra("verification_id",verification_id);
                        intent.putExtra("combinedString",combinedString);
                        intent.putExtra("sp_status",sp_status);
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

    public void AlertDialogRenew() {


        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(R.drawable.logo)
//set title
                .setTitle("Submit")
//set message
                .setMessage("Do you want to Submit?")

//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                        int status = 1,checkForm=1;
                        getOfficersJobRenew(verification_id, status);
                        Intent intent = new Intent(getApplicationContext(), PDf_Activity.class);
                        intent.putExtra("verification_id",verification_id);
                        intent.putExtra("combinedString",combinedString);
                        intent.putExtra("sp_status",sp_status);
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
}