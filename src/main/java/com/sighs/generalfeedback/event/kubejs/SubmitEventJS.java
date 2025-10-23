package com.sighs.generalfeedback.event.kubejs;

import com.sighs.generalfeedback.event.SubmitEvent;
import com.sighs.generalfeedback.init.Entry;
import com.sighs.generalfeedback.init.Form;
import dev.latvian.mods.kubejs.event.KubeEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitEventJS implements KubeEvent {
    private Entry entry;
    private Form form;

    public SubmitEventJS(SubmitEvent event) {
        this.entry = event.getEntry();
        this.form = event.getForm();
    }
}
