package net.woodburry.client.common;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/7/13
 * Time: 11:33 PM
 */
public class Hover {
    public static void enableHover(final Widget widget) {
        widget.sinkEvents(Event.MOUSEEVENTS);
        widget.addHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                widget.addStyleName("hovered");
            }
        }, MouseOverEvent.getType());

        widget.addHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                widget.removeStyleName("hovered");
            }
        }, MouseOutEvent.getType());
    }
}
