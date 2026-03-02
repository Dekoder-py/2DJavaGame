package dev.codingcorner.entity;

import dev.codingcorner.core.GamePanel;
import dev.codingcorner.core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
        direction = "down";
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            // TODO: add more sprite states (up1, up2, down1, down2, left1 ...) and load them as the names over there <-- also, video: https://youtu.be/wT9uNGzMEM4
            sprite = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed) {
            y -= speed;
            direction = "up";
        }
        if (keyH.downPressed) {
            y += speed;
            direction = "down";
        }
        if (keyH.leftPressed) {
            x -= speed;
            direction = "left";
        }
        if (keyH.rightPressed) {
            x += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;

        // TODO: add more sprite states (up1, up2, down1, down2, left1 ...) and use switch statement to set sprite; see: https://youtu.be/wT9uNGzMEM4
        // switch (direction) {}

        image = sprite;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
