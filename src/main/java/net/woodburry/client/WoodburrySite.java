package net.woodburry.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/29/13
 * Time: 11:05 PM
 */
public class WoodburrySite implements EntryPoint {

    public void onModuleLoad() {
        RootPanel.get().add(new Label("Welcome to Woodburry!"));
    }
}
