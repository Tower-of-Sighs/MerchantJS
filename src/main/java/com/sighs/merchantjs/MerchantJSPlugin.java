package com.sighs.merchantjs;

import com.sighs.merchantjs.event.MerchantEvents;
import com.sighs.merchantjs.init.Utils;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;

public class MerchantJSPlugin extends KubeJSPlugin {

    public void registerEvents() {
        MerchantEvents.GROUP.register();
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("MerchantJSUtils", Utils.class);
    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {

    }
}
