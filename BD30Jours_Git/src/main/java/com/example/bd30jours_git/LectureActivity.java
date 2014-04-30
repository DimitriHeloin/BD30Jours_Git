package com.example.bd30jours_git;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Dimitri on 05/03/14.
 */
public class LectureActivity extends Activity {
    ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.layout_lecture);
        Bundle b = getIntent().getExtras();
        final int valueId = b.getInt("idImage");
        final String valueUrlImage=b.getString("urlImageFull");

        Button bouton_retour_list= (Button) findViewById(R.id.bouton_retour_list);
        Button mettre_favori=(Button)findViewById(R.id.bouton_mettre_favori);
        TextView titre = (TextView) findViewById(R.id.textImageLecture);
        ImageView image=(ImageView) findViewById(R.id.imageViewLecture);
        titre.setText("image n°"+valueId);
        imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(valueUrlImage, image);

        bouton_retour_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle b = new Bundle();

                startActivity(intent);
                finish();
            }
        });

        mettre_favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addFavori(valueId);
                Log.e("nombre de favoris", "" + db.getFavorisCount());
            }
        });

        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisc(true) // default
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .discCache(new UnlimitedDiscCache(cacheDir)) // default
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .defaultDisplayImageOptions(options) // default
                .writeDebugLogs()
                .build();



        ImageLoader.getInstance().init(config);

    }

}
