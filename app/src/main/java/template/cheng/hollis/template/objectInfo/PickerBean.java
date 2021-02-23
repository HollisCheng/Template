package template.cheng.hollis.template.objectInfo;

import java.util.ArrayList;

public class PickerBean {
    private int pickerId;
    private ArrayList<Integer> areaPointListNeedToGo;
    private ArrayList<OrderBean> assignedOrders;

    public PickerBean() {
    }

    public PickerBean(int pickerId, ArrayList<Integer> areaPointListNeedToGo, ArrayList<OrderBean> assignedOrders) {
        this.pickerId = pickerId;
        this.areaPointListNeedToGo = areaPointListNeedToGo;
        this.assignedOrders = assignedOrders;
    }

    @Override
    public String toString() {
        return "PickerBean{" +
                "pickerId=" + pickerId +
                ", areaPointListNeedToGo=" + areaPointListNeedToGo +
                ", assignedOrders=" + assignedOrders +
                '}';
    }

    public int getPickerId() {
        return pickerId;
    }

    public void setPickerId(int pickerId) {
        this.pickerId = pickerId;
    }

    public ArrayList<Integer> getAreaPointListNeedToGo() {
        return areaPointListNeedToGo;
    }

    public void setAreaPointListNeedToGo(ArrayList<Integer> areaPointListNeedToGo) {
        this.areaPointListNeedToGo = areaPointListNeedToGo;
    }

    public ArrayList<OrderBean> getAssignedOrders() {
        return assignedOrders;
    }

    public void setAssignedOrders(ArrayList<OrderBean> assignedOrders) {
        this.assignedOrders = assignedOrders;
    }
}
