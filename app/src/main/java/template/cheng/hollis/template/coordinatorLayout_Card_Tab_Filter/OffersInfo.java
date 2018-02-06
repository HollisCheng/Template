package template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter;

import java.util.ArrayList;

public class OffersInfo {
    private int Type;
    private String Category;
    private String CategoryGA;//=EngTitle
    private String Description;
    private String Distance;
    private String EndDate;
    private ArrayList<IconList> IconList;
    private boolean IsBookmarked;
    private boolean IsClub;
    private boolean IsHTML;
    private boolean IsOfferUsed;
    private boolean IsShowWebButton;
    private int MerchantID;
    private String Merchant_Name;
    private String Merchant_PhotoPath;
    private String Merchant_Status;
    private String Name;
    private String OfferPIN;
    private String OfferRefNo;
    private String OfferStatus;
    private String PhotoPath;
    private String PhotoPathIn;
    private String PostDate;
    private int PrivilegeID;
    private String ReferralCode;
    private int RemainingDay;
    private int RemainingQuota;
    private ArrayList<ShopList> ShopList;
    private String StartDate;
    private String Status;
    private String TCLink;
    private int ViewCount;
    private String WebSite;
    private double XCoordinate;
    private double YCoordinate;
    private String NameGA;
    private int DisplaySeq;
    private String OpenTo;
    private String OpenWith;

    public OffersInfo() {
    }

    public OffersInfo(int type, String category, String categoryGA, String description, String distance, String endDate, ArrayList<template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.IconList> iconList, boolean isBookmarked, boolean isClub, boolean isHTML, boolean isOfferUsed, boolean isShowWebButton, int merchantID, String merchant_Name, String merchant_PhotoPath, String merchant_Status, String name, String offerPIN, String offerRefNo, String offerStatus, String photoPath, String photoPathIn, String postDate, int privilegeID, String referralCode, int remainingDay, int remainingQuota, ArrayList<template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.ShopList> shopList, String startDate, String status, String TCLink, int viewCount, String webSite, double XCoordinate, double YCoordinate, String nameGA, int displaySeq, String openTo, String openWith) {
        Type = type;
        Category = category;
        CategoryGA = categoryGA;
        Description = description;
        Distance = distance;
        EndDate = endDate;
        IconList = iconList;
        IsBookmarked = isBookmarked;
        IsClub = isClub;
        IsHTML = isHTML;
        IsOfferUsed = isOfferUsed;
        IsShowWebButton = isShowWebButton;
        MerchantID = merchantID;
        Merchant_Name = merchant_Name;
        Merchant_PhotoPath = merchant_PhotoPath;
        Merchant_Status = merchant_Status;
        Name = name;
        OfferPIN = offerPIN;
        OfferRefNo = offerRefNo;
        OfferStatus = offerStatus;
        PhotoPath = photoPath;
        PhotoPathIn = photoPathIn;
        PostDate = postDate;
        PrivilegeID = privilegeID;
        ReferralCode = referralCode;
        RemainingDay = remainingDay;
        RemainingQuota = remainingQuota;
        ShopList = shopList;
        StartDate = startDate;
        Status = status;
        this.TCLink = TCLink;
        ViewCount = viewCount;
        WebSite = webSite;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        NameGA = nameGA;
        DisplaySeq = displaySeq;
        OpenTo = openTo;
        OpenWith = openWith;
    }

