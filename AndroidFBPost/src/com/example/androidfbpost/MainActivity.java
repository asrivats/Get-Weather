package com.example.androidfbpost;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public String JSON;
	public String LocationType;
	public String tempUnit = "F";
	public String message;
	public String result;
	public String forecast1;
	public String forecast2;
	public String forecast3;
	public String forecast4;
	public String forecast0;
	private String low0;
	private String high0;
	private String low1;
	private String high1;
	private String low2;
	private String high2;
	private String low3;
	private String high3;
	private String low4;
	private String high4;
	public String tempG;
	public static String link;
	public static String totalForecast;
	public static JSONObject inside;
	public static JSONObject properties;
	public static String feed;
	public static String img;
	public static String country;
	public static String region;
	public static String city;
	public static String text;
	public static String temp;
	public static String units;
	public static int current = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void afterUrlConnection(String res) {
		result = res;
		System.out.println(result);
		try {
			JSONObject json = new JSONObject(result);
			JSONObject weather = json.getJSONObject("weather");
			JSONArray forecast = weather.getJSONArray("forecast");
			country = weather.getJSONObject("location").getString("country");
			region = weather.getJSONObject("location").getString("region");
			city = weather.getJSONObject("location").getString("city");
			text = weather.getJSONObject("condition").getString("text");
			temp = weather.getJSONObject("condition").getString("temp");
			units = weather.getJSONObject("units").getString("temperature");
			if (tempG == "C") {
				temp = Integer.toString(Convert(Integer.parseInt(temp)));
				units = tempG;
			}
			TextView cityTV = (TextView) findViewById(R.id.CITY);
			cityTV.setText(city);

			TextView rcTV = (TextView) findViewById(R.id.REGCOUN);
			rcTV.setText(region + "," + country);

			img = weather.getJSONObject("img").getString("src");
			TextView cloudyTV = (TextView) findViewById(R.id.IMGcloudy);
			TextView fairTV = (TextView) findViewById(R.id.IMGfair);
			TextView clearTV = (TextView) findViewById(R.id.IMGclear);
			TextView partlycTV = (TextView) findViewById(R.id.IMGpcloudy);
TextView hazeTV = (TextView)findViewById(R.id.IMGhaze);
			if (text.equals("Partly Cloudy")) {
				partlycTV.setVisibility(0);
			}
			if (text.equals("Haze")) {
				hazeTV.setVisibility(0);
			}if (text.equals("Cloudy")) {
				cloudyTV.setVisibility(0);
			}
			if (text.equals("Fair")) {
				fairTV.setVisibility(0);
			}
			if (text.equals("Clear")) {
				clearTV.setVisibility(0);
			}
			feed = weather.getString("feed");
			link = weather.getString("link");

			inside = new JSONObject();
			inside.put("text", "here");
			inside.put("href", link);

			properties = new JSONObject();
			properties.put("Look at details", inside);
			TextView textTV = (TextView) findViewById(R.id.TEXT);
			textTV.setText(text);
			TextView tempTV = (TextView) findViewById(R.id.TEMP);
			tempTV.setText(temp + "°" + units);
			TextView fTV = (TextView) findViewById(R.id.FORECAST);
			fTV.setText("Forecast");

			JSONObject f0 = forecast.getJSONObject(0);
			String day0 = f0.getString("day");
			low0 = f0.getString("low");
			high0 = f0.getString("high");

			if (tempG == "C") {
				low0 = Integer.toString(Convert(Integer.parseInt(low0)));
				high0 = Integer.toString(Convert(Integer.parseInt(high0)));
			}
			String text0 = f0.getString("text");
			forecast0 = day0 + ": " + text0 + "," + high0 + "/" + low0 + "°"
					+ units + ";";

			TableRow t1 = (TableRow) findViewById(R.id.tableRow1);
			/*
			 * t1.setBackgroundColor(Color.parseColor("#808080"));
			 */
			TextView dayHTV = (TextView) findViewById(R.id.DAYH);
			dayHTV.setVisibility(0);
			dayHTV.setText("Day");

			/*
			 * dayHTV.setBackgroundColor(0x808080);
			 */TextView lowHTV = (TextView) findViewById(R.id.LOWH);
			lowHTV.setVisibility(0);
			lowHTV.setText("Low");
			TextView highHTV = (TextView) findViewById(R.id.HIGHH);
			highHTV.setVisibility(0);
			highHTV.setText("High");
			TextView textHTV = (TextView) findViewById(R.id.TEXTH);
			textHTV.setVisibility(0);
			textHTV.setText("Weather");

			TableRow t2 = (TableRow) findViewById(R.id.tableRow2);
			/* t2.setBackgroundColor(Color.parseColor("#FFFFFF")); */
			TableRow t4 = (TableRow) findViewById(R.id.tableRow4);
			/* t4.setBackgroundColor(Color.parseColor("#FFFFFF")); */
			TableRow t3 = (TableRow) findViewById(R.id.tableRow3);
			/* t3.setBackgroundColor(Color.parseColor("#00FFFF")); */
			TableRow t5 = (TableRow) findViewById(R.id.tableRow5);
			/*
			 * t5.setBackgroundColor(Color.parseColor("#00FFFF"));
			 */TableRow t6 = (TableRow) findViewById(R.id.tableRow6);
			/* t6.setBackgroundColor(Color.parseColor("#FFFFFF")); */

			TextView day0TV = (TextView) findViewById(R.id.DAY0);
			day0TV.setVisibility(0);
			day0TV.setText(day0);
			TextView low0TV = (TextView) findViewById(R.id.LOW0);
			low0TV.setVisibility(0);
			low0TV.setText(low0 + "°" + units);
			TextView high0TV = (TextView) findViewById(R.id.HIGH0);
			high0TV.setVisibility(0);
			high0TV.setText(high0 + "°" + units);
			TextView text0TV = (TextView) findViewById(R.id.TEXT0);
			text0TV.setVisibility(0);
			text0TV.setText(text0);

			JSONObject f1 = forecast.getJSONObject(1);
			String day1 = f1.getString("day");
			low1 = f1.getString("low");
			high1 = f1.getString("high");
			if (tempG == "C") {
				low1 = Integer.toString(Convert(Integer.parseInt(low1)));
				high1 = Integer.toString(Convert(Integer.parseInt(high1)));
			}
			String text1 = f1.getString("text");
			forecast1 = day1 + ": " + text1 + "," + high1 + "/" + low1 + "°"
					+ units + ";";

			TextView day1TV = (TextView) findViewById(R.id.DAY1);
			day1TV.setVisibility(0);
			day1TV.setText(day1);
			TextView low1TV = (TextView) findViewById(R.id.LOW1);
			low1TV.setVisibility(0);
			low1TV.setText(low1 + "°" + units);
			TextView high1TV = (TextView) findViewById(R.id.HIGH1);
			high1TV.setVisibility(0);
			high1TV.setText(high1 + "°" + units);
			TextView text1TV = (TextView) findViewById(R.id.TEXT1);
			text1TV.setVisibility(0);
			text1TV.setText(text0);

			JSONObject f2 = forecast.getJSONObject(2);
			String day2 = f2.getString("day");
			low2 = f2.getString("low");
			high2 = f2.getString("high");
			if (tempG == "C") {
				low2 = Integer.toString(Convert(Integer.parseInt(low2)));
				high2 = Integer.toString(Convert(Integer.parseInt(high2)));
			}
			String text2 = f2.getString("text");
			forecast2 = day2 + ": " + text2 + "," + high2 + "/" + low2 + "°"
					+ units + ";";

			TextView day2TV = (TextView) findViewById(R.id.DAY2);
			day2TV.setVisibility(0);
			day2TV.setText(day2);
			TextView low2TV = (TextView) findViewById(R.id.LOW2);
			low2TV.setVisibility(0);
			low2TV.setText(low2 + "°" + units);
			TextView high2TV = (TextView) findViewById(R.id.HIGH2);
			high2TV.setVisibility(0);
			high2TV.setText(high2 + "°" + units);
			TextView text2TV = (TextView) findViewById(R.id.TEXT2);
			text2TV.setVisibility(0);
			text2TV.setText(text2);

			JSONObject f3 = forecast.getJSONObject(3);
			String day3 = f3.getString("day");
			low3 = f3.getString("low");
			high3 = f3.getString("high");
			if (tempG == "C") {
				low3 = Integer.toString(Convert(Integer.parseInt(low3)));
				high3 = Integer.toString(Convert(Integer.parseInt(high3)));
			}
			String text3 = f3.getString("text");
			forecast3 = day3 + ": " + text3 + "," + high3 + "/" + low3 + "°"
					+ units + ";";

			TextView day3TV = (TextView) findViewById(R.id.DAY3);
			day3TV.setVisibility(0);
			day3TV.setText(day3);
			TextView low3TV = (TextView) findViewById(R.id.LOW3);
			low3TV.setVisibility(0);
			low3TV.setText(low3 + "°" + units);
			TextView high3TV = (TextView) findViewById(R.id.HIGH3);
			high3TV.setVisibility(0);
			high3TV.setText(high3 + "°" + units);
			TextView text3TV = (TextView) findViewById(R.id.TEXT3);
			text3TV.setVisibility(0);
			text3TV.setText(text3);

			JSONObject f4 = forecast.getJSONObject(4);
			String day4 = f4.getString("day");
			low4 = f4.getString("low");
			high4 = f4.getString("high");
			if (tempG == "C") {
				low4 = Integer.toString(Convert(Integer.parseInt(low4)));
				high4 = Integer.toString(Convert(Integer.parseInt(high4)));
			}
			String text4 = f4.getString("text");
			forecast4 = day4 + ": " + text4 + "," + high4 + "/" + low4 + "°"
					+ units + ";";

			TextView day4TV = (TextView) findViewById(R.id.DAY4);
			day4TV.setVisibility(0);
			day4TV.setText(day4);
			TextView low4TV = (TextView) findViewById(R.id.LOW4);
			low4TV.setVisibility(0);
			low4TV.setText(low4 + "°" + units);
			TextView high4TV = (TextView) findViewById(R.id.HIGH4);
			high4TV.setVisibility(0);
			high4TV.setText(high4 + "°" + units);
			TextView text4TV = (TextView) findViewById(R.id.TEXT4);
			text4TV.setVisibility(0);
			text4TV.setText(text4);

			totalForecast = forecast0 + forecast1 + forecast2 + forecast3
					+ forecast4;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId()) {
		case R.id.radio_f:
			if (checked) {
				Context context = getApplicationContext();
				CharSequence text = "Fahrenheit is temp type";
				int duration = Toast.LENGTH_SHORT;
				tempUnit = "F";
				/*
				 * Toast toast = Toast.makeText(context, text, duration);
				 * toast.show();
				 */}
			break;
		case R.id.radio_c:
			if (checked)
				System.out.println("Celcius is temp type");
			Context context = getApplicationContext();
			CharSequence text = "Celcius is temp type";
			int duration = Toast.LENGTH_SHORT;
			tempUnit = "C";
			tempG = "C";
			/*
			 * Toast toast = Toast.makeText(context, text, duration);
			 * toast.show();
			 */}
	}

	public void postCurentToFb(View view) {
		current = 1;
		Intent intent = new Intent(MainActivity.this,
				LoginUsingActivityActivity.class);

		startActivity(intent);

	}

	public void postForecastToFb(View view) {
		current = 0;
		Intent intent = new Intent(MainActivity.this,
				LoginUsingActivityActivity.class);

		startActivity(intent);

	}

	public void sendLoc(View view) throws IOException {

		EditText editText = (EditText) findViewById(R.id.edit_message);
		message = editText.getText().toString();
		String trimmedmsg = message.trim().replace(" ", "");
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		if (message.equals("") || message == null) {
			Toast.makeText(
					view.getContext(),
					"Please enter location:\nExample: 90089\nExample: Los Angeles,CA",
					Toast.LENGTH_SHORT).show();
			return;
		}

		else if (message.equals("00000") || message.equals("Nope,Nope")
				|| message.equals("11111")
				|| message.equals("NopeNope,NopeNope")) {
			Toast.makeText(view.getContext(), "No results found",
					Toast.LENGTH_SHORT).show();
		} else if (message.trim().replace(" ", "")
				.matches("[a-zA-Z0-9_']+,[a-zA-Z0-9_]+")) {
			LocationType = "City";
			// Toast.makeText(context, message, duration).show();
			GetJsonFromServlet j1 = new GetJsonFromServlet(this);
			j1.execute(message, LocationType, tempUnit);
			Toast.makeText(view.getContext(), result, Toast.LENGTH_SHORT);
		}

		else if (message.matches("[0-9]+")) {
			if (message.length() != 5) {
				Toast.makeText(view.getContext(),
						"Please enter a valid zip code, must be 5 digits",
						Toast.LENGTH_SHORT).show();
				return;
			}
			LocationType = "zip";
			// Toast.makeText(context, message, duration).show();
			GetJsonFromServlet j2 = new GetJsonFromServlet(this);
			j2.execute(message, LocationType, tempUnit);
			Toast.makeText(view.getContext(), result, Toast.LENGTH_SHORT);
		} else if (!message.contains(",")) {
			Toast.makeText(
					view.getContext(),
					"Invalid Location : Must include state or country separated by comma\n Example: Los Angeles,CA",
					Toast.LENGTH_SHORT).show();
		}

	}

	private int Convert(int fahrenheit) {
		// TODO Auto-generated method stub
		return (fahrenheit - 32) * 5 / 9;

	}

}
