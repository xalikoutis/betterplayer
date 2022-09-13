package com.jhomlala.better_player

import android.app.Activity
import android.content.Context
import android.util.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class ChromeCastFactoryKotlin(binaryMessenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    var activty: Activity? = null
    var binaryMessenger: BinaryMessenger

    init {
        this.binaryMessenger = binaryMessenger
    }


    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        Log.d("ChromeCast", "Create view! $viewId")
        return ChromeCastController(binaryMessenger, viewId, context!!)
    }
}