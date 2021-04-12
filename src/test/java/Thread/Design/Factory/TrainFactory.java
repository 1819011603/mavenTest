package Thread.Design.Factory;

public class TrainFactory implements Factory{
    @Override
    public Vehicle getVehicle() {
        System.out.println("This is a Train");
        return new Train();
    }
}
