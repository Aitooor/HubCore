package online.nasgar.hubcore.hubcore.api.repository.key.annotation;

import online.nasgar.hubcore.hubcore.api.user.impl.UserImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the annotated type as a class
 * through {@link #value()}.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InstanceDeserializer {

    Class<UserImpl> value();
}
