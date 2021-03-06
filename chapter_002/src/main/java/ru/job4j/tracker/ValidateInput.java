package ru.job4j.tracker;

public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введенная информация неверна. Введите целое число от 0 до 6.");
            } catch (MenuOutException moe) {
                System.out.println("Введенное число не соответствует какому-нибудь из пунктов меню. Введите другое целое число.");
            }
        } while (invalid);

        return value;
    }

}
