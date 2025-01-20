package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.UnitTargetPathFinder;
import com.battle.heroes.army.programs.Edge;

import java.util.*;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {
    private static final int WIDTH = 27;
    private static final int HEIGHT = 21;

    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
        boolean[][] occupied = new boolean[WIDTH][HEIGHT];
        for (Unit unit : existingUnitList) {
            if (unit.isAlive()) {
                occupied[unit.getxCoordinate()][unit.getyCoordinate()] = true;
            }
        }

        Queue<Edge> queue = new LinkedList<>();
        queue.add(new Edge(attackUnit.getxCoordinate(), attackUnit.getyCoordinate()));

        boolean[][] visited = new boolean[WIDTH][HEIGHT];
        visited[attackUnit.getxCoordinate()][attackUnit.getyCoordinate()] = true;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Edge targetNode = null;

        while (!queue.isEmpty()) {
            Edge current = queue.poll();

            if (current.x == targetUnit.getxCoordinate() && current.y == targetUnit.getyCoordinate()) {
                targetNode = current;
                break;
            }

            for (int[] direction : directions) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT && !visited[newX][newY] && !occupied[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new Node(newX, newY, current));
                }
            }
        }

        List<Edge> path = new LinkedList<>();
        while (targetNode != null) {
            path.add(0, new Edge(targetNode.x, targetNode.y));
            targetNode = targetNode.previous;
        }

        return path;
    }


   
}
