package template.cheng.hollis.template;

/**
 * Created by hollischeng on 21/7/2017.
 */

public class FingerPrintBus {
    public final String message;
    public boolean IsCorrectFingerPrint;

    public FingerPrintBus(String message, boolean IsCorrectFingerPrint) {
        this.message = message;
        this.IsCorrectFingerPrint = IsCorrectFingerPrint;
    }

}
