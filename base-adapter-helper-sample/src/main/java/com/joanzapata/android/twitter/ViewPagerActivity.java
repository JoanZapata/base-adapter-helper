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

    @OptionsItem(R.id.search)
    protected void onSearch() {
        viewPager.setCurrentItem(0);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final FragmentActivity context;

        @SuppressWarnings("unchecked")
        private Class<? extends Fragment>[] pages = new Class[]
                {TwitterFragment_.class, AboutFragment_.class};

        public ViewPagerAdapter(FragmentActivity context) {
            super(context.getSupportFragmentManager());
            this.context = context;
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
