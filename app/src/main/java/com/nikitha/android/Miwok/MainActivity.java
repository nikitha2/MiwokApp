package com.nikitha.android.Miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Activity PhrasesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView famMem = (TextView) findViewById(R.id.famMem);
        TextView colors = (TextView) findViewById(R.id.colors);
        TextView phrases = (TextView) findViewById(R.id.phrases);
        TextView[] idList = {numbers, famMem, colors, phrases};
        final Class[] activity = new Class[]{NumbersActivity.class, FamilyMembersActivity.class, ColorsActivity.class, PhrasesActivity.class};

        for (int i = 0; i < idList.length; i++) {
            final int finalI = i;
            idList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create a new intent to open the {@link NumbersActivity}
                    Intent intent = new Intent(MainActivity.this, activity[finalI]);
                    // Start the new activity
                    startActivity(intent);
                }
            });
        }
    }
}

