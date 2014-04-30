package com.example.bd30jours_git;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Dimitri on 05/03/14.
 */
public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View mainView = inflater.inflate(R.layout.layout_fragment_menu,container);
        Button favoris = (Button) mainView.findViewById(R.id.bouton_favoris);
        Button planches = (Button) mainView.findViewById(R.id.bouton_planches);
        Button strips = (Button) mainView.findViewById(R.id.bouton_strip);
        Button auteurs = (Button) mainView.findViewById(R.id.bouton_auteurs);

        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavorisActivity.class);

                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });


        return mainView;
    }
}