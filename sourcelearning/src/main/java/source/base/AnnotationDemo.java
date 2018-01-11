package source.base;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

public class AnnotationDemo {

    @Alias(value = "id")
    @Getter
    @Setter
    @AnnotationForSourceCodeEditEffect(value = "s")
    @AnnotationForRunTimeEffect(checkParamsCount = "s")
    private long flagId;

    @Alias("name")
    @Getter
    @Setter
    private String flagName;

    @AnnotationForRunTimeEffect(checkParamsCount = "1")
    public void print(String interest) {
        System.out.println("Why are you interesting in " + interest);
    }

    public static void main(String[] args) {
        AnnotationDemo demo = new AnnotationDemo();
        printAlias(demo);
    }

    private static void printAlias(Object userBeanObject) {
        for (Field field : userBeanObject.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Alias.class)) {
                Alias alias = field.getAnnotation(Alias.class);
                System.out.println(alias.value());
            }
        }
    }

}
