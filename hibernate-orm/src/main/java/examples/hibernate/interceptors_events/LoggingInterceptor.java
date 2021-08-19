package examples.hibernate.interceptors_events;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

class LoggingInterceptor extends EmptyInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onFlushDirty(
			Object entity,
			Serializable id,
			Object[] currentState,
			Object[] previousState,
			String[] propertyNames,
			Type[] types) {
		System.out.println( MessageFormat.format("Entity {0}#{1} changed from {2} to {3}",
				entity.getClass().getSimpleName(),
				id,
				Arrays.toString( previousState ),
				Arrays.toString( currentState )
				));
		return super.onFlushDirty( entity, id, currentState,
				previousState, propertyNames, types
				);
	}
}