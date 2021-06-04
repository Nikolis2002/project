public class Beneficiary extends User
 {
    private int noPersons=1;
    private RequestDonationList recievedList;
    private Requests requestsList;

    public Beneficiary(String name,String phone,int noPersons)
    {
        super(name,phone);
        requestsList= new Requests(this);  //gia na deixnei panta sto antikeimeno pou dhmiourgeitai
        recievedList=new RequestDonationList();
        this.noPersons=noPersons;
    }

    public int getNoPersons()  //setters kai getters
    {
        return this.noPersons;
    }

    public void setNoPersons(int noPersons)
    {
        this.noPersons=noPersons;
    } 

    public boolean benefExists(Beneficiary B) //elegxei an yparxei o Beneficiary
    {
        if(this.getPhone().equals(B.getPhone()))
            return true;
        else
            return false;
    }

    public RequestDonationList returnRecievList() //getter
    {
        return this.recievedList;
    }

    public Requests returnRequestList()
    {
        return this.requestsList;
    }

    public RequestDonation returnRequest(int id) //wrappers
    {
        return requestsList.get(id);
    }

    public void addRequest(RequestDonation e) throws ItemNotinListException, notInStockException, TooMuchException
    {
        requestsList.add(e);
    }

    public void removeRequest(RequestDonation a)
    {
        requestsList.remove(a);
    }

    public void modifyRequest(int j,double quantity) throws notInStockException, TooMuchException
    {
        requestsList.modify(j,quantity);
    }

    public void commit() throws notInStockException,TooMuchException, ItemNotinListException
    {
        requestsList.commit();
    }

    public void resetRequests()
    {
        requestsList.reset();
    }
    
    public void seeRequests()
    {
        requestsList.monitor();
    }

    public void seeReceived()
    {
        recievedList.monitor();
    }

    public void resetReceived()
    {
        recievedList.reset();
    }



 }
