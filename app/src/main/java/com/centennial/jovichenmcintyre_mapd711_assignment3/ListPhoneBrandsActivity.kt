package com.centennial.jovichenmcintyre_mapd711_assignment3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.centennial.jovichenmcintyre_mapd711_assignment3.enumerator.Brand
import com.centennial.jovichenmcintyre_mapd711_assignment3.models.Company
import com.centennial.jovichenmcintyre_mapd711_assignment3.models.PhoneStoreLocation
import com.google.gson.Gson
import java.util.ArrayList

//Name: Jovi Chen-Mcintyre
//ID: 301125059

class ListPhoneBrandsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_phone_brands)
        //get list view
        val listView = findViewById<ListView>(R.id.list)
        //get list of companies
        val listOfCompanies = getBrandList()
        //create instance of a custom listAdpator called PhoneListAdaptor
        var listAdaptor = BrandListAdaptor(this, listOfCompanies)

        //create a listener for on click aciton on list view
        listView.setOnItemClickListener { _, _, position, _ ->
            var newIntent = Intent(this,MapsActivity::class.java)
            //update create PhoneCheckOut and serialize data and pass to intent
            newIntent.putExtra("company", Gson().toJson(listOfCompanies[position]))
            //load new Intent
            startActivity(newIntent)
        }

        //attact adaptor to listview
        listView.adapter = listAdaptor
    }


    //function to load data
    private fun getBrandList():List<Company>{
        var list = ArrayList<Company>()
        list.add(Company(getString(R.string.apple), Brand.APPLE,"apple_pin", getString(R.string.l_apple_phones)))
        var tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.jump),
                43.7755398,
                -79.260746,
                "+1 (647) 417-1434",
                "300 Borough Dr, Scarborough, ON M1P 4P5",
                "10:00 am",
                "5:00 pm",
                "jump",
                "http://jumpplus.com"
            )
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.jump),
                43.726844,
                -79.5624598,
                "+1 (289) 459-1887",
                "1 Bass Pro Mills Dr, Concord, ON L4K 5W4",
                "10:00 am",
                "5:00 pm",
                "jump",
                "http://jumpplus.com"
            ),
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.best_buy),
                43.773719,
                -79.3990286,
                "+1 (866) 237-8289",
                "480 Progress Ave, Scarborough, ON M1P 5J1",
                "10:00 am",
                "5:00 pm",
                "bestbuy",
                "https://stores.bestbuy.ca"
            ),
        )
        list.add(Company(getString(R.string.google), Brand.GOOGLE ,"google_pin",getString(R.string.l_google_phones)))
        tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.best_buy),
                43.773719,
                -79.3990286,
                "+1 (866) 237-8289",
                "480 Progress Ave, Scarborough, ON M1P 5J1",
                "10:00 am",
                "5:00 pm",
                "bestbuy",
                "https://stores.bestbuy.ca"
            ),
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.best_buy),
                43.8692615,
                -79.291075,
                "+1 (866) 237-8289",
                "5000 Hwy 7 Unit 2070L, Markham, ON L3R 4M9",
                "10:00 am",
                "5:00 pm",
                "bestbuy",
                "https://stores.bestbuy.ca"
            ),
        )
        list.add(Company("Samsung", Brand.SAMSUNG, "samsung_pin",getString(R.string.l_samsung_phones) ))
        tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.samsung_xp_store),
                43.7752514,
                -79.3277146,
                "+1 (416) 775-3527",
                "3401 Dufferin St Unit 508, North York, ON M6A 2T9",
                "10:00 am",
                "5:00 pm",
                "samsung",
                "http://www.samsung.com/ca/ses"
            ),
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.samsung_xp_store),
                43.7305344,
                -79.4627086,
                "+1 (416) 775-3527",
                "220 Yonge St, Toronto, ON M5B 2H1",
                "10:00 am",
                "5:00 pm",
                "samsung",
                "http://www.samsung.com/ca/ses"
            ),
        )
        list.add(Company("Oppo", Brand.OPPO,"oppo_pin", getString(R.string.l_oppo_phones) ))
        tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                getString(R.string.best_buy),
                43.773719,
                -79.3990286,
                "+1 (866) 237-8289",
                "480 Progress Ave, Scarborough, ON M1P 5J1",
                "10:00 am",
                "5:00 pm",
                "bestbuy",
                "https://stores.bestbuy.ca"
            ),
        )


        return list
    }
}


//custom list adaptor to achieve design
class BrandListAdaptor(context: Activity, list:List<Company>):  BaseAdapter(){

    var context = context
    var list = list

    override fun getCount(): Int {
        return list.count()
    }

    override fun getItem(position: Int): Company {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var inflatedView: View? = convertView
        //load data at position
        val company = list[position]


        if(inflatedView == null){
            //inflate custom list item layout
            inflatedView = LayoutInflater.from(context).
            inflate(R.layout.brand_list_item, parent, false)
        }

        //find views
        val imageView = inflatedView?.findViewById<ImageView>(R.id.pin_image)
        val descriptionTextView = inflatedView?.findViewById<TextView>(R.id.description)

        descriptionTextView!!.text = company.listDescription

        //dynamically load phone images using phone uri
        val resourceImage: Int = context.resources.getIdentifier(company.pin_icon, "drawable", context.packageName)
        imageView?.setImageResource(resourceImage)

        return inflatedView!!
    }

}