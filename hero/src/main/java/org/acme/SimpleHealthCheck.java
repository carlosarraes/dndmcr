package org.acme;

import java.util.logging.Logger;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class SimpleHealthCheck implements HealthCheck {
  Logger logger = Logger.getLogger(SimpleHealthCheck.class.getName());

  @Override
  public HealthCheckResponse call() {
    logger.info("Simple health check");
    return HealthCheckResponse.up("Simple health check");
  }
}
