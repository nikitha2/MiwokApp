package com.nikitha.android.Miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    int numberListSize=11;
    wordAdaptor itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<miwokEnglishViewObject> wordsList=new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<TextView> textViewsList=new ArrayList<>();
        wordsList=populateData(wordsList);

        for(miwokEnglishViewObject v:wordsList){
            Log.v("NumbersActivity",wordsList.size()+"---------- englishword="+v.englishWord +" and miwok= "+ v.miwokWord+" ------------");
            Log.v("NumbersActivity",wordsList.get(5).englishWord.toString()+"-----------ppppp-----------");
        }

        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        itemsAdapter = new wordAdaptor(this,  wordsList);
        ListView listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(itemsAdapter);
   }

   public  ArrayList<miwokEnglishViewObject> populateData(ArrayList<miwokEnglishViewObject> wordsList){

       ArrayList<String> engNumbersList =new ArrayList<>();
       ArrayList<String> miwokNumbersList =new ArrayList<>();

       //Add Numbers to the list
       for(int i=1;i<numberListSize;i++) {
           miwokEnglishViewObject w=new miwokEnglishViewObject();
           w.englishWord= getEngNumber(i);
           w.miwokWord=getMiwokNumbersList(i);
           w.image=getMiwokNumbersImageList(i);
           w.play=getMediaPlayList(i);
           //System.out.println("------------"+i+"-----------"+w.englishWord+" ---------------"+w.miwokWord);
           wordsList.add(w);
       }
       return wordsList;
   }

    public String getEngNumber(int numberName) {
        switch (numberName) {
            case 1:return getString(R.string.Eng1);
            case 2:return getString(R.string.Eng2);
            case 3:return getString(R.string.Eng3);
            case 4:return getString(R.string.Eng4);
            case 5:return getString(R.string.Eng5);
            case 6:return getString(R.string.Eng6);
            case 7: return getString(R.string.Eng7);
            case 8:return getString(R.string.Eng8);
            case 9: return getString(R.string.Eng9);
            case 10:return getString(R.string.Eng10);
            default: return null;
        }
    }


    public String getMiwokNumbersList(int numberName) {
        switch (numberName) {
            case 1:return getString(R.string.Miwok1);
            case 2:return getString(R.string.Miwok2);
            case 3:return getString(R.string.Miwok3);
            case 4:return getString(R.string.Miwok4);
            case 5:return getString(R.string.Miwok5);
            case 6:return getString(R.string.Miwok6);
            case 7:return getString(R.string.Miwok7);
            case 8:return getString(R.string.Miwok8);
            case 9:return getString(R.string.Miwok9);
            case 10:return getString(R.string.Miwok10);
            default: return null;
        }
    }


    public int getMiwokNumbersImageList(int numberName) {
        switch (numberName) {
            case 1:return R.drawable.number_two;
            case 2:return R.drawable.number_three;
            case 3:return R.drawable.number_three;
            case 4:return R.drawable.number_four;
            case 5:return R.drawable.number_five;
            case 6:return R.drawable.number_six;
            case 7:return R.drawable.number_seven;
            case 8:return R.drawable.number_eight;
            case 9:return R.drawable.number_nine;
            case 10:return R.drawable.number_ten;
            default: return 0;
        }
    }

    public int getMediaPlayList(int numberName) {
        switch (numberName) {
            case 1:return R.raw.number_one;
            case 2:return R.raw.number_two;
            case 3:return R.raw.number_three;
            case 4:return R.raw.number_four;
            case 5:return R.raw.number_five;
            case 6:return R.raw.number_six;
            case 7:return R.raw.number_seven;
            case 8:return R.raw.number_eight;
            case 9:return R.raw.number_nine;
            case 10:return R.raw.number_ten;
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
