import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class OptimisedDSApp {
	static final ActualDS actualDs=new ActualDS();
	static String filepath="C:\\SaveData\\SaveData";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
        //Read object from file
		
		try{
			    actualDs.ReadObjectFromFile(filepath);
		        
		}catch(Exception e){e.printStackTrace();}
	
	      

		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to Optimised Data Structure App");
		System.out.println("Enter 1: To create new Student:");
		System.out.println("Enter 2: To search a student:");
		System.out.println("Enter 3: To delete a student:");
		System.out.println("Enter 4: To save & exit data to a file");
		int val=0;
		String ID;
		boolean value;
		while(val!=4){
		val=sc.nextInt();
		switch(val){    
	    //case statements within the switch block  
	    case 1: 
	    	System.out.println("You have chosen create option!");
	    	System.out.println("Please enter following");
	    	System.out.println("Studet ID: ");
	    	ID=sc.next();
	    	System.out.println("Studet name: ");
	    	String name=sc.next();
	    	System.out.println("Studet GPA: ");
	    	String GPA=sc.next();
	    	value=actualDs.Insert(ID, name, GPA);
	    	if(value){System.out.println("Insertion Succesful");}
	    	else{System.out.println("Insertion failed");}
	    	System.out.println("Please enter the option again:");
	    	break;
	    case 2:
	    	System.out.println("You have chosen search option!");
	    	System.out.println("Enter Student ID");
	    	ID=sc.next();
	    	student stu=actualDs.Search(ID);
	    	if(stu==null){System.out.println("Student not Found");}
	    	else{
	    		System.out.println("Name:"+stu.getName());
	    		System.out.println("Name:"+stu.getGPA());
	    	}
	    	System.out.println("Please enter the option again:");
	        break;    
	    case 3:
	    	System.out.println("You have chosen delete option!");
	    	System.out.println("Enter Student ID");
	    	ID=sc.next();
	    	value=actualDs.delete(ID);
	    	if(value){System.out.println("Deletion Succesful!");}
	    	else{System.out.println("Deletion Failed");}
	    	System.out.println("Please enter the option again:");
	        break;    
	    case 4: 
	    	System.out.println("You have chosen save data option!"); 
	    	value=actualDs.Exit();
	    	if(value){System.out.println("Data saved succesfully!");}
	    	else{System.out.println("Failed to save");}
	    	System.out.println("Closing App:");
	        break;    
	    
	    default:
	    	System.out.println("Invalid selection!");
	    	System.out.println("Make Sure to select one of these: ");
			System.out.println("Enter 1: To create new Student:");
			System.out.println("Enter 2: To search a student:");
			System.out.println("Enter 3: To delete a student:");
			System.out.println("Enter 4: To save & exit data to a file");
			
	    	
	    } }   
	}
}
	class student implements Serializable{
		private String ID;
		private String name;
		private String GPA;
		
		public student(String ID,String name,String GPA){
			this.ID=ID;
			this.name=name;
			this.GPA=GPA;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGPA() {
			return GPA;
		}

		public void setGPA(String gPA) {
			GPA = gPA;
		}

		@Override
		public String toString() {
			return "student [ID=" + ID + ", name=" + name + ", GPA=" + GPA
					+ "]";
		}
	}
	 class studentIDMapping{
		HashMap hmap =new HashMap<String,student>() ;
	}
	
	class ActualDS{
		ActualDS(){}
		HashMap<Integer,studentIDMapping>studentMap=new HashMap<>();
		
		String year;
		student stu;
		public boolean Insert(String ID,String name,String GPA){
			int gpa=Integer.parseInt(GPA);
			if(gpa>10){
				System.out.println("GPA Must be less than 10");
				return false;}
			if(ID.length()<=4||ID.charAt(0)=='0'|| ID.charAt(0)=='1'){
				System.out.println("Wrong ID!!");
				return false;
			}else{
				year=ID.substring(0,4);
				
			}
		
			if(!year.equals(null)){
				int yearNumber=Integer.parseInt(year);
				int indexInList=yearNumber-2000;
				
				if(!studentMap.containsKey(indexInList)){
					studentMap.put(indexInList,new studentIDMapping());
				}
				if(studentMap.get(indexInList).hmap==null){
					studentMap.get(indexInList).hmap=new HashMap<String,student>();
				}
				student stu=new student(ID,name,GPA);
				studentMap.get(indexInList).hmap.put(ID, stu);
			}else{
				return false;
			}
			return true;
		}
		
		public student Search(String ID){
			stu=null;
			if(ID.length()<=4||ID.charAt(0)=='0'|| ID.charAt(0)=='1'){
				System.out.println("Wrong ID!!");
				return null;
			}else{
				year=ID.substring(0,4);
				System.out.println("Year: "+year);

			}
			if(!year.equals(null)){
				int yearNumber=Integer.parseInt(year);
				int indexInList=yearNumber-2000;
              
				if(!studentMap.containsKey(indexInList)){
					return null;
				}
				if(studentMap.get(indexInList).hmap.containsKey(ID)){
					stu=(student) studentMap.get(indexInList).hmap.get(ID);
					
					return stu;
				}else{
					return null;
				}

			}else{
				return null;
			}

		}
		public boolean delete(String ID){
			
			if(ID.length()<=4||ID.charAt(0)=='0'|| ID.charAt(0)=='1'){
                System.out.println("Wrong ID!!");
				return false;
			}else{
				year=ID.substring(0,4);
			}
			if(!year.equals(null)){
				int yearNumber=Integer.parseInt(year);
				int indexInList=yearNumber-2000;
				
				if(!studentMap.containsKey(indexInList)){
					return false;
				}
				if(studentMap.get(indexInList).hmap.containsKey(ID)){
					studentMap.get(indexInList).hmap.remove(ID);
					
					return true;
				}else{
					return false;
				}

			}else{
				return false;
			}
			
		}
		
		public boolean Exit(){
			
	

            List<student>studentSerialList=new ArrayList<>();
		    boolean flag=false;	
			String filepath="C:\\SaveData\\SaveData";
			 FileOutputStream fileOut=null;
			 ObjectOutputStream objectOut=null;
			 try{
			 
			fileOut = new FileOutputStream(filepath);
	         objectOut = new ObjectOutputStream(fileOut);
	         }catch(Exception e){
	        	 e.printStackTrace();
	        	 return false;
	         }
			 for(int ShortId : studentMap.keySet()){
				 HashMap entryMap = studentMap.get(ShortId).hmap;
				 Iterator<Map.Entry<String, student>> itr = entryMap.entrySet().iterator();
				 while(itr.hasNext())
			        {
			             Map.Entry<String, student> entry = itr.next();
			             student serObj=entry.getValue();
			             studentSerialList.add(serObj);
    
			        }
			 }
 	         try {

	          //  objectOut.writeObject(serObj);
 	        	 FileOutputStream f=new FileOutputStream(filepath);
		 	     ObjectOutputStream os1 = new ObjectOutputStream(f);
		 		 os1.writeObject(studentSerialList);
		 		 os1.close();
 	        	
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }

			return true;
		}
		

		public Object ReadObjectFromFile(String filepath) {
			ActualDS actualDSObj=new ActualDS();
			
			ObjectInputStream objectinputstream = null;
			try {
			    FileInputStream streamIn = new FileInputStream(filepath);
			    objectinputstream = new ObjectInputStream(streamIn);
			    List<student> readCase = (List<student>) objectinputstream.readObject();
			    for(int i=0;i<readCase.size();i++){
			    	
			    	student st=readCase.get(i);
			    	actualDSObj.Insert(st.getID(), st.getName(), st.getGPA());
			    	System.out.println(readCase.get(i));
			    	
			    }
			    System.out.println();
			    System.out.println("****************");
			    System.out.println("Above Data loaded into the memory");
			    System.out.println();
			    System.out.println("****************");
			    
			} catch (Exception e) {
			    e.printStackTrace();
			} 
			return true;
		}
	}
	



