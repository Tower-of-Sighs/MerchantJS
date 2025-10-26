package com.sighs.merchantjs;

import com.sighs.merchantjs.event.MerchantEvents;
import com.sighs.merchantjs.init.Utils;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;

public class MerchantJSPlugin implements KubeJSPlugin {

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("MerchantJSUtils", Utils.class);
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(MerchantEvents.GROUP);
    }
}
