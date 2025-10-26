package com.sighs.merchantjs.init;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.common.crafting.CraftingHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class TradeLoader {

    public static MerchantOffers loadTradesFromDatapack(String namespace, String path) {
        MerchantOffers offers = new MerchantOffers();
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

        ResourceLocation resLoc = new ResourceLocation(
            namespace, 
            "merchant/" + path + ".json"
        );

//        System.out.print(resLoc+"\n\n");
        System.out.print(resourceManager.listResources("merchant", resourceLocation -> {
            System.out.print(resourceLocation+"\n");
            return true;
        }));

        System.out.print(Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation("merchantjs", "merchant/test.json"))+"\n\n");

        // 获取资源
        List<Resource> resources = resourceManager.getResourceStack(resLoc);

        System.out.print(resources+"\n\n\n");

        for (Resource resource : resources) {
            int a = 0;
            System.out.print(++a+"\n\n");
            try {
                BufferedReader reader = resource.openAsReader();
                System.out.print(++a+"\n\n");
                JsonObject json = new Gson().fromJson(reader, JsonObject.class);
                System.out.print(++a+"\n\n");
                JsonArray tradesArray = json.getAsJsonArray("trades");
                System.out.print(tradesArray.getAsString()+"\n\n");

                for (JsonElement tradeElement : tradesArray) {
                    JsonObject tradeObj = tradeElement.getAsJsonObject();
                    MerchantOffer offer = parseTrade(tradeObj);
                    if (offer != null) {
                        offers.add(offer);
                    }
                }
            } catch (IOException e) {
                System.out.print(e+"\n\n\n");
            }
        }

        return offers;
    }

    private static MerchantOffer parseTrade(JsonObject tradeObj) {
        try {
            // 解析输入物品A
            JsonObject buyObj = tradeObj.getAsJsonObject("buy");
            ItemStack buyStack = CraftingHelper.getItemStack(buyObj, true);
            
            // 解析输入物品B（可选）
            ItemStack buyBStack = ItemStack.EMPTY;
            if (tradeObj.has("buyB")) {
                JsonObject buyBObj = tradeObj.getAsJsonObject("buyB");
                buyBStack = CraftingHelper.getItemStack(buyBObj, true);
            }
            
            // 解析输出物品
            JsonObject sellObj = tradeObj.getAsJsonObject("sell");
            ItemStack sellStack = CraftingHelper.getItemStack(sellObj, true);
            
            // 解析其他参数
            int maxUses = tradeObj.has("max_uses") ? tradeObj.get("max_uses").getAsInt() : 8;
            int xp = tradeObj.has("xp") ? tradeObj.get("xp").getAsInt() : 0;
            float priceMultiplier = tradeObj.has("price_multiplier") ? tradeObj.get("price_multiplier").getAsFloat() : 0.05f;
            int demand = tradeObj.has("demand") ? tradeObj.get("demand").getAsInt() : 0;
            
            // 创建交易
            return new MerchantOffer(
                buyStack, 
                buyBStack, 
                sellStack, 
                0,          // 初始使用次数
                maxUses, 
                xp, 
                priceMultiplier, 
                demand
            );
        } catch (Exception e) {
            // 处理解析错误
            e.printStackTrace();
            return null;
        }
    }
}