package com.yongoe.core;

import java.awt.*;

/**
 * 游戏事件
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class GameEvent {

    private Chunk chunk;

    /**
     * 初始化
     */
    public GameEvent() {
        this.chunk = new Chunk();
        this.chunk.createPoint();
        this.chunk.createPoint();
    }


    /**
     * 画出小号字体
     */
    public void drawGameInfo(Graphics g) {
        this.drawFont(g, "得分：" + chunk.getScore(), -2);
    }

    /**
     * 画地图
     */
    public void drawMap(Graphics g) {
        //绘制静态界面
        g.setColor(GameConstant.mapColor);
        g.fillRect(GameConstant.xOffset, GameConstant.yOffset, GameConstant.mapLength, GameConstant.mapLength);
    }


    /**
     * 画方块
     */
    public void drawChunk(Graphics g) {
        int[][] map = chunk.getMap();
        int size = GameConstant.length;
        int chunkMaxLength = GameConstant.chunkLength;
        int chunkLength = (int) (GameConstant.chunkLength * 0.8);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // 画格子
                if (map[x][y] == 0)
                    g.setColor(Color.white);
                else
                    g.setColor(Color.GREEN);
                g.fillRect(GameConstant.xOffset + chunkMaxLength * y + (int) (chunkMaxLength * 0.1),
                        GameConstant.yOffset + chunkMaxLength * x + (int) (chunkMaxLength * 0.1),
                        chunkLength, chunkLength);
                // 画数字
                if (map[x][y] != 0) {
                    if(map[x][y]>100)
                        g.setFont(new Font("微软雅黑", Font.BOLD, (int)(GameConstant.fontSizeBig*0.7)));
                    else if (map[x][y]>10) {
                        g.setFont(new Font("微软雅黑", Font.BOLD, (int)(GameConstant.fontSizeBig*0.8)));
                    }else
                        g.setFont(new Font("微软雅黑", Font.BOLD,  GameConstant.fontSizeBig ));
                    g.setColor(Color.black);
                    g.drawString(String.valueOf(map[x][y]),
                            GameConstant.xOffset + chunkMaxLength * y + (int) (chunkMaxLength * 0.1) + (int)(chunkLength*0.1),
                            GameConstant.yOffset + chunkMaxLength * x + (int) (chunkMaxLength * 0.1) + (int)(chunkLength*0.8));
                }

            }
        }
    }

    /**
     * 结束游戏标题
     */
    public void drawGameOver(Graphics g) {
        g.setFont(new Font("微软雅黑", Font.BOLD, GameConstant.fontSizeBig));
        g.setColor(new Color(255, 255, 255, 128));
        g.fillRect(0, 0, GameConstant.maxWidth, GameConstant.maxHeight);
        g.setColor(Color.black);
        g.drawString("游戏结束", GameConstant.maxWidth / 5, GameConstant.maxHeight / 3);
    }


    /**
     * 绘制一行文字
     *
     * @param g   Graphics
     * @param str 文字
     * @param row 距离窗口中心位置的行数
     */
    private void drawFont(Graphics g, String str, int row) {
        g.setColor(Color.red);
        g.setFont(new Font("微软雅黑", Font.BOLD, GameConstant.fontSizeSmall));
        g.drawString(str,
                GameConstant.xOffset + GameConstant.mapLength + 10,
                GameConstant.yOffset + GameConstant.mapLength / 2 + GameConstant.fontSizeSmall * row);
    }


    public Chunk getChunk() {
        return chunk;
    }
}

