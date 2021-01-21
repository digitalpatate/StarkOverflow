package ch.heigvd.amt.starkoverflow.infrastructure.persistence.rest;

import ch.heigvd.amt.starkoverflow.application.PointScale.CreatePointScaleCommand;
import ch.heigvd.amt.starkoverflow.application.PointScale.dto.PointScaleDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.ActionDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.ConditionDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.CreateRuleCommand;
import ch.heigvd.amt.starkoverflow.domain.pointScale.IPointScaleRepository;
import ch.heigvd.amt.starkoverflow.domain.pointScale.PointScale;
import ch.heigvd.amt.starkoverflow.domain.rule.IRuleRepository;
import ch.heigvd.amt.starkoverflow.domain.rule.Rule;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.GamificatorService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Named("RestPointScaleRepository")
@NoArgsConstructor
public class RestPointScaleRepository implements IPointScaleRepository {

    @Inject
    @Named("GamificatorService")
    private GamificatorService gamificatorService;

    @Override
    public void save(PointScale pointScale) {
        CreatePointScaleCommand createPointScaleCommand = CreatePointScaleCommand.builder()
                .description(pointScale.getDescription())
                .name(pointScale.getName())
                .build();

        gamificatorService.sendPointScale(createPointScaleCommand);
    }

    @Override
    public List<PointScaleDTO> getAll() {
        try {
            return gamificatorService.getAllPointScales();
        } catch (NotFoundException e) {
            return new LinkedList<>();
        }
    }
}
