package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog;

    @Override
    public void simulate(Army playerArmy, Army computerArmy) {
        try {
            List<Unit> playerUnits = new ArrayList<>(playerArmy.getUnits());
            List<Unit> computerUnits = new ArrayList<>(computerArmy.getUnits());

            while (hasAliveUnits(playerUnits) && hasAliveUnits(computerUnits)) {
    
                List<Unit> allUnits = new ArrayList<>();
                allUnits.addAll(playerUnits);
                allUnits.addAll(computerUnits);
                allUnits.removeIf(unit -> !unit.isAlive());
                allUnits.sort(Comparator.comparingInt(Unit::getBaseAttack).reversed());

                Queue<Unit> attackQueue = new LinkedList<>(allUnits);

                while (!attackQueue.isEmpty()) {
                    Unit attacker = attackQueue.poll();

                    if (!attacker.isAlive()) continue;

                    Unit target = attacker.getProgram().attack(); 
                    if (target != null && target.isAlive()) {
                        printBattleLog.printBattleLog(attacker, target);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hasAliveUnits(List<Unit> units) {
        return units.stream().anyMatch(Unit::isAlive);
    }
}
