package com.amway.amwayperformance.jdbcwrapper.logger;

/**
 * Created by aiueq92 on 11/1/17.
 */
public interface AmwayJDBCSubLogger
{

	void logSQL(long hardMatchHash, long threadId, int connectionId, long elapse, String preparedSql);

	void logSQL(long hardMatchHash, long threadId, int connectionId, long elapse, String preparedSql, String amwayStack);

	public void setLogfile(String fileName);

	public void setIncludeTokens(String includeTables);

	public void prepStats();

	public void logStats();
}
