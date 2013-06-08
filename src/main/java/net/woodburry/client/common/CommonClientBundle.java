package net.woodburry.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/7/13
 * Time: 11:59 PM
 */
public interface CommonClientBundle extends ClientBundle {
    public static final CommonClientBundle INSTANCE = GWT.create(CommonClientBundle.class);

    @Source("sign.png")
    ImageResource sign();

    @Source("woodburry.png")
    ImageResource logo();
}
