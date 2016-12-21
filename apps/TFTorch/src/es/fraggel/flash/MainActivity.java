package es.fraggel.flash;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
//import static android.provider.Settings.System.FLASH_CALL;

public class MainActivity extends Activity implements View.OnClickListener {
    boolean flashIsOn=false;
    public Camera cam;
    private Camera.Parameters camParams;
    ImageButton btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btn =(ImageButton)findViewById(R.id.button);
        btn.setOnClickListener(this);
	if(cam==null){
		cam = Camera.open();
	        camParams = cam.getParameters();
	    }
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(camParams);
            cam.startPreview();
            btn.setImageResource(R.drawable.logoon);
            flashIsOn = true;
	
    }
    @Override
    public void onClick(View view) {
        try {
        if(flashIsOn){
	    if(cam==null){
		cam = Camera.open();
	        camParams = cam.getParameters();
	    }
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            cam.setParameters(camParams);
            cam.startPreview();
            btn.setImageResource(R.drawable.logooff);
            flashIsOn = false;
        }else{
	    if(cam==null){
		cam = Camera.open();
	        camParams = cam.getParameters();
	    }
            camParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(camParams);
            cam.startPreview();
            btn.setImageResource(R.drawable.logoon);
            flashIsOn = true;
        }
        }
        catch(Exception t) {
            t.printStackTrace();
        }
    }
	@Override
	protected void onStop() {
	super.onStop();
	    if (cam != null) {
	
	        cam.release();
		cam=null;
	    }
	    btn.setImageResource(R.drawable.logooff);
	    flashIsOn=false;
	}
}
