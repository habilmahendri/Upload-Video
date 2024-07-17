package com.core.domain.repository

import com.core.model.data.UploadVideoResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Part

interface AppRepository {
    fun uploadVideo(@Part file: MultipartBody.Part):Observable<UploadVideoResponse>
}