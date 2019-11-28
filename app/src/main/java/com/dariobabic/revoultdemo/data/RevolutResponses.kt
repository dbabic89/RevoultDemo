package com.dariobabic.revoultdemo.data

data class RevolutBaseResponse(
    val base: String?,
    val date: String?,
    val rates: Map<String, Double>?
)