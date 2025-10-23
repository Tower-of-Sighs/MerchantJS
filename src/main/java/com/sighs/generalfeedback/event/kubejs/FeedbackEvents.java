package com.sighs.generalfeedback.event.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface FeedbackEvents {

    EventGroup GROUP = EventGroup.of("FeedbackEvents");

    EventHandler SUBMIT_EVENT = GROUP.client("onSubmit", () -> SubmitEventJS.class).hasResult();

}
