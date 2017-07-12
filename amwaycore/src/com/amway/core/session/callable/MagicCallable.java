package com.amway.core.session.callable;


import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.amway.core.session.MagicSessionDataHelper;


public class MagicCallable<T> implements Callable<T>
{
	private static final Logger LOG = Logger.getLogger(MagicCallable.class);
	private final String methodName;
	private final Object invokeObject;
	private final String sessionId;

	public MagicCallable(final String methodName, final String sessionId, final Object invokeObject)
	{
		this.methodName = methodName;
		this.sessionId = sessionId;
		this.invokeObject = invokeObject;
	}

	@Override
	public T call() throws Exception
	{
		final Class<MagicSessionDataHelper> c = MagicSessionDataHelper.class;
		final Method method;
		try
		{
			final long startTime = Calendar.getInstance().getTimeInMillis();
			Registry.activateMasterTenant();
			final JaloConnection jaloConnection = JaloConnection.getInstance();
			final JaloSession jSession = jaloConnection.getSession(sessionId);
			jSession.activate();
			method = c.getDeclaredMethod(methodName, new Class[] { boolean.class });
			final T result = (T) method.invoke(invokeObject, new Object[] { true });
			//jSession.deactivate();
			LOG.info("time for store setup (" + sessionId + ") magic call - " + methodName + " :" + (
					Calendar.getInstance().getTimeInMillis() - startTime));
			return result;
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e)
		{
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}
