package cz.mikealdo.qa;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TestClassFinder {

    public static final String TEST_PACKAGE = "cz.mikealdo.tests";

    public static Collection<Class<?>> findTestClasses() {
      List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
      classLoadersList.add(ClasspathHelper.contextClassLoader());
      classLoadersList.add(ClasspathHelper.staticClassLoader());

      Reflections reflections = new Reflections(new ConfigurationBuilder()
              .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
              .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
              .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(TEST_PACKAGE))));
      return reflections.getSubTypesOf(Object.class);
  }
}