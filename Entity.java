public abstract class Entity 
{
    private String name;
    private String description;
    private int id;
    private boolean isMaterial;

    protected Entity(String name,String description,int id,boolean isMaterial) //protected gt den dhmiourgountai antikeimena kai klhronimeitai
    {
        this.name=name;
        this.description=description;
        this.id=id;
        this.isMaterial=isMaterial;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    public void setPhone(String description)
    {
        this.description=description;
    }
    
    public int getID()
    {
        return this.id;
    }
    
    public void setID(int id)
    {
         this.id=id;
    }
    
    public boolean getISmaterial()
    {
            return this.isMaterial;
    }

    public void setISmaterial(boolean isMaterial)
    {
        this.isMaterial=isMaterial;
    }
    
    public String getEntityInfo()
    {
        return "Το όνομα του προιόντος είναι:"+name+" ,η περιγραφή του είναι:"+description+" ,και ο κώδικος του είναι:"+id;
    }

    public abstract String getDetails(); //abstract giati tha ylopoihthei apo tis paragwges

    public String toString()
    {
        return getEntityInfo()+" "+getDetails();
    }

    public boolean entExists(Entity e) //elegxei an yparxei ti entity me bash to id
    {
        if(this.id==e.id)
            return true;
        else 
            return false;
    }

    

    
}
