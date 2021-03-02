package team1.afinal.swu.deliciouswu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.bean.MenuBean;
import team1.afinal.swu.deliciouswu.bean.TotalBean;
import team1.afinal.swu.deliciouswu.bean.TotalBean2;
import team1.afinal.swu.deliciouswu.fragment.MainTabFragment.CalculateFragment;
import team1.afinal.swu.deliciouswu.util.PrefUtil;

import static java.lang.Integer.parseInt;

public class StoreAdapter extends BaseAdapter {

    private Context pContext;
    private List<MenuBean> pList;

    //생성자
    public StoreAdapter(Context context, List<MenuBean>list) {
        pContext = context;
        pList = list;
    }

    @Override
    public int getCount() {
        return pList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //인플레이팅 하는 작업
        LayoutInflater inflater = (LayoutInflater)
                pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_menu, null);

        //해당 ROW 의 데이터를 찾는 작업
        final MenuBean menuBean = pList.get(position);

        //인플레이팅 된 뷰에서 ID 찾는작업
        final ImageView imgTitle = convertView.findViewById( R.id.imgTitle );
        final TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtDesc = convertView.findViewById(R.id.txtDesc);
        final TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        ImageButton btnShop = convertView.findViewById(R.id.btnShop);

        final MenuBean mBean = pList.get(position);

        //데이터 셋팅
        imgTitle.setImageResource( menuBean.getImgTitle() );
        txtTitle.setText( menuBean.getTitle() );
        txtDesc.setText( menuBean.getDesc() );
        txtPrice.setText( menuBean.getPrice() );

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
                builder.setMessage("이 상품을 장바구니에 담을까요?");
                builder.setCancelable(false);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TotalBean tb = new TotalBean();
                        tb.setImgTitle( menuBean.getImgTitle());
                        tb.setTitle(txtTitle.getText().toString());
                        tb.setPrice(txtPrice.getText().toString());

                        TotalBean2 totalBean2 = new TotalBean2();
                        Gson gson = new Gson();
                        String jsonStr = PrefUtil.getData(pContext, "totalBean");
                        if(jsonStr.length() > 0) {
                            totalBean2 = gson.fromJson(jsonStr, TotalBean2.class);
                        }

                        if( totalBean2.getTotalList() == null ) {
                            totalBean2.setTotalList( new ArrayList<TotalBean>() );
                            totalBean2.setPriceSum(0);
                        }

                        totalBean2.getTotalList().add(tb);//추가
                        totalBean2.setPriceSum(totalBean2.getPriceSum()+parseInt(tb.getPrice()));

                        jsonStr = gson.toJson(totalBean2);
                        PrefUtil.setData(pContext, "totalBean", jsonStr);

                        Toast.makeText(pContext, "장바구니에 담았습니다.", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(pContext, "장바구니에 담지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        return convertView;
    }  //end getView()
}