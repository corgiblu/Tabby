package net.xbyz.tabby.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerListHud.class)
public class TabSizeMixin {
	@ModifyConstant(constant = @Constant(longValue = 80L), method = "collectPlayerEntries")
	private long modifyCount(long count) {
		return 200L;
	}
	@ModifyConstant(constant = @Constant(intValue = 20), method = "render")
	private int modifyMaxRows(int MAX_ROWS) {
		return 35;
	}
}