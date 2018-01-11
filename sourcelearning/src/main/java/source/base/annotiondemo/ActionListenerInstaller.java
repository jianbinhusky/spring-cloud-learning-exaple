package source.base.annotiondemo;

import java.awt.event.ActionListener;
import java.lang.reflect.*;

/**
 * Created by hujianbin on 18/1/11.
 */
public class ActionListenerInstaller {

    public static void processAnnotation(Object object){
        try {
            Class<?> clz = object.getClass();
            for (Method method : clz.getDeclaredMethods()) {
                ActionListenerFor actionListenerFor = method.getAnnotation(ActionListenerFor.class);
                if (actionListenerFor != null) {
                    Field field = clz.getDeclaredField(actionListenerFor.source());
                    field.setAccessible(true);
                    addListener(field.get(object), object, method);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void addListener(Object source, final Object param, final Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InvocationHandler invocationHandler = (proxy, innermethod, args) -> method.invoke(param);
//        InvocationHandler invocationHandler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method innermethod, Object[] args) throws Throwable {
//                return method.invoke(param);
//            }
//        };
        Object listener = Proxy.newProxyInstance(null, new Class[]{java.awt.event.ActionListener.class}, invocationHandler);
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(source, listener);
    }
}
