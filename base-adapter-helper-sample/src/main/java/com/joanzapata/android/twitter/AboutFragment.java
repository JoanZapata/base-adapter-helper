package com.joanzapata.android.twitter;

import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FromHtml;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.about)
public class AboutFragment extends Fragment {

    @ViewById
    @FromHtml
    TextView htmlAbout;

}
