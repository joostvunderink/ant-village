package org.antvillage.log;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides logging of the actions of a single game. Effectively it provides a separate logging namespace that
 * allows these events to be logged to a separate file or output. 
 * 
 * @author Verik
 */
public class Recorder {

	private static final Logger LOG = LoggerFactory.getLogger(Recorder.class);

	private static Recorder INSTANCE = new Recorder();
	
	public static Recorder getRecorder() {
		return INSTANCE;
	}
	
	private Recorder() {
	}
	
	public void info(String message, Object arg0) {
		LOG.info(message, arg0);
	}

	public void info(String message, Object arg0, Object arg1) {
		LOG.info(message, arg0, arg1);
	}

	public void info(String message, Object arg0, Object arg1, Object arg2) {
		Object[] args = { arg0, arg1, arg2 };
		LOG.info(message, args);
	}

	public void adjustForGames(int games) {
		if ( games < 10) {
			org.apache.log4j.Logger l = org.apache.log4j.Logger.getLogger(Recorder.class);
			l.setLevel(Level.INFO);
		}
		else {
			org.apache.log4j.Logger l = org.apache.log4j.Logger.getLogger(Recorder.class);
			l.setLevel(Level.FATAL);
		}
	}
}
