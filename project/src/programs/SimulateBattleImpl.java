package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;

import com.battle.heroes.army.programs.SimulateBattle;

import java.util.List;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog;

    @Override
    public void simulate(Army leftArmy, Army rightArmy) {
        try {
            List<Unit> userUnits = rightArmy.getUnits();
            List<Unit> computerUnits = leftArmy.getUnits();
    
            int userIndex = 0, computerIndex = 0;
    
            while (userIndex < userUnits.size() && computerIndex < computerUnits.size()) {
                Unit userUnit = userUnits.get(userIndex);
                Unit computerUnit = computerUnits.get(computerIndex);
    
                if (userUnit.isAlive()) {
                    userUnit.getProgram().attack();
                    printBattleLog.printBattleLog(userUnit, computerUnit);
                }
    
                if (computerUnit.isAlive()) {
                    computerUnit.getProgram().attack();
                    printBattleLog.printBattleLog(userUnit, computerUnit);
                }
    
                if (!userUnit.isAlive()) userIndex++;
                if (!computerUnit.isAlive()) computerIndex++;
            }
    
            if (userIndex >= userUnits.size() && computerIndex >= computerUnits.size()) {
                System.out.println("Battle ended in a draw.");
            } else if (userIndex >= userUnits.size()) {
                System.out.println("Computer army wins!");
            } else {
                System.out.println("User army wins!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
