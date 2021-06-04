public class TooMuchException extends Exception
{
    private final String message;

    public TooMuchException(String message)
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
