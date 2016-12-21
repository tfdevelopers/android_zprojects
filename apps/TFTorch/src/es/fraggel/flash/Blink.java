package es.fraggel.flash;

import android.hardware.Camera;

/**
 * Created by root on 26/09/15.
 */
public class Blink implements Runnable {
    public Camera cam;
    private Camera.Parameters camParams;
    @Override
    public void run() {
        try {
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(camParams);
            cam.startPreview();
            Long blinkDelay = 250L; //Delay in ms
            Thread.sleep(blinkDelay);
        }catch(Exception e){

        }

    }
}
