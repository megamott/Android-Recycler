package com.megamott.android_recycler.view.adapter;

import android.os.Bundle;

@FunctionalInterface
public interface ItemClickListener {
    void onItemClick(Bundle args, int position);
}
