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

import libcore.io.IoUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonalSettingsSound extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener, OnPreferenceClickListener, Indexable {
    	private static final String TAG = "PersonalSettings";
	SwitchPreference mSoundCap=null;
	SwitchPreference mSoundUnion=null;
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

        addPreferencesFromResource(R.xml.personal_settings_sound);
	
	/*mSoundCap = (SwitchPreference) findPreference("toggle_sound_cap");
        try {

 	boolean soundCap = Settings.System.getInt(getContentResolver(), SCREENSHOT_SHUTTER_SOUND, 1) == 1 ? false : true;
            mSoundCap.setChecked(soundCap);
	    mSoundCap.setOnPreferenceChangeListener(this);
        } catch (Exception snfe) {
        }*/
	/*mSoundUnion = (SwitchPreference) findPreference("toggle_sound_union");
	try {
	boolean soundUnion = Settings.System.getInt(getContentResolver(), NOTIFICATIONS_USE_RING_VOLUME, 1) == 1 ? false : true;
            mSoundUnion.setChecked(soundUnion);
	    mSoundUnion.setOnPreferenceChangeListener(this);
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
		
		/*if("toggle_sound_cap".equals(key)){
		    	Settings.System.putInt(getContentResolver(), SCREENSHOT_SHUTTER_SOUND, (Boolean) newValue ? 0 : 1);
		} 
		if("toggle_sound_union".equals(key)){
		    	Settings.System.putInt(getContentResolver(), NOTIFICATIONS_USE_RING_VOLUME, (Boolean) newValue ? 0 : 1);
		}*/
		return true;
	}

}
