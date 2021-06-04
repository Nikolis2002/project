import java.io.IOException;
import java.util.Scanner;

public class MenuHelper 


{
    private MenuHelper(){};
     static Scanner scan=new Scanner(System.in);



    public static int scanInt()
    {
        try{
        String str1=scan.nextLine(); //scannarw to input
        String str=str1.trim();  //kanw trim ta whitespaces
        int integer=Integer.parseInt(str); //to mtatrepw se akeraiw kai to guranw
        return integer;
        }catch(NumberFormatException a){System.out.println("αυτό που βάλατε δεν είναι αριθμός");}
        return 0;
    }


    public static double scanDouble()
    {
        double d;
        try{
        String str1=scan.nextLine(); //paromoio opws h scanInt
        String str=str1.trim();
        d=Double.parseDouble(str);
        return d;
        }catch(NumberFormatException a){System.out.println("αυτό που βάλατε δεν είναι αριθμός");}
        return 0;
    }

    public static String checkString(String checker1,String checker2,String checker3)
    {
        boolean check=true;
        String checked;
        do
        {
            checked=scan.nextLine().trim();
             check=checked.equals(checker1)||checked.equals(checker2)||checked.equals(checker3); //checkarei to input an einai iso me tous checkers an oxi prepei na to xanadwseis
                if(!check)
                    System.out.print("Δεν βάλατε σωστό input,ξαναδώστε την απάντηση σας: ");
        }while(!check);

        return checked;
    }

    public static String checkString(String checker1,String checker2)
    {
        boolean check=true;
        String checked;
        do
        {
             checked=scan.nextLine().trim();
             check=checked.equals(checker1)||checked.equals(checker2);
                if(!check)
                    System.out.print("Δεν βάλατε σωστό input,ξαναδώστε την απάντηση σας: ");
        }while(!check);

        return checked;
    }


    public static String phoneChecker()
    {
        boolean flag=true; 
        String phone;
        
        do
        {
            phone=scan.nextLine().trim();

            if(phone.length()!=10)  //ta ellhnika thlefwna exoun 10 arithmous
                {
                    flag=false;
                    System.out.print("Δεν δώσατε το τηλέφωνο σας,ξαναπροσπαθήστε: ");
                }
            else
                for(int i=0; i<phone.length(); i++)
                    {
                        int checker=phone.charAt(i); //an periexei xarakthra den einai thlefwno
                        
                        if(checker<48||checker>57) //h asc timh twn 0 kai 9
                            {
                                flag=false;
                                System.out.print("To input που βάλατε δεν είναι αριθμός,ξαναπροσπαθήστε: ");
                                break;
                            }
                        else
                            flag=true;
                    }
        }while(!flag);

        return phone;

    }

    public static String nameChecker()
    {
        String name;
        boolean flag=true;

        do
        {
            name=scan.nextLine().trim();
            
            if(name.length()==0)
                {
                    System.out.print("Δεν δώσατε το όνομα σας,ξαναπροσπαθήστε: ");
                    flag=false;
                }
            else
            for(int i=0; i<name.length(); i++)
            {
                int checker=name.charAt(i); //an periexei arithmo den einai onoma
                
                if(checker>48&&checker<57)
                    {
                        flag=false;
                        System.out.print("To input που βάλατε δεν είναι όνομα,ξαναπροσπαθήστε: ");
                        break;
                    }
                else
                    flag=true;
            }
        }while(!flag);

            return name;
    }

        public static void userInput()
        {
            String next=scan.nextLine(); //gia na dwsei to enter
        }

        
        
        public static void clearScreen() {
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //ftiaxnei ena
                }
                else {
                    System.out.print("\033[H\033[2J"); //gia linux,ios,etc(πρεπει να τρέχει σε terminal ομως)
                    System.out.flush();
                }
            } catch (IOException | InterruptedException ex) {System.out.println(ex.toString());}
        }

}

  
