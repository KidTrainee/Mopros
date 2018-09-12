package jp.co.ienter.mopros.utils;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.fragments.base.BaseFragment;

public class ScreenTransition {

    private static final String TAG = ScreenTransition.class.getSimpleName();
    private FragmentManager mFragmentManager;

    public static final String DELIVER_REPORT_BACK_STACK = "back_stack_deliver_report";

    public ScreenTransition(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public void replaceFragment(@IdRes int containerId, BaseFragment fragment) {
        mFragmentManager
                .beginTransaction()
                .replace(containerId, fragment, fragment.getTAG())
                .addToBackStack(TAG)
                .commit();

        logFragments();
    }

    private void logFragments() {
        StringBuilder sb = new StringBuilder();
        for (Fragment fragment : mFragmentManager.getFragments()) {
            sb.append(fragment.getClass().getSimpleName()).append(",\t");
        }
        Log.d(TAG, "logFragments: " + sb.toString());
    }

    public void replaceChildFragment(@IdRes int container_id, Fragment childFragment, Fragment parentFragment) {
//        mFragmentManager.beginTransaction()
//                .hide(mFragmentManager.getFragments().get(0))
//                .add(container_id, childFragment, childFragment.getTAG())
//                .commit();
        Log.d(TAG, "replaceChildFragment: " + childFragment.getClass().getSimpleName()
                + "\t" + parentFragment.getClass().getSimpleName());

        mFragmentManager
                .beginTransaction()
                .hide(parentFragment)
                .add(container_id, childFragment)
                .commit();
        logFragments();
    }

    private void logFirstFragment() {
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        Log.d(TAG, "logFirstFragment: " + fragmentList.get(0)
                + "\t" +fragmentList.get(fragmentList.size()-1));
    }


    public void replacePreviousFragment() {
        List<Fragment> fragmentList = mFragmentManager.getFragments();
//        Log.d(TAG, "replacePreviousFragment: " + fragmentList);
        logFragments();
        mFragmentManager.beginTransaction()
                .remove(fragmentList.get(fragmentList.size()-1))
                .show(fragmentList.get(fragmentList.size() - 2))
                .commit();
        logFirstFragment();
    }
//
//    public void replaceFragment(@IdRes int container_id, Class<BaseFragment> fragmentClass) {
//        BaseFragment fragment = (BaseFragment) mFragmentManager.findFragmentByTag(fragmentClass.getSimpleName());
//        if (fragment == null) {
//            try {
//                fragment = fragmentClass.newInstance();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//        }
//
//        replaceFragment(container_id, fragment);
//    }

    public void replaceFragment(BaseFragment fragment) {
        replaceFragment(R.id.top_activity_frg_container, fragment);
    }

    public void removeFragment(BaseFragment fragment) {
        Log.d(TAG, "removeFragment: " + mFragmentManager.getFragments());
        mFragmentManager.beginTransaction().remove(fragment).commit();
    }
}
