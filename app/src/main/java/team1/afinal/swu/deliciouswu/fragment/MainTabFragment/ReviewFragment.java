package team1.afinal.swu.deliciouswu.fragment.MainTabFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.ListView;

import team1.afinal.swu.deliciouswu.MainActivity;
import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.WriteReviewActivity;
import team1.afinal.swu.deliciouswu.adapter.ReviewAdapter;
import team1.afinal.swu.deliciouswu.bean.ReviewBean;


public class ReviewFragment extends Fragment {
    public ListView lstReview;
    public Button btnNew;
    private Button btnFindReview;

    public FirebaseAuth mAuth;
    public FirebaseStorage mStorage;
    public FirebaseDatabase mDatabase;

    public List<ReviewBean> mReviewList = new ArrayList<ReviewBean>();
    public ReviewAdapter mReviewAdapter, mReviewAdapter2;
    private Spinner mSpinner;
    private String mStoreName = "스타벅스";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, null);

        mSpinner = view.findViewById(R.id.spinner1);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String[] storeArr = getResources().getStringArray(R.array.store);

//                Toast.makeText(getActivity(), storeArr[position], Toast.LENGTH_LONG).show();
                mStoreName = storeArr[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        lstReview =view.findViewById(R.id.lstReview);
        btnNew = view.findViewById(R.id.btnNew);

        btnFindReview = view.findViewById(R.id.btnFindReview);



        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("새로운 리뷰를 작성하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                if(mAuth.getCurrentUser() == null || mAuth.getCurrentUser().getEmail() == null) {
                    Toast.makeText(getActivity(), "리뷰 작성을 위해 로그인 해주세요.", Toast.LENGTH_LONG).show();
                    ((MainActivity)getActivity()).goTabIndex(0);
                    return;
                }

                Intent i = new Intent(getActivity(), WriteReviewActivity.class);
                startActivity(i);     }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), " 리뷰작성 취소", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();

            }
        });

        //데이터 목록을 Firebase로 부터 가져온다.
        DatabaseReference ref = mDatabase.getReference().child("review");
        if(ref != null) {
            mDatabase.getReference().child("review").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //실시간으로 서버가 변경된 내용이 있을경우 호출된다.
                    //기존에 리스트는 날려버린다.
                    mReviewList.clear();

                    //리스트를 서버로부터 온 데이터로 새로 만든다.
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ReviewBean bean = snapshot.getValue(ReviewBean.class);
                        mReviewList.add(bean);
                    }

                    //어댑터를 갱신하는 메서드를 호출한다.
                    mReviewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        mReviewAdapter = new ReviewAdapter(getActivity(), mReviewList);
        lstReview.setAdapter(mReviewAdapter);

        btnFindReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ReviewBean> selReview = new ArrayList<ReviewBean>();
                int selNum = mSpinner.getSelectedItemPosition();
                if(selNum == 0) {
                    lstReview.setAdapter((mReviewAdapter));
                } else if(selNum > 0) {
                    for(int i= 0; i<mReviewList.size(); i++) {
                        if(mReviewList.get(i).getRevStoreNum() == selNum)
                            selReview.add(mReviewList.get(i));
                    }

                    mReviewAdapter2 = new ReviewAdapter(getActivity(), selReview);
                    lstReview.setAdapter(mReviewAdapter2);
                }
            }
        });

        return view;
    }

}





