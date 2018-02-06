package template.cheng.hollis.template.objectInfo;

import java.util.ArrayList;

public class KeyWordsInfo {
    private String EstateCode;
    private int EstateID;
    private String NameEng;
    private String NameSChi;
    private String NameTChi;
    private ArrayList<String> EstateKeywordList;

    public KeyWordsInfo(String estateCode, int estateID, String nameEng, String nameSChi, String nameTChi, ArrayList<String> estateKeywordList) {
        EstateCode = estateCode;
        EstateID = estateID;
        NameEng = nameEng;
        NameSChi = nameSChi;
        NameTChi = nameTChi;
        EstateKeywordList = estateKeywordList;
    }

    @Override
    public String toString() {
        return "KeyWordsInfo{" +
                "EstateCode='" + EstateCode + '\'' +
                ", EstateID=" + EstateID +
                ", NameEng='" + NameEng + '\'' +
                ", NameSChi='" + NameSChi + '\'' +
                ", NameTChi='" + NameTChi + '\'' +
                ", EstateKeywordList=" + EstateKeywordList +
                '}';
    }

    public String getEstateCode() {
        return EstateCode;
    }

    public void setEstateCode(String estateCode) {
        EstateCode = estateCode;
    }

    public int getEstateID() {
        return EstateID;
    }

    public void setEstateID(int estateID) {
        EstateID = estateID;
    }

    public String getNameEng() {
        return NameEng;
    }

    public void setNameEng(String nameEng) {
        NameEng = nameEng;
    }

    public String getNameSChi() {
        return NameSChi;
    }

    public void setNameSChi(String nameSChi) {
        NameSChi = nameSChi;
    }

    public String getNameTChi() {
        return NameTChi;
    }

    public void setNameTChi(String nameTChi) {
        NameTChi = nameTChi;
    }

    public ArrayList<String> getEstateKeywordList() {
        return EstateKeywordList;
    }

    public void setEstateKeywordList(ArrayList<String> estateKeywordList) {
        EstateKeywordList = estateKeywordList;
    }
}
