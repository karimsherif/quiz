package com.ksh.core.exception;

import com.ksh.core.entity.BaseEntity;

public class RemoveUsedEntityException extends KSHException {

    private static final long serialVersionUID = 1L;

    private Class<?> removedEntity;

    private String errorMessage;

    public RemoveUsedEntityException() {
    }

    public RemoveUsedEntityException(String errorMessage) {
	this.errorMessage = errorMessage;
    }

    public <T extends BaseEntity> RemoveUsedEntityException(Class<T> removedEntity) {
	this.removedEntity = removedEntity;
    }

    public <T extends BaseEntity> void setRemovedEntity(Class<T> removedEntity) {
	this.removedEntity = removedEntity;
    }

    public <T extends BaseEntity> Class<T> getRemovedEntity() {
	return (Class<T>) removedEntity;
    }

    public String getErrorMessage() {
	return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }
}
