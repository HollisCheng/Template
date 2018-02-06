package template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter;

public class ShopList {
    private String Address;
    private String Name;
    private String Phone;
    private int ShopID;
    private double XCoordinate;
    private double YCoordinate;

    public ShopList() {
    }

    public ShopList(String address, String name, String phone, int shopID, double XCoordinate, double YCoordinate) {
        Address = address;
        Name = name;
        Phone = phone;
        ShopID = shopID;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    @Override
    public String toString() {
        return "ShopList{" +
                "Address='" + Address + '\'' +
                ", Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", ShopID='" + ShopID + '\'' +
                ", XCoordinate=" + XCoordinate +
                ", YCoordinate=" + YCoordinate +
                '}';
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getShopID() {
        return ShopID;
    }

    public void setShopID(int shopID) {
        ShopID = shopID;
    }

    public double getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(double XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public double getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(double YCoordinate) {
        this.YCoordinate = YCoordinate;
    }
}
