package com.joanzapata.android.sample;

import android.util.Log;
import twitter4j.*;

import java.util.List;

public class TwitterService {

    private static final int NB_PAGES = 3;

    private static final Twitter twitter = TwitterFactory.getSingleton();

    private static final String TAG = TwitterService.class.getSimpleName();

    public static List<Status> getTweetsBefore(String username, Status beforeTweet) {
        try {
            final Paging paging = new Paging().count(30);
            if (beforeTweet != null) {
                paging.maxId(beforeTweet.getId() - 1);
            }
            return twitter.getUserTimeline(username, paging);
        } catch (TwitterException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }
}
