package edu.uca.pokedexapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uca.pokedexapp.R
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
//import edu.uca.pokedexapp.ui.MainViewModel Crear otro MainViewModel
import javax.inject.Inject
import edu.uca.pokedexapp.intent.Intent
import edu.uca.pokedexapp.model.ElementalTypes
import edu.uca.pokedexapp.model.Pokemon
import edu.uca.pokedexapp.ui.SecondMainViewModel
import edu.uca.pokedexapp.utils.AdapterPokemon
import edu.uca.pokedexapp.utils.DataStatePokemon
import edu.uca.pokedexapp.utils.RecyclerViewClickListener
import kotlinx.android.synthetic.main.fragment_first.progress_bar
import kotlinx.android.synthetic.main.fragment_second.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FragmentSecPkmn
constructor(): Fragment(R.layout.fragment_second) {
    private val TAG: String = "AppDebug"

    private val viewModel: SecondMainViewModel by viewModels()

    @Inject
    lateinit var adapterPkmn: AdapterPokemon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        val layoutManager =
            LinearLayoutManager(
                requireActivity().applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerViewPokemon.layoutManager = layoutManager
        recyclerViewPokemon.adapter = adapterPkmn

        subscribeObservers()
        lifecycleScope.launch {
            viewModel.userIntent.send(Intent.GetPokemonEvent)
        }

        /**Con el adapter, se manda a llamar el Listener que creamos
         * para el RecyclerView, ya luego se declara un objeto de tipo
         * DetailsPokemon, para luego mandar los datos del pokémon seleccionado
         * al dialog fragment, al mismo tiempo se manda a mostrar el fragment*/
        adapterPkmn.setOnClickListener(object: RecyclerViewClickListener{
            override fun onClickType(position: Int, elementalTypes: ElementalTypes) {
                TODO("Not yet implemented")
            }
            override fun onClickPokemon(position: Int, pkmn: Pokemon) {
                Log.d("Probando, nombre:",pkmn.pkmnname)
                val dp = DetailsPokemon()
                dp.setPkmnDetail(pkmn)
                activity?.let { dp.show(it.supportFragmentManager, "DialogFragmentPkmnDetails") }
            }

        })
    }

    private fun subscribeObservers(){
        lifecycleScope.launch {
            viewModel.dataState.collect {
                when(it){
                    is DataStatePokemon.Success -> {
                        displayProgressBar(false)
                        adapterPkmn.setPkmns(it.pkmn)
                    }
                    is DataStatePokemon.Error -> {
                        displayProgressBar(false)
                        displayError(it.exception.message)
                    }
                    is DataStatePokemon.Loading -> {
                        displayProgressBar(true)
                    }
                }
            }
        }
    }

    private fun displayError(message: String?) {
        //  if (message != null) text.text = message else text.text = "Unknown error."
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}