package template.cheng.hollis.template.tabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();
    private FragmentManager manager;

    public TabsAdapter(FragmentManager manager) {
        super(manager);
        this.manager = manager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void setmFragmentTitleList(List<String> mFragmentTitleList) {
        this.mFragmentTitleList = mFragmentTitleList;
    }

    public void setmFragmentList(List<Fragment> mFragmentList) {
        this.mFragmentList = mFragmentList;
    }

    public void setClearManager() {
        for (int i = 0; i < mFragmentList.size(); i++) {
            manager.beginTransaction().remove(mFragmentList.get(i)).commit();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


}