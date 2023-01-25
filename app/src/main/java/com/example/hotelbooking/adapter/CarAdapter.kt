import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.models.Car
import com.squareup.picasso.Picasso
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Transformation
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newapp.API.RetrofitInstance
import com.example.newapp.utis.ClickHandler
import kotlinx.android.synthetic.main.car_item.view.*

class CarAdapter (val fragment : Fragment, private val clickHandler: ClickHandler): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private var carList: List<Car>? = null


    inner class CarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        val Cartype : TextView = itemView.findViewById(R.id.cartype)
        val Carbrand :TextView = itemView.findViewById(R.id.carbrand)
        val Carprice :TextView = itemView.findViewById(R.id.carprice)
        val Carengine :TextView = itemView.findViewById(R.id.carengine)
        val CarPic :ImageView = itemView.findViewById(R.id.carPic)


        fun bind(data: Car) {

            Cartype.text = data.cartype.toString()
            Carbrand.text = data.carbrand.toString()
            Carprice.text = data.carprice.toString()
            Carengine.text = data.carengine.toString()
            val url = "RetrofitInstance.BASE_URL"

            Picasso.get()
                // .load("${RetrofitInstance.BASE_URL}/uploads2/${data.carPic}")
                .load(data.carPic.toString())
                .fit()
                .centerCrop()
                .into(CarPic)
            Log.d("TESTEST",data.carPic.toString())
        }


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickHandler.ClickItem(position)
            }
        }



    }
    fun setCarList(carList: List<Car>?) {
        this.carList = carList
    }

    fun getCarList() :List<Car>? {
        return this.carList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view)

    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(carList == null)return 0
        else return carList?.size!!
    }

}
/*

class CarAdapter(private val carList: List<Car>): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class CarViewHolder(itemView: View,listener:OnItemClickListener):RecyclerView.ViewHolder(itemView){
        val car_type = itemView.findViewById<TextView>(R.id.cartype)
        val car_brand = itemView.findViewById<TextView>(R.id.carbrand)
        val car_engine = itemView.findViewById<TextView>(R.id.carengine)
        val car_price = itemView.findViewById<TextView>(R.id.carprice)
        val car_Pic = itemView.findViewById<ImageView>(R.id.carPic)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(adapterPosition)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.car_type.text =car.cartype
        holder.car_brand.text = car.carbrand
       /* Picasso.get().load(Car.carPic).into(holder.car_Pic)
    }
*/}
    override fun getItemCount(): Int {
        return carList.size
    }
}*/

