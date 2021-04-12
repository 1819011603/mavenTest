package Thread.Design.Factory;

public class PlaneFactory implements Factory{
    @Override
    public Vehicle getVehicle() {
        System.out.println("this is a Plane");
        return new Plane();
    }
}
