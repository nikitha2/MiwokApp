package com.nikitha.android.Miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.*;
import java.util.ArrayList;

public class wordAdaptor extends ArrayAdapter<miwokEnglishViewObject> {

private static final String LOG_TAG = wordAdaptor.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    int length;
    Activity contextActivity;
    public AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.
                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of AndroidFlavor objects to display in a list
     */
    public wordAdaptor(Activity context, ArrayList<miwokEnglishViewObject> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        contextActivity=context;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
            R.layout.each_listitem_layout, parent, false);
        }
        final String[] parts = (parent.toString()).split("app:id/",2);
//
        // Get the {@link AndroidFlavor} object located at this position in the list
        final miwokEnglishViewObject currentWords = getItem(position);
        final miwokEnglishViewObject currentWords1=currentWords;
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.englishText);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentWords.getEnglishWord());
//      Random rand = new Random();
//      nameTextView.setBackgroundColor(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256) ));

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwokText);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentWords.getMiwokWord());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.miwokImage);
    if(currentWords.getImage()!=0) {
        iconView.setImageResource(currentWords.getImage());
//        Log.v("----------------", " image resource set for " + position);
        iconView.setVisibility(View.VISIBLE);
//        setImageColor(iconView,parts[1]);
    }
    else{
        //HIDDNIG THE IMAGE VIEW IF ITS NOT REQUIRED
        iconView.setVisibility(View.GONE);
    }


        ImageView playiconView = (ImageView) listItemView.findViewById(R.id.audioPlay);
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if media player is null means its been release so i can create a new instance of it and play. good practice for memory being used efficiently


                        mAudioManager=(AudioManager) contextActivity.getSystemService(Context.AUDIO_SERVICE);

                        int result= mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                            //have audioFocus now
                            mediaPlayer = MediaPlayer.create(contextActivity, currentWords.getPlay());
                            mediaPlayer.start();
                            // asyn call back to perform a tast after the audio is finished playing.
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                                    @Override
                                                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                                                        // Toast.makeText(contextActivity,"Im done",Toast.LENGTH_SHORT).show();
                                                                        releaseMediaPlayer(); // to release the media player object so
                                                                    }});
                        }
                }
        });



        return listItemView;
    }

    public void releaseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release(); // to release the media player object so
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}