import java.util.*;

class EmptyStudentListException extends Exception {
    public EmptyStudentListException(String message){
        super(message);
    }
}

class StudentListManager {
    private List<String> students=new ArrayList<>();

    public void addStudent(String name){
        students.add(name);
    }

    public void removeStudent(String name){
        if(students.remove(name)){
            System.out.println(name+" removed.");
        }else{
            System.out.println(name+" not found.");
            
        }
    }

    public void displayStudents() throws EmptyStudentListException {
        if(students.isEmpty()){
            throw new EmptyStudentListException("Student list is empty!");
        }
        Collections.sort(students);
        System.out.println("Sorted names: "+students);
    }

    public void menu(){
        Scanner sc=new Scanner(System.in);
        int choice;
        do{
            System.out.println("\nStudent List Menu");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Display Students");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter student name to add: ");
                    String addName=sc.nextLine();
                    addStudent(addName);
                    System.out.println(addName+" added.");
                    break;
                case 2:
                    System.out.print("Enter student name to remove: ");
                    String removeName=sc.nextLine();
                    removeStudent(removeName);
                    break;
                case 3:
                    try{
                        displayStudents();
                    }catch(EmptyStudentListException e){
                        System.out.println("Error: "+e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }while(choice!=4);
    }
}

class AverageCalculator {
    public void calculateAverage(){
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> numbers=new ArrayList<>();
        String input;

        System.out.println("Enter numbers (type 'done' to finish):");
        while(true){
            input=sc.nextLine();
            if(input.equalsIgnoreCase("done")) break;

            try{
                int num=Integer.parseInt(input);
                numbers.add(num);
            }catch(NumberFormatException e){
                System.out.println("Error: Invalid input, please enter a valid integer.");
            }
        }

        try{
            if(numbers.isEmpty()){
                throw new ArithmeticException("Cannot calculate average, list is empty!");
            }
            int sum=0;
            for(int n:numbers) sum+=n;
            double avg=(double)sum/numbers.size();
            System.out.println("Average: "+avg);
        }catch(ArithmeticException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
public class MainProgram {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        StudentListManager studentManager=new StudentListManager();
        AverageCalculator avgCalc=new AverageCalculator();
        int choice;

        do{
            System.out.println("\nMain Menu");
            System.out.println("1. Manage Student List");
            System.out.println("2. Calculate Average of Numbers");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    studentManager.menu();
                    break;
                case 2:
                    avgCalc.calculateAverage();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }while(choice!=3);

        sc.close();
    }
}