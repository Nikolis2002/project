public class RequestDonation 
{
    private Entity entity;
    private double quantity;


    public RequestDonation(Entity e,double quantity)
    {
        entity=e;
        this.quantity=quantity;
    }

    public Entity getEntDon()
    {
        return entity;
    }

    public void setQuantity(double quantity)
    {
        this.quantity=quantity;
    }

    public double getQuantity()
    {
        return this.quantity;
    }

    boolean exists(RequestDonation a)
    {
        if(this.entity.getID()==a.entity.getID()) //elegxei me bash to id
            return true;
        else
            return false;
    }

    public String getInfo()
    {
        return entity.toString()+" η πόσοτητα είναι: "+quantity; //epistrefei tis plhrofories
    }
}
