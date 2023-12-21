import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore implements ToyRepository {
    private PriorityQueue<Toy> toyQueue;
    private Map<Integer, Toy> toyMap;

    public ToyStore() {
        this.toyQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getFrequency(), t1.getFrequency()));
        this.toyMap = new HashMap<>();
    }

    @Override
    public void addToy(Toy toy) {
        toyQueue.add(toy);
        toyMap.put(toy.getId(), toy);
    }

    @Override
    public void updateFrequency(int toyId, int newFrequency) {
        Toy updatedToy = toyMap.get(toyId);
        if (updatedToy != null) {
            updatedToy = new Toy(updatedToy.getId(), updatedToy.getName(), newFrequency);
            toyQueue.removeIf(t -> t.getId() == toyId);
            toyQueue.add(updatedToy);
            toyMap.put(toyId, updatedToy);
        }
    }

    @Override
    public Toy getToyById(int toyId) {
        return toyMap.get(toyId);
    }

    @Override
    public Iterable<Toy> getAllToys() {
        return toyQueue;
    }

    public int getRandomToyId() {
        Random rand = new Random();
        int randomNum = rand.nextInt(100) + 1;
        int cumulativeFrequency = 0;

        for (Toy toy : toyQueue) {
            cumulativeFrequency += toy.getFrequency();
            if (randomNum <= cumulativeFrequency) {
                return toy.getId();
            }
        }

        return -1;
    }

    public String getToyNameById(int toyId) {
        Toy toy = getToyById(toyId);
        return (toy != null) ? toy.getName() : null;
    }
}