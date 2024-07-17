package com.core.model.repository.remote

import com.core.network.ApiInterface
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

class RemoteDataSource constructor(private val apiService: ApiInterface)  {
    fun uploadVideo(@Part file: MultipartBody.Part,publicId:String) = apiService.uploadVideo(
        file = file,
        publicId = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), publicId),
    )
}