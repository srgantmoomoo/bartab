package me.srgantmoomoo.bartab.backend.event.events;

import me.srgantmoomoo.bartab.backend.event.Event;

public class PlayerJoinEvent extends Event {

	private final String name;

	public PlayerJoinEvent(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}