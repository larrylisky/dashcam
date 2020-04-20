package ai.e_motion.dashcam

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar


class DashcamPlugin: FlutterPlugin, MethodCallHandler, ActivityAware  {

  private var _methodChannel: MethodChannel? = null
  private var _eventChannel: EventChannel? = null
  private lateinit var _activity: Activity
  private lateinit var _context: Context
  private var dashcam = Dashcam()

  companion object {
    @Suppress("unused")
    @JvmStatic
    fun registerWith(registrar: Registrar) {

      val instance = DashcamPlugin()
      instance.onAttachedToEngine(registrar.activeContext(), registrar.messenger())

    }
  }


  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    onAttachedToEngine(binding.applicationContext, binding.binaryMessenger)

  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    _methodChannel!!.setMethodCallHandler(null)
    _methodChannel = null
    _eventChannel!!.setStreamHandler(null)
    _eventChannel = null

  }


  private fun onAttachedToEngine(binding: Context, messenger: BinaryMessenger) {

    _context = binding
    _methodChannel = MethodChannel(messenger, "dashcam")
   // _eventChannel = EventChannel(messenger, "dashcam/arrival")

  }

  override fun onDetachedFromActivity() {
    _activity.finish()
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    _activity = binding.activity
    _methodChannel!!.setMethodCallHandler(this)
    //_eventChannel!!.setStreamHandler(this)
  }

  override fun onDetachedFromActivityForConfigChanges() {
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    var arguments = call.arguments as? Map<String, Any>

    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    }
    else if (call.method == "configureCamera") {
      var w = arguments?.get("width") as? Int
      var h = arguments?.get("height") as? Int
      var rate = arguments?.get("frameRate") as? Double
      val res = dashcam.configureCamera(w, h, rate)
      result.success("ConfigureCamera call returns $res")
    }
    else {
      result.notImplemented()
    }
  }

  /*
  private fun configureCamera(width: Int?, height: Int?, frameRate: Double?) : Boolean {
    Log.d("configureCamera", "width: $width height: $height frameRate: $frameRate")
    return true
  }
  */
}
