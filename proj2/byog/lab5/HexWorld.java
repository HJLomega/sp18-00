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

    static TETile[] tileList = {Tileset.WALL, Tileset.LOCKED_DOOR,
            Tileset.FLOWER, Tileset.FLOOR, Tileset.GRASS, Tileset.MOUNTAIN};
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static class Position {
        public int x;
        public int y;

        public Position(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    /**
     * adds a hexagon of side length s and TETile type tto a given position in the world
     * range from (0, 0) to (WIDTH, HEIGHT)
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
                    world[p.x + j][p.y + i + s] = t;
                }
            }
        }
    }

    public static boolean shouldAddDown(int i, int j, int s) {
        int width = 3 * s - 2;
        int blank = s - i - 1;
        int actualCol = j + 1;
        return actualCol >= blank + 1 && actualCol <= width - blank;
    }

    public static boolean shouldAddUp(int i, int j, int s) {
        int width = 3 * s - 2;
        int blank = i;
        int actualCol = j + 1;
        return actualCol >= blank + 1 && actualCol <= width - blank;
    }

    public static void sSizeHexWorld(int s) {
        int width = 3 * s - 2; //single Hex's width
        int height = 2 * s; //single Hex's height
        int WIDTH = 2 * s + 3 * width; //World's width
        int HEIGHT = 5 * height; //World's height

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        //fill the world with Hex of s side
        fillWithSHex(world, s);
        // draws the world to the screen
        ter.renderFrame(world);
    }

    /**
     * fill the world with Hex of s side
     */
    public static void fillWithSHex(TETile[][] world, int s) {
        // 1 - 3 column
        for (int i = 1; i <= 3; i += 1) {
            for (int j = 1; j <= i + 2; j += 1) {
                Position p = calPosition(i, j, s);
                TETile t = randomTile();
                addHexagon(world, p, s, t);
            }
        }
        // 4 - 5 column
        for (int i = 4; i <= 5; i += 1) {
            for (int j = 1; j <= 8 - i; j += 1) {
                Position p = calPosition(i, j, s);
                TETile t = randomTile();
                addHexagon(world, p, s, t);
            }
        }
    }

    /**
     * calculate Posinion of the  i-column-th (left to right), j-row-th (down to up)
     * Hex of s side
     */
    public static Position calPosition(int i, int j, int s) {
        // TODO
        int gapX = s + s - 1;
        int x = gapX * (i - 1);
        int height = 2 * s;
        int y;
        switch (i) {
            case 1:
                y = height + height * (j - 1);
                break;
            case 2:
                y = height / 2 + height * (j - 1);
                break;
            case 3:
                y = height * (j - 1);
                break;
            case 4:
                y = height / 2 + height * (j - 1);
                break;
            case 5:
                y = height + height * (j - 1);
                break;
            default:
                return null;
        }
        return new Position(x, y);
    }

    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(tileList.length);
        return tileList[tileNum];
    }

    public static void main(String[] args) {
        sSizeHexWorld(2);
    }

}

