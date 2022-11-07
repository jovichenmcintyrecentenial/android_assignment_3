package com.centennial.jovichenmcintyre_mapd711_assignment3.models

import com.centennial.jovichenmcintyre_mapd711_assignment3.enumerator.Brand
import java.util.ArrayList

//Name: Jovi Chen-Mcintyre
//ID: 301125059

class Company(
     val name: String,
     val brand: Brand,
     val pin_icon: String,
     val listDescription:String
) {

    var locations = ArrayList<PhoneStoreLocation>()

}