package template.cheng.hollis.template.youtubeAPI;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class ThumbnailLoadedListener implements YouTubeThumbnailLoader.OnThumbnailLoadedListener {
    @Override
    public void onThumbnailError(YouTubeThumbnailView arg0, YouTubeThumbnailLoader.ErrorReason arg1) {
    }

    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView arg0, String arg1) {
    }
}