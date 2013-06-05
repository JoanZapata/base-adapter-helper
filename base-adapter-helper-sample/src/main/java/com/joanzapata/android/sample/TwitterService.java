package com.joanzapata.android.sample;

import android.util.Log;
import twitter4j.*;

import java.util.List;

public class TwitterService {

    private static final int NB_PAGES = 3;

    private static final Twitter twitter = TwitterFactory.getSingleton();

    private static final String TAG = TwitterService.class.getSimpleName();

    public static List<Status> getLastTweets(String name) {
        try {
            return twitter.getUserTimeline(name, new Paging().count(30));
        } catch (TwitterException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public static List<Status> getTweetsBefore(String name, long lastId) {
        try {
            return twitter.getUserTimeline(name, new Paging().count(30).maxId(lastId - 1));
        } catch (TwitterException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }
}
