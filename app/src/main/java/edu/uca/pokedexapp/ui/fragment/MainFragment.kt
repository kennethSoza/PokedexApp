package edu.uca.pokedexapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import edu.uca.pokedexapp.ui.MainViewModel
import javax.inject.Inject
import edu.uca.pokedexapp.R
import edu.uca.pokedexapp.intent.Intent
import edu.uca.pokedexapp.model.ElementalTypes
import edu.uca.pokedexapp.utils.AdapterType
import edu.uca.pokedexapp.utils.DataStateTypes
import edu.uca.pokedexapp.utils.RecyclerViewClickListener

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
constructor() : Fragment(R.layout.fragment_first){
    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var adapterTypes: AdapterType

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
        recyclerViewTypes.layoutManager = layoutManager
        recyclerViewTypes.adapter = adapterTypes

        subscribeObservers()
        lifecycleScope.launch {
            viewModel.userIntent.send(Intent.GetTypeEvent)
        }

        /**Con el adapter, se manda a llamar el Listener que creamos
         * para el RecyclerView, ya luego se declara un objeto de tipo
         * DetailsPokemon, para luego mandar los datos del pokémon seleccionado
         * al dialog fragment, al mismo tiempo se manda a mostrar el fragment*/
        adapterTypes.setOnClickListener(object: RecyclerViewClickListener{
            override fun onClickType(position: Int, elementalTypes: ElementalTypes) {
                Log.d("Probando, nombre:",elementalTypes.typename)
                /*
                val dp = DetailsPokemon()
                dp.setPkmnDetail(pkmn)
                activity?.let { dp.show(it.supportFragmentManager, "DialogFragmentPkmnDetails") }
                */
            }

        })
    }

    private fun subscribeObservers(){
        lifecycleScope.launch {
            viewModel.dataState.collect {
                when(it){
                    is DataStateTypes.Success -> {
                        displayProgressBar(false)
                        adapterTypes.setTypes(it.elementalTypes)
                    }
                    is DataStateTypes.Error -> {
                        displayProgressBar(false)
                        displayError(it.exception.message)
                    }
                    is DataStateTypes.Loading -> {
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