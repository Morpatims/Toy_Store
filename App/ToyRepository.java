public interface ToyRepository {
    void addToy(Toy toy);

    void updateFrequency(int toyId, int newFrequency);

    Toy getToyById(int toyId);

    Iterable<Toy> getAllToys();
}
