package net.woodburry.client.new_account_page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/31/13
 * Time: 11:07 PM
 */
public interface NewAccountPageClientBundle extends ClientBundle {

    public static final NewAccountPageClientBundle INSTANCE = GWT.create(NewAccountPageClientBundle.class);

    interface MyCss extends CssResource {
        String body();
        String newAccountPanel();
    }

    @Source("NewAccountPage.css")
    MyCss css();
}
