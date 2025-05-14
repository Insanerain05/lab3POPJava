public class Consumer extends Thread {
    private final int itemCount;
    private final Manager manager;

    public Consumer(int itemCount, Manager manager) {
        this.itemCount = itemCount;
        this.manager = manager;
        this.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < itemCount; i++) {
            try {
                manager.empty.acquire();
                Thread.sleep(500);
                manager.access.acquire();

                String item = manager.storage.remove(0);
                System.out.println("Споживач взяв: " + item);

                manager.access.release();
                manager.full.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
