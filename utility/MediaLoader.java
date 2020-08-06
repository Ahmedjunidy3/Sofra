package com.example.sofra.utility;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

public class MediaLoader implements AlbumLoader {
    private static MediaLoader mediaLoader;

    public static void initAlbum(Activity activity) {
        Album.initialize(AlbumConfig.newBuilder(activity)
                .setAlbumLoader(MediaLoader.getInstance())
                .build());
    }

    public static MediaLoader getInstance() {
        if (mediaLoader == null) {
             mediaLoader = new MediaLoader();
        }
        return mediaLoader;
    }

    @Override
    public void load(ImageView imageView, AlbumFile albumFile) {
        load(imageView, albumFile.getPath());
    }

    @Override
    public void load(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
//                .error(R.drawable.placeholder)
//                .placeholder(R.drawable.placeholder)
//                .crossFade()
                .into(imageView);
    }
}
