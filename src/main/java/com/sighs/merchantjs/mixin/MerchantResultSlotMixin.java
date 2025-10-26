package com.sighs.merchantjs.mixin;

import com.sighs.merchantjs.event.AfterTradeEventJS;
import com.sighs.merchantjs.event.MerchantEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.inventory.MerchantResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MerchantResultSlot.class)
public class MerchantResultSlotMixin {
    @Shadow @Final private Merchant merchant;

    @Shadow @Final private MerchantContainer slots;

    @Inject(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/trading/Merchant;notifyTrade(Lnet/minecraft/world/item/trading/MerchantOffer;)V"))
    private void event(Player p_150631_, ItemStack p_150632_, CallbackInfo ci) {
        MerchantEvents.AFTER_TRADE.post(new AfterTradeEventJS(merchant, slots.getActiveOffer()));
    }
}
