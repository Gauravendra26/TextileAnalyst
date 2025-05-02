package com.example.nssoseedanalyst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Account_Details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static String BEARER_TOKEN = ""; // Replace with your Bearer token

    private static final int REQUEST_CODE_PERMISSION = 2;
    private ImageView imageView;
    private Uri selectedImageUri;

    String imageFilename, imageUrl;
    private String currentPhotoPath;
    ImageView imgPhoto;

    TextView tvback, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15,
            tv16, tv17, tv18, tv19, tv20, tv21, tv22, tv23, tv24, tv25, tv26, tv27;

    EditText et1, et2, et3, et4, et5, et6, etAdd1, etAdd2, etAdd3;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10, spinner11,
            spinner12, spinner13, spinner14, spinner15, spinner16, spinner17, spinner18, spinner19, spinner20,
            spinner21, spinner22, spinner23, spinner24, spinner25;
    LinearLayout llspinnerRc2;
    RelativeLayout rlspinnerRc2, rlUpload;
    Spinner spinnerRc1, spinnerRc2;

    private RelativeLayout spinner2Layout;
    RelativeLayout rlnext, rlDraft;
    String selectedValue, selectedValue1, selectedValue2, selectedValue3, selectedValue4, selectedValue5,
            selectedValue6, selectedValue7, selectedValue8, selectedValue9, selectedValue10, selectedValue11,
            selectedValue12, selectedValue13, selectedValue14, selectedValue15, selectedValue16, selectedValue17,
            selectedValue18, selectedValue19, selectedValue20, selectedValue21, selectedValue22,
            selectedValue23,
            sp_sirname, sp_applicant, sp_registration_no, sp_mobile, sp_state, sp_address, combinedString,
            sp_pin, sp_village, check, sp_taluk, fullName, fullName1, registrationnumber, fullAddress, fullAddress1;
    String nameOfTheRsp, registration_no, registered_address,
            contact_no, displayed_ther_registration_certificate_prominently,
            number_of_regd_seed_cocoon_producers_with_rsp,
            whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop,
            whether_seed_cocoons_are_purchased_as_per_norms,
            whether_seed_cocoons_are_properly_sorted_and_sex_separated,
            whether_pupal_testing_is_done_for_pebrine, sex_separation_efficiency,
            male_female_ratio, whether_each_parental_pupae_are_kept_in_separate_room,
            whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms,
            whether_seeds_are_properly_washed_dried_and_packed_as_recommended,
            whether_male_moths_are_preserved_in_cold_room, whether_cold_rooms_have_uninterrupted_power_supply, whether_proper_temp_humidity_are_maintained_for_oviposition, whether_incubation_chamber_available,
            whether_seeds_are_properly_labeled_for_race_lot_no_dol, no_of_batches_detected,
            lot_details_of_source_seed_cocoon,
            skilled_person_for_testing, disinfection_register, seed_cocoon_procurement_register, cocoon_processing_register,
            pebrine_testing_register, dfl_supply_register, test_hatching_register_for_dfls_supplied,
            temp_rh_maintenance_register, bill_book_for_dfl_supply,
            whether_following_self_certification_for_each_supply_lot,
            whether_quarterly_reports_are_being_regularly_submitted_to_nsso, image, extra1, extra2, extra3;
    int verification_id, checkForm, sp_status;
    TextView tvday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        Utils.blackIconStatusBar(Account_Details.this, R.color.white);
        init();

        Intent intent = getIntent();

        check = intent.getStringExtra("check");
        combinedString = intent.getStringExtra("combinedString");
        registrationnumber = intent.getStringExtra("registrationnumber");
        checkForm = intent.getIntExtra("checkForm", 0);
        sp_status = intent.getIntExtra("sp_status", 0);
