package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun FragmentManager.setupForAccessibility() {
    addOnBackStackChangedListener {
        val lastFragmentWithView = fragments.lastOrNull { it.view != null }
        for (fragment in fragments) {
            if (fragment == lastFragmentWithView) {
                fragment.view?.importantForAccessibility =
                    View.IMPORTANT_FOR_ACCESSIBILITY_YES
            } else {
                fragment.view?.importantForAccessibility =
                    View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
            }
        }
    }
}


