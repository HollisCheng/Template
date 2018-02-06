package template.cheng.hollis.template.coordinatorLayout_Card_Tab_Filter;

public class IconList {
    private String CardNo;
    private String Code;
    private String Name;
    private String NameOnCard;

    public IconList() {
    }

    public IconList(String cardNo, String code, String name, String nameOnCard) {
        CardNo = cardNo;
        Code = code;
        Name = name;
        NameOnCard = nameOnCard;
    }

    @Override
    public String toString() {
        return "IconList{" +
                "CardNo='" + CardNo + '\'' +
                ", Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", NameOnCard='" + NameOnCard + '\'' +
                '}';
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameOnCard() {
        return NameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        NameOnCard = nameOnCard;
    }
}