//        Toast.makeText(this, ""+sp_status, Toast.LENGTH_SHORT).show();
        sp_sirname = intent.getStringExtra("sp_sirname");
        sp_applicant = intent.getStringExtra("sp_applicant");
        sp_mobile = intent.getStringExtra("sp_mobile");
        sp_state = intent.getStringExtra("sp_state");
        sp_address = intent.getStringExtra("sp_address");
        sp_pin = intent.getStringExtra("sp_pin");
        sp_village = intent.getStringExtra("sp_village");
        sp_taluk = intent.getStringExtra("sp_taluk");
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
//        Toast.makeText(this, "" + check, Toast.LENGTH_SHORT).show();
        if (checkForm == 1) {
            fullName = intent.getStringExtra("fullName");
            fullAddress = intent.getStringExtra("fullAddress");
            sp_registration_no = intent.getStringExtra("sp_registration_no");
            verification_id = intent.getIntExtra("verification_id", verification_id);

        } else {
            fullName = sp_sirname + " " + sp_applicant;
            fullAddress = sp_address + " " + sp_taluk + " " + sp_state + " " + sp_pin;
            sp_registration_no = intent.getStringExtra("sp_registration_no");
            verification_id = intent.getIntExtra("verification_id", verification_id);

        }
        tvday.setText(combinedString);
        Log.e("checkNAme", fullName + " " + fullAddress + " " + sp_registration_no);
        Log.e("checkNAmeEd", fullName1 + " " + fullAddress1 + " " + registrationnumber);

        rlnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsEmpty()) {
                    Toast.makeText(Account_Details.this, "Complete all questions", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage();
                }

                // Animation effect
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlnext.startAnimation(myAnim);
            }
        });


        rlDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if any of the specified strings are null or empty
//                if (isEmptyOrNull(selectedValue) || isEmptyOrNull(selectedValue1) || isEmptyOrNull(selectedValue2) ||
//                        isEmptyOrNull(selectedValue3) || isEmptyOrNull(selectedValue4) || isEmptyOrNull(selectedValue5) ||
//                        isEmptyOrNull(selectedValue6) || isEmptyOrNull(selectedValue7) || isEmptyOrNull(selectedValue8) ||
//                        isEmptyOrNull(selectedValue9) || isEmptyOrNull(selectedValue10) || isEmptyOrNull(selectedValue11) ||
//                        isEmptyOrNull(selectedValue12) || isEmptyOrNull(selectedValue13) || isEmptyOrNull(selectedValue14) ||
//                        isEmptyOrNull(selectedValue15) || isEmptyOrNull(selectedValue16) || isEmptyOrNull(selectedValue17) ||
//                        isEmptyOrNull(selectedValue18) || isEmptyOrNull(selectedValue19) || isEmptyOrNull(selectedValue20) ||
//                        isEmptyOrNull(selectedValue21) || isEmptyOrNull(selectedValue22) || isEmptyOrNull(selectedValue23) ||
//                         isEmptyOrNull(sp_sirname) || isEmptyOrNull(sp_applicant) ||
//                        isEmptyOrNull(sp_registration_no) || isEmptyOrNull(sp_mobile) || isEmptyOrNull(sp_state) ||
//                        isEmptyOrNull(sp_address) || isEmptyOrNull(sp_pin) || isEmptyOrNull(sp_village) ||
//                        isEmptyOrNull(check) || isEmptyOrNull(sp_taluk) ||
//                        et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() ||
//                        et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty() ) {
//                    Toast.makeText(Account_Details.this, "Complete all questions", Toast.LENGTH_SHORT).show();
//                } else {

//                    if (currentPhotoPath == null) {
//                        Toast.makeText(Account_Details.this, "Upload a photo", Toast.LENGTH_SHORT).show();
//                    } else {
                // All conditions are met, go to activity1

                int status = 2;
//                getOfficersJob(verification_id, status);


//                    }
//                }
            }
        });

        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Details_page.class);
//                startActivity(intent);
                finish();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                tvback.startAnimation(myAnim);

            }
        });

        SharedPreferences sharedPreferences =
                getSharedPreferences("MyData", MODE_PRIVATE);

        BEARER_TOKEN = sharedPreferences.getString("Login_Token", ""); // Replace with your Bearer token

