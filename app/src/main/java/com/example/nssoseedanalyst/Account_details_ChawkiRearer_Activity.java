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

public class Account_details_ChawkiRearer_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static String BEARER_TOKEN = ""; // Replace with your Bearer token

    private static final int REQUEST_CODE_PERMISSION = 2;
    private ImageView imageView;
    private Uri selectedImageUri1;

    String imageFilename, imageUrl;
    ImageView imgPhoto;

    TextView tvback, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15,
            tv16, tv17, tv18, tv19, tv20, tv21, tv22, tv23, tv24, tv25, tv26, tv27, tv28, tv29, tv30;

    EditText et1, et2, et3, et4, etAdd1, etAdd2, etAdd3;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10, spinner11,
            spinner12, spinner13, spinner14, spinner15, spinner16, spinner17, spinner18, spinner19, spinner20,
            spinner21, spinner22, spinner23, spinner24, spinner25, spinner26;
    LinearLayout llspinnerRc2;
    RelativeLayout rlspinnerRc2, rlUpload;
    Spinner spinnerRc1, spinnerRc2;
    String fullName, fullName1, registrationnumber, fullAddress, fullAddress1,
            sr_sirname, sr_applicant, sr_registration_no, sr_mobile, sr_state, sr_address,
            sr_pin, sr_village, sr_district, sr_taluk;

    private RelativeLayout spinner2Layout;
    RelativeLayout rlnext, rlDraft;
    String selectedValue, selectedValue1, selectedValue2, selectedValue3, selectedValue4, selectedValue5, selectedValue6,
            selectedValue7, selectedValue8, selectedValue9, selectedValue10, selectedValue11, selectedValue12,
            selectedValue13, selectedValue14, selectedValue15, selectedValue16, selectedValue17, selectedValue18,
            selectedValue19, selectedValue20, selectedValue21, selectedValue22, selectedValue23, selectedValue24,
            selectedValue25, selectedValue26, selectedValue27, sp_sirname, sp_applicant, sp_registration_no, sp_mobile, sp_state,
            sp_address, sp_pin, check, combinedString, sp_taluk, sp_village;

    int verification_id, checkForm, sr_status;
    TextView tvday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_chawkirearer_producer);
        Utils.blackIconStatusBar(Account_details_ChawkiRearer_Activity.this, R.color.white);

        SharedPreferences sharedPreferences =
                getSharedPreferences("MyData", MODE_PRIVATE);

        BEARER_TOKEN = sharedPreferences.getString("Login_Token", "");


        init();

        Intent intent = getIntent();
        combinedString = intent.getStringExtra("combinedString");

        registrationnumber = intent.getStringExtra("registrationnumber");
        checkForm = intent.getIntExtra("checkForm", 0);
        sr_status = intent.getIntExtra("sr_status", 0);
//        Toast.makeText(this, ""+sr_status, Toast.LENGTH_SHORT).show();
        sr_sirname = intent.getStringExtra("sr_sirname");
        sr_applicant = intent.getStringExtra("sr_applicant");
        sr_mobile = intent.getStringExtra("sr_mobile");
        sr_state = intent.getStringExtra("sr_state");
        sr_address = intent.getStringExtra("sr_address");
        sr_pin = intent.getStringExtra("sr_pin");
        sr_village = intent.getStringExtra("sr_village");
        sr_district = intent.getStringExtra("sr_district");
        sr_taluk = intent.getStringExtra("sr_taluk");
        Log.e("addresscheck", fullName1 + " " + fullAddress1 + " " + registrationnumber);

        if (checkForm == 1) {
            fullName = intent.getStringExtra("fullName");
            fullAddress = intent.getStringExtra("fullAddress");
            sr_registration_no = intent.getStringExtra("sr_registration_no");
            verification_id = intent.getIntExtra("verification_id", verification_id);
            check = intent.getStringExtra("check");

        } else {
            fullName = sr_sirname + " " + sr_applicant;
            fullAddress = sr_address + " " + sr_taluk + " " + sr_state + " " + sr_pin;
            sr_registration_no = intent.getStringExtra("sr_registration_no");
            verification_id = intent.getIntExtra("verification_id", verification_id);
            check = intent.getStringExtra("check");

        }
        tvday.setText(combinedString);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.select, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRc1.setAdapter(adapter1);

        spinnerRc1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue26 = parentView.getItemAtPosition(position).toString();
                if (check == "12b") {
                    spinner2Layout.setVisibility(View.GONE);
                } else {
                    spinner2Layout.setVisibility(View.GONE);
                }
