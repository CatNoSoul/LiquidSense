package net.ccbluex.liquidbounce.features.command.commands;

import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.utils.misc.StringUtils;

public class SayCommand extends Command
{
    public SayCommand() {
        super("say");
    }

    @Override
    public void execute(final String[] args) {
        if (args.length > 1) {
            mc.thePlayer.sendChatMessage(StringUtils.toCompleteString(args, 1));
            this.chat("Message was send to the chat.");
            return;
        }
        this.chatSyntax(".say <message...>");
    }
}
