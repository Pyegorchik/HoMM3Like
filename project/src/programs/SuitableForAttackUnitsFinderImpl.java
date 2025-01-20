package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.ArrayList;
import java.util.List;



public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {
    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        List<Unit> suitableUnits = new ArrayList<>();
        int numRows = unitsByRow.size();
        if (numRows == 0) {
            return suitableUnits;
        }

        int numColumns = unitsByRow.get(0).size();

        if (isLeftArmyTarget) {
            for (int col = 0; col < numColumns; col++) {
                for (int row = 0; row < numRows; row++) {
                    if (col < unitsByRow.get(row).size()) {
                        suitableUnits.add(unitsByRow.get(row).get(col));
                    }
                }
            }
        } else {
            for (int col = numColumns - 1; col >= 0; col--) {
                for (int row = 0; row < numRows; row++) {
                    if (col < unitsByRow.get(row).size()) {
                        suitableUnits.add(unitsByRow.get(row).get(col));
                    }
                }
            }
        }

        return suitableUnits;
    }
}


