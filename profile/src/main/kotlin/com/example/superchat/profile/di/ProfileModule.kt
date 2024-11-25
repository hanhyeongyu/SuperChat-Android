package com.example.superchat.profile.di

import com.example.superchat.profile.model.Profile
import com.example.superchat.profile.model.ProfileImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {
    @Binds
    internal abstract fun bindProfile(
        auth: ProfileImp
    ): Profile
}