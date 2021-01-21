package ch.heigvd.amt.starkoverflow.application.PointScale;

import ch.heigvd.amt.starkoverflow.application.PointScale.dto.PointScaleDTO;
import ch.heigvd.amt.starkoverflow.domain.pointScale.IPointScaleRepository;
import ch.heigvd.amt.starkoverflow.domain.pointScale.PointScale;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Named("PointScaleService")
@ApplicationScoped
public class PointScaleService {

    @Inject
    @Named("RestPointScaleRepository")
    private IPointScaleRepository pointScaleRepository;

    public void createPointScale(PointScale pointScale){
        pointScaleRepository.save(pointScale);
    }

    public List<PointScaleDTO> getAllPointScale(){

        return pointScaleRepository.getAll();

    }
}
