package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        lab1();
    }

    private static void lab1() {
        String input = "등록";
        Scanner scanner = new Scanner(input);
        String cmd = scanner.nextLine();

        System.out.println(cmd);
    }

}
