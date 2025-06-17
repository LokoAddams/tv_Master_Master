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

class ControlViewModel(

) : ViewModel()
{
}