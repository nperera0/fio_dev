package fio_dev;

import java.util.Scanner;

public class RegistrationSystem {

	public static void main(String[] args) {

		int selection;
		Boolean loop = true;
		Scanner input = new Scanner(System.in);
		DBHandler dbHandler = new DBHandler();
		String[] Patientinfo = new String[4];
		String[] Documentinfo = new String[4];

		System.out.println("\nWelcome to Patient Registration System");

		while (loop) {

			System.out.println("-------------Main Menu-----------------");
			System.out.println("1 - Create a new Patient Demographic");
			System.out.println("2 - Retrieve a Patient Demographic");
			System.out.println("3 - Delete a Patient Demographic");
			System.out.println("4 - Search Patient Demographics");
			System.out.println("5 - View all Patient Demographics");
			System.out.println("6 - Add a new Patient Document");
			System.out.println("7 - Retrieve a Patient Document");
			System.out.println("8 - Quit");
			System.out.println("---------------------------------------\n");

			System.out.print("Enter your option number :");
			selection = Integer.parseInt(input.nextLine().trim());

			switch (selection) {

			case 1: // Create a new Patient Demographic
				System.out.println("\nSelected: Create a new Patient Demographic");
				System.out.print("Enter Patient's First Name :");
				Patientinfo[0] = input.nextLine();
				System.out.print("Enter Patient's Last Name :");
				Patientinfo[1] = input.nextLine();
				System.out.print("Enter Patient's Age :");
				Patientinfo[2] = input.nextLine();
				System.out.print("Enter Patient's Date of Birth (yyyy-mm-dd):");
				Patientinfo[3] = input.nextLine();

				dbHandler.createNewDemograhipcInfo(Patientinfo);
				break;

			case 2: // Retrive a Patient Demographic by ID
				System.out.println("\nSelected: Retrive a Patient Demographic");
				System.out.print("Enter Patient's ID number :");

				dbHandler.getDemographicInfo(Integer.parseInt(input.nextLine().trim()));
				break;

			case 3:// Delete a Patient Demographic by ID
				System.out.println("\nSelected: Delete a Patient Demographic");
				System.out.print("Enter Patient's ID number :");

				dbHandler.deleteDemographicInfo(Integer.parseInt(input.nextLine().trim()));
				break;

			case 4: // Search Patient Demographics
				System.out.println("\nSelected: Search Patient Demographics");
				System.out.print("Enter Patient's First Name :");
				Patientinfo[0] = input.nextLine();
				System.out.print("Enter Patient's Last Name :");
				Patientinfo[1] = input.nextLine();
				System.out.print("Enter Patient's Age :");
				Patientinfo[2] = input.nextLine();
				System.out.print("Enter Patient's Date of Birth (yyyy-mm-dd):");
				Patientinfo[3] = input.nextLine();

				// perform the search and print results
				dbHandler.demographicInfoSearch(Patientinfo);
				break;

			case 5:// View all Patient Demographics
				System.out.println("\nSelected: View all Patient Demographics");
				dbHandler.getAllDemographicInfo();
				break;

			case 6:// Add a new Patient Document
				System.out.println("\nSelected: Add a new Patient Document");
				System.out.print("Enter Document type (Passport,Driver Licence,OHIP etc.) :");
				Documentinfo[0] = input.nextLine();
				System.out.print("Enter the Document issuer (ex: Government of Canada) :");
				Documentinfo[1] = input.nextLine();
				System.out.print("Enter the Document ID :");
				Documentinfo[2] = input.nextLine();
				System.out.print("Enter Patient's ID :");
				Documentinfo[3] = input.nextLine();

				// perform the search and print results
				dbHandler.createDocumentReference(Documentinfo);
				break;

			case 7:// Retrieve a Patient Document
				System.out.println("\nSelected: View all Patient Demographics");
				System.out.print("Enter the Document issuer (ex: Government of Canada) :");
				String issuer = input.nextLine();
				System.out.print("Enter the Document ID :");
				String id = input.nextLine();

				dbHandler.getDocumentReference(issuer, Integer.parseInt(id.trim()));
				break;

			case 8: // Perform "quit" case
				// close scanner
				input.close();
				loop = false;
				break;

			default: // The user input an unexpected choice.
				System.out.println("\nPlease enter a number from 1-8");
			}
		}

		System.out.println("Goodbye !");

	}

}
