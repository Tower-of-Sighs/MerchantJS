package com.sighs.merchantjs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface MerchantEvents {

    EventGroup GROUP = EventGroup.of("MerchantEvents");

    EventHandler AFTER_TRADE = GROUP.server("afterTrade", () -> AfterTradeEventJS.class);
    EventHandler SWITCH_TRADE = GROUP.client("switchTrade", () -> SwitchTradeEvent.class);

}
