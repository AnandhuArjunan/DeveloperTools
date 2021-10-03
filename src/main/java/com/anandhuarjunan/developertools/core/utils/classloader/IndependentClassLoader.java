package com.anandhuarjunan.developertools.core.utils.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class IndependentClassLoader extends URLClassLoader {

    public IndependentClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addURL(URL url) {
        super.addURL(url);
    }
}
