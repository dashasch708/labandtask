/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    /** Сравнивает две различные местоположения. **/
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            // Приводит объект к типу Location,
            // затем сравнивает.
            Location other = (Location) obj;
            if (xCoord == other.xCoord && yCoord == other.yCoord) {
                return true;
            }
        }
        return false;
    }

    /** Определяет хэш-код для каждого местоположения. **/
    public int hashCode() {
        int result = 31; // Некоторое простое число
        result = 31 * result + xCoord;
        result = 31 * result + yCoord;
        return result;
    }
}
