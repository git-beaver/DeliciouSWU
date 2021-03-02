package team1.afinal.swu.deliciouswu.fragment.MainTabFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import team1.afinal.swu.deliciouswu.MenuMainActivity;
import team1.afinal.swu.deliciouswu.R;

public class MenuFragment extends Fragment {

    public ImageView btnStar,btnGaeun,btnPan,btnBtJuice,btnTlj,
            btnQuiz,btnGt,btnChu,btnGyudong,btnEtang;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, null);

        btnStar = view.findViewById(R.id.btnStar); //스타벅스
        btnGaeun = view.findViewById(R.id.btnGaeun); //가은
        btnPan = view.findViewById(R.id.btnPan); //팬도로시
        btnBtJuice = view.findViewById(R.id.btnBtJuice); //비틀주스
        btnQuiz = view.findViewById(R.id.btnQuiz); //퀴즈노스
        btnGt = view.findViewById(R.id.btnGt); //감탄 떡볶이
        btnChu = view.findViewById(R.id.btnChu); //츄밥
        btnGyudong = view.findViewById(R.id.btnGyudong); //오니기리와 이규동
        btnEtang = view.findViewById(R.id.btnEtang); //에땅


        //버튼 클릭이벤트
        btnStar.setOnClickListener(btnClick);
        btnGaeun.setOnClickListener(btnClick);
        btnPan.setOnClickListener(btnClick);
        btnQuiz.setOnClickListener(btnClick);
        btnGyudong.setOnClickListener(btnClick);

        return view;
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getActivity(),MenuMainActivity.class);

            switch (view.getId()) {
                case R.id.btnStar:
                    intent.putExtra("storeKey", 1);
                    break;
                case R.id.btnGaeun:
                    intent.putExtra("storeKey", 2);
                    break;
                case R.id.btnPan:
                    intent.putExtra("storeKey", 3);
                    break;
                case R.id.btnBtJuice:
                    intent.putExtra("storeKey", 4);
                    break;
                case R.id.btnGt:
                    intent.putExtra("storeKey", 5);
                    break;
                case R.id.btnQuiz:
                    intent.putExtra("storeKey", 6);
                    break;
                case R.id.btnChu:
                    intent.putExtra("storeKey", 7);
                    break;
                case R.id.btnGyudong:
                    intent.putExtra("storeKey", 8);
                    break;
                case R.id.btnEtang:
                    intent.putExtra("storeKey", 9);
                    break;

            }

            startActivity(intent);

        }
    };
}