//        Toast.makeText(this, "" + BEARER_TOKEN, Toast.LENGTH_SHORT).show();

        rlUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();

                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlUpload.startAnimation(myAnim);

            }
        });

        String imagePath = String.valueOf(selectedImageUri);
        boolean isNotMoreThan2MB = isImageNotMoreThan2MB(imagePath);
        if (isNotMoreThan2MB) {
            // Image is not more than 2MB
            Log.d("ImageCheck", "Image is not more than 2MB");
        } else {
            // Image is larger than 2MB
            Log.d("ImageCheck", "Image is larger than 2MB");
        }

        SpinnerAdapters();

    }

    public boolean isImageNotMoreThan2MB(String imagePath) {
        try {
            File imageFile = new File(imagePath);

            // Check if the file exists
            if (!imageFile.exists()) {
                return false;
            }

            // Calculate the size of the image in bytes
            long imageSizeInBytes = imageFile.length();

            // Check if the size is less than or equal to 2MB (2 * 1024 * 1024 bytes)
            if (imageSizeInBytes <= 2 * 1024 * 1024) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void SpinnerAdapters() {
        ArrayAdapter<CharSequence> adapterc1 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapterc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRc1.setAdapter(adapterc1);

        spinnerRc1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue23 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Short, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Short, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue1 = parentView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue2 = parentView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.Short, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter3);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue3 = parentView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });


        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter4);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue4 = parentView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.Morethan5, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter5);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue5 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter6);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue6 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.Available, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter7);
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue7 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter8);
        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue8 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter9);
        spinner10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue9 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this,
                R.array.Available, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter10);
        spinner11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue10 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter11);
        spinner12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue11 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this,
                R.array.Available, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter12);
        spinner13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue12 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner14.setAdapter(adapter13);
        spinner14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue13 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner15.setAdapter(adapter14);
        spinner15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue14 = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner16.setAdapter(adapter15);
        spinner16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue15 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner17.setAdapter(adapter16);
        spinner17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue16 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner18.setAdapter(adapter17);
        spinner18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue17 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner19.setAdapter(adapter18);
        spinner19.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue18 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter19 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner20.setAdapter(adapter19);
        spinner20.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue19 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter20 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner21.setAdapter(adapter20);
        spinner21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue20 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(this,
                R.array.Partial, android.R.layout.simple_spinner_item);
        adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner22.setAdapter(adapter21);
        spinner22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue21 = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter22 = ArrayAdapter.createFromResource(this,
                R.array.Partial, android.R.layout.simple_spinner_item);
        adapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner23.setAdapter(adapter22);
        spinner23.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue22 = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });
        setDataToSpinner(spinnerRc1, displayed_ther_registration_certificate_prominently);
        setDataToSpinner(spinner1, whether_seed_cocoons_are_purchased_from_regd_seed_cocoon_producers_for_every_crop);
        setDataToSpinner(spinner2, whether_seed_cocoons_are_purchased_as_per_norms);
        setDataToSpinner(spinner3, whether_seed_cocoons_are_properly_sorted_and_sex_separated);
        setDataToSpinner(spinner4, whether_pupal_testing_is_done_for_pebrine);
        setDataToSpinner(spinner5, whether_each_parental_pupae_are_kept_in_separate_room);
        setDataToSpinner(spinner6, whether_proper_temp_rh_maintained_in_cocoon_pupae_oviposition_rooms);
        setDataToSpinner(spinner7, whether_seeds_are_properly_washed_dried_and_packed_as_recommended);
        setDataToSpinner(spinner8, whether_male_moths_are_preserved_in_cold_room);
        setDataToSpinner(spinner9, whether_cold_rooms_have_uninterrupted_power_supply);
        setDataToSpinner(spinner10, whether_proper_temp_humidity_are_maintained_for_oviposition);
        setDataToSpinner(spinner11, whether_incubation_chamber_available);
        setDataToSpinner(spinner12, whether_seeds_are_properly_labeled_for_race_lot_no_dol);
        setDataToSpinner(spinner13, skilled_person_for_testing);
        setDataToSpinner(spinner14, disinfection_register);
        setDataToSpinner(spinner15, seed_cocoon_procurement_register);
        setDataToSpinner(spinner16, cocoon_processing_register);
        setDataToSpinner(spinner17, pebrine_testing_register);
        setDataToSpinner(spinner18, dfl_supply_register);
        setDataToSpinner(spinner19, test_hatching_register_for_dfls_supplied);
        setDataToSpinner(spinner20, temp_rh_maintenance_register);
        setDataToSpinner(spinner21, bill_book_for_dfl_supply);
        setDataToSpinner(spinner22, whether_following_self_certification_for_each_supply_lot);
        setDataToSpinner(spinner23, whether_quarterly_reports_are_being_regularly_submitted_to_nsso);
        if (number_of_regd_seed_cocoon_producers_with_rsp != null) {
            et1.setText(number_of_regd_seed_cocoon_producers_with_rsp);
        }
        if (sex_separation_efficiency != null) {
            et2.setText(sex_separation_efficiency);
        }
        if (male_female_ratio != null) {
            et3.setText(male_female_ratio);
        }
        if (no_of_batches_detected != null) {
            et4.setText(no_of_batches_detected);
        }
        if (lot_details_of_source_seed_cocoon != null) {
            et5.setText(lot_details_of_source_seed_cocoon);
        }
        if (extra1 != null) {
            etAdd1.setText(extra1);
        }
        if (extra2 != null) {
            etAdd2.setText(extra2);
        }
        if (extra3 != null) {
            etAdd3.setText(extra3);
        }

    }


    private void setDataToSpinner(Spinner spinner, String data) {
        if (data != null) {
            ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinner.getAdapter();
            if (adapter != null) {
                int position = adapter.getPosition(data);
                if (position >= 0) {
                    spinner.setSelection(position);
                } else {
                    // Data not found in the adapter, handle the case accordingly
                    // You can log an error or display a message
                }
            } else {
                // Adapter is null, handle this case as well
                // You might want to initialize the adapter if it's null
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void init() {
        tvday = findViewById(R.id.tvday);

        imgPhoto = findViewById(R.id.imgPhoto);
        tvback = findViewById(R.id.tvback);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv10 = findViewById(R.id.tv10);
        tv11 = findViewById(R.id.tv11);
        tv12 = findViewById(R.id.tv12);
        tv13 = findViewById(R.id.tv13);
        tv14 = findViewById(R.id.tv14);
        tv15 = findViewById(R.id.tv15);
        tv16 = findViewById(R.id.tv16);
        tv17 = findViewById(R.id.tv17);
        tv18 = findViewById(R.id.tv18);
        tv19 = findViewById(R.id.tv19);
        tv20 = findViewById(R.id.tv20);
        tv21 = findViewById(R.id.tv21);
        tv22 = findViewById(R.id.tv22);
        tv23 = findViewById(R.id.tv23);
        tv24 = findViewById(R.id.tv24);
        tv25 = findViewById(R.id.tv25);
        tv26 = findViewById(R.id.tv26);
        tv27 = findViewById(R.id.tv27);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        etAdd1 = findViewById(R.id.etAdd1);
        etAdd2 = findViewById(R.id.etAdd2);
        etAdd3 = findViewById(R.id.etAdd3);
        rlnext = findViewById(R.id.rlnext);
        rlDraft = findViewById(R.id.rlDraft);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        spinner8 = findViewById(R.id.spinner8);
        spinner9 = findViewById(R.id.spinner9);
        spinner10 = findViewById(R.id.spinner10);
        spinner11 = findViewById(R.id.spinner11);
        spinner12 = findViewById(R.id.spinner12);
        spinner13 = findViewById(R.id.spinner13);
        spinner14 = findViewById(R.id.spinner14);
        spinner15 = findViewById(R.id.spinner15);
        spinner16 = findViewById(R.id.spinner16);
        spinner17 = findViewById(R.id.spinner17);
        spinner18 = findViewById(R.id.spinner18);
        spinner19 = findViewById(R.id.spinner19);
        spinner20 = findViewById(R.id.spinner20);
        spinner21 = findViewById(R.id.spinner21);
        spinner22 = findViewById(R.id.spinner22);
        spinner23 = findViewById(R.id.spinner23);
        llspinnerRc2 = findViewById(R.id.llspinnerRc2);
        rlspinnerRc2 = findViewById(R.id.rlspinnerRc2);
        rlUpload = findViewById(R.id.rlUpload);
        spinnerRc1 = findViewById(R.id.spinnerRc1);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open the gallery.
                openGallery();
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user).
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imgPhoto.setImageURI(selectedImageUri);
        }
    }

    private void uploadImage() {
        if (sp_status == 1) {
            if (selectedImageUri != null) {
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.new_progress);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                ApiService apiService = ApiClient.create(BEARER_TOKEN);

                try {
                    // Compress the image before uploading
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Create a RequestBody with the compressed image bytes
                    RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
                    MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", "image.jpg", imageRequestBody);

                    Call<ResponseBody> call = apiService.uploadImage(BEARER_TOKEN, imagePart);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                try {
                                    // Parse the response JSON
                                    String responseBody = response.body().string();
                                    JSONObject json = new JSONObject(responseBody);

                                    // Check if the response has "status" and it's true
                                    if (json.has("status") && json.getBoolean("status")) {
                                        JSONObject data = json.getJSONObject("data");

                                        // Get the image filename and full URL
                                        imageFilename = data.getString("image");
                                        imageUrl = data.getString("full_url");

                                        Log.e("response", "" + imageFilename);
                                        Log.e("response", "" + imageUrl);


                                        Intent intent = new Intent(getApplicationContext(), Preview_page.class);
                                        intent.putExtra("image_path", imageUrl);
                                        intent.putExtra("image_path1", imageFilename);
//                                Toast.makeText(AccontDetailPage.this, "" + imageUrl, Toast.LENGTH_SHORT).show();
                                        intent.putExtra("verification_id", verification_id);
                                        intent.putExtra("combinedString", combinedString);
                                        intent.putExtra("selectedValue", selectedValue);
                                        intent.putExtra("selectedValue1", selectedValue1);
                                        intent.putExtra("selectedValue2", selectedValue2);
                                        intent.putExtra("selectedValue3", selectedValue3);
                                        intent.putExtra("selectedValue4", selectedValue4);
                                        intent.putExtra("selectedValue5", selectedValue5);
                                        intent.putExtra("selectedValue6", selectedValue6);
                                        intent.putExtra("selectedValue7", selectedValue7);
                                        intent.putExtra("selectedValue8", selectedValue8);
                                        intent.putExtra("selectedValue9", selectedValue9);
                                        intent.putExtra("selectedValue10", selectedValue10);
                                        intent.putExtra("selectedValue11", selectedValue11);
                                        intent.putExtra("selectedValue12", selectedValue12);
                                        intent.putExtra("selectedValue13", selectedValue13);
                                        intent.putExtra("selectedValue14", selectedValue14);
                                        intent.putExtra("selectedValue15", selectedValue15);
                                        intent.putExtra("selectedValue16", selectedValue16);
                                        intent.putExtra("selectedValue17", selectedValue17);
                                        intent.putExtra("selectedValue18", selectedValue18);
                                        intent.putExtra("selectedValue19", selectedValue19);
                                        intent.putExtra("selectedValue20", selectedValue20);
                                        intent.putExtra("selectedValue21", selectedValue21);
                                        intent.putExtra("selectedValue22", selectedValue22);
                                        intent.putExtra("selectedValue23", selectedValue23);
                                        intent.putExtra("et1", et1.getText().toString().trim());
                                        intent.putExtra("et2", et2.getText().toString().trim());
                                        intent.putExtra("et3", et3.getText().toString().trim() + " : " + et6.getText().toString().trim());
                                        intent.putExtra("et4", et4.getText().toString().trim());
                                        intent.putExtra("et5", et5.getText().toString().trim());
                                        intent.putExtra("etAdd1", etAdd1.getText().toString().trim());
                                        intent.putExtra("etAdd2", etAdd2.getText().toString().trim());
                                        intent.putExtra("etAdd3", etAdd3.getText().toString().trim());
                                        intent.putExtra("check", check);
                                        intent.putExtra("checkForm", checkForm);

                                        intent.putExtra("sp_registration_no", sp_registration_no);
                                        intent.putExtra("sp_status", sp_status);

                                        intent.putExtra("fullName", fullName);
                                        intent.putExtra("fullAddress", fullAddress);
                                        intent.putExtra("registrationnumber", registrationnumber);
                                        intent.putExtra("fullName1", fullName1);
                                        intent.putExtra("fullAddress1", fullAddress1);
//                                        int status = 1;
//                getOfficersJob(verification_id, status
//                                , selectedValue,selectedValue1,selectedValue2,
//                                selectedValue3,selectedValue4,selectedValue5,selectedValue6,selectedValue7,
//                                selectedValue8,selectedValue9,selectedValue10,selectedValue11,selectedValue12,selectedValue13,
//                                selectedValue14
//                );
                                        startActivity(intent);
                                        Toast.makeText(Account_Details.this, "Success", Toast.LENGTH_SHORT).show();


                                    } else {
                                        // Handle API error, if needed
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    // Handle JSON parsing error
                                }
                            } else {
                                // Handle HTTP error
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Account_Details.this, " " + t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("response", "" + t);

                        }
                    });
                } catch (Exception e) {

                    Log.e("Error ", e.getMessage());
                }


            } else {
                Toast.makeText(Account_Details.this, "Upload a photo", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (selectedImageUri != null) {
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.new_progress);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                ApiServiceRenew apiService = ApiClientRenew.create(BEARER_TOKEN);

                try {
                    // Compress the image before uploading
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();

                    // Create a RequestBody with the compressed image bytes
                    RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
                    MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", "image.jpg", imageRequestBody);

                    Call<ResponseBody> call = apiService.uploadImage(BEARER_TOKEN, imagePart);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                try {
                                    // Parse the response JSON
                                    String responseBody = response.body().string();
                                    JSONObject json = new JSONObject(responseBody);

                                    // Check if the response has "status" and it's true
                                    if (json.has("status") && json.getBoolean("status")) {
                                        JSONObject data = json.getJSONObject("data");

                                        // Get the image filename and full URL
                                        imageFilename = data.getString("image");
                                        imageUrl = data.getString("full_url");

                                        Log.e("response", "" + imageFilename);
                                        Log.e("response", "" + imageUrl);


                                        Intent intent = new Intent(getApplicationContext(), Preview_page.class);
                                        intent.putExtra("image_path", imageUrl);
                                        intent.putExtra("image_path1", imageFilename);
//                                Toast.makeText(AccontDetailPage.this, "" + imageUrl, Toast.LENGTH_SHORT).show();
                                        intent.putExtra("verification_id", verification_id);
                                        intent.putExtra("combinedString", combinedString);
                                        intent.putExtra("selectedValue", selectedValue);
                                        intent.putExtra("selectedValue1", selectedValue1);
                                        intent.putExtra("selectedValue2", selectedValue2);
                                        intent.putExtra("selectedValue3", selectedValue3);
                                        intent.putExtra("selectedValue4", selectedValue4);
                                        intent.putExtra("selectedValue5", selectedValue5);
                                        intent.putExtra("selectedValue6", selectedValue6);
                                        intent.putExtra("selectedValue7", selectedValue7);
                                        intent.putExtra("selectedValue8", selectedValue8);
                                        intent.putExtra("selectedValue9", selectedValue9);
                                        intent.putExtra("selectedValue10", selectedValue10);
                                        intent.putExtra("selectedValue11", selectedValue11);
                                        intent.putExtra("selectedValue12", selectedValue12);
                                        intent.putExtra("selectedValue13", selectedValue13);
                                        intent.putExtra("selectedValue14", selectedValue14);
                                        intent.putExtra("selectedValue15", selectedValue15);
                                        intent.putExtra("selectedValue16", selectedValue16);
                                        intent.putExtra("selectedValue17", selectedValue17);
                                        intent.putExtra("selectedValue18", selectedValue18);
                                        intent.putExtra("selectedValue19", selectedValue19);
                                        intent.putExtra("selectedValue20", selectedValue20);
                                        intent.putExtra("selectedValue21", selectedValue21);
                                        intent.putExtra("selectedValue22", selectedValue22);
                                        intent.putExtra("selectedValue23", selectedValue23);
                                        intent.putExtra("et1", et1.getText().toString().trim());
                                        intent.putExtra("et2", et2.getText().toString().trim());
                                        intent.putExtra("et3", et3.getText().toString().trim() + " : " + et6.getText().toString().trim());
                                        intent.putExtra("et4", et4.getText().toString().trim());
                                        intent.putExtra("et5", et5.getText().toString().trim());
                                        intent.putExtra("etAdd1", etAdd1.getText().toString().trim());
                                        intent.putExtra("etAdd2", etAdd2.getText().toString().trim());
                                        intent.putExtra("etAdd3", etAdd3.getText().toString().trim());
                                        intent.putExtra("check", check);
                                        intent.putExtra("checkForm", checkForm);

                                        intent.putExtra("sp_registration_no", sp_registration_no);
                                        intent.putExtra("sp_status", sp_status);

                                        intent.putExtra("fullName", fullName);
                                        intent.putExtra("fullAddress", fullAddress);
                                        intent.putExtra("registrationnumber", registrationnumber);
                                        intent.putExtra("fullName1", fullName1);
                                        intent.putExtra("fullAddress1", fullAddress1);
//                                        int status = 1;
//                getOfficersJob(verification_id, status
//                                , selectedValue,selectedValue1,selectedValue2,
//                                selectedValue3,selectedValue4,selectedValue5,selectedValue6,selectedValue7,
//                                selectedValue8,selectedValue9,selectedValue10,selectedValue11,selectedValue12,selectedValue13,
//                                selectedValue14
//                );
                                        startActivity(intent);
                                        Toast.makeText(Account_Details.this, "Success", Toast.LENGTH_SHORT).show();


                                    } else {
                                        // Handle API error, if needed
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    // Handle JSON parsing error
                                }
                            } else {
                                // Handle HTTP error
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Account_Details.this, " " + t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("response", "" + t);

                        }
                    });
                } catch (Exception e) {

                    Log.e("Error ", e.getMessage());
                }


            } else {
                Toast.makeText(Account_Details.this, "Upload a photo", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        }

        return uri.getPath();
    }

    // Separate method to check if any field is empty
    private boolean areFieldsEmpty() {
        return et1.getText().toString().isEmpty() ||
                et2.getText().toString().isEmpty() ||
                et3.getText().toString().isEmpty() ||
                et4.getText().toString().isEmpty() ||
                et5.getText().toString().isEmpty() ||
                et6.getText().toString().isEmpty() ||
                selectedValue.equals("Select") ||
                selectedValue1.equals("Select") || selectedValue2.equals("Select") ||
                selectedValue3.equals("Select") || selectedValue4.equals("Select") ||
                selectedValue5.equals("Select") || selectedValue6.equals("Select") ||
                selectedValue7.equals("Select") || selectedValue8.equals("Select") ||
                selectedValue9.equals("Select") || selectedValue10.equals("Select") ||
                selectedValue11.equals("Select") || selectedValue12.equals("Select") ||
                selectedValue13.equals("Select") || selectedValue14.equals("Select") ||
                selectedValue15.equals("Select") || selectedValue16.equals("Select") ||
                selectedValue17.equals("Select") || selectedValue18.equals("Select") ||
                selectedValue19.equals("Select") || selectedValue20.equals("Select") ||
                selectedValue21.equals("Select") || selectedValue22.equals("Select") ||
                selectedValue23.equals("Select");
    }
}
