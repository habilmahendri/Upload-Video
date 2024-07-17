package com.core.domain.usecase

import com.core.model.data.UploadVideoResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Part

interface HomeUseCase {
    fun uploadVideo(@Part file: MultipartBody.Part):Observable<UploadVideoResponse>
}