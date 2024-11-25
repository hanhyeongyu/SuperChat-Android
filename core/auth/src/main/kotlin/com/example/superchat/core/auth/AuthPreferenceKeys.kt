package com.example.superchat.core.auth

import com.example.superchat.core.auth.model.OAuthToken
import com.example.superchat.core.preference.PrefKey
import com.example.superchat.core.preference.PreferenceKeys
import com.example.superchat.core.preference.PreferenceStorage


val PreferenceKeys.Token: PreferenceStorage.Key<OAuthToken?>
    get() = PrefKey<OAuthToken?>(name = "stringKey", defaultValue = null, encrypted = true)
