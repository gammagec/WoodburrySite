package net.woodburry.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 11:41 PM
 */
public interface MainPageClientBundle extends ClientBundle {

    static public MainPageClientBundle INSTANCE = GWT.create(MainPageClientBundle.class);

    interface MyCss extends CssResource {
        String footer();
        String body();
        String mainPage();
        String header();
    }

    @Source("MainPage.css")
    MyCss css();
}
