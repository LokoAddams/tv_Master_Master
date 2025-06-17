package com.example.tvmaster.control

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.DispTV
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.usecases.SaveDispTV
import com.example.usecases.existeDispTV
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlViewModel @Inject constructor(
    private val saveDispTV: SaveDispTV,
    private val existeDispTV: existeDispTV
) : ViewModel()
{
    fun guardarTV(dispTV: DispTV) {
        viewModelScope.launch {
            val existe = existeDispTV(dispTV.friendlyName)
            if (!existe) {
                saveDispTV(dispTV)
            }
        }
    }
}