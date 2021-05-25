package edu.uca.pokedexapp.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import edu.uca.pokedexapp.intent.Intent
import edu.uca.pokedexapp.repository.PokemonRepository
import edu.uca.pokedexapp.utils.DataStatePokemon
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SecondMainViewModel
@ViewModelInject constructor(private val pokemonRepository: PokemonRepository,
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel(){
    val userIntent = Channel<Intent>(Channel.UNLIMITED)
    private val _dataState= MutableStateFlow<DataStatePokemon>(DataStatePokemon.Idle)

    val dataState: StateFlow<DataStatePokemon>
        get() = _dataState
    init {
        setStateEvent()
    }

    fun setStateEvent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{intn ->
                when(intn){
                    is Intent.GetPokemonEvent -> {
                        pokemonRepository.getPokemon()
                            .onEach {
                                _dataState.value = it
                            }
                            .launchIn(viewModelScope)
                    }
                    Intent.None -> {

                    }
                }

            }
        }
    }
}