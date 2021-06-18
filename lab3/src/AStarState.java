import java.util.*;

/**
 * This class stores the basic state necessary for the A* algorithm to compute
 * a path across a map.  This state includes a collection of "open waypoints"
 * and another collection of "closed waypoints."  In addition, this class
 * provides the basic operations that the A* pathfinding algorithm needs to
 * perform its processing.
 **/
public class AStarState
{
    /**
     * This is a reference to the map that the A* algorithm
     * is navigating.
     **/
    private Map2D map;

    /** Добавляет хэш-карту для хранения открытых вершин и их местоположений. **/
    private Map<Location, Waypoint> open_waypoints
            = new HashMap<Location, Waypoint>();

    /** Добавляет хэш-карту для хранения закрытых вершин и их местоположений. **/
    private Map<Location, Waypoint> closed_waypoints
            = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Метод, который сканирует все вершины и возвращает одну с минимальной
     * общей стоимостью.  Если открытых вершин нет, возвращается null.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // Если нет открытых вершин, возвращаем null.
        if (numOpenWaypoints() == 0)
            return null;

        Set open_waypoint_keys = open_waypoints.keySet();
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;

        // Сканирует все доступные открытые вершины
        while (i.hasNext())
        {
            // Сохраняет текущее местоположение.
            Location location = (Location)i.next();
            // Сохраняет текущие вершины.
            Waypoint waypoint = open_waypoints.get(location);
            // Сохраняет общую стоимость для текущей вершины.
            float waypoint_total_cost = waypoint.getTotalCost();
            if (waypoint_total_cost < best_cost)
            {
                best = open_waypoints.get(location);
                best_cost = waypoint_total_cost;
            }

        }
        return best;
    }

    /**
     * Метод добавляет вершину в коллекцию открытых. Если в местоположении еще
     * нет открытой вершины, то новая вершины просто добавляется в коллекцию.
     * Но если вершина уже присутствует, новая вершина заменяет ее только в том
     * случае, если ее стоимость меньше предыдущей. Если новая вершина была успешно
     * добавлена в коллекцию возвращает true, иначе - false.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Находит местоположение новой вершины.
        Location location = newWP.getLocation();

        // Проверяет, есть ли уже открытая вершина в местоположении новой
        // вершины.
        if (open_waypoints.containsKey(location))
        {
            // Если условие выполняется, то проверяет является ли стоимость
            // новой вершины меньше, чем у текущей.
            Waypoint current_waypoint = open_waypoints.get(location);
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                open_waypoints.put(location, newWP);
                return true;
            }
            return false;
        }
        open_waypoints.put(location, newWP);
        return true;
    }


    /** Возвращает количество точек в наборе открытых вершин. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }

    /**
     * Перемещает вершину из набора открытых вершин в набор закрытых.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open_waypoints.remove(loc);
        closed_waypoints.put(loc, waypoint);
    }

    /**
     * Возвращает true если коллекция закрытых вершин содержит вершину для
     * указанного местоположения.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }
}