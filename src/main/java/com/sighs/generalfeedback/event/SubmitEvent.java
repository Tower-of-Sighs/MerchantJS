package com.sighs.generalfeedback.event;

import com.sighs.generalfeedback.init.Entry;
import com.sighs.generalfeedback.init.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

@Getter
@Setter
@AllArgsConstructor
public class SubmitEvent extends Event implements ICancellableEvent {
    private Entry entry;
    private Form form;
}
