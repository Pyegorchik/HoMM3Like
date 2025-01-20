package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.ArrayList;
import java.util.List;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {
    private static final int NUM_COLUMNS = 3;

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        List<Unit> suitableUnits = new ArrayList<>();

        for (List<Unit> row : unitsByRow) {
            int rowSize = row.size();
            if (rowSize == 0) continue;

            if (isLeftArmyTarget) {
                for (int col = 0; col < NUM_COLUMNS && col < rowSize; col++) {
                    Unit unit = row.get(col);
                    if (unit != null) {
                        suitableUnits.add(unit);
                    }
                }
            } else {
                for (int col = rowSize - 1; col >= Math.max(rowSize - NUM_COLUMNS, 0); col--) {
                    Unit unit = row.get(col);
                    if (unit != null) {
                        suitableUnits.add(unit);
                    }
                }
            }
        }

        return suitableUnits;
    }
}
