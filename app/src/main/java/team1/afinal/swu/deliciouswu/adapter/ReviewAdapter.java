package team1.afinal.swu.deliciouswu.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.net.URL;
import java.util.List;

import team1.afinal.swu.deliciouswu.DownImgTask;
import team1.afinal.swu.deliciouswu.R;
import team1.afinal.swu.deliciouswu.WriteReviewActivity;
import team1.afinal.swu.deliciouswu.bean.ReviewBean;

public class ReviewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReviewBean> mList;

    public ReviewAdapter(Context context, List<ReviewBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
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
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_list_review, null);

        ImageView imgPhoto = convertView.findViewById(R.id.imgPhoto);
        TextView txtStore = convertView.findViewById(R.id.txtStore);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtContents = convertView.findViewById(R.id.txtContents);
        Button btnModify = convertView.findViewById(R.id.btnModify);
        Button btnDel = convertView.findViewById(R.id.btnDel);
        Spinner spinner2= convertView.findViewById(R.id.spinner2);


        final ReviewBean bean = mList.get(position);

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("후기를 수정하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(mContext, WriteReviewActivity.class);
                //데이터를 실어준다.
                i.putExtra(ReviewBean.class.getName(), bean);
                mContext.startActivity(i);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "후기를 수정 취소", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("이 후기를 삭제하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String emailUUID = WriteReviewActivity.getUserIdFromUUID( email );

                //데이터 삭제
                FirebaseDatabase.getInstance()
                        .getReference().child("review").child(bean.id).removeValue();

                //이미지 삭제
                FirebaseStorage.getInstance().getReference()
                        .child("images").child(bean.imgName).delete();

                Toast.makeText( mContext, "삭제 되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "삭제 취소", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });

        if(imgPhoto.getDrawable() == null) {
            try {
                new DownImgTask(imgPhoto).execute(new URL(bean.imgUrl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        txtTitle.setText( bean.title );
        txtContents.setText( bean.contents );
        txtStore.setText(bean.getStore());
        
        return convertView;
    }

}
