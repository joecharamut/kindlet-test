package rocks.spaghetti.kindletest.hacks;

import javassist.*;
import rocks.spaghetti.kindletest.Log;

public class UIClassPatcher {
    private UIClassPatcher() {
        throw new IllegalStateException("Utility Class");
    }

    private static final boolean DEBUG = false;

    private static int makePublic(int mod) {
        mod &= ~Modifier.FINAL;
        mod &= ~Modifier.PRIVATE;
        mod &= ~Modifier.PROTECTED;
        mod |= Modifier.PUBLIC;
        return mod;
    }

    private static void patchClass(CtClass clazz) throws Throwable {
        if (DEBUG) Log.info("Patching class: " + Modifier.toString(clazz.getModifiers()) + " " + clazz.getName());

        clazz.setModifiers(makePublic(clazz.getModifiers()));

        for (CtConstructor ctor : clazz.getDeclaredConstructors()) {
            if (DEBUG) Log.info("Patching ctor " + ctor);
            ctor.setModifiers(Modifier.PUBLIC);
        }

        for (CtMethod method : clazz.getDeclaredMethods()) {
            if (DEBUG) Log.info("Patching method: " +
                    Modifier.toString(method.getModifiers()) + " " +
                    method.getLongName() + " " + method.getSignature());

            method.setModifiers(makePublic(method.getModifiers()));

            if (DEBUG) Log.info("New method: " +
                    Modifier.toString(method.getModifiers()) + " " +
                    method.getLongName() + " " + method.getSignature());
        }

        if (DEBUG) Log.info("Class is now: " + Modifier.toString(clazz.getModifiers()) + " " + clazz.getName());
    }

    public static void apply() {
        Log.info("Patching ui classes...");
        try {
            ClassPool pool = ClassPool.getDefault();

            String[] classesToMakePublic = new String[] {
                    "a", "b", "KMenuPeer", "KMenuDefaultItemManager", "KMenuItem", "KMenuItemPeer",
                    "KMenuItemButton", "KMenu"
            };
            for (String className : classesToMakePublic) {
                CtClass aClazz = pool.getCtClass("com.amazon.kindle.kindlet.ui.".concat(className));

                aClazz.defrost();
                patchClass(aClazz);

//                for (CtClass subClass : aClazz.getNestedClasses()) {
//                    patchClass(subClass);
//                }

                aClazz.toClass();
            }

//            pool.getCtClass("com.amazon.kindle.kindlet.ui.KMenu").debugWriteFile("/tmp");

            Log.info("Success");
        } catch (Throwable t) {
            Log.catching(t);
            Log.info("Didn't work");
        }
    }
}
