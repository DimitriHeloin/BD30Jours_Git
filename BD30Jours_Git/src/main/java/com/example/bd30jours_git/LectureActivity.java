package com.example.bd30jours_git;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Dimitri on 05/03/14.
 */
public class LectureActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.layout_lecture);
        Bundle b = getIntent().getExtras();
        final int value = b.getInt("idImage");

        Button bouton_retour_list= (Button) findViewById(R.id.bouton_retour_list);
        Button mettre_favori=(Button)findViewById(R.id.bouton_mettre_favori);
        TextView titre = (TextView) findViewById(R.id.textImageLecture);
        titre.setText("image n°"+value);


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
                db.addFavori(value);
                Log.e("nombre de favoris", "" + db.getFavorisCount());
            }
        });



    }

}
