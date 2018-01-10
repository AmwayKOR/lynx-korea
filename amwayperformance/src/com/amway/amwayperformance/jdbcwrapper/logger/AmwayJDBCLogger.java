package com.amway.amwayperformance.jdbcwrapper.logger;

/**
 * Created by aiueq92 on 11/1/17.
 */

import de.hybris.platform.core.Tenant;
import de.hybris.platform.jdbcwrapper.logger.FileLogger;
import de.hybris.platform.util.config.ConfigIntf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.amway.amwayperformance.jdbcwrapper.logger.impl.AmwayJDBCSubLoggerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AmwayJDBCLogger extends FileLogger
{

	private static long HASH_LARGE_PRIME = 37123;
	private static long HASH_LARGE_PRIME_MAX = 995773;

	private final ConfigIntf.ConfigChangeListener cfgChangeListener;
	protected boolean appendingAmwayStackActivated;
	Tenant tenantContext = null;

	//Loggers scoped by Hybris items (db table)
	Map<String, AmwayJDBCSubLogger> subLogRegistryItems = new HashMap<String, AmwayJDBCSubLogger>();
	//Loggers scoped by spring bean
	Map<String, AmwayJDBCSubLogger> subLogRegistryBeans = new HashMap<String, AmwayJDBCSubLogger>();



	/**
	 * Initialize context and setup sub loggers
	 * @param tenant
	 */
	public AmwayJDBCLogger(Tenant tenant)
	{
		//Load config context and listener
		super(tenant);

		//cuz parents for set a variable they just init
		tenantContext = tenant;
		//setup a config change listener
		this.cfgChangeListener = new AmwayJDBCLogger.AmwayJDBCLogUtilConfigListener();
		tenant.getConfig().registerConfigChangeListener(this.cfgChangeListener);

		//Setup Amway stack boolean - used to filter beans from amway packages.
		this.appendingAmwayStackActivated = tenant.getConfig().getBoolean("db.log.appendAmwayStackTrace", false);

		//Load all sub loggers
		loadSubLogs();
	}

	/**
	 * Log a sql statement to main and sub loggers
	 * @param threadId
	 * @param dataSourceID
	 * @param connectionId
	 * @param now
	 * @param elapsed
	 * @param category
	 * @param prepared
	 * @param sql
	 */
	public void logSQL(long threadId, String dataSourceID, int connectionId, String now, long elapsed, String category,
			String prepared, String sql)
	{
		//Hash a key on the prepared stmt to uniquely ID queries
		long exactHash = hashBash(prepared);

		//Log to the default logger - JDBC.log
		logText(
				exactHash + "|" + threadId + "|" + dataSourceID + "|" + now + "|" + elapsed + " ms|" + category + "|" + prepared + "|"
						+ sql);

		//Log this stmts to all applicable sub loggers
		logSQLSubLogs(exactHash, threadId, dataSourceID, connectionId, now, elapsed, category, prepared, sql);
	}


	private void loadSubLogs()
	{
		//Item loggers
		loadSubLogs(subLogRegistryItems, tenantContext.getConfig().getParameter("amway.db.sublogsItems"));
		//Bean loggers
		loadSubLogs(subLogRegistryBeans, tenantContext.getConfig().getParameter("amway.db.sublogsBeans"));

	}

	private void loadSubLogs(Map registry, String json)
	{
		if (json != null)
		{
			try
			{
				final HashMap<String, String> subLoggers = new ObjectMapper().readValue(json, HashMap.class);
				for (String fileName : subLoggers.keySet())
				{
					if (!registry.containsKey(fileName))
					{
						AmwayJDBCSubLogger subLogger = new AmwayJDBCSubLoggerImpl(tenantContext);
						subLogger.setLogfile(fileName);
						subLogger.setIncludeTokens(subLoggers.get(fileName));
						registry.put(fileName, subLogger);
					}

				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}


	}

	private void logSQLSubLogs(long exactHash, long threadId, String dataSourceID, int connectionId, String now, long elapsed,
			String category, String prepared, String sql)
	{
		String amwayStack = "";
		if (this.isAmwayStackAppendingActivated())
		{
			amwayStack = this.buildAmwayStackTraceCompact(new Exception());
		}

		//Spin item loggers
		if (!subLogRegistryItems.isEmpty())
		{
			for (AmwayJDBCSubLogger subLogger : subLogRegistryItems.values())
			{
				subLogger.logSQL(exactHash, threadId, connectionId, elapsed, prepared, amwayStack);
			}
		}

		//Spin bean loggers
		if (!subLogRegistryBeans.isEmpty())
		{
			for (AmwayJDBCSubLogger subLogger : subLogRegistryBeans.values())
			{
				subLogger.logSQL(exactHash, threadId, connectionId, elapsed, prepared, amwayStack);
			}
		}
	}

	private long hashBash(String hashee)
	{
		long hash = HASH_LARGE_PRIME;
		for (int i = 0; i < hashee.length(); i++)
		{
			hash = hash * HASH_LARGE_PRIME + hashee.charAt(i);
		}
		hash = hash % HASH_LARGE_PRIME_MAX;

		//don't return negatives
		return hash > 0 ? hash : hash * -1;
	}

	/**
	 * Build a stack string with only Amway or Lynx classes...
	 * @param thr
	 * @return
	 */
	private String buildAmwayStackTraceCompact(Throwable thr)
	{
		StringBuilder buf = new StringBuilder();

		for (StackTraceElement ste : thr.getStackTrace())
		{
			String clName = ste.getClassName();
			//Bail as soon as possible
			if (clName.startsWith("org.apache.catalina"))
			{
				break;
			}
			String lowerName = clName.toLowerCase();
			if (lowerName.indexOf("amwayjdbclogger") <= 0)
			{
				if (lowerName.indexOf("amway") > 0 || lowerName.indexOf("lynx") > 0)
				{
					if (clName.contains("."))
					{
						clName = clName.substring(clName.lastIndexOf(46) + 1);
					}

					buf.append(clName + ":" + ste.getLineNumber() + ",");
				}
			}


		}

		return buf.toString();
	}


	public boolean isAmwayStackAppendingActivated()
	{
		return this.appendingAmwayStackActivated;
	}

	public void destroy()
	{
		tenantContext.getConfig().unregisterConfigChangeListener(this.cfgChangeListener);
	}

	private void clearAllSubLogs()
	{
		//Clear items log
		clearSubLogs(subLogRegistryItems);
		subLogRegistryItems = new HashMap<String, AmwayJDBCSubLogger>();

		//Clear bean logs
		clearSubLogs(subLogRegistryBeans);
		subLogRegistryBeans = new HashMap<String, AmwayJDBCSubLogger>();

	}

	private void clearSubLogs(Map<String, AmwayJDBCSubLogger> registry)
	{
		//Clear items log
		for (String fileName : registry.keySet())
		{
			try
			{
				FileUtils.forceDelete(new File(fileName));

			}
			catch (IOException e)
			{
				//no one cares
				//e.printStackTrace();
			}
		}

	}

	private void prepareStats()
	{
		prepLogStats(subLogRegistryItems);
		prepLogStats(subLogRegistryBeans);
	}

	private void prepLogStats(Map<String, AmwayJDBCSubLogger> registry)
	{
		//Clear items log
		for (AmwayJDBCSubLogger logger : registry.values())
		{
			logger.prepStats();
		}

	}

	/**
	 * tell all sub loggers to dump a histogram cuz logging was turned off.
	 */
	private void logStats()
	{

		logStats(subLogRegistryItems);
		logStats(subLogRegistryBeans);

	}

	private void logStats(Map<String, AmwayJDBCSubLogger> registry)
	{
		//Clear items log
		for (AmwayJDBCSubLogger logger : registry.values())
		{
			logger.logStats();
		}

	}

	/**
	 * Inner class to listen for config setting changes related to sub loggers
	 */
	private class AmwayJDBCLogUtilConfigListener implements ConfigIntf.ConfigChangeListener
	{
		private AmwayJDBCLogUtilConfigListener()
		{
		}

		public void configChanged(String key, String value)
		{

			if(key.equals("db.log.active"))
			{
				boolean active = Boolean.parseBoolean(value);
				if (active) {
					AmwayJDBCLogger.this.prepareStats();
				} else {
					AmwayJDBCLogger.this.logStats();
				}
			}
			if (key.equals("amway.db.sublogsItems"))
			{
				AmwayJDBCLogger.this.loadSubLogs();
			}
			if (key.equals("amway.db.sublogsBeans"))
			{
				AmwayJDBCLogger.this.loadSubLogs();
			}
			if (key.equals("db.log.appendAmwayStackTrace"))
			{
				AmwayJDBCLogger.this.appendingAmwayStackActivated = Boolean.parseBoolean(value);
			}
			//Clear log in HAC causes this config change which is empty
			if (key.equals("db.log.file.path"))
			{
				if (value.equals("") || value.isEmpty())
				{
					clearAllSubLogs();
				}
				else
				{
					loadSubLogs();
				}
			}
		}
	}


}
