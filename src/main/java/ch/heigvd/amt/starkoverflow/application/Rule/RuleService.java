package ch.heigvd.amt.starkoverflow.application.Rule;

import ch.heigvd.amt.starkoverflow.domain.event.Event;
import ch.heigvd.amt.starkoverflow.domain.event.IEventRepository;
import ch.heigvd.amt.starkoverflow.domain.rule.IRuleRepository;
import ch.heigvd.amt.starkoverflow.domain.rule.Rule;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@AllArgsConstructor
@Named("RuleService")
@ApplicationScoped
public class RuleService {

    @Inject
    @Named("RestRuleRepository")
    private IRuleRepository ruleRepository;

    public void createRule(Rule rule){
        ruleRepository.save(rule);
    }
}
