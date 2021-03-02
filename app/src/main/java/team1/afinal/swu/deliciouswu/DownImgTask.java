package team1.afinal.swu.deliciouswu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

public class DownImgTask extends AsyncTask<URL, Void, Bitmap> {

    //이미지뷰를 저장하는 임시 멤버변수 (메모리 관리 ImageView)
    private WeakReference<ImageView> mImageView;

    public DownImgTask(ImageView imageView) {
        mImageView = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(URL... urls) {
        //쓰레드 처리
        URL imageURL = urls[0];
        Bitmap downloadedBitmap = null;

        try {
            //인터넷을 통해서 이미지를 다운로드 받는다.
            InputStream inputStream = imageURL.openStream();
            downloadedBitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return downloadedBitmap;
    }//end doInBackground()

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null && mImageView.get() != null) {
            //화면상에 이미지를 대입한다.
            mImageView.get().setImageBitmap(bitmap);
        }
    }//end onPostExecute()

}