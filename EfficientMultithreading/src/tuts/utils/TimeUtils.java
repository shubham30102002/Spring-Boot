package tuts.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A utility class for time and date related methods.
 * 
 * @author Arun Mehra
 */
public class TimeUtils {

	/**
	 * Privatize the constructor
	 */
	private TimeUtils(){
		//Do nothing
	}
	
	/**
	 * Adds the 'millisToAdd' no. of milliseconds to the 'initialTime' and returns the resultant timestamp
	 * as a <tt>java.util.Date</tt> object.
	 * @param initialTime
	 * @param millisToAdd
	 * @return Date
	 */
	public static Date getFutureTime(Date initialTime, long millisToAdd) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(initialTime.getTime() + millisToAdd);
		
		return cal.getTime();
	}

	/**
	 * Converts the given <i>seconds</i> and <i>millis</i> to fractional seconds and returns a String
	 * representation of the same.
	 * @param seconds
	 * @param millis
	 * @return String
	 */
	public static String convertToFractionalSecondsStr(long seconds, long millis) {
		return new SecondsAndMillis(seconds, millis).toString();
	}
	
	/**
	 * Returns the duration between two time instants in terms of seconds and milliseconds. If end < start,
	 * then negative duration will be returned.
	 * @param start
	 * @param end
	 * @return SecondsAndMillis - the duration
	 */
	public static SecondsAndMillis getTimeDifferenceInSeconds(Date start, Date end) {
		return new SecondsAndMillis(end.getTime() - start.getTime());
	}
	
	
	/**
	 * Bean class encapsulating a time duration in units of seconds and milliseconds.
	 * @author Arun Mehra
	 */
	public static class SecondsAndMillis {
		
		public final String sign;
		public final long seconds;
		public final long millis;
		
		/**
		 * Two-args constructor
		 * @param seconds
		 * @param millis
		 */
		public SecondsAndMillis(long seconds, long millis) {
			this.sign    = "";
			this.seconds = seconds;
			this.millis  = millis;
		}
		
		/**
		 * One arg constructor.
		 * @param seconds
		 * @param millis
		 */
		public SecondsAndMillis(long millisDuration) {
			this.sign = (millisDuration < 0) ? "-" : "";
			this.seconds = Math.abs(millisDuration / 1000);
			this.millis  = Math.abs(millisDuration % 1000);
		}
		
		@Override
		public String toString() {
			double secsNMillis = seconds + (millis/1000.0);
			String secsStr     = String.format("%.3f", secsNMillis);
			
			return sign + secsStr + " SECONDS";
		}
	}
}
