package dev.htgl;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class HtGl implements ClientModInitializer {
	public static KeyMapping key;

	public void onInitializeClient() {
		Cfg.ld();
		KeyMapping k = key = KeyMappingHelper.registerKeyMapping(new KeyMapping("key.hitglow.open", InputConstants.KEY_G, KeyMapping.Category.register(Identifier.fromNamespaceAndPath("hitglow", "main"))));
		ClientTickEvents.END_CLIENT_TICK.register(m -> {
			while (k.consumeClick()) if (m.gui.screen() == null) {
				if (net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("wysslib")) m.gui.setScreen(new dev.htgl.ui.Panel(null));
			}
		});
	}
}
