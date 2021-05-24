package edu.uca.pokedexapp.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uca.pokedexapp.model.ElementalTypes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_types.view.*
import edu.uca.pokedexapp.R

class AdapterType : RecyclerView.Adapter<AdapterType.ViewHolder>(){
    lateinit var items: ArrayList<ElementalTypes>
    private var clickListener : RecyclerViewClickListener?=null


    fun setOnClickListener(clickListener: RecyclerViewClickListener){
        this.clickListener = clickListener
    }


    fun setTypes(items: List<ElementalTypes>){
        this.items = items as ArrayList<ElementalTypes>
        notifyDataSetChanged()
    }


    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        // Holds the TextView that will add each picture to
        val typeImage: ImageView = view.type_logo
        val type_name: TextView = view.type_name

        init{
            itemView.setOnClickListener(this)
        }
        /**Método para cuando se hace click o tap sobre uno
         * de los pokémon*/

        override fun onClick(v: View?) {
            var model = items.get(adapterPosition)
            clickListener?.onClickType(adapterPosition, model)

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_types, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model= items[position]
        holder.type_name.text = model.typename
        Picasso.get()
            .load(model.url_Image)
            .into(holder.typeImage)
    }

    override fun getItemCount(): Int {
        return if(::items.isInitialized){
            items.size
        }else{
            0
        }
    }
}