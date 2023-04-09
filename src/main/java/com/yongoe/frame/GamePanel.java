package com.yongoe.frame;

import com.yongoe.core.Chunk;
import com.yongoe.core.Direction;
import com.yongoe.core.GameConstant;
import com.yongoe.core.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    GameEvent gameEvent;
    // 游戏状态
    boolean status = true;

    public GamePanel() {
        gameEvent = new GameEvent();
        //获得焦点和键盘事件
        this.setFocusable(true);//焦点事件，专注游戏窗口
        this.addKeyListener(this);//键盘事件，当前类中实现了，所以用this
        this.repaint();
    }

    /**
     * 每帧执行代码
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(GameConstant.background);
        gameEvent.drawMap(g);
        gameEvent.drawChunk(g);
        if (!status) {
            gameEvent.drawGameOver(g);
        }
        gameEvent.drawGameInfo(g);
    }


    /**
     * 键盘监听事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (status) {
            Chunk chunk = gameEvent.getChunk();
            int keycode = e.getKeyCode();//获取键盘按键
            if (keycode == KeyEvent.VK_W) {
                chunk.move(Direction.up);
            } else if (keycode == KeyEvent.VK_D) {
                chunk.move(Direction.right);
            } else if (keycode == KeyEvent.VK_S) {
                chunk.move(Direction.down);
            } else if (keycode == KeyEvent.VK_A) {
                chunk.move(Direction.left);
            } else
                return;
            if (chunk.isOver()) {
                status = false;
            } else {
                //只有地图没变，才生成
                if (!chunk.isChange() || !chunk.isMerge())
                    chunk.createPoint();
            }
            this.repaint();// 绘制
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}