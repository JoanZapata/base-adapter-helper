/**
 * Copyright 2013 Joan Zapata
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
package com.joanzapata.android.twitter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.*;

@EActivity(R.layout.main)
@OptionsMenu(R.menu.actionbar)
public class ViewPagerActivity extends SherlockFragmentActivity {

    private static final String TAG = ViewPagerActivity.class.getSimpleName();

    @ViewById
    protected ViewPager viewPager;

    @AfterViews
    protected void setUp() {
        viewPager.setAdapter(new ViewPagerAdapter(this));
    }

    @OptionsItem(R.id.about)
    protected void onAbout() {
        viewPager.setCurrentItem(1);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        @SuppressWarnings("unchecked")
        private Class<? extends Fragment>[] pages = new Class[]
                {TwitterFragment_.class, AboutFragment_.class};

        public ViewPagerAdapter(FragmentActivity context) {
            super(context.getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return pages[position].newInstance();
            } catch (Exception e) {
                Log.e(TAG, "", e);
                return null;
            }
        }
    }
}
