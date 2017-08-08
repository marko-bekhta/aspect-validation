package com.acme;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Marko Bekhta
 */
public class Foo {

	private final String string;
	private final int number;

	private Foo(String string, int number) {
		this.string = string;
		this.number = number;
	}

	// even though this will be picked up by aspect no validation will be performed
	// as there's no metadata for static methods
	//	@Validated
	public static Foo of(@NotNull String string, @Min(5) int number) {
		return new Foo( string, number );
	}

	@Validated
	@NotNull
	public String getString() {
		return string;
	}

	@Validated
	public void doNoting(@NotNull String justString) {
		// just an instance method for checking the validation through aspect.
	}

	public int getNumber() {
		return number;
	}

}
