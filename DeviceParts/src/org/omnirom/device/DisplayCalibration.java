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
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.database.ContentObserver;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;

import android.app.ActionBar;

public class DisplayCalibration extends DeviceSettings implements
        SeekBar.OnSeekBarChangeListener {

    public static final String KEY_KCAL = "kcal_enable";

    private SeekBar mSeekBar;
    private OnSeekBarChangeListener mOnSeekBarChangeListener;
    private int mOldStrength;
    private int mMinValue;
    private int mMaxValue;
    private SwitchPreference mKcalEnable;

    private static final String COLOR_FILE = "/sys/devices/platform/kcal_ctrl.0/kcal";
    private static final String COLOR_FILE_CONT = "/sys/devices/platform/kcal_ctrl.0/kcal_cont";
    private static final String COLOR_FILE_SAT = "/sys/devices/platform/kcal_ctrl.0/kcal_sat";
    private static final String COLOR_FILE_ENABLE = "/sys/devices/platform/kcal_ctrl.0/kcal_enable";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //actionBar.setDisplayHomeAsUpEnabled(true);

        addPreferencesFromResource(R.xml.display_calibration);
    }

    @Override
    public void onProgressChanged(final SeekBar seekbar, final int progress,
        final boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seek) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seek) {
    }
}

