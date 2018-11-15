package com.example.soccerlive;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class LiveMatch extends Activity {

	ProgressDialog mProgressDialogue;
	TextView tvMatchTime;
	TextView tvFirstTeam;
	TextView tvSecondTeam;
	TextView tvResult;
	TextView tournament, tvTournament;

	String stMatchTime, stFirstTeam, stSecondTeam, stfirstTeamGoal,
			stsecondTeamGoal, sttournament, stTournament;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.livematch);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// tvlivematch = (TextView) findViewById(R.id.tvMatchTime);
		tvMatchTime = (TextView) findViewById(R.id.tvMatchTime);
		tvFirstTeam = (TextView) findViewById(R.id.tvFirstTeam);
		tvSecondTeam = (TextView) findViewById(R.id.tvSecondTeam);
		tvResult = (TextView) findViewById(R.id.tvResult);
		tvTournament = (TextView) findViewById(R.id.tvTournament);

		new Title().execute();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class Title extends AsyncTask<Void, Void, Void> {
		String title = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialogue = new ProgressDialog(LiveMatch.this);
			mProgressDialogue.setTitle("Initializing");
			mProgressDialogue.setMessage("Loading...");
			mProgressDialogue.setIndeterminate(false);
			mProgressDialogue.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			String url = "http://www.livescore.cz/live-soccer.php";
			Document document;
			try {
				document = Jsoup.connect(url).get();
				Elements tournament = document
						.select("table.tab tr:nth-child(1) td:nth-child(2) ");
				Elements currentTime = document
						.select("table.tab tr:nth-child(2) td:nth-child(2) ");
				Elements teamOne = document
						.select("table.tab tr:nth-child(2) td:nth-child(3) ");
				Elements result = document
						.select("table.tab tr:nth-child(2) td:nth-child(4)");
				Elements teamTwo = document
						.select("table.tab tr:nth-child(2) td:nth-child(5) ");
				// title += currentTime.text() + "\n" + teamOne.text() + " vs "
				// + teamTwo.text() + "\n" + "Result: " + result.text()
				// + "\n";
				stMatchTime = currentTime.text();
				stFirstTeam = teamOne.text();
				stSecondTeam = teamTwo.text();
				stfirstTeamGoal = result.text().replace(":",
						"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
				stTournament = tournament.text();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			tvMatchTime.setText(stMatchTime);
			tvFirstTeam.setText(stFirstTeam);
			tvSecondTeam.setText(stSecondTeam);
			tvResult.setText(stfirstTeamGoal);
			tvTournament.setText(stTournament);
			mProgressDialogue.dismiss();
		}

	}
}
