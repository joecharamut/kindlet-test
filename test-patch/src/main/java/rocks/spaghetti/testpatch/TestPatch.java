package rocks.spaghetti.testpatch;

import com.amazon.kindle.kindlet.internal.security.ClassNameWhitelistFilter;
import rocks.spaghetti.classloader.annotation.Overwrite;
import rocks.spaghetti.classloader.annotation.Patch;

@Patch(target = ClassNameWhitelistFilter.class)
public class TestPatch {
    @Overwrite(method = "Gl(Ljava/lang/String;)Z")
    private boolean alwaysInWhitelist(String arg0) {
        return true;
    }
}
