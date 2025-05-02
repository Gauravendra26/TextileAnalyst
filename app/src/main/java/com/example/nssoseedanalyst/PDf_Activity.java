package com.example.nssoseedanalyst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PDf_Activity extends AppCompatActivity {
    int verification_id, checkForm,sp_status,sr_status;
    String combinedString;
    RelativeLayout rlPDFbutton,rlClose;
    private static final int OPEN_URL_REQUEST_CODE = 1;

    TextView tvCloseDetails,tvDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Utils.blackIconStatusBar(PDf_Activity.this, R.color.white);
        init();

        Intent intent = getIntent();
//        Toast.makeText(this, ""+verification_id, Toast.LENGTH_SHORT).show();
        verification_id = intent.getIntExtra("verification_id", 0);
        combinedString = intent.getStringExtra("combinedString");
        sp_status = intent.getIntExtra("sp_status",0);
        sr_status = intent.getIntExtra("sr_status",0);
//        Toast.makeText(this, ""+verification_id, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "sp_status ="+sp_status+"sr_status ="+sr_status, Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        rlPDFbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Execute the API request when the button is clicked
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlPDFbutton.startAnimation(myAnim);

                if (sp_status==1 || sr_status==1){
                    String Url = "https://csbseedact.in/api/download?verification_id=" + verification_id;
                    Context context = v.getContext();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                    context.startActivity(intent);
//                    Toast.makeText(context, "New", Toast.LENGTH_SHORT).show();

                } else {
                    String Url = "https://csbseedact.in/api/renew-download?verification_id=" + verification_id;
                    Context context = v.getContext();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                    context.startActivity(intent);
//                    Toast.makeText(context, "Renew", Toast.LENGTH_SHORT).show();

                }

            }
        });


        tvDay.setText(combinedString);

        rlClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(), profile_page.class);
                startActivity(intent1);
                finish();
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                rlClose.startAnimation(myAnim);

            }
        });
    }

    void init() {
        rlPDFbutton = findViewById(R.id.rlPDFbutton);
        rlClose = findViewById(R.id.rlClose);
        tvCloseDetails = findViewById(R.id.tvCloseDetails);
        tvDay = findViewById(R.id.tvDay);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_URL_REQUEST_CODE) {
            // The URL activity has completed
            if (resultCode == RESULT_OK) {
                // URL activity completed successfully, start ProfilePage activity now
                Context context = this;
                Intent activity1Intent = new Intent(context, profile_page.class);
                context.startActivity(activity1Intent);
            }
            // You can optionally handle other result codes or errors here
        }
    }


}