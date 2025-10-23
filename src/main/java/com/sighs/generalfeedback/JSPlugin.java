package com.sighs.generalfeedback;

import com.sighs.generalfeedback.event.kubejs.CommonEventsPostJS;
import com.sighs.generalfeedback.event.kubejs.FeedbackEvents;
import com.sighs.generalfeedback.utils.FeedbackUtils;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.neoforged.neoforge.common.NeoForge;

public class JSPlugin implements KubeJSPlugin {

    @Override
    public void init() {
        NeoForge.EVENT_BUS.register(CommonEventsPostJS.class);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        if (bindings.type() == ScriptType.CLIENT) {
            bindings.add("FeedbackUtils", FeedbackUtils.class);
        }
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(FeedbackEvents.GROUP);
    }
}
