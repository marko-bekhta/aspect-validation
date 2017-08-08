package com.acme;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marker annotation for validation to kick in ...
 *
 * @author Marko Bekhta
 */

// can be placed for on methods or constructors. Also would be nice to
// be able to place it on a type so all the methods of that type get validated.
@Target({ METHOD, TYPE, CONSTRUCTOR })
@Retention(RUNTIME)
public @interface Validated {

}
