package com.sighs.merchantjs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

public class SwitchTradeEvent extends EventJS {
    private final MerchantOffers offers;
    private final MerchantOffer clickedOffer;
    private final String titleKey;

    public SwitchTradeEvent(MerchantOffers offers, MerchantOffer clickedOffer, String titleKey) {
        this.offers = offers;
        this.clickedOffer = clickedOffer;
        this.titleKey = titleKey;
    }

    public MerchantOffer getClickedOffer() {
        return clickedOffer;
    }

    public MerchantOffers getOffers() {
        return offers;
    }

    public String getTitleKey() {
        return titleKey;
    }
}
