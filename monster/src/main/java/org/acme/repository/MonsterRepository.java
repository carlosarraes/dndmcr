package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Monster;

@ApplicationScoped
public class MonsterRepository implements PanacheRepository<Monster> {}
