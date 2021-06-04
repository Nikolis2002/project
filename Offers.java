
public class Offers extends RequestDonationList
{
    public void commit() throws ItemNotinListException, notInStockException, TooMuchException
    {
        for(var x:returnOrder())
        {
            Organization.getOrg().returnCurDon().add(x); //h add elegxei apo monh ths an yparxei h posothta
        }
        reset(); //otan teliwsei na ta kanei add sto currentDonations kanei reset thn lista
    }
    

     
}
