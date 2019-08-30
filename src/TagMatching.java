import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class TagMatching {
	
	public static void main(String[] args){
		
		
		boolean validFile = false;
		//reading html file
		BufferedReader reader;
		String path = args[0];
		String line = null;
		
		
		//Stack OBJ
		StringStackImpl<String> stack = new StringStackImpl();
		
		//varieables to control the stack elements
		String lastPush = null;
		
		//exception
		try{
			//fis = new FileInputStream(htmlFile);
			reader = new BufferedReader(new InputStreamReader( new FileInputStream(path) ) ); //reading html file
			
			
			try{
				
				line = reader.readLine(); //read first line of html file
				
				while(line != null){ //while there is not EOF
					
					
					String tag = ""; //tag will be saved at this string char by char
					for(int i = 0; i < line.length(); i++){ //search all the length of line
						int length = i; //keep position
						if(line.charAt(i) == '<'){ //if there is a tag
							
							tag += line.charAt(length); //create string tag
							length++; //go to next char
							
							while(true){
								tag += line.charAt(length); //add tag on string tag
								length++; //go to the next char
								if(line.charAt(length) == '>'){ //if you find the symbol that ends the tag
									tag += line.charAt(length); //add it on the string tag
									
									//getting last tag before entering new tag on the top of stack
									if(stack.size() != 0){
										lastPush = stack.peek();
									}//end if
									stack.push(tag); //add tag on the stack
									
									
									//edw tha ginontai oi elenxoi ths stoivas
									if(isClosingTag(tag, lastPush)){ //if tag is closing tag of lastPush then remove both of them
										if(stack.size() >= 2){
											stack.pop();
											stack.pop();
										}//end if
										
										if(!stack.isEmpty()){
											lastPush = stack.peek(); //then making lastPush the tag on the top of stack 
										}//end if
									}//end if
									
									stack.printStack(new PrintStream(System.out)); //printing stack
									System.out.println("------------------------------");
									tag = "";
									break;
								}//end if
								
							}//end while
							
						}//end if
					}//end for
		
					line = reader.readLine(); //read nextLine
					
				}//end while
				
				
				reader.close();
			}catch(IOException e){
				System.out.println("Error while readeing the file!");
			}//end try
			
			
			
			
			
		}catch(FileNotFoundException e){
			System.out.println("File not found at " + path);
		}//end try/catch
		
		if(stack.isEmpty()){
			System.out.println("HTML file is valid!");
		}else{
			System.out.println("HTML file is not valid!");
		}
		
	}//end main
	
	//this method checks if tag1 is closing tag of tag2
	public static boolean isClosingTag(String tag1, String tag2){
		int lengthTag1 = 0;
		int lengthTag2 = 0;
		
		if(tag1 != null && tag2 != null){
			lengthTag1 = tag1.length();//closing Tag 
			lengthTag2 = tag2.length();
		}//end if
		
		if(lengthTag1 == lengthTag2 + 1){ //checking length of two tags, tag's length + 1 =  closing tag's length 
			lengthTag1 = 0;
			lengthTag2 = 0;
			lengthTag1++; //skipping <
			lengthTag1++; //skipping /
			lengthTag2++; //skipping <
			boolean tagsLookLike = true;
			while(lengthTag2 != tag2.length()){ //could use lengthTag2 != tag2.length() too
				if(tag1.charAt(lengthTag1) == tag2.charAt(lengthTag2)){
					lengthTag1++;
					lengthTag2++;
				}else{
					tagsLookLike = false;
					break;
				}//end if
			}//end while
			return tagsLookLike;
		}//end if
		return false;
	}

}//end TagMtching
