package com.example.newapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.newapp.API.RetrofitInstance
import com.example.newapp.models.Car
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class addcarr : AppCompatActivity() {

    lateinit var carr: Car
    lateinit var cartype: TextView
    lateinit var carengine: TextView
    lateinit var carprice: TextView
    lateinit var carbrand: TextView
    lateinit var CarPic: ImageView
    lateinit var rentbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcarr)
        rentbtn = findViewById(R.id.addcarclient)
        cartype = findViewById(R.id.carType)
        carengine = findViewById(R.id.carEngine)
        carprice = findViewById(R.id.carPrice)
        carbrand = findViewById(R.id.carBrand)
        CarPic = findViewById(R.id.CarPic)
        carr = (intent.getSerializableExtra("car object") as? Car)!!
        showDetails()
        rentbtn.setOnClickListener{
            // navigateDetails(carr)
        }
    }


    private fun showDetails() {
        val apiInterface = RetrofitInstance.api(this)
        apiInterface.getCarById(carr._id!!).enqueue(object : Callback<Car> {
            override fun onResponse(call: Call<Car>, response: Response<Car>) {
                Log.w("url", response.raw().request.url.toString())
                Log.w("body", response.body().toString())
                if (response.isSuccessful) {
                    var car: Car = response.body()!!
                    cartype.text = car.cartype.toString()
                    carengine.text = car.carengine.toString()
                    carprice.text = car.carprice.toString()
                    carbrand.text = car.carbrand.toString()
                    //CarPic.text = car.lights.toString()
                    val url = "RetrofitInstance.BASE_URL"

                    Picasso.get()
                        // .load("${RetrofitInstance.BASE_URL}/uploads2/${data.carPic}")
                        .load(car.carPic.toString())
                        .fit()
                        .centerCrop()
                        .into(CarPic)
                    // Log.d("TESTEST",carPic.toString())

                }
            }

            override fun onFailure(call: Call<Car>, t: Throwable) {
                Toast.makeText(applicationContext, "Connexion Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }
//    private fun navigateDetails(car : Car)
//    {
//        val i = Intent(this, AddCarrental::class.java)
//        i.putExtra("carr object", car)
//        startActivity(i)
//    }
}