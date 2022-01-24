package com.company;

import java.util.Random;

public class Main {

    private static int bossHealth = 700;
    private static int bossDamage = 50;
    private static String bossDefenceType = "";
    private static int[] heroesHealth = {300, 290, 280, 300, 250};
    private static int[] heroesDamage = {30, 25, 30, 20, 0};
    private static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Archer", "Medic"};
    private static int roundCounter = 1;


    public static void main(String[] args) {
        printStatistics();

        while (isGameOver() != true) {
            round();
        }

    }

    public static void medic() {
        int randomAttackType = new Random().nextInt(heroesAttackType.length);
        if (heroesHealth[randomAttackType] < 100&&heroesHealth[randomAttackType]!=0&& !heroesAttackType[randomAttackType].equals(heroesAttackType[4])) {
            int randomHealth = new Random().nextInt(100) + 20;
            heroesHealth[randomAttackType] = heroesHealth[randomAttackType] + randomHealth;
            System.out.println("Медик вылечил " + heroesAttackType[randomAttackType] + " на " + randomHealth);
        }
    }




    public static void printStatistics() {
        System.out.println("-------------------");
        System.out.println("Round: " + roundCounter);
        roundCounter++;
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("-------------------");
    }

    public static void bossHits() {

        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits() {

        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    heroesDamage[i] = 0;
                    System.out.println(heroesAttackType[i] + " damage = 0");
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            } else {
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead == true) {
            System.out.println("Boss won!!!");
        }

        return allHeroesDead;
    }

    public static void changeDefenceType() {
        int randomAttackType = new Random().nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomAttackType];
        System.out.println("Boss choose: " + bossDefenceType);
    }

    public static void round() {

        // Проверяем жив ли босс
        if (bossHealth > 0) {
            // Изменение невосприимчивости нашего босса
            changeDefenceType();
            // Нанесение урона боссом
            bossHits();
        }

        // Герои наносят урон
        heroesHits();
        medic();

        // Распечатка статистики
        printStatistics();
    }
}











