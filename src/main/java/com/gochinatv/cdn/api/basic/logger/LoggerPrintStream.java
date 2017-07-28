/**
 * Copyright 2007, NetworkBench Systems Corp.
 */
package com.gochinatv.cdn.api.basic.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;

/**
 * @author jacktomcat
 *
 */
public class LoggerPrintStream extends PrintStream {
	private final static String DEFAULT_CHARSET = "UTF-8";
	private Logger logger;
	private String charset;
	private StringWriter buffer;
	
	public LoggerPrintStream(Logger logger) {
		this(logger, DEFAULT_CHARSET);
	}
	
	public LoggerPrintStream(Logger logger, String charset) {
		super(new NOPOutputStream());
		this.logger = logger;
		this.charset = (charset == null ? DEFAULT_CHARSET : charset);
		this.buffer = new java.io.StringWriter();
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#flush()
	 */
	@Override
	public void flush() {
		StringBuffer buf = this.buffer.getBuffer();
		if(buf.length() > 0) {
			logger.info(buf.toString());
			buf.delete(0, buf.length());
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#close()
	 */
	@Override
	public void close() {
		flush();
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#write(int)
	 */
	@Override
	public void write(int b) {
		this.buffer.write(b);
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] buf, int off, int len) {
		try {
			this.buffer.write(new String(buf, off, len, this.charset));
		} catch (UnsupportedEncodingException e) {
			logger.error("unsupported encoding: {}", this.charset);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(boolean)
	 */
	@Override
	public void print(boolean b) {
		this.buffer.write(b ? "true" : "false");
	}
	
	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(char)
	 */
	@Override
	public void print(char c) {
		this.buffer.write(c);
	}
	
	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(int)
	 */
	@Override
	public void print(int i) {
		this.buffer.write(String.valueOf(i));
	}
	
	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(long)
	 */
	@Override
	public void print(long l) {
		this.buffer.write(String.valueOf(l));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(float)
	 */
	@Override
	public void print(float f) {
		this.buffer.write(String.valueOf(f));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(double)
	 */
	@Override
	public void print(double d) {
		this.buffer.write(String.valueOf(d));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(char[])
	 */
	@Override
	public void print(char[] s) {
		if(s != null && s.length > 0) {
			this.buffer.write(String.valueOf(s));
			if(s[s.length - 1] == '\n') {
				flush();
			}
		} else {
			this.buffer.write(s == null ? "null" : "");
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(java.lang.String)
	 */
	@Override
	public void print(String s) {
		if(s != null && s.length() > 0) {
			this.buffer.write(s);
			if(s.charAt(s.length() - 1) == '\n') {
				flush();
			}
		} else {
			this.buffer.write(s == null ? "null" : "");
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(java.lang.Object)
	 */
	@Override
	public void print(Object obj) {
		print(String.valueOf(obj));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println()
	 */
	@Override
	public void println() {
		StringBuffer buf = this.buffer.getBuffer();
		if(buf.length() > 0) {
			logger.info(buf.toString());
			buf.delete(0, buf.length());
		} else {
			logger.info("");
		}
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(boolean)
	 */
	@Override
	public void println(boolean x) {
		flush();
		logger.info(String.valueOf(x));
	}



	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(char)
	 */
	@Override
	public void println(char x) {
		flush();
		logger.info(String.valueOf(x));
	}



	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(int)
	 */
	@Override
	public void println(int x) {
		flush();
		logger.info(String.valueOf(x));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(long)
	 */
	@Override
	public void println(long x) {
		flush();
		logger.info(String.valueOf(x));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(float)
	 */
	@Override
	public void println(float x) {
		flush();
		logger.info(String.valueOf(x));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(double)
	 */
	@Override
	public void println(double x) {
		flush();
		logger.info(String.valueOf(x));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(char[])
	 */
	@Override
	public void println(char[] x) {
		flush();
		logger.info(String.valueOf(x));
	}



	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(java.lang.String)
	 */
	@Override
	public void println(String x) {
		flush();
		logger.info(String.valueOf(x));
	}



	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(java.lang.Object)
	 */
	@Override
	public void println(Object x) {
		flush();
		logger.info(String.valueOf(x));
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#append(java.lang.CharSequence, int, int)
	 */
	@Override
	public PrintStream append(CharSequence csq, int start, int end) {
		CharSequence cs = (csq == null ? "null" : csq);
		this.print(cs.subSequence(start, end).toString());
		return this;
	}

	private final static class NOPOutputStream extends OutputStream {
		/* (non-Javadoc)
		 * @see java.io.OutputStream#write(int)
		 */
		@Override
		public void write(int b) throws IOException {

		}		
	}
}
