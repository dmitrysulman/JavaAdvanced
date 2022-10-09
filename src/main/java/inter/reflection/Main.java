package inter.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    void dependencyInjection(List<Object> objects) throws Exception {
        Map<Class<?>, Object> beans = new HashMap<>();
        objects.forEach(object -> beans.put(object.getClass(), object));
        objects.forEach(object -> {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = beans.get(field.getType());
                    if (bean != null) {
                        try {
                            field.set(object, bean);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        throw new CandidateNotFindException();
                    }
                }
            }
        });
    }
}
