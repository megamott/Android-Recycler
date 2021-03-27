package com.megamott.android_recycler.adapter;

import android.os.Bundle;

@FunctionalInterface
public interface ItemClickListener {
    void onItemClick(Bundle args, int position);
}
