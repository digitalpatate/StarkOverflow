package ch.heigvd.amt.starkoverflow.infrastructure.gamificator;

import ch.heigvd.amt.starkoverflow.application.Reputation.ReputationDTO;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("GamificatorService")
@NoArgsConstructor
public class GamificatorService {

    @Inject @Named("RestService")
    private RestService restService;

    public Object getAllBadges(){
        return restService.get("/badges",String.class);
    }
    public void sendEvent(Object event){
        restService.post("/events",event);
    }

    public ReputationDTO getReputation(UserId userId){
        return  (ReputationDTO) restService.get("/reputations/" + userId.asString(), ReputationDTO.class);
    }
}
