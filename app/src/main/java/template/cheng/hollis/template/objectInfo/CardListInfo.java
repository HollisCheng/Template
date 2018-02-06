package template.cheng.hollis.template.objectInfo;

public class CardListInfo {
    private String CardNo;
    private String CardType;
    private String Name;
    private String UserName;

    public CardListInfo() {
    }

    public CardListInfo(String cardNo, String cardType, String name, String userName) {
        CardNo = cardNo;
        CardType = cardType;
        Name = name;
        UserName = userName;
    }

    @Override
    public String toString() {
        return "CardListInfo{" +
                "CardNo='" + CardNo + '\'' +
                ", CardType='" + CardType + '\'' +
                ", Name='" + Name + '\'' +
                ", UserName='" + UserName + '\'' +
                '}';
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
