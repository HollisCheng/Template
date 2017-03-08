package template.cheng.hollis.template.ObjectInfo;

public class ShoppingItemInfo {

    private int HotShoppingID;
    private String Description;
    private String PhotoPath;
    private String Title;
    private String Type;
    private String UnitPrice;
    private String WebSite;

    public ShoppingItemInfo() {
    }

    public ShoppingItemInfo(int hotShoppingID, String description, String photoPath, String title, String type, String unitPrice, String webSite) {
        HotShoppingID = hotShoppingID;
        Description = description;
        PhotoPath = photoPath;
        Title = title;
        Type = type;
        UnitPrice = unitPrice;
        WebSite = webSite;
    }

    @Override
    public String toString() {
        return "ShoppingItemInfo{" +
                "HotShoppingID=" + HotShoppingID +
                ", Description='" + Description + '\'' +
                ", PhotoPath='" + PhotoPath + '\'' +
                ", Title='" + Title + '\'' +
                ", Type='" + Type + '\'' +
                ", UnitPrice='" + UnitPrice + '\'' +
                ", WebSite='" + WebSite + '\'' +
                '}';
    }

    public int getHotShoppingID() {
        return HotShoppingID;
    }

    public void setHotShoppingID(int hotShoppingID) {
        HotShoppingID = hotShoppingID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
    }
}
