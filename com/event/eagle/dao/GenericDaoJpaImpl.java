package com.event.eagle.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	public GenericDaoJpaImpl() {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EagleEvents");

		entityManager = entityManagerFactory.createEntityManager();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public T create(T t) {

		entityManager.getTransaction().begin();
		this.entityManager.persist(t);
		entityManager.getTransaction().commit();

		return t;
	}

	public void createAll(List<T> t) {
		int batchSize = 25;

		try {
			entityManager.getTransaction().begin();

			for (int i = 0; i < t.size(); i++) {
				if (i > 0 && i % batchSize == 0) {
					entityManager.getTransaction().commit();
					entityManager.getTransaction().begin();

					entityManager.clear();
				}

				entityManager.persist(t.get(i));
			}

			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}

	}

	public T read(PK id) {
		return this.entityManager.find(entityClass, id);
	}

	public T update(T t) {

		try {
			entityManager.getTransaction().begin();
			t = this.entityManager.merge(t);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;

	}

	public void delete(T t) {
		entityManager.getTransaction().begin();

		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
		entityManager.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {

		entityManager.getTransaction().begin();

		List<T> data = entityManager.createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
		entityManager.getTransaction().commit();
		return data;
	}
}
