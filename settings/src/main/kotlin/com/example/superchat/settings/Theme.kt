package com.example.superchat.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superchat.core.datastore.DarkTheme
import com.example.superchat.core.datastore.ThemeBrand
import com.example.superchat.core.datastore.UserPreferencesDataSource
import com.example.superchat.settings.SettingsUiState.Loading
import com.example.superchat.settings.SettingsUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
class Theme @Inject constructor(
    private val userPreferences: UserPreferencesDataSource
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        userPreferences.userData
            .map { userData ->
                Success(
                    settings = UserEditableSettings(
                        brand = userData.themeBrand,
                        useDynamicColor = userData.useDynamicColor,
                        darkThemeConfig = userData.darkTheme,
                    ),
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5.seconds.inWholeMilliseconds),
                initialValue = Loading,
            )

    fun updateThemeBrand(themeBrand: ThemeBrand) {
        viewModelScope.launch {
            userPreferences.setThemeBrand(themeBrand)
        }
    }

    fun updateDarkThemeConfig(darkThemeConfig: DarkTheme) {
        viewModelScope.launch {
            userPreferences.setDarkThemeConfig(darkThemeConfig)
        }
    }

    fun updateDynamicColorPreference(useDynamicColor: Boolean) {
        viewModelScope.launch {
            userPreferences.setDynamicColorPreference(useDynamicColor)
        }
    }
}


/**
 * Represents the settings which the user can edit within the app.
 */
data class UserEditableSettings(
    val brand: ThemeBrand,
    val useDynamicColor: Boolean,
    val darkThemeConfig: DarkTheme,
)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val settings: UserEditableSettings) : SettingsUiState
}
