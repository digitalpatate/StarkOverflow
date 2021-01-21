package ch.heigvd.amt.starkoverflow.infrastructure;

import javax.inject.Inject;
import javax.resource.spi.ConfigProperty;

public class ConfigLoader {

    @Inject
    @ConfigProperty()
    private String gamificatorUrl;

}
