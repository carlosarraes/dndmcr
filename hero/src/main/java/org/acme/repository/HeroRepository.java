package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Hero;

@ApplicationScoped
public class HeroRepository implements PanacheRepository<Hero> {}
