package template.cheng.hollis.template.objectInfo;

import java.util.ArrayList;

public class OrderBean {
    private int orderId;
    private ArrayList<Integer> areaPointListNeedToGo;
    private int arraySize;
    private boolean isAssigned = false;

    public OrderBean(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId=" + orderId +
                ", areaPointListNeedToGo=" + areaPointListNeedToGo +
                ", arraySize=" + arraySize +
                ", isAssigned=" + isAssigned +
                '}';
    }

    public void setAreaPointListNeedToGoAndArraySize(ArrayList<Integer> areaPointListNeedToGo) {
        this.areaPointListNeedToGo = areaPointListNeedToGo;
        this.arraySize = areaPointListNeedToGo.size();
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

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }
}
