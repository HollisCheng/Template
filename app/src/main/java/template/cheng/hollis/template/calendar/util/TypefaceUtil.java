package template.cheng.hollis.template.Calendar.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class TypefaceUtil {

    /**
     * Method that gets a Typeface from a directory.
     *
     * @param context    - the context where this method is going to invoke.
     * @param typefaceId - the id of the typeface path to get.
     * @return a typeface object.
     */
    public static Typeface getCustomTypeface(@NonNull final Context context, @StringRes int typefaceId) {
        return Typeface.createFromAsset(context.getAssets(), context.getString(typefaceId));
    }
}