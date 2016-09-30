package template.cheng.hollis.template.YoutubeAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeThumbnailView;

import template.cheng.hollis.template.R;
import template.cheng.hollis.template.Utility;


public class VideoYoutubeFragment extends Fragment {

    private String YouTubeID;
    private static final String param_url = "url";

    public static VideoYoutubeFragment newInstance(String url) {
        VideoYoutubeFragment frag = new VideoYoutubeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(param_url, url);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YouTubeID = getArguments().getString(param_url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater1, ViewGroup container1, Bundle savedInstanceState) {
        final View view = inflater1.inflate(R.layout.fragment_youtube_player, container1, false);

//        YouTubeID = Utility.getYouTubeID(Utility.VideoAL.get(0));
        //Log.w("V1YF", "YouTubeID=" + YouTubeID);
        //YouTubeThumbnailView
        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) view.findViewById(R.id.youtubethumbnailview1);
        youTubeThumbnailView.initialize(Utility.YOUTUBE_API_KEY, new YoutubeApplication(
//                youTubeThumbnailView, youTubeThumbnailLoader,
                YouTubeID));

        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = YouTubeIntents.createPlayVideoIntentWithOptions(getContext(), YouTubeID, true, false);
                startActivity(intent);
            }
        });

        return view;
    }

}
