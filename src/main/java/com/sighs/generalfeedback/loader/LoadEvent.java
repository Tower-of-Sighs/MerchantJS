package com.sighs.generalfeedback.loader;

import com.sighs.generalfeedback.Generalfeedback;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


@EventBusSubscriber(modid = Generalfeedback.MODID)
public class LoadEvent {
    @SubscribeEvent
    public static void onLoad(FMLClientSetupEvent event) {
        EntryCache.loadAllRule();
    }
}
