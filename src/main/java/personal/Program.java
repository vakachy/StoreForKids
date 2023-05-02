package personal;

import personal.model.Raffle;
import personal.model.Toy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Program {
    static final int RAFFLES_COUNT = 100; // количество случайных выборок при розыгрыше
    static List<Toy> toys = new ArrayList<>(); // все игрушки (БД)
    static List<Raffle> raffles = new ArrayList<>(); // все розыгрыши
    static List<Toy> toysRange = new ArrayList<>(); // все игрушки, участвующие в акции

    /**
     * запись данных обо всех игрушках в БД (инициализирует список всех игрушек при запуске)
     */
    public static void dataInit() {
        toys.add(new Toy("Toy_1", 0.22));
        toys.add(new Toy("Toy_2", 0.23));
        toys.add(new Toy("Toy_3", 0.21));
        toys.add(new Toy("Toy_4", 0.2));
    }

    /**
     * Добавляет Новую игрушку в список всех игрушек (в БД)
     */
    public static void addToy() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите НАЗВАНИЕ: ");
        String name = in.next();
        System.out.println("Введите ВЕСОВОЙ КОЭФФИЦИЕНТ: ");
        double weightCoeff = in.nextDouble();
        Toy toy = new Toy(name, weightCoeff);
        // внести игрушку в общий список игрушек (в БД)
        toys.add(toy);
        // внести игрушку в список игрушек, участвующих в розыгрыше: toysRange
        raffleAddToy(toy);
        System.out.println("Игрушка внесена в БД.");
    }

    /**
     * Отображает главное Меню в консоли.
     *
     * @return Возвращает выбранный пункт меню.
     */
    public static int showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("┌──────────────────┐");
        System.out.println("│ ***** МЕНЮ ***** │");
        System.out.println("└──────────────────┘");
        System.out.println("1. Внести Игрушку в БД: ");
        System.out.println("2. Изменить весовой коэффициент Игрушки: ");
        System.out.println("3. Провести розыгрыш Игрушки: ");
        System.out.println("4. Выдать Игрушку: ");
        System.out.println("5. Просмотреть список всех игрушек в БД: ");
        System.out.println("6. Завершить программу: ");
        System.out.println("───────────────────────");
        System.out.print("Выбрать пункт меню: ");
        int option = scanner.nextInt();
        System.out.println("───────────────────────");
        return option;
    }

    /**
     * Инициализирует список товаров, участвующих в розыгрыше: toysRange
     */
    public static void raffleInit() {
        toysRange.add(toys.get(0));
        toysRange.add(toys.get(1));
        toysRange.add(toys.get(2));
        toysRange.add(toys.get(3));
    }

    /**
     * Добавляет игрушку в список игрушек, участвующих в розыгрыше
     *
     * @param toy Принимает объект класса Игрушка.
     */
    public static void raffleAddToy(Toy toy) {
        toysRange.add(toy);
    }

    /**
     * Осуществляет розыгрыш игрушек.
     * 1) формируется выборка из 100 игрушек (выбранных случайно) из ассортимента toysRange и заносится в список
     * randomToys;
     * 2) подсчитывается количество каждой игрушки в randomToys. Затем, количество, умноженное на "вес" (т.е. баллы)
     * и ID каждой игрушки записывается в tmp[ ];
     * 3) определяется ID игрушки-победителя в temp[ ], набравшей наибольшее количество баллов;
     *
     * @param toysRange Получает список товаров, участвующих в розыгрыше.
     * @return Возвращает ID выпавшего товара в розыгрыше.
     */
    public static int getToyIdAfterRaffle(List<Toy> toysRange) {
        HashMap<Integer, Double> hm = new HashMap<>();
        Random randomIndex = new Random();
        List<Toy> randomToys = new ArrayList<>(); // случайная выборка из 100 товаров

        // 1) случайно формируется выборка из 100 игрушек из ассортимента toysRange и заносится в randomToys
        for (int i = 0; i < RAFFLES_COUNT; i++) {
            randomToys.add(toysRange.get(randomIndex.nextInt(toysRange.size())));
        }

        // 2) подсчитывается количество каждой игрушки в randomToys.
        // Затем, количество, умноженное на "вес" (т.е. баллы) и ID каждой игрушки записывается в tmp[]
        int count = 0;
        int i = 0;
        double[] temp = new double[toysRange.size() * 2];
        for (Toy toy : toysRange) {
            for (Toy item : randomToys) {
                if (toy.getID() == item.getID()) {
                    count++;
                }
            }
            temp[i] = count * toy.getWeight();
            temp[i + 1] = toy.getID();
            hm.put(toy.getID(), count * toy.getWeight());
            count = 0;
            i += 2;
        }

        // 3) определяется id игрушки-победителя в temp[], набравшей наибольшее количество баллов
        double winner = temp[1];
        double max = temp[0];
        for (int j = 0; j < temp.length; j += 2) {
            if (temp[j] > max) {
                max = temp[j];
                winner = temp[j + 1];
            }
        }
        ArrayList<Integer> keys = new ArrayList<>(hm.keySet());
        Double maximum = hm.get(keys.get(0));
        for (Integer key : keys
        ) {
            maximum = hm.get(key) > maximum ? hm.get(key) : maximum;
        }


        return (int) winner;
    }

    /**
     * Записывает каждый проведенный розыгрыш в список всех розыгрышей.
     *
     * @param toyId Получает ID игрушки, которая выпала в розыгрыше.
     */
    public static void registerRaffle(int toyId) {
        Raffle raffle = new Raffle(toyId);
        raffles.add(raffle);
    }

    /**
     * Метод для поиска игрушки по ее ID. Возвращает объект класса "Игрушка"
     *
     * @param toy_id Принимает ID игрушки
     * @return Возвращает объект класса "Игрушка"
     */
    public static Toy searchToyById(int toy_id) {
        for (Toy toy : toys
        ) {
//            int id = toy.getID();
//            System.out.println(id);
            if (toy.getID() == toy_id) {
                return toy;
            }
        }
        return null;
    }

    /**
     * Выдать игрушку.
     * Удаляет игрушку из БД игрушек магазина.
     *
     * @param toy_id Принимает ID игрушки
     */
    public static void giveOutToy(int toy_id) {
        for (Toy item : toys
        ) {
            if (item.getID() == toy_id) {
                toys.remove(item);
                break;
            }
        }
    }

    /**
     * Записывает информацию о выданной игрушке в файл.
     *
     * @param toy Получает объект класса Игрушка.
     */
    public static void writeGiveoutTicket(Toy toy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ticket.txt", true))) {
            bufferedWriter.write("Игрушка ID=" + toy.getID() + " Название: " + toy.getName() + " --> выдана" + "\n");
            bufferedWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Метод для ввода ID с клавиатуры.
     *
     * @return Возвращает ID игрушки
     */
    public static int enterId() {
        System.out.println("Введите ID игрушки: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    /** Метод перезаписи/измменения весового коэффициента. Для доступа к коэффициенту, необходимо на вход подать ID.
     * @param toy Принимает переменную класса Игрушка.
     */
    public static void changeWeightCoeff(Toy toy) {
        System.out.println("Ввести НОВЫЙ весовой коэффициент для игрушки: ");
        Scanner sc = new Scanner(System.in);
        double coeff = sc.nextDouble();
        toy.setWeight(coeff);
    }

    /**
     * Показать все игрушки в списке (БД)
     */
    public static void showAllToys() {
        for (Toy toy:toys
             ) {
            toy.getInfo();
        }
    }

    public static void main(String[] args) {
        // для хранения ID игрушки, выпавшей в розыгрыше
        int toyId = 0;

        // для хранения объекта класса Игрушка
        Toy toy = null;

        // записать данные об игрушках в БД
        dataInit();

        // записать данные об игрушках, участвующих в акции в список
        raffleInit();

        while (true) {
            // показать главное меню
            int option = showMainMenu();

            if (option == 1) {
                System.out.println("Внести игрушку в БД");
                addToy();
            } else if (option == 2) {
                System.out.println("Изменить весовой коэффициент для игрушки ");
                int id = enterId();
                toy = searchToyById(id);
                changeWeightCoeff(toy);
                System.out.println("Весовой коэффициент для игрушки изменен успешно!");
            } else if (option == 3) {
                // провести розыгрыш
                toyId = getToyIdAfterRaffle(toysRange);
                System.out.println("В розыгрыше выпала игрушка: " + toyId);
                // записать ID игрушки в список всех проводившихся розыгрышей
                registerRaffle(toyId);
            } else if (option == 4) {
                if (toyId == 0) {
                    System.out.println("Сначала нужно провести розыгрыш!");
                } else {
                    // записать информацию о выдаче игрушки в файл и удалить игрушку из списка всех игрушек
                    writeGiveoutTicket(searchToyById(toyId));
                    giveOutToy(toyId);
                }
            } else if (option == 5) {
                System.out.println("Просмотреть список всех игрушек в БД: ");
                 showAllToys();
            } else if (option == 6) {
                System.exit(0);
            }
        }
    }
}