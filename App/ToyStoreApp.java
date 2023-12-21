import java.io.FileWriter;
import java.io.IOException;

public class ToyStoreApp {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        Toy toy1 = new Toy(1, "Constructor", 20);
        Toy toy2 = new Toy(2, "Robot", 20);
        Toy toy3 = new Toy(3, "Doll", 60);

        toyStore.addToy(toy1);
        toyStore.addToy(toy2);
        toyStore.addToy(toy3);

        toyStore.updateFrequency(2, 30);

        try (FileWriter writer = new FileWriter("toy_results.txt")) {
            for (int i = 0; i < 10; i++) {
                int randomToyId = toyStore.getRandomToyId();
                writer.write("Result " + (i + 1) + ": Toy with ID " + randomToyId + " - "
                        + toyStore.getToyNameById(randomToyId) + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}