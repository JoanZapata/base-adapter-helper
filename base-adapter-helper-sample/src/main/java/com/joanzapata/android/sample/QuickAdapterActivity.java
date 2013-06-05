package com.joanzapata.android.sample;

import android.app.Activity;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.joanzapata.android.R;
import com.joanzapata.android.sample.util.ExtendedListView;
import twitter4j.*;

import java.util.List;

import static com.joanzapata.android.sample.TwitterService.getLastTweets;
import static com.joanzapata.android.sample.TwitterService.getTweetsBefore;

@EActivity(R.layout.activity_main)
public class QuickAdapterActivity extends Activity implements ExtendedListView.OnEndOfListListener<Status> {

    public static final String TAG = QuickAdapterActivity.class.getSimpleName();

    @ViewById
    protected ExtendedListView listView;

    private QuickAdapter adapter;

    private String followingAccount = "JoanZap";

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
        if (status == null) {
            installTweets(getLastTweets(followingAccount));
            return;
        }
        installTweets(getTweetsBefore(followingAccount, status.getId()));
    }

    @UiThread
    protected void installTweets(List<Status> tweets) {
        adapter.addAll(tweets);
    }
}
