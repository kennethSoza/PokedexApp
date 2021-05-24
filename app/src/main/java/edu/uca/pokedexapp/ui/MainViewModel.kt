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
import edu.uca.pokedexapp.repository.TypesRepository
import edu.uca.pokedexapp.utils.DataStateTypes
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject constructor(
    private val typesRepository: TypesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    val userIntent = Channel<Intent>(Channel.UNLIMITED)
    private val _dataState= MutableStateFlow<DataStateTypes>(DataStateTypes.Idle)

    val dataState: StateFlow<DataStateTypes>
        get() = _dataState
    init {
        setStateEvent()
    }
    fun setStateEvent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{intn ->
                when(intn){
                    is Intent.GetTypeEvent -> {
                        typesRepository.getTypes()
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