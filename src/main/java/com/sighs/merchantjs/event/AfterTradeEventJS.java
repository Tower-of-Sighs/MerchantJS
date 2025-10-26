package com.sighs.merchantjs.event;

import com.sighs.merchantjs.Merchantjs;
import com.sighs.merchantjs.init.Utils;
import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Merchantjs.MODID)
public class AfterTradeEventJS extends EventJS {
    private final Merchant merchant;
    private final MerchantOffer offer;

    @SubscribeEvent
    public static void interact(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Pig) {
            var offers = Utils.loadMerchantOffers(Merchantjs.MODID, "test");
            Utils.openMerchant(event.getEntity(), Component.translatable("小飞猪卡通屋"), offers);
        }
    }

    public AfterTradeEventJS(Merchant merchant, MerchantOffer offer) {
        this.merchant = merchant;
        this.offer = offer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public MerchantOffer getOffer() {
        return offer;
    }

    public Player getPlayer() {
        return merchant.getTradingPlayer();
    }
}
