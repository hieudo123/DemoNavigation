package com.example.stackexchange.navigationarchitecture.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MenuModel(
    @Expose
    @SerializedName("title")
    var title: String ="",
    @Expose
    @SerializedName("image")
    var image: Int =0) {
}