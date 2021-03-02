package team1.afinal.swu.deliciouswu.bean;

import java.io.Serializable;

public class TotalBean implements Serializable {

    //타이틀 이미지
    private int imgTitle;
    //타이틀
    private String title;
    //가격
    private String price;

    public int getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(int imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
