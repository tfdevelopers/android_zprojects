package es.fraggel.flash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    public Camera cam;
    private Camera.Parameters camParams;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //Log.d("TORCH", "Check cam");
            // Get CAM reference
            cam = Camera.open();
            camParams = cam.getParameters();
            cam.startPreview();
            //Log.d("TORCH", "HAS CAM ["+hasCam+"]");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        String flash = intent.getStringExtra("flash");
        if ("1".equals(flash)) {
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(camParams);
            cam.startPreview();
        } else if ("0".equals(flash)) {
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            cam.setParameters(camParams);
            cam.startPreview();
        }
    }
}