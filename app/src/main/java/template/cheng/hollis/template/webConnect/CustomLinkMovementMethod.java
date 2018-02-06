package template.cheng.hollis.template.webConnect;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;

public class CustomLinkMovementMethod extends LinkMovementMethod {
    private static Context movementContext;
    private static CustomLinkMovementMethod linkMovementMethod = new CustomLinkMovementMethod();

    public boolean onTouchEvent(android.widget.TextView widget, android.text.Spannable buffer, android.view.MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            URLSpan[] link = buffer.getSpans(off, off, URLSpan.class);
            if (link.length != 0) {
                String url = link[0].getURL();
                if (url.contains("https")) {
                    Log.d("Link", url);
//                    Toast.makeText(movementContext, "https Link was clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(movementContext.getApplicationContext(), WebViewClientPage.class);
                    intent.putExtra("LINK", url);
                    intent.putExtra("Title", "");
                    movementContext.startActivity(intent);
                } else if (url.contains("tel")) {
//                    Log.d("Link", url);
//                    Toast.makeText(movementContext, "Tel was clicked", Toast.LENGTH_LONG).show();
                } else if (url.contains("mailto")) {
//                    Log.d("Link", url);
//                    Toast.makeText(movementContext, "Mail link was clicked", Toast.LENGTH_LONG).show();
                } else if (url.contains("http")) {
                    Log.d("Link", url);
//                    Toast.makeText(movementContext, "http Link was clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(movementContext.getApplicationContext(), WebViewClientPage.class);
                    intent.putExtra("LINK", url);
                    intent.putExtra("Title", "");
                    movementContext.startActivity(intent);
                } else if (url.contains("www")) {
                    Log.d("Link", url);
//                    Toast.makeText(movementContext, "www Link was clicked", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(movementContext.getApplicationContext(), WebViewClientPage.class);
//                    intent.putExtra("LINK", url);
//                    movementContext.startActivity(intent);
                }
                return true;
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }

    public static android.text.method.MovementMethod getInstance(Context c) {
        movementContext = c;
        return linkMovementMethod;
    }
}