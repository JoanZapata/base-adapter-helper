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
package com.joanzapata.android.twitter.service;

import android.util.Log;
import com.googlecode.androidannotations.annotations.EBean;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

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
