package hr.algebra;

import hr.algebra.DAL.VehicleFactory;
import hr.algebra.models.BatteryCharger;
import hr.algebra.models.BigTrain;
import hr.algebra.models.SmallTrain;
import hr.algebra.models.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class EventProcessorTest {


    @Test
    public void shouldReturnTrueIfGasLevelIsLow() {
        Vehicle vehicle = new Vehicle(1,0.05,0.8,"Vehicle1");

        Assertions.assertTrue(vehicle.getGasTankLevel() < 0.1);
    }


    @Test
    public void shouldReturnTrueIfBatteryLevelIsFull() {
        Vehicle vehicle = new Vehicle(1,0.05,1,"Vehicle1");

        Assertions.assertTrue(vehicle.getBatteryLevel() == 1);
    }

    @Test
    public void shouldReturnFalseIfGasLevelIsLow() {
        Vehicle vehicle = new Vehicle(1,0.05,0.8,"Vehicle1");

        Assertions.assertFalse(vehicle.getGasTankLevel() > 0.1);
    }


    @Test
    public void shouldReturnFalseIfBatteryLevelIsNotFull() {
        Vehicle vehicle = new Vehicle(1,0.05,1,"Vehicle1");

        Assertions.assertFalse(vehicle.getBatteryLevel() != 1);
    }



    @Test
    public void shouldCalculateEarnedMoneyFromTicketsForOneBigTrain(){
        BigTrain bt1 =  new BigTrain(1);
        List<Vehicle> bigVehicles = new ArrayList<Vehicle>(bt1.capicity());

        bigVehicles.add(VehicleFactory.createVehicle("Bus",1, 0.5, 0.7, "Bus1"));
        bigVehicles.add(VehicleFactory.createVehicle("Bus",2, 0.05, 0.01, "Bus2"));
        bigVehicles.add(VehicleFactory.createVehicle("Bus",3, 0.01, 0.05,"Bus3"));

        bigVehicles.add(VehicleFactory.createVehicle("Truck",1, 0.3, 0.09,"Truck1"));
        bigVehicles.add(VehicleFactory.createVehicle("Truck",2, 0.05, 0.7,"Truck2"));
        bigVehicles.add(VehicleFactory.createVehicle("Truck",3, 0.2,0.3 ,"Truck3"));

        Assertions.assertEquals(480,bt1.calculateProfitPerTrain(bigVehicles));

    }

    @Test
    public void shouldCalculateEmployeersSalaryFromOneSmallTrain(){
        SmallTrain st1 = new SmallTrain(2);

        List<Vehicle> smallVehicles = new ArrayList<>(st1.capicity());

        smallVehicles.add(VehicleFactory.createVehicle("Car",1, 0.5, 0.2, "Car1"));
        smallVehicles.add(VehicleFactory.createVehicle("Car",2, 0.05, 0.0, "Car2"));
        smallVehicles.add(VehicleFactory.createVehicle("Car",3, 0.2, 0.01, "Car3"));
        smallVehicles.add(VehicleFactory.createVehicle("Car",4, 0.05, 0.0, "Car4"));


        smallVehicles.add(VehicleFactory.createVehicle("Van",1, 0.5, 0.9, "Van1"));
        smallVehicles.add(VehicleFactory.createVehicle("Van",2, 0.05, 1.0, "Van2"));
        smallVehicles.add(VehicleFactory.createVehicle("Van",3, 0.2, 0.7, "Van3"));
        smallVehicles.add(VehicleFactory.createVehicle("Van",4, 0.05, 1.0, "Van4"));
        double e2payment = 0;

        BatteryCharger employee2 = new BatteryCharger(2,"Milutin", "Krmpotic");
        for (Vehicle v : smallVehicles)

        {
            System.out.println(v.getName() + " - Gas level: " + v.getGasTankLevel() + ", Battery level: " + v.getBatteryLevel());
            employee2.chargeVehicle(v);
            e2payment+=employee2.getPayment(v);

        }



        Assertions.assertEquals(57.2,e2payment);

    }





}

