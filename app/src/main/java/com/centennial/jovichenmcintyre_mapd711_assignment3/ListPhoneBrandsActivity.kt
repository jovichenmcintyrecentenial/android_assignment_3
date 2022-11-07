package com.centennial.jovichenmcintyre_mapd711_assignment3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.centennial.jovichenmcintyre_mapd711_assignment3.enumerator.Brand
import com.centennial.jovichenmcintyre_mapd711_assignment3.models.Company
import com.centennial.jovichenmcintyre_mapd711_assignment3.models.PhoneStoreLocation
import java.util.ArrayList

class ListPhoneBrandsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_phone_brands)

        val listView = findViewById<ListView>(R.id.list)

        //create instance of a custom listAdpator called PhoneListAdaptor
        var listAdaptor = BrandListAdaptor(this, getBrandList())

        //create a listener for on click aciton on list view
        listView.setOnItemClickListener { parent, view, position, id ->
//            var newIntent = Intent(this,PhoneOptionsSelectActivity::class.java)
//            //update create PhoneCheckOut and serialize data and pass to intent
//            newIntent.putExtra("checkout", Gson().toJson(PhoneCheckOut(listOfPhones[position])))
//            //load new Intent
//            startActivity(newIntent)
        }

        //attact adaptor to listview
        listView.adapter = listAdaptor
    }


    //function to get a list of phone  based on company names
    private fun getBrandList():List<Company>{
        var list = ArrayList<Company>()
        list.add(Company("Apple", Brand.APPLE))
        var tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Jump+",
                43.7755398,
                -79.260746,
                "+1 (647) 417-1434",
                "300 Borough Dr, Scarborough, ON M1P 4P5",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "http://jumpplus.com/"
            )
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Jump+",
                43.726844,
                -79.5624598,
                "+1 (289) 459-1887",
                "1 Bass Pro Mills Dr, Concord, ON L4K 5W4",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "http://jumpplus.com/"
            ),
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Best Buy",
                43.773719,
                -79.3990286,
                "+1 (866) 237-8289",
                "480 Progress Ave, Scarborough, ON M1P 5J1",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "https://stores.bestbuy.ca/en-ca/on/scarborough/480-progress-ave"
            ),
        )
        list.add(Company("Google", Brand.GOOGLE ))
        tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Best Buy",
                43.773719,
                -79.3990286,
                "+1 (866) 237-8289",
                "480 Progress Ave, Scarborough, ON M1P 5J1",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "https://stores.bestbuy.ca/en-ca/on/scarborough/480-progress-ave"
            ),
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Best Buy",
                43.8692615,
                -79.291075,
                "+1 (866) 237-8289",
                "5000 Hwy 7 Unit 2070L, Markham, ON L3R 4M9",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "https://stores.bestbuy.ca/en-ca/on/scarborough/480-progress-ave"
            ),
        )
        list.add(Company("Samsung", Brand.SAMSUNG ))
        tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Samsung Experience Store",
                43.7752514,
                -79.3277146,
                "+1 (416) 775-3527",
                "3401 Dufferin St Unit 508, North York, ON M6A 2T9",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "http://www.samsung.com/ca/ses/"
            ),
        )
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Samsung Experience Store",
                43.7305344,
                -79.4627086,
                "+1 (416) 775-3527",
                "220 Yonge St, Toronto, ON M5B 2H1",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "http://www.samsung.com/ca/ses/"
            ),
        )
        list.add(Company("Oppo", Brand.OPPO ))
        tempCompany = list[list.count()-1]
        tempCompany.locations.add(
            PhoneStoreLocation(
                "Best Buy",
                43.773719,
                -79.3990286,
                "+1 (866) 237-8289",
                "480 Progress Ave, Scarborough, ON M1P 5J1",
                "10:00 am",
                "5:00 pm",
                "ic_launcher",
                "https://stores.bestbuy.ca/en-ca/on/scarborough/480-progress-ave"
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
        val phone = list[position]


        if(inflatedView == null){
            //inflate custom list item layout
            inflatedView = LayoutInflater.from(context).
            inflate(R.layout.brand_list_item, parent, false)
        }

//        //find views
//        val priceTextView = inflatedView?.findViewById<TextView>(R.id.phone_price)
//        val phoneImage = inflatedView?.findViewById<ImageView>(R.id.phone_image)
//        val phoneNameTextView = inflatedView?.findViewById<TextView>(R.id.phone_name)
//
//        //dynamically load phone images using phone uri
//        val resourceImage: Int = context.resources.getIdentifier(phone.uri, "drawable", context.packageName)
//        phoneImage?.setImageResource(resourceImage)
//
//        //update phone name in list
//        phoneNameTextView?.text = phone.name
//        //update price on list time
//        priceTextView?.text = phone.getFormatterPrice().toString()
        return inflatedView!!
    }

}