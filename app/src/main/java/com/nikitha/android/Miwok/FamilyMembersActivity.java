package com.nikitha.android.Miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {
    int numberListSize=10;
    wordAdaptor itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<miwokEnglishViewObject> wordsList=new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<TextView> textViewsList=new ArrayList<>();
        wordsList=populateData(wordsList);

        itemsAdapter = new wordAdaptor(this,  wordsList);
        ListView listView = (ListView) findViewById(R.id.rootViewFamMem);

        listView.setAdapter(itemsAdapter);
    }

    public  ArrayList<miwokEnglishViewObject> populateData(ArrayList<miwokEnglishViewObject> wordsList){

        ArrayList<String> engNumbersList =new ArrayList<>();
        ArrayList<String> miwokNumbersList =new ArrayList<>();

        //Add Numbers to the list
        for(int i=0;i<numberListSize;i++) {
            miwokEnglishViewObject w=new miwokEnglishViewObject();
            w.englishWord= getEngWord(i);
            w.miwokWord=getMiwokWord(i);
            w.image=getMiwokNumbersImageList(i);
            w.play=getMediaPlayList(i);
            //System.out.println("------------"+i+"-----------"+w.englishWord+" ---------------"+w.miwokWord);
            wordsList.add(w);
        }
        return wordsList;
    }

    public String getEngWord(int numberName) {
        switch (numberName) {
            case 0: return getString(R.string.EngFather);
            case 1: return getString(R.string.EngMother);
            case 2: return getString(R.string.EngSon);
            case 3: return getString(R.string.EngDaughter);
            case 4: return getString(R.string.EngOldBro);
            case 5: return getString(R.string.EngYoungBro);
            case 6: return getString(R.string.EngOldSis);
            case 7: return getString(R.string.EngYoungSis);
            case 8: return getString(R.string.EngGrandma);
            case 9: return getString(R.string.EngGrandpa);
            default:
        }
        return null;
    }


    public String getMiwokWord(int numberName) {
        switch (numberName) {
            case 0: return getString(R.string.MiwokFather);
            case 1: return getString(R.string.MiwokMother);
            case 2: return getString(R.string.MiwokSon);
            case 3: return getString(R.string.MiwokDaughter);
            case 4: return getString(R.string.MiwokOldBro);
            case 5: return getString(R.string.MiwokYoungBro);
            case 6: return getString(R.string.MiwokOldSis);
            case 7: return getString(R.string.MiwokYoungSis);
            case 8: return getString(R.string.MiwokGrandma);
            case 9: return getString(R.string.MiwokGrandpa);
            default:
        }
        return null;
    }
    public int getMiwokNumbersImageList(int numberName) {
        switch (numberName) {
            case 0: return R.drawable.family_father;
            case 1:return R.drawable.family_mother;
            case 2:return R.drawable.family_son;
            case 3:return R.drawable.family_daughter;
            case 4:return R.drawable.family_older_brother;
            case 5:return R.drawable.family_younger_brother;
            case 6:return R.drawable.family_older_sister;
            case 7:return R.drawable.family_younger_sister;
            case 8:return R.drawable.family_grandmother;
            case 9:return R.drawable.family_grandfather;
            default:
        }
        return 0;
    }

    public int getMediaPlayList(int numberName) {
        switch (numberName) {
            case 0:return R.raw.family_father;
            case 1:return R.raw.family_mother;
            case 2:return R.raw.family_son;
            case 3:return R.raw.family_daughter;
            case 4:return R.raw.family_older_brother;
            case 5:return R.raw.family_younger_brother;
            case 6:return R.raw.family_older_sister;
            case 7:return R.raw.family_younger_sister;
            case 8:return R.raw.family_grandmother;
            case 9:return R.raw.family_grandfather;
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
