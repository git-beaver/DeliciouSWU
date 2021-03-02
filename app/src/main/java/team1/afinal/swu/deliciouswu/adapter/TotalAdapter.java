package team1.afinal.swu.deliciouswu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.bean.TotalBean;
import team1.afinal.swu.deliciouswu.bean.TotalBean2;
import team1.afinal.swu.deliciouswu.fragment.MainTabFragment.CalculateFragment;
import team1.afinal.swu.deliciouswu.util.PrefUtil;

import static java.lang.Integer.parseInt;

public class TotalAdapter extends BaseAdapter {
    private Context pContext;
    private List<TotalBean> pList;
    private CalculateFragment calculateFragment;

    //생성자
    public TotalAdapter (Context context, List<TotalBean>list, CalculateFragment calculateFragment) {
        pContext = context;
        pList = list;
        this.calculateFragment = calculateFragment;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        //인플레이팅 하는 작업
        LayoutInflater inflater = (LayoutInflater)
                pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_calculate, null);

        //해당 ROW 의 데이터를 찾는 작업
        TotalBean totalBean = pList.get(position);

        //인플레이팅 된 뷰에서 ID 찾는작업
        ImageView imgTitle = convertView.findViewById( R.id.imgTitle );
        final TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        ImageButton btnDel = convertView.findViewById(R.id.btnDel);

        //데이터 셋팅
        imgTitle.setImageResource( totalBean.getImgTitle());
        txtTitle.setText( totalBean.getTitle() );
        txtPrice.setText( totalBean.getPrice() );



        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(pContext);
                builder.setMessage("이 상품을 삭제하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TotalBean2 tb = new TotalBean2();
                        Gson gson = new Gson();
                        String jsonStr = PrefUtil.getData(pContext, "totalBean");

                        tb = gson.fromJson(jsonStr, TotalBean2.class);
                        tb.setPriceSum(tb.getPriceSum() - parseInt(tb.getTotalList().get(position).getPrice()));
                        tb.getTotalList().remove(position);

                        jsonStr = gson.toJson(tb);
                        tb = gson.fromJson(jsonStr, TotalBean2.class);
                        PrefUtil.setData(pContext, "totalBean", jsonStr);

                        pList.remove(position);

                        notifyDataSetInvalidated();

                        calculateFragment.calculate();

                        Toast.makeText(pContext, "삭제가 완료 되었습니다.", Toast.LENGTH_SHORT).show();


                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(pContext, "삭제 하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });
        return convertView;
    }
}
