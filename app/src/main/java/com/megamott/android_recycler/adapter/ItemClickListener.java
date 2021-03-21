package com.megamott.android_recycler.adapter;

import android.view.View;

@FunctionalInterface
public interface ItemClickListener {
    void onItemClick(View view, int position);
}
