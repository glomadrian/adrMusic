package com.adrian.music.notifications.nativeJava.sytles;

import ch.swingfx.color.ColorUtil;
import ch.twinkle.style.AbstractNotificationStyle;
import ch.twinkle.style.background.ColorBackground;
import ch.twinkle.style.closebutton.RoundCloseButton;
import ch.twinkle.style.overlay.BorderOverlay;
import ch.twinkle.style.overlay.GradientOverlay;
import ch.twinkle.style.overlay.OverlayPaintMode;
import ch.twinkle.window.NotificationWindowTypes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: lordfire
 * Date: 6/09/13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class AdrianStyle extends AbstractNotificationStyle{

    public AdrianStyle() {

        super();
        withNotificationWindowCreator(NotificationWindowTypes.DEFAULT);
        withTitleFontColor(new Color(0xff, 0xcc, 0x33));
        withMessageFontColor(Color.WHITE);
        withAlpha(0.85f);
        withWidth(800);
        withBackground(new ColorBackground(new Color(0x10, 0x10, 0x10)));
        withWindowCornerRadius(8);
        withOverlay(new BorderOverlay(1, Color.WHITE, OverlayPaintMode.MOUSE_OVER,
                new GradientOverlay(ColorUtil.withAlpha(Color.WHITE, 0f), ColorUtil.withAlpha(Color.WHITE, 0.1f), OverlayPaintMode.MOUSE_OVER)));
        withCloseButton(new RoundCloseButton(ColorUtil.withAlpha(Color.BLACK, 0.6f), Color.WHITE).withPosition(9, 9));


    }
}
