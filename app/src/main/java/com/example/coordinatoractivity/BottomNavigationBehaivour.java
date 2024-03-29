package com.example.coordinatoractivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class BottomNavigationBehaivour extends CoordinatorLayout.Behavior {
    public BottomNavigationBehaivour() {
    }

    public BottomNavigationBehaivour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//ctrl+o

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        child.setTranslationY(Math.max(0f,
                Math.min(Float.parseFloat(String.valueOf(child.getHeight())),child.getTranslationY()+dyConsumed)));
    }



    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            updateSnackBar(child, dependency);
        }
        return super.layoutDependsOn(parent, child, dependency);
    }


    private void updateSnackBar(View child, View dependency) {
        if (dependency.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) dependency.getLayoutParams();
            params.setAnchorId(child.getId());
            params.anchorGravity = Gravity.TOP;
            params.gravity = Gravity.TOP;
            dependency.setLayoutParams(params);
        }
    }
}
