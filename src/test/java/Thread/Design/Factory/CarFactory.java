package Thread.Design.Factory;

public class CarFactory implements Factory{
    @Override
    public Vehicle getVehicle() {
        System.out.println("this is a Car");
        return new Car();
    }
}
