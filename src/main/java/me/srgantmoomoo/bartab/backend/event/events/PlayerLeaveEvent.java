package me.srgantmoomoo.bartab.backend.event.events;

import me.srgantmoomoo.bartab.backend.event.Event;

public class PlayerLeaveEvent extends Event {

	private final String name;

	public PlayerLeaveEvent(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}