package team1.afinal.swu.deliciouswu.fragment.MainTabFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.adapter.StoreAdapter;
import team1.afinal.swu.deliciouswu.adapter.TotalAdapter;
import team1.afinal.swu.deliciouswu.bean.TotalBean;
import team1.afinal.swu.deliciouswu.bean.TotalBean2;
import team1.afinal.swu.deliciouswu.util.PrefUtil;

import static java.lang.Integer.parseInt;

public class CalculateFragment extends Fragment {

    private ListView lstMenu;
    private TextView txtNum;
    private TextView txtSum, txtMoney;
    private EditText edtPNum;
    private Button btnOk;
    private TotalAdapter totalAdapter;
    private TotalBean2 totalBean2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, null);


        lstMenu = view.findViewById(R.id.lstMenu);
        txtNum = view.findViewById(R.id.txtNum);
        txtSum = view.findViewById(R.id.txtSum);
        txtMoney = view.findViewById(R.id.txtMoney);
        edtPNum = view.findViewById(R.id.edtPNum);
        btnOk = view.findViewById(R.id.btnOk);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        totalBean2 = new TotalBean2();
        Gson gson = new Gson();

        //기존에 saveBean에 저장된 리스트를 가져온다.
        String jsonStr = PrefUtil.getData(getActivity(), "totalBean");

        if (jsonStr.length() > 0) {
            totalBean2 = gson.fromJson(jsonStr, TotalBean2.class);
        }

        if (totalBean2.getTotalList() == null) {
            totalBean2.setTotalList(new ArrayList<TotalBean>());
        }

        //Adapter 생성
        totalAdapter = new TotalAdapter(getActivity(), totalBean2.getTotalList(), this);


        //Adapter를 ListView에 부착
        lstMenu.setAdapter(totalAdapter);

       calculate();

    }//end onResume()

    public void calculate() {
        Gson gson = new Gson();

        //기존에 saveBean에 저장된 리스트를 가져온다.
        String jsonStr = PrefUtil.getData(getActivity(), "totalBean");

        if (jsonStr.length() > 0) {
            totalBean2 = gson.fromJson(jsonStr, TotalBean2.class);
        }

        if (totalBean2.getTotalList() == null) {
            totalBean2.setTotalList(new ArrayList<TotalBean>());
        }
        final int priceSum = totalBean2.getPriceSum();

        txtNum.setText("총 " + totalAdapter.getCount() + "개");
        txtSum.setText("합계 " + priceSum + "원");
        txtMoney.setText("1인당 금액 " + priceSum + "원");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPNum != null && edtPNum.length() > 0) {
                    int person = parseInt(edtPNum.getText().toString());
                    int devidePrice = priceSum / person;
                    int devidePrice2 = 0;
                    if (priceSum % person != 0) {
                        devidePrice2 = priceSum - (devidePrice * (person - 1));
                        txtMoney.setText("1인당 금액 " + devidePrice + "원, 랜덤 1인의 금액 " + devidePrice2 + "원");
                    } else {
                        txtMoney.setText("1인당 금액 " + devidePrice + "원");
                    }
                    edtPNum.setText("");
                }
            }
        });
    }
}
