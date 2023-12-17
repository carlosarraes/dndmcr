package org.acme.restclients;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.model.Monster;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/monster")
@RegisterRestClient
public interface MonsterClient {
  @GET
  @Path("/{id}")
  Uni<Monster> getMonsterById(@PathParam("id") Long id);

  @PUT
  @Path("/{id}")
  Uni<Monster> updateMonster(@PathParam("id") Long id, Monster monster);
}
