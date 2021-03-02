package team1.afinal.swu.deliciouswu.fragment.StoreFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import team1.afinal.swu.deliciouswu.R;

public class StoreInfoFragment extends Fragment {

    private int mStoreIndex;
    private TextView txtInfo1, txtCall;
    private ImageView imgLocation, btnCall, imgGifLogo, imgStoreLogo;

    public void setStoreIndex(int storeIndex) {
        mStoreIndex = storeIndex;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_info, null);

        txtInfo1 = view.findViewById(R.id.txtInfo1); //가게 메인 슬로건
        txtCall = view.findViewById(R.id.txtCall);  //가게 전화번호
        imgLocation = view.findViewById(R.id.imgLocation); //가게 위치 이미지뷰
        btnCall = view.findViewById(R.id.btnCall);  //가게 전화 연결
        imgGifLogo = view.findViewById(R.id.imgGifLogo); //가게 gif 이미지
        imgStoreLogo = view.findViewById(R.id.imgStoreLogo);

        switch (mStoreIndex) {
            case 1:
                txtInfo1.setText("여기는 스타벅스입니다.");
                break;
            case 2:
                txtInfo1.setText("여기는 가은입니다.");
                break;
            case 3:
                txtInfo1.setText("여기는 팬도로시입니다.");
                break;

                case 4:
                    break;

                    case 5:
                         break;

            case 6:
                txtInfo1.setText("The Best Sandwich you'll ever eat!");
                imgStoreLogo.setImageResource(R.drawable.quiznos_long_logo);
                imgLocation.setImageResource(R.drawable.quiz); //퀴즈노스 지도 삽입하기
                GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imgGifLogo);
                Glide.with(this).load(R.drawable.quiznos_ad).into(gifImage);
                txtCall.setText("02-977-7923");
                break;

                case 7:
            break;

            case 8: //오니기리
                txtInfo1.setText("웰빙트렌드를 기반으로 한\n전문화, 다양화");
                imgStoreLogo.setImageResource(R.drawable.onigirilongimg);
                imgLocation.setImageResource(R.drawable.oni);
                GlideDrawableImageViewTarget gifImage1 = new GlideDrawableImageViewTarget(imgGifLogo);
                Glide.with(this).load(R.drawable.onigiri_ad).into(gifImage1);
                txtCall.setText("070-8848-0290");
            break;

            case 9:
            break;

        }

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력된 전화번호를 가져온다.
                String tel = txtCall.getText().toString();
                Intent i = new Intent(Intent.ACTION_DIAL);  //전화걸기
                i.setData(Uri.parse("tel:" + tel));
                startActivity(i);
            }
        });

        return view;
    }

}