//                Toast.makeText(Account_details_ChawkiRearer_Activity.this, "" + selectedValue26, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        rlnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("SpinnerSelection", "Selected value: " + selectedValue);
                if (areFieldsEmpty()) {
                    Toast.makeText(Account_details_ChawkiRearer_Activity.this,
                            "Complete all questions", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage();
                }
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlnext.startAnimation(myAnim);

            }
        });
        rlDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (isEmptyOrNull(selectedValue) || isEmptyOrNull(selectedValue1) || isEmptyOrNull(selectedValue2) ||
//                        isEmptyOrNull(selectedValue3) || isEmptyOrNull(selectedValue4) || isEmptyOrNull(selectedValue5) ||
//                        isEmptyOrNull(selectedValue6) || isEmptyOrNull(selectedValue7) || isEmptyOrNull(selectedValue8) ||
//                        isEmptyOrNull(selectedValue9) || isEmptyOrNull(selectedValue10) || isEmptyOrNull(selectedValue11) ||
//                        isEmptyOrNull(selectedValue12) || isEmptyOrNull(selectedValue13) || isEmptyOrNull(selectedValue14) ||
//                        isEmptyOrNull(selectedValue15) || isEmptyOrNull(selectedValue16) || isEmptyOrNull(selectedValue17) ||
//                        isEmptyOrNull(selectedValue18) || isEmptyOrNull(selectedValue19) || isEmptyOrNull(selectedValue20) ||
//                        isEmptyOrNull(selectedValue21) || isEmptyOrNull(selectedValue22) || isEmptyOrNull(selectedValue23) ||
//                        isEmptyOrNull(selectedValue24) || isEmptyOrNull(selectedValue25) || isEmptyOrNull(selectedValue26) ||
//                          isEmptyOrNull(sp_sirname) || isEmptyOrNull(sp_applicant) ||
//                        isEmptyOrNull(sp_registration_no) || isEmptyOrNull(sp_mobile) || isEmptyOrNull(sp_state) ||
//                        isEmptyOrNull(sp_address) || isEmptyOrNull(sp_pin) || isEmptyOrNull(sp_village) ||
//                        isEmptyOrNull(check) || isEmptyOrNull(sp_taluk) ||
//                        et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() ||
//                        et3.getText().toString().isEmpty() || et4.getText().toString().isEmpty()  ) {
//                    Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Complete all questions", Toast.LENGTH_SHORT).show();
//                } else {

//                    if (currentPhotoPath == null) {
//                        Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Upload a photo", Toast.LENGTH_SHORT).show();
//                    } else {


//                        int status = 2;
//                        getOfficersJob(verification_id, status);

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

        rlUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if camera permission is granted

                openGallery();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlUpload.startAnimation(myAnim);

            }
        });


        SpinnerAdapters();
    }


    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void SpinnerAdapters() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Irrgeular, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
                Log.d("SpinnerSelection", "Selected value: " + selectedValue);
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Irrgeular, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
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
                R.array.V1, android.R.layout.simple_spinner_item);
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
                R.array.FullyPestWeedFree, android.R.layout.simple_spinner_item);
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
                R.array.select, android.R.layout.simple_spinner_item);
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
                R.array.Less, android.R.layout.simple_spinner_item);
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
                R.array.Insufficient, android.R.layout.simple_spinner_item);
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
                R.array.Excellent, android.R.layout.simple_spinner_item);
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
                R.array.Moderate, android.R.layout.simple_spinner_item);
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
                R.array.Partial, android.R.layout.simple_spinner_item);
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
                R.array.Moderate, android.R.layout.simple_spinner_item);
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
                R.array.Poor, android.R.layout.simple_spinner_item);
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
                R.array.Crowdy, android.R.layout.simple_spinner_item);
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
                R.array.AsRecommended, android.R.layout.simple_spinner_item);
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
                R.array.Available, android.R.layout.simple_spinner_item);
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
                R.array.Maintained, android.R.layout.simple_spinner_item);
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
                R.array.Maintained, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence> adapter23 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner24.setAdapter(adapter23);
        spinner24.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue23 = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter24 = ArrayAdapter.createFromResource(this,
                R.array.Maintained, android.R.layout.simple_spinner_item);
        adapter24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner25.setAdapter(adapter24);
        spinner25.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue24 = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

        ArrayAdapter<CharSequence> adapter25 = ArrayAdapter.createFromResource(this,
                R.array.Partial, android.R.layout.simple_spinner_item);
        adapter25.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner26.setAdapter(adapter25);
        spinner26.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue25 = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected (if needed)
            }
        });

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
        spinner24 = findViewById(R.id.spinner24);
        spinner25 = findViewById(R.id.spinner25);
        spinner26 = findViewById(R.id.spinner26);
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
        tv28 = findViewById(R.id.tv28);
        tv29 = findViewById(R.id.tv29);
        tv30 = findViewById(R.id.tv30);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        etAdd1 = findViewById(R.id.etAdd1);
        etAdd2 = findViewById(R.id.etAdd2);
        etAdd3 = findViewById(R.id.etAdd3);
        llspinnerRc2 = findViewById(R.id.llspinnerRc2);
        rlspinnerRc2 = findViewById(R.id.rlspinnerRc2);
        rlUpload = findViewById(R.id.rlUpload);
        spinnerRc1 = findViewById(R.id.spinnerRc1);
        spinnerRc2 = findViewById(R.id.spinnerRc2);
        spinner2Layout = findViewById(R.id.spinner2_layout);
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
            selectedImageUri1 = data.getData();
            imgPhoto.setImageURI(selectedImageUri1);
        }
    }

    private void uploadImage() {
        if (sr_status == 1) {
            if (selectedImageUri1 != null) {
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.new_progress);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                ApiService apiService = ApiClient.create(BEARER_TOKEN);

                try {
                    // Compress the image before uploading
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri1);
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


                                        Intent intent = new Intent(getApplicationContext(), Previewpage_ChawkiRearer_Activity.class);
                                        intent.putExtra("image_path", imageUrl);
                                        intent.putExtra("image_path1", imageFilename);
                                        intent.putExtra("verification_id", verification_id);
                                        intent.putExtra("check", check);
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
                                        intent.putExtra("selectedValue24", selectedValue24);
                                        intent.putExtra("selectedValue25", selectedValue25);
                                        intent.putExtra("selectedValue26", selectedValue26);

                                        intent.putExtra("tv1", tv1.getText());
                                        intent.putExtra("tv2", tv2.getText());
                                        intent.putExtra("tv3", tv3.getText());
                                        intent.putExtra("tv4", tv4.getText());
                                        intent.putExtra("tv5", tv5.getText());
                                        intent.putExtra("tv6", tv6.getText());
                                        intent.putExtra("tv7", tv7.getText());
                                        intent.putExtra("tv8", tv8.getText());
                                        intent.putExtra("tv9", tv9.getText());
                                        intent.putExtra("tv10", tv10.getText());
                                        intent.putExtra("tv11", tv11.getText());
                                        intent.putExtra("tv12", tv12.getText());
                                        intent.putExtra("tv13", tv13.getText());
                                        intent.putExtra("tv14", tv14.getText());
                                        intent.putExtra("tv15", tv15.getText());
                                        intent.putExtra("tv16", tv16.getText());
                                        intent.putExtra("tv17", tv17.getText());
                                        intent.putExtra("tv18", tv18.getText());
                                        intent.putExtra("tv19", tv19.getText());
                                        intent.putExtra("tv20", tv20.getText());
                                        intent.putExtra("tv21", tv21.getText());
                                        intent.putExtra("tv22", tv22.getText());
                                        intent.putExtra("tv23", tv23.getText());
                                        intent.putExtra("tv24", tv24.getText());
                                        intent.putExtra("tv25", tv25.getText());
                                        intent.putExtra("tv26", tv26.getText());
                                        intent.putExtra("tv27", tv27.getText());
                                        intent.putExtra("tv28", tv28.getText());
                                        intent.putExtra("tv29", tv29.getText());
                                        intent.putExtra("tv30", tv30.getText());
                                        intent.putExtra("et1", et1.getText().toString());
                                        intent.putExtra("et2", et2.getText().toString());
                                        intent.putExtra("et3", et3.getText().toString());
                                        intent.putExtra("et4", et4.getText().toString());
                                        intent.putExtra("etAdd1", etAdd1.getText().toString());
                                        intent.putExtra("etAdd2", etAdd2.getText().toString());
                                        intent.putExtra("etAdd3", etAdd3.getText().toString());
                                        intent.putExtra("check", check);
                                        intent.putExtra("sr_sirname", sr_sirname);
                                        intent.putExtra("sr_applicant", sr_applicant);
                                        intent.putExtra("sr_registration_no", sr_registration_no);
                                        intent.putExtra("sr_mobile", sr_mobile);
                                        intent.putExtra("sr_state", sr_state);
                                        intent.putExtra("sr_address", sr_address);
                                        intent.putExtra("sr_pin", sr_pin);
                                        intent.putExtra("sr_village", sr_village);
                                        intent.putExtra("sr_taluk", sr_taluk);
                                        intent.putExtra("sr_status", sr_status);
                                        intent.putExtra("fullName", fullName);
                                        intent.putExtra("fullAddress", fullAddress);
                                        int status = 1;
//                                getOfficersJob(verification_id, status);
                                        startActivity(intent);
                                        Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Success", Toast.LENGTH_SHORT).show();


                                    } else {
                                        Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Account_details_ChawkiRearer_Activity.this, " " + t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("response", "" + t);

                        }
                    });
                } catch (Exception e) {

                    Log.e("Error ", e.getMessage());
                }


            } else {
                Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Upload a photo", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (selectedImageUri1 != null) {
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.new_progress);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                ApiServiceRenew apiService = ApiClientRenew.create(BEARER_TOKEN);

                try {
                    // Compress the image before uploading
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri1);
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


                                        Intent intent = new Intent(getApplicationContext(), Previewpage_ChawkiRearer_Activity.class);
                                        intent.putExtra("image_path", imageUrl);
                                        intent.putExtra("image_path1", imageFilename);
                                        intent.putExtra("verification_id", verification_id);
                                        intent.putExtra("check", check);
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
                                        intent.putExtra("selectedValue24", selectedValue24);
                                        intent.putExtra("selectedValue25", selectedValue25);
                                        intent.putExtra("selectedValue26", selectedValue26);

                                        intent.putExtra("tv1", tv1.getText());
                                        intent.putExtra("tv2", tv2.getText());
                                        intent.putExtra("tv3", tv3.getText());
                                        intent.putExtra("tv4", tv4.getText());
                                        intent.putExtra("tv5", tv5.getText());
                                        intent.putExtra("tv6", tv6.getText());
                                        intent.putExtra("tv7", tv7.getText());
                                        intent.putExtra("tv8", tv8.getText());
                                        intent.putExtra("tv9", tv9.getText());
                                        intent.putExtra("tv10", tv10.getText());
                                        intent.putExtra("tv11", tv11.getText());
                                        intent.putExtra("tv12", tv12.getText());
                                        intent.putExtra("tv13", tv13.getText());
                                        intent.putExtra("tv14", tv14.getText());
                                        intent.putExtra("tv15", tv15.getText());
                                        intent.putExtra("tv16", tv16.getText());
                                        intent.putExtra("tv17", tv17.getText());
                                        intent.putExtra("tv18", tv18.getText());
                                        intent.putExtra("tv19", tv19.getText());
                                        intent.putExtra("tv20", tv20.getText());
                                        intent.putExtra("tv21", tv21.getText());
                                        intent.putExtra("tv22", tv22.getText());
                                        intent.putExtra("tv23", tv23.getText());
                                        intent.putExtra("tv24", tv24.getText());
                                        intent.putExtra("tv25", tv25.getText());
                                        intent.putExtra("tv26", tv26.getText());
                                        intent.putExtra("tv27", tv27.getText());
                                        intent.putExtra("tv28", tv28.getText());
                                        intent.putExtra("tv29", tv29.getText());
                                        intent.putExtra("tv30", tv30.getText());
                                        intent.putExtra("et1", et1.getText().toString());
                                        intent.putExtra("et2", et2.getText().toString());
                                        intent.putExtra("et3", et3.getText().toString());
                                        intent.putExtra("et4", et4.getText().toString());
                                        intent.putExtra("etAdd1", etAdd1.getText().toString());
                                        intent.putExtra("etAdd2", etAdd2.getText().toString());
                                        intent.putExtra("etAdd3", etAdd3.getText().toString());
                                        intent.putExtra("check", check);
                                        intent.putExtra("sr_sirname", sr_sirname);
                                        intent.putExtra("sr_applicant", sr_applicant);
                                        intent.putExtra("sr_registration_no", sr_registration_no);
                                        intent.putExtra("sr_mobile", sr_mobile);
                                        intent.putExtra("sr_state", sr_state);
                                        intent.putExtra("sr_address", sr_address);
                                        intent.putExtra("sr_pin", sr_pin);
                                        intent.putExtra("sr_village", sr_village);
                                        intent.putExtra("sr_taluk", sr_taluk);
                                        intent.putExtra("sr_status", sr_status);
                                        intent.putExtra("fullName", fullName);
                                        intent.putExtra("fullAddress", fullAddress);
                                        int status = 1;
