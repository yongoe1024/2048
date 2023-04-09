package com.yongoe.core;

/**
 * 方块
 * <p>
 * <p>
 * 0_______ y
 * ｜
 * ｜
 * ｜
 * x
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class Chunk {
    //地图
    private final int[][] map;
    private final int[][] oldMap;
    //合并数组
    private final int[] mergeArray;
    //得分
    private int score;
    //是否发生合并
    private boolean isMerge = false;
    // 是否发生改变
    private boolean isChange = false;

    /**
     * 生成新方块
     */
    public void createPoint() {
        int x, y;
        int num = (int) (10 * Math.random() + 1);
        // 百分之20的概率是4
        num = num == 1 || num == 2 ? 4 : 2;
        do {
            x = (int) (GameConstant.length * Math.random());
            y = (int) (GameConstant.length * Math.random());
        } while (map[x][y] != 0);
        map[x][y] = num;
    }

    public Chunk() {
        map = new int[GameConstant.length][GameConstant.length];
        oldMap = new int[GameConstant.length][GameConstant.length];
        mergeArray = new int[GameConstant.length];
    }

    /**
     * 游戏是否结束:无法移动和生成新点
     */
    public boolean isOver() {
        for (int x = 0; x < GameConstant.length; x++) {
            for (int y = 0; y < GameConstant.length; y++) {
                if (map[x][y] == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * 移动，把每一条线的数据，整理，相加，再整理
     */
    public void move(Direction direction) {
        //初始化isMerge ， 保存当前地图
        isMerge = false;
        isChange = false;
        cloneMap();
        switch (direction) {
            case up:
                up();
                break;
            case down:
                down();
                break;
            case left:
                left();
                break;
            case right:
                right();
                break;
        }
        if (isMerge)
            score++;
        isChange = isChangeForMap();
    }

    /**
     * 向上，遍历y轴
     */
    private void up() {
        for (int y = 0; y < GameConstant.length; y++) {
            for (int x = 0; x < GameConstant.length; x++)
                mergeArray[x] = map[x][y];
            merge();
            for (int x = 0; x < GameConstant.length; x++)
                map[x][y] = mergeArray[x];
        }
    }

    /**
     * 向下，遍历y轴
     */
    private void down() {
        for (int y = 0; y < GameConstant.length; y++) {
            for (int x = GameConstant.length - 1; x >= 0; x--)
                mergeArray[GameConstant.length - 1 - x] = map[x][y];
            merge();
            for (int x = GameConstant.length - 1; x >= 0; x--)
                map[x][y] = mergeArray[GameConstant.length - 1 - x];
        }
    }

    /**
     * 向左，遍历x轴
     */
    private void left() {
        for (int x = 0; x < GameConstant.length; x++) {
            for (int y = 0; y < GameConstant.length; y++)
                mergeArray[y] = map[x][y];
            merge();
            for (int y = 0; y < GameConstant.length; y++)
                map[x][y] = mergeArray[y];
        }
    }

    /**
     * 向右，遍历x轴
     */
    private void right() {
        for (int x = 0; x < GameConstant.length; x++) {
            for (int y = GameConstant.length - 1; y >= 0; y--)
                mergeArray[GameConstant.length - 1 - y] = map[x][y];
            merge();
            for (int y = GameConstant.length - 1; y >= 0; y--)
                map[x][y] = mergeArray[GameConstant.length - 1 - y];
        }
    }

    /**
     * 除去空隙，归拢
     */
    private void removeZero() {
        for (int i = 0; i < GameConstant.length - 1; i++) {
            for (int j = 0; j < GameConstant.length - 1; j++) {
                if (mergeArray[j] == 0) {
                    mergeArray[j] = mergeArray[j + 1];
                    mergeArray[j + 1] = 0;
                }
            }
        }
    }

    /**
     * 合并相加
     */
    private void merge() {
        removeZero();
        for (int i = 0; i < GameConstant.length - 1; i++) {
            if (mergeArray[i] != 0 && mergeArray[i] == mergeArray[i + 1]) {
                mergeArray[i] += mergeArray[i + 1];
                mergeArray[i + 1] = 0;
                isMerge = true;
            }
        }
        removeZero();
    }


    private void print(int[][] map) {
        for (int i = 0; i < GameConstant.length; i++) {
            for (int j = 0; j < GameConstant.length; j++) {
                if (map[i][j] == 0)
                    System.out.print("   ");
                else
                    System.out.print(map[i][j] + "  ");
            }
            System.out.println();

        }
        System.out.println();
    }

    /**
     * 新地图是否发生移动
     */
    private boolean isChangeForMap() {
        for (int x = 0; x < GameConstant.length; x++) {
            for (int y = 0; y < GameConstant.length; y++) {
                int i = map[x][y];
                int i1 = oldMap[x][y];
                if (i != i1)
                    return true;
            }
        }
        return false;
    }

    /**
     * 克隆
     */
    private void cloneMap() {
        for (int x = 0; x < GameConstant.length; x++) {
            for (int y = 0; y < GameConstant.length; y++) {
                int i = map[x][y];
                oldMap[x][y] = i;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public boolean isMerge() {
        return isMerge;
    }

    public boolean isChange() {
        return isChange;
    }
}
