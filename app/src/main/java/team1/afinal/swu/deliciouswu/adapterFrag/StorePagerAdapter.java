package team1.afinal.swu.deliciouswu.adapterFrag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import team1.afinal.swu.deliciouswu.fragment.StoreFragment.StoreInfoFragment;
import team1.afinal.swu.deliciouswu.fragment.StoreFragment.StoreMenuFragment;

public class StorePagerAdapter extends FragmentStatePagerAdapter {

    //탭의 갯수를 저장하고 있는 멤버변수
    private int mNumOfTab;
    private int mStoreIndex;

    //생성자
    public StorePagerAdapter(FragmentManager fm, int numOfTab, int storeIndx) {
        super(fm);
        mNumOfTab = numOfTab;
        mStoreIndex = storeIndx;
    }

    @Override
    public Fragment getItem(int position) {
        //BaseAdapter에서 getView()메서드에 해당되는 메서드로써,
        //position 값이 곧! 현재 선택된 Tab의 Index번호를 나타낸다.

        switch (position) {
            case 0:
                StoreInfoFragment tab1 = new StoreInfoFragment();
                tab1.setStoreIndex(mStoreIndex);
                return tab1;
            case 1:
                StoreMenuFragment tab2 = new StoreMenuFragment();
                tab2.setStoreIndex(mStoreIndex);
                return tab2;
        }

        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTab;
    }
}
