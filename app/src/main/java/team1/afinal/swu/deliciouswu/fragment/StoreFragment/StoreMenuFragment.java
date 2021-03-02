package team1.afinal.swu.deliciouswu.fragment.StoreFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.adapter.StoreAdapter;
import team1.afinal.swu.deliciouswu.bean.MenuBean;

public class StoreMenuFragment extends Fragment {

    private ListView lstMain;
    private int mStoreIndex;
    private FrameLayout MenuChk8;
    private CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8;
    private Button btnOK;
    private int chkNum;

    public void setStoreIndex(int storeIndex) {
        mStoreIndex = storeIndex;
    }

    public List<MenuBean> menuList = new ArrayList<>();

    //어댑터생성
    //StoreAdapter storeAdapter = new StoreAdapter(getActivity(), chkMenuList);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_menu, null);
        btnOK = view.findViewById(R.id.btnOK);
        chk1 = view.findViewById(R.id.chk1);
        chk2 = view.findViewById(R.id.chk2);
        chk3 = view.findViewById(R.id.chk3);
        chk4 = view.findViewById(R.id.chk4);
        chk5 = view.findViewById(R.id.chk5);
        chk6 = view.findViewById(R.id.chk6);
        chk7 = view.findViewById(R.id.chk7);
        chk8 = view.findViewById(R.id.chk8);
        MenuChk8 = view.findViewById(R.id.MenuChk8);
        MenuChk8.setVisibility(View.GONE);

        lstMain = view.findViewById(R.id.lstMain);

