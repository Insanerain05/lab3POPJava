public class Producer extends Thread {
    private final int itemCount;
    private final Manager manager;

    public Producer(int itemCount, Manager manager) {
        this.itemCount = itemCount;
        this.manager = manager;
        this.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < itemCount; i++) {
            try {
                manager.full.acquire();
                manager.access.acquire();

                String item = "item " + manager.itemCounter++;
                manager.storage.add(item);
                System.out.println("Виробник додав: " + item);

                manager.access.release();
                manager.empty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
