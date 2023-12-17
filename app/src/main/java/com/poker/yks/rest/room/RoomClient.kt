package com.poker.yks.rest.room

import android.app.Application
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient

object RoomClient {


    fun providesStockApi(
        app: Application,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): StockApi {
        val scarlet = Scarlet.Builder()
            .backoffStrategy(LinearBackoffStrategy(2000))
            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .webSocketFactory(okHttpClient.newWebSocketFactory("www.pokoje.pl"))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .addMessageAdapterFactory(GsonMessageAdapter.Factory(gson))
            .build()
        return scarlet.create()
    }

}
