package template.cheng.hollis.template;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import template.cheng.hollis.template.ObjectInfo.ShoppingItemInfo;
import template.cheng.hollis.template.TextView.SweetSansBoldTextView;

public class ShoppingAdapter extends RecyclerView.Adapter<ViewHolder0> {
    private ArrayList<ShoppingItemInfo> info;
    private Context mContext;
    private Activity act;

    public ShoppingAdapter(Context context, ArrayList<ShoppingItemInfo> info, Activity act) {
        this.info = info;
        this.mContext = context;
        this.act = act;
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    @Override
    public ViewHolder0 onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.happens_shopping_items, parent, false);

        return new ViewHolder0(itemView);

    }

    @Override
    public void onBindViewHolder(final ViewHolder0 holder, final int position) {


        Glide.with(mContext).load(info.get(position).getPhotoPath())
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.img_card_demo)
                .centerCrop()
                .into(holder.srivImage);

//                    imageLoader.displayImage(info.get(position).getBackgroundPath(), holder.srivImage);
//                }

        //Logo
//                if (info.get(position).getMerchant_PhotoPath().equals("")) {
//                    holder.offersLogo.setImageResource(R.drawable.img_card_demo_logo);
//                } else {

//                Glide.with(mContext).load(info.get(position).getMerchant_PhotoPath())
////                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .error(R.drawable.img_card_demo_logo)
//                        .centerCrop()
//                        .into(holder.offersLogo);

//                    imageLoader.displayImage(info.get(position).getBackgroundPath(), holder.srivImage);
//                }

        holder.tvOfferTitle.setText(info.get(position).getTitle());

        holder.tvShopNAME.setText(info.get(position).getDescription());

//                holder.tvViewCount.setText(info.get(position).getViewCount() + "");

//                holder.tvDaysLeft.setText(info.get(position).getEndDate());
//                if (Utility.dayLeft(info.get(position).getEndDate()) > 100) {
//                    holder.tvDaysLeft.setVisibility(View.GONE);
//                    holder.llBottomText.setWeightSum(2);
//                } else {
//                    holder.llBottomText.setWeightSum(3);
//                    holder.tvDaysLeft.setVisibility(View.VISIBLE);
//                    holder.tvDaysLeft.setText(Utility.dayLeft(info.get(position).getEndDate()) + " " + mContext.getResources().getString(R.string.DL));
//                }

