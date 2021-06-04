import java.util.ArrayList;
import java.util.Iterator;

public class Requests extends RequestDonationList
{
    private Beneficiary b;


    public Requests(Beneficiary l)
    {
        b=l;
    }

    
    @Override
    public void add(RequestDonation e) throws ItemNotinListException, notInStockException,TooMuchException
    {
        if(e.getEntDon()==null)
        throw new ItemNotinListException("το είδος που ζητάτε δεν υπάρχει στην λίστα."); //an den yparxei to entity pou thelei petaei exception
         
        boolean exist=b.returnRequestList().requestExists(e.getEntDon().getID()); //elegxoume an yparxei sto requestlist hdh
        for(var x: Organization.getOrg().returnCurDon().returnOrder())
        {  
            
                double y;

                        if(exist)
                    y=x.getQuantity()-e.getQuantity()-b.returnRequestList().get(e.getEntDon().getID()).getQuantity(); //an nai afairoume kai thn posothta pou exei zhthsei prin
                        else
                    y=x.getQuantity()-e.getQuantity(); //an oxi afairoume thn posothta pou zhtaei twra

                if(e.exists(x)&&(x.getQuantity()>0)&&(y>=0)&&validRequestDonation(e,true)) //bazoume true sto validRequestDonation gt mporei na exei zhthsei hdh thn posothta
                {
                    super.add(e); //kaleitai h supper add kai kanei return
                    return;
                }
                
            
         
            if(!validRequestDonation(e,true))
            {
                throw new TooMuchException("Η ποσότητα που ζητάς μαζί με αυτά που έχεις ζητήσει ξεπερνά το όριο!"); //an zhtaei perissotera apo osa prepei na parei
            }
        }
                throw new notInStockException("Δεν υπάρχει αρκετή ποσότητα στον οργανισμό."); //an to programma ftasei edw shmainei pws den ekane return ara logw demorgan isxuei to
                                                                                             //!e.exists(x)||!(x.getQuantity>0)||!y>=0
    }
    
    @Override
    public void modify(int j,double quantity) throws notInStockException,TooMuchException
    {
        for(var x: Organization.getOrg().returnCurDon().returnOrder())
        {    
             if(get(j).exists(x)&&(x.getQuantity()>0)&&validRequestDonation(j,quantity))  //elegxw to deytero validRequestDonation
                {
                    super.modify(j,quantity);  
                    break; //kanoume break an epiteuxthei allagei gia teliwsei to pogramma
                }

            if(x.getQuantity()<=0)
                throw new notInStockException("Το αντικείμενο που ζητάτε έχει τελειώσει.");
            if(!validRequestDonation(j,quantity))
                throw new TooMuchException("Η ποσότητα που ζητάς μαζί με αυτά που έχεις ζητήσει ξεπερνά το όριο!");
        }
     
    } 

    public boolean validRequestDonation(RequestDonation r,boolean flag) //bazw flag giati analoga an trexw panw sto requestList h oxi,prepei na kanw diaforetiko elgxo
    {
        boolean exist=b.returnRecievList().requestExists(r.getEntDon().getID());  //elegxoume na yparxei sto requestList alla kai sto receivedList gia na sygkrinoume osa exei parei
        boolean exists2=b.returnRequestList().requestExists(r.getEntDon().getID()); //me to epitrepomeno orio
        double quantity;
        if(r.getEntDon().getISmaterial()==true)
        {
            if(exist&&exists2&&flag)
                quantity=r.getQuantity()+b.returnRecievList().get(r.getEntDon().getID()).getQuantity()+b.returnRequestList().get(r.getEntDon().getID()).getQuantity();
            else if(exist)
                quantity=r.getQuantity()+b.returnRecievList().get(r.getEntDon().getID()).getQuantity();
            else if(exists2&&flag)
                quantity=r.getQuantity()+b.returnRequestList().get(r.getEntDon().getID()).getQuantity();
            else
                quantity=r.getQuantity();

                if(quantity>((Material)r.getEntDon()).returnLevel(b.getNoPersons())) //κανω cast γιατί το returnLevel() ειναι μέθοδος της Μaterial
                    return false;
                else 
                    return true;
        }
        else
            return true;
    }

    public boolean validRequestDonation(int j,double quantity) 
    {
        boolean exist=b.returnRecievList().requestExists(j); //kanw mono sto recievedList gt tropopoiw thn posothta sto request ara den eexei nohma na kanw kai sta 2
        double sum;
        if(get(j).getEntDon().getISmaterial()==true)
        {
           if(exist)
            sum=quantity+b.returnRecievList().get(j).getQuantity();
            else
                sum=quantity;

                if(sum>((Material)get(j).getEntDon()).returnLevel(b.getNoPersons()))
                    return false;
                else 
                    return true;
        }
        else
            return true;
    }
    
        

    public void commit() throws notInStockException,TooMuchException, ItemNotinListException
    {
        ArrayList<Integer> served=new ArrayList<Integer>();
        boolean flag=false;
        Iterator<RequestDonation> it=  Organization.getOrg().returnCurDon().returnOrder().iterator();
        for(var x:returnOrder())
        {    
           
             if(!validRequestDonation(x,false))
                throw new TooMuchException("Η ποσότητα που ζητάς μαζί με αυτά που έχεις ζητήσει ξεπερνά το όριο!");
               
                else{  //elegxw an to antikeimeno yparxei stocurrentDonations
                        while(it.hasNext())
                        {
                            RequestDonation y=it.next();
                            double quant=y.getQuantity()-x.getQuantity();
                            if(y.exists(x)&&(x.getQuantity()>0)&&(quant>=0)) //an nai afairw thn posothta 
                            {
                                flag=true;
                                y.setQuantity(y.getQuantity()-x.getQuantity());
                                break;
                            }
                            
                        }
                        if(!flag) // !flag=!y.exists(x)||!(x.getQuantity()>0)||!(quant>=0) logw Demorgan
                            throw new notInStockException("Δεν υπάρχει αρκετή ποσότητα στον οργανισμό.");

                        b.returnRecievList().add(x); //to kanw add sthn receivedList
                        served.add(x.getEntDon().getID()); //to kanw add sto arraylist gia na ta afairesw meta
                    }
                    

        }

            for(var x: served)
            {
                remove(get(x)); //afairw ta antikeimena apo to requestList
            }
         
    }



}
