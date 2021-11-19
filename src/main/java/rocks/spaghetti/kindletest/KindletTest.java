package rocks.spaghetti.kindletest;

import com.amazon.kindle.kindlet.AbstractKindlet;
import com.amazon.kindle.kindlet.KindletContext;
import com.amazon.kindle.kindlet.ui.KMenu;
import rocks.spaghetti.kindletest.hacks.ClassLoaderBypass;
import rocks.spaghetti.kindletest.hacks.KMenuNoDefaults;
import rocks.spaghetti.kindletest.hacks.UIClassPatcher;
import rocks.spaghetti.kindletest.ui.MainUI;

@SuppressWarnings("unused")
public class KindletTest extends AbstractKindlet {
    private KindletContext context;
    private KMenu menu;
    private MainUI ui;

    private boolean created = false;

    public KindletTest() {
//        ClassLoaderBypass.apply();
//        UIClassPatcher.apply();
        Log.info("did it work?");
        try {
            Class.forName("java.net.URLClassLoader");
            Log.info("YES?");
        } catch (Throwable t) {
            Log.catching(t);
            Log.info("no :(");
        }
    }

    @Override
    public void create(KindletContext context) {
        if (!created) {
            created = true;

            Log.info("enter create()");

            Log.info("context");
            this.context = context;

            Log.info("menu");
            this.menu = new KMenuNoDefaults();
            this.context.setMenu(this.menu);
            this.menu.add("the");
            this.menu.add("scrunkly");
            Log.info(this.menu.getItemCount());

            Log.info("icons");
            Icons.loadIcons();

            Log.info("ui");
            this.ui = new MainUI();

            Log.info("exit create()");
        } else {
            Log.info("already created");
        }
    }

    @Override
    public void destroy() {
        Log.info("destroy()");
    }

    @Override
    public void start() {
        Log.info("enter start()");
        try {
            context.getRootContainer().add(ui.getRootComponent());
        } catch (Throwable t) {
            t.printStackTrace();
            Log.catching(t);
        }
        Log.info("exit start()");
    }
}
