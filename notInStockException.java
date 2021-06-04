public class notInStockException extends Exception
{
    private final String message;

    public notInStockException(String message)
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
