package com.example.soccerlive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView list;
	String[] web = { "Live Match", "Match Today", "Match Tomorrow",
			"Match Yesterday", "Football News" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		CustomList adapter = new CustomList(MainActivity.this, web);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (web[+position] == "Live Match") {
					Intent intentlivematch = new Intent(MainActivity.this,
							LiveMatch.class);
					startActivity(intentlivematch);
				}
				if (web[+position] == "Match Today") {
					Intent intentmatchtoday = new Intent(MainActivity.this,
							MatchToday.class);
					startActivity(intentmatchtoday);
				}
				if (web[+position] == "Match Tomorrow") {
					Intent matchtomorrow = new Intent(MainActivity.this,
							MatchTomorrow.class);
					startActivity(matchtomorrow);
				}
				if (web[+position] == "Match Yesterday") {
					Intent intentmatchyesterday = new Intent(MainActivity.this,
							MatchYesterday.class);
					startActivity(intentmatchyesterday);
				}
				if (web[+position] == "Football News") {
					Intent footballnews = new Intent(MainActivity.this,
							FootBallNews.class);
					startActivity(footballnews);
				}

			}
		});

	}

}
