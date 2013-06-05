package com.joanzapata.android.sample.component;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.joanzapata.android.sample.TweeterActivity;

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
                if (!hasWarned
                        && view.getLastVisiblePosition() == totalItemCount - 1
                        && onEndOfListListener != null) {
                    hasWarned = true;
                    onEndOfListListener.onEndOfList(view.getItemAtPosition(totalItemCount - 1));
                }
            }
        });
    }

    @Override
    protected void handleDataChanged() {
        super.handleDataChanged();
        Log.i(TweeterActivity.TAG, "Dataset has changed");
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
