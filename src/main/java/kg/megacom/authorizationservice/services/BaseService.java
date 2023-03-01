package kg.megacom.authorizationservice.services;

import kg.megacom.authorizationservice.models.entities.Account;

import java.util.List;

public interface BaseService <T> {
    T save(T t);
    T findById(Long id);
    T delete(Long id);
    List<T> findAll();
}
