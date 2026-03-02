package dev.codingcorner.entity;

import dev.codingcorner.core.GamePanel;
import dev.codingcorner.core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down2.png")));
//            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_1.png")));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_2.png")));
//            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_1.png")));
//            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_2.png")));
//            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_1.png")));
//            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_2.png")));
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

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed)
            spriteCounter++;

        if (spriteCounter > 12) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
