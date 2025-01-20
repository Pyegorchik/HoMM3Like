package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        Map<String, Integer> unitCount = new HashMap<>();
        for (Unit unit : unitList) {
            unitCount.put(unit.getUnitType(), 0);
        }

        unitList.sort(Comparator.comparingDouble(unit -> 
            -((double) unit.getBaseAttack() / unit.getCost() + (double) unit.getHealth() / unit.getCost())));

        List<Unit> selectedUnits = new ArrayList<>();
        int totalPoints = 0;

        for (Unit unit : unitList) {
            while (unitCount.get(unit.getUnitType()) < 11 && totalPoints + unit.getCost() <= maxPoints) {
                selectedUnits.add(unit);
                unitCount.put(unit.getUnitType(), unitCount.get(unit.getUnitType()) + 1);
                totalPoints += unit.getCost();
            }
        }

        Army army = new Army();
        army.setUnits(selectedUnits);
        army.setPoints(totalPoints);

        return army;
    }
}
