
/**
    Inheritance
    SubClass
 */
public class NonMembership extends Customer
{
   private String phoneNo, email;
   //default constructor 
   public NonMembership()
   {
        phoneNo="unknown";
        email="unknown";
   }
   //normal constructor
   public NonMembership(String name, Ticket movie, char member, String phoneNo, String email)
   {
       super(name,movie,member);
       this.phoneNo=phoneNo;
       this.email=email;
   }
   //retriever
   public void setPhoneNo(String phoneNo){this.phoneNo=phoneNo;} 
   public void setEmail(String email){this.email=email;}
   //mutator
   public String getPhoneNo(){return phoneNo;}
   public String getEmail(){return email;}
   
   //operator
   public void memberValidation()
   {System.out.println ("This customer does not have membership");}
    
    //create membership 
   public void createMembership()   
   {
       System.out.println("Member details:"+
                           "\nName: "+getName()+
                           "\nPhone number: "+phoneNo+
                           "\nE-mail: "+email);
   }
   public double createMemberPrice()
   {
       double memberPrice= 10.00;
       return memberPrice;
   }
   public double calcFinalPrice()
   {
       return (super.calcTotPrice()+createMemberPrice()); //pay rm10 for membership
   }
   public String receipt(double fp,char redeem)
    {
        return(super.receipt(fp,redeem) + 
                "\nCreate Membership : +RM" + createMemberPrice()+
                "\nFinal Price : RM" + fp);            
    }
}
