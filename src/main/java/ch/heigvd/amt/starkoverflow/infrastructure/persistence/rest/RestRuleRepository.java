package ch.heigvd.amt.starkoverflow.infrastructure.persistence.rest;

import ch.heigvd.amt.starkoverflow.application.Rule.ActionDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.ConditionDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.CreateRuleCommand;
import ch.heigvd.amt.starkoverflow.domain.rule.IRuleRepository;
import ch.heigvd.amt.starkoverflow.domain.rule.Rule;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.GamificatorService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("RestRuleRepository")
@NoArgsConstructor
public class RestRuleRepository implements IRuleRepository {

    @Inject
    @Named("GamificatorService")
    private GamificatorService gamificatorService;

    @Override
    public void save(Rule rule) {
        CreateRuleCommand createRuleCommand = CreateRuleCommand.builder()
                .actionDTO(new ActionDTO(rule.getAwardBadges(), rule.getAwardPointDTOList()))
                .conditionDTO(new ConditionDTO(rule.getConditionType()))
                .build();

        gamificatorService.sendRule(createRuleCommand);
    }
}
