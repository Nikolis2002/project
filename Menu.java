
import java.util.concurrent.TimeUnit;

public class Menu 
{
   
    private String phone=" ";
    private Donator don=null;
    private String answer=" ";
    private Beneficiary benef=null;
    private int selection=0;
    private Entity ent=null;
    private RequestDonation r=null;
    private int answer2=0;
    
    
    public void menuStart() throws AlreadyExistsException, InterruptedException   //ta exception auta ta xeirizetai h main
    {
     while(true)
        {
            System.out.println("Καλώς ήρθατε στον οργανισμό "+Organization.getOrg().getName());
            whatUserIsThis();
            MenuHelper.clearScreen();

            if(selection<0) //h synthkh gia na kanei break
                break;
            
            if(isDonator())
                menuDonator();
            else if(isBenef())
                menuBeneficiary();
            else if(  Organization.getOrg().getAdmin().getPhone().equals(phone))
                menuAdmin();
            else 
                newUser();


        }
    }

    public void menuDonator() throws InterruptedException    //kanei throw to exception ths sleep sthn main
   {
     
        
    
    
        System.out.println("Καλώς ήρθατε "+don.getName()+" στο σύστημα οργάνωσης εθελοντών δωρητών ο "+  Organization.getOrg().getName());
        selection=0;
        
        while(true)
        {
            

            switch(selection)
            {
                
                    case 0:
                    
                        System.out.println("Στον οργανισμό μας μπορείτε να:");
                        System.out.println("1-Add offer(Δήλωση προσφοράς)"+"\n"+"2-Show offers(Εμφάνιση και τροποποιήση προσφορών)"+"\n"+"3-Commit(ολοκλήρωση δωρεάς)");
                        System.out.println("4-Back(Επιστροφή στο προηγούμενο επίπεδο)"+"\n"+"5-Logout"+"\n"+"6-Exit");
                        
                        do
                        {
                            System.out.print("Εισάγετε την επιλογή σας(σωστό νούμερο απο 1-6):");
                            selection=MenuHelper.scanInt();  //xrhsimopoiw thn scanner kai methodous apo thn MenuHlelper gia ton swsto elegxo 
                            System.out.println(""); //afhnw ena keno
                        }while(selection<=0||selection>6);
                    
                        MenuHelper.clearScreen(); //bazw clearScreen otan paw apo ena epipedo tou menu sto allo opws edw apo to 0 se opoio tha epilexthei
                        break;
                    
                        case 1:
                    
                          Organization.getOrg().listEntitiesAndQuantity();
                        System.out.print("Θέλετε να επιλεξέτε μία κατηγορία απο τις 2; Αν ναι δώστε το όνομα της κατηγορίας αλλιώς πατήστε 4 για να πάτε πίσω: "); 

                        answer=MenuHelper.checkString("Materials","Services","4");
                        System.out.println("");
                        if(answer.equals("Materials"))
                                selection=11;
                            else if(answer.equals("Services"))
                                selection=13;
                        else if(answer.equals("4"))
                                selection=0;

                                MenuHelper.clearScreen();
                                
                                break;
                    case 11:
                    
                              Organization.getOrg().listMaterials();
    

                        System.out.print("Θέλετε να επιλέξετε ένα απο τα Materials;(y-ναι,n-όχι,4-πίσω): ");
                        
                        answer=MenuHelper.checkString("y","n","4");

                        if(answer.equals("y"))
                            selection=12;
                        else if(answer.equals("n")||answer.equals("4"))
                        {
                            selection=1;
                            MenuHelper.clearScreen();
                        }
                    
                            
                            break;
                            
                    case 12:
                    do
                    {
                        System.out.print("Μπορείτε να επιλέξετε τι Material θέλετε να κάνετε δωρεά.Δώστε το id του Material: ");
                        answer2=MenuHelper.scanInt();
                        System.out.println("");
                        ent=entReturn(answer2,true); //true giati elegxw ta materials
                    }while(ent==null);  //den epistrefetai null an mou dwsei swsto id ara teliwnei to while
                    
                    System.out.print("Θέλετε να προσφέρετε την δωρεά;(y-ναι,n-όχι,4-πίσω): ");
                    answer=MenuHelper.checkString("y","n","4");  // gia ton elegxo pou thelei na metabei o xrhsths xrhsimopoiw thn checkString ths MenuHelper
                    if(answer.equals("y"))
                        {
                            System.out.print("Δώστε την ποσότητα που θέλετε να κάνετε δωρεά: ");
                            double quantity=MenuHelper.scanDouble();
                            RequestDonation d= new RequestDonation(ent,quantity); //dhmiourgei ena neo antikeimeno me tis plhrofories pou exei dwsei
                            selection=0; 
                                try {
                                    don.addOffer(d);  // to addOffer mporei na petaxei sthn ousia mono to 1o exception ta alla yparxoun logw ths epikalypshs sthn Requests
                                } catch (ItemNotinListException | notInStockException | TooMuchException e) //logw tou do-while synhthws den tha proklhthei auto exception 
                                {
                                    selection=11; //an proklhthei exception gyrnaei se ena epipedo pio panw gia na xanaepilexei o xrhsths
                                    System.out.println(e.toString());
                                    TimeUnit.SECONDS.sleep(2); //kathysterw to programma 2 deyterolepta gia na mporei na typwthei to mhnyma
                                }// bazw na kanei edw catch to exception  gia na mhn stamata thn omalh roh tou programmatos 
                                
                        }
                    else if(answer.equals("4")||answer.equals("n"))
                        selection=11;

                        MenuHelper.clearScreen();

                        break;
                    
                    case 13:
                    
                          Organization.getOrg().listServices();
                        

                        System.out.print("Θέλετε να επιλέξετε ένα απο τα Services;(y-ναι,n-όχι,4-πίσω): ");
                        answer=MenuHelper.checkString("y","n","4");

                        if(answer.equals("y"))
                        {
                            selection=14;
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                        {
                            selection=1;
                            MenuHelper.clearScreen();
                        }
                    

                            break;
                            
                     case 14:
                            do
                            {
                                System.out.print("Μπορείτε να επιλέξετε τι Service θέλετε να κάνετε δωρεά.Δώστε το id του Service: ");
                                answer2=MenuHelper.scanInt();
                                System.out.println("");
                                ent=entReturn(answer2,false); //false giati elegxw ta Services
                            }while(ent==null);
                            
                            System.out.print("Θέλετε να προσφέρετε την δωρεά ;(y-ναι,n-όχι,4-πίσω).");
                            answer=MenuHelper.checkString("y","n","4");
                            if(answer.equals("y"))
                            {
                                System.out.print("Δώστε την ποσότητα που θέλετε να κάνετε δωρεά: ");
                                double quantity=MenuHelper.scanDouble();
                                RequestDonation d= new RequestDonation(ent,quantity); 
                                try {
                                    don.addOffer(d);
                                } catch (ItemNotinListException | notInStockException | TooMuchException e) 
                                {
                                    selection=13;
                                    System.out.println(e.toString());
                                    TimeUnit.SECONDS.sleep(2);
                                }
                                selection=0;
                            }
                            else if(answer.equals("4")||answer.equals("n"))
                                selection=13;

                                MenuHelper.clearScreen();
                                
                                break;

                    case 2:
                    
                        if(!don.returnOfferList().returnOrder().isEmpty())
                        {
                            don.monitorOffers();
                            System.out.print("1)Επιλογή παροχής"+'\n'+"2)Καθαρισμός όλων των παροχών."+'\n'+"3)Καταχώρηση δωρεάς"+'\n'+"4)Επιστροφή : ");
                            
                            
                            do
                            {
                                System.out.print("Δώστε έναν ακέραιο απο 1 έως 4: ");
                                answer2=MenuHelper.scanInt();
                            }while(answer2<1||answer2>4);

                            if(answer2==1)
                            {
                                selection=21;
                                MenuHelper.clearScreen();
                            }
                            else if(answer2==2)
                                selection=24;
                            else if(answer2==3)
                                selection=25;
                            else if(answer2==4)
                            {
                                selection=0;
                                MenuHelper.clearScreen();
                            }
                        }
                               
                        else
                        {
                            System.out.println("Δεν υπάρχουν διαθέσιμες προσφορές.");
                            selection=0;
                
                            TimeUnit.SECONDS.sleep(2);

                            MenuHelper.clearScreen();
                        }
                        break;
                    
                        
                    case 21:
                        don.monitorOffers();
                        do
                        {
                            System.out.print("Δώστε το id του Offer που θέλετε να τροποποιήσετε: ");
                            answer2=MenuHelper.scanInt(); 
                            System.out.println("");
                            r=don.getOffer(answer2);
                        }while(r==null);
                        System.out.println("Οι πληροφορίες της παροχής που επιλέξατε: "+r.getInfo());
                        System.out.print("1)Διαγραφή παροχής με 1"+"\n"+"2)Τροποποιήση ποσότητας παροχής με 2"+"\n"+"3) 4 για πίσω: ");
                        answer2=MenuHelper.scanInt();

                        if(answer2==1)
                            selection=22;
                        else if(answer2==2)
                            selection=23;
                        else if(answer2==4)
                        {
                            selection=2;
                            MenuHelper.clearScreen();
                        }


                            break;

                        case 22:
                        System.out.print("Μπορείτε να διαγράψετε την παροχή(y-διαγραφή,n-καμία αλλαγή,4-πίσω : ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                            don.removeOffer(r); //kaleitai o wrapper
                            selection=0;
                            MenuHelper.clearScreen();
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                        {
                            selection=21;
                            MenuHelper.clearScreen();
                        }

                            break;
                        
                        case 23:
                        System.out.print("Μπορείτε να τροποποιήσετε την ποσότητα(y-τροποποίηση,n-καμία αλλαγή,4-πίσω: ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                        System.out.println("Δώσε την ποσότητα");
                            double quantity=MenuHelper.scanDouble();
                            r.setQuantity(quantity); //allazw to quantity
                            selection=0;
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                            selection=21;

                            MenuHelper.clearScreen();

                            break;
                        
                        case 24:
                        System.out.print("Μπορείτε να διαγράψετε όλες τις δωρεές σας(y-διαγραφή,n-καμία αλλαγή,4)-πίσω : ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                        don.resetOffer();
                            selection=0;
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                            selection=2;
                            
                            MenuHelper.clearScreen();

                            break;

                        case 25:
                        System.out.print("Μπορείτε να καταχωρήσετε όλες τις δωρεές σας πατώντας y η πατήστε 4 για πίσω: ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                            try 
                            {
                                don.commitOffer();
                                System.out.println("H δωρεά σας καταχωρήθηκε ευχαριστούμε!");
                                TimeUnit.SECONDS.sleep(2);
                            } catch (ItemNotinListException | notInStockException | TooMuchException e) 
                            {
                                System.out.println(e.toString());
                                TimeUnit.SECONDS.sleep(2);
                            }
                            selection=0;
                            MenuHelper.clearScreen();
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                            selection=2;

                            MenuHelper.clearScreen();

                            break;
                        
                        case 3:
                        
                            try 
                            {
                                don.commitOffer();
                                System.out.println("H δωρεά σας καταχωρήθηκε ευχαριστούμε!");
                                TimeUnit.SECONDS.sleep(2);
                            } catch (ItemNotinListException | notInStockException | TooMuchException e) 
                            {
                                System.out.println(e.toString());
                                    TimeUnit.SECONDS.sleep(2);
                            }
                            catch(InterruptedException e){e.printStackTrace();}
                            selection=0;
                            MenuHelper.clearScreen();
                        
                        break;

                        case 4:
                        MenuHelper.clearScreen();
                            return; //xrhsimopoioume thn return gia na bgw apo to loop
                        
                        case 5:
                        MenuHelper.clearScreen();
                            return;
                    

                        case 6:
                            System.out.println("Ευχαριστούμε που επιλέξατε τον οργανισμό μας");
                            System.exit(0); //xrhsimopoioume thn exit gia na termathsoume to programma
                        
                        default:
                        selection=0;
                        break;
                    }
        }
   }
   
                
   public void menuBeneficiary() throws InterruptedException
   {
     
        //h menuBeneficiary einai idia me thn menuDonator me thn monh diafora oti xrhsimopoiw tis methodous ths Beneficiary
    
    
        System.out.println("Καλώς ήρθατε "+benef.getName()+" στο σύστημα οργάνωσης εθελοντών δωρητών ο "+  Organization.getOrg().getName());
        selection=0;
        
        while(true)
        {
            

            switch(selection)
            {
                
                    case 0:
                    
                    System.out.println("Στον οργανισμό μας μπορείτε να:");
                    System.out.println("1-Add Request(Δήλωση αίτησης)"+"\n"+"2-Show Requests(Εμφάνιση και τροποποιήση αιτήσεων)"+"\n"+"3-Commit(ολοκλήρωση αίτησης)");
                    System.out.println("4-Back(Επιστροφή στο προηγούμενο επίπεδο)"+"\n"+"5-Logout"+"\n"+"6-Exit");
                        
                        do
                        {
                            System.out.print("Εισάγετε την επιλογή σας(σωστό νούμερο απο 1-6):");
                            selection=MenuHelper.scanInt();
                            System.out.println("");
                        }while(selection<=0||selection>6); //elegxei tis times pou dinei o xrhsths
                    
                        MenuHelper.clearScreen(); 
                        break;
                    
                        case 1:
                    
                          Organization.getOrg().listEntitiesAndQuantity();
                        System.out.print("Θέλετε να επιλεξέτε μία κατηγορία απο τις 2; Αν ναι δώστε το όνομα της κατηγορίας αλλιώς πατήστε 4 για να πάτε πίσω: "); 

                        answer=MenuHelper.checkString("Materials","Services","4");
                        System.out.println("");
                        if(answer.equals("Materials"))
                                selection=11;  
                            else if(answer.equals("Services"))
                                selection=13;
                        else if(answer.equals("4"))
                                selection=0;

                                MenuHelper.clearScreen();
                                
                                break;
                    case 11:
                    
                              Organization.getOrg().listMaterials();
    

                        System.out.print("Θέλετε να επιλέξετε ένα απο τα Materials;(y-ναι,n-όχι,4-πίσω): ");
                        
                        answer=MenuHelper.checkString("y","n","4");

                        if(answer.equals("y"))
                            selection=12;
                        else if(answer.equals("n")||answer.equals("4"))
                        {
                            selection=1;
                            MenuHelper.clearScreen();
                        }
                    
                            
                            break;
                            
                    case 12:
                    do
                    {
                        System.out.print("Μπορείτε να επιλέξετε τι Material θέλετε να ζητήσετε.Δώστε το id του Material: ");
                        answer2=MenuHelper.scanInt();
                        System.out.println("");
                        ent=entReturn(answer2,true);
                    }while(ent==null);
                    
                    System.out.print("Θέλετε να κάνετε την αίτηση;(y-ναι,n-όχι,4-πίσω): ");
                    answer=MenuHelper.checkString("y","n","4");
                    if(answer.equals("y"))
                        {
                            System.out.print("Δώστε την ποσότητα που θέλετε να πάρετε: ");
                            double quantity=MenuHelper.scanDouble();
                            RequestDonation d= new RequestDonation(ent,quantity);
                            try 
                            {
                                benef.addRequest(d);
                                    selection=0;
                                
                            }catch (ItemNotinListException | notInStockException | TooMuchException e)  
                            {
                                selection=11;
                                System.out.println(e.toString());
                                TimeUnit.SECONDS.sleep(2);
                            }
                        }
                            else if(answer.equals("4")||answer.equals("n"))
                                selection=11;
    
                        MenuHelper.clearScreen();

                        break;
                    
                    case 13:
                    
                          Organization.getOrg().listServices();
                        

                        System.out.print("Θέλετε να επιλέξετε ένα απο τα Services;(y-ναι,n-όχι,4-πίσω): ");
                        answer=MenuHelper.checkString("y","n","4");

                        if(answer.equals("y"))
                        {
                            selection=14;
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                        {
                            selection=1;
                            MenuHelper.clearScreen();
                        }
                    

                            break;
                            
                     case 14:
                            do
                            {
                                System.out.print("Μπορείτε να επιλέξετε τι Service θέλετε να πάρετε.Δώστε το id του Service: ");
                                answer2=MenuHelper.scanInt();
                                System.out.println("");
                                ent=entReturn(answer2,false);
                            }while(ent==null);
                            
                            System.out.print("Θέλετε να κάνετε την αίτηση;(y-ναι,n-όχι,4-πίσω): ");
                            answer=MenuHelper.checkString("y","n","4");
                            if(answer.equals("y"))
                            {
                                System.out.print("Δώστε την ποσότητα που θέλετε να πάρετε: ");
                                double quantity=MenuHelper.scanDouble();
                                RequestDonation d= new RequestDonation(ent,quantity);
                                try 
                                {
                                    benef.addRequest(d);
                                        selection=0;
                                    

                                }catch (ItemNotinListException | notInStockException | TooMuchException e)  
                                {
                                    selection=13;
                                    System.out.println(e.toString());
                                    TimeUnit.SECONDS.sleep(2);
                                }
                            }
                            
                            else if(answer.equals("4")||answer.equals("n"))
                                    selection=13;

                                MenuHelper.clearScreen();
                                
                                break;

                        case 2:
                    
                        if(!benef.returnRequestList().returnOrder().isEmpty())
                        {
                            benef.seeRequests();
                            System.out.print("1)Επιλογή παροχής"+'\n'+"2)Καθαρισμός όλων των παροχών."+'\n'+"3)Καταχώρηση αίτησης"+'\n'+"4)Επιστροφή : ");
                            
                            do
                            {
                                System.out.print("Δώστε έναν ακέραιο απο 1 έως 4: ");
                                answer2=MenuHelper.scanInt();
                            }while(answer2<1||answer2>4);

                            if(answer2==1)
                            {
                                selection=21;
                                MenuHelper.clearScreen();
                            }
                            else if(answer2==2)
                                selection=24;
                            else if(answer2==3)
                                selection=25;
                            else if(answer2==4)
                            {
                                selection=0;
                                MenuHelper.clearScreen();
                            }
                        }
                               
                        else
                        {
                            System.out.println("Δεν υπάρχουν διαθέσιμες αιτήσεις.");
                            selection=0;
                            
                                TimeUnit.SECONDS.sleep(2);
                          
                        }
                        break;
                    
                        
                    case 21:
                        benef.seeRequests();
                        do
                        {
                            System.out.print("Δώστε το id του Offer που θέλετε να τροποποιήσετε.");
                            answer2=MenuHelper.scanInt();
                            System.out.println("");
                            r=benef.returnRequest(answer2);
                        }while(r==null);
                        System.out.println("Οι πληροφορίες της παροχής που επιλέξατε: "+r.getInfo());
                        System.out.print("1)Διαγραφή παροχής με 1"+"\n"+"2)Τροποποιήση ποσότητας παροχής με 2"+"\n"+"3)4 για πίσω : ");
                        answer2=MenuHelper.scanInt();

                        if(answer2==1)
                            selection=22;
                        else if(answer2==2)
                            selection=23;
                        else if(answer2==4)
                        {
                            selection=2;
                            MenuHelper.clearScreen();
                        }


                            break;

                        case 22:
                        System.out.print("Μπορείτε να διαγράψετε την παροχή(y-διαγραφή,n-καμία αλλαγή,4-πίσω : ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                            benef.removeRequest(r);
                            selection=0;
                            MenuHelper.clearScreen();
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                        {
                            selection=21;
                            MenuHelper.clearScreen();
                        }

                            break;
                        
                        case 23:
                        System.out.print("Μπορείτε να τροποποιήσετε την πόσοτητα(y-τροποποίηση,n-καμία αλλαγή,4-πίσω : ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                        System.out.print("Δώσε την ποσότητα");
                        double quantity=MenuHelper.scanDouble();
                            try {
                                benef.modifyRequest(r.getEntDon().getID(), quantity);
                            } catch (notInStockException | TooMuchException e) 
                            {
                                System.out.println(e.toString());
                                TimeUnit.SECONDS.sleep(2);
                            }
                            selection=0;
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                            selection=21;

                            MenuHelper.clearScreen();

                            break;
                        
                        case 24:
                        System.out.print("Μπορείτε να διαγράψετε όλες τις αιτήσεις σας(y-διαγραφή,n-καμία αλλαγή,4-πίσω : ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                            benef.resetRequests();
                            selection=0;
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                            selection=2;
                            
                            MenuHelper.clearScreen();

                            break;

                        case 25:
                        System.out.print("Μπορείτε να καταχωρήσετε όλες τις αιτήσεις σας πατώντας y η πατήστε 4 για πίσω : ");
                        answer=MenuHelper.checkString("y","n","4");
                        if(answer.equals("y"))
                        {
                            try 
                            {
                                benef.commit();
                                System.out.println("H αίτηση σας καταχωρήθηκε ευχαριστούμε!");
                                TimeUnit.SECONDS.sleep(2);
                            } catch (ItemNotinListException | notInStockException | TooMuchException e) 
                            {
                                System.out.println(e.toString());
                                    TimeUnit.SECONDS.sleep(2);
                            }
                            selection=0;
                            MenuHelper.clearScreen();
                        }
                        else if(answer.equals("4")||answer.equals("n"))
                            selection=2;

                            MenuHelper.clearScreen();

                            break;
                        
                        case 3:
                        
                            try 
                            {
                                benef.commit();
                                System.out.println("H αίτηση σας καταχωρήθηκε ευχαριστούμε!");
                                TimeUnit.SECONDS.sleep(2);
                            } catch (ItemNotinListException | notInStockException | TooMuchException e) 
                            {
                                System.out.println(e.toString());
                                TimeUnit.SECONDS.sleep(2);
                            }
                            
                            selection=0;
                            MenuHelper.clearScreen();
                        
                        break;

                        case 4:
                        MenuHelper.clearScreen();
                            return;

                        case 5:
                        MenuHelper.clearScreen();
                            return;
                    

                        case 6:
                            System.out.println("Ευχαριστούμε που επιλέξατε τον οργανισμό μας");
                            System.exit(0);
                        
                        default:
                        selection=0;
                        break;
                    }
        }
   }
       

     















public void menuAdmin() throws InterruptedException
{
        System.out.println("Καλώς ήρθατε "+  Organization.getOrg().getAdmin().getName()+" στο σύστημα οργάνωσης εθελοντών δωρητών ο "+  Organization.getOrg().getName());
        selection=0;
    while(true)
    {
    switch(selection) //ta case 0 kai 1 einai pomoia me thn menuDonator
    {
        case 0:
            System.out.println("Στον οργανισμό μας μπορείτε να:");
            System.out.println("1-View"+"\n"+"2-Monitor Organization"+"\n"+"3-Back");
            System.out.println("4-logout"+"\n"+"5-exit");
            
            do
            {
                System.out.print("Εισάγετε την επιλογή σας(σωστό νούμερο απο 1-5):");
                selection=MenuHelper.scanInt();
                System.out.println("");
            }while(selection<=0||selection>5);

            MenuHelper.clearScreen();
            
            break;
    
        case 1:
              Organization.getOrg().listEntitiesAndQuantity();
            System.out.print("Θέλετε να επιλεξέτε μία κατηγορία απο τις 2; Αν ναι δώστε το όνομα της κατηγορίας αλλιώς πατήστε 3 για να πάτε πίσω: ");
            answer=MenuHelper.checkString("Materials","Services","3");
            System.out.println("");
            if(answer.equals("Materials"))
                selection=11;
            else if(answer.equals("Services"))
                selection=13;
            else if(answer.equals("3"))
                selection=0;
                
                MenuHelper.clearScreen();          
                
                break;
        
        case 11:
                  Organization.getOrg().listMaterials();
            
            do
            {
                    System.out.print("Μπορείτε να επιλέξετε πιο Material θέλετε να δείτε.Δώστε το id του Material: ");
                    answer2=MenuHelper.scanInt();
                System.out.println("");
                ent=entReturn(answer2,true);
            }while(ent==null);

            System.out.print("Θέλετε να δείτε τις λεπτομέρειες του προιόντος;Πατήστε y αν θέλετε αλλίως 3 για πίσω ");
            answer=MenuHelper.checkString("y","3");

            if(answer.equals("y"))
                selection=12;
            else if(answer.equals("3"))
                selection=1;

                break;

        
        case 12:

        System.out.println(ent.toString());
        selection=0;

        System.out.println("");
        System.out.print("Πάτα enter για να συνεχίσεις.");
        MenuHelper.userInput(); //zhatw apo ton xrhsth na pathsei enter gia na dei to mhnuma gia oso xrono thelei
        MenuHelper.clearScreen();
        
                
                break;
        
        case 13:
              Organization.getOrg().listServices();
            
            
            do
            {
                System.out.print("Μπορείτε να επιλέξετε τι Service να κάνετε δωρεά.Δώστε το id του Service: ");
                answer2=MenuHelper.scanInt();
                System.out.println("");
                ent=entReturn(answer2,false);
            }while(ent==null);

            System.out.print("Θες να δεις τις λεπτομέρειες του προιόντος;Πατήστε y αν θέλετε αλλίως 3 για πίσω ");
            answer=MenuHelper.checkString("y","3");

            if(answer.equals("y"))
                selection=14;
            else if(answer.equals("3"))
                selection=1;

                break;

        case 14:
   
                System.out.println(ent.toString());
                selection=0;

                System.out.println("");
                System.out.print("Πάτα enter για να συνεχίσεις.");
                MenuHelper.userInput();
                MenuHelper.clearScreen();
                break; 

        case 2:
            System.out.print("1)Εμφάνιση Beneficiaries"+"\n"+"2)Εμφάνιση Donators"+"\n"+"3)πίσω"+"\n"+"4)Reset τις λίστες με τα είδη των Donators ");
            
            do
            {
                answer2=MenuHelper.scanInt();
            }while(answer2<1||answer2>4);
            
            if(answer2==1)
                selection=21; 
            else if(answer2==2)
                selection=24;
            else if(answer2==3)
                selection=0;
            else if(answer2==4)
                selection=27;

                MenuHelper.clearScreen();

                break;
        
        case 21:
              Organization.getOrg().listBeneficiaries();
            do
            {
                System.out.println("Επιλέξτε έναν Beneficiary δίνοντας το τηλέφωνο του(Σωστό τηλέφωνο):");
                answer=MenuHelper.phoneChecker();
                benef=getBenef(answer);

            }while(benef==null); 

            if(!benef.returnRecievList().returnOrder().isEmpty())
                benef.seeReceived();
            else
                System.out.println("Δεν υπάρχει τίποτα στην λίστα");

            System.out.print("Μπορείτε να διαγράψετε τα αντικείμενα που έχει πάρει ο Beneficiary με 1,τον ίδιο τον Beneficiary με 2 και με 3 πίσω: ");
            answer2=MenuHelper.scanInt();

            if(answer2==1)
            {
                selection=22;
            }
            else if(answer2==2)
            {
                selection=23;
            }
            else if(answer2==3)
            {
                selection=2;
                MenuHelper.clearScreen();
            }
           

            break;
        case 22:
            System.out.print("Πατήστε y αν θέλετε να γίνει καθαρισμός της λίστας αλλιώς 3 για πίσω: ");
            answer=MenuHelper.checkString("y","3");
            if(answer.equals("y"))
                {
                    benef.resetReceived();
                    selection=0;
                }
            else if(answer.equals("3"))
                selection=21;

                MenuHelper.clearScreen();
                
                break;
        
        case 23:
        System.out.print("Πατήστε y αν θέλετε να διαγραφεί ο Beneficiary αλλιώς 3 για πίσω: ");
        answer=MenuHelper.checkString("y","3");

        if(answer.equals("y"))
            {
                  Organization.getOrg().removeBeneficiary(benef);
                selection=0;
            }
        else if(answer.equals("3"))
            selection=21;

            MenuHelper.clearScreen();
        
            break;

        case 24:
          Organization.getOrg().listDonators();
        do
        {
            System.out.println("Επιλέξτε έναν Donator δίνοντας το τηλέφωνο του(Σωστό τηλέφωνο):");
            answer=MenuHelper.phoneChecker();
            don=getDon(answer);

        }while(don==null);


        System.out.println("Μπορείτε να δείτε τα αντικείμενα που θέλει να δωρίσει ο Donator με 1,διαγραφή του Donator με 2 και να πάτε πίσω με 3.");
        answer2=MenuHelper.scanInt();

        if(answer2==1)
        {
            selection=25;
        }
        else if(answer2==2)
        {
           selection=26;
        }
        else if(answer2==3)
        {
            selection=2;
            MenuHelper.clearScreen();
        }

        MenuHelper.clearScreen();

        break;

        case 25:
        System.out.println("Τα offers που προσφέρει ο Donator: ");
        
        if(!don.returnOfferList().returnOrder().isEmpty())
            don.monitorOffers();  
        else
            System.out.println("Δεν υπάρχει τίποτα στην λίστα");
        
            TimeUnit.SECONDS.sleep(2);
            MenuHelper.clearScreen();
            
            selection=0;

        break;
        
        case 26:
        System.out.println("Μπορείτε να διαγράψετε τον ίδιο τον Donator με 1 και να πάτε πίσω με 3.");
        answer2=MenuHelper.scanInt();

        if(answer2==1)
        {
              Organization.getOrg().removeDonator(don);
            selection=0;
        }
        else if(answer2==3)
            selection=24;

            MenuHelper.clearScreen();

        break;
       

        case 27:

        for(var x: Organization.getOrg().getBenefList())
            {
                x.resetReceived();
            }
            selection=0;
            
            MenuHelper.clearScreen();
        
            break;

        case 3:
            MenuHelper.clearScreen();
            return ;

        case 4:
             MenuHelper.clearScreen();
            return ;

        case 5:
            System.exit(0);
            




    }
  }
}

    






public Entity entReturn(int id,boolean flag) //analoga to flag elegxei ta materials h ta services
    {
        Entity e=null;
        for(var x : Organization.getOrg().getEntityList())
        {   if(flag)
            {
                if(x.getID()==id&&x.getISmaterial())
                    e=x;
            }
            else if(x.getID()==id&&!x.getISmaterial())
            {e=x;} 
        }

            return e;

    }

    public Beneficiary getBenef(String phone)  //brhskei ton benef me bash to thlefwno
    {
        Beneficiary ben=null;
        for(var x:   Organization.getOrg().getBenefList())
        {
            if(x.getPhone().equals(phone)==true)
                ben=x;
        }
        return ben;
    }


    public Donator getDon(String phone) //brhskei ton Donator me bash to thlefwno
    {
        Donator d=null;
        for(var x:Organization.getOrg().getDonatorList())
        {
            if(x.getPhone().equals(phone)==true)
                d=x;
        }
        return d;
    }

    public void whatUserIsThis()
    {
        System.out.print("Αν θέλετε να συνεχίσετε πατήστε y αλλιώς n: ");
        answer=MenuHelper.checkString("y","n");
        if(answer.equals("n"))
                selection=-1; //mporw kai System.exit(0);
        else if(answer.equals("y"))
        {
            System.out.print("Δώστε το τηλέφωνο σας:");
            phone=MenuHelper.phoneChecker();
            System.out.println("");
        }
    }

    public void newUser() throws AlreadyExistsException
        {
            System.out.print("Μπορείτε να εγγραφείτε ως Donator ή ως Beneficiary, επιλέξτε τι από τα 2 θέλετε: ");
            answer=MenuHelper.checkString("Donator","Beneficiary");
            System.out.println("");

            if(answer.equals("Donator"))        //analoga ti tha epilexei me auta pou tha dwsei tha dhmiourghsei ena neo antikeimeno
            {
                
                System.out.println("Δώσε τα στοιχεία: ");
                System.out.print("Όνομα: ");
                String newName=MenuHelper.nameChecker();
                System.out.println("");
                   

                don=new Donator(newName,phone);
                  Organization.getOrg().insertDonator(don);
            }
            else if(answer.equals("Beneficiary"))
            {
                System.out.println("Δώσε τα στοιχεία:");
                System.out.print("Όνομα: ");
                String newName=MenuHelper.nameChecker();
                System.out.println("");
                

                System.out.print("Αριθμός ατόμων οικογένειας ");
                int newNoPersons;
                do
                {
                 System.out.print("Δώστε σωστό αριθμό ατόμων: ");
                 newNoPersons=MenuHelper.scanInt();
                }while(newNoPersons<=0);

                benef=new Beneficiary(newName,phone,newNoPersons);
                  Organization.getOrg().insertBeneficiary(benef);
            }
            
            MenuHelper.clearScreen();
        }

        public boolean isDonator() //elegxos an einai donator
    {
        for(var x:  Organization.getOrg().getDonatorList())
        {
            if(phone.equals(x.getPhone()))
                {
                    don=x;
                    return true;
                }
        }
            return false;
    }

    public boolean isBenef() //elegxos an einai beneficiary
    {
        for(var x:   Organization.getOrg().getBenefList())
        {
            if(phone.equals(x.getPhone()))
                {
                    benef=x;
                    return true;
                }
        }
            return false;
    }


    

    

}

    































