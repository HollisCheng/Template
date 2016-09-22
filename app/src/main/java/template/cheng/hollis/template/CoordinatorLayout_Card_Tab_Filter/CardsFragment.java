package template.cheng.hollis.template.CoordinatorLayout_Card_Tab_Filter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import template.cheng.hollis.template.MainActivity;
import template.cheng.hollis.template.R;
import template.cheng.hollis.template.TextView.RobotoBoldTextView;

public class CardsFragment extends Fragment {
    private String cardType;
    private String cardsUserName;
    private String cardNo;
    private ImageView CardsFront, CardsBack;
    private SelectableRoundedImageView SRIVCardsFront, SRIVCardsBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);

        RobotoBoldTextView cardUserName = (RobotoBoldTextView) view.findViewById(R.id.CardsNumber);
        TextView tvCardNo = (TextView) view.findViewById(R.id.tvCardNo);
//        final TextView edit = (TextView) view.findViewById(R.id.tvEdit);
//        final TextView cancel = (TextView) view.findViewById(R.id.tvCancel);
//        final ImageView cardDel = (ImageView) view.findViewById(R.id.CardsDel);
//        set frame layout
//        ImageView CardsFront = (ImageView) view.findViewById(R.id.Cards);
        CardsFront = (ImageView) view.findViewById(R.id.CardsFront);
//        CardsBack = (ImageView) view.findViewById(R.id.CardsBack);
        SRIVCardsFront = (SelectableRoundedImageView) view.findViewById(R.id.SRIVCardsFront);
//        SRIVCardsBack = (SelectableRoundedImageView) view.findViewById(R.id.SRIVCardsBack);
        cardsUserName = getArguments().getString("cardsUserName");
        cardType = getArguments().getString("cardType");
        cardNo = getArguments().getString("cardNo");

//        final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
//                R.animator.card_flip_right_out);
//
//        final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
//                R.animator.card_flip_left_in);
//        CardsFront.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setRightOut.setTarget(CardsFront);
//                setLeftIn.setTarget(CardsBack);
//                setRightOut.start();
//                setLeftIn.start();
//            }
//        });
//
//        CardsBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setRightOut.setTarget(CardsBack);
//                setLeftIn.setTarget(CardsFront);
//                setRightOut.start();
//                setLeftIn.start();
//            }
//        });

//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cardDel.setVisibility(View.VISIBLE);
//                edit.setVisibility(View.GONE);
//                cancel.setVisibility(View.VISIBLE);
//            }
//        });

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cardDel.setVisibility(View.GONE);
//                cancel.setVisibility(View.GONE);
//                edit.setVisibility(View.VISIBLE);
//            }
//        });
//        cardDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Del cardsID" + cardsID);
//                confirmDialog();
//            }
//        });

        //check if 01/05/2016
//        long currentTime = System.currentTimeMillis();
//        boolean IsBefore1StOfMay = true;
//        if (currentTime - Utility.TransferDateTimeToLong("01/05/2016", "00:00") > 0) {
//            IsBefore1StOfMay = false;
//        }

