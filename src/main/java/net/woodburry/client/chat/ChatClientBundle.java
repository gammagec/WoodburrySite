package net.woodburry.client.chat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 10:56 PM
 */
public interface ChatClientBundle extends ClientBundle {

    public static final ChatClientBundle INSTANCE = GWT.create(ChatClientBundle.class);

    interface MyCss extends CssResource {
        String chatEntryBox();
        String chatDisplay();
    }

    @Source("chat.css")
    MyCss css();
}
