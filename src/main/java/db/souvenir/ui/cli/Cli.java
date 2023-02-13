package db.souvenir.ui.cli;

import db.souvenir.ui.Ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class Cli implements Ui {
    private final Scanner scanner;

    Cli () {
        this.scanner = new Scanner(System.in);
    }

    public void printSimpleList(List<?> list) {
        System.out.println("Output:");
        System.out.println("-------");

        list.forEach(System.out::println);
    }

    @Override
    public void printNestedList(Map<?, ? extends List> map) {
        System.out.println("Output:");
        System.out.println("-------");

        map.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        });
    }

    @Override
    public void printHeader(String headerValue) {
        String header = """
            
            %s
            **********
            """;
        System.out.printf(header, headerValue);
    }

    @Override
    public int printMenu(String header, Map<Integer, String> menu, int exitCode) {
        StringBuilder sb = new StringBuilder();
        printHeader(header);

        for (var e: menu.entrySet()) {
            sb.append("%s - %s".formatted(e.getKey(), e.getValue())).append("\n");
        }

        sb.append("----------").append("\n");
        sb.append("%d - Exit".formatted(exitCode)).append("\n");

        System.out.print(sb);
        return getIntegerInput("Enter option");
    }

    public String getStringInput(String label) {
        System.out.printf("%s: ", label);

        return scanner.nextLine();
    }

    public int getIntegerInput(String label) {
        System.out.printf("%s: ", label);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return getIntegerInput(label);
        }
    }

    public double getDoubleInput(String label) {
        System.out.printf("%s: ", label);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return getDoubleInput(label);
        }
    }

    @Override
    public void printNotification(String notification) {
        System.out.println(notification);
    }
}
