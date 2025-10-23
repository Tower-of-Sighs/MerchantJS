package com.sighs.generalfeedback.event.kubejs;

import com.sighs.generalfeedback.event.SubmitEvent;
import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;

public class CommonEventsPostJS {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onFeedbackSubmit(SubmitEvent event) {
        if (FeedbackEvents.SUBMIT_EVENT.hasListeners()) {
            EventResult result = FeedbackEvents.SUBMIT_EVENT.post(ScriptType.CLIENT, new SubmitEventJS(event));
            if (result.interruptFalse()) {
                event.setCanceled(true);
            }
        }
    }
}
