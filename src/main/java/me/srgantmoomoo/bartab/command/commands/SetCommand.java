package me.srgantmoomoo.bartab.command.commands;

import me.srgantmoomoo.bartab.command.Command;
import me.srgantmoomoo.bartab.command.CommandManager;

public class SetCommand extends Command {

	public SetCommand() {
		super("-set", "set command for bartab.", "set <number> <key>", "-set");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 0) {
			CommandManager.addChatMessage("this");
		}
	}
	
}