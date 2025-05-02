package com.example.nssoseedanalyst;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

public class Camera_Activity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 71;
    private static final int REQUEST_PERMISSIONS = 100;
    Bitmap imageBitmap;
            String encodeImageString;

    private String filePath;
    private String selectedPicture = "";
    private ProgressDialog progressDialog;
    private ImageView imageView;
    private Button buttonCamera;
    private Button buttonGallery;
    private Button buttonUpload;
    private static String BEARER_TOKEN = "cCy1jMG21uf8LD1EeXQmKQgheItNXzhA2ubAnxQsZndhmzpub4J2hOWca6x5"; // Replace with your Bearer token

    private static final int REQUEST_CODE_PERMISSION = 2;
    private Uri selectedImageUri,imageUri;
    File imageFile;
    String imageFilename, imageUrl;
    private String currentPhotoPath;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    ImageView imgPhoto;
    private RequestQueue requestQueue;
    boolean isNotMoreThan2MB;
    String imagePath;
    private static final int REQUEST_CAMERA_CAPTURE = 1;
    private static final int REQUEST_GALLERY_PICK = 2;
    private static final int REQUEST_CAMERA_PERMISSION = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = findViewById(R.id.imageView);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonGallery = findViewById(R.id.buttonGallery);
        buttonUpload = findViewById(R.id.buttonUpload);

        requestQueue = Volley.newRequestQueue(this);


        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Camera_Activity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    ActivityCompat.requestPermissions(Camera_Activity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            }

        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openGallery();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                uploadImage();
            }
        });
    }

    private void openCamera() {
        // Create an intent to open the camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Check if there is a camera app available
        PackageManager packageManager = getPackageManager();
        if (cameraIntent.resolveActivity(packageManager) != null) {
            // Create a file to store the captured image
            File photoFile = createImageFile();
            if (photoFile != null) {
                imageUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, REQUEST_CAMERA_CAPTURE);
            }
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY_PICK);
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            imageFile = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );
            currentPhotoPath = imageFile.getAbsolutePath();
        } catch (IOException ex) {
            Log.e("Camera_Activity", "Error creating image file: " + ex.getMessage());
        }
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA_CAPTURE && resultCode == RESULT_OK) {
            // Display the captured image in the ImageView
            imageView.setImageURI(imageUri);
        } else if (requestCode == REQUEST_GALLERY_PICK && resultCode == RESULT_OK && data != null) {
            // Handle image picked from gallery
            selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }






    private void uploadImage() {

        if (selectedImageUri != null || imageUri != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.new_progress);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            ApiService apiService = ApiClient.create(BEARER_TOKEN);
if (imageUri==null){
      imageFile = new File(getRealPathFromUri(selectedImageUri));
} else {
      imageFile = new File(getRealPathFromUri(imageUri));

}

            RequestBody imageRequestBody = RequestBody.create(imageFile, MediaType.parse("image/*"));
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), imageRequestBody);

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
                            Toast.makeText(Camera_Activity.this, "Success", Toast.LENGTH_SHORT).show();

                            // Check if the response has "status" and it's true
                            if (json.has("status") && json.getBoolean("status")) {
                                JSONObject data = json.getJSONObject("data");

                                // Get the image filename and full URL
                                imageFilename = data.getString("image");
                                imageUrl = data.getString("full_url");
                                Toast.makeText(Camera_Activity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                                Log.e("response", "" + imageFilename);
                                Log.e("response", "" + imageUrl);


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
                    Toast.makeText(Camera_Activity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("response", "" + t);
                }
            });
        } else {
            Toast.makeText(Camera_Activity.this, "Upload a photo", Toast.LENGTH_SHORT).show();
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


    // Clear the ImageView
    private void clearImageView() {
        imageView.setImageDrawable(null);
    }

}












