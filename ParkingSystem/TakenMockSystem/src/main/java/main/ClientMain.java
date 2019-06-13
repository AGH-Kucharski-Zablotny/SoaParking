package main;

import pl.agh.soa.soap.client.SlotOccupiedException_Exception;
import pl.agh.soa.soap.client.SlotResource;
import pl.agh.soa.soap.client.SlotResourceImplService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientMain
{
    public static void main(String[] args) throws SlotOccupiedException_Exception, InterruptedException
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
        System.out.println("Taking Parking Slot..");
        slot.takeParkingSlot(slotID, registrationPlate);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Releasing Parking Slot..");
        slot.releaseParkingSlot(slotID);
        System.out.println("***************************");
    }
}
