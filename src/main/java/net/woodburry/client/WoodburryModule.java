package net.woodburry.client;

import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.binder.GinBinder;
import net.woodburry.client.chat.ChatManager;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/9/13
 * Time: 11:25 PM
 */
public class WoodburryModule implements GinModule {
    @Override
    public void configure(GinBinder ginBinder) {
        ginBinder.bind(ChatManager.class).asEagerSingleton();
    }
}
