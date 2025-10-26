package com.sighs.merchantjs.init;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.List;

public class Utils {
    public static void openMerchant(Player player, Component title, MerchantOffers offers) {
        var merchant = new VisualMerchant(player);
        merchant.overrideOffers(offers);

        merchant.openTradingScreen(player, title, 1);
    }

    public static void openMerchant(Player player, Component title, List<MerchantOffer> offerList) {
        var offers = new MerchantOffers();
        offers.addAll(offerList);
        openMerchant(player, title, offers);
    }

    public static MerchantOffer createMerchantOffer(CompoundTag nbt) {
        CompoundTag tag = new CompoundTag();

        tag.put("buyB", ItemStack.EMPTY.save(new CompoundTag()));
        tag.putInt("uses", 0);
        tag.putInt("maxUses", 99);
        tag.putFloat("priceMultiplier", 0);
        tag.putInt("demand", 0);

        return new MerchantOffer(tag.merge(nbt));
    }
}
