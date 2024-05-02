
/**
    Inheritance
    SubClass
 */

public class Membership extends Customer
{
    private String memberID;
    private int points;
    
    //default constructor
    public Membership()
    {
        memberID = "unknown";
        points =0;
    }
    //normal constructor
    public Membership(String name,Ticket movie,char member, String memberID, int points)
    {
        super(name,movie,member);
        this.memberID=memberID;
        this.points=points;
    }
    //retriever
    public void setMemberID(String memberID){this.memberID=memberID;}
    public void setPoints(int points){this.points=points;}
    //mutator
    public String getMemberID(){return memberID;}
    public int getPoints(){return points;}
    
    //operator
    
    public void memberValidation()
    {   
        System.out.println ("This customer have membership");
    }
    
    //update point based on purchase of ticket and point redeem (using overloading method)
    public int updatePoint()
    {
        return this.points +(10*movie.getNumbOfSeat());//1ticket=10point
    
    }
    
    public int updatePoint(char redeem)
    {
       if(redeem == 'Y' )
        {
            return updatePoint()-1000;
        }
        else
        {
            return updatePoint();
        }
    }
    
    public boolean checkPoint()
    {
        if(updatePoint()>=1000)
        {
            return true;
        }
        else
        {
            System.out.println("This customer can't redeem point");
            return false;
        }
    }
    
    //redeem point
    public double giveDiscount(char redeem)
    {
        double discount=0;
        if (redeem=='Y')
            discount = super.calcTotPrice() * 0.2;
        return discount;
    }
    
    //calculation
    public double calcFinalPrice(char redeem)
    {
       double finalP = 0.00;
       if (redeem == 'Y')
       {
           finalP =super.calcTotPrice()- giveDiscount(redeem);
       }
       else 
       {
           finalP = super.calcTotPrice();
       }
       return finalP;
    }
    
    //print (overidden method)
    public String receipt(double fp,char redeem)
    {
        return(super.receipt(fp,redeem) + 
                "\nDiscount : -RM"+(giveDiscount(redeem)) +
                "\nFinal Price : RM" + fp);
    }
}
