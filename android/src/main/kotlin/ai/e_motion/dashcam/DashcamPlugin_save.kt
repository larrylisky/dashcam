package ai.e_motion.dashcam

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** DashcamPlugin */
//public class DashcamPlugin: FlutterPlugin, MethodCallHandler {
public class DashcamPlugin_save: FlutterPlugin, MethodCallHandler {

    private var _methodChannel: MethodChannel? = null
    private var _eventChannel: EventChannel? = null
    private lateinit var _activity: Activity
    private lateinit var _context: Context


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "dashcam")
        channel.setMethodCallHandler(DashcamPlugin());
    }


    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "dashcam")
            channel.setMethodCallHandler(DashcamPlugin())
        }
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
            val res = configureCamera(w, h, rate)
            result.success("ConfigureCamera call returns $res")
        }
        else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    private fun configureCamera(width: Int?, height: Int?, frameRate: Double?) : Boolean {
        Log.d("configureCamera", "width: $width height: $height frameRate: $frameRate")
        return true
    }
}
