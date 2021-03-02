package team1.afinal.swu.deliciouswu;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import team1.afinal.swu.deliciouswu.adapterFrag.StorePagerAdapter;

public class MenuMainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private int storeIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        mTabLayout = findViewById(R.id.tabLayout);
        mPager = findViewById(R.id.pager);

        Intent intent = getIntent();
        storeIdx = intent.getExtras().getInt("storeKey");

        //탭을 추가한다.(동적으로)
        mTabLayout.addTab( mTabLayout.newTab().setText("가게 정보") );
        mTabLayout.addTab( mTabLayout.newTab().setText("메뉴") );


        //탭의 가로 전체 사이즈 지정
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //ViewPager는 Adapter를 통해서 Page(=Fragment)를 관리한다.
        StorePagerAdapter adapter = new StorePagerAdapter(getSupportFragmentManager(),
                mTabLayout.getTabCount(),storeIdx);
        mPager.setAdapter(adapter);

        //TabLayout 과 ViewPager를 서로 연결 시킨다
        //ViewPager가 움직였을 때, 탭이 바뀌게끔 한다.
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //TabLayout이 움직일때 ViewPager가 움직이도록 연결시킨다.
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //현재 사용자가 클릭한 탭의 이벤트가 실행된다.
                mPager.setCurrentItem( tab.getPosition() );
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
