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

    public PreviewALaConAdapter(Context context, int resource, List<PreviewALaCon> objects) {
        super(context, resource, objects);

        layoutResource = resource;
        previewALaConList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), LectureActivity.class);
                Bundle b = new Bundle();
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



         /*   new Thread(new Runnable() {
                public void run() {
                    Bitmap icon = getBitmapFromURL(previewALaConList.get(position).getUrlPreview());

                    ImageView imagePreview = (ImageView) v.findViewById(R.id.imageButton_vue_unitaire);
                    imagePreview.setImageBitmap(icon);
                }
            }).start();


        */
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
}
