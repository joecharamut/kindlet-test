package rocks.spaghetti.kindletest;

import java.net.URL;

public class ResourceLoader {
    private ResourceLoader() {
        throw new IllegalStateException("Utility Class");
    }

    public static URL getResource(String name) {
        URL resource = ResourceLoader.class.getClassLoader().getResource(name);
        if (resource == null) {
            Log.error("Could not load resource: " + name);
            throw new RuntimeException("Could not load resource: " + name);
        }
        return resource;
    }
}
