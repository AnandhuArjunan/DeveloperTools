/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anandhuarjunan.developertools.core.thread;

import javafx.application.Platform;

/**
 * @author Victor Oliveira
 */
public abstract class AsyncTask<T1, T2, T3> {
	
	private boolean daemon = true;

	private T1[] params;

	public abstract void onPreExecute();

	public abstract T3 doInBackground(T1... params);

	public abstract void onPostExecute(T3 params);

	public abstract void progressCallback(T2... params);
	
	public abstract String name();

	public void publishProgress(final T2... progressParams) {
		Platform.runLater(() -> progressCallback(progressParams));
	}

	public final Thread backGroundThread = new Thread(new Runnable() {
		@Override
		public void run() {

			final T3 param = doInBackground(params);

			Platform.runLater(() -> onPostExecute(param));
		}
	});

	public void execute(final T1... params) {
		this.params = params;
		Platform.runLater(() -> {

			onPreExecute();

			backGroundThread.setDaemon(daemon);
			backGroundThread.start();
		});
	}

	public void setDaemon(boolean daemon) {
		this.daemon = daemon;
	}

	public final boolean isInterrupted() {
		return this.backGroundThread.isInterrupted();
	}

	public final boolean isAlive() {
		return this.backGroundThread.isAlive();
	}
	
	public void interrupt() {
		 this.backGroundThread.interrupt();
	}
	
}