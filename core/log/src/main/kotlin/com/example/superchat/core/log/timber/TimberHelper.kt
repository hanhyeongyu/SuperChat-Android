package com.example.superchat.core.log.timber

import com.example.superchat.core.log.BuildConfig
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


class TimberHelper {
    companion object{
        fun setupLogger(){
            if(BuildConfig.DEBUG){
                plant(DebugTree())
            }else{
                plant(CrashReportingTree())
            }
        }
    }
}

