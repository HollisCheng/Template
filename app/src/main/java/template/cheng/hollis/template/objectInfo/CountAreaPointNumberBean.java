package template.cheng.hollis.template.objectInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CountAreaPointNumberBean {
    private int total1 = 0;
    private int total2 = 0;
    private int total3 = 0;
    private int total4 = 0;
    private int total5 = 0;
    private int total6 = 0;
    private int total7 = 0;
    private int total8 = 0;
    private int total9 = 0;
    private int total10 = 0;

    private ArrayList<OrderBean> isContain1List = new ArrayList<>();
    private ArrayList<OrderBean> isContain2List = new ArrayList<>();
    private ArrayList<OrderBean> isContain3List = new ArrayList<>();
    private ArrayList<OrderBean> isContain4List = new ArrayList<>();
    private ArrayList<OrderBean> isContain5List = new ArrayList<>();
    private ArrayList<OrderBean> isContain6List = new ArrayList<>();
    private ArrayList<OrderBean> isContain7List = new ArrayList<>();
    private ArrayList<OrderBean> isContain8List = new ArrayList<>();
    private ArrayList<OrderBean> isContain9List = new ArrayList<>();
    private ArrayList<OrderBean> isContain10List = new ArrayList<>();

    public CountAreaPointNumberBean() {
    }

    public CountAreaPointNumberBean(int total1, int total2, int total3, int total4, int total5, int total6, int total7, int total8, int total9, int total10) {
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
        this.total4 = total4;
        this.total5 = total5;
        this.total6 = total6;
        this.total7 = total7;
        this.total8 = total8;
        this.total9 = total9;
        this.total10 = total10;
    }

    public CountAreaPointNumberBean(int total1, int total2, int total3, int total4, int total5, int total6, int total7, int total8, int total9, int total10, ArrayList<OrderBean> isContain1List, ArrayList<OrderBean> isContain2List, ArrayList<OrderBean> isContain3List, ArrayList<OrderBean> isContain4List, ArrayList<OrderBean> isContain5List, ArrayList<OrderBean> isContain6List, ArrayList<OrderBean> isContain7List, ArrayList<OrderBean> isContain8List, ArrayList<OrderBean> isContain9List, ArrayList<OrderBean> isContain10List) {
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
        this.total4 = total4;
        this.total5 = total5;
        this.total6 = total6;
        this.total7 = total7;
        this.total8 = total8;
        this.total9 = total9;
        this.total10 = total10;
        this.isContain1List = isContain1List;
        this.isContain2List = isContain2List;
        this.isContain3List = isContain3List;
        this.isContain4List = isContain4List;
        this.isContain5List = isContain5List;
        this.isContain6List = isContain6List;
        this.isContain7List = isContain7List;
        this.isContain8List = isContain8List;
        this.isContain9List = isContain9List;
        this.isContain10List = isContain10List;
    }

    @Override
    public String toString() {
        return "CountAreaPointNumberBean{" +
                "total1=" + total1 +
                ", total2=" + total2 +
                ", total3=" + total3 +
                ", total4=" + total4 +
                ", total5=" + total5 +
                ", total6=" + total6 +
                ", total7=" + total7 +
                ", total8=" + total8 +
                ", total9=" + total9 +
                ", total10=" + total10 +
                ", isContain1List=" + isContain1List +
                ", isContain2List=" + isContain2List +
                ", isContain3List=" + isContain3List +
                ", isContain4List=" + isContain4List +
                ", isContain5List=" + isContain5List +
                ", isContain6List=" + isContain6List +
                ", isContain7List=" + isContain7List +
                ", isContain8List=" + isContain8List +
                ", isContain9List=" + isContain9List +
                ", isContain10List=" + isContain10List +
                '}';
    }

    public int getTotal1() {
        return total1;
    }

    public void setTotal1(int total1) {
        this.total1 = total1;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }

    public int getTotal3() {
        return total3;
    }

    public void setTotal3(int total3) {
        this.total3 = total3;
    }

    public int getTotal4() {
        return total4;
    }

    public void setTotal4(int total4) {
        this.total4 = total4;
    }

    public int getTotal5() {
        return total5;
    }

    public void setTotal5(int total5) {
        this.total5 = total5;
    }

    public int getTotal6() {
        return total6;
    }

    public void setTotal6(int total6) {
        this.total6 = total6;
    }

    public int getTotal7() {
        return total7;
    }

    public void setTotal7(int total7) {
        this.total7 = total7;
    }

    public int getTotal8() {
        return total8;
    }

    public void setTotal8(int total8) {
        this.total8 = total8;
    }

    public int getTotal9() {
        return total9;
    }

    public void setTotal9(int total9) {
        this.total9 = total9;
    }

    public int getTotal10() {
        return total10;
    }

    public void setTotal10(int total10) {
        this.total10 = total10;
    }

    public void deleteOrderListCount(ArrayList<Integer> orderAreaPointList) {
        for (int i = 0; i < orderAreaPointList.size(); i++) {
            if (orderAreaPointList.contains(1)){
                total1--;
            }

            if (orderAreaPointList.contains(2)) {
                total2--;
            }

            if (orderAreaPointList.contains(3)) {
                total3--;
            }

            if (orderAreaPointList.contains(4)) {
                total4--;
            }

            if (orderAreaPointList.contains(5)) {
                total5--;
            }

            if (orderAreaPointList.contains(6)) {
                total6--;
            }

            if (orderAreaPointList.contains(7)) {
                total7--;
            }

            if (orderAreaPointList.contains(8)) {
                total8--;
            }

            if (orderAreaPointList.contains(9)) {
                total9--;
            }

            if (orderAreaPointList.contains(10)) {
                total10--;
            }
        }
    }

    public void updateAvaPointList(){
        ArrayList<Integer> avaPointList = new ArrayList<>();
        for (int i = 0; i < avaPointList.size(); i++) {
            if (avaPointList.contains(1)){
                avaPointList.add(1);
            }

            if (avaPointList.contains(2)) {
                avaPointList.add(2);
            }

            if (avaPointList.contains(3)) {
                avaPointList.add(3);
            }

            if (avaPointList.contains(4)) {
                avaPointList.add(4);
            }

            if (avaPointList.contains(5)) {
                avaPointList.add(5);
            }

            if (avaPointList.contains(6)) {
                avaPointList.add(6);
            }

            if (avaPointList.contains(7)) {
                avaPointList.add(7);
            }

            if (avaPointList.contains(8)) {
                avaPointList.add(8);
            }

            if (avaPointList.contains(9)) {
                avaPointList.add(9);
            }

            if (avaPointList.contains(10)) {
                avaPointList.add(10);
            }
        }
    }
}
