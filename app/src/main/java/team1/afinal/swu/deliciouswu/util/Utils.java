package team1.afinal.swu.deliciouswu.util;

import android.app.Dialog;
import android.content.Context;

import team1.afinal.swu.deliciouswu.R;

public class Utils {

    private static Dialog mProgDlg;

    //화면에 프로그레스를 보여준다
    public static void showProgress(Context context) {
        if (mProgDlg != null && mProgDlg.isShowing()) {
            mProgDlg.hide();
        }

        mProgDlg = new Dialog(context);
        mProgDlg.setContentView(R.layout.view_progress);
        mProgDlg.setCancelable(false);
        mProgDlg.show();

    }  //end showProgress()

    public static void hideProgress(Context context) {
        if (mProgDlg != null) {
            mProgDlg.hide();
        }
    }  //end hideProgress()
}
