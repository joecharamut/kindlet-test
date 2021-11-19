package rocks.spaghetti.kindletest.hacks;

import javassist.ClassPool;
import javassist.CtClass;
import rocks.spaghetti.kindletest.Log;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ClassLoaderBypass {
    private ClassLoaderBypass() {
        throw new IllegalStateException("Utility Class");
    }

    public static boolean apply() {
        Log.info("Bypassing classloader security...");

        try {
            ClassLoader loader = ClassLoaderBypass.class.getClassLoader();

            // private final WhiteListFilter iJC;
            Field filterField = loader.getClass().getSuperclass().getDeclaredField("iJC");
            filterField.setAccessible(true);
            Object classNameWhitelistFilter = filterField.get(loader);

            // private final Set iIZ;
            Field whitelistSetField = classNameWhitelistFilter.getClass().getDeclaredField("iIZ");
            whitelistSetField.setAccessible(true);

            Set evilSet = new EvilSet();
            whitelistSetField.set(classNameWhitelistFilter, evilSet);

            Log.info("Success");

            apply2();
            return true;
        } catch (Throwable t) {
            Log.catching(t);
        }

        Log.info("Failure");
        return false;
    }

    private static void apply2() {
        try {
            Log.info("Current classloader: " + ClassLoaderBypass.class.getClassLoader());
            ClassPool pool = ClassPool.getDefault();
            CtClass clazz = pool.getCtClass("com.amazon.kindle.kindlet.internal.security.KindletBookletKindletClassLoader");
            clazz.debugWriteFile("/tmp");
        } catch (Throwable t) {
            Log.catching(t);
        }
    }

    private static class EvilSet implements Set {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return true;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public Object[] toArray(Object[] objects) {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection collection) {
            return false;
        }

        @Override
        public boolean removeAll(Collection collection) {
            return false;
        }

        @Override
        public void clear() {

        }
    }
}
