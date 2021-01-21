package ch.heigvd.amt.starkoverflow.domain.pointScale;


import ch.heigvd.amt.starkoverflow.application.PointScale.dto.PointScaleDTO;

import java.util.List;

public interface IPointScaleRepository {

    void save(PointScale pointScale);

    List<PointScaleDTO> getAll();
}
