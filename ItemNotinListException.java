public class ItemNotinListException extends Exception
{
    private final String message;
    
    public ItemNotinListException(String message)
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
