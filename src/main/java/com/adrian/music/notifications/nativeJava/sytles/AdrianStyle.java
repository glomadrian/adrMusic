package com.adrian.music.notifications.nativeJava.sytles;

import ch.swingfx.color.ColorUtil;
import ch.twinkle.style.AbstractNotificationStyle;
import ch.twinkle.style.background.GradientBackground;
import ch.twinkle.style.background.IBackground;
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
        withTitleFontColor(new Color(183, 165, 37));
        withMessageFontColor(Color.WHITE);
        withAlpha(0.85f);
        withWidth(450);

        withTitleFont(new Font("Times New Roman", Font.BOLD, 25));
        withMessageFont(new Font("Times New Roman", Font.ITALIC, 25));
//      withBackground(new ColorBackground(new Color(28, 28, 28)));

        IBackground background = new GradientBackground(new Color(53,51,51),new Color(37, 37, 36));
        background.setAlpha(0.5f);
        withBackground(background);

        withWindowCornerRadius(28);
        withOverlay(new BorderOverlay(1, Color.WHITE, OverlayPaintMode.MOUSE_OVER,
                new GradientOverlay(ColorUtil.withAlpha(Color.WHITE, 0f), ColorUtil.withAlpha(Color.WHITE, 0.1f), OverlayPaintMode.MOUSE_OVER)));
        withCloseButton(new RoundCloseButton(ColorUtil.withAlpha(Color.BLACK, 0.6f), Color.WHITE).withPosition(9, 9));


    }
}
