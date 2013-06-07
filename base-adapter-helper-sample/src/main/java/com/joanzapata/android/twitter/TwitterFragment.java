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

import android.os.Build;
import android.util.Log;
import android.view.View;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.view.menu.ActionMenuItemView;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.googlecode.androidannotations.annotations.*;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.joanzapata.android.twitter.component.ExtendedListView;
import com.joanzapata.android.twitter.service.TwitterService;
import twitter4j.Status;

import java.text.DateFormat;
import java.util.List;

import static com.joanzapata.android.twitter.R.id.*;
import static java.text.DateFormat.*;

@OptionsMenu(R.menu.actionbar_search)
@EFragment(R.layout.activity_main)
public class TwitterFragment extends SherlockFragment implements ExtendedListView.OnEndOfListListener<Status> {

    private static final DateFormat dateFormat = getDateInstance(SHORT);

    @ViewById
    protected ExtendedListView listView;

    @Bean
    protected TwitterService twitter;

    protected QuickAdapter<Status> adapter;

    protected String followingAccount = "JoanZap";

    private MenuItem searchMenuItem;

    @AfterViews
    void afterViews() {
        setRetainInstance(true);
        getSherlockActivity().setTitle("@" + followingAccount);
        listView.setOnEndOfListListener(this);
        if (adapter == null)
            adapter = new QuickAdapter<Status>(getSherlockActivity(), R.layout.tweet) {
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
        showIndeterminateProgress(true);
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
        showIndeterminateProgress(false);
        adapter.addAll(tweets);
    }

    @UiThread
    protected void showIndeterminateProgress(boolean visibility) {
        adapter.showIndeterminateProgress(visibility);
        final View refreshMenuItem = getSherlockActivity().findViewById(R.id.refresh);
        final View searchMenuItem = getSherlockActivity().findViewById(R.id.search);
        searchMenuItem.setEnabled(!visibility);
        refreshMenuItem.setEnabled(!visibility);
        if (Build.VERSION.SDK_INT >= 11) {
            float alpha = visibility ? 0.4f : 1f;
            searchMenuItem.setAlpha(alpha);
            refreshMenuItem.setAlpha(alpha);
        }
    }

    @OptionsItem(R.id.refresh)
    protected void onRefresh(final MenuItem item) {
        onSearchSubmit(followingAccount);
    }

    @OptionsItem(R.id.search)
    protected void onSearch(final MenuItem item) {
        this.searchMenuItem = item;
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint(followingAccount);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                item.collapseActionView();
                onSearchSubmit(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void onSearchSubmit(String query) {
        followingAccount = query;
        afterViews();
        adapter.clear();
    }
}
