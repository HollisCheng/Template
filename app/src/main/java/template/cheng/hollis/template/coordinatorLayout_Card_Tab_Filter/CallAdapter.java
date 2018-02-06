package template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import template.cheng.hollis.template.R;

public class CallAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    private int positionSelected;

    public CallAdapter(Context context, ArrayList<String> values, int positionSelected) {
        super(context, R.layout.call_alert_dialog, values);
        this.context = context;
        this.values = values;
        this.positionSelected = positionSelected;
    }

    public CallAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.call_alert_dialog, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.call_alert_dialog, parent, false);
//        AutoResizeTextView autoResizeTextView = (AutoResizeTextView) rowView.findViewById(R.id.ARTV);
        TextView ARTV = (TextView) rowView.findViewById(R.id.ARTV);

        ARTV.setText(values.get(position));

//        Log.w("CA", "positionSelected=" + positionSelected + ",position=" + position+",values="+values.toString());
        if (positionSelected != 0) {
            if (position == positionSelected - 1)
                ARTV.setBackgroundColor(context.getResources().getColor(R.color.SelectedColor));
        }
        return rowView;
    }
}