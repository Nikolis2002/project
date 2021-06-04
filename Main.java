public class Main 
{
    public static void main(String[] args)
    {
            try{
        Menu m=new Menu();
        Admin admin= new Admin("Nick", "6980612790");
         Organization org= new  Organization("ceid",admin);
        Material sugar= new Material("sugar","yummy",10,100,200,300);
        Material milk= new Material("milk","good",11,400,500,600);
        Material rice= new Material("rice","carbs",12,200,300,350);


        Service medicalSupport=new Service("medicalSupport","health",13);
        Service nurseySupport=new Service("nurseySupport","health",14);
        Service babySitting=new Service("babySitting","family",15);

       
        Beneficiary ben= new Beneficiary("Bob", "6977151027", 2);
        Beneficiary alexhs=new Beneficiary("Alexhs","6974902780",5);
        Donator giannhs=new Donator("Giannhs","6978672878");
        RequestDonation req1=new RequestDonation(sugar, 210);
        RequestDonation req2=new RequestDonation(medicalSupport, 100);
        RequestDonation req3=new RequestDonation(medicalSupport, 100);
        RequestDonation req4=new RequestDonation(rice,560);
        RequestDonation req5=new RequestDonation(nurseySupport,560);

         org.insertDonator(giannhs);
         org.insertBeneficiary(ben);
         org.insertBeneficiary(alexhs);
         org.addEntity(sugar);
         org.addEntity(milk);
         org.addEntity(rice);
         org.addEntity(medicalSupport);
         org.addEntity(nurseySupport);
         org.addEntity(babySitting);
         org.addCurrentDon(req1);
         org.addCurrentDon(req2);
         org.addCurrentDon(req3);
         org.addCurrentDon(req4);
         org.addCurrentDon(req5);
         //org.removeEntity(sugar,admin);

        
        m.menuStart();
        MenuHelper.scan.close();
         }catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        catch(AlreadyExistsException a){System.out.println(a.toString());} catch (ItemNotinListException e) {e.printStackTrace();}
        catch (notInStockException e) {System.out.println(e.toString());}
        catch (TooMuchException e) {System.out.println(e.toString());}
    
    }
}
