package team1.afinal.swu.deliciouswu.bean;

import java.io.Serializable;
import java.util.List;

public class TotalBean2 implements Serializable {
    private List<TotalBean> totalList;
    private int priceSum;

    public List<TotalBean> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<TotalBean> totalList) {
        this.totalList = totalList;
    }

    public int getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(int priceSum) {
        this.priceSum = priceSum;
    }
}
