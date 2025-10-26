package com.sighs.merchantjs.mixin;

import com.sighs.merchantjs.event.MerchantEvents;
import com.sighs.merchantjs.event.SwitchTradeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.inventory.MerchantMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MerchantScreen.class)
public class MerchantScreenMixin extends Screen {
    @Shadow private int shopItem;

    protected MerchantScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "postButtonClick", at = @At("HEAD"))
    private void event(CallbackInfo ci) {
        if (Minecraft.getInstance().player.containerMenu instanceof MerchantMenu menu) {
            MerchantEvents.SWITCH_TRADE.post(new SwitchTradeEvent(menu.getOffers(), menu.getOffers().get(shopItem), getTranslationKey(title)));
        }
    }

    private static String getTranslationKey(Component component) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatable) {
            return translatable.getKey();
        }
        return null;
    }
}
