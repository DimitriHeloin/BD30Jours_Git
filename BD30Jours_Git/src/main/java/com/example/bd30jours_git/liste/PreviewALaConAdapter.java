package com.example.bd30jours_git.liste;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bd30jours_git.LectureActivity;
import com.example.bd30jours_git.MainActivity;
import com.example.bd30jours_git.PreviewALaCon;
import com.example.bd30jours_git.R;
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
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Dimitri on 05/03/14.
 */
public class PreviewALaConAdapter extends ArrayAdapter<PreviewALaCon> {

    private int layoutResource;
    private List<PreviewALaCon> previewALaConList;
    ImageLoader imageLoader;
    public PreviewALaConAdapter(Context context, int resource, List<PreviewALaCon> objects) {
        super(context, resource, objects);

        layoutResource = resource;
        previewALaConList = objects;

        File cacheDir = StorageUtils.getCacheDirectory(context);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisc(true) // default
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
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
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .defaultDisplayImageOptions(options) // default
                .writeDebugLogs()
                .build();



        ImageLoader.getInstance().init(config);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        imageLoader=ImageLoader.getInstance();

        ViewHolder viewHolder = null;

        if(v == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) v.findViewById(R.id.imageButton_vue_unitaire);
            v.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) v.getTag();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), LectureActivity.class);
                Bundle b = new Bundle();
                b.putString("urlImageFull",previewALaConList.get(position).getUrlLecture());
                b.putInt("idImage",previewALaConList.get(position).getId()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                getContext().startActivity(intent);
                //getContext().finish();


            }
        });
        PreviewALaCon previewALaConEnTrainDetreDessinee = previewALaConList.get(position);

        if(previewALaConEnTrainDetreDessinee != null){
            TextView textViewTitre = (TextView) v.findViewById(R.id.titre_vue_unitaire);
            textViewTitre.setText(previewALaConList.get(position).getTitre());

            TextView textViewDescription = (TextView) v.findViewById(R.id.description_vue_unitaire);
            textViewDescription.setText(previewALaConList.get(position).getDescription());

            TextView textViewAuteur = (TextView) v.findViewById(R.id.auteur_unitaire);
            textViewAuteur.setText(previewALaConList.get(position).getAuteur().getNom());

            TextView textViewType = (TextView) v.findViewById(R.id.textViewType);
            textViewType.setText(previewALaConList.get(position).getType().toString());


            String imageUri = previewALaConList.get(position).getUrlPreview(); // from Web
            imageLoader.displayImage(imageUri, viewHolder.mImageView);

        }
        return v;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class ViewHolder {
        public ImageView mImageView;
        public int id;
    }
}
