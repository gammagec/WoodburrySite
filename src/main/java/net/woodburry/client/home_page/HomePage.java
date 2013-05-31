package net.woodburry.client.home_page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/30/13
 * Time: 11:06 PM
 */
public class HomePage extends Composite {
    interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
    }

    private static HomePageUiBinder ourUiBinder = GWT.create(HomePageUiBinder.class);

    public HomePage() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}