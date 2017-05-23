/*
* Copyright (C) 2016 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.omnirom.device;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.database.ContentObserver;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;

import android.app.ActionBar;
import org.omnirom.device.SeekBarPreference;

public class DisplayCalibration extends PreferenceActivity implements
        Preference.OnPreferenceChangeListener {

    public static final String KEY_KCAL_ENABLED = "kcal_enabled";
    public static final String KEY_KCAL_RED = "kcal_red";
    public static final String KEY_KCAL_GREEN = "kcal_green";
    public static final String KEY_KCAL_BLUE = "kcal_blue";
    public static final String KEY_KCAL_SATURATION = "kcal_saturation";
    public static final String KEY_KCAL_CONTRAST = "kcal_contrast";

    private SeekBarPreference mKcalRed;
    private SeekBarPreference mKcalBlue;
    private SeekBarPreference mKcalGreen;
    private SeekBarPreference mKcalSaturation;
    private SeekBarPreference mKcalContrast;
    private int mProgress;
    private int mOldStrength;
    private int mMinValue;
    private int mMaxValue;
    private SwitchPreference mKcalEnabled;
    private boolean mEnabled;

    private static final String COLOR_FILE = "/sys/devices/platform/kcal_ctrl.0/kcal";
    private static final String COLOR_FILE_CONT = "/sys/devices/platform/kcal_ctrl.0/kcal_cont";
    private static final String COLOR_FILE_SAT = "/sys/devices/platform/kcal_ctrl.0/kcal_sat";
    private static final String COLOR_FILE_ENABLE = "/sys/devices/platform/kcal_ctrl.0/kcal_enable";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.display_cal);

        ImageView imageView = (ImageView) findViewById(R.id.calibration_pic);
        imageView.setImageResource(R.drawable.calibration_png);

        addPreferencesFromResource(R.xml.display_calibration);

        mKcalEnabled = (SwitchPreference) findPreference(KEY_KCAL_ENABLED);
        mEnabled = isCurrentlyEnabled(COLOR_FILE_ENABLE, this);
        mKcalEnabled.setChecked(mEnabled);
        mKcalEnabled.setOnPreferenceChangeListener(this);

        mKcalRed = (SeekBarPreference) findPreference(KEY_KCAL_RED);
        mKcalRed.setOnPreferenceChangeListener(this);
        mKcalGreen = (SeekBarPreference) findPreference(KEY_KCAL_GREEN);
        mKcalRed.setOnPreferenceChangeListener(this);
        mKcalBlue = (SeekBarPreference) findPreference(KEY_KCAL_BLUE);
        mKcalRed.setOnPreferenceChangeListener(this);
        mKcalSaturation = (SeekBarPreference) findPreference(KEY_KCAL_SATURATION);
        mKcalSaturation.setOnPreferenceChangeListener(this);
        mKcalContrast = (SeekBarPreference) findPreference(KEY_KCAL_CONTRAST);
        mKcalContrast.setOnPreferenceChangeListener(this);


    }

    private boolean isSupported(String file) {
        return Utils.fileWritable(file);
    }

    private static boolean isCurrentlyEnabled(String file, Context context) {
        return Utils.getFileValueAsBoolean(file, false);
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        if(enabled)
            Utils.writeValue(COLOR_FILE_ENABLE, "1");
        else
            Utils.writeValue(COLOR_FILE_ENABLE, "0");
        return true;
    }
}

