package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import me.aquavit.liquidsense.utils.render.BlurBuffer;
import me.aquavit.liquidsense.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;


@ElementInfo(name = "KeyBinds")
public class KeyBinds extends Element {

	@Nullable
	@Override
	public Border drawElement() {
		int index = 0;
		for (Module module : LiquidBounce.moduleManager.getModules()) {
			if (module.getKeyBind() == Keyboard.KEY_NONE)
				continue;
			index++;
		}
		BlurBuffer.blurArea((int) ((-4.5F + this.getRenderX()) * this.getScale()),
			(int) ((this.getRenderY() + Fonts.csgo40.FONT_HEIGHT - 2) * this.getScale()),
			(Fonts.csgo40.getStringWidth("F") + Fonts.font20.getStringWidth("Binds") + 67) * this.getScale(),
			(8 + index * 14) * this.getScale(),
			true);

		if (!this.getInfo().disableScale())
			GL11.glScalef(this.getScale(), this.getScale(), this.getScale());
		GL11.glTranslated(this.getRenderX(), this.getRenderY(), 0.0);

		int y = 1;
		for (Module module : LiquidBounce.moduleManager.getModules()) {
			if(module.getKeyBind() == Keyboard.KEY_NONE && module.getKeytranslate().getY() > 0f)
				module.getKeytranslate().translate(0f, 0f);
                  else {

				GlStateManager.resetColor();

				module.getKeytranslate().translate(0f, 14f);

				if (module.getState()) {
					Fonts.font20.drawString(module.getName(), -1.1F, y + 17, Color.WHITE.getRGB());
					Fonts.font20.drawString("on", Fonts.csgo40.getStringWidth("F") + Fonts.font20.getStringWidth("Binds") + 46F, y + 17, Color.WHITE.getRGB());
				} else {
					Fonts.font20.drawString(module.getName(), -1.1F, y + 17, Color.WHITE.getRGB());
					Fonts.font20.drawString("off", Fonts.csgo40.getStringWidth("F") + Fonts.font20.getStringWidth("Binds") + 45, y + 17, Color.WHITE.getRGB());

				}
				y += module.getKeytranslate().getY();
			}
		}
		RenderUtils.drawRoundedRect(-5.2F, -5.5F, Fonts.csgo40.getStringWidth("K") + Fonts.font20.getStringWidth("Binds") + 65, Fonts.csgo40.FONT_HEIGHT + 6F, 1.5F,
			new Color(16, 25, 32, 200).getRGB(), 1F, new Color(16, 25, 32, 200).getRGB());
		//RenderUtils.drawBorderedRect(-5.5F, -5.5F, Fonts.csgo40.getStringWidth("K") + Fonts.font20.getStringWidth("Binds") + 60, Fonts.csgo40.FONT_HEIGHT + 0.5F, 3F, new Color(16, 25, 32, 200).getRGB(), new Color(16, 25, 32, 200).getRGB());
		Fonts.csgo40.drawString("K", -1.5F, -1.5F, new Color(0, 131, 193).getRGB(), false);
		Fonts.font20.drawString("Binds", Fonts.csgo40.getStringWidth("K") + 1.8F, -1F, Color.WHITE.getRGB(), false);
		return new Border(20, 20, 120, 80);
	}
}
