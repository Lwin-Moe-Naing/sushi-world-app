package c209L05;

import java.io.*;
import java.util.ArrayList;

import C09L01S1.Helper;

public class SushiWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
ArrayList<Item> itemList=Load();
int option=-1;
while( option!=5) {
	menu();
	option=Helper.readInt("Enter choice");
	if(option==1) {
		viewAllItems(itemList);
	}else if(option==2) {
		addItem();
		itemList=Load();
	}else if(option==3) {
		getMostExpensive(itemList);
		
	}else if(option==4) {
		getCheapest(itemList);
	}else if(option==5) {
		System.out.println("Thank u for using sushi wolrd!");
	}else {
		System.out.println("Invalid option");
	}
}
	}
	
	private static void getCheapest(ArrayList<Item> itemList) {
		// TODO Auto-generated method stub
		Item cheapest=itemList.get(0);
		
		for(Item i:itemList) {
			if(cheapest.getPrice()>i.getPrice()) {
				cheapest=i;
			}
		}
		System.out.println(String.format("The cheapest item is the %s.It costs %.2f", cheapest.getType(),cheapest.getPrice()));
	}

	private static void getMostExpensive(ArrayList<Item> itemList) {
		// TODO Auto-generated method stub
		Item mostExp =itemList.get(0);
		
		for(Item i:itemList) {
			if(mostExp.getPrice()<i.getPrice()) {
				mostExp=i;
			}
		}
		System.out.println(String.format("The most expensive item is the %s.It costs %.2f",
				mostExp.getType(),mostExp.getPrice()));
	}

	private static void addItem() {
		// TODO Auto-generated method stub
		String itemType=Helper.readString("Enter new item type>");
		double itemPrice=Helper.readDouble("Enter new item price>$");
		
		try {
			File file=new File("sushi.txt");
			FileWriter fw=new FileWriter(file,true);
			BufferedWriter bw=new BufferedWriter(fw);
			
			bw.write(String.format("\n%s,%f", itemType,itemPrice));
			
			bw.close();
		}catch(IOException io) {
			io.printStackTrace();
		}
	}

	private static void viewAllItems(ArrayList<Item> itemList) {
		// TODO Auto-generated method stub
		String output="";
		output+=String.format("%-20s%-10s\n","ITEM","PRICE");
		
		for(Item i:itemList) {
			output+=String.format("%-20s%-10.2f\n", i.getType(),i.getPrice());	
			}
		System.out.println(output);
	}

	

	public static void menu() {
		Helper.line(70, "-");
		System.out.println("WELCOME TO SUSHI WORLD");
		Helper.line(70, "-");
		System.out.println("1.View all items");
		System.out.println("2.Add item");
		System.out.println("3.Most expensive item");
		System.out.println("4.Cheapest item");
		System.out.println("5.Quit");
		
	}

	private static ArrayList<Item> Load() {
		// TODO Auto-generated method stub
		//return null;
		ArrayList<Item> itemList=new ArrayList<Item>();
		try {
			File file=new File("sushi.txt");
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			
			String line=br.readLine();
			
			while(line!=null) {
				String[] itemInfo =line.split(",");
                Item item=new Item(itemInfo[0],Double.parseDouble(itemInfo[1]));
                itemList.add(item);
                line=br.readLine();
                	
			}
			br.close();
		}catch(FileNotFoundException e) {
			System.out.println("The file could not be found:(");
		}catch(IOException io) {
			System.out.println("There was an I/o error");
		}
		return itemList;
	}

}
