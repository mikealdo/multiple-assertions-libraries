package cz.mikealdo.qa;

import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.InputStream;
import java.util.Collection;
import java.util.Set;

public class PreferredAssertionsLibrariesUsedTest {

    Collection<Class<?>> classes = TestClassFinder.findTestClasses();

    @Test
    public void shouldVerifyOnlyOneAssertionLibraryInAllTheTests() throws Exception {
        DependencyVisitor v = new DependencyVisitor();
        for (Class<?> aClass : classes) {
            String resourceName = "/cz/mikealdo/tests/" + aClass.getSimpleName() + ".class";
            InputStream resourceAsStream = aClass.getResourceAsStream(resourceName);
            new ClassReader(resourceAsStream).accept(v, 0);
            Set<String> classPackages = v.getPackages();
            for (String classPackage : classPackages) {
                if (classPackage.contains("com/google/common/truth")) {
                    System.err.println("Google Truth library is not allowed for using in class " + aClass.getSimpleName());
                }
            }
        }
    }

}
