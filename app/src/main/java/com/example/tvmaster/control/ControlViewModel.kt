package com.example.tvmaster.control

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.control.usecases.Back
import com.control.usecases.Home
import com.control.usecases.PowerOff
import com.control.usecases.ChannelUp
import com.control.usecases.ChannelDown
import com.control.usecases.Microphone
import com.control.usecases.VolumenDown
import com.control.usecases.VolumenUp
import com.example.tvmaster.Manager
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlViewModel @Inject constructor(
    private val back: Back,
    private val home: Home,
    private val poweroff: PowerOff,
    private val channelup: ChannelUp,
    private val channeldown: ChannelDown,
    private val microphone: Microphone,
    private val volumenup: VolumenUp,
    private val volumendown: VolumenDown
) : ViewModel()
{
}

class FakeControlViewModel : ViewModel() {
    fun onApagarClicked() {
    }
}
