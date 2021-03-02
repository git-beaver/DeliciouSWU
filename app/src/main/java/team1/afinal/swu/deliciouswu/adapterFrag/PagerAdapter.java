package team1.afinal.swu.deliciouswu.adapterFrag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import team1.afinal.swu.deliciouswu.fragment.MainTabFragment.MainFragment;
import team1.afinal.swu.deliciouswu.fragment.MainTabFragment.MenuFragment;
import team1.afinal.swu.deliciouswu.fragment.MainTabFragment.CalculateFragment;
import team1.afinal.swu.deliciouswu.fragment.MainTabFragment.ReviewFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    //탭의 갯수를 저장하고 있는 멤버변수
    private int mNumOfTab;

    //생성자
    public PagerAdapter(FragmentManager fm, int numOfTab) {
        super(fm);
        mNumOfTab = numOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        //BaseAdapter에서 getView()메서드에 해당되는 메서드로써,
        //position 값이 곧! 현재 선택된 Tab의 Index번호를 나타낸다.

        switch (position) {
            case 0:
                MainFragment tab1 = new MainFragment();
                return tab1;
            case 1:
                MenuFragment tab2 = new MenuFragment();
                return tab2;
            case 2:
                CalculateFragment tab3 = new CalculateFragment();
                return tab3;
            case 3:
                ReviewFragment tab4 = new ReviewFragment();
                return tab4;
        }

        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTab;
    }
}
