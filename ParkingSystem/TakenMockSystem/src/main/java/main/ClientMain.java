package main;

import pl.agh.soa.soap.client.SlotOccupiedException_Exception;
import pl.agh.soa.soap.client.SlotResource;
import pl.agh.soa.soap.client.SlotResourceImplService;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientMain
{
    public static void main(String[] args) throws SlotOccupiedException_Exception
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("***************************");
        System.out.println("Create Web Service Client..");
        SlotResourceImplService slotResource = new SlotResourceImplService();
        System.out.println("Create Web Service..");
        SlotResource slot = slotResource.getSlotResourceImplPort();
        System.out.println("Type SlotID..");
        int slotID = scanner.nextInt();
        System.out.println("Type Registration Plate Number..");
        String registrationPlate = scanner.next();

        System.out.println("To take a Parking Slot type: 1");
        System.out.println("To release a Parking Slot type: 2");
        System.out.println("To exit type: 3");
        int option = 0;
        boolean isParked = false;

        while(option != 3)
        {
            option = scanner.nextInt();

            if(option == 1)
            {
                System.out.println("Taking Parking Slot..");
                slot.takeParkingSlot(slotID, registrationPlate);
                isParked = true;
            }
            else if(option == 2 && isParked)
            {
                System.out.println("Releasing Parking Slot..");
                slot.releaseParkingSlot(slotID);
                isParked = false;
            }
            else if(option == 2)
                System.out.println("This Parking Slot is not occupied!");
            else
                System.out.println("Wrong input!");
        }
    }
}
