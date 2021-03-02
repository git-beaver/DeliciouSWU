package team1.afinal.swu.deliciouswu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuLotateActivity extends AppCompatActivity {

    private TextView txtMon, txtThu,txtWed,txtThur,txtFri,txtSat,txtSun;
    private TextView txtBreakfast, txtLunch1, txtLunch2, txtDinner;
    private ImageView imgPre,imgNext;
    private int mImgIdx = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_lotate);

        txtMon = findViewById(R.id.txtMon);
        txtThu = findViewById(R.id.txtThu);
        txtWed = findViewById(R.id.txtWed);
        txtThur = findViewById(R.id.txtThur);
        txtFri = findViewById(R.id.txtFri);
        txtSat = findViewById(R.id.txtSat);
        txtSun = findViewById(R.id.txtSun);

        imgPre = findViewById(R.id.imgPre);
        imgNext = findViewById(R.id.imgNext);

        txtBreakfast = findViewById(R.id.txtBreakfast);
        txtLunch1 = findViewById(R.id.txtLunch1);
        txtLunch2 = findViewById(R.id.txtLunch2);
        txtDinner = findViewById(R.id.txtDinner);

       /* txtBreakfast.setText("월요일 아침");
        txtLunch1.setText("월요일 점심 한식");
        txtLunch2.setText("월요일 점심 일품");
        txtDinner.setText("월요일 저녁");*/
        txtBreakfast.setText("쌀밥 / 콩가루배춧국 / 햄계란전 / 잔멸치볶음 / 고춧잎무침 / 배추김치");
        txtLunch1.setText("쌀밥 / 짬뽕순두부 / 채소잡채 / 구운파래김*양념장 / 고들빼기무침 / 배추김치");
        txtLunch2.setText("치킨마요덮밥 / 유부장국 / 양배추콘샐러드 / 단무지");
        txtDinner.setText("돼지고기채새싹비빔밥 / 미소장국 / 핫도그*케찹 / 배추김치 / 쥬시쿨");

    }  //end onCreate()

    @Override
    protected void onResume() {
        super.onResume();

        txtMon.setVisibility(View.VISIBLE);
        txtThu.setVisibility(View.GONE);
        txtWed.setVisibility(View.GONE);
        txtThur.setVisibility(View.GONE);
        txtFri.setVisibility(View.GONE);
        txtSat.setVisibility(View.GONE);
        txtSun.setVisibility(View.GONE);

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImgIdx++;
                dispWeek();
            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImgIdx--;
                dispWeek();
            }
        });

    } //end onReasume;


    private void dispWeek() {
        switch (mImgIdx){
            case 1:
                txtMon.setVisibility(View.VISIBLE);
                txtThu.setVisibility(View.INVISIBLE);
                txtWed.setVisibility(View.INVISIBLE);
                txtThur.setVisibility(View.INVISIBLE);
                txtFri.setVisibility(View.INVISIBLE);
                txtSat.setVisibility(View.INVISIBLE);
                txtSun.setVisibility(View.INVISIBLE);
                txtBreakfast.setText("쌀밥 / 콩가루배춧국 / 햄계란전 / 잔멸치볶음 / 고춧잎무침 / 배추김치");
                txtLunch1.setText("쌀밥 / 짬뽕순두부 / 채소잡채 / 구운파래김*양념장 / 고들빼기무침 / 배추김치");
                txtLunch2.setText("치킨마요덮밥 / 유부장국 / 양배추콘샐러드 / 단무지");
                txtDinner.setText("돼지고기채새싹비빔밥 / 미소장국 / 핫도그*케찹 / 배추김치 / 쥬시쿨");
                break;
            case 2:
                txtMon.setVisibility(View.INVISIBLE);
                txtThu.setVisibility(View.VISIBLE);
                txtWed.setVisibility(View.INVISIBLE);
                txtThur.setVisibility(View.INVISIBLE);
                txtFri.setVisibility(View.INVISIBLE);
                txtSat.setVisibility(View.INVISIBLE);
                txtSun.setVisibility(View.INVISIBLE);
                txtBreakfast.setText("잡곡밥 / 시락국 / 숯불바베큐 / 단호박조림 / 콩나물무침 / 배추김치");
                txtLunch1.setText("쌀밥 / 햄모둠찌개 / 계란찜 / 미역초무침 / 고추지무침 / 깍두기");
                txtLunch2.setText("로제파스타 / 채소스프 / 바게트빵*딸기쩀 / 모둠샐러드*드레싱 / 오이피클");
                txtDinner.setText("취나물밥*양념장 / 건새우근대된장국 / 갈비만두찜 / 연근조림 / 허브생채 / 배추김치");
                break;
            case 3:
                txtMon.setVisibility(View.INVISIBLE);
                txtThu.setVisibility(View.INVISIBLE);
                txtWed.setVisibility(View.VISIBLE);
                txtThur.setVisibility(View.INVISIBLE);
                txtFri.setVisibility(View.INVISIBLE);
                txtSat.setVisibility(View.INVISIBLE);
                txtSun.setVisibility(View.INVISIBLE);
                txtBreakfast.setText("잡곡밥 / 감잣국 / 참치김치볶음 / 연두부찜 / 돌자반볶음 / 깍두기");
                txtLunch1.setText("쌀밥 / 청량고추된장찌개 / 두부완자 / 게맛살채소볶음 / 해초샐러드 / 배추김치");
                txtLunch2.setText("왕새우튀김오무라이스 / 팽이버섯맑은국 / 모둠샐러드*드레싱 / 배추김치");
                txtDinner.setText("쌀밥 / 호박새우젓국 / 유자향돼지불고기 / 모둠채소겉절이 / 콩자반 / 배추김치");
                break;
            case 4:
                txtMon.setVisibility(View.INVISIBLE);
                txtThu.setVisibility(View.INVISIBLE);
                txtWed.setVisibility(View.INVISIBLE);
                txtThur.setVisibility(View.VISIBLE);
                txtFri.setVisibility(View.INVISIBLE);
                txtSat.setVisibility(View.INVISIBLE);
                txtSun.setVisibility(View.INVISIBLE);
                txtBreakfast.setText("잡곡밥 / 매운버섯찌개 / 청파레오징어가스 / 도시락김 / 숙주나물 / 배추김치");
                txtLunch1.setText("쌀밥 / 시금치된장국 / 쌈무 / 부추생채 / 배추김치");
                txtLunch2.setText("등심돈까스*소스 / 쌀밥 / 크림스프 / 반달감자튀김*케찹 / 오이피클 / 배추김치");
                txtDinner.setText("닭갈비볶음밥&계란후라이 / 우동국 / 매콤고로케 / 양상추샐러드*드레싱 / 배추김치");
                break;
            case 5:
                txtMon.setVisibility(View.INVISIBLE);
                txtThu.setVisibility(View.INVISIBLE);
                txtWed.setVisibility(View.INVISIBLE);
                txtThur.setVisibility(View.INVISIBLE);
                txtFri.setVisibility(View.VISIBLE);
                txtSat.setVisibility(View.INVISIBLE);
                txtSun.setVisibility(View.INVISIBLE);
                txtBreakfast.setText("잡곡밥 / 쇠고기뭇국 / 오징어완자*케찹 / 쑥갓두부무침 / 무말랭이무침 / 배추김치");
                txtLunch1.setText("쌀밥 / 얼큰수제비국 / 치킨너겟 / 사각어묵볶음 / 마늘종지무침 / 배추김치");
                txtLunch2.setText("돼지고기쫄면 / 다시마뭇국 / 만두튀김 / 단무지 / 후리가케양념밥");
                txtDinner.setText("쌀밥 / 돼지고기고추장찌개 / 알찬소세지전*케찹 / 청포묵&무순양념장 / 치커리무침 / 배추김치");
                break;
            case 6:
                txtMon.setVisibility(View.INVISIBLE);
                txtThu.setVisibility(View.INVISIBLE);
                txtWed.setVisibility(View.INVISIBLE);
                txtThur.setVisibility(View.INVISIBLE);
                txtFri.setVisibility(View.INVISIBLE);
                txtSat.setVisibility(View.VISIBLE);
                txtSun.setVisibility(View.INVISIBLE);
                txtBreakfast.setText("잡곡밥 / 오징어찌개 / 미트볼케첩조림 / 고구마순들깨나물 / 간장깻잎지 / 배추김치");
                txtLunch1.setText("쌀밥 / 쇠고기미역국 / 사각어묵볶음 / 잡채어묵조림 / 매운호박볶음 / 열무생채 / 배추김치");
                txtLunch2.setText("");
                txtDinner.setText("쌀밥 / 우거지해장국 / 후랑크소시지볶음 / 양파초절임 / 오이생채 / 깍두기");
                break;
            case 7:
                txtMon.setVisibility(View.INVISIBLE);
                txtThu.setVisibility(View.INVISIBLE);
                txtWed.setVisibility(View.INVISIBLE);
                txtThur.setVisibility(View.INVISIBLE);
                txtFri.setVisibility(View.INVISIBLE);
                txtSat.setVisibility(View.INVISIBLE);
                txtSun.setVisibility(View.VISIBLE);
                txtBreakfast.setText("잡곡밥 / 장떡국 / 너비아니계란전 / 배추된장나물 / 참나물무침 / 깍두기");
                txtLunch1.setText("쌀밥 / 근대된장국 / 고등어구이*와사비장 / 마카로니콘샐러드 / 쑥갓사과무침 / 배추김치");
                txtLunch2.setText("");
                txtDinner.setText("쌀밥 / 전주콩나물국밥 / 오미산적 / 잔멸치볶음 / 다시마튀각 / 배추김치");
                break;
        }
    }

}