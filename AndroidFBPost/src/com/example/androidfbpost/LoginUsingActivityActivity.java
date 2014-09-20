/**
 * Copyright 2010-present Facebook.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidfbpost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class LoginUsingActivityActivity extends Activity {
	private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";

	private TextView textInstructionsOrLink;
	private Button buttonLoginLogout;
	private Session.StatusCallback statusCallback = new SessionStatusCallback();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * setContentView(R.layout.activity); buttonLoginLogout =
		 * (Button)findViewById(R.id.buttonLoginLogout); textInstructionsOrLink
		 * = (TextView)findViewById(R.id.instructionsOrLink);
		 * 
		 * Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
		 */
		Session session = Session.getActiveSession();
		if (session == null) {
			if (savedInstanceState != null) {
				session = Session.restoreSession(this, null, statusCallback,
						savedInstanceState);
			}
			if (session == null) {
				session = new Session(this);
			}
			Session.setActiveSession(session);
			if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
				session.openForRead(new Session.OpenRequest(this)
						.setCallback(statusCallback));
			}
		}

		updateView();
	}

	@Override
	public void onStart() {
		super.onStart();
		Session.getActiveSession().addCallback(statusCallback);
	}

	@Override
	public void onStop() {
		super.onStop();
		Session.getActiveSession().removeCallback(statusCallback);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Session session = Session.getActiveSession();
		Session.saveSession(session, outState);
	}

	private void updateView() {
		Session session = Session.getActiveSession();
		if (session.isOpened()) {
			if (MainActivity.current == 1) {

				Bundle params = new Bundle();
				params.putString("name", MainActivity.city + ","
						+ MainActivity.region + "," + MainActivity.country);
				params.putString("caption", "The current condition for "
						+ MainActivity.city + " is " + MainActivity.text + ".");
				params.putString("description", "Temperature is "
						+ MainActivity.temp + "°" + MainActivity.units + ".");
				params.putString("link", MainActivity.feed);
				params.putString("picture", MainActivity.img);
				params.putString("properties",
						MainActivity.properties.toString());
				WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
						LoginUsingActivityActivity.this,
						Session.getActiveSession(), params))
						.setOnCompleteListener(new OnCompleteListener() {

							@Override
							public void onComplete(Bundle values,
									FacebookException error) {
								if (error == null) {
									// When the story is posted, echo the
									// success
									// and the post Id.
									final String postId = values
											.getString("post_id");
									if (postId != null) {
										Toast.makeText(
												LoginUsingActivityActivity.this,
												"Posting success, id: "
														+ postId,
												Toast.LENGTH_SHORT).show();
									} else {
										// User clicked the Cancel button
										Toast.makeText(
												LoginUsingActivityActivity.this
														.getApplicationContext(),
												"Publish cancelled",
												Toast.LENGTH_SHORT).show();
									}
								} else if (error instanceof FacebookOperationCanceledException) {
									// User clicked the "x" button
									Toast.makeText(
											LoginUsingActivityActivity.this
													.getApplicationContext(),
											"Publish cancelled",
											Toast.LENGTH_SHORT).show();
								} else {
									// Generic, ex: network error
									Toast.makeText(
											LoginUsingActivityActivity.this
													.getApplicationContext(),
											"Error posting story",
											Toast.LENGTH_SHORT).show();
								}
							}

						}).build();
				feedDialog.show();
			} else {

				Bundle params = new Bundle();
				params.putString("name", MainActivity.city + ","
						+ MainActivity.region + "," + MainActivity.country);
				params.putString("caption", "Weather Forecast for "
						+ MainActivity.city);
				params.putString("description", MainActivity.totalForecast);
				params.putString("link", MainActivity.feed);
				params.putString("picture", MainActivity.img);
				params.putString("properties",
						MainActivity.properties.toString());
				WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
						LoginUsingActivityActivity.this,
						Session.getActiveSession(), params))
						.setOnCompleteListener(new OnCompleteListener() {

							@Override
							public void onComplete(Bundle values,
									FacebookException error) {
								if (error == null) {
									// When the story is posted, echo the
									// success
									// and the post Id.
									final String postId = values
											.getString("post_id");
									if (postId != null) {
										Toast.makeText(
												LoginUsingActivityActivity.this,
												"Posted story, id: " + postId,
												Toast.LENGTH_SHORT).show();
									} else {
										// User clicked the Cancel button
										Toast.makeText(
												LoginUsingActivityActivity.this
														.getApplicationContext(),
												"Publish cancelled",
												Toast.LENGTH_SHORT).show();
									}
								} else if (error instanceof FacebookOperationCanceledException) {
									// User clicked the "x" button
									Toast.makeText(
											LoginUsingActivityActivity.this
													.getApplicationContext(),
											"Publish cancelled",
											Toast.LENGTH_SHORT).show();
								} else {
									// Generic, ex: network error
									Toast.makeText(
											LoginUsingActivityActivity.this
													.getApplicationContext(),
											"Error posting story",
											Toast.LENGTH_SHORT).show();
								}
							}

						}).build();
				feedDialog.show();

			}

			/*
			 * textInstructionsOrLink.setText(URL_PREFIX_FRIENDS +
			 * session.getAccessToken());
			 * buttonLoginLogout.setText(R.string.logout);
			 * buttonLoginLogout.setOnClickListener(new OnClickListener() {
			 * public void onClick(View view) { onClickLogout(); } });
			 */

		} else {
			textInstructionsOrLink.setText(R.string.instructions);
			buttonLoginLogout.setText(R.string.login);
			buttonLoginLogout.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					onClickLogin();
				}
			});
		}
	}

	private void onClickLogin() {
		Session session = Session.getActiveSession();
		if (!session.isOpened() && !session.isClosed()) {
			session.openForRead(new Session.OpenRequest(this)
					.setCallback(statusCallback));
		} else {
			Session.openActiveSession(this, true, statusCallback);
		}
	}

	private void onClickLogout() {
		Session session = Session.getActiveSession();
		if (!session.isClosed()) {
			session.closeAndClearTokenInformation();
		}
	}

	private void DisplayCurrentWeather() {

	}

	private void DisplayForecast() {
		// TODO Auto-generated method stub

	}

	private class SessionStatusCallback implements Session.StatusCallback {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			updateView();
		}
	}
}
