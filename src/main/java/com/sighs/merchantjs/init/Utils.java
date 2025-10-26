package com.sighs.merchantjs.init;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.List;

public class Utils {
    @HideFromJS
    public static void openMerchant(Player player, Component title, MerchantOffers offers) {
        var merchant = new VisualMerchant(player);
        merchant.overrideOffers(offers);

        merchant.openTradingScreen(player, title, 1);
    }

    public static void openMerchant(Player player, Component title, List<MerchantOfferJS> offerList) {
        List<MerchantOffer> merchantOffers = offerList.stream().map(MerchantOfferJS::toMerchantOffer).toList();
        MerchantOffers mfs = new MerchantOffers();
        mfs.addAll(merchantOffers);
        openMerchant(player, title, mfs);
    }
}
