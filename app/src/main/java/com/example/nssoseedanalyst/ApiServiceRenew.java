package com.example.nssoseedanalyst;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceRenew {
    @Multipart
    @POST("renew-upload") // Replace with your server's endpoint
    Call<ResponseBody> uploadImage(
            @Header("Authorization") String authorizationHeader,
            @Part MultipartBody.Part image
    );
}
