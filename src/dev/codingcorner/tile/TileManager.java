package dev.codingcorner.tile;

import dev.codingcorner.core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tiles = new Tile[10];

        getTileImage();
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

    public void draw(Graphics2D g2) {

        for (int x = 0; x < gp.maxScreenCol; x++) {
            for (int y = 0; y < gp.maxScreenRow; y++) {
                g2.drawImage(tiles[0].image, gp.tileSize * x, gp.tileSize * y, gp.tileSize, gp.tileSize, null);
            }
        }

    }
}
