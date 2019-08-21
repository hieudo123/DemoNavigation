package com.example.stackexchange.navigationarchitecture.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel(
    @Expose
    @SerializedName("username")
    var username : String ="",
    @Expose
    @SerializedName("coin")
    var coin : Int = 0,
    @Expose
    @SerializedName("gender")
    var gender : String = "",
    @Expose
    @SerializedName("image")
    var image : Int = 0
):Serializable {

}