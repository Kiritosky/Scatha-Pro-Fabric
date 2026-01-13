package namelessju.scathapro.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.gui.GuiNewChat;
import java.util.List;

@Mixin(GuiNewChat.class)
public interface GuiChatAccessor {
    @Accessor("chatLines")
    List<?> getChatLines();
}
