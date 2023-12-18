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
import java.util.logging.Logger;
import org.acme.model.Monster;
import org.acme.service.MonsterService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/monster")
public class MonsterController {
  @Inject MonsterService monsterService;
  Logger logger = Logger.getLogger(MonsterController.class.getName());

  @GET
  @WithTransaction
  public Uni<RestResponse<List<Monster>>> findAll() {
    logger.info("findAll");
    return monsterService.findAll().onItem().transform(RestResponse::ok);
  }

  @GET
  @Path("/{id}")
  @WithTransaction
  public Uni<RestResponse<Monster>> findById(Long id) {
    logger.info("findById: " + id);
    try {
      return monsterService.findById(id).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      logger.info("findById: " + e.getMessage());
      return Uni.createFrom().item(RestResponse.status(404, "Monster not found"));
    }
  }

  @POST
  @WithTransaction
  public Uni<RestResponse<Monster>> create(Monster monster) {
    logger.info("create: " + monster);
    return monsterService.create(monster).onItem().transform(RestResponse::ok);
  }

  @PUT
  @Path("/{id}")
  @WithTransaction
  public Uni<RestResponse<Monster>> update(Long id, Monster monster) {
    logger.info("update: " + id + " " + monster);
    try {
      return monsterService.update(id, monster).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      logger.info("update: " + e.getMessage());
      return Uni.createFrom().item(RestResponse.status(404, "Monster not found"));
    }
  }

  @DELETE
  @Path("/{id}")
  @WithTransaction
  public Uni<RestResponse<Void>> delete(Long id) {
    logger.info("delete: " + id);
    try {
      return monsterService.delete(id).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      logger.info("delete: " + e.getMessage());
      return Uni.createFrom().item(RestResponse.status(404, "Monster not found"));
    }
  }
}
