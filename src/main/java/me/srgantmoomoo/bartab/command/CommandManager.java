package me.srgantmoomoo.bartab.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.bartab.command.commands.SetCommand;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class CommandManager {
	
	public List<Command> commands = new ArrayList<Command>();
	public static String prefix = "bartab";
	public boolean commandFound = false;
	
	public CommandManager() {
		MinecraftForge.EVENT_BUS.register(this);
		Main.EVENT_BUS.subscribe(this);
		register();
	}
	
	public void register() {
		commands.add(new SetCommand());
	}
	
	@EventHandler
    public Listener<ClientChatEvent> listener = new Listener<>(event -> {
        String message = event.getMessage();
        
        if(!message.startsWith(prefix))
        	return;
        
        event.setCanceled(true);
        message = message.substring(prefix.length());
        if(message.split(" ").length > 0) {
        	boolean commandFound = false;
        	String commandName = message.split(" ")[0];
        	for(Command c : commands) {
        		if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
        		c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
        		commandFound = true;
        		break;
        		}
        	}
        	if(!commandFound) {
        		addChatMessage("welcome to bartab!");
        	}
        }
    });
	
	@SubscribeEvent
	public void key(KeyInputEvent e) {
		if (prefix.length() == 1) {
            final char key = Keyboard.getEventCharacter();
            if (prefix.charAt(0) == key) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat());
                ((GuiChat) Minecraft.getMinecraft().currentScreen).inputField.setText(prefix);
            }
        }
	}
	
	public static void setCommandPrefix(String pre) {
        prefix = pre;
        
        /*if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}*/
    }
	
	public static String getCommandPrefix(String name) {
        return prefix;
    }
	
	public static void addChatMessage(String message) {
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
	
	public static void correctUsageMsg(String name, String syntax) {
		// usage
		String usage = "correct usage of " + name + " command -> " + prefix + syntax;
				
		// prefix
		String message = ChatFormatting.AQUA + "@" + ChatFormatting.ITALIC + Reference.NAME + ChatFormatting.GRAY + ": " + usage;
		
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
	
}