//                                getOfficersJob(verification_id, status);
                                        startActivity(intent);
                                        Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Success", Toast.LENGTH_SHORT).show();


                                    } else {
                                        Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Account_details_ChawkiRearer_Activity.this, " " + t.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("response", "" + t);

                        }
                    });
                } catch (Exception e) {

                    Log.e("Error ", e.getMessage());
                }


            } else {
                Toast.makeText(Account_details_ChawkiRearer_Activity.this, "Upload a photo", Toast.LENGTH_SHORT).show();
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

    private boolean areFieldsEmpty() {
        return et1.getText().toString().isEmpty() ||
                et2.getText().toString().isEmpty() ||
                et3.getText().toString().isEmpty() ||
                et4.getText().toString().isEmpty() ||
                selectedValue.equals("Select")||
                selectedValue1.equals("Select")|| selectedValue2.equals("Select")||
                selectedValue3.equals("Select")|| selectedValue4.equals("Select")||
                selectedValue5.equals("Select")|| selectedValue6.equals("Select") ||
                selectedValue7.equals("Select")|| selectedValue8.equals("Select") ||
                selectedValue9.equals("Select")|| selectedValue10.equals("Select") ||
                selectedValue11.equals("Select")|| selectedValue12.equals("Select")||
                selectedValue13.equals("Select")|| selectedValue14.equals("Select")||
                selectedValue15.equals("Select")|| selectedValue16.equals("Select") ||
                selectedValue17.equals("Select")|| selectedValue18.equals("Select") ||
                selectedValue19.equals("Select")|| selectedValue20.equals("Select") ||
                selectedValue21.equals("Select")|| selectedValue22.equals("Select")||
                selectedValue23.equals("Select")|| selectedValue24.equals("Select")||
                selectedValue25.equals("Select")|| selectedValue26.equals("Select")  ;
    }

}