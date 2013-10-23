package com.example.hoteltap.database;

import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DatabaseThread extends Thread {

	private static final String TAG = DatabaseThread.class.getSimpleName();
	private Queue<Object> jobQueue1;

	private boolean pause;
	private onDatabaseUpdateCompletion databaseUpdateCompletion;

	public DatabaseThread(Context context) {
		jobQueue1 = new LinkedList<Object>();
	}

	public DatabaseThread(Context context,
			onDatabaseUpdateCompletion databaseUpdateCompletion) {
		jobQueue1 = new LinkedList<Object>();
		this.databaseUpdateCompletion = databaseUpdateCompletion;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			synchronized (this) {
				while (!isInterrupted()
						&& (this.jobQueue1.isEmpty() || this.pause)) {
					try {
						wait();
					} catch (InterruptedException e) {
						interrupt();
					}
				}
			}

			if (interrupted()) {
				Log.v(TAG, "interrupted");
				return;
			}

			Object object = null;

			try {
				object = jobQueue1.poll();
			} catch (Exception ex) {
				ex.printStackTrace();
				Log.v(TAG, "poll  " + ex.getMessage());
			}

			if (object != null) {
				if (object instanceof String) {
					
				}
			}

			if (jobQueue1.isEmpty()) {
				if (databaseUpdateCompletion != null) {
					Message.obtain(handler, 100).sendToTarget();
				}
			}

			if (isInterrupted()) {
				Log.v(TAG, "MovingBack");
				break;
			}
		}

		Log.v(TAG, "InRunWait " + isInterrupted());
		if (this.jobQueue1 != null) {
			Log.v(TAG, "clear ");
			this.jobQueue1.clear();
			this.jobQueue1 = null;
		}
	}

	public synchronized void addJob(Object object) {
		if (jobQueue1 != null && object != null) {
			boolean isEmpty = jobQueue1.isEmpty();
			jobQueue1.offer(object);
			if (isEmpty) {
				Log.v(TAG, "empty");
				notify();
			}
		}
	}

	public void clearJobs() {
		if (jobQueue1 != null) {
			jobQueue1.clear();
		}
	}

	public synchronized void pause() {
		this.pause = true;
	}

	public synchronized void unPause() {
		this.pause = false;
		notify();
	}

	public static interface onDatabaseUpdateCompletion {
		public void databaseCompleted();

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 100) {
				databaseUpdateCompletion.databaseCompleted();
			}
		}

	};
}