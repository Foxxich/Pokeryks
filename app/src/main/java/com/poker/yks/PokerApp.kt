package com.poker.yks

import android.app.Application
import timber.log.Timber
import timber.log.Timber.Forest.plant


class PokerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // comment if you dont want to see any logs
        plant(Timber.DebugTree())
    }
}