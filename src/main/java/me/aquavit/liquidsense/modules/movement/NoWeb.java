package me.aquavit.liquidsense.modules.movement;

import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.ListValue;

@ModuleInfo(name = "NoWeb", description = "Prevents you from getting slowed down in webs.", category = ModuleCategory.MOVEMENT)
public class NoWeb extends Module {
    private ListValue modeValue = new ListValue("Mode", new String[] {"None", "AAC", "LAAC", "Rewi"}, "None");

    @EventTarget
    public void onUpdate(UpdateEvent event){
        if (!mc.thePlayer.isInWeb)
            return;

        switch (modeValue.get()){
            case "None": {
                mc.thePlayer.isInWeb = false;
                break;
            }
            case "AAC": {
                mc.thePlayer.jumpMovementFactor = 0.59f;

                if (!mc.gameSettings.keyBindSneak.isKeyDown())
                    mc.thePlayer.motionY = 0;
                break;
            }
            case "LAAC": {
                mc.thePlayer.jumpMovementFactor = mc.thePlayer.movementInput.moveStrafe != 0f ? 1.0f : 1.21f;

                if (!mc.gameSettings.keyBindSneak.isKeyDown())
                    mc.thePlayer.motionY = 0;

                if (mc.thePlayer.onGround)
                    mc.thePlayer.jump();
                break;
            }
            case "Rewi": {
                mc.thePlayer.jumpMovementFactor = 0.42f;

                if (mc.thePlayer.onGround)
                    mc.thePlayer.jump();
                break;
            }
        }
    }

    @Override
    public String getTag() {
        return modeValue.get();
    }
}
