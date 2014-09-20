package com.example.androidfbpost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetJsonFromServlet extends AsyncTask<String, Void, String> {

	MainActivity local;
	public String str;

	public GetJsonFromServlet(MainActivity m1) {
		// TODO Auto-generated constructor stub
		local = m1;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		local.afterUrlConnection(result);
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub

		String urlString = "http://cs-server.usc.edu:22779/HW8/SearchServlet";
		try {
			urlString = urlString + "?location="
					+ URLEncoder.encode(local.message, "UTF-8") + "&type="
					+ local.LocationType + "&tempUnit=" + local.tempUnit;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			URLConnection urlConnection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream urlStream = null;
		try {
			urlStream = url.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(urlStream));
		try {
			str = br.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return str;
	}

}
