package online.nasgar.hubcore.hubcore.api.repository.key.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the key of the class to be saved
 * through {@link #value()}.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepositoryKey {

    String value() default "OVERRIDEABLE";
}