        switch (mStoreIndex) {
            case 1: //스타벅스

                break;
            case 2: //가은

                break;
            case 3: //팬도로시
                MenuBean p1 = new MenuBean();
                p1.setImgTitle(R.drawable.p_blackbubbletea);
                p1.setTitle("블랙 버블티");
                p1.setDesc("블랙티에 버블 추가요~");
                p1.setPrice("3500");

                MenuBean p2 = new MenuBean();
                p2.setImgTitle(R.drawable.p_blueberryyoghurtfrappe);
                p2.setTitle("블루베리 요거트 프라페");
                p2.setDesc("블루베리 요거트 냠냠");
                p2.setPrice("3400");

                menuList.add(p1);
                menuList.add(p2);

                break;

            case 4: //비틀주스

                break;

            case 5: //감탄

                break;

            case 6: //퀴즈노스
                MenuChk8.setVisibility(View.VISIBLE);
                chkNum = 8;

                MenuBean q1 = new MenuBean();
                q1.setImgTitle(R.drawable.q_zestygrilledsteak);
                q1.setTitle("제스티 그릴드 스테이크");
                q1.getTastList().add(2);
                q1.getTastList().add(3);
                q1.setDesc("제스티 비프 바베큐, 체다 치즈, 모짜렐라 치즈, 양송이, 볶은 양파, 허니 버번 머스터드 드레싱, 제스티 그릴 드레싱");
                q1.setPrice("7700");

                MenuBean q2 = new MenuBean();
                q2.setImgTitle(R.drawable.q_doublecheesesteak);
                q2.setTitle("더블 치즈 스테이크");
                q2.getTastList().add(4);
                q2.getTastList().add(6);
                q2.setDesc("프라임 비프, 더블 스위스 치즈, 볶은 양파, 마요 드레싱");
                q2.setPrice("7700");

                MenuBean q3 = new MenuBean();
                q3.setImgTitle(R.drawable.q_lostbeafand);
                q3.setTitle("로스트 비프 & 구아카몰");
                q3.getTastList().add(4);
                q3.getTastList().add(5);
                q3.getTastList().add(6);
                q3.setDesc("로스트비프, 체다치즈, 토마토, 양파, 양상추, 구아카몰&마요네즈 드레싱 ");
                q3.setPrice("7700");

                MenuBean q4 = new MenuBean();
                q4.setImgTitle(R.drawable.q_hamandcheese);
                q4.setTitle("햄&치즈");
                q4.getTastList().add(3);
                q4.getTastList().add(4);
                q4.getTastList().add(5);
                q4.setDesc("벌꿀로 숙성한 햄, 모짜렐라 치즈, 양파, 토마토, 양상추 랜치드레싱");
                q4.setPrice("4500");

                MenuBean q5 = new MenuBean();
                q5.setImgTitle(R.drawable.q_spicyshirimp);
                q5.setTitle("스파이시 쉬림프");
                q5.getTastList().add(6);
                q5.setDesc("새우, 크라비아, 양파, 피망, 치폴레 마요 드레싱");
                q5.setPrice("5600");

                MenuBean q6 = new MenuBean();
                q6.setImgTitle(R.drawable.q_chickencarbonara);
                q6.setTitle("치킨 까르보나라");
                q6.getTastList().add(1);
                q6.getTastList().add(4);
                q6.getTastList().add(5);
                q6.setDesc("부드러운 치킨 가슴살, 베이컨, 모짜렐라 치즈, 양송이, 크리미 알프렐도 드레싱 & 스파이시즈");
                q6.setPrice("6200");

                MenuBean q7 = new MenuBean();
                q7.setImgTitle(R.drawable.q_chickenbaconlench);
                q7.setTitle("치킨 베이컨 렌치");
                q7.getTastList().add(4);
                q7.getTastList().add(5);
                q7.setDesc("부드러운 치킨 가슴살, 베이컨, 체다 치즈, 양파, 토마토, 양상추, 랜치 드레싱 ");
                q7.setPrice("6200");

                MenuBean q8 = new MenuBean();
                q8.setImgTitle(R.drawable.q_honeymustardchecken);
                q8.setTitle("허니 머스타드 치킨");
                q8.getTastList().add(2);
                q8.getTastList().add(4);
                q8.getTastList().add(5);
                q8.setDesc("부드러운 치킨 가슴살, 스위스 치즈, 양파, 토마토, 양상추, 허니머스타드 드레싱");
                q8.setPrice("6200");

                MenuBean q9 = new MenuBean();
                q9.setImgTitle(R.drawable.q_crazyhotchicken);
                q9.setTitle("크레이지 핫 치킨");
                q9.getTastList().add(1);
                q9.getTastList().add(4);
                q9.setDesc("매운소스 치킨살, 스위스 치즈, 양송이, 양파, 피망, 청양페퍼소스, 크리미 베이컨 알프레도 드레싱");
                q9.setPrice("6200");

                MenuBean q10 = new MenuBean();
                q10.setImgTitle(R.drawable.q_chifoletuna);
                q10.setTitle("치폴레스테이크");
                q10.setDesc("");
                q10.setPrice("7700");

                MenuBean q11 = new MenuBean();
                q11.setImgTitle(R.drawable.q_seafoodclassic);
                q11.setTitle("씨푸드 클래식");
                q11.getTastList().add(4);
                q11.getTastList().add(5);
                q11.getTastList().add(6);
                q11.setDesc("씨푸드믹스, 체다치즈, 양파, 양상추, 레드와인 & 마요네즈 드레싱");
                q11.setPrice("4500");

                MenuBean q12 = new MenuBean();
                q12.setImgTitle(R.drawable.q_chifoletuna);
                q12.setTitle("치폴레 튜나");
                q12.getTastList().add(4);
                q12.getTastList().add(5);
                q12.getTastList().add(6);
                q12.setDesc("튜나 믹스, 체다 치즈, 양파, 피클, 양상추, 치폴레 마요 드레싱");
                q12.setPrice("4500");

                MenuBean q13 = new MenuBean();
                q13.setImgTitle(R.drawable.q_turkeyandguakamall);
                q13.setTitle("터키&구아카몰");
                q13.getTastList().add(5);
                q13.getTastList().add(6);
                q13.setDesc("터키가슴살, 토마토, 양파, 양상추, 구아카몰, 랜치 드레싱");
                q13.setPrice("6200");

                MenuBean q14 = new MenuBean();
                q14.setImgTitle(R.drawable.q_turkeylenchswish);
                q14.setTitle("터키 렌치 스위스");
                q14.getTastList().add(5);
                q14.getTastList().add(6);
                q14.setDesc("터키 가슴살, 스위스 치즈, 양파, 토마토, 양상추, 랜치 드레싱");
                q14.setPrice("6200");

                MenuBean q15 = new MenuBean();
                q15.setImgTitle(R.drawable.q_traditional);
                q15.setTitle("트레디셔널");
                q15.getTastList().add(2);
                q15.getTastList().add(3);
                q15.getTastList().add(4);
                q15.getTastList().add(5);
                q15.getTastList().add(6);
                q15.setDesc("로스트비프, 터키 가슴살, 벌꿀로 숙성한 햄, 체다 치즈, 블랙올리브, 양파, 토마토, 양상추, 랜치 드레싱");
                q15.setPrice("5600");

                MenuBean q16 = new MenuBean();
                q16.setImgTitle(R.drawable.q_italian);
                q16.setTitle("이탈리안");
                q16.getTastList().add(2);
                q16.getTastList().add(3);
                q16.getTastList().add(4);
                q16.getTastList().add(5);
                q16.setDesc("살라미, 페파로니, 벌꿀로 숙성한 햄, 모짜렐라 치즈, 블랙올리브, 양파, 토마토, 양상추, 레드와인 드레싱");
                q16.setPrice("5600");

                MenuBean q17 = new MenuBean();
                q17.setImgTitle(R.drawable.q_doublebaconblt);
                q17.setTitle("더블 베이컨 비엘티");
                q17.getTastList().add(3);
                q17.getTastList().add(5);
                q17.getTastList().add(6);
                q17.setDesc("베이컨, 양파, 토마토, 양상추, 마요네즈 & 스파이시즈");
                q17.setPrice("5600");

                MenuBean q18 = new MenuBean();
                q18.setImgTitle(R.drawable.q_honeylicortarcheese);
                q18.setTitle("허니 리코타 치즈&베지");
                q18.getTastList().add(3);
                q18.getTastList().add(4);
                q18.getTastList().add(5);
                q18.setDesc("허니리코타 치즈, 체다 치즈, 블랙올리브, 양파, 피망, 양송이, 토마토, 양상추, 레드와인 드레싱");
                q18.setPrice("5600");

                MenuBean q19 = new MenuBean();
                q19.setTitle("오리지널 피자");
                q19.getTastList().add(1);
                q19.getTastList().add(3);
                q19.getTastList().add(4);
                q19.getTastList().add(6);
                q19.setDesc("제스티 비프 바베큐, 페파로니, 피자 치즈, 피망, 양송이, 양파, 올리브, 크리미 베이컨 알프레도 드레싱 & 스파이시즈");
                q19.setPrice("7900");

                MenuBean q20 = new MenuBean();
                q20.setTitle("크레이지 핫 치킨 피자");
                q20.getTastList().add(1);
                q20.getTastList().add(4);
                q20.setDesc("매운소스 치킨살, 피자 치즈, 피망, 양송이, 양파, 크리미 베이컨 알프레도 드레싱 & 스파이시즈");
                q20.setPrice("7900");

                MenuBean q21 = new MenuBean();
                q21.setTitle("스파이시 쉬림프 피자");
                q21.getTastList().add(1);
                q21.setDesc("");
                q21.setPrice("7900");

                MenuBean q22 = new MenuBean();
                q22.setTitle("씨푸드 샐러드");
                q22.getTastList().add(5);
                q22.setDesc("게맛살, 토마토, 블랙 올리브, 신선한 야채믹스, 퀴즈노스 브레드");
                q22.setPrice("6200");

                MenuBean q23 = new MenuBean();
                q23.setTitle("이탈리안 샐러드");
                q23.getTastList().add(1);
                q23.getTastList().add(2);
                q23.getTastList().add(5);
                q23.setDesc("매콤한 페퍼로니, 와인 숙성한 살라미, 허니 숙성 햄, 모짜렐라 치즈, 양파, 토마토, 블랙 올리브, 신선한 야채믹스, 퀴즈노스 브레드");
                q23.setPrice("6200");

                MenuBean q24 = new MenuBean();
                q24.setTitle("허니 머스타드 치킨 샐러드");
                q24.getTastList().add(2);
                q24.getTastList().add(5);
                q24.setDesc("허브 닭 가슴살, 베이컨, 체다 치즈, 양파, 토마토, 블랙 올리브, 신선한 야채믹스, 퀴즈노스 브레드");
                q24.setPrice("6200");

                MenuBean q25 = new MenuBean();
                q25.setTitle("허니 리코타 치즈 샐러드");
                q25.getTastList().add(2);
                q25.getTastList().add(5);
                q25.setDesc("허니 리코타 치즈, 크랜베리, 양파, 토마토, 블랙 올리브, 신선한 야채믹스, 퀴즈노스 브레드");
                q25.setPrice("7200");

                MenuBean q26 = new MenuBean();
                q26.setTitle("브로콜리 치즈 스프");
                q26.getTastList().add(4);
                q26.getTastList().add(8);
                q26.setDesc("");
                q26.setPrice("3500");

                MenuBean q27 = new MenuBean();
                q27.setTitle("클램차우더 스프");
                q27.getTastList().add(8);
                q27.setDesc("");
                q27.setPrice("3500");

                MenuBean q28 = new MenuBean();
                q28.setTitle("웨지 포테이토");
                q28.getTastList().add(3);
                q28.setDesc("");
                q28.setPrice("3000");

                MenuBean q29 = new MenuBean();
                q29.setTitle("컨츄리 프렌치 치킨 브레드 볼");
                q29.getTastList().add(8);
                q29.setDesc("");
                q29.setPrice("6100");

                MenuBean q30 = new MenuBean();
                q30.setTitle("보스톤 클램 차우더 브레드 볼");
                q30.getTastList().add(8);
                q30.setDesc("");
                q30.setPrice("6100");

                MenuBean q31 = new MenuBean();
                q31.setTitle("칩 ");
                q31.getTastList().add(3);
                q31.getTastList().add(7);
                q31.setDesc("프리토스칩");
                q31.setPrice("1000");

                MenuBean q32 = new MenuBean();
                q32.setTitle("초코 쿠키");
                q32.getTastList().add(2);
                q32.setDesc("초콜렛이 숑숑 박혀있는 맛있는 수제쿠키");
                q32.setPrice("1800");

                MenuBean q33 = new MenuBean();
                q33.setTitle("호두 쿠키");
                q33.getTastList().add(4);
                q33.setDesc("호두가 콕콕 박혀있는 맛있는 수제쿠키");
                q33.setPrice("1800");

                MenuBean q34 = new MenuBean();
                q34.setTitle("에스프레소");
                q34.setDesc("");
                q34.setPrice("2500");

                MenuBean q35 = new MenuBean();
                q35.setTitle("아메리카노");
                q35.setDesc("");
                q35.setPrice("2000");

                MenuBean q36 = new MenuBean();
                q36.setTitle("카페라떼");
                q36.setDesc("");
                q36.setPrice("2000");

                MenuBean q37 = new MenuBean();
                q37.setTitle("카푸치노");
                q37.setDesc("");
                q37.setPrice("2500");

                MenuBean q38 = new MenuBean();
                q38.setTitle("카페모카");
                q38.setDesc("");
                q38.setPrice("3500");

                MenuBean q39 = new MenuBean();
                q39.setTitle("바닐라라떼");
                q39.setDesc("");
                q39.setPrice("3500");

                MenuBean q40 = new MenuBean();
                q40.setTitle("카라멜라떼");
                q40.setDesc("");
                q40.setPrice("3500");

                MenuBean q41 = new MenuBean();
                q41.setTitle("연유라떼");
                q41.setDesc("");
                q41.setPrice("3500");

                MenuBean q42 = new MenuBean();
                q42.setTitle("토피넛라떼");
                q42.setDesc("");
                q42.setPrice("3500");

                MenuBean q43 = new MenuBean();
                q43.setTitle("민트초코라떼");
                q43.setDesc("");
                q43.setPrice("3500");

                MenuBean q44 = new MenuBean();
                q44.setTitle("카라멜마끼아또");
                q44.setDesc("");
                q44.setPrice("3500");

                MenuBean q45 = new MenuBean();
                q45.setTitle("헤이즐넛라떼");
                q45.setDesc("");
                q45.setPrice("3500");

                MenuBean q46 = new MenuBean();
                q46.setTitle("녹차라떼");
                q46.setDesc("");
                q46.setPrice("3000");

                MenuBean q47 = new MenuBean();
                q47.setTitle("홍차라떼");
                q47.setDesc("");
                q47.setPrice("3000");

                MenuBean q48 = new MenuBean();
                q48.setTitle("핫/아이스 초코");
                q48.setDesc("");
                q48.setPrice("3000");

                MenuBean q49 = new MenuBean();
                q49.setTitle("TEA Time");
                q49.setDesc("얼그레이/카모마일/페퍼민트/쟈스민/마테/루이보스/과일홍차");
                q49.setPrice("2500");

                MenuBean q50 = new MenuBean();
                q50.setTitle("스파클링 에이드");
                q50.setDesc("오렌지/레몬/자몽/블루레몬/모히또/깔라만시/오미자/유자");
                q50.setPrice("3000");

                MenuBean q51 = new MenuBean();
                q51.setTitle("계절과일 주스");
                q51.setDesc("");
                q51.setPrice("3000");

                MenuBean q52 = new MenuBean();
                q52.setTitle("소다(탄산음료)");
                q52.setDesc("");
                q52.setPrice("1700");

                menuList.add(q1);
                menuList.add(q2);
                menuList.add(q3);
                menuList.add(q4);
                menuList.add(q5);
                menuList.add(q6);
                menuList.add(q7);
                menuList.add(q8);
                menuList.add(q9);
                menuList.add(q10);
                menuList.add(q11);
                menuList.add(q12);
                menuList.add(q13);
                menuList.add(q14);
                menuList.add(q15);
                menuList.add(q16);
                menuList.add(q17);
                menuList.add(q18);
                menuList.add(q19);
                menuList.add(q20);
                menuList.add(q21);
                menuList.add(q22);
                menuList.add(q23);
                menuList.add(q24);
                menuList.add(q25);
                menuList.add(q26);
                menuList.add(q27);
                menuList.add(q28);
                menuList.add(q29);
                menuList.add(q30);
                menuList.add(q31);
                menuList.add(q32);
                menuList.add(q33);
                menuList.add(q34);
                menuList.add(q35);
                menuList.add(q36);
                menuList.add(q37);
                menuList.add(q38);
                menuList.add(q39);
                menuList.add(q40);
                menuList.add(q41);
                menuList.add(q42);
                menuList.add(q43);
                menuList.add(q44);
                menuList.add(q45);
                menuList.add(q46);
                menuList.add(q47);
                menuList.add(q48);
                menuList.add(q49);
                menuList.add(q50);
                menuList.add(q51);
                menuList.add(q52);



                break;

            case 7: //츄밥

                break;

            case 8: //오니기리와 이규동
                MenuChk8.setVisibility(View.VISIBLE);
                chkNum = 8;
                MenuBean o1 = new MenuBean();
                o1.setImgTitle(R.drawable.o_gyudong);
                o1.getTastList().add(2);
                o1.getTastList().add(3);
                o1.getTastList().add(5);
                o1.setTitle("규동");
                o1.setDesc("특제 소스에 우삼겹을 살짝 데쳐 상큼한 파, 양파와 함께 먹는 소고기 샤브 덮밥");
                o1.setPrice("5900");

                MenuBean o2 = new MenuBean();
                o2.setImgTitle(R.drawable.o_icon2);
                o2.getTastList().add(2);
                o2.getTastList().add(3);
                o2.getTastList().add(5);
                o2.setTitle("계란규동");
                o2.setDesc("특제 소스와 계란이 함께한 베이직 규동");
                o2.setPrice("6400");

                MenuBean o3 = new MenuBean();
                o3.setImgTitle(R.drawable.o_bossamgyudong);
                o3.getTastList().add(2);
                o3.getTastList().add(3);
                o3.getTastList().add(5);
                o3.setTitle("보쌈愛규동");
                o3.setDesc("일본식 샤브 소불고기와 상큼하고 아삭한 보쌈무를 함께 즐길 수 있는 규동");
                o3.setPrice("6500");

                MenuBean o4 = new MenuBean();
                o4.setImgTitle(R.drawable.o_triplecheezegyudong);
                o4.getTastList().add(2);
                o4.getTastList().add(3);
                o4.getTastList().add(6);
                o4.setTitle("트리플치즈규동");
                o4.setDesc("일본식 샤브 소불고기와 3가지 치즈로 맛을 낸 규동");
                o4.setPrice("7400");

                MenuBean o5 = new MenuBean();
                o5.setImgTitle(R.drawable.o_gallicgyudong);
                o5.getTastList().add(2);
                o5.getTastList().add(3);
                o5.getTastList().add(4);
                o5.getTastList().add(7);
                o5.setTitle("갈릭규동");
                o5.setDesc("마늘후레이크가 더해져 고소한 풍미를 느낄 수 있는 규동");
                o5.setPrice("6900");

                MenuBean o6 = new MenuBean();
                o6.setImgTitle(R.drawable.o_spicygyudong);
                o6.getTastList().add(1);
                o6.getTastList().add(8);
                o6.setTitle("화끈규동");
                o6.setDesc("화끈한 국물맛이 일품인 국물 규동");
                o6.setPrice("6900");

                MenuBean o7 = new MenuBean();
                o7.setImgTitle(R.drawable.o_gyucurrydong);
                o7.getTastList().add(2);
                o7.getTastList().add(3);
                o7.setTitle("규카레동");
                o7.setDesc("매콤한 카레와 일본식 규동의 맛이 어우려져 새로운 맛으로 탄생");
                o7.setPrice("6900");

                MenuBean o8 = new MenuBean();
                o8.setImgTitle(R.drawable.o_chickenmayodong);
                o8.getTastList().add(3);
                o8.getTastList().add(4);
                o8.getTastList().add(7);
                o8.setTitle("치킨마요동");
                o8.setDesc("누구나 좋아하는 치킨 가라아게와 데리야끼소스의 조합");
                o8.setPrice("5900");

                MenuBean o9 = new MenuBean();
                o9.setImgTitle(R.drawable.o_spammayodong);
                o9.getTastList().add(2);
                o9.getTastList().add(3);
                o9.getTastList().add(4);
                o9.setTitle("스팸마요동");
                o9.setDesc("스팸과 짭쪼름하고 달콤한 데리야끼 소스와 마요네즈가 어울린 메뉴");
                o9.setPrice("5900");

                MenuBean o10 = new MenuBean();
                o10.setImgTitle(R.drawable.o_butamayodong);
                o10.getTastList().add(1);
                o10.getTastList().add(3);
                o10.getTastList().add(4);
                o10.setTitle("부타마요동");
                o10.setDesc("매콤한 돼지목살과 고소한 마요네즈가 최강 조합인 덮밥");
                o10.setPrice("6500");

                MenuBean o11 = new MenuBean();
                o11.setImgTitle(R.drawable.o_buldakmayodong);
                o11.getTastList().add(1);
                o11.getTastList().add(2);
                o11.getTastList().add(3);
                o11.getTastList().add(4);
                o11.getTastList().add(7);
                o11.setTitle("불닭마요동");
                o11.setDesc("바삭한 치킨과 매콤달콤 소스가 어우러진 매운치킨마요 덮밥");
                o11.setPrice("6500");

                MenuBean o12 = new MenuBean();
                o12.setImgTitle(R.drawable.o_spicybutadong);
                o12.getTastList().add(1);
                o12.setTitle("화끈부타동");
                o12.setDesc("기름기 쏙 뺀 목살도 만든 매운 돼지고기 덮밥");
                o12.setPrice("6500");

                MenuBean o13 = new MenuBean();
                o13.setImgTitle(R.drawable.o_porkcutlet);
                o13.getTastList().add(7);
                o13.setTitle("돈가스");
                o13.setDesc("돼지고기 순살등심으로 만든 바삭바삭한 돈가스");
                o13.setPrice("6500");

                MenuBean o14 = new MenuBean();
                o14.setImgTitle(R.drawable.o_cheezeporkcutlet);
                o14.getTastList().add(6);
                o14.getTastList().add(7);
                o14.setTitle("치즈돈가스");
                o14.setDesc("바삭바삭 돈가스 안에 치즈가 듬뿍 들어간 돈가스");
                o14.setPrice("7500");

                MenuBean o15 = new MenuBean();
                o15.setImgTitle(R.drawable.o_curryporkcutlet);
                o15.getTastList().add(3);
                o15.getTastList().add(4);
                o15.getTastList().add(7);
                o15.setTitle("카레돈가스");
                o15.setDesc("고소한 카레와 바삭바삭한 돈가스");
                o15.setPrice("7500");

                MenuBean o16 = new MenuBean();
                o16.setImgTitle(R.drawable.o_gyudongbest);
                o16.getTastList().add(2);
                o16.getTastList().add(3);
                o16.getTastList().add(4);
                o16.getTastList().add(5);
                o16.getTastList().add(8);
                o16.setTitle("이규동베스트");
                o16.setDesc("규동(M)+오니기리2개(참치샐러드,고소한멸치)+사누끼우동");
                o16.setPrice("12400");

                MenuBean o17 = new MenuBean();
                o17.setImgTitle(R.drawable.o_sanukkiwoodong);
                o17.getTastList().add(3);
                o17.getTastList().add(8);
                o17.setTitle("사누끼우동");
                o17.setDesc("감칠맛 나는 일본 정통 우동소스에 쫄깃한 최고급 면을 사용");
                o17.setPrice("3900");

                MenuBean o18 = new MenuBean();
                o18.setImgTitle(R.drawable.o_fishcakewoodong);
                o18.getTastList().add(3);
                o18.getTastList().add(8);
                o18.setTitle("꼬치어묵우동");
                o18.setDesc("진한 사누끼 우동장국과 다양한 어묵과 함께 즐길 수 있는 우동");
                o18.setPrice("4900");

                MenuBean o19 = new MenuBean();
                o19.setImgTitle(R.drawable.o_icon3);
                o19.getTastList().add(3);
                o19.getTastList().add(6);
                o19.getTastList().add(8);
                o19.setTitle("치즈야끼우동");
                o19.setDesc("쫀득한 치즈가 들어간 매콤한 볶음우동요리");
                o19.setPrice("5400");

                MenuBean o20 = new MenuBean();
                o20.setImgTitle(R.drawable.o_bossamkimcgionigiri);
                o20.getTastList().add(1);
                o20.getTastList().add(2);
                o20.getTastList().add(3);
                o20.getTastList().add(5);
                o20.setTitle("보쌈김치");
                o20.setDesc("상큼하고 아삭한 보쌈 무를 넣은 개운한 오니기리");
                o20.setPrice("1500");

                MenuBean o21 = new MenuBean();
                o21.setImgTitle(R.drawable.o_bulgangnan_onigiri);
                o21.getTastList().add(1);
                o21.getTastList().add(3);
                o21.setTitle("불장난오징어");
                o21.setDesc("쫄깃한 오징어와 오이를 고춧가루양념에 버무린 매운 오니기리");
                o21.setPrice("1900");

                MenuBean o22 = new MenuBean();
                o22.setImgTitle(R.drawable.o_tunaonigiri);
                o22.getTastList().add(3);
                o22.getTastList().add(4);
                o22.setTitle("참치샐러드");
                o22.setDesc("담백한 참치에 피클과 마요네즈를 함께 버무려 고소함이 일품인 오니기리");
                o22.setPrice("1600");

                MenuBean o23 = new MenuBean();
                o23.setImgTitle(R.drawable.o_mulchionigiri);
                o23.getTastList().add(3);
                o23.getTastList().add(4);
                o23.setTitle("고소한멸치");
                o23.setDesc("멸치를 고소하고 바삭하게 볶아 넣은 남녀노소가 좋아하는 오니기리");
                o23.setPrice("2200");

                MenuBean o24 = new MenuBean();
                o24.setImgTitle(R.drawable.o_curryonigiri);
                o24.getTastList().add(2);
                o24.getTastList().add(3);
                o24.getTastList().add(4);
                o24.setTitle("커리치킨");
                o24.setDesc("고소한 치킨 가라아게에 달콤한 카레소스를 버무린 오니기리");
                o24.setPrice("1600");

                MenuBean o25 = new MenuBean();
                o25.setImgTitle(R.drawable.o_spamonigiri);
                o25.getTastList().add(3);
                o25.setTitle("구운스팸");
                o25.setDesc("스팸을 사용한 짭쪼름하고 구수한 오니기리");
                o25.setPrice("2200");

                MenuBean o26 = new MenuBean();
                o26.setImgTitle(R.drawable.o_cupbap);
                o26.getTastList().add(1);
                o26.getTastList().add(3);
                o26.getTastList().add(4);
                o26.getTastList().add(5);
                o26.setTitle("오니한컵");
                o26.setDesc("신개념 휴대용 컵밥(참치샐러드+보쌈김치+고소한멸치)");
                o26.setPrice("3300");

                MenuBean o27 = new MenuBean();
                o27.setImgTitle(R.drawable.o_icon1);
                o27.getTastList().add(6);
                o27.setTitle("치즈 추가");
                o27.setDesc("");
                o27.setPrice("1000");

                MenuBean o28 = new MenuBean();
                o28.setImgTitle(R.drawable.o_icon1);
                o28.getTastList().add(7);
                o28.setTitle("치킨가라아게(5pcs)");
                o28.setDesc("");
                o28.setPrice("3000");

                MenuBean o29 = new MenuBean();
                o29.setImgTitle(R.drawable.o_icon1);
                o29.getTastList().add(7);
                o29.setTitle("새우튀김(2pcs)");
                o29.setDesc("");
                o29.setPrice("3000");

                MenuBean o = new MenuBean();
                o.setImgTitle(R.drawable.o_icon3);
                o.setTitle("세트 추가");
                o.setDesc("미니우동 추가 제공(돈부리 종류만 가능)");
                o.setPrice("1500");

                menuList.add(o1);
                menuList.add(o2);
                menuList.add(o3);
                menuList.add(o4);
                menuList.add(o5);
                menuList.add(o6);
                menuList.add(o7);
                menuList.add(o8);
                menuList.add(o9);
                menuList.add(o10);
                menuList.add(o11);
                menuList.add(o12);
                menuList.add(o13);
                menuList.add(o14);
                menuList.add(o15);
                menuList.add(o);
                menuList.add(o16);
                menuList.add(o17);
                menuList.add(o18);
                menuList.add(o19);
                menuList.add(o20);
                menuList.add(o21);
                menuList.add(o22);
                menuList.add(o23);
                menuList.add(o24);
                menuList.add(o25);
                menuList.add(o26);
                menuList.add(o27);
                menuList.add(o28);
                menuList.add(o29);


                break;

            case 9: //에땅

                break;


        }

