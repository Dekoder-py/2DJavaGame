package dev.codingcorner.tile;

import dev.codingcorner.core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tiles = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        final int defaultTile = 0;

        try {
            InputStream is = getClass().getResourceAsStream(filePath);

            if (is == null) {
                throw new IllegalStateException("Map resource not found");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.maxScreenRow; row++) {

                String line = br.readLine();

                if (line == null) {
                    for (int col = 0; col < gp.maxScreenCol; col++) {
                        mapTileNum[col][row] = defaultTile;
                    }
                    continue;
                }

                line = line.trim();
                if (line.isEmpty()) {
                    for (int col = 0; col < gp.maxScreenCol; col++) {
                        mapTileNum[col][row] = defaultTile;
                    }
                    continue;
                }

                String[] numbers = line.split("\\s+");

                for (int col = 0; col < gp.maxScreenCol; col++) {

                    int num = defaultTile;

                    if (col < numbers.length) {
                        try {
                            num = Integer.parseInt(numbers[col]);
                        } catch (NumberFormatException ignored) {
                            num = defaultTile;
                        }
                    }

                    mapTileNum[col][row] = num;
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }
}
