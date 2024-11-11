package com.example.interfaces;

import java.util.Scanner;

public interface Input {
    default String getString() {
        try (Scanner scanner = new Scanner(System.in)) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            return "";
        }
    }
}
