package team1.afinal.swu.deliciouswu.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuBean implements Serializable {

    //타이틀 이미지
    private int imgTitle;
    //타이틀
    private String title;
    //설명
    private String desc;
    //가격
    private String price;
    //맛
    private List<Integer> tastList = new ArrayList<Integer>();


    public List<Integer> getTastList() {
        return tastList;
    }

    public void setTastList(List<Integer> tastList) {
        this.tastList = tastList;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}