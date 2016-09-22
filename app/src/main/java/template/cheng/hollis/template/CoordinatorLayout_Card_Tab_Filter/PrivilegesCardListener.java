package template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter;

public class PrivilegesCardListener {

    private static ViewPagerCardListener VPSListener = null;

    public static ViewPagerCardListener getViewPagerCardListener() {
        return VPSListener;
    }

    public static void setViewPagerCardListener(ViewPagerCardListener VPSelListener) {
        VPSListener = VPSelListener;
    }

    public interface ViewPagerCardListener {
        void eventOccurred(int goToPage);
    }
}
