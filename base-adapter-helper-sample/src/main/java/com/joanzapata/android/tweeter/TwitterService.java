package com.joanzapata.android.tweeter;

import android.util.Log;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class TwitterService {

    private static final int NB_PAGES = 3;

    private static final Twitter twitter = TwitterFactory.getSingleton();

    private static final String TAG = TwitterService.class.getSimpleName();

    private static final int TWEET_COUNT = 40;

    public static List<Status> getTweetsBefore(String username, Status beforeTweet) {
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
