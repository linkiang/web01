package com.lq.web01.platform.log;

import java.io.Serializable;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class LQLoggerImpl implements LQLogger, Serializable {

	private static final long serialVersionUID = 1L;

	private static Locale locale = Locale.CHINA;
	private Logger log = null;

	public LQLoggerImpl(Class clazz) {
		log = LoggerFactory.getLogger(clazz);
	}

	private boolean shouldOutput(Locale specifyLocale) {
		if (specifyLocale == null || locale == null) {
			return false;
		}
		return locale.getLanguage().equalsIgnoreCase(
				specifyLocale.getLanguage());
	}

	@Override
	public void debug(String msg) {
		debug(msg, Locale.CHINA);
	}

	@Override
	public void debug(String arg0, Object arg1) {
		debug(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void debug(String arg0, Object... arg1) {
		debug(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void debug(String arg0, Throwable t) {
		debug(arg0, t, Locale.CHINA);
	}

	@Override
	public void debug(Marker arg0, String arg1) {
		debug(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void debug(String arg0, Object arg1, Object arg2) {
		debug(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2) {
		debug(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object... arg2) {
		debug(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) {
		debug(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		debug(arg0, arg1, arg2, arg3, Locale.CHINA);
	}

	@Override
	public void error(String arg0) {
		error(arg0, Locale.CHINA);
	}

	@Override
	public void error(String arg0, Object arg1) {
		error(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void error(String arg0, Object... arg1) {
		error(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void error(String arg0, Throwable arg1) {
		error(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void error(Marker arg0, String arg1) {
		error(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void error(String arg0, Object arg1, Object arg2) {
		error(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2) {
		error(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void error(Marker arg0, String arg1, Object... arg2) {
		error(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		error(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		error(arg0, arg1, arg2, arg3, Locale.CHINA);
	}

	@Override
	public String getName() {
		return log.getName();
	}

	@Override
	public void info(String arg0) {
		info(arg0, Locale.CHINA);
	}

	@Override
	public void info(String arg0, Object arg1) {
		info(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void info(String arg0, Object... arg1) {
		info(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void info(String arg0, Throwable arg1) {
		info(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void info(Marker arg0, String arg1) {
		info(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void info(String arg0, Object arg1, Object arg2) {
		info(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2) {
		info(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void info(Marker arg0, String arg1, Object... arg2) {
		info(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		info(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		info(arg0, arg1, arg2, arg3, Locale.CHINA);
	}

	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	@Override
	public boolean isDebugEnabled(Marker arg0) {
		return log.isDebugEnabled(arg0);
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public boolean isErrorEnabled(Marker arg0) {
		return log.isErrorEnabled(arg0);
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	@Override
	public boolean isInfoEnabled(Marker arg0) {
		return log.isInfoEnabled(arg0);
	}

	@Override
	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	@Override
	public boolean isTraceEnabled(Marker arg0) {
		return log.isTraceEnabled(arg0);
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	@Override
	public boolean isWarnEnabled(Marker arg0) {
		return log.isWarnEnabled(arg0);
	}

	@Override
	public void trace(String msg) {
		trace(msg, locale.CHINA);
	}

	@Override
	public void trace(String format, Object arg) {
		trace(format, arg, Locale.CHINA);
	}

	@Override
	public void trace(String format, Object... argArray) {
		trace(format, argArray, Locale.CHINA);
	}

	@Override
	public void trace(String msg, Throwable t) {
		trace(msg, t, Locale.CHINA);
	}

	@Override
	public void trace(Marker marker, String msg) {
		trace(marker, msg, Locale.CHINA);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		trace(format, arg1, arg2, locale.CHINA);
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		trace(marker, format, arg, Locale.CHINA);
	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		trace(marker, format, argArray, Locale.CHINA);
	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {
		trace(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void trace(Marker marker, String format, Object arg2, Object arg3) {
		trace(marker, format, arg2, arg3, Locale.CHINA);
	}

	@Override
	public void warn(String arg0) {
		warn(arg0, Locale.CHINA);
	}

	@Override
	public void warn(String arg0, Object arg1) {
		warn(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void warn(String arg0, Object... arg1) {
		warn(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		warn(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void warn(Marker arg0, String arg1) {
		warn(arg0, arg1, Locale.CHINA);
	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) {
		warn(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2) {
		warn(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2) {
		warn(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		warn(arg0, arg1, arg2, Locale.CHINA);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		warn(arg0, arg1, arg2, arg3, Locale.CHINA);
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
		// LQLoggerImpl.locale = locale;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void trace(String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(msg);
		}
	}

	@Override
	public void trace(String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(format, arg);
		}
	}

	@Override
	public void trace(String format, Object arg1, Object arg2, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(format, arg1, arg2);
		}
	}

	@Override
	public void trace(String format, Object[] argArray, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(format, argArray);
		}
	}

	@Override
	public void trace(String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(msg, t);
		}
	}

	@Override
	public void trace(Marker marker, String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(marker, msg);
		}
	}

	@Override
	public void trace(Marker marker, String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(marker, format, arg);
		}
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(marker, format, arg1, arg2);
		}

	}

	@Override
	public void trace(Marker marker, String format, Object[] argArray,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(marker, format, argArray);
		}
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.trace(marker, msg, t);
		}
	}

	@Override
	public void debug(String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(msg);
		}
	}

	@Override
	public void debug(String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(format, arg);
		}
	}

	@Override
	public void debug(String format, Object arg1, Object arg2, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(format, arg1, arg2);
		}
	}

	@Override
	public void debug(String format, Object[] argArray, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(format, argArray);
		}
	}

	@Override
	public void debug(String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(msg, t);
		}
	}

	@Override
	public void debug(Marker marker, String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(marker, msg);
		}

	}

	@Override
	public void debug(Marker marker, String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(marker, format, arg);
		}

	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(marker, format, arg1, arg2);
		}

	}

	@Override
	public void debug(Marker marker, String format, Object[] argArray,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(marker, format, argArray, locale);
		}
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.debug(marker, msg, t);
		}
	}

	@Override
	public void info(String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(msg);
		}

	}

	@Override
	public void info(String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(format, arg);
		}
	}

	@Override
	public void info(String format, Object arg1, Object arg2, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(format, arg1, arg2);
		}
	}

	@Override
	public void info(String format, Object[] argArray, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(format, argArray);
		}
	}

	@Override
	public void info(String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(msg, t);
		}
	}

	@Override
	public void info(Marker marker, String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(marker, msg);
		}
	}

	@Override
	public void info(Marker marker, String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(marker, format, arg);
		}
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.info(marker, format, arg1, arg2);
		}
	}

	@Override
	public void info(Marker marker, String format, Object[] argArray,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.info(marker, format, argArray);
		}
	}

	@Override
	public void info(Marker marker, String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.info(marker, msg, t);
		}
	}

	@Override
	public void warn(String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(msg);
		}

	}

	@Override
	public void warn(String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(format, arg);
		}
	}

	@Override
	public void warn(String format, Object arg1, Object arg2, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(format, arg1, arg2);
		}
	}

	@Override
	public void warn(String format, Object[] argArray, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(format, argArray);
		}
	}

	@Override
	public void warn(String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(msg, t);
		}
	}

	@Override
	public void warn(Marker marker, String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(marker, msg);
		}
	}

	@Override
	public void warn(Marker marker, String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(marker, format, arg);
		}
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(marker, format, arg1, arg2);
		}
	}

	@Override
	public void warn(Marker marker, String format, Object[] argArray,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(marker, format, argArray);
		}
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.warn(marker, msg, t);
		}
	}

	@Override
	public void error(String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(msg);
		}
	}

	@Override
	public void error(String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(format, arg);
		}
	}

	@Override
	public void error(String format, Object arg1, Object arg2, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(format, arg1, arg2);
		}
	}

	@Override
	public void error(String format, Object[] argArray, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(format, argArray);
		}
	}

	@Override
	public void error(String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(msg, t);
		}
	}

	@Override
	public void error(Marker marker, String msg, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(marker, msg);
		}
	}

	@Override
	public void error(Marker marker, String format, Object arg, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(marker, format, arg);
		}
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2,
			Locale locale) {
		if (shouldOutput(locale)) {
			log.error(marker, format, arg1, arg2);
		}
	}

	@Override
	public void error(Marker marker, String format, Object[] argArray,
			Locale locale) {

		if (shouldOutput(locale)) {
			log.error(marker, format, argArray);
		}
	}

	@Override
	public void error(Marker marker, String msg, Throwable t, Locale locale) {
		if (shouldOutput(locale)) {
			log.error(marker, msg, t);
		}
	}

}
