package dev.garage474.mspagamento.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Profile("dev")
public class FlywayCleaner implements CommandLineRunner {

  private boolean enabled = false;
  private final Flyway flyway;
  private final Logger logger = Logger.getLogger(FlywayCleaner.class.getName());

  public FlywayCleaner(Flyway flyway) {
    this.enabled = false;
    this.flyway = flyway;
  }

  @Override
  public void run(String... args) throws Exception {
    if (this.enabled) {
      logger.info("Limpando o banco de dados com flyway...");
      flyway.clean();
      flyway.migrate();
      logger.info("Banco de dados limpo com sucesso!");
    }
  }

}
