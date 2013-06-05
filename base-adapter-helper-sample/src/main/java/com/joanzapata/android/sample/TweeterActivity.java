package com.joanzapata.android.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.Window;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.joanzapata.android.R;
import com.joanzapata.android.sample.component.ExtendedListView;
import twitter4j.Status;

import java.util.List;

import static com.joanzapata.android.sample.TwitterService.*;

@EActivity(R.layout.activity_main)
public class TweeterActivity extends SherlockActivity implements ExtendedListView.OnEndOfListListener<Status> {

    public static final String TAG = TweeterActivity.class.getSimpleName();

    @ViewById
    protected ExtendedListView listView;

    private QuickAdapter adapter;

    private String followingAccount = "JoanZap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    }

    @AfterViews
    void afterViews() {
        listView.setOnEndOfListListener(this);
        listView.setAdapter(adapter = new QuickAdapter<Status>(this, R.layout.tweet) {
            @Override
            protected void convert(BaseAdapterHelper helper, Status status) {
                if (status.isRetweet()) status = status.getRetweetedStatus();
                helper.setText(R.id.tweetText, status.getText())
                        .setText(R.id.tweetName, status.getUser().getName())
                        .setImageUrl(R.id.tweetAvatar, status.getUser().getProfileImageURL());
            }
        });
    }

    @Override
    @Background
    public void onEndOfList(Status status) {
        showProgressDialog(true);
        installTweets(getTweetsBefore(followingAccount, status));
    }

    @UiThread
    protected void installTweets(List<Status> tweets) {
        if (tweets.isEmpty())
            listView.setOnEndOfListListener(null);
        showProgressDialog(false);
        adapter.addAll(tweets);
    }

    @UiThread
    protected void showProgressDialog(boolean visibility) {
        getSherlock().setProgressBarIndeterminateVisibility(visibility);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
