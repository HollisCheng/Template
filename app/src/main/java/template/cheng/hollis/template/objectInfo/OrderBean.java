package template.cheng.hollis.template.objectInfo;

import java.util.ArrayList;

public class OrderBean {
    private int orderId;
    private ArrayList<Integer> areaPointListNeedToGo;

    public OrderBean(int orderId, ArrayList<Integer> areaPointListNeedToGo) {
        this.orderId = orderId;
        this.areaPointListNeedToGo = areaPointListNeedToGo;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId=" + orderId +
                ", areaPointListNeedToGo=" + areaPointListNeedToGo +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Integer> getAreaPointListNeedToGo() {
        return areaPointListNeedToGo;
    }

    public void setAreaPointListNeedToGo(ArrayList<Integer> areaPointListNeedToGo) {
        this.areaPointListNeedToGo = areaPointListNeedToGo;
    }
}
