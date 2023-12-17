package org.acme.controller;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import java.util.List;
import org.acme.model.Hero;
import org.acme.service.HeroService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/hero")
public class HeroController {
  @Inject HeroService heroService;

  @GET
  @WithTransaction
  public Uni<RestResponse<List<Hero>>> findAll() {
    return heroService.findAll().onItem().transform(RestResponse::ok);
  }

  @GET
  @Path("/{id}")
  @WithTransaction
  public Uni<RestResponse<Hero>> findById(Long id) {
    try {
      return heroService.findById(id).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      return Uni.createFrom().item(RestResponse.status(404, "Hero not found"));
    }
  }

  @POST
  @WithTransaction
  public Uni<RestResponse<Hero>> create(Hero hero) {
    return heroService.create(hero).onItem().transform(RestResponse::ok);
  }

  @PUT
  @Path("/{id}")
  @WithTransaction
  public Uni<RestResponse<Hero>> update(Long id, Hero hero) {
    try {
      return heroService.update(id, hero).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      return Uni.createFrom().item(RestResponse.status(404, "Hero not found"));
    }
  }

  @DELETE
  @Path("/{id}")
  @WithTransaction
  public Uni<RestResponse<Void>> delete(Long id) {
    try {
      return heroService.delete(id).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      return Uni.createFrom().item(RestResponse.status(404, "Hero not found"));
    }
  }
}
