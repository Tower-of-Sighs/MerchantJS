package com.sighs.merchantjs.event;

import com.sighs.merchantjs.Merchantjs;
import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Merchantjs.MODID)
public class AfterTradeEventJS extends EventJS {
    private final Merchant merchant;
    private final MerchantOffer offer;

    public AfterTradeEventJS(Merchant merchant, MerchantOffer offer) {
        this.merchant = merchant;
        this.offer = offer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public MerchantOffer getOffer() {
        return offer;
    }

    public Player getPlayer() {
        return merchant.getTradingPlayer();
    }
}
