package viewmodels

import com.example.newapp.API.RetrofitInstance
import com.example.newapp.models.Car
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  CarViewModel : ViewModel() {
    var lifeDataList: MutableLiveData<List<Car>?>
    init {
        lifeDataList = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<List<Car>?> {
        return lifeDataList
    }

    fun makeApiCall(context: Context?){
        val apiInterface = RetrofitInstance.api(context)
        apiInterface.getCar().enqueue(object : Callback<List<Car>> {
            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                lifeDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<Car>>,
                response: Response<List<Car>>

            ) {
                Log.i(
                    "response",
                    response.body().toString()
                )
                lifeDataList.postValue(response.body())
            }
        })

    }
}