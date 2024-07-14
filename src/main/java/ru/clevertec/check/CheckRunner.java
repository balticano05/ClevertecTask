package ru.clevertec.check;

public class CheckRunner {
    public static void main(String[] args) {

        String ARGS = String.join(" ", args);
        SystemCheckGenerator systemCheckGenerator = new SystemCheckGenerator();
        systemCheckGenerator.generate(ARGS);
    }
}