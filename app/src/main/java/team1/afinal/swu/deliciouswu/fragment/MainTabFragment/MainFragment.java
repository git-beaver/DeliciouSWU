package team1.afinal.swu.deliciouswu.fragment.MainTabFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import team1.afinal.swu.deliciouswu.LoginActivity;
import team1.afinal.swu.deliciouswu.MainActivity;
import team1.afinal.swu.deliciouswu.MenuLotateActivity;
import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.util.PrefUtil;
import team1.afinal.swu.deliciouswu.util.Utils;

public class MainFragment extends Fragment {

    //파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    private Button btnLogout,btnGo;
    private RadioButton radioSWU,radioSWUEclass,radioSwis;
    private TextView txtId;
    private ImageView imgShalom;

    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);

        //파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogout = view.findViewById(R.id.btnLogout);
        btnGo = view.findViewById(R.id.btnGo);
        radioSWU = view.findViewById(R.id.radioSWU);
        radioSWUEclass = view.findViewById(R.id.radioSWUEclass);
        radioSwis = view.findViewById(R.id.radioSwis);
        txtId = view.findViewById(R.id.txtId);

        imgShalom = view.findViewById(R.id.imgShalom);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imgShalom);
        Glide.with(this).load(R.drawable.todaybap).into(gifImage);



        txtId.setText(firebaseAuth.getCurrentUser().getEmail()+"님\n방문을 환영합니다.");

        //샬롬 식단표
        imgShalom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MenuLotateActivity.class);
                startActivity(intent);
            }
        });

        //학교 로고 버튼 클릭시 학교 홈페이지로 이동
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioSWU.isChecked()) {
                    String url = "http://www.swu.ac.kr/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return;
                } else if(radioSWUEclass.isChecked()) {
                    String url = "http://cyber.swu.ac.kr/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return;
                } else if(radioSwis.isChecked()) {
                    String url = "http://swis.swu.ac.kr/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return;
                } else {
                    Toast.makeText(getActivity(),"선택된 페이지가 없습니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        //로그아웃
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    } // end onCreateView()

}
