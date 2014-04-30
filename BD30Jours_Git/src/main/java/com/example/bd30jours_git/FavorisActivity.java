package com.example.bd30jours_git;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.bd30jours_git.slidingMenu.app.app.SlidingMenu;

/**
 * Created by Dimitri on 19/03/14.
 */
public class FavorisActivity extends com.example.bd30jours_git.slidingMenu.app.app.SlidingFragmentActivity {

    private SlidingMenu menu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DatabaseHandler db = new DatabaseHandler(this);

        Auteur auteur=new Auteur("AuteurTest","NomTest","PrenomTest");

        setContentView(R.layout.activity_favoris);
        setBehindContentView(R.layout.layout_menu);

        menu=getSlidingMenu();
        menu.setSlidingEnabled(true);





        int widthMenu, widthShadow;
        Display display = getWindowManager().getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            Point size = new Point();
            display.getSize(size);
            widthMenu = (((38*size.x)/100));
            widthShadow = (((14*size.x)/100));
        }
        else
        {
            widthMenu = (((38*display.getWidth())/100));
            widthShadow = (((14*display.getWidth())/100));
        }

        menu.setBehindOffset(widthMenu);


        FavorisFragment fragment = (FavorisFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_list_favoris);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }





    }

//pddddddddd14laule

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_favoris, container, false);
            return rootView;
        }
    }

    public void showMenu(View view) {
        // Kabloey
        toggle();
    }
}
