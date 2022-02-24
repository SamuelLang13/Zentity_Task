package com.task.zentity.api.exception;

public class EntityStateException extends RuntimeException {

    public <E> EntityStateException(E entity) {
        super("Illegal state of entity " + entity);
    }

    public EntityStateException() {

    }
}
