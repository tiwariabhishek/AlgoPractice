package algorithms.stackqueue;

/**
 * Created by abhishektiwari on 26/04/18.
 * Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.
 * 1. The amount of petrol that every petrol pump has.
 * 2. Distance from that petrol pump to the next petrol pump.
 * Calculate the first point from where a truck will be able to complete the circle (The truck will stop
 * at each petrol pump and it has infinite capacity). Expected time complexity is O(n). Assume for 1 litre petrol,
 * the truck can go 1 unit of distance.
 * 
 * https://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 */

class FuelStation {
    int petrolQuantity;
    int distance;

    public FuelStation(int petrolQuantity, int distance) {
        this.petrolQuantity = petrolQuantity;
        this.distance = distance;
    }
}

public class CircularTourPetrolPumps {

    // The function returns starting point if there is a possible solution,
    // otherwise returns -1
    public int findStartingPointOfTour(FuelStation[] fuelStations) {
        // if there are no stations then return -1, or there is 1 station then return zeroth index (0)
        if (fuelStations.length == 0 || fuelStations.length == 1) return fuelStations.length - 1;
        int start = 0, end = 1, fuelRemaining = fuelStations[0].petrolQuantity - fuelStations[0].distance;
        while (start != end || fuelRemaining < 0) {
            while (start != end && fuelRemaining < 0) {
                fuelRemaining -= fuelStations[start].petrolQuantity - fuelStations[start].distance;
                start = (start + 1) % fuelStations.length;
                if (start == 0) return -1;
            }
            fuelRemaining += fuelStations[end].petrolQuantity - fuelStations[end].distance;
            end = (end + 1) % fuelStations.length;
        }
        return start;
    }

    public static void main(String args[]) {
        FuelStation[] fuelStations = {new FuelStation(4, 6),
                new FuelStation(6, 5),
                new FuelStation(7, 3),
                new FuelStation(4, 5)};
        int start = new CircularTourPetrolPumps().findStartingPointOfTour(fuelStations);
        System.out.println(start == -1 ? "No Solution" : start);
    }
}
