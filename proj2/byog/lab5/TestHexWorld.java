package byog.lab5;

import static org.junit.Assert.*;
import org.junit.Test;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class TestHexWorld {
    @Test
    public void testShouldAddDown() {
        assertEquals(false, HexWorld.shouldAddDown(0, 0, 2));
        assertEquals(true, HexWorld.shouldAddDown(0, 1, 2));
        assertEquals(true, HexWorld.shouldAddDown(1, 0, 2));
        assertEquals(false, HexWorld.shouldAddDown(1, 1, 4));
        assertEquals(true, HexWorld.shouldAddDown(2, 8, 4));
        assertEquals(false, HexWorld.shouldAddDown(2, 9, 4));

    }

    @Test
    public void testAddHexagon() {
        int WIDTH = 60;
        int HEIGHT = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        HexWorld.addHexagon(world,new HexWorld.Position(0,0), 6, Tileset.WALL);

        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        TestHexWorld test = new TestHexWorld();
        test.testAddHexagon();
    }
}
