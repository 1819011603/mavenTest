package Thread.Design.Strategy;

public class Dog implements Comparable<Dog> {
    int food;

    @Override
    public int compareTo(Dog o) {
        return Integer.compare(o.food,this.food);
    }
    Dog(){
        this(0);
    }
    public Dog(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
