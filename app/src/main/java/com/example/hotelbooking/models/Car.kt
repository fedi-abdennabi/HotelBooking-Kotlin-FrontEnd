package com.example.newapp.models

import okhttp3.MultipartBody
import java.io.Serializable

data class Car (var _id: String ? = null,
           var cartype: String ? = null,
           var carbrand: String ? = null,
           var carprice: String ? = null,
           var carPic: String? = null,
           var carengine: String ? = null
): Serializable
