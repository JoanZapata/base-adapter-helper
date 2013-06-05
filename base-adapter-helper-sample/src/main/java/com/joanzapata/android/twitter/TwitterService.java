package com.joanzapata.android.twitter;

import android.content.Context;
import android.util.Log;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import twitter4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@EBean
public class TwitterService {

    private static final int NB_PAGES = 3;

    private final Twitter twitter;

    private static final String TAG = TwitterService.class.getSimpleName();

    private static final int TWEET_COUNT = 40;

    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
    }

    public List<Status> getTweetsBefore(String username, Status beforeTweet) {
        try {
            final Paging paging = new Paging().count(TWEET_COUNT);
            if (beforeTweet != null) paging.maxId(beforeTweet.getId() - 1);
            final ResponseList<Status> userTimeline = twitter.getUserTimeline(username, paging);
            if (userTimeline == null) return new ArrayList<Status>();
            return userTimeline;
        } catch (TwitterException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }
}
