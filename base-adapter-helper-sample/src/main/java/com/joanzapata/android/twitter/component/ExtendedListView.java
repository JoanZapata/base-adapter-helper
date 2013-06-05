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
package com.joanzapata.android.twitter.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Add a method to the basic list view.<br/>
 * setOnEndOfListListener();
 */
public class ExtendedListView extends ListView {

    private OnEndOfListListener onEndOfListListener;

    private boolean hasWarned = false;

    public ExtendedListView(Context context) {
        super(context);
        init();
    }

    public ExtendedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExtendedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (hasWarned
                        || view.getLastVisiblePosition() != totalItemCount - 1
                        || onEndOfListListener == null)
                    return;

                hasWarned = true;
                Object lastItem = view.getItemAtPosition(totalItemCount - 1);
                if (lastItem != null || totalItemCount == 0)
                    onEndOfListListener.onEndOfList(lastItem);
            }
        });
    }

    @Override
    protected void handleDataChanged() {
        super.handleDataChanged();
        hasWarned = false;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        hasWarned = false;
    }

    public void setOnEndOfListListener(OnEndOfListListener onEndOfListListener) {
        this.onEndOfListListener = onEndOfListListener;
    }

    public static interface OnEndOfListListener<T> {
        void onEndOfList(T lastItem);
    }
}
