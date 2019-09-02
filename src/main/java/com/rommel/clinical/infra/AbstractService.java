package com.rommel.clinical.infra;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T extends Serializable> {

	@Autowired
	protected abstract AbstractRepository<T> getRepository();

	@Transactional(rollbackFor = Throwable.class)
	public T insert(T entity) {
		return insertOrUpdate(entity);
	}

	@Transactional(rollbackFor = Throwable.class)
	public void delete(Long id) {
		this.getRepository().deleteById(id);
	}

	@Transactional(rollbackFor = Throwable.class)
	public T update(T entity) {
		return insertOrUpdate(entity);
	}

	@Transactional(readOnly = true)
	public T findById(final Long entidadeId) {
		T result;
		try {
			Optional<T> optional = this.getRepository().findById(entidadeId);
			result = optional.orElse(null);
		} catch (final NoSuchElementException e) {
			result = null;
		}
		return result;
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return this.getRepository().findAll();
	}

	private T insertOrUpdate(T entity) {
		return getRepository().save(entity);
	}

}
