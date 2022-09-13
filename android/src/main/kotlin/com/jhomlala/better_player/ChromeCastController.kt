package com.jhomlala.better_player

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.*
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView


class ChromeCastController constructor(binaryMessenger: BinaryMessenger, viewId: Int, context: Context) :
    SessionManagerListener<Session?>, PlatformView, MethodChannel.MethodCallHandler, PendingResult.StatusListener {
    val binaryMessenger: BinaryMessenger
    val viewId: Int
    val context: Context
    var methodChannel: MethodChannel
    var mediaRouteButton: MediaRouteButton
    var sessionManager: SessionManager

    init {
        this.binaryMessenger = binaryMessenger
        this.viewId = viewId
        this.context = context
        methodChannel = MethodChannel(binaryMessenger, "flutter_video_cast/chromeCast_$viewId")
        mediaRouteButton = MediaRouteButton(context)
        mediaRouteButton.setRemoteIndicatorDrawable(ColorDrawable(Color.TRANSPARENT))
        sessionManager = CastContext.getSharedInstance()?.sessionManager!!
        CastButtonFactory.setUpMediaRouteButton(context, mediaRouteButton)
        methodChannel.setMethodCallHandler(this)
        Log.d("ChromeCast", "Controller created for id$viewId")
    }


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method.equals("chromeCast#click")) {
            Log.d("ChromeCast", "CLICK CALLED")
            mediaRouteButton.performClick()
            result.success(null)
        }
    }

    override fun getView(): View {
        return mediaRouteButton
    }


    override fun onSessionEnded(p0: Session, p1: Int) {
        methodChannel.invokeMethod("chromeCast#didEndSession", null);
    }

    override fun onSessionEnding(p0: Session) {
        TODO("Not yet implemented")
    }

    override fun onSessionResumeFailed(p0: Session, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSessionResumed(p0: Session, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSessionResuming(p0: Session, p1: String) {
        TODO("Not yet implemented")
    }

    override fun onSessionStartFailed(p0: Session, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSessionStarted(p0: Session, p1: String) {
        methodChannel.invokeMethod("chromeCast#didStartSession", null);
    }

    override fun onSessionStarting(p0: Session) {
        TODO("Not yet implemented")
    }

    override fun onSessionSuspended(p0: Session, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onComplete(p0: Status) {
        TODO("Not yet implemented")
    }



    override fun onFlutterViewAttached(flutterView: View) {}

    override fun onFlutterViewDetached() {}

    override fun dispose() {}

    override fun onInputConnectionLocked() {}

    override fun onInputConnectionUnlocked() {}


}