package com.dev_app.ecommercesales.models

import com.google.gson.annotations.SerializedName

    data class ShoesProduct(
        @SerializedName("name")
        val title: String,

        @SerializedName("img")
        val photoUrl: String,

        @SerializedName("shortDesc")
        val shortDesc: String,

        @SerializedName("fullDesc")
        val fullDesc: String,

        val price: Double,

        val size: Double,

        val isOnSale: Boolean
    )