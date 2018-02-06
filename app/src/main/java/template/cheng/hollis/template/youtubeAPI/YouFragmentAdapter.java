package template.cheng.hollis.template.youtubeAPI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class YouFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentsList;
    private ArrayList<String> video;
//    Context _context;
//    LayoutInflater _inflater;
//    HorizontalScrollView _hsv;
//    LinearLayout _thumbnail;
//    ViewPager vp;


    public YouFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public YouFragmentAdapter(FragmentManager fm, ArrayList<String> fragments) {
        super(fm);
        this.video = fragments;
//        this._context = context;
//        this._inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this._hsv = hsv;
//        this._thumbnail = linearLayout;
//        this.vp = viewPager;
    }

    @Override
    public int getCount() {
        return video.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        String getVid = video.get(arg0);
        VideoYoutubeFragment frag = VideoYoutubeFragment.newInstance(getVid);
        return frag;
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, final int position) {
//        Utility.insertExampleImg();
//        View itemView = _inflater.inflate(R.layout.owner_area_pagergalleryitem, container, false);
//        container.addView(itemView);
//
//        // Get the border size to show around each image
//        int borderSize = _thumbnail.getPaddingTop();
//        System.out.println("borderSize: " + borderSize);
//        // Get the size of the actual thumbnail image
//        int thumbnailSize = ((LinearLayout.LayoutParams)
//                vp.getLayoutParams()).bottomMargin - (borderSize * 2);
//        System.out.println("thumbnailSize: " + thumbnailSize);
//
//        // Set the thumbnail layout parameters. Adjust as required
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(thumbnailSize, thumbnailSize);
//        System.out.println("check1");
//        params.setMargins(0, 0, borderSize, 0);
//
//        final ImageView thumbView = new ImageView(_context);
//        thumbView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        thumbView.setLayoutParams(params);
//        thumbView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Thumbnail clicked");
//                // Set the pager position when thumbnail clicked
//                vp.setCurrentItem(position);
//                _hsv.scrollTo(position, 0);
//            }
//        });
//        _thumbnail.addView(thumbView);
//
//        final SubsamplingScaleImageView imageView =
//                (SubsamplingScaleImageView) itemView.findViewById(R.id.image);
//
//        // Asynchronously load the image and set the thumbnail and pager view
//        Glide.with(_context)
//                .load(Utility.AyImage.get(position))
//                .asBitmap()
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
//                        imageView.setImage(ImageSource.bitmap(bitmap));
//                        thumbView.setImageBitmap(bitmap);
//                    }
//                });
//
//        return itemView;
//    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

//    @Override
//    public int getItemPosition(Object object){
//        return PagerAdapter.POSITION_NONE;
//    }


}
