package com.example.bd30jours_git;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.bd30jours_git.liste.PreviewALaConAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private static String url = "http://30joursdebd.com/?json=get_recent_posts&count=20";
    private static final String TAG_POSTS = "posts";
    private static final String TAG_ID = "id";
    private static final String TAG_URL = "url";
    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHOR = "author";


    public static ArrayList<String> listeId;
    public static ArrayList<String> listeUrl;
    public static ArrayList<String> listeTitle;


    JSONArray posts = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_main,container);
        ImageView image= (ImageView)mainView.findViewById(R.id.imageButton_vue_unitaire);
        maListViewPerso = (ListView) mainView.findViewById(R.id.listviewperso);
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });



        previewALaConList = new ArrayList<PreviewALaCon>();
        listeId=new ArrayList<String>();
        listeUrl=new ArrayList<String>();
        listeTitle = new ArrayList<String>();
        new JSONParse().execute();
        Log.e("Taille de la liste de preview","__"+listeId.size());




        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {

                // TODO Auto-generated method stub
            }
        });
        return mainView;
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            Log.e("NOMBRE DELEEMENTS"," lala  "+json.length());
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                posts = json.getJSONArray(TAG_POSTS);
                Log.e("Nombre de posts",""+posts.length());
                for(int i=0;i<posts.length();i++)
                {
                    JSONObject c = posts.getJSONObject(i);
                    String id = c.getString(TAG_ID);
                    String url = c.getString(TAG_URL);
                    String title = c.getString(TAG_TITLE);
                    JSONObject aut = c.getJSONObject(TAG_AUTHOR);
                    String nomAuteur =aut.getString("name");
                    Log.e("AUTEUR",""+nomAuteur);
                    Auteur auteur = new Auteur(nomAuteur,nomAuteur,nomAuteur);
                    JSONArray attachment = c.getJSONArray("attachments");
                    JSONObject preview = attachment.getJSONObject(1);
                    JSONObject lecture = attachment.getJSONObject(0);

                    String urlPreview=preview.getString("url");
                    String urlLecture=lecture.getString("url");

                    Log.e("URL DES IMAGES PUTAINS","c'est : "+urlPreview);

                    listeId.add(id);
                    listeTitle.add(title);
                    listeUrl.add(url);
                    previewALaConList.add(new PreviewALaCon(0,title,c.getString("date"),auteur,Type.bande,Integer.parseInt(id),urlPreview,urlLecture));
                }
                mPreviewALaConAdapter = new PreviewALaConAdapter(getActivity(), R.layout.bd_preview, previewALaConList);




//
                maListViewPerso.setAdapter(mPreviewALaConAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
