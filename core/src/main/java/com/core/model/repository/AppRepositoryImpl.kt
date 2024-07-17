package com.core.model.repository

import com.core.domain.repository.AppRepository
import com.core.model.data.UploadVideoResponse
import com.core.model.repository.remote.RemoteDataSource
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Part

class AppRepositoryImpl constructor(private val remoteDataSource: RemoteDataSource): AppRepository {
    override fun uploadVideo(@Part file: MultipartBody.Part): Observable<UploadVideoResponse> {
        val currentTimestamp = System.currentTimeMillis()
        val remoteSource = remoteDataSource.uploadVideo(file,currentTimestamp.toString())
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    data class Result<out T>(
        val data: T? = null,
        val error: Throwable? = null
    )

    private fun <T> T.asResult(): Result<T> = Result(data = this, error = null)

}