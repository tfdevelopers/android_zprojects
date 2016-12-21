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
import android.content.SharedPreferences;
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

import libcore.io.IoUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonalSettingsScreenLed extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener, OnPreferenceClickListener, Indexable {
    	private static final String TAG = "PersonalSettings";
	SwitchPreference mFlashCall=null;
	EditTextPreference mChangeDpi=null;
	ListPreference mChangeRes=null;
	//SwitchPreference mFlashLock=null;
	SwitchPreference mDobleTap=null;
	SwitchPreference mLedCharg=null;
	SharedPreferences prefs=null;
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
		if(cadenaLeida.trim().indexOf("ro.product.model")!=-1){
                    modelo = cadenaLeida.trim().replaceAll(" ","").replaceAll("ro.product.model=", "");
                }
                cadenaLeida = br.readLine();
            }
        }catch(Exception e){}

        final Activity activity = getActivity();
        final ContentResolver resolver = activity.getContentResolver();
	prefs = activity.getSharedPreferences("com.android.settings.tf", Context.MODE_PRIVATE);
        addPreferencesFromResource(R.xml.personal_settings_screenled);
	
	/*mLedCharg = (SwitchPreference) findPreference("toggle_led_charge");
        try {

        boolean ledCharge = Settings.System.getInt(getContentResolver(), LED_CHARGING, 1) == 1 ? false : true;
            mLedCharg.setChecked(ledCharge);
            mLedCharg.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }
	mDobleTap = (SwitchPreference) findPreference("toggle_double_tap");
        try {

 	boolean dobletap = Settings.System.getIntForUser(getContentResolver(), DOUBLE_TAP_SLEEP_GESTURE, 1,UserHandle.USER_CURRENT) == 1 ? true : false;
            mDobleTap.setChecked(dobletap);
	    mDobleTap.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }
	mChangeDpi = (EditTextPreference) findPreference("edt_cambioDpi");
        try {
	    DisplayMetrics metrics = getResources().getDisplayMetrics();
 	    mChangeDpi.setText(String.valueOf(metrics.densityDpi));
	    mChangeDpi.setSummary(getResources().getString(R.string.tf_dpi_current)+": "+String.valueOf(metrics.densityDpi)+"\n"+getResources().getString(R.string.tf_change_dpi_summary));
	    mChangeDpi.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }
	mChangeRes = (ListPreference) findPreference("edt_cambioRes");
        try {
	    int res=prefs.getInt("resolution",0);
	    DisplayMetrics metrics = getResources().getDisplayMetrics();
	    int height=metrics.heightPixels;
	    int width=metrics.widthPixels;
	    if(height<=1920 && height>1500){
			height=1920;
	    }else if(height<=1280 && height>1000){
			height=1280;
	    }else if(height<=960 && height>800){
			height=960;
	    } 
	    mChangeRes.setSummary(getResources().getString(R.string.tf_change_res_current)+": "+String.valueOf(height)+"x"+String.valueOf(width)+"\n"+getResources().getString(R.string.tf_change_res_summary));
	    mChangeRes.setValueIndex(res);
            mChangeRes.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }
	mFlashCall = (SwitchPreference) findPreference("toggle_flash_call");
        try {
	    boolean flashCall = Settings.System.getInt(getContentResolver(), FLASH_CALL, 0) == 1 ? true : false;
            mFlashCall.setChecked(flashCall);
	    mFlashCall.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }*/
	/*mFlashLock = (SwitchPreference) findPreference("toggle_flash_lockscreen");
        try {
	    boolean flashLock = Settings.System.getInt(getContentResolver(), FLASH_LOCKSCREEN, 0) == 1 ? true : false;
            mFlashLock.setChecked(flashLock);
	    mFlashLock.setOnPreferenceChangeListener(this);
	    if("k15tb_a".equals(modelo)){
		mFlashLock.setEnabled(false);
	    }
        } catch (Exception snfe) {
        }*/
    }
	@Override
	public boolean onPreferenceClick(Preference preference) {
		return false;
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		final String key = preference.getKey();
		
		/*if("toggle_flash_call".equals(key)){
			Settings.System.putInt(getContentResolver(), FLASH_CALL, (Boolean) newValue ? 1 : 0);
		}*/
		/*if("toggle_flash_lockscreen".equals(key)){
			Settings.System.putInt(getContentResolver(), FLASH_LOCKSCREEN, (Boolean) newValue ? 1 : 0);
		}*/
		
		/*if("toggle_double_tap".equals(key)){
			Settings.System.putIntForUser(getContentResolver(), DOUBLE_TAP_SLEEP_GESTURE, (Boolean) newValue ? 1 : 0, UserHandle.USER_CURRENT);
		    
		}

		if("toggle_led_charge".equals(key)){
		    	Settings.System.putInt(getContentResolver(), LED_CHARGING, (Boolean) newValue ? 0 : 1);
		}
		if("edt_cambioDpi".equals(key)){
            	try {
			final String nDPI=((String)newValue).trim();
			if("".equals(nDPI)){
			        try {
					
								OutputStream bos=Runtime.getRuntime().exec("su\n").getOutputStream();
								bos.write(("wm density reset\n").getBytes());		
                		        			bos.flush();
                		        			bos.close();
							
					
					Thread trh=new Thread(new Runnable(){
						public void run(){
							try {
								try {
								    Thread.sleep(1500);                 //1000 milliseconds is one second.
								} catch(InterruptedException ex) {
								    Thread.currentThread().interrupt();
								}
								OutputStream bos=Runtime.getRuntime().exec("su\n").getOutputStream();
								bos.write("busybox pkill -f com.android.launcher3\n".getBytes());							
								bos.write("busybox pkill -f es.tfandroid.tflauncher\n".getBytes());	
								bos.write("busybox pkill -f com.android.settings\n".getBytes());						
								bos.flush();
				        			bos.close();
								bos=Runtime.getRuntime().exec("su\n").getOutputStream();							
								bos.write("busybox pkill -f com.android.systemui\n".getBytes());
								bos.flush();
				        			bos.close();
								bos=Runtime.getRuntime().exec("su\n").getOutputStream();
								bos.write(("wm density reset\n").getBytes());
								bos.write(("wm size reset\n").getBytes());		
                		        			bos.flush();
                		        			bos.close();
							} catch(Exception ex) {
					    			
							}
						}
					});
					trh.start();									
					
				     }catch(Exception e){
                        		
                    		     }
			}else{
				if(Integer.parseInt(nDPI)<180|| Integer.parseInt(nDPI)>600){
					Toast.makeText(this.getActivity().getBaseContext(),nDPI+" "+getResources().getString(R.string.tf_dpi_notvalid), Toast.LENGTH_LONG).show();
				}else{
				     	try {
					
								OutputStream bos=Runtime.getRuntime().exec("su\n").getOutputStream();
								bos.write(("wm density "+nDPI+"\n").getBytes());		
                		        			bos.flush();
                		        			bos.close();
							
					
					Thread trh=new Thread(new Runnable(){
						public void run(){
							try {
								try {
								    Thread.sleep(1500);                 //1000 milliseconds is one second.
								} catch(InterruptedException ex) {
								    Thread.currentThread().interrupt();
								}
								OutputStream bos=Runtime.getRuntime().exec("su\n").getOutputStream();
								bos.write("busybox pkill -f com.android.launcher3\n".getBytes());							
								bos.write("busybox pkill -f es.tfandroid.tflauncher\n".getBytes());	
								bos.write("busybox pkill -f com.android.settings\n".getBytes());						
								bos.flush();
				        			bos.close();
								bos=Runtime.getRuntime().exec("su\n").getOutputStream();							
								bos.write("busybox pkill -f com.android.systemui\n".getBytes());
								bos.flush();
				        			bos.close();
								bos=Runtime.getRuntime().exec("su\n").getOutputStream();
								bos.write(("wm density "+nDPI+"\n").getBytes());		
                		        			bos.flush();
                		        			bos.close();
							} catch(Exception ex) {
					    			
							}
						}
					});
					trh.start();									
					
				     }catch(Exception e){
                        		
                    		     }
				}
			}
			
                    }catch(Exception e){
                        
                    }

        	} 
		if("edt_cambioRes".equals(key)){
            	try {
			
		     	try {
				String nRes2=((String)newValue).trim();
				int nDpi2=480;
				String nRes3="0";
				OutputStream bos=Runtime.getRuntime().exec("su\n").getOutputStream();				
				if("0".equals(nRes2)){
				     	nRes3="1080x1920";
					nDpi2=480;
				}else if("1".equals(nRes2)){
					nRes3="720x1280";
					nDpi2=320;
				}else if("2".equals(nRes2)){
					nRes3="540x960";
					nDpi2=240;
				}else{
					nRes3="1080x1920";
					nDpi2=480;
				}

				bos.write(("wm size "+nRes3+"\n").getBytes());
				bos.write(("wm density "+nDpi2+"\n").getBytes());		
        			bos.flush();
        			bos.close();
			final String nRes=nRes3;
			final int nDpi=nDpi2;	
			prefs.edit().putInt("resolution", Integer.parseInt(nRes2)).apply();
			prefs.edit().commit();
			Thread trh=new Thread(new Runnable(){
				public void run(){
					try {
						try {
						    Thread.sleep(1500);                 //1000 milliseconds is one second.
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						OutputStream bos=Runtime.getRuntime().exec("su\n").getOutputStream();
						bos.write("busybox pkill -f com.android.launcher3\n".getBytes());							
						bos.write("busybox pkill -f es.tfandroid.tflauncher\n".getBytes());						
						bos.flush();
		        			bos.close();
						bos=Runtime.getRuntime().exec("su\n").getOutputStream();							
						bos.write("busybox pkill -f com.android.systemui\n".getBytes());
						bos.flush();
		        			bos.close();
						bos=Runtime.getRuntime().exec("su\n").getOutputStream();
						bos.write(("wm size "+nRes+"\n").getBytes());
						bos.write(("wm density "+nDpi+"\n").getBytes());		
		        			bos.flush();
		        			bos.close();
					} catch(Exception ex) {
			    			
					}
				}
			});
			trh.start();									
			
		     }catch(Exception e){
        		
    		     }
                    }catch(Exception e){
                        
                    }
		
        	} */
		return true;
	}

}
