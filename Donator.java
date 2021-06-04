public class Donator extends User
{
    private Offers offersList=new Offers();
    
    public Donator(String name,String phone)
    {
        super(name,phone);
    }
    public boolean donExists(Donator d) //elegxei an yparxei o Donator
    {
        if(this.getPhone().equals(d.getPhone()))
            return true;
        else 
            return false;
    }

    public void commitOffer() throws ItemNotinListException, notInStockException, TooMuchException //wrappers
    {
        offersList.commit();
    }

    public void addOffer(RequestDonation e) throws ItemNotinListException, notInStockException, TooMuchException
    {
        offersList.add(e);
    }

    public void removeOffer(RequestDonation e)
    {
        offersList.remove(e);
    }

    public void resetOffer()
    {
        offersList.reset();
    }

    public void monitorOffers()
    {
        offersList.monitor();
    }

    public RequestDonation getOffer(int Id) 
    {
        return offersList.get(Id);
    }

    public Offers returnOfferList() //getter
    {
        return this.offersList;
    }

}
