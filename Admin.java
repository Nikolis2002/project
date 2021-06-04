public class Admin extends User
 {
    private boolean isAdmin=true;

    public Admin(String name,String phone)
    {
        super(name,phone);  //ftiaxnoume setters kai getters
    }
    public boolean getIsAdmin()
    {
        return this.isAdmin;
    }
    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin=isAdmin;
    }
 }
