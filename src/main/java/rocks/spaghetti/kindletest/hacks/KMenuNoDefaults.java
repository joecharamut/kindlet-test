package rocks.spaghetti.kindletest.hacks;

import com.amazon.kindle.kindlet.ui.KMenu;
import com.amazon.kindle.kindlet.ui.KMenuDefaultItemManager;
import com.amazon.kindle.kindlet.ui.KMenuPeer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import rocks.spaghetti.kindletest.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class KMenuNoDefaults extends KMenu {
    public KMenuNoDefaults() {
//        super(createMenuPeer(), createDefaultItemManager());
    }

    private static KMenuPeer createMenuPeer() {
        try {
            return (KMenuPeer) Class.forName("com.amazon.kindle.kindlet.ui.KMenuPeer").newInstance();
        } catch (Throwable t) {
            Log.catching(t);
        }

        return null;
    }

    private static KMenuDefaultItemManager createDefaultItemManager() {
        try {
            return KMenuDefaultItemManager.class.newInstance();
        } catch (Throwable t) {
            Log.catching(t);
        }

        return null;
    }
}
