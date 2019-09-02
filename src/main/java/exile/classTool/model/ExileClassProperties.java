package exile.classTool.model;

import java.util.Optional;

@SuppressWarnings("unused") // model
public class ExileClassProperties {

    private final int quality;
    private final int price;
    private final Integer sellPrice;

    ExileClassProperties(int quality, int price, Integer sellPrice) {
        this.quality = quality;
        this.price = price;
        this.sellPrice = sellPrice;
    }

    public int getQuality() {
        return quality;
    }

    public int getPrice() {
        return price;
    }

    public Optional<Integer> getSellPrice() {
        return Optional.ofNullable(sellPrice);
    }

    @Override
    public String toString() {
        return "ExileClassProperties{" +
                "quality=" + quality +
                ", price=" + price +
                ", sellPrice=" + sellPrice +
                '}';
    }
}