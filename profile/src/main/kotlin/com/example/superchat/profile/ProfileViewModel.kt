package com.example.superchat.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superchat.profile.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profile: Profile
): ViewModel(){

    private val _profileUIState: MutableStateFlow<ProfileUIState> = MutableStateFlow(ProfileUIState.Initialized)
    val profileUIState = _profileUIState

    fun buttonOnTap(){
        viewModelScope.launch {
            try {
                _profileUIState.update { ProfileUIState.Loading }
                _profileUIState.update { ProfileUIState.Success(profile.profile()) }
            }catch (e: Exception){
                Timber.e(e)
                _profileUIState.update { ProfileUIState.Error }
            }
        }
    }
}


sealed interface ProfileUIState{
    object Initialized: ProfileUIState
    object Loading: ProfileUIState
    data class Success(val result: String): ProfileUIState
    object Error: ProfileUIState
}