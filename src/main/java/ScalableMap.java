package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ScalableMap extends JPanel {
    private static final int HORIZONTAL_TILES = 19;
    private static final int VERTICAL_TILES = 10;
    private BufferedImage[][] mapImages = new BufferedImage[VERTICAL_TILES][HORIZONTAL_TILES];
    private static final int TILE_SIZE = 50;
    private Map<String, BufferedImage> imageMap = new HashMap<>();

    private String[][] mapLayout = {
            {"water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water"},
            {"water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "grass", "wood", "grass", "grass", "grass", "wood", "water"},
            {"water", "water", "water", "mountain water", "water", "water", "water", "water", "grass", "turn right down", "street h", "street h", "street h", "street h", "street h", "street h", "little village", "grass", "water"},
            {"water", "water", "water", "water", "water", "water", "water", "mountain", "grass", "street v", "grass", "wood", "grass", "grass", "grass", "grass", "grass", "grass", "water"},
            {"water", "water", "water", "water", "water", "wood", "grass", "wood", "wood", "street v", "grass", "grass", "grass", "wood", "grass", "wood", "grass", "water", "water"},
            {"water", "water", "wood", "grass", "grass", "grass", "wood", "grass", "grass", "street v", "wood", "wood", "wood", "grass", "water", "water", "water", "water","water"},
            {"water", "grass", "grass", "grass", "grass", "grass", "wood", "wood", "grass", "street v", "grass", "mountain", "water", "water", "water", "water", "water", "water", "water"},
            {"water", "grass", "little village", "street h", "street h", "street h", "street h", "street h", "street h", "turn left up", "grass", "water", "water", "mountain water", "water", "water", "water", "water", "water"},
            {"water", "wood", "grass", "grass", "grass", "wood", "grass", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water"},
            {"water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water", "water"}
    };

    public ScalableMap() {
        loadImages();
        loadMap();
    }

    private void loadImages() {
        String[] imageFiles = {
                "bridge h", "bridge v", "grass", "little village", "mountain",
                "mountain water", "street h", "street v", "turn left down",
                "turn left up", "turn right down", "turn right up", "water", "wood"
        };

        for (String imageName : imageFiles) {
            try {
                BufferedImage img = ImageIO.read(getClass().getResource("/main/resources/" + imageName + ".png"));
                imageMap.put(imageName, img);
                if (img == null) {
                    System.err.println("Image not found: " + imageName);
                }
            } catch (IOException e) {
                System.err.println("Error loading image: " + imageName);
                e.printStackTrace();
            }
        }
    }

    private void loadMap() {
        for (int i = 0; i < VERTICAL_TILES; i++) {
            for (int j = 0; j < HORIZONTAL_TILES; j++) {
                String imageName = mapLayout[i][j];
                mapImages[i][j] = imageMap.get(imageName);
                if (mapImages[i][j] == null) {
                    System.err.println("Image not found in map: " + imageName);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileWidth = getWidth() / HORIZONTAL_TILES;
        int tileHeight = getHeight() / VERTICAL_TILES;

        for (int i = 0; i < VERTICAL_TILES; i++) {
            for (int j = 0; j < HORIZONTAL_TILES; j++) {
                if (mapImages[i][j] != null) {
                    g.drawImage(mapImages[i][j], j * tileWidth, i * tileHeight, tileWidth, tileHeight, this);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                    g.setColor(Color.BLACK);
                    g.drawString("Missing", j * tileWidth + 10, i * tileHeight + 25);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scalable Map");
        ScalableMap scalableMap = new ScalableMap();
        frame.add(scalableMap);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
