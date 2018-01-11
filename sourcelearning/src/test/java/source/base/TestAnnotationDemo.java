package source.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Slf4j
public class TestAnnotationDemo {

    @Test
    public void testAnnotation1() throws NoSuchFieldException {
        AnnotationDemo ad = new AnnotationDemo();
        ad.setFlagId(1L);
        ad.setFlagName("Nemo");
        System.out.println(ad.toString());

        Class clz = AnnotationDemo.class;
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field field = clz.getDeclaredField("flagId");
        if (field != null) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.annotationType());
            }
        }

        Annotation[] annotations = AnnotationDemo.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
        }

        ad.print("Java");
    }
}
