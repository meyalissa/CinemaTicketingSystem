/**
 * Super Class
 */
public class Customer 
{
   protected String name; 
   protected Ticket movie;
   protected char member;
   private int paymentMethod;
   //default constructor
   public Customer()
   {
       name = "unknown";
       movie = new Ticket();
       member = '0';
   }
   //normal constructor
   public Customer(String name, Ticket movie, char member)
   {
       this.name = name;
       this.movie = movie;
       this.member = member;
   }
   //retriever
   public void setName(String name){this.name=name;}
   public void setMovie(Ticket movie){this.movie=movie;} 
   public void setMember(char member){this.member=member;}
   public void setPayMethod(int paymentMethod){this.paymentMethod=paymentMethod;}
   //mutator
   public String getName(){return name;}
   public Ticket getMovie(){return movie;}
   public char getMember(){return member;}
   public int getPayMethod(){return paymentMethod;}
   //operator
   public double calcTotPrice()
   {
       double finalPrice = movie.calcTotTick(); 
       return finalPrice;
   }
   
   public void memberValidation()
   {}
   public String StringPayMethod(int paymentMethod)
   {   
       String pay;
       if (paymentMethod == 1)
           pay = "Cash";
       else if (paymentMethod == 2)
           pay = "Card";
       else 
           pay = "QR";
           
        return pay;
    }
   public String receipt(double fp,char redeem)
   {
       return ("\n"+ name + "'s ticket details" + 
                movie.toString());
   }
  
}
