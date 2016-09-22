package template.cheng.hollis.template.GoogleAnalytics;

/*
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;

import template.cheng.hollis.template.R;

/**
 * This is a subclass of {@link Application} used to provide shared objects for this app, such as
 * the {@link com.google.android.gms.analytics.Tracker}.
 */
public class AnalyticsApplication extends Application {
    private com.google.android.gms.analytics.Tracker Tracker;

    /**
     * Gets the default {@link com.google.android.gms.analytics.Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public com.google.android.gms.analytics.Tracker getDefaultTracker() {
        if (Tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            Tracker = analytics.newTracker(R.xml.app_tracker);
            //need to add google analytics id in to above xml
        }
        return Tracker;
    }

    public com.google.android.gms.analytics.Tracker getTracker() {
        return Tracker;
    }

    public void setTracker(com.google.android.gms.analytics.Tracker Tracker) {
        this.Tracker = Tracker;
    }
}