import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class NetBenefit {

	public static void main(String[] args) {
		
		
		//reading txt file
		BufferedReader reader;
		String path = args[0];
		String line = null;
		
		//creating queue
		IntQueueImpl<Integer> queuePrice = new IntQueueImpl();
		IntQueueImpl<Integer> queueQuantity = new IntQueueImpl();
		
		//need to tokenize string
		//StringTokenizer tokenizer;
		String[] lineTokens = new String[4];
		
		//need to manage sells
		int sellPrice = 0;
		int sellQuantity = 0;
		int total = 0;
		int benefit = 0;
		
		
		
		try{
			
			reader = new BufferedReader(new InputStreamReader( new FileInputStream(path) ) ); //reading txt file with BufferedReader
			
			
			try{
				
				line = reader.readLine(); //read first line
				while(line != null){ //while the is no EOF keep searching the file
					lineTokens = line.split(" "); //tokenize string wthen you fine space between two words 
					
					//checking action buy or sell
					if(lineTokens[0].equals("buy")){ //if action is buy
						queueQuantity.put(Integer.parseInt(lineTokens[1])); //take the quantity
						total += Integer.parseInt(lineTokens[1]); //add total shares
						queuePrice.put(Integer.parseInt(lineTokens[3])); //take the price
					}else{ //if action is sell
						sellPrice = Integer.parseInt(lineTokens[3]);
						sellQuantity = Integer.parseInt(lineTokens[1]);
						if(sellQuantity <= total){ //checking quantity of the items that we are going to sell
							while(sellQuantity > 0){   //while we still got shares to sell 
								if(sellQuantity - queueQuantity.peek() < 0){ //if its the last sell action
									int remaining = queueQuantity.peek() - sellQuantity; //get the remaining shares
									int rPrice = queuePrice.peek(); //get the price of the remaining sells
									benefit += (queueQuantity.get() - remaining) * (sellPrice - queuePrice.get()); //add the benefit for this action
									queueQuantity.put(remaining); //adding the remaining on the queue
									queuePrice.put(rPrice); //adding the price of the remainig on the queue
									sellQuantity = 0;
								}else{ //if its not the last sell action
									sellQuantity -= queueQuantity.peek(); //reducing sold each time
									benefit += queueQuantity.get() * (sellPrice - queuePrice.get());// recalculating benefit
								}//end if/else
							}//end while
						}else{ //if sell shares is > that total shares
							System.out.println("This action cannot be done because your shares are not enough.");
							System.out.println("Action: " + line);
						}
						
					}//end if
					//queueQuantity.printQueue(new PrintStream(System.out));
					line = reader.readLine();
				}//end while
				
			}catch(IOException e){
				System.out.println("Error while reading file");
			}//end try
			
			
		}catch(FileNotFoundException e){
			System.out.println("File doesnt exist! :(");
		}//end try
		
		System.out.println("--------------------------");
		System.out.println("Shares left");
		System.out.println("--------------------------");
		queueQuantity.printQueue(new PrintStream(System.out));
		System.out.println("--------------------------");
		
		System.out.println("Price of Shares left");
		System.out.println("--------------------------");
		queuePrice.printQueue(new PrintStream(System.out));
		
		
		System.out.println("--------------------------");
		System.out.println("Your befenit for the gives action is: " +  benefit);
	}//end main

}//end class
