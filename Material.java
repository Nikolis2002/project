public class Material extends Entity
{
    private  final double LEVEL1,LEVEL2,LEVEL3;

    public Material(String name,String description,int id,double level1,double level2,double level3)
    {
        super(name,description,id,true);
        LEVEL1=level1;
        LEVEL2=level2;
        LEVEL3=level3;
    }

    
public String getDetails()
{
    return "Το level 1 είναι: "+LEVEL1+" materials"+" το level2 είναι: "+LEVEL2+" materials"+" το level3 είναι: "+LEVEL3+"materials"+" και το αντικείμενο είναι τύπου material.";
}

public double returnLevel(int person) //epistefei ta levels me bash posa atoma yparxoun se mia oikogeneia
{
    if(person==1)
        return LEVEL1;
    else if(person>=2&&person<=4)
        return LEVEL2;
    else 
        return LEVEL3;
}
}
