/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);



        //Find the view that shows the numbers category
        TextView numbers = findViewById(R.id.numbers);

        //Set a clickListener on that view
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this,NumbersActivity.class);
                Toast.makeText(view.getContext(),"Open the List of Numbers",Toast.LENGTH_SHORT).show();
                startActivity(numbersIntent);
            }
        });

        //Find the view that shows the colors category
        TextView colors = findViewById(R.id.colors);

        //Set a clickListenet on that view

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this,ColorsActivity.class);
                Toast.makeText(view.getContext(),"Open the List of Colors",Toast.LENGTH_SHORT).show();
                startActivity(colorsIntent);
            }
        });

        //Find the view that shows the family category
        TextView family = findViewById(R.id.family);

        //Set a clickListenet on that view

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this,FamilyActivity.class);
                Toast.makeText(view.getContext(),"Open the List of Family",Toast.LENGTH_SHORT).show();
                startActivity(familyIntent);
            }
        });

        //Find the view that shows the phrases category
        TextView phrases = findViewById(R.id.phrases);

        //Set a clickListenet on that view

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(MainActivity.this,PhrasesActivity.class);
                Toast.makeText(view.getContext(),"Open the List of Phrases",Toast.LENGTH_SHORT).show();
                startActivity(phrasesIntent);
            }
        });


    }
//    public void openNumbersList(View view){
//
//    }


}



