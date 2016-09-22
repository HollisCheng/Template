package template.cheng.hollis.template.TestBundlePage;

import java.util.ArrayList;

public class MsgInfo {
    private int NoticeID;
    private String Type;
    private String NoticeTitle;
    private String NoticeName;
    private String Category;
    private String DateTo;
    private String DateFrom;
    private String IconPath;
    private String Desc;
    private String RefNo;
    private boolean IsRead;
    private ArrayList<String> FileList;

    public MsgInfo() {
    }

    public MsgInfo(int noticeID, String type, String noticeTitle, String noticeName, String category, String dateTo, String dateFrom, String iconPath, String desc, String refNo, boolean isRead, ArrayList<String> fileList) {
        NoticeID = noticeID;
        Type = type;
        NoticeTitle = noticeTitle;
        NoticeName = noticeName;
        Category = category;
        DateTo = dateTo;
        DateFrom = dateFrom;
        IconPath = iconPath;
        Desc = desc;
        RefNo = refNo;
        IsRead = isRead;
        FileList = fileList;
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "NoticeID=" + NoticeID +
                ", Type='" + Type + '\'' +
                ", NoticeTitle='" + NoticeTitle + '\'' +
                ", NoticeName='" + NoticeName + '\'' +
                ", Category='" + Category + '\'' +
                ", DateTo='" + DateTo + '\'' +
                ", DateFrom='" + DateFrom + '\'' +
                ", IconPath='" + IconPath + '\'' +
                ", Desc='" + Desc + '\'' +
                ", RefNo='" + RefNo + '\'' +
                ", IsRead=" + IsRead +
                ", FileList=" + FileList +
                '}';
    }

    public int getNoticeID() {
        return NoticeID;
    }

    public void setNoticeID(int noticeID) {
        NoticeID = noticeID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNoticeTitle() {
        return NoticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        NoticeTitle = noticeTitle;
    }

    public String getNoticeName() {
        return NoticeName;
    }

    public void setNoticeName(String noticeName) {
        NoticeName = noticeName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getIconPath() {
        return IconPath;
    }

    public void setIconPath(String iconPath) {
        IconPath = iconPath;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public boolean isRead() {
        return IsRead;
    }

    public void setIsRead(boolean isRead) {
        IsRead = isRead;
    }

    public ArrayList<String> getFileList() {
        return FileList;
    }

    public void setFileList(ArrayList<String> fileList) {
        FileList = fileList;
    }
}
