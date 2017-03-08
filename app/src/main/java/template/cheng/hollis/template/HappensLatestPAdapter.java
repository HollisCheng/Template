package template.cheng.hollis.template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONObject;

import java.util.ArrayList;

import template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter.OffersInfo;
import template.cheng.hollis.template.TextView.SweetSansBoldTextView;
import template.cheng.hollis.template.WebConnect.WebViewClientPage;

public class HappensLatestPAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<OffersInfo> info;
    private Context mContext;
    private Activity act;

    public HappensLatestPAdapter(Context context, ArrayList<OffersInfo> info, Activity act) {
        this.info = info;
        this.mContext = context;
        this.act = act;
//        mQueue = Volley.newRequestQueue(context);
//        imageLoader = new ImageLoader(mContext.getApplicationContext());
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (info.get(position).getType() == 0) {
            return 0;
        } else if (info.get(position).getType() == 1) {
            return 1;
        } else if (info.get(position).getType() == 9) {
            return 9;
        } else {
            //Log.w("MOA","MyOffersAdapter:getItemViewType:error");
            return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
//            case 0:
//                View v1 = inflater.inflate(R.layout.offer_view_1st_item, parent, false);
////                View view = View.inflate(parent.getContext(), R.layout.card_view_items, null);
//                viewHolder = new ViewHolder0(v1);
//                break;
            case 1:
                View v2 = inflater.inflate(R.layout.happens_privileges_items, parent, false);
//                View view1 = View.inflate(parent.getContext(), R.layout.offer_view_1st_item, null);
                viewHolder = new ViewHolder1(v2);
                break;

            default:
                View viewD = View.inflate(parent.getContext(), R.layout.card_view_items, null);
                viewHolder = new ViewHolder1(viewD);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            //region case0
//            case 0:
//                final ViewHolder0 vh0 = (ViewHolder0) holder;

                //endregion

                //region case 1
            case 1:
                final ViewHolder1 vh1 = (ViewHolder1) holder;

//                vh1.cardView.setPreventCornerOverlap(false);

                //Background Image
//                if (info.get(position).getPhotoPath().equals("")) {
//                    vh1.srivImage.setImageResource(R.drawable.img_card_demo);
//                } else {

                Glide.with(mContext).load(info.get(position).getPhotoPath())
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.img_card_demo)
                        .centerCrop()
                        .into(vh1.srivImage);

//                    imageLoader.displayImage(info.get(position).getBackgroundPath(), vh1.srivImage);
//                }

                //Logo
//                if (info.get(position).getMerchant_PhotoPath().equals("")) {
//                    vh1.offersLogo.setImageResource(R.drawable.img_card_demo_logo);
//                } else {

//                Glide.with(mContext).load(info.get(position).getMerchant_PhotoPath())
////                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .error(R.drawable.img_card_demo_logo)
//                        .centerCrop()
//                        .into(vh1.offersLogo);

//                    imageLoader.displayImage(info.get(position).getBackgroundPath(), vh1.srivImage);
//                }

                vh1.tvOfferTitle.setText(info.get(position).getName());

                vh1.tvShopNAME.setText(info.get(position).getMerchant_Name());

                vh1.tvViewCount.setText(info.get(position).getViewCount() + "");

//                vh1.tvDaysLeft.setText(info.get(position).getEndDate());
//                if (Utility.dayLeft(info.get(position).getEndDate()) > 100) {
//                    vh1.tvDaysLeft.setVisibility(View.GONE);
//                    vh1.llBottomText.setWeightSum(2);
//                } else {
//                    vh1.llBottomText.setWeightSum(3);
//                    vh1.tvDaysLeft.setVisibility(View.VISIBLE);
//                    vh1.tvDaysLeft.setText(Utility.dayLeft(info.get(position).getEndDate()) + " " + mContext.getResources().getString(R.string.DL));
//                }

                if (info.get(position).getRemainingDay() > 100) {
                    vh1.tvDaysLeft.setVisibility(View.GONE);
                    vh1.llBottomText.setWeightSum(2);
                } else {
                    vh1.llBottomText.setWeightSum(3);
                    vh1.tvDaysLeft.setVisibility(View.VISIBLE);
                    vh1.tvDaysLeft.setText(info.get(position).getRemainingDay() + " " + mContext.getResources().getString(R.string.DL));
                }

                if (info.get(position).getIconList().size() > 0) {
                    vh1.TVOfferSide.setText(info.get(position).getIconList().get(0).getName());
                    vh1.TVOfferSide.setVisibility(View.VISIBLE);
                } else {
                    vh1.TVOfferSide.setVisibility(View.INVISIBLE);
                }

//                vh1.cardView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.w("MOA", "MyOffersAdapter:vh1.cardView:OnClickPosition =" + position);
//
//                        Tracker t = Utility.mTracker;
//                        t.setScreenName("Privileges-" + info.get(position).getCategory());
//                        t.send(new HitBuilders.ScreenViewBuilder().build());
//
//                        Utility.mTracker.send(new HitBuilders.EventBuilder()
//                                .setCategory("Privileges")
//                                .setAction("ViewDetail")
//                                .setLabel(info.get(position).getCategoryGA() + "")
//                                .setValue(100)
//                                .build());
//
////                        try {
////                            JSONObject jsonObj = new JSONObject();
////                            jsonObj.put("PrivilegeID", info.get(position).getPrivilegeID());
////                            if (info.get(position).isClub())
////                                jsonObj.put("Type", info.get(position).getType());
////                            JSONObject privilege = new JSONObject();
////                            privilege.put("privilege", jsonObj);
////                            Utility.mQueue.add(Utility.VolleyPOST(Utility.ipURL + "CreatePrivilegeRead", privilege));
////                        } catch (Exception e) {
//////                            Log.w("VolleyJson", e.toString());
////                        }
//                        Utility.PrintLog(getClass().getName(), "OpenTo=" + info.get(position).getOpenTo().toLowerCase() + ",OpenWith=" + info.get(position).getOpenWith());
//                        boolean IsPDF = false, IsWeb = false, IsScheme = false, IsStaff = false, IsImage = false;
//                        if (info.get(position).getOpenTo().toLowerCase().equals("app")) {
//                            IsScheme = true;
//                        } else if (info.get(position).getOpenTo().toLowerCase().equals("pdf")) {
//                            IsPDF = true;
//                        } else if (info.get(position).getOpenTo().toLowerCase().equals("web")) {
//                            IsWeb = true;
//                        } else if (info.get(position).getOpenTo().toLowerCase().equals("staffarea")) {
//                            IsStaff = true;
//                        } else if (info.get(position).getOpenTo().toLowerCase().equals("image")) {
//                            IsImage = true;
//                        }
//
//                        if (IsPDF) {
//                            Intent intent = new Intent(mContext, WebViewClientPage.class);
////                            intent.putExtra("LINK", "https://drive.google.com/viewerng/viewer?embedded=true&url=" + "https://gradcollege.okstate.edu/sites/default/files/PDF_linking.pdf");
////                            intent.putExtra("LINK", "https://drive.google.com/viewerng/viewer?embedded=true&url=" + info.get(position).getOpenWith());
//                            intent.putExtra("LINK", info.get(position).getOpenWith());
//                            intent.putExtra("Title", info.get(position).getName());
//                            mContext.startActivity(intent);
//                        } else if (IsWeb) {
//                            Intent intent = new Intent(mContext, WebViewClientPage.class);
////                            intent.putExtra("LINK", "http://www.google.com.hk");
//                            intent.putExtra("IsShowWebButton", info.get(position).isShowWebButton());
//                            intent.putExtra("LINK", info.get(position).getOpenWith());
//                            intent.putExtra("Title", info.get(position).getName());
//                            mContext.startActivity(intent);
//                        } else if (IsScheme) {
////                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100010747800544"));
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(info.get(position).getOpenWith()));
//                            mContext.startActivity(intent);
//                        } else if (IsStaff) {
////                            Intent intent = new Intent(mContext, StaffAreaActivity.class);
////                            mContext.startActivity(intent);
//                        } else if (IsImage) {
////                            Utility.OfferImagePopUpDialog(mContext, act, info.get(position).getOpenWith());
//                        } else {
////                            Utility.selectedOffer = info.get(position);
////                            Utility.SelectedIsClub = info.get(position).isClub();
////                            if (info.get(position).getIconList().size() > 0) {
////                                Utility.CardIDCode = info.get(position).getIconList().get(0).getCode();
//////                        Log.w("VolleyJson", " Utility.selectedOffer =" + info.get(position) + "," +
//////                                "Utility.SelectedIsClub =" + info.get(position).isClub() + "," +
//////                                "Utility.CardIDCode =" + info.get(position).getIconListAL().get(0).getCode());
////                                Utility.mTracker.send(new HitBuilders.EventBuilder()
////                                        .setCategory("Privileges-" + Utility.selectedOffer.getIconList().get(0).getCode() + "-" + Utility.selectedOffer.getCategoryGA())
////                                        .setAction("Click")
////                                        .setLabel(info.get(position).getCategoryGA())
////                                        .setValue(100)
////                                        .build());
////                            }
////                            Intent intent = new Intent(mContext, OffersActivity.class);
//////                        intent.putExtra("ResidentID", Utility.LocalResidentID);
////                            intent.putExtra("IsMe", 1);
////                            mContext.startActivity(intent);
//                        }
//
//                    }
//                });
                break;

            //endregion

            case 9:
                final ViewHolder9 vh9 = (ViewHolder9) holder;
                vh9.tvNoOA.setText(mContext.getString(R.string.NO_OFFERS_AVAILABLE));
                break;
            default:

                break;

        }


