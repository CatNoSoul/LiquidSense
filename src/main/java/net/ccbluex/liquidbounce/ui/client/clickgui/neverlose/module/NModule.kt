package net.ccbluex.liquidbounce.ui.client.clickgui.neverlose.moduleimport me.aquavit.liquidsense.utils.render.ColorUtilsimport net.ccbluex.liquidbounce.features.module.Moduleimport net.ccbluex.liquidbounce.ui.client.miscible.`package`.ValueElementimport net.ccbluex.liquidbounce.ui.client.neverlose.Implimport net.ccbluex.liquidbounce.ui.client.neverlose.Mainimport net.ccbluex.liquidbounce.ui.font.Fontsimport net.ccbluex.liquidbounce.ui.font.GameFontRendererimport me.aquavit.liquidsense.utils.render.RenderUtilsimport net.ccbluex.liquidbounce.ui.client.clickgui.neverlose.implvalue.FloatValuedrawimport net.ccbluex.liquidbounce.ui.client.clickgui.neverlose.implvalue.ListValuedrawimport net.ccbluex.liquidbounce.ui.client.clickgui.neverlose.implvalue.MultiBoolValuedrawimport net.ccbluex.liquidbounce.ui.client.neverlose.implvalue.BooleanValuedrawimport net.ccbluex.liquidbounce.ui.client.neverlose.implvalue.IntegerValuedrawimport net.ccbluex.liquidbounce.value.*import net.minecraft.client.audio.PositionedSoundRecordimport net.minecraft.client.gui.FontRendererimport net.minecraft.client.renderer.GlStateManagerimport net.minecraft.util.ResourceLocationimport org.lwjgl.input.Mouseimport org.lwjgl.opengl.GL11import java.awt.Colorclass NModule(x: Float, y: Float, mX: Int, mY: Int, moduleclass: Module, mainclass: Main)  {    val positionX = x    val positionY = y    val mouseX = mX    val mouseY = mY    val module = moduleclass    val main = mainclass    var list = 0    var values: List<Value<*>>? = null    var valuePosY = 0    var MiidelKey = false    fun drawModule() {        val yes = main.hoverConfig(Impl.coordinateX.toInt() + 103f, Impl.coordinateY.toInt() + 60f, Impl.coordinateX.toInt() + 455f, Impl.coordinateY.toInt() + 335f, mouseX, mouseY, false)        if(main.hoverConfig(positionX, positionY, positionX + 173f,                            positionY + 20f + (20 * (module.values.size + module.outvalue)) , mouseX , mouseY , false) && yes) {            Fonts.font18.drawString(module.description , Impl.coordinateX + 105 , Impl.coordinateY  + 50 , -1)            if(Mouse.isButtonDown(2) && !main.midclick) {                Impl.openmidmanger = !Impl.openmidmanger                Impl.midmangermodule = module                if(Impl.openmidmanger) {                    Impl.midmangerPositionX = (mouseX - Impl.coordinateX).toInt()                    Impl.midmangerPositionY = (mouseY - Impl.coordinateY).toInt()                    Impl.midmangerSetnameString = module.arrayListName                }            }        }        GlStateManager.pushMatrix()        RenderUtils.makeScissorBox(Impl.coordinateX.toInt() + 103f, Impl.coordinateY.toInt() + 60f, Impl.coordinateX.toInt() + 455f,Impl.coordinateY.toInt() + 335f)        GL11.glEnable(GL11.GL_SCISSOR_TEST)        module.clickAnimation.translate( if (module.state) 10f else 0f, 0f)        if (main.hovertoFloatL(positionX , positionY + 3, positionX + 173f, positionY + 15f, mouseX, mouseY, true) && yes) {            module.state = !module.state        }        RenderUtils.drawRect(positionX, positionY, positionX + 173f, positionY + 20f + (20 * module.values.size + 12 * module.outvalue ),                if (Impl.hue == "white") Color(245,245,245).rgb else if (Impl.hue == "black") Color(11,11,14).rgb else Color(10, 22, 34).rgb) //功能背景        RenderUtils.drawRect(positionX + 3f, positionY + 17f, positionX + 170f, positionY + 18f,                if (Impl.hue == "white") Color(213,213,213).rgb else if (Impl.hue == "black") Color(29,29,29).rgb else Color(14, 26, 38).rgb) //功能栏下条        if (Impl.hue == "blue" || Impl.hue == "black" ) {            Fonts.font17.drawString(module.name, positionX + 6, positionY + 7, if( module.state)-1 else Color(175, 175 ,175).rgb)            RenderUtils.drawNLRect(positionX.toFloat() + 153, positionY.toFloat() + 6, positionX + 167f, positionY + 12f, 2.1f,                    if (module.state) Color(3, 23, 46).rgb else Color(7, 19, 31).rgb) //功能开启按钮            RenderUtils.drawFullCircle(positionX.toFloat() + 155 + module.clickAnimation.x, positionY.toFloat() + 9, 3.5f, 0f,                    if (module.state) Color(3, 168, 245) else Color(74, 87, 97))        } else {            Fonts.font17.drawString(module.name, positionX + 6, positionY + 7, if( module.state)Color(1, 1, 1).rgb else Color(90, 90, 90).rgb)            RenderUtils.drawNLRect(positionX.toFloat() + 153, positionY.toFloat() + 6, positionX + 167f, positionY + 12f, 2.1f,                    if (module.state) Color(0,120,194).rgb else Color(230,230,230).rgb) //功能开启按钮            RenderUtils.drawFullCircle(positionX.toFloat() + 155 + module.clickAnimation.x, positionY.toFloat() + 9, 3.5f, 0f,                    if (module.state) Color(255,255,255) else Color(255,255,255))        }        // Draw settings        val moduleValues: List<Value<*>> = module.values        module.outvalue = 0        if (moduleValues.isNotEmpty()) {            valuePosY = 0            module.openValue.translate(0f, 20f)            for (value in module.values) {                if (value is BoolValue) {                    val bool = BooleanValuedraw(this , value)                    bool.yes = yes                    bool.draw()                }                else if (value is ListValue) {                    val list = ListValuedraw(this, value)                    list.yes = yes                    list.draw()                }                else if (value is MultiBoolValue) {                    val multi = MultiBoolValuedraw(this, value)                    multi.yes = yes                    multi.draw()                }                else if (value is IntegerValue) {                    val int = IntegerValuedraw(this, value)                    int.yes = yes                    int.draw()                }                else if (value is FloatValue) {                    val float = FloatValuedraw(this, value)                    float.yes = yes                    float.draw()                }                else if (value is FontValue) {                    val fonts = Fonts.getFonts()                    var displayString = "Font: Unknown"                    if (value.get() is GameFontRenderer) {                        val liquidFontRenderer = value.get() as GameFontRenderer                        displayString = "Font: " + liquidFontRenderer.defaultFont.font.name + " - " + liquidFontRenderer.defaultFont.font.size                    }                    else if (value.get() === Fonts.minecraftFont) displayString = "Font: Minecraft"                    else {                        val objects = Fonts.getFontDetails(value.get())                        if (objects != null) {                            displayString = objects[0].toString() + if (objects[1] as Int != -1) " - " + objects[1] else ""                        }                    }                    if (main.hovertoFloatL(positionX + 8f, positionY + 22f + valuePosY, positionX + 120f, positionY + 34f + valuePosY,                                           mouseX, mouseY, true) && yes) {                        ValueElement.mc.soundHandler.playSound(PositionedSoundRecord.create(ResourceLocation("gui.button.press"), 5f))                        var i = 0                        while (i < fonts.size) {                            val font: FontRenderer = fonts.get(i)                            if (font === value.get()) {                                i++                                if (i >= fonts.size) i = 0                                value.set(fonts.get(i))                                break                            }                            i++                        }                    }                    if (main.hovertoFloatR(positionX + 8f, positionY + 22f + valuePosY, positionX + 120f, positionY + 34f + valuePosY,                                           mouseX, mouseY, true) && yes && module.showSettings) {                        ValueElement.mc.soundHandler.playSound(PositionedSoundRecord.create(ResourceLocation("gui.button.press"), 5f))                        var i = fonts.size - 1                        while (i >= 0) {                            val font = fonts[i]                            if (font === value.get()) {                                i--                                if (i >= fonts.size) i = 0                                if (i < 0) i = fonts.size - 1                                value.set(fonts[i])                                break                            }                            i--                        }                    }                    main.drawText(displayString, 100, Fonts.font15,positionX + 8, positionY + 25 + valuePosY, Color(175, 175, 175).rgb)                    valuePosY += module.openValue.y.toInt()                }                else {                    main.drawText(ColorUtils.translateAlternateColorCodes(value.name + " : " + value.get()), 45, Fonts.font17, positionX + 6, positionY + 27 + valuePosY, Color(175, 175, 175).rgb)                    valuePosY += module.openValue.y.toInt()                }            }        }        GL11.glDisable(GL11.GL_SCISSOR_TEST)        GlStateManager.popMatrix()    }}