package team1.afinal.swu.deliciouswu;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import team1.afinal.swu.deliciouswu.adapterFrag.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabLayout);
        mPager = findViewById(R.id.pager);
        imgLogo = findViewById(R.id.imgLogo);

        //탭을 추가한다.(동적으로)
        mTabLayout.addTab( mTabLayout.newTab().setText("홈") );
        mTabLayout.addTab( mTabLayout.newTab().setText("메뉴") );
        mTabLayout.addTab( mTabLayout.newTab().setText("장바구니") );
        mTabLayout.addTab( mTabLayout.newTab().setText("후기") );

        //탭 아이콘 추가
        mTabLayout.getTabAt(0).setIcon(R.drawable.homeimg2); //최대 48x48 pixel
        mTabLayout.getTabAt(1).setIcon(R.drawable.menuimg2);
        mTabLayout.getTabAt(2).setIcon(R.drawable.cartimg2);
        mTabLayout.getTabAt(3).setIcon(R.drawable.reviewimg3);

        //탭의 가로 전체 사이즈 지정
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        //ViewPager는 Adapter를 통해서 Page(=Fragment)를 관리한다.
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),
                mTabLayout.getTabCount());
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
            @Overridee
            public void onTabReslected(TabLayout.Tab tab) {

            }
        });

    }

    public void goTabIndex(int index) {
        mPager.setCurrentItem( index );
    }

    @Override
    public void onBackPressed() {

        if(mPager.getCurrentItem() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("앱을 종료 하시겠습니까?");
            builder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("아니오", null);
            builder.create().show();
        } else {
            mPager.setCurrentItem(0);
            return;
        }
    }
}
