package com.core.domain.interactor

import com.core.domain.repository.AppRepository
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.UploadVideoResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Part

class HomeInteractor(private val appRepository: AppRepository): HomeUseCase {
    override fun uploadVideo(@Part file: MultipartBody.Part): Observable<UploadVideoResponse> = appRepository.uploadVideo(file)
}