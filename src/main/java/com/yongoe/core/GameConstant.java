package com.yongoe.core;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 游戏常量
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class GameConstant {

    // 网格数量
    public static int length = 5;

    public static Color mapColor = Color.lightGray;
    public static Color background = Color.white;


    // 窗口总长
    static public int maxWidth;
    static public int maxHeight;
    // 地图距离窗口左上角的偏移
    public static int xOffset;
    public static int yOffset;
    // 地图的长宽
    public static int mapLength;
    // 小格子长宽
    public static int chunkLength;
    // 大字的size
    public static int fontSizeBig;
    // 小字的size
    public static int fontSizeSmall;

    public static void init(String path) {
        Properties prop = new Properties();
        try {
            InputStream inputStream1 = Files.newInputStream(Paths.get(path));
            prop.load(inputStream1);
            length = Integer.parseInt(prop.getProperty("length"));
            mapColor = inv(prop.getProperty("mapColor"));
            background = inv(prop.getProperty("background"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = ge.getMaximumWindowBounds();
        maxWidth = rect.width;
        maxHeight = rect.height;

        // 计算上下留出的距离
        xOffset = (int) (maxHeight * 0.1);
        yOffset = (int) (maxHeight * 0.1);
        // 计算 网格大小
        chunkLength = (maxHeight - yOffset * 2) / length;
        // 网格长度 * 网格数 = 地图长度
        mapLength = chunkLength * length;

        fontSizeBig = (int) (maxHeight * 0.08);
        fontSizeSmall = (int) (maxHeight * 0.03);
    }

    private static Color inv(String name) {
        try {
            for (Field field : Color.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals(name)) {
                    Color o = (Color) field.get(name);
                    return o;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Color.white;
    }


}