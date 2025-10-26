package com.sighs.merchantjs.init;

import lombok.Builder;
import lombok.Data;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.Optional;

@Data
@Builder
public class MerchantOfferJS {
    private ItemStack buy;
    private ItemStack buyB;
    private ItemStack sell;
    private int uses;
    private int maxUses;
    private int xp;
    private float priceMultiplier;
    private int demand;

    public MerchantOfferJS(ItemStack buy, ItemStack buyB, ItemStack sell, Integer uses, Integer maxUses, Integer xp, Float priceMultiplier, Integer demand) {
        this.buy = buy;
        this.buyB = buyB != null ? buyB : ItemStack.EMPTY;
        this.sell = sell;
        this.uses = uses != null ? uses : 0;
        this.maxUses = maxUses != null ? maxUses : 99;
        this.xp = xp != null ? xp : 0;
        this.priceMultiplier = priceMultiplier != null ? priceMultiplier : 0;
        this.demand = demand != null ? demand : 0;
    }

    public MerchantOffer toMerchantOffer() {
        return new MerchantOffer(
                new ItemCost(this.getBuy().getItem()),
                Optional.of(new ItemCost(this.getBuyB().getItem())),
                this.getSell(),
                this.getUses(),
                this.getMaxUses(),
                this.getXp(),
                this.getPriceMultiplier(),
                this.getDemand()
        );
    }
}
