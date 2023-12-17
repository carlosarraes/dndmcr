package org.acme.controller;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.Map;
import java.util.logging.Logger;
import org.acme.model.Monster;
import org.acme.service.AdventureService;
import org.acme.service.MonsterService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/adventure")
public class AdventureController {
  @Inject AdventureService adventureService;
  @Inject MonsterService monsterService;
  Logger logger = Logger.getLogger(AdventureController.class.getName());

  @GET
  @Path("/inspect/{id}")
  public Uni<RestResponse<Monster>> getMonster(@PathParam("id") Long id) {
    logger.info("inspecting monster: " + id);
    try {
      return monsterService.getMonsterById(id).onItem().transform(RestResponse::ok);
    } catch (Exception e) {
      logger.info("error: " + e.getMessage());
      return Uni.createFrom().item(RestResponse.status(404, "Monster not found"));
    }
  }

  @GET
  @Path("/fight/{hero_id}/{monster_id}")
  @WithTransaction
  public Uni<RestResponse<Map<String, String>>> fight(
      @PathParam("hero_id") Long heroId, @PathParam("monster_id") Long monsterId) {
    logger.info("fighting monster: " + monsterId + " with hero: " + heroId);
    try {
      return adventureService
          .fight(heroId, monsterId)
          .onItem()
          .transform(RestResponse::ok)
          .onFailure()
          .recoverWithItem(RestResponse.status(404, "Monster not found"));
    } catch (Exception e) {
      logger.info("error: " + e.getMessage());
      return Uni.createFrom().item(RestResponse.status(404, "Monster not found"));
    }
  }
}
