package com.example.superchat.core.network.di

import com.example.superchat.core.network.monitor.ConnectivityManagerNetworkMonitor
import com.example.superchat.core.network.monitor.NetworkMonitor
import com.example.superchat.core.network.monitor.TimeZoneBroadcastMonitor
import com.example.superchat.core.network.monitor.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkUtilModule {
    @Binds
    internal abstract fun bindsNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor

    @Binds
    internal abstract fun binds(impl: TimeZoneBroadcastMonitor): TimeZoneMonitor
}