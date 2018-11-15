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

public class FootBallNews extends Activity {
	ProgressDialog mProgressDialogue;
	ListView list;

	ArrayList<String> listString = new ArrayList<String>();
	String[] vusco = new String[listString.size()];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Title().execute();
		setContentView(R.layout.footballnews);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// switch (matchTom[+position]) {
		// // case "Live Match":
		// // Intent intentlivematch = new Intent(MainActivity.this,
		// // LiveMatch.class);
		// // startActivity(intentlivematch);
		//
		// default:
		// break;
		// }
		//
		// }
		// });

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
			mProgressDialogue = new ProgressDialog(FootBallNews.this);
			mProgressDialogue.setTitle("Initializing");
			mProgressDialogue.setMessage("Loading...");
			mProgressDialogue.setIndeterminate(false);
			mProgressDialogue.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			String url = "http://www.goal.com/en-india/";
			Document document = null;
			try {
				document = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements liveScore = document.select("article.story hgroup a span");
			for (Element element : liveScore) {
				System.out.println("Headline :" + element.text());
				listString.add(element.text());
			}
			vusco = listString.toArray(vusco);

			// listString.add("cisco");
			// listString.add("vasco");
			// listString.add("mosco");
			// vusco = listString.toArray(vusco);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			CustomList adapter = new CustomList(FootBallNews.this, vusco);
			list = (ListView) findViewById(R.id.lstNewsMaatch);
			list.setAdapter(adapter);
			mProgressDialogue.dismiss();
		}

	}

}
