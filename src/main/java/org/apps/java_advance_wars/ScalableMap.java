package org.apps.java_advance_wars;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ScalableMap extends JPanel {
    private static final int TILE_SIZE = 50;
    private BufferedImage[][] mapImages;
    private Map<String, BufferedImage> imageMap = new HashMap<>();
    private String[][] mapLayout;

    public ScalableMap(int mapNumber) {
        MapManager mapManager = new MapManager(mapNumber);
        this.mapLayout = mapManager.getCurrentMap();
        loadImages();
        loadMap(mapLayout.length, mapLayout[0].length); // Pass actual dimensions
    }

    private void loadImages() {
        String[] imageFiles = {
                "bridge h", "bridge v", "grass", "little village", "mountain",
                "mountain water", "street h", "street v", "turn left down",
                "turn left up", "turn right down", "turn right up", "water", "wood"
        };

        ClassLoader classLoader = getClass().getClassLoader();
        for (String imageName : imageFiles) {
            try {
                BufferedImage img = ImageIO.read(classLoader.getResource("main/resources/" + imageName + ".png"));
                if (img != null) {
                    imageMap.put(imageName, img);
                } else {
                    System.err.println("Image not found: " + imageName);
                }
            } catch (IOException e) {
                System.err.println("Error loading image: " + imageName);
                e.printStackTrace();
            }
        }
    }

    private void loadMap(int verticalTiles, int horizontalTiles) {
        // Allocate based on actual map size
        mapImages = new BufferedImage[verticalTiles][horizontalTiles];

        for (int i = 0; i < verticalTiles; i++) {
            for (int j = 0; j < horizontalTiles; j++) {
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

        // Calculate tile dimensions based on actual map size and component size
        int verticalTiles = mapLayout.length;
        int horizontalTiles = mapLayout[0].length;
        int tileWidth = getWidth() / horizontalTiles;
        int tileHeight = getHeight() / verticalTiles;

        for (int i = 0; i < verticalTiles; i++) {
            for (int j = 0; j < horizontalTiles; j++) {
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
        String[] options = {"Little Island", "Eon Springs", "Piston Dam"};
        int choice = JOptionPane.showOptionDialog(null, "Choose a map", "Map Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        ScalableMap scalableMap = new ScalableMap(choice + 1);
        frame.add(scalableMap);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
