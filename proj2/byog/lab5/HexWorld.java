package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static class Position{
        public int x;
        public int y;
        public Position(int xx, int yy){
            x = xx;
            y = yy;
        }
    }
    /**
     * adds a hexagon of side length s and TETile type tto a given position in the world
     */

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int i = 0; i < s; i += 1) {
            for (int j = 0; j < 3 * s - 2; j += 1) {
                if (shouldAddDown(i, j, s)) {
                    world[p.x + j][p.y + i] = t;
                }
            }
        }

        for (int i = 0; i < s; i += 1) {
            for (int j = 0; j < 3 * s - 2; j += 1) {
                if (shouldAddUp(i, j, s)) {
                    world[p.x + j ][p.y + i + s] = t;
                }
            }
        }
    }

    public static void main(String[] args) {

    }

    public static boolean shouldAddDown(int i, int j, int s){
        int width = 3 * s - 2;
        int blank = s - i - 1;
        int actualCol = j + 1;
        return actualCol >= blank + 1 && actualCol <= width - blank;
    }

    public static boolean shouldAddUp(int i, int j, int s){
        int width = 3 * s - 2;
        int blank = i ;
        int actualCol = j + 1;
        return actualCol >= blank + 1 && actualCol <= width - blank;
    }

}

