package com.example.bd30jours_git;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bd30jours_git.liste.PreviewALaConAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitri on 19/03/14.
 */
public class FavorisFragment  extends Fragment {

    private ListView maListViewPerso;

    private List<PreviewALaCon> previewALaConList;
    private PreviewALaConAdapter mPreviewALaConAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        View mainView = inflater.inflate(R.layout.fragment_favoris,container);
        ImageView image= (ImageView)mainView.findViewById(R.id.imageButton_vue_unitaire);
        maListViewPerso = (ListView) mainView.findViewById(R.id.listviewperso_favoris);
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        Auteur auteur = new Auteur("TestAuteur","prenomtest","nomtest");


        previewALaConList = new ArrayList<PreviewALaCon>();

        //rempli favori via base de donnée
        for(int i=0;i<db.getAllFavoris().size();i++)
        {
            previewALaConList.add(new PreviewALaCon(0,"Favori","blablafav",auteur,Type.bande,db.getAllFavoris().get(i),"",""));
            Log.e("FAVORIS N°", "" + db.getAllFavoris().get(i));
        }





        mPreviewALaConAdapter = new PreviewALaConAdapter(getActivity(), R.layout.bd_preview, previewALaConList);




//
        maListViewPerso.setAdapter(mPreviewALaConAdapter);

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {

                // TODO Auto-generated method stub
            }
        });
        return mainView;
    }
}