//        holder.srivImage.setOval(true);

//        holder.CWCIV.setText(list.get(position));
//        holder.CWDL.setText(list.get(position));
//        holder.CWDesc.setText(list.get(position));

//        try {
//            String contentString = holder.CWDL.getText().toString();
//            if (!contentString.equals("")) {
//
//                Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
//                holder.CWQR.setImageBitmap(qrCodeBitmap);
//            } else {
//                Toast.makeText(mContext, "Text can not be empty", Toast.LENGTH_SHORT).show();
//            }
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Log.w("MOA","Recycle Click" + position);
//
//                Intent intent = new Intent(mContext, OffersActivity.class);
////                intent.putExtra("UserID", UserID);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

//    class ViewHolder0 extends RecyclerView.ViewHolder {
//
//        ImageView ivBackgroundIV;
//        circleimageview offersLogo;
//        ImageView offersBookmark;
//        SweetSansBoldTextView tvOfferTitle;
//        TextView tvDaysLeft;
//        TextView TVOfferSide;
//        TextView tvViewCount;
//
//        public ViewHolder0(View view) {
//            super(view);
//            ivBackgroundIV = (ImageView) view.findViewById(R.id.ivBackgroundIV);
//            offersBookmark = (ImageView) view.findViewById(R.id.offersBookmark);
//            offersLogo = (circleimageview) view.findViewById(R.id.offersLogo);
//            tvOfferTitle = (SweetSansBoldTextView) view.findViewById(R.id.tvOfferTitle);
//            tvDaysLeft = (TextView) view.findViewById(R.id.tvDaysLeft);
//            TVOfferSide = (TextView) view.findViewById(R.id.TVOfferSide);
//            tvViewCount = (TextView) view.findViewById(R.id.tvViewCount);
//        }
//    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        RelativeLayout cardView;
        LinearLayout llBottomText;
        ImageView srivImage;
        SweetSansBoldTextView tvOfferTitle;
        SweetSansBoldTextView tvShopNAME;
        TextView tvDaysLeft;
        TextView TVOfferSide;
        TextView tvViewCount;

        public ViewHolder1(View view) {
            super(view);
            cardView = (RelativeLayout) view.findViewById(R.id.cardView);
            llBottomText = (LinearLayout) view.findViewById(R.id.llBottomText);
            srivImage = (ImageView) view.findViewById(R.id.srivImage);
            tvOfferTitle = (SweetSansBoldTextView) view.findViewById(R.id.tvOfferTitle);
            tvShopNAME = (SweetSansBoldTextView) view.findViewById(R.id.tvShopNAME);
            tvDaysLeft = (TextView) view.findViewById(R.id.tvDaysLeft);
            TVOfferSide = (TextView) view.findViewById(R.id.TVOfferSide);
            tvViewCount = (TextView) view.findViewById(R.id.tvViewCount);
        }
    }

    class ViewHolder9 extends RecyclerView.ViewHolder {
        TextView tvNoOA;

        public ViewHolder9(View view) {
            super(view);
            tvNoOA = (TextView) view.findViewById(R.id.tvNoOA);

        }
    }
}