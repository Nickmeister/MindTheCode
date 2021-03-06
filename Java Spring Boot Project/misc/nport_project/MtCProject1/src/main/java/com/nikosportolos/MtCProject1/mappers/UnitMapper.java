package com.nikosportolos.MtCProject1.mappers;

import com.nikosportolos.MtCProject1.models.Unit;
import com.nikosportolos.MtCProject1.models.responses.UnitResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnitMapper {

    public List<UnitResponse> mapUnits(Iterable<Unit> all) {
        List<UnitResponse> response = new ArrayList<>();
        for (Unit unit : all) {
            response.add(mapUnitToResponse(unit));
        }
        return response;
    }

    public UnitResponse mapUnitToResponse(Unit unit) {
        return new UnitResponse(unit.getId(), unit.getName(), unit.getDepartment());
    }
}
