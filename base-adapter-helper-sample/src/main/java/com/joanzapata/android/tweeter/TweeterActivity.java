package com.joanzapata.android.tweeter;

import android.os.Bundle;
import android.util.Log;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.Window;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.joanzapata.android.tweeter.component.ExtendedListView;
import twitter4j.Status;

import java.text.DateFormat;
import java.util.List;

import static com.actionbarsherlock.view.Window.FEATURE_INDETERMINATE_PROGRESS;
import static com.joanzapata.android.tweeter.TwitterService.getTweetsBefore;
import static java.text.DateFormat.SHORT;
import static java.text.DateFormat.getDateInstance;

@EActivity(R.layout.activity_main)
public class TweeterActivity extends SherlockActivity implements ExtendedListView.OnEndOfListListener<Status> {

    public static final String TAG = TweeterActivity.class.getSimpleName();

    private static final DateFormat dateFormat = getDateInstance(SHORT);

    @ViewById
    protected ExtendedListView listView;

    @NonConfigurationInstance
    protected QuickAdapter adapter;

    private String followingAccount = "JoanZap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(FEATURE_INDETERMINATE_PROGRESS);
    }

    @AfterViews
    void afterViews() {
        listView.setOnEndOfListListener(this);
        if (adapter == null)
            adapter = new QuickAdapter<Status>(this, R.layout.tweet) {
                @Override
                protected void convert(BaseAdapterHelper helper, Status status) {
                    if (status.isRetweet()) status = status.getRetweetedStatus();
                    helper.setText(R.id.tweetText, status.getText())
                            .setText(R.id.tweetName, status.getUser().getName())
                            .setText(R.id.tweetDate, dateFormat.format(status.getCreatedAt()))
                            .setImageUrl(R.id.tweetAvatar, status.getUser().getProfileImageURL());
                }
            };
        listView.setAdapter(adapter);
    }

    @Override
    @Background
    public void onEndOfList(Status status) {
        showProgressDialog(true);
        installTweets(getTweetsBefore(followingAccount, status));
    }

    @UiThread
    protected void installTweets(List<Status> tweets) {
        // Problem with connection, retry
        if (tweets == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        // No more tweets
        if (tweets.isEmpty()) {
            listView.setOnEndOfListListener(null);
        }
        showProgressDialog(false);
        adapter.addAll(tweets);
    }

    @UiThread
    protected void showProgressDialog(boolean visibility) {
        adapter.showIndeterminateProgress(visibility);
        getSherlock().setProgressBarIndeterminateVisibility(visibility);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
