package com.example.soccerlive;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class MatchYesterday extends Activity {
	ProgressDialog mProgressDialogue;
	ListView list;
	ArrayList<String> listString = new ArrayList<String>();
	String[] vusco = new String[listString.size()];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Title().execute();
		setContentView(R.layout.matchyesterday);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
			mProgressDialogue = new ProgressDialog(MatchYesterday.this);
			mProgressDialogue.setTitle("Initializing");
			mProgressDialogue.setMessage("Loading...");
			mProgressDialogue.setIndeterminate(false);
			mProgressDialogue.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			String url = "http://www.livescore.cz/yesterday.php";
			Document document = null;
			try {
				document = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements getTable = document.select("table tr");
			for (Element element : getTable) {
				String match = element.text();
				Elements tournament = element.select("td:nth-child(2)");
				Elements firstTeam = element.select("td:nth-child(3)");
				Elements result = element.select("td:nth-child(4)");
				Elements secondTeam = element.select("td:nth-child(5)");
				if (match.contains(":")) {
					listString.add(result.text() + " " + firstTeam.text()
							+ " vs " + secondTeam.text());
					vusco = listString.toArray(vusco);

				}
				if (!match.contains(":")) {
					System.out.println("Tournament: " + tournament.text());
					System.out.println("---------------------------------");

				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			CustomList adapter = new CustomList(MatchYesterday.this, vusco);
			list = (ListView) findViewById(R.id.lstYesMaatch);
			list.setAdapter(adapter);
			mProgressDialogue.dismiss();
		}
	}

}