//                if (info.get(position).getRemainingDay() > 100) {
//                    holder.tvDaysLeft.setVisibility(View.GONE);
//                    holder.llBottomText.setWeightSum(2);
//                } else {
//                    holder.llBottomText.setWeightSum(3);
//                    holder.tvDaysLeft.setVisibility(View.VISIBLE);
//                    holder.tvDaysLeft.setText(info.get(position).getRemainingDay() + " " + mContext.getResources().getString(R.string.DL));
//                }
//
//                if (info.get(position).getIconList().size() > 0) {
//                    holder.TVOfferSide.setText(info.get(position).getIconList().get(0).getName());
//                    holder.TVOfferSide.setVisibility(View.VISIBLE);
//                } else {
//                    holder.TVOfferSide.setVisibility(View.INVISIBLE);
//                }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.w("MOA", "MyOffersAdapter:holder.cardView:OnClickPosition =" + position);
//
//                Tracker t = Utility.mTracker;
//                t.setScreenName("Privileges-" + info.get(position).getCategory());
//                t.send(new HitBuilders.ScreenViewBuilder().build());
//
//                Utility.mTracker.send(new HitBuilders.EventBuilder()
//                        .setCategory("Privileges")
//                        .setAction("ViewDetail")
//                        .setLabel(info.get(position).getCategoryGA() + "")
//                        .setValue(100)
//                        .build());
//
//                try {
//                    JSONObject jsonObj = new JSONObject();
//                    jsonObj.put("PrivilegeID", info.get(position).getPrivilegeID());
//                    if (info.get(position).isClub())
//                        jsonObj.put("Type", info.get(position).getType());
//                    JSONObject privilege = new JSONObject();
//                    privilege.put("privilege", jsonObj);
//                    Utility.mQueue.add(Utility.VolleyPOST(Utility.ipURL + "CreatePrivilegeRead", privilege));
//                } catch (Exception e) {
//                    //Log.w("VolleyJson", e.toString());
//                }
//                Utility.PrintLog(getClass().getName(), "OpenTo=" + info.get(position).getOpenTo().toLowerCase() + ",OpenWith=" + info.get(position).getOpenWith());
//                boolean IsPDF = false, IsWeb = false, IsScheme = false, IsStaff = false, IsImage = false;
//                if (info.get(position).getOpenTo().toLowerCase().equals("app")) {
//                    IsScheme = true;
//                } else if (info.get(position).getOpenTo().toLowerCase().equals("pdf")) {
//                    IsPDF = true;
//                } else if (info.get(position).getOpenTo().toLowerCase().equals("web")) {
//                    IsWeb = true;
//                } else if (info.get(position).getOpenTo().toLowerCase().equals("staffarea")) {
//                    IsStaff = true;
//                } else if (info.get(position).getOpenTo().toLowerCase().equals("image")) {
//                    IsImage = true;
//                }
//
//                if (IsPDF) {
//                    Intent intent = new Intent(mContext, WebViewClientPage.class);
////                            intent.putExtra("LINK", "https://drive.google.com/viewerng/viewer?embedded=true&url=" + "https://gradcollege.okstate.edu/sites/default/files/PDF_linking.pdf");
////                            intent.putExtra("LINK", "https://drive.google.com/viewerng/viewer?embedded=true&url=" + info.get(position).getOpenWith());
//                    intent.putExtra("LINK", info.get(position).getOpenWith());
//                    intent.putExtra("Title", info.get(position).getName());
//                    mContext.startActivity(intent);
//                } else if (IsWeb) {
//                    Intent intent = new Intent(mContext, WebViewClientPage.class);
////                            intent.putExtra("LINK", "http://www.google.com.hk");
//                    intent.putExtra("IsShowWebButton", info.get(position).isShowWebButton());
//                    intent.putExtra("LINK", info.get(position).getOpenWith());
//                    intent.putExtra("Title", info.get(position).getName());
//                    mContext.startActivity(intent);
//                } else if (IsScheme) {
////                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100010747800544"));
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(info.get(position).getOpenWith()));
//                    mContext.startActivity(intent);
//                } else if (IsStaff) {
//                    Intent intent = new Intent(mContext, StaffAreaActivity.class);
//                    mContext.startActivity(intent);
//                } else if (IsImage) {
//                    Utility.OfferImagePopUpDialog(mContext, act, info.get(position).getOpenWith());
//                } else {
//                    Utility.selectedOffer = info.get(position);
//                    Utility.SelectedIsClub = info.get(position).isClub();
//                    if (info.get(position).getIconList().size() > 0) {
//                        Utility.CardIDCode = info.get(position).getIconList().get(0).getCode();
////                        Log.w("VolleyJson", " Utility.selectedOffer =" + info.get(position) + "," +
////                                "Utility.SelectedIsClub =" + info.get(position).isClub() + "," +
////                                "Utility.CardIDCode =" + info.get(position).getIconListAL().get(0).getCode());
//                        Utility.mTracker.send(new HitBuilders.EventBuilder()
//                                .setCategory("Privileges-" + Utility.selectedOffer.getIconList().get(0).getCode() + "-" + Utility.selectedOffer.getCategoryGA())
//                                .setAction("Click")
//                                .setLabel(info.get(position).getCategoryGA())
//                                .setValue(100)
//                                .build());
//                    }
//                    Intent intent = new Intent(mContext, OffersActivity.class);
////                        intent.putExtra("ResidentID", Utility.LocalResidentID);
//                    intent.putExtra("IsMe", 1);
//                    mContext.startActivity(intent);
//                }

                //TODO need to activate this below API CreateHotShoppingRead
//                try {
//                    JSONObject jsonObj = new JSONObject();
//                    jsonObj.put("HotShoppingID", info.get(position).getHotShoppingID());
//                    JSONObject privilege = new JSONObject();
//                    privilege.put("shop", jsonObj);
//                    Utility.mQueue.add(Utility.VolleyPOST(Utility.ipURL + "CreateHotShoppingRead", privilege));
//                } catch (Exception e) {
//                    Log.w("ShoppingAdapter", e.toString());
//                }
            }
        });


    }
}

class ViewHolder0 extends RecyclerView.ViewHolder {

    RelativeLayout cardView;
    ImageView srivImage;
    SweetSansBoldTextView tvOfferTitle;
    TextView tvShopNAME;

    public ViewHolder0(View view) {
        super(view);
        cardView = (RelativeLayout) view.findViewById(R.id.cardView);
        srivImage = (ImageView) view.findViewById(R.id.srivImage);
        tvOfferTitle = (SweetSansBoldTextView) view.findViewById(R.id.tvOfferTitle);
        tvShopNAME = (TextView) view.findViewById(R.id.tvShopNAME);
    }
}
