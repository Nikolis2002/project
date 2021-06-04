public class Service extends Entity
{
    public Service(String name,String description,int id)
    {
        super(name,description,id,false);
    }
 
   
    public String getDetails()
   {
       return  "Το αντικείμενο είναι service.";
   }
    
}
