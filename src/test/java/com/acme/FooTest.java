package com.acme;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import javax.validation.ConstraintViolationException;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class FooTest {

	@Test
	public void of() throws Exception {
		Foo f = Foo.of( null, 1 );
		try {
			Foo.of( null, 7 ).doNoting( null );
			fail( "method above should throw a ConstraintViolationException" );
		}
		catch (ConstraintViolationException e) {
			assertThat( e.getConstraintViolations() ).hasSize( 1 );
			assertThat( e.getConstraintViolations() ).extracting( constraintViolation -> constraintViolation.getMessage() )
					.containsOnly( "must not be null" );
			assertThat( e.getConstraintViolations() ).extracting( constraintViolation -> constraintViolation.getPropertyPath().toString() )
					.containsOnly( "doNoting.arg0" );
		}
	}

}