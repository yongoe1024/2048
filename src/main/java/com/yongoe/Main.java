package com.yongoe;

import com.yongoe.core.GameConstant;
import com.yongoe.frame.GamePanel;

import javax.swing.*;

/**
 * 启动
 *
 * @author yongoe
 * @since 2023/1/1
 */
public class Main {

    public static void main(String[] args) {
        // 读取配置文件 , 直接运行放在项目主目录下，打包后必须放到jar目录下
        GameConstant.init(System.getProperty("user.dir") + "/config.properties");

        JFrame gameFrame = new JFrame("2048");
        gameFrame.add(new GamePanel());
        gameFrame.setVisible(true);
        gameFrame.setBounds(0, 0, (int) (GameConstant.maxHeight * 1.2), GameConstant.maxHeight);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

}
