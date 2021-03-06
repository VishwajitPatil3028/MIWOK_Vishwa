package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.System.lineSeparator;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer = new MediaPlayer();

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // The AUDIO_FOCUS_GAIN case means we have regained focus and can resume
                        // playback
                        mMediaPlayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        // The audio focus loss means we've lost audio focus and
                        // stop playback and cleanup resources
                        releaseMediaPlayer();
                    }
                }

            };

    private MediaPlayer.OnCompletionListener mComplitionListner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


//        String [] words = new String[10];
//        words[0] = "one";
//        words[1] = "two";
//        words[2] = "three";
//        words[3] = "four";
//        words[4] = "five";
//        words[5] = "six";
//        words[6] = "seven";
//        words[7] = "eight";
//        words[8] = "nine";
//        words[9] = "ten";

        final ArrayList<Word> words = new ArrayList<Word>();

//        Word w = new Word("one","lutti");
//        words.add(w);

        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));

        WordsAdapter adapter = new WordsAdapter(this,words);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(NumbersActivity.this, "List Item Clicked", Toast.LENGTH_SHORT).show();

                //get the object at the given position the user clicked on
                Word word = words.get(i);

             // Before assigning an audio file we need to release the media player to
             // play the different audio file
                releaseMediaPlayer();
//              Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //mAudioManager.registerMediaButtonEventReceiver(RemoteCOntrolReceiver);
                    // We have audion focus now so we add the block of code which we use to
                    // play the audio

                    //create and setup the link for the audion resource associated with
                    // the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

    //              Setup a listener on the media player, so we can stop and release the
    //              media player once the sounds has finished playing
                    mMediaPlayer.setOnCompletionListener(mComplitionListner);

                }
            }
        });


//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item,words);
//        GridView listView = findViewById(R.id.list);
//
//        listView.setAdapter(itemsAdapter);


//        words.add("one");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");


//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,words);
//        GridView listView = findViewById(R.id.list);
//
//        listView.setAdapter(itemsAdapter);



//        for(int i=0 ; i < words.size() ; i++)
//        {
//            //System.lineSeparator();
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//        When the activity is stopped, release the media player resources because we won't
//        be playing any more sounds
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
//        if the media player is not null then it may be currently playing the sound
        if(mMediaPlayer != null){
//            Regardless of the current state of the media player, release its resources
//            because we no longer need it.

            mMediaPlayer.release();
//            Set the media player back to null. For our code, we've decided that
//            setting the media player to null is an easy way to tell that the media player is not
//            configured to play an audio

            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

}