//        if (IsBefore1StOfMay) {
//            if (cardType.toLowerCase().equals(Utility.estateInfo.getEstateCode().toLowerCase())) {
//           for(int i = 0 ; i<Utility.CardInfoAL.size();i++){}
        if (cardType.toLowerCase().equals("ph")) {
            //primary property
//            CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.sp));
            CardsFront.setVisibility(View.INVISIBLE);
//            CardsBack.setVisibility(View.INVISIBLE);
            SRIVCardsFront.setVisibility(View.VISIBLE);
//            SRIVCardsBack.setVisibility(View.VISIBLE);
            SRIVCardsFront.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_manage));

        } else if (cardType.toLowerCase().equals("tph")) {
//            CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.tph));
            CardsFront.setVisibility(View.INVISIBLE);
//            CardsBack.setVisibility(View.INVISIBLE);
            SRIVCardsFront.setVisibility(View.VISIBLE);
//            SRIVCardsBack.setVisibility(View.VISIBLE);
            SRIVCardsFront.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_slideshow));
        } else if (cardType.toLowerCase().equals("infinity")) {
            //infinity also = diamond
            CardsFront.setVisibility(View.VISIBLE);
//            CardsBack.setVisibility(View.VISIBLE);
            SRIVCardsFront.setVisibility(View.INVISIBLE);
//            SRIVCardsBack.setVisibility(View.INVISIBLE);
            Glide.with(this).load(R.drawable.ic_menu_camera).asGif().into(CardsFront);
//            CardsFront.setPadding(6, 0, 6, 0);
        } else if (cardType.toLowerCase().equals("circle")) {
            //circle
            CardsFront.setVisibility(View.VISIBLE);
//            CardsBack.setVisibility(View.VISIBLE);
            SRIVCardsFront.setVisibility(View.INVISIBLE);
//            SRIVCardsBack.setVisibility(View.INVISIBLE);
            Glide.with(this).load(R.drawable.ic_menu_send).asGif().into(CardsFront);
//            CardsFront.setPadding(6, 0, 6, 0);

        } else if (cardType.toLowerCase().equals("viva")) {
            //viva
//            CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.viva));
            CardsFront.setVisibility(View.VISIBLE);
//            CardsBack.setVisibility(View.VISIBLE);
            SRIVCardsFront.setVisibility(View.INVISIBLE);
//            SRIVCardsBack.setVisibility(View.INVISIBLE);
            Glide.with(this).load(R.drawable.ic_menu_share).asGif().into(CardsFront);
//            CardsFront.setPadding(6, 0, 6, 0);
        } else if (cardType.toLowerCase().equals("diamond")) {
            CardsFront.setVisibility(View.VISIBLE);
//            CardsBack.setVisibility(View.VISIBLE);
            SRIVCardsFront.setVisibility(View.INVISIBLE);
//            SRIVCardsBack.setVisibility(View.INVISIBLE);
            Glide.with(this).load(R.drawable.ic_menu_gallery).asGif().into(CardsFront);
//            CardsFront.setPadding(6, 0, 6, 0);
        } else {
            CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.icon_feedback));

        }
//        } else {
//            if (cardType.toLowerCase().equals(Utility.estateInfo.getEstateCode().toLowerCase())) {
//                //primary property
//                CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.resident_card));
//
//            } else if (cardType.toLowerCase().equals("infinity")) {
//                //No infinity after 1St of May
//                CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.diamond));
//
//            } else if (cardType.toLowerCase().equals("circle")) {
//                //circle
//                CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.circle));
//
//            } else if (cardType.toLowerCase().equals("viva")) {
//                //viva
//                CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.viva));
//
//            } else if (cardType.toLowerCase().equals("diamond")) {
//                CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.diamond));
//
//            } else {
//                CardsFront.setImageDrawable(getResources().getDrawable(R.drawable.resident_card));
//
//            }
//        }
        cardUserName.setText(cardsUserName);
        tvCardNo.setText(cardNo);


//        cardsName.setText(Utility.userData.getDisplayName());
//        if (pts.equals("0")) {
//            cardsPts.setVisibility(View.GONE);
//        } else {
//            cardsPts.setVisibility(View.VISIBLE);
//            cardsPts.setText(pts);
//        }

        return view;
    }

    private void confirmDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
        alertDlg.setMessage(R.string.deleteQ);
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //volley delete card wallet

                //TODO think about volley connection fail and reconnect again

                //TODO delete card wallet and refresh
                //reload and the download already -1 = correct
                //should store card in a Info and delete one of it when del then upload

                //refresh part
                //close all and go back to card wallet page
                Intent intent = new Intent(getActivity(), MainActivity.class);
                //clear all previous activities
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();
    }
}