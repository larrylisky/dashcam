package ai.e_motion.dashcam

import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

class Dashcam(): Fragment(), View.OnClickListener,                                     
                            ActivityCompat.OnRequestPermissionsResultCallback {
    
    
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    fun configureCamera(width: Int?, height: Int?, frameRate: Double?) : Boolean {
        Log.d("configureCamera", "width: $width height: $height frameRate: $frameRate")
        return true
    }
}