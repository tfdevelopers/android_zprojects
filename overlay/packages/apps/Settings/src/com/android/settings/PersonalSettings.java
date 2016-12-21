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
import static android.provider.Settings.System.SETTINGS_COLUMNS;
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

public class PersonalSettings extends SettingsPreferenceFragment implements OnPreferenceClickListener, Indexable {
    	private static final String TAG = "PersonalSettings";
	Preference mSystem=null;
	Preference mScreenLed=null;
	Preference mSound=null;
	Preference mMisc=null;
	String modelo="";
/* Vanzo:luanshijun on: Fri, 24 Apr 2015 10:00:49 +0800
 * add breathlight support
 */
    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.PERSONAL;
    }
    public PersonalSettings(){}

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

        addPreferencesFromResource(R.xml.personal_settings);
	/*mSystem = (Preference) findPreference("toggle_settings_system");
        try {
	    mSystem.setOnPreferenceClickListener(this);
        } catch (Exception snfe) {
        }
	mScreenLed = (Preference) findPreference("toggle_settings_screenled");
        try {
	    mScreenLed.setOnPreferenceClickListener(this);
        } catch (Exception snfe) {
        }
	mSound = (Preference) findPreference("toggle_settings_sound");
        try {
	    mSound.setOnPreferenceClickListener(this);
        } catch (Exception snfe) {
        }
	mMisc = (Preference) findPreference("toggle_settings_misc");
        try {
	    mMisc.setOnPreferenceClickListener(this);
        } catch (Exception snfe) {
        }*/
    }
	@Override
	public boolean onPreferenceClick(Preference preference) {
		final String key = preference.getKey();
		if("toggle_settings_system".equals(key)){
			Toast.makeText(getActivity(), "SYSTEM", 
   			Toast.LENGTH_SHORT).show();
        	} 
		if("toggle_settings_screenled".equals(key)){
			Toast.makeText(getActivity(), "SCREENLED", 
   			Toast.LENGTH_SHORT).show();
        	} 
		if("toggle_settings_sound".equals(key)){
			Toast.makeText(getActivity(), "SOUND", 
   			Toast.LENGTH_SHORT).show();
        	} 
		if("toggle_settings_misc".equals(key)){
			Toast.makeText(getActivity(), "MISC", 
   			Toast.LENGTH_SHORT).show();
        	} 
		if("toggle_settings_modem".equals(key)){
			Toast.makeText(getActivity(), "MODEM", 
   			Toast.LENGTH_SHORT).show();
        	} 
		return true;
	}

}
