package com.example.tvmaster.Menu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.DispTV
import com.example.data.DispGuardadosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.usecases.GetDispTV

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getDispTV: GetDispTV
) : ViewModel() {

    sealed class TelevisoresState {
        data class Mostrar(val televisores: List<DispTV>) : TelevisoresState()
        object NoHayTelevisores : TelevisoresState()
    }

    private val _state = MutableStateFlow<TelevisoresState>(TelevisoresState.NoHayTelevisores)
    val state: StateFlow<TelevisoresState> = _state

    fun cargarTelevisores() {
        viewModelScope.launch {
            val televisores = getDispTV() // <-- uso del usecase con invoke
            if (televisores.isEmpty()) {
                _state.value = TelevisoresState.NoHayTelevisores
            } else {
                _state.value = TelevisoresState.Mostrar(televisores)
            }
        }
    }
}
