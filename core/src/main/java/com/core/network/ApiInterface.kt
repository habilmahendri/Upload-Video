package com.core.network

import com.core.model.data.UploadVideoResponse
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {

    @Multipart
    @POST("v1_1/dk3lhojel/video/upload")
    fun uploadVideo(
        @Part file: MultipartBody.Part,
        @Part("upload_preset") uploadPreset: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "ml_default"),
        @Part("public_id") publicId:RequestBody ,
        @Part("api_key") apiKey:RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "764292367668433")
    ): Observable<UploadVideoResponse>
}