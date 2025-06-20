package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        lab1();
    }

    private static void lab1() {
        String input = "등록\n나의 죽음을 적들에게 알리지 말라";
        Scanner scanner = new Scanner(input);
        String cmd = scanner.nextLine();

        System.out.println(cmd);
    }

}
