package com.zhoulesin.apt_library;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindViewTools {

    public static void bind(Activity activity){
        Class clz = activity.getClass();

        try {
            Class<?> aClass = Class.forName(clz.getName() + "_ViewBinding");
            Method method = aClass.getMethod("bind", activity.getClass());
            method.invoke(aClass.newInstance(),activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
