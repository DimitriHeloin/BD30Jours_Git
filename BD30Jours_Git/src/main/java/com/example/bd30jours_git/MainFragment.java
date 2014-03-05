package com.example.bd30jours_git;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dimitri on 05/02/14.
 */
public class MainFragment extends Fragment {
    private ListView maListViewPerso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_main,container);

        maListViewPerso = (ListView) mainView.findViewById(R.id.listviewperso);
        // getsupportfragmentmanager().findfragmentbyid(r.id.)

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        //Création d'une HashMap pour insérer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        map.put("titre_vue_unitaire", "Word");
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("description_vue_unitaire", "Editeur de texte");
        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("imageButton_vue_unitaire", String.valueOf(R.drawable.previewalakon));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);



        for(int i=0;i<10;i++)
        {
        map = new HashMap<String, String>();
        //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        map.put("titre_vue_unitaire", "Word");
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("description_vue_unitaire", "Editeur de texte");
        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("imageButton_vue_unitaire", String.valueOf(R.drawable.previewalakon));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);
        }



        SimpleAdapter mSchedule = new SimpleAdapter (this.getActivity(), listItem, R.layout.bd_preview,
                new String[] {"imageButton_vue_unitaire", "titre_vue_unitaire", "description_vue_unitaire"}, new int[] {R.id.imageButton_vue_unitaire, R.id.titre_vue_unitaire, R.id.description_vue_unitaire});

//
        maListViewPerso.setAdapter(mSchedule);
        return mainView;
    }
}
