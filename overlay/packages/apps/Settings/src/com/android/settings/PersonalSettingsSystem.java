/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import com.android.internal.view.RotationPolicy;
import com.android.settings.DropDownPreference;
import com.android.settings.DropDownPreference.Callback;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.internal.logging.MetricsLogger;
//import static android.provider.Settings.System.NET_SPEED;
//import static android.provider.Settings.System.FLASH_CALL;
//import static android.provider.Settings.System.MULTI_USER;
//import static android.provider.Settings.System.SEARCH_BAR_RECENTS;
//import static android.provider.Settings.System.CLOCK_AM;
//import static android.provider.Settings.System.CLOCK_CENTER;
//import static android.provider.Settings.System.MAIN_SD_CARD;
//import static android.provider.Settings.System.SCREENSHOT_SHUTTER_SOUND;
//import static android.provider.Settings.System.KILL_APP_LONGPRESS_BACK;
//import static android.provider.Settings.System.VIBRATE_BUTTONS;
//import static android.provider.Settings.System.DOUBLE_TAP_SLEEP_GESTURE;
//import static android.provider.Settings.System.LED_CHARGING;
//import static android.provider.Settings.System.FLASH_LOCKSCREEN;
import static android.provider.Settings.System.SETTINGS_COLUMNS;
//import static android.provider.Settings.System.NOTIFICATIONS_USE_RING_VOLUME;
import android.app.Activity;
import android.app.ActivityManagerNative;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.preference.EditTextPreference;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.util.DisplayMetrics;
import android.app.ActivityManager;
import java.lang.reflect.Method;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.lang.Thread;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import android.view.WindowManager;
import android.os.UserHandle;
import android.os.PowerManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import libcore.io.IoUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonalSettingsSystem extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener, OnPreferenceClickListener, Indexable {
    	private static final String TAG = "PersonalSettings";
	SwitchPreference mMultiUser=null;
	//SwitchPreference mSoftKeys=null;
	SwitchPreference mBackKill=null;
	SwitchPreference msuperSu=null;
	String multiuser="";
	//String softKeys="";
	String modelo="";
/* Vanzo:luanshijun on: Fri, 24 Apr 2015 10:00:49 +0800
 * add breathlight support
 */
@Override
    protected int getMetricsCategory() {
        return MetricsLogger.PERSONAL;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	try {
	    
	    BufferedReader br = new BufferedReader(new FileReader(new File("/system/build.prop")));
            String cadenaLeida = br.readLine();
            while (cadenaLeida != null) {
                if(cadenaLeida.trim().indexOf("fw.show_multiuserui")!=-1){
                    multiuser = cadenaLeida.trim().replaceAll(" ","").replaceAll("fw.show_multiuserui=", "");
                }
                /*if(cadenaLeida.trim().indexOf("qemu.hw.mainkeys")!=-1){
                    softKeys = cadenaLeida.trim().replaceAll(" ","").replaceAll("qemu.hw.mainkeys=", "");
                }*/
		if(cadenaLeida.trim().indexOf("ro.product.model")!=-1){
                    modelo = cadenaLeida.trim().replaceAll(" ","").replaceAll("ro.product.model=", "");
                }
                cadenaLeida = br.readLine();
            }
        }catch(Exception e){}


        final Activity activity = getActivity();
        final ContentResolver resolver = activity.getContentResolver();

        addPreferencesFromResource(R.xml.personal_settings_system);
	
	/*mMultiUser = (SwitchPreference) findPreference("toggle_multi_user");
        try {
            boolean multi =false;
            if(multiuser!=null){
                if("0".equals(multiuser)){
                    multi=false;
                }else if("1".equals(multiuser)){
                    multi=true;
                }
            }else{
                multi=false;
            }
             //Settings.System.getInt(getContentResolver(), MULTI_USER, 0) == 1 ? true : false;
            mMultiUser.setChecked(multi);
            mMultiUser.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }*/
        /*mSoftKeys = (SwitchPreference) findPreference("toggle_main_keys");
        try {
            boolean soft =false;
            if(softKeys!=null){
                if("1".equals(softKeys)){
                    soft=false;
                }else if("0".equals(softKeys)){
                    soft=true;
                }
            }
             //Settings.System.getInt(getContentResolver(), MULTI_USER, 0) == 1 ? true : false;
            mSoftKeys.setChecked(soft);
            mSoftKeys.setOnPreferenceChangeListener(this);
	    if("k15tb_a".equals(modelo)){
		mSoftKeys.setEnabled(false);
	    }
        } catch (Exception snfe) {
        }*/
	/*mBackKill = (SwitchPreference) findPreference("toggle_back_kill");
        try {

 	boolean backKill = Settings.System.getInt(getContentResolver(), KILL_APP_LONGPRESS_BACK, 0) == 1 ? true : false;
            mBackKill.setChecked(backKill);
	    mBackKill.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }*/
	
	msuperSu = (SwitchPreference) findPreference("toggle_supersu");
	boolean supersu =false;
        try {
	    File supSu=new File("/system/xbin/su");
            
	    if(supSu.exists()){
	    	supersu=true;
	    }else{
		    supersu=false;
		    supSu=new File("/system/bin/su");
		    if(supSu.exists()){
		    	supersu=true;
		    }else{
			    supersu=false;
			    supSu=new File("/su/su.d");
			    if(supSu.exists()){
			    	supersu=true;
			    }else{
				supersu=false;
				    supSu=new File("/su/lib/libsupol.so");
				    if(supSu.exists()){
				    	supersu=true;
				    }else{
					supersu=false;
				    }
			    }
		    }
	    }
            msuperSu.setChecked(supersu);
            msuperSu.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }
    }
	@Override
	public boolean onPreferenceClick(Preference preference) {
		return false;
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		final String key = preference.getKey();
		if("toggle_supersu".equals(key)){
                        if((Boolean) newValue){
                           
				try {
						String cad="/system/UPDATE-SuperSU-v2.76systemless.zip";

						BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File("/cache/recovery/extendedcommand")));

						bos.write(("run_program(\"/sbin/umount\",\""+"/system"+"\");\n").getBytes());
						bos.write(("run_program(\"/sbin/mount,\""+"/system"+"\");\n").getBytes());
						bos.write(("install_zip(\"" + cad + "\");\n").getBytes());
						bos.flush();
						bos.close();

						BufferedOutputStream bos2=new BufferedOutputStream(new FileOutputStream(new File("/cache/recovery/openrecoveryscript")));

						bos2.write(("unmount "+"/system"+"\n").getBytes());
						bos2.write(("mount "+"/system"+"\n").getBytes());
						bos2.write(("install " + cad + "\n").getBytes());
						bos2.flush();
						bos2.close();
						((PowerManager) getSystemService(Context.POWER_SERVICE)).reboot("recovery");
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                        }else{
				

                            /*try {		
                                OutputStream bos=Runtime.getRuntime().exec("su").getOutputStream();
				bos.write("dd if=/system/boot.img of=/dev/block/platform/mtk-msdc.0/11230000.msdc0/by-name/boot bs=4096\n".getBytes());
				bos.write("rm -r /data/app/eu.chainfire.supersu-1\n".getBytes());
				bos.write("rm -r /data/app/eu.chainfire.supersu-2\n".getBytes());
				bos.write("rm -r /data/data/eu.chainfire.supersu\n".getBytes());
				bos.write("rm /data/stock_boot_*\n".getBytes());	
				bos.write("rm /data/su.img\n".getBytes());
				bos.write("rm /cache/su.img\n".getBytes());
				bos.write("reboot\n".getBytes());
				bos.flush();
				bos.close();
                            }catch(Exception e){
				Toast.makeText(this.getActivity().getBaseContext(), e.getMessage()+" "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }*/
				try {
						String cad="/system/unroot.zip";

						BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File("/cache/recovery/extendedcommand")));

						bos.write(("run_program(\"/sbin/umount\",\""+"/system"+"\");\n").getBytes());
						bos.write(("run_program(\"/sbin/mount,\""+"/system"+"\");\n").getBytes());
						bos.write(("run_program(\"/sbin/sleep\", \"3\");\n").getBytes());
						bos.write(("install_zip(\"" + cad + "\");\n").getBytes());
						bos.flush();
						bos.close();

						BufferedOutputStream bos2=new BufferedOutputStream(new FileOutputStream(new File("/cache/recovery/openrecoveryscript")));

						bos2.write(("unmount "+"/system"+"\n").getBytes());
						bos2.write(("mount "+"/system"+"\n").getBytes());
						bos2.write(("cmd /sbin/sleep 3;\n").getBytes());
						bos2.write(("install " + cad + "\n").getBytes());
						bos2.flush();
						bos2.close();
						((PowerManager) getSystemService(Context.POWER_SERVICE)).reboot("recovery");
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                        }
                        //Toast.makeText(this.getActivity().getBaseContext(), getResources().getString(R.string.tf_need_restart), Toast.LENGTH_SHORT).show();
		}
		/*if("toggle_multi_user".equals(key)){
			if((Boolean) newValue){
		            try {
		                OutputStream bos=Runtime.getRuntime().exec("su").getOutputStream();
		                bos.write("mount -o,remount rw /system\n".getBytes());
		                bos.write("busybox sed -i s/fw.show_multiuserui=0/fw.show_multiuserui=1/g /system/build.prop\n".getBytes());
		                bos.flush();
		                bos.close();
		            }catch(Exception e){
		                Toast.makeText(this.getActivity().getBaseContext(), e.getMessage()+" "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
		            }
		        }else{
		            try {
		                OutputStream bos=Runtime.getRuntime().exec("su").getOutputStream();
                        bos.write("mount -o,remount rw /system\n".getBytes());
                        bos.write("busybox sed -i s/fw.show_multiuserui=1/fw.show_multiuserui=0/g /system/build.prop\n".getBytes());
                        bos.flush();
                        bos.close();
		            }catch(Exception e){
		               
		            }
		        }
			Toast.makeText(this.getActivity().getBaseContext(), getResources().getString(R.string.tf_need_restart), Toast.LENGTH_SHORT).show();
			
		}*/
		/*if("toggle_main_keys".equals(key)){
            if((Boolean) newValue){
                    try {
                        OutputStream bos=Runtime.getRuntime().exec("su").getOutputStream();
                        bos.write("mount -o,remount rw /system\n".getBytes());
                        bos.write("busybox sed -i s/qemu.hw.mainkeys=1/qemu.hw.mainkeys=0/g /system/build.prop\n".getBytes());
                        bos.flush();
                        bos.close();
                    }catch(Exception e){
                        
                    }
             }else{
                    try {
                        OutputStream bos=Runtime.getRuntime().exec("su").getOutputStream();
                        bos.write("mount -o,remount rw /system\n".getBytes());
                        bos.write("busybox sed -i s/qemu.hw.mainkeys=0/qemu.hw.mainkeys=1/g /system/build.prop\n".getBytes());
                        bos.flush();
                        bos.close();
                    }catch(Exception e){
                        
                    }
             }
			
            Toast.makeText(this.getActivity().getBaseContext(), getResources().getString(R.string.tf_need_restart), Toast.LENGTH_SHORT).show();
        } */
	
	/*if("toggle_back_kill".equals(key)){
            	Settings.System.putInt(getContentResolver(), KILL_APP_LONGPRESS_BACK, (Boolean) newValue ? 1 : 0);
        }*/	
		
		return true;
	}

}
