package com.nikitha.android.Miwok;

import android.media.MediaPlayer;

public class miwokEnglishViewObject {

    String englishWord;
    String miwokWord;
    int  image;
    int  play;


    public int getPlay() {
        return play;
    }

    public void setPlay(int  play) {
        this.play = play;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setMiwokWord(String miwokWord) {

        this.miwokWord = miwokWord;
    }


    public int  getImage() {
        return image;
    }

    public void setImage(int  image) {
        this.image = image;
    }

    public miwokEnglishViewObject(String englishWord, String miwokWord){
        this.englishWord=englishWord;
        this.miwokWord= miwokWord;
    }

    public miwokEnglishViewObject(){

    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }
}
