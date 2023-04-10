package com.yongoe.core;

import java.util.Scanner;

/**
 * 控制台版，极简
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chunk chunk = new Chunk();
        chunk.createPoint();
        chunk.createPoint();
        chunk.print();
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "w":
                    chunk.move(Direction.up);
                    break;
                case "a":
                    chunk.move(Direction.left);
                    break;
                case "s":
                    chunk.move(Direction.down);
                    break;
                case "d":
                    chunk.move(Direction.right);
                    break;
                default:
                    continue;
            }
            chunk.createPoint();
            System.out.println();
            System.out.println();
            System.out.println();
            chunk.print();
        }
    }
}
