package template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter;

import java.util.ArrayList;

public class OffersInfo {
    private int Type;
    private int PrivilegeID;
    private int MerchantID;
    private int ViewCount;
    private boolean IsBookmarked;
    private ArrayList<IconList> iconListAL;
    private ArrayList<ShopList> ShopListAL;
    private String Name;
    private String Merchant_PhotoPath;
    private String PhotoPath;
    private String PhotoPathIn;
    private String StartDate;
    private String EndDate;
    private String OfferRefNo;
    private String Merchant_Name;
    private String Merchant_Status;
    private String Status;
    private String TCLink;
    private boolean IsClub;
    private String Desc;
    private String WebSite;
    private String Category;
    private boolean IsOfferUsed;
    private String OfferPIN;
    private String EngTitle;
    private String OfferStatus;
    private int RemainingQuota;
    private String ReferralCode;

    public OffersInfo() {
    }

    public OffersInfo(int type, int privilegeID, int merchantID, int viewCount, boolean isBookmarked, ArrayList<IconList> iconListAL, ArrayList<ShopList> shopListAL, String name, String merchant_PhotoPath, String photoPath, String photoPathIn, String startDate, String endDate, String offerRefNo, String merchant_Name, String merchant_Status, String status, String TCLink, boolean isClub, String desc, String webSite, String category, boolean isOfferUsed, String offerPIN, String engTitle, String offerStatus, int remainingQuota, String referralCode) {
        Type = type;
        PrivilegeID = privilegeID;
        MerchantID = merchantID;
        ViewCount = viewCount;
        IsBookmarked = isBookmarked;
        this.iconListAL = iconListAL;
        ShopListAL = shopListAL;
        Name = name;
        Merchant_PhotoPath = merchant_PhotoPath;
        PhotoPath = photoPath;
        PhotoPathIn = photoPathIn;
        StartDate = startDate;
        EndDate = endDate;
        OfferRefNo = offerRefNo;
        Merchant_Name = merchant_Name;
        Merchant_Status = merchant_Status;
        Status = status;
        this.TCLink = TCLink;
        IsClub = isClub;
        Desc = desc;
        WebSite = webSite;
        Category = category;
        IsOfferUsed = isOfferUsed;
        OfferPIN = offerPIN;
        EngTitle = engTitle;
        OfferStatus = offerStatus;
        RemainingQuota = remainingQuota;
        ReferralCode = referralCode;
    }

    @Override
    public String toString() {
        return "OffersInfo{" +
                "Type=" + Type +
                ", PrivilegeID=" + PrivilegeID +
                ", MerchantID=" + MerchantID +
                ", ViewCount=" + ViewCount +
                ", IsBookmarked=" + IsBookmarked +
                ", iconListAL=" + iconListAL +
                ", ShopListAL=" + ShopListAL +
                ", Name='" + Name + '\'' +
                ", Merchant_PhotoPath='" + Merchant_PhotoPath + '\'' +
                ", PhotoPath='" + PhotoPath + '\'' +
                ", PhotoPathIn='" + PhotoPathIn + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", OfferRefNo='" + OfferRefNo + '\'' +
                ", Merchant_Name='" + Merchant_Name + '\'' +
                ", Merchant_Status='" + Merchant_Status + '\'' +
                ", Status='" + Status + '\'' +
                ", TCLink='" + TCLink + '\'' +
                ", IsClub=" + IsClub +
                ", Desc='" + Desc + '\'' +
                ", WebSite='" + WebSite + '\'' +
                ", Category='" + Category + '\'' +
                ", IsOfferUsed=" + IsOfferUsed +
                ", OfferPIN='" + OfferPIN + '\'' +
                ", EngTitle='" + EngTitle + '\'' +
                ", OfferStatus='" + OfferStatus + '\'' +
                ", RemainingQuota=" + RemainingQuota +
                ", ReferralCode='" + ReferralCode + '\'' +
                '}';
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getPrivilegeID() {
        return PrivilegeID;
    }

    public void setPrivilegeID(int privilegeID) {
        PrivilegeID = privilegeID;
    }

    public int getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(int merchantID) {
        MerchantID = merchantID;
    }

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int viewCount) {
        ViewCount = viewCount;
    }

    public boolean isBookmarked() {
        return IsBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        IsBookmarked = bookmarked;
    }

    public ArrayList<IconList> getIconListAL() {
        return iconListAL;
    }

    public void setIconListAL(ArrayList<IconList> iconListAL) {
        this.iconListAL = iconListAL;
    }

    public ArrayList<ShopList> getShopListAL() {
        return ShopListAL;
    }

    public void setShopListAL(ArrayList<ShopList> shopListAL) {
        ShopListAL = shopListAL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMerchant_PhotoPath() {
        return Merchant_PhotoPath;
    }

    public void setMerchant_PhotoPath(String merchant_PhotoPath) {
        Merchant_PhotoPath = merchant_PhotoPath;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public String getPhotoPathIn() {
        return PhotoPathIn;
    }

    public void setPhotoPathIn(String photoPathIn) {
        PhotoPathIn = photoPathIn;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getOfferRefNo() {
        return OfferRefNo;
    }

    public void setOfferRefNo(String offerRefNo) {
        OfferRefNo = offerRefNo;
    }

    public String getMerchant_Name() {
        return Merchant_Name;
    }

    public void setMerchant_Name(String merchant_Name) {
        Merchant_Name = merchant_Name;
    }

    public String getMerchant_Status() {
        return Merchant_Status;
    }

    public void setMerchant_Status(String merchant_Status) {
        Merchant_Status = merchant_Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTCLink() {
        return TCLink;
    }

    public void setTCLink(String TCLink) {
        this.TCLink = TCLink;
    }

    public boolean isClub() {
        return IsClub;
    }

    public void setClub(boolean club) {
        IsClub = club;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isOfferUsed() {
        return IsOfferUsed;
    }

    public void setOfferUsed(boolean offerUsed) {
        IsOfferUsed = offerUsed;
    }

    public String getOfferPIN() {
        return OfferPIN;
    }

    public void setOfferPIN(String offerPIN) {
        OfferPIN = offerPIN;
    }

    public String getEngTitle() {
        return EngTitle;
    }

    public void setEngTitle(String engTitle) {
        EngTitle = engTitle;
    }

    public String getOfferStatus() {
        return OfferStatus;
    }

    public void setOfferStatus(String offerStatus) {
        OfferStatus = offerStatus;
    }

    public int getRemainingQuota() {
        return RemainingQuota;
    }

    public void setRemainingQuota(int remainingQuota) {
        RemainingQuota = remainingQuota;
    }

    public String getReferralCode() {
        return ReferralCode;
    }

    public void setReferralCode(String referralCode) {
        ReferralCode = referralCode;
    }
}
