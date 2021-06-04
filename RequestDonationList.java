import java.util.ArrayList;
import java.util.Iterator;

public class RequestDonationList
{
    private ArrayList<RequestDonation> rdEntities=new ArrayList<RequestDonation>();

    public RequestDonationList(){};

    public RequestDonation get(int ID) 
    {
            RequestDonation obj=null;
            for(var x: rdEntities)
            {
                if(ID==x.getEntDon().getID())
                    obj=x;
            }
            return obj; //epistefetai h anafora, etsi opws exei ylopoihthei h menu den mas peirazei an epistrepsei null
    }

    public boolean requestExists(int id) //elegxei an yparxei me bash to id
    {
        boolean flag=false;
        for(var x: rdEntities)
            {
                if(id==x.getEntDon().getID())
                    flag=true;
            }
            
            return flag;
    }
    
    public void add(RequestDonation r) throws ItemNotinListException, notInStockException, TooMuchException //ta 2 teleytaia exceptions ta kanei throw giati kaleitai apo thn super
    {                                                                                                       //ths requests
        Iterator<RequestDonation> it= rdEntities.iterator();
        boolean flag=false;

        if(r.getEntDon()==null)
            throw new ItemNotinListException("το είδος που ζητάτε δεν υπάρχει στην λίστα."); //an den yparxei katholoy petaei exception
            
            int count=0;
            for(var z: Organization.getOrg().getEntityList()) //an yparxei kai den einai sto entityList petaei exception
            {
                if(!r.getEntDon().entExists(z))
                count++; 
            }
            if(count== Organization.getOrg().getEntityList().size())
                throw new ItemNotinListException("το είδος που ζητάτε δεν υπάρχει στην λίστα.");

        
        while(it.hasNext())      
        {
            RequestDonation e=it.next();
            if(e.exists(r)==true)
            {
                e.setQuantity(e.getQuantity()+r.getQuantity()); //an yparxei prosthetai ston hdh yparxon
                flag=true;
            }


        }
        if(!flag)
        {
            rdEntities.add(r); //an den yparxei to kanoume apla add
        }
            
        
        
    }
        
    

    public void remove(RequestDonation e)
    {
        rdEntities.remove(e);
    }

    public void modify(int j,double quantity) throws notInStockException, TooMuchException 
    {
        get(j).setQuantity(quantity);
    }


    public void  reset()
    {
        Iterator<RequestDonation> it= rdEntities.iterator();
        
        while(it.hasNext())
        {
            it.next();
            it.remove();  
        }
    }

     public void monitor()
     {
         for(var z: rdEntities)
         {
             System.out.println(z.getEntDon().getName()+" "+z.getEntDon().getID()+" "+z.getQuantity());
         }
     }   

     public ArrayList<RequestDonation> returnOrder() //getter
     {
         return rdEntities;
     }

    
}