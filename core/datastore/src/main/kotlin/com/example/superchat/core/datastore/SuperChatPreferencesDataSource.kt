/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.superchat.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SuperChatPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<com.example.superchat.core.datastore.UserPreferences>,
) {

    val userData = userPreferences.data.map {
        UserData(
            show_completed = it.showCompleted,
            themeBrand = when (it.themeBrandConfig) {
                null,
                com.example.superchat.core.datastore.ThemeBrandProto.THEME_BRAND_UNSPECIFIED,
                com.example.superchat.core.datastore.ThemeBrandProto.UNRECOGNIZED,
                com.example.superchat.core.datastore.ThemeBrandProto.THEME_BRAND_DEFAULT,
                    -> ThemeBrand.DEFAULT
                com.example.superchat.core.datastore.ThemeBrandProto.THEME_BRAND_ANDROID -> ThemeBrand.ANDROID
            },
            darkThemeConfig = when (it.darkThemeConfig) {
                null,
                com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                com.example.superchat.core.datastore.DarkThemeConfigProto.UNRECOGNIZED,
                com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
                    ->
                    DarkTheme.FOLLOW_SYSTEM
                com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT ->
                    DarkTheme.LIGHT
                com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkTheme.DARK
            },
        )
    }

    suspend fun updateShowCompleted(showCompleted: Boolean) {
        try {
            userPreferences.updateData {
                it.copy { this.showCompleted = showCompleted }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }


    suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        userPreferences.updateData {
            it.copy {
                this.themeBrandConfig = when (themeBrand) {
                    ThemeBrand.DEFAULT -> com.example.superchat.core.datastore.ThemeBrandProto.THEME_BRAND_DEFAULT
                    ThemeBrand.ANDROID -> com.example.superchat.core.datastore.ThemeBrandProto.THEME_BRAND_ANDROID
                }
            }
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkTheme) {
        userPreferences.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkTheme.FOLLOW_SYSTEM ->
                        com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM
                    DarkTheme.LIGHT -> com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkTheme.DARK -> com.example.superchat.core.datastore.DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }



}