        viewCheckMenu(menuList);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<MenuBean> newList = new ArrayList<>();

                //체크되어있는 항목 확인하고 배열에 부울값 넣기
                Boolean menuCheck[] = new Boolean[chkNum];
                if(chk1.isChecked()) menuCheck[0] = true;
                else menuCheck[0] = false;
                if(chk2.isChecked()) menuCheck[1] = true;
                else menuCheck[1] = false;
                if(chk3.isChecked()) menuCheck[2] = true;
                else  menuCheck[2] = false;
                if(chk4.isChecked()) menuCheck[3] = true;
                else menuCheck[3] = false;
                if(chk5.isChecked()) menuCheck[4] = true;
                else menuCheck[4] = false;
                if(chk6.isChecked()) menuCheck[5] = true;
                else menuCheck[5] = false;
                if(chk7.isChecked()) menuCheck[6] = true;
                else menuCheck[6] = false;
                if(chk8.isChecked()) menuCheck[7] = true;
                else menuCheck[7] = false;


                //for문을 돌며 부울값이 true인데 끝까지 존재하면 list에 추가하기
                Boolean add = false; //리스트 추가할 것인지
                for(int i = 0; i<menuList.size(); i++) { //i번째 메뉴
                    Boolean real = true; //체크된 모든게 선택된 것인지
                    for(int j = 0; j<chkNum; j++) {//체크박스 갯수
                        if(menuCheck[j] == true) {
                            Boolean change = false; //add가 이번 체크박스에 바뀐것인지
                            for(int k = 0; k<menuList.get(i).getTastList().size(); k++) {//i번째 메뉴의 k번째 맛
                                if(menuList.get(i).getTastList().get(k) == j+1)  {
                                    add = true;
                                    change = true;
                                }
                                else if(k == menuList.get(i).getTastList().size()-1 && change == false) {
                                    add = false;
                                    real = false;
                                }
                            }
                        }
                    }
                    if(add == true && real == true ) newList.add(menuList.get(i));
                    viewCheckMenu(newList);
                }
            }
        });
    }

    public void viewCheckMenu(List<MenuBean> list) {
        StoreAdapter viewAdapter = new StoreAdapter(getActivity(), list);
        lstMain.setAdapter(viewAdapter);
    }

}