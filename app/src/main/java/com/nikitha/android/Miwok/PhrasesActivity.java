package com.nikitha.android.Miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    wordAdaptor itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<miwokEnglishViewObject> wordsList=new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<TextView> textViewsList=new ArrayList<>();
        wordsList=populateData(wordsList);

        itemsAdapter = new wordAdaptor(this,  wordsList);
        ListView listView = (ListView) findViewById(R.id.rootViewPhases);

        listView.setAdapter(itemsAdapter);
    }

    public  ArrayList<miwokEnglishViewObject> populateData(ArrayList<miwokEnglishViewObject> wordsList){
        int numberListSize=10;

        ArrayList<String> engNumbersList =new ArrayList<>();
        ArrayList<String> miwokNumbersList =new ArrayList<>();

        //Add Numbers to the list
        for(int i=0;i<numberListSize;i++) {
            miwokEnglishViewObject w=new miwokEnglishViewObject();
            w.englishWord= getEngWord(i);
            w.miwokWord=getMiwokWord(i);
            w.play=getMediaPlayList(i);
            //System.out.println("------------"+i+"-----------"+w.englishWord+" ---------------"+w.miwokWord);
            wordsList.add(w);
        }
        return wordsList;
    }

    public String getEngWord(int numberName) {
        switch (numberName) {
            case 0: return getString(R.string.Engphrase1);
            case 1: return getString(R.string.Engphrase2);
            case 2: return getString(R.string.Engphrase3);
            case 3: return getString(R.string.Engphrase4);
            case 4: return getString(R.string.Engphrase5);
            case 5: return getString(R.string.Engphrase6);
            case 6: return getString(R.string.Engphrase7);
            case 7: return getString(R.string.Engphrase8);
            case 8: return getString(R.string.Engphrase9);
            case 9: return getString(R.string.Engphrase10);
            default:
        }
        return null;
    }


    public String getMiwokWord(int numberName) {
        switch (numberName) {
            case 0: return getString(R.string.MiwokPharase1);
            case 1: return getString(R.string.MiwokPharase2);
            case 2: return getString(R.string.MiwokPharase3);
            case 3: return getString(R.string.MiwokPharase4);
            case 4: return getString(R.string.MiwokPharase5);
            case 5: return getString(R.string.MiwokPharase6);
            case 6: return getString(R.string.MiwokPharase7);
            case 7: return getString(R.string.MiwokPharase8);
            case 8: return getString(R.string.MiwokPharase9);
            case 9: return getString(R.string.MiwokPharase10);
            default:
        }
        return null;
    }

    public int getMediaPlayList(int numberName) {
        switch (numberName) {
            case 0:return R.raw.phrase_where_are_you_going;
            case 1:return R.raw.phrase_what_is_your_name;
            case 2:return R.raw.phrase_my_name_is;
            case 3:return R.raw.phrase_how_are_you_feeling;
            case 4:return R.raw.phrase_im_feeling_good;
            case 5:return R.raw.phrase_are_you_coming;
            case 6:return R.raw.phrase_yes_im_coming;
            case 7:return R.raw.phrase_im_coming;
            case 8:return R.raw.phrase_lets_go;
            case 9:return R.raw.phrase_come_here;
            default: return 0;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        itemsAdapter.releaseMediaPlayer();
    }
}
