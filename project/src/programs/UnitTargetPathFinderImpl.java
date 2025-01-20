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
        List<Edge> path = new ArrayList<>();

        boolean[][] grid = new boolean[HEIGHT][WIDTH];
        for (boolean[] row : grid) {
            Arrays.fill(row, true);
        }

        for (Unit unit : existingUnitList) {
            if (unit.isAlive()) {
                grid[unit.getyCoordinate()][unit.getxCoordinate()] = false;
            }
        }

        Edge start = new Edge(attackUnit.getxCoordinate(), attackUnit.getyCoordinate());
        Edge end = new Edge(targetUnit.getxCoordinate(), targetUnit.getyCoordinate());

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        Map<Edge, Edge> cameFrom = new HashMap<>();
        Map<Edge, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);
        openSet.add(new Node(start, heuristic(start, end)));

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            Edge current = currentNode.edge;

            if (current.equals(end)) {
                path = reconstructPath(cameFrom, current);
                break;
            }

            for (Edge neighbor : getNeighbors(current, grid)) {
                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;

                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    openSet.add(new Node(neighbor, tentativeGScore + heuristic(neighbor, end)));
                }
            }
        }

        return path;
    }

    private List<Edge> getNeighbors(Edge current, boolean[][] grid) {
        List<Edge> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] dir : directions) {
            int newX = current.getX() + dir[0];
            int newY = current.getY() + dir[1];

            if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT && grid[newY][newX]) {
                neighbors.add(new Edge(newX, newY));
            }
        }

        return neighbors;
    }

    private List<Edge> reconstructPath(Map<Edge, Edge> cameFrom, Edge current) {
        List<Edge> path = new LinkedList<>();
        while (current != null) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        return path;
    }

    private int heuristic(Edge a, Edge b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private static class Node {
        Edge edge;
        int f;

        Node(Edge edge, int f) {
            this.edge = edge;
            this.f = f;
        }
    }
}
