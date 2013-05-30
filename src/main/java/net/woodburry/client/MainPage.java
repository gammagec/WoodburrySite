package net.woodburry.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 5/29/13
 * Time: 11:56 PM
 */
public class MainPage extends Composite {

    interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
    }

    private static MainPageUiBinder ourUiBinder = GWT.create(MainPageUiBinder.class);

    public MainPage() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}