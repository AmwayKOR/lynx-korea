package com.amway.amwayperformance.jdbcwrapper.logger.impl;

import de.hybris.platform.core.Tenant;
import de.hybris.platform.jdbcwrapper.logger.FileLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.amway.amwayperformance.jdbcwrapper.logger.AmwayJDBCSubLogger;


/**
 * Created by aiueq92 on 11/1/17.
 */
public class AmwayJDBCSubLoggerImpl extends FileLogger implements AmwayJDBCSubLogger
{

	Tenant tenantContext = null;
	private String[] includeTokens;
	//Store as a list to allow more detailed reporting based on amwayStack line #
	private HashMap<String,List<StatBean>> statMap = new HashMap<String,List<StatBean>> ();

	/**
	 * Init a sub logger
	 * @param tenant
	 */
	public AmwayJDBCSubLoggerImpl(Tenant tenant)
	{
		//Setup config context
		super(tenant);
		tenantContext = tenant;
	}

	/**
	 * Custom log stmt for sub loggers
	 * @param hardMatchHash
	 * @param threadId
	 * @param connectionId
	 * @param elapsed
	 * @param preparedSql
	 * @param amwayStack
	 */
	public void logSQL(long hardMatchHash, long threadId, int connectionId, long elapsed, String preparedSql,
			String amwayStack)
	{
		//Is the query a match for this sub logger
		if (queryOk(preparedSql) || beanOk(amwayStack))
		{
			this.logText(hardMatchHash + "|" + elapsed + " ms|" + preparedSql + "|" + amwayStack);
			logQueryToStats(hardMatchHash,preparedSql,amwayStack);
		}
	}

	/**
	 * Overload method for no class stack
	 * @param hardMatchHash
	 * @param threadId
	 * @param connectionId
	 * @param elapsed
	 * @param preparedSql
	 */
	public void logSQL(long hardMatchHash, long threadId, int connectionId, long elapsed, String preparedSql)
	{
		logSQL(hardMatchHash, threadId, connectionId, elapsed, preparedSql, "");
	}

	/**
	 *
	 * @param value
	 */
	public void setIncludeTokens(String value)
	{
		if (value == null)
		{
			value = "";
		}

		this.includeTokens = parseCSVList(value);
	}

	public void prepStats() {
		statMap = new HashMap<String,List<StatBean>> ();

	}

	/**
	 * Take a snap and dump a histogram as a log session ends.
	 */
	public void logStats() {
      if (statMap.size() > 0) {
      	StringBuffer queryLegend = new StringBuffer();
			this.logText("\n***** amway sub logging deactivated ***** " +tenantContext.getTenantID());
			this.logText("***** table or bean histogram - count : queryId ***** " +tenantContext.getTenantID());
			int totalQueries = 0;
			for (List listStat: statMap.values()) {
				if (listStat != null) {
					//for now get first bean and log
					StatBean statBean = (StatBean) listStat.get(0);
					logText(listStat.size() + " : " + statBean.getQueryId());
					totalQueries+=listStat.size();
					queryLegend.append(statBean.getQueryId()+ " : " +statBean.getPreparedStmt()+"\n");
				}
			}
			this.logText(totalQueries+":Total");
			this.logText("\n***** queryId legend *****");
			this.logText("***** queryId : query statement ***** " +tenantContext.getTenantID());
			this.logText(queryLegend.toString());
		}
	}

	/*
	Test whether this item logger wants to log the current query
	 */
	private boolean queryOk(String sql)
	{
		return (this.includeTokens == null || this.includeTokens.length == 0 || this.foundTable(sql, this.includeTokens));
	}

	/*
	Test whether this bean logger wants to log the current query
	 */
	private boolean beanOk(String stack)
	{
		return (this.includeTokens == null || this.includeTokens.length == 0 || this.foundTable(stack, this.includeTokens));
	}

	/*
	Nasty code lifted from Hybris
	 */
	private boolean foundTable(String sql, String[] tables)
	{
		sql = sql.toLowerCase();
		boolean isOk = false;
		if (tables != null)
		{
			for (int i = 0; !isOk && i < tables.length; ++i)
			{
				isOk = sql.indexOf(tables[i]) > 0;
			}
		}

		return isOk;
	}

	/*
	Nasty code lifted from Hybris
	 */
	private static String[] parseCSVList(String csvList)
	{
		String[] array = null;
		if (csvList != null)
		{
			StringTokenizer tok = new StringTokenizer(csvList, ",");
			ArrayList list = new ArrayList();

			while (tok.hasMoreTokens())
			{
				String item = tok.nextToken().toLowerCase().trim();
				if (item.length() > 0)
				{
					list.add(item.toLowerCase().trim());
				}
			}

			int max = list.size();
			Iterator<String> iterator = list.iterator();
			array = new String[max];

			for (int index = 0; index < max; ++index)
			{
				array[index] = (String) iterator.next();
			}
		}

		return array;
	}

	private void logQueryToStats(long queryId, String preparedSql, String amwayStack) {

		List statList = statMap.get(""+queryId);
		if (statList == null) {
			statList = new ArrayList<StatBean>();
			statMap.put(""+queryId,statList);
		}
		statList.add(new StatBean(queryId,preparedSql,amwayStack));
	}

	private class StatBean {

		String preparedStmt = null;
		String amwayStack = null;
		long queryId = 0;

		public StatBean(long queryId, String preparedStmt, String amwayStack)
		{
			this.preparedStmt = preparedStmt;
			this.amwayStack = amwayStack;
			this.queryId = queryId;
		}


		public String getPreparedStmt()
		{
			return preparedStmt;
		}

		public void setPreparedStmt(String preparedStmt)
		{
			this.preparedStmt = preparedStmt;
		}

		public String getAmwayStack()
		{
			return amwayStack;
		}

		public void setAmwayStack(String amwayStack)
		{
			this.amwayStack = amwayStack;
		}

		public long getQueryId()
		{
			return queryId;
		}

		public void setQueryId(long queryId)
		{
			this.queryId = queryId;
		}

	}

}
