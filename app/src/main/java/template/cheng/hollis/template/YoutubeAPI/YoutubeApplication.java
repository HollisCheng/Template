package template.cheng.hollis.template.YoutubeAPI;

import android.app.Application;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class YoutubeApplication extends Application implements YouTubeThumbnailView.OnInitializedListener {

//    private YouTubeThumbnailView _youTubeThumbnailView;
//    private YouTubeThumbnailLoader _youTubeThumbnailLoader;
    private String YouTubeID;

    public YoutubeApplication() {
    }

    public YoutubeApplication(
//            YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader,
            String youTubeID) {
//        this._youTubeThumbnailView = youTubeThumbnailView;
//        this._youTubeThumbnailLoader = youTubeThumbnailLoader;
        YouTubeID = youTubeID;
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
        youTubeThumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());
        youTubeThumbnailLoader.setVideo(YouTubeID);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
