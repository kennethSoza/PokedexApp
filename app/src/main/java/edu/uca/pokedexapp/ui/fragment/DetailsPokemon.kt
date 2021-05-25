package edu.uca.pokedexapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uca.pokedexapp.R
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import edu.uca.pokedexapp.model.Pokemon
import kotlinx.android.synthetic.main.fragment_detail_pokemon.view.*
import kotlinx.android.synthetic.main.item_pkmns.view.*


class DetailsPokemon : DialogFragment() {
    //Variable global en la que se almacena el pokémon seleccionado
    var pkmnSelected: Pokemon? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView: View = inflater.inflate(R.layout.fragment_detail_pokemon, container, false)
        return rootView
    }

    /**Una vez inflada la vista, se manda a colocar los datos en text view y la imagen
     * en el image view, haciendo uso de la variable global*/
    override fun onViewCreated(view: View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val pkdxnumberFragment: TextView = view.pkdxnumberFragment
        val pkmnnameFragment: TextView = view.pkmnnameFragment
        val descriptonFragment: TextView = view.descriptionFragment
        val pkmnImageFragment: ImageView = view.pkmn_imageFragment

        pkdxnumberFragment.text = "#"+pkmnSelected?.pkdxnumber
        pkmnnameFragment.text = pkmnSelected?.pkmnname
        descriptonFragment.text = pkmnSelected?.description
        Picasso.get()
            .load(pkmnSelected?.url_image)
            .into(pkmnImageFragment)
        pkmnSelected?.pkmnname?.let { Log.d("Probando ViewCreated", it) }
    }


    fun setPkmnDetail(pkmn: Pokemon){
        /**
         * Aquí se toman los datos del pokémon seleccionado por el usuario
         * para ser mostrado en el dialog fragment, para ello se almacena
         * el objeto seleccionado en la variable global pkmnSelected*/
        pkmnSelected = pkmn
        pkmnSelected?.pkmnname?.let { Log.d("Probando setPkmnDetail", it) }
    }


}