package Thread.Design.Strategy;

public class Cat implements Comparable<Cat>{
    int weight;
    int height;
    Cat(){
        this(0,0);
    }

    @Override
    public int compareTo(Cat o) {
        return Integer.compare(this.height,o.height);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
