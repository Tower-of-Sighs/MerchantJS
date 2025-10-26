package com.sighs.merchantjs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;

@Getter
public class AfterTradeEventJS implements KubeEvent {
    private final Merchant merchant;
    private final MerchantOffer offer;

    public AfterTradeEventJS(Merchant merchant, MerchantOffer offer) {
        this.merchant = merchant;
        this.offer = offer;
    }

    public Player getPlayer() {
        return merchant.getTradingPlayer();
    }
}
