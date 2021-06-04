public abstract class User
 {
    private String name;
    private String phone;

    protected User(String name,String phone)  //protected  gt den ftiaxnoume antikeimena kai klhronomeitai
    {
        this.name=name;
        this.phone=phone;
    }
    
    public String getName() //ftiaxnoume setters kai getters
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }
}
