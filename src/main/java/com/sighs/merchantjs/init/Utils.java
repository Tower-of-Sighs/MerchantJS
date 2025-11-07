package com.sighs.merchantjs.init;

import com.mojang.serialization.Dynamic;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.List;
import java.util.Objects;
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

        Tag buy = merge.get("buy");
        Tag buyB = merge.get("buyB");
        Tag sell = merge.get("sell");
        int uses = merge.getInt("uses");
        int maxUses = merge.getInt("maxUses");
        int xp = merge.getInt("xp");
        int priceMultiplier = merge.getInt("priceMultiplier");
        int demand = merge.getInt("demand");

        ItemStack buyItem = ItemStack.CODEC.parse(new Dynamic<>(NbtOps.INSTANCE, buy)).getOrThrow();
        ItemStack buyBItem = Objects.equals(buyB, new CompoundTag()) ? ItemStack.EMPTY : ItemStack.CODEC.parse(new Dynamic<>(NbtOps.INSTANCE, buyB)).getOrThrow();
        ItemStack sellItem = ItemStack.CODEC.parse(new Dynamic<>(NbtOps.INSTANCE, sell)).getOrThrow();

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
