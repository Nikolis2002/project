public class AlreadyExistsException extends Exception 
{
    private final String message;
    public AlreadyExistsException(String message)
    {
        super();
        this.message=message;
    }
    
    @Override
    public String toString()
    {
        return this.message;
    }
}
