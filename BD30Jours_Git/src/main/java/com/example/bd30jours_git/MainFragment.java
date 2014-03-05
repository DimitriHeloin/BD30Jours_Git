package com.example.bd30jours_git;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.bd30jours_git.liste.PreviewALaConAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dimitri on 05/02/14.
 */
public class MainFragment extends Fragment {

    private ListView maListViewPerso;

    private List<PreviewALaCon> previewALaConList;
    private PreviewALaConAdapter mPreviewALaConAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_main,container);

        maListViewPerso = (ListView) mainView.findViewById(R.id.listviewperso);
        Auteur auteur = new Auteur("TestAuteur","prenomtest","nomtest");


        previewALaConList = new ArrayList<PreviewALaCon>();
        for(int i=0;i<10;i++)
            previewALaConList.add(new PreviewALaCon(0,"PreviewAlkon","ceci est une description",auteur,Type.bande));

        mPreviewALaConAdapter = new PreviewALaConAdapter(getActivity(), R.layout.bd_preview, previewALaConList);


        mPreviewALaConAdapter = new PreviewALaConAdapter(getActivity(), R.layout.bd_preview, previewALaConList);

//
        maListViewPerso.setAdapter(mPreviewALaConAdapter);
        return mainView;
    }
}
