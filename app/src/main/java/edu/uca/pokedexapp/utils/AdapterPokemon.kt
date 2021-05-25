package edu.uca.pokedexapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.uca.pokedexapp.R
import edu.uca.pokedexapp.model.Pokemon
import edu.uca.pokedexapp.ui.fragment.DetailsPokemon
import edu.uca.pokedexapp.ui.fragment.FragmentSecPkmn
import kotlinx.android.synthetic.main.item_pkmns.view.*


class AdapterPokemon() : RecyclerView.Adapter<AdapterPokemon.ViewHolder>() {
    lateinit var items: ArrayList<Pokemon>
    private var clickListener : RecyclerViewClickListener?=null

    fun setOnClickListener(clickListener: RecyclerViewClickListener){
        this.clickListener = clickListener
    }

    fun setPkmns(items: List<Pokemon>){
        this.items = items as ArrayList<Pokemon>
        notifyDataSetChanged()
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        // Holds the TextView that will add each picture to
        val pkmnImage: ImageView = view.pkmn_image


        init{
            itemView.setOnClickListener(this)
        }

        /**Método para cuando se hace click o tap sobre uno
         * de los pokémon*/
        override fun onClick(v: View?) {
            var model = items.get(adapterPosition)
            clickListener?.onClickPokemon(adapterPosition, model)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pkmns, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model= items[position]
        Picasso.get()
            .load(model.url_image)
            .into(holder.pkmnImage)
    }

    override fun getItemCount(): Int {
        return if(::items.isInitialized){
            items.size
        }else{
            0
        }
    }


}