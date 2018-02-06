package template.cheng.hollis.template.testBundlePage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import template.cheng.hollis.template.R;
import template.cheng.hollis.template.textView.SweetSansRegTextView;

public class MeNoticePagerAdapter extends PagerAdapter {

    private Context _context;
    private LayoutInflater _inflater;
    private ArrayList<MsgInfo> _PropertyNotice = new ArrayList<>();

    public MeNoticePagerAdapter(Context context, ArrayList<MsgInfo> PropertyNotice) {
        _context = context;
        _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _PropertyNotice = PropertyNotice;
    }

    @Override
    public int getCount() {
        return _PropertyNotice.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = _inflater.inflate(R.layout.me_property_notice_layout, container, false);
        container.addView(itemView);

        LinearLayout llWholeNotice = (LinearLayout) itemView.findViewById(R.id.llWholeNotice);
        SweetSansRegTextView tvNoticeName = (SweetSansRegTextView) itemView.findViewById(R.id.tvNoticeName);
        TextView tvDesc = (TextView) itemView.findViewById(R.id.tvPropertyNoticeDesc);

        tvNoticeName.setText(_PropertyNotice.get(position).getNoticeName());
        tvDesc.setText(_PropertyNotice.get(position).getDesc());

        llWholeNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}