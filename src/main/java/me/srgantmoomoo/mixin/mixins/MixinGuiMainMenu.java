package me.srgantmoomoo.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.Reference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
	@Inject(method = {"drawScreen"}, at = {@At("TAIL")}, cancellable = true)
	public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
			FontRenderer fr = mc.fontRenderer;
		    fr.drawStringWithShadow(Reference.NAME + " is loaded in :D :)", 2, 2, 0xffffffff); 
	}
}