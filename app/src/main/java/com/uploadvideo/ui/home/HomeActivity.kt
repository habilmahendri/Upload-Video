package com.uploadvideo.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.uploadvideo.R
import com.uploadvideo.utils.NetworkState.Companion.ERROR
import com.uploadvideo.utils.NetworkState.Companion.LOADED
import com.uploadvideo.utils.NetworkState.Companion.LOADING
import com.uploadvideo.utils.getMultiPart
import com.uploadvideo.utils.toast
import kotlinx.android.synthetic.main.activity_home.btn_take_video
import kotlinx.android.synthetic.main.activity_home.btn_upload
import kotlinx.android.synthetic.main.activity_home.tv_file_location
import kotlinx.android.synthetic.main.activity_home.tv_url_video
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
    private var videoUri: Uri? = null
    private val viewModel: HomeViewModel by viewModel()
    private val CAMERA_REQUEST_CODE_VIDEO:Int = 285

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        observerLiveData()
        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        videoUri = data?.data

        if (requestCode == CAMERA_REQUEST_CODE_VIDEO) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    toast("Video saved to: $videoUri")
                    tv_file_location.text = videoUri.toString()

                }
                Activity.RESULT_CANCELED -> {
                    toast("Video recording cancelled.")
                }
                else -> {
                    toast("Failed to record video")
                }
            }
        }
    }


    private fun observerLiveData() {

        viewModel.uploadVideo.observe(this, Observer {
            toast(it.url)
            tv_url_video.text = it.url
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                LOADED -> {
                    stateButtonUpload(true)
                    toast(it.message)
                }

                LOADING -> {
                    stateButtonUpload(false)
                    toast(it.message)
                }

                ERROR -> {
                    stateButtonUpload(true)
                    toast(it.message)
                }
            }
        })
    }

    private fun initView() {
        btn_upload.setOnClickListener {
            if (videoUri != null) {
                stateButtonUpload(false)
                viewModel.uploadVideo(getMultiPart(videoUri)!!)
            }
            else {
                stateButtonUpload(true)
                toast("Video tidak ditemukan")
            }
        }

        btn_take_video.setOnClickListener {
            tv_file_location.text = ""
            tv_url_video.text = ""
            val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            if (takeVideoIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takeVideoIntent, CAMERA_REQUEST_CODE_VIDEO)
            }
        }
    }

    private fun stateButtonUpload(state:Boolean) {
        btn_upload.isEnabled = true
        btn_upload.text = if (state) "UPLOAD VIDEO" else "Loading..."
    }


}