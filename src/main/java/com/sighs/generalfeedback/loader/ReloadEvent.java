package com.sighs.generalfeedback.loader;

import com.sighs.generalfeedback.Generalfeedback;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.CommandEvent;


@EventBusSubscriber(modid = Generalfeedback.MODID)
public class ReloadEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onCommand(CommandEvent event) {
        String rawCommand = event.getParseResults().getReader().getString();
        if (rawCommand.equals("reload")) EntryCache.loadAllRule();
    }
}
