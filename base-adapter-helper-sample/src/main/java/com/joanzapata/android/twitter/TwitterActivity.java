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

import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.joanzapata.android.twitter.component.ExtendedListView;
import twitter4j.Status;

import java.text.DateFormat;
import java.util.List;

import static com.actionbarsherlock.view.Window.FEATURE_INDETERMINATE_PROGRESS;
import static com.joanzapata.android.twitter.R.id.*;
import static java.text.DateFormat.*;

@EActivity(R.layout.activity_main)
public class TwitterActivity extends SherlockActivity implements ExtendedListView.OnEndOfListListener<Status> {

    public static final String TAG = TwitterActivity.class.getSimpleName();

    private static final DateFormat dateFormat = getDateInstance(SHORT);

    @ViewById
    protected ExtendedListView listView;

    @Bean
    protected TwitterService twitter;

    @NonConfigurationInstance
    protected QuickAdapter adapter;

    private String followingAccount = "JoanZap";

    @AfterViews
    void afterViews() {
        setTitle("@" + followingAccount);
        listView.setOnEndOfListListener(this);
        if (adapter == null)
            adapter = new QuickAdapter<Status>(this, R.layout.tweet) {
                @Override
                protected void convert(BaseAdapterHelper helper, Status status) {
                    boolean isRetweet = status.isRetweet();
                    if (isRetweet) status = status.getRetweetedStatus();

                    helper.setText(tweetText, status.getText())
                            .setVisible(tweetRT, isRetweet)
                            .setText(tweetName, status.getUser().getName())
                            .setText(tweetDate, dateFormat.format(status.getCreatedAt()))
                            .setImageUrl(tweetAvatar, status.getUser().getProfileImageURL())
                            .linkify(tweetText);
                }
            };
        listView.setAdapter(adapter);
    }

    @Override
    @Background
    public void onEndOfList(Status status) {
        showProgressDialog(true);
        installTweets(twitter.getTweetsBefore(followingAccount, status));
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
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }

}
