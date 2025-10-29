package com.sighs.merchantjs.init;

import dev.latvian.mods.kubejs.plugin.builtin.wrapper.ItemWrapper;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.ContextFactory;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.List;
import java.util.Optional;

public class Utils {
    @HideFromJS
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

        tag.put("buyB", new CompoundTag());
        tag.putInt("uses", 0);
        tag.putInt("maxUses", 99);
        tag.putFloat("priceMultiplier", 0);
        tag.putInt("demand", 0);

        CompoundTag merge = tag.merge(nbt);

        Context ctx = new Context(new ContextFactory());

        String buy = merge.getString("buy").replaceAll("'", "");
        String buyB = merge.getString("buyB").replaceAll("'", "");
        String sell = merge.getString("sell").replaceAll("'", "");
        int uses = merge.getInt("uses");
        int maxUses = merge.getInt("maxUses");
        int xp = merge.getInt("xp");
        int priceMultiplier = merge.getInt("priceMultiplier");
        int demand = merge.getInt("demand");

        ItemStack buyItem = ItemWrapper.wrap(ctx, buy);
        ItemStack buyBItem = ItemWrapper.wrap(ctx, buyB);
        ItemStack sellItem = ItemWrapper.wrap(ctx, sell);

        return new MerchantOffer(
                new ItemCost(Holder.direct(buyItem.getItem()), buyItem.getCount(), DataComponentPredicate.allOf(buyItem.getComponents())),
                Optional.of(new ItemCost(Holder.direct(buyBItem.getItem()), buyBItem.getCount(), DataComponentPredicate.allOf(buyBItem.getComponents()))),
                sellItem,
                uses,
                maxUses,
                xp,
                priceMultiplier,
                demand
        );
    }
}
