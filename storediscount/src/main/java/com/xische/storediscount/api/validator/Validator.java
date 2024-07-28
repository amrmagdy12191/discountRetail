package com.xische.storediscount.api.validator;

public interface Validator<T> {
	public boolean validate(T request);

}
