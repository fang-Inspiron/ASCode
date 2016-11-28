package com.bishedemo.ui;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by fang on 2016/11/15.
 */

public class SimpleFantasyListener implements FantasyListener {
    @Override
    public boolean onHover(@Nullable View view) {
        return false;
    }

    @Override
    public boolean onSelect(View view) {
        return false;
    }

    @Override
    public void onCancel() {

    }
}
