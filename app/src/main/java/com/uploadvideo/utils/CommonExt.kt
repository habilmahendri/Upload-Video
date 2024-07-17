package com.uploadvideo.utils

import android.app.Activity
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invis(){
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Activity.getMultiPart(uri: Uri?): MultipartBody.Part? {
    val cursor = uri?.let { contentResolver.query(it, null, null, null, null) }
    cursor?.use {
        if (it.moveToFirst()) {
            val file = FileUtils.from(this, uri)
            if (file != null) {
                val requestBody = file.asRequestBody("multipart".toMediaTypeOrNull())
                return MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestBody
                )
            }
        }
    }
    return null
}