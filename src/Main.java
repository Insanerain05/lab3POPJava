public class Main {

    static int consumersAmount = 2;
    static int producersAmount = 3;
    static int totalItems = 10;
    static int storageSize = 3;

    public static void main(String[] args) {
        Manager manager = new Manager(storageSize);

        int[] producerItems = distributeItems(totalItems, producersAmount);
        int[] consumerItems = distributeItems(totalItems, consumersAmount);

        Producer[] producers = new Producer[producersAmount];
        Consumer[] consumers = new Consumer[consumersAmount];

        for (int i = 0; i < producersAmount; i++) {
            producers[i] = new Producer(producerItems[i], manager);
        }

        for (int i = 0; i < consumersAmount; i++) {
            consumers[i] = new Consumer(consumerItems[i], manager);
        }

        // Чекаємо завершення потоків
        for (Producer p : producers) {
            try {
                p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Consumer c : consumers) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Вся продукція оброблена. Програма завершила роботу.");
    }

    private static int[] distributeItems(int total, int count) {
        int[] result = new int[count];
        int base = total / count;
        int extra = total % count;

        for (int i = 0; i < count; i++) {
            result[i] = base + (i < extra ? 1 : 0);
        }

        return result;
    }
}
