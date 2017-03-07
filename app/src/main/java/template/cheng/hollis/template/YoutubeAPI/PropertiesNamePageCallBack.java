package template.cheng.hollis.template.YoutubeAPI;

import android.content.Context;

public class PropertiesNamePageCallBack {
    private Context cxt;
    private String SelectText;
    private MyInterface myInterface;

    public PropertiesNamePageCallBack() {
    }

    public PropertiesNamePageCallBack(Context cxt, MyInterface myInterface) {
        this.cxt = cxt;
        this.myInterface = myInterface;
    }

    public Context getCxt() {
        return cxt;
    }

    public void setCxt(Context cxt) {
        this.cxt = cxt;
    }

    public String getSelectText() {
        return SelectText;
    }

    public void setSelectText(String selectText) {
        SelectText = selectText;
    }

    public MyInterface getMyInterface() {
        return myInterface;
    }

    public void setMyInterface(MyInterface myInterface) {
        this.myInterface = myInterface;
    }
}

