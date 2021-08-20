package com.ieireference.system.event;

/**
 * Represents a universal interface for use of events, the {@code TEventArgs} is a template of class with event arguments.
 * */
public interface EventListener<TEventArgs> {

	/**
	 * 
	 * A function of action when the event is active.
	 * @param e The class args to get.
	 * */
	public void action(TEventArgs e);
	
}
