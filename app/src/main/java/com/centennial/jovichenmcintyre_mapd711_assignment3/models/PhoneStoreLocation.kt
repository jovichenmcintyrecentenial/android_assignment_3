package com.centennial.jovichenmcintyre_mapd711_assignment3.models

data class PhoneStoreLocation(
    val latitude: Double,
    val longitude: Double,
    val phoneNumber:String,
    val address:String,
    val openHours:String,
    val closeHours:String,
    val image:String,
)