    @Override
    public String toString() {
        return "OffersInfo{" +
                "Type=" + Type +
                ", Category='" + Category + '\'' +
                ", CategoryGA='" + CategoryGA + '\'' +
                ", Description='" + Description + '\'' +
                ", Distance='" + Distance + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", IconList=" + IconList +
                ", IsBookmarked=" + IsBookmarked +
                ", IsClub=" + IsClub +
                ", IsHTML=" + IsHTML +
                ", IsOfferUsed=" + IsOfferUsed +
                ", IsShowWebButton=" + IsShowWebButton +
                ", MerchantID=" + MerchantID +
                ", Merchant_Name='" + Merchant_Name + '\'' +
                ", Merchant_PhotoPath='" + Merchant_PhotoPath + '\'' +
                ", Merchant_Status='" + Merchant_Status + '\'' +
                ", Name='" + Name + '\'' +
                ", OfferPIN='" + OfferPIN + '\'' +
                ", OfferRefNo='" + OfferRefNo + '\'' +
                ", OfferStatus='" + OfferStatus + '\'' +
                ", PhotoPath='" + PhotoPath + '\'' +
                ", PhotoPathIn='" + PhotoPathIn + '\'' +
                ", PostDate='" + PostDate + '\'' +
                ", PrivilegeID=" + PrivilegeID +
                ", ReferralCode='" + ReferralCode + '\'' +
                ", RemainingDay=" + RemainingDay +
                ", RemainingQuota=" + RemainingQuota +
                ", ShopList=" + ShopList +
                ", StartDate='" + StartDate + '\'' +
                ", Status='" + Status + '\'' +
                ", TCLink='" + TCLink + '\'' +
                ", ViewCount=" + ViewCount +
                ", WebSite='" + WebSite + '\'' +
                ", XCoordinate=" + XCoordinate +
                ", YCoordinate=" + YCoordinate +
                ", NameGA='" + NameGA + '\'' +
                ", DisplaySeq=" + DisplaySeq +
                ", OpenTo='" + OpenTo + '\'' +
                ", OpenWith='" + OpenWith + '\'' +
                '}';
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCategoryGA() {
        return CategoryGA;
    }

    public void setCategoryGA(String categoryGA) {
        CategoryGA = categoryGA;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public ArrayList<template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.IconList> getIconList() {
        return IconList;
    }

    public void setIconList(ArrayList<template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.IconList> iconList) {
        IconList = iconList;
    }

    public boolean isBookmarked() {
        return IsBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        IsBookmarked = bookmarked;
    }

    public boolean isClub() {
        return IsClub;
    }

    public void setClub(boolean club) {
        IsClub = club;
    }

    public boolean isHTML() {
        return IsHTML;
    }

    public void setHTML(boolean HTML) {
        IsHTML = HTML;
    }

    public boolean isOfferUsed() {
        return IsOfferUsed;
    }

    public void setOfferUsed(boolean offerUsed) {
        IsOfferUsed = offerUsed;
    }

    public boolean isShowWebButton() {
        return IsShowWebButton;
    }

    public void setShowWebButton(boolean showWebButton) {
        IsShowWebButton = showWebButton;
    }

    public int getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(int merchantID) {
        MerchantID = merchantID;
    }

    public String getMerchant_Name() {
        return Merchant_Name;
    }

    public void setMerchant_Name(String merchant_Name) {
        Merchant_Name = merchant_Name;
    }

    public String getMerchant_PhotoPath() {
        return Merchant_PhotoPath;
    }

    public void setMerchant_PhotoPath(String merchant_PhotoPath) {
        Merchant_PhotoPath = merchant_PhotoPath;
    }

    public String getMerchant_Status() {
        return Merchant_Status;
    }

    public void setMerchant_Status(String merchant_Status) {
        Merchant_Status = merchant_Status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOfferPIN() {
        return OfferPIN;
    }

    public void setOfferPIN(String offerPIN) {
        OfferPIN = offerPIN;
    }

    public String getOfferRefNo() {
        return OfferRefNo;
    }

    public void setOfferRefNo(String offerRefNo) {
        OfferRefNo = offerRefNo;
    }

    public String getOfferStatus() {
        return OfferStatus;
    }

    public void setOfferStatus(String offerStatus) {
        OfferStatus = offerStatus;
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

    public String getPostDate() {
        return PostDate;
    }

    public void setPostDate(String postDate) {
        PostDate = postDate;
    }

    public int getPrivilegeID() {
        return PrivilegeID;
    }

    public void setPrivilegeID(int privilegeID) {
        PrivilegeID = privilegeID;
    }

    public String getReferralCode() {
        return ReferralCode;
    }

    public void setReferralCode(String referralCode) {
        ReferralCode = referralCode;
    }

    public int getRemainingDay() {
        return RemainingDay;
    }

    public void setRemainingDay(int remainingDay) {
        RemainingDay = remainingDay;
    }

    public int getRemainingQuota() {
        return RemainingQuota;
    }

    public void setRemainingQuota(int remainingQuota) {
        RemainingQuota = remainingQuota;
    }

    public ArrayList<template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.ShopList> getShopList() {
        return ShopList;
    }

    public void setShopList(ArrayList<template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter.ShopList> shopList) {
        ShopList = shopList;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
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

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int viewCount) {
        ViewCount = viewCount;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
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

    public String getNameGA() {
        return NameGA;
    }

    public void setNameGA(String nameGA) {
        NameGA = nameGA;
    }

    public int getDisplaySeq() {
        return DisplaySeq;
    }

    public void setDisplaySeq(int displaySeq) {
        DisplaySeq = displaySeq;
    }

    public String getOpenTo() {
        return OpenTo;
    }

    public void setOpenTo(String openTo) {
        OpenTo = openTo;
    }

    public String getOpenWith() {
        return OpenWith;
    }

    public void setOpenWith(String openWith) {
        OpenWith = openWith;
    }
}
