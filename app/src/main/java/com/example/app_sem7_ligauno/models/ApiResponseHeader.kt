package com.example.app_sem7_ligauno.models

import com.google.gson.annotations.SerializedName

class ApiResponseHeader (
    @SerializedName("api")
    val api: ApiResponseDetails
)