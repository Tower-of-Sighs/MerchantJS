package com.sighs.merchantjs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import lombok.Getter;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

@Getter
public class SwitchTradeEvent implements KubeEvent {
    private final MerchantOffers offers;
    private final MerchantOffer clickedOffer;
    private final String titleKey;

    public SwitchTradeEvent(MerchantOffers offers, MerchantOffer clickedOffer, String titleKey) {
        this.offers = offers;
        this.clickedOffer = clickedOffer;
        this.titleKey = titleKey;
    }

}
