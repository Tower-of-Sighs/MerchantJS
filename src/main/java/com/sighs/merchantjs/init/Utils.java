package com.sighs.merchantjs.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffers;

public class Utils {
    public static void openMerchant(Player player, Component title, MerchantOffers offers) {
        var merchant = new VisualMerchant(player);
        merchant.overrideOffers(offers);

        merchant.openTradingScreen(player, title, 1);
    }

    public static MerchantOffers loadMerchantOffers(String namespace, String path) {
        return TradeLoader.loadTradesFromDatapack(namespace, path);
    }
}
