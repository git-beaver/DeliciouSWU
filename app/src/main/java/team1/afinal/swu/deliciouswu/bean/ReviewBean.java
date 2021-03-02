package team1.afinal.swu.deliciouswu.bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {

    public String id;  //게시글 고유ID
    public String userId; //게시글 소유자 ID
    public String imgUrl; //이미가 업로드된 풀경로
    public String imgName; //이미지 파일 이름
    public String title; //제목
    public String contents; //내용

    public String store;//가게

    public int revStoreNum; //리뷰 쓸 가게 번호

    public int getRevStoreNum() {
        return revStoreNum;
    }

    public void setRevStoreNum(int revStoreNum) {
        this.revStoreNum = revStoreNum;
    }

    public String getStore() {
        switch(getRevStoreNum()) {
            case 1:
                store = "스타벅스";
                break;
            case 2:
                store = "가은";
                break;
            case 3:
                store = "팬도로시";
                break;
            case 4:
                store = "비틀주스";
                break;
            case 5:
                store = "퀴즈노스";
                break;
            case 6:
                store = "감탄떡볶이";
                break;
            case 7:
                store = "츄밥";
                break;
            case 8:
                store = "오니기리와 이규동";
                break;
            case 9:
                store = "에땅";
                break;
            default:
                store = "가게를 선택하지 않았습니다.";
                break;
        }
        return store;
    }

    public ReviewBean() {
        //RealDB가 디폴트 생성자를 필요로 한다.
    }
}
