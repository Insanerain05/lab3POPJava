import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Manager {
    public final Semaphore access;
    public final Semaphore full;
    public final Semaphore empty;

    public final ArrayList<String> storage = new ArrayList<>();
    public int itemCounter = 0;

    public Manager(int storageSize) {
        this.access = new Semaphore(1);
        this.full = new Semaphore(storageSize);
        this.empty = new Semaphore(0);
    }
}
