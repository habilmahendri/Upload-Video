package com.uploadvideo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uploadvideo.base.BaseViewModel
import com.uploadvideo.utils.NetworkState
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.UploadVideoResponse
import okhttp3.MultipartBody
import retrofit2.http.Part

class HomeViewModel  constructor(private val useCase: HomeUseCase) : BaseViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _uploadVideo = MutableLiveData<UploadVideoResponse>()
    val uploadVideo: LiveData<UploadVideoResponse>
        get() = _uploadVideo


    fun uploadVideo(@Part file: MultipartBody.Part) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.uploadVideo(file).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _uploadVideo.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }
}