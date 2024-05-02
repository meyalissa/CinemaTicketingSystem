
/**
 * Composition Class
 */
public class Ticket
{
   private int movieTitle;
   private int numbOfSeat;
   private int row;
   private int column;
   
   public Ticket()
   {
       movieTitle= 0;
       numbOfSeat=0;
       row=0;
       column=0;
   }
   
   public Ticket(int movieTitle, int numbOfSeat, int row, int column)
   {
       this.movieTitle=movieTitle;
       this.numbOfSeat=numbOfSeat;
       this.row=row;
       this.column=column;
   }
   
   public void setTicket(int movieTitle, int numbOfSeat, int row, int column)
   {
       this.movieTitle=movieTitle;
       this.numbOfSeat=numbOfSeat;
       this.row=row;
       this.column=column;
   }
   
   public void setMovieTitle(int movieTitle){this.movieTitle=movieTitle;}
   public void setNumbOfSeat(int numbOfSeat){this.numbOfSeat=numbOfSeat;}
   public void setRow(int row){this.row=row;}
   public void setColumn(int column){this.column=column;}
   
   public int getMovieTitle()
   { return movieTitle; }
   public  int getNumbOfSeat()
   { return numbOfSeat; }
   public int getRow()
   { return row; }
   public  int getColumn()
   { return column; }
   
   public String StringTitle()
    {
        String title= "unknown";
        if (movieTitle == 1)
        {title = "Insidious: The Red Door";}
        else if (movieTitle == 2)
        {title = "The Super Mario Bros Movie";}
        else if (movieTitle == 3)
        {title = "The Little Mermaid";}
        else if (movieTitle == 4)
        {title = "Barbie";}
        else if (movieTitle == 5)
        {title = "Spider-Man : Across The Spider Verse";}
        else if (movieTitle == 6)
        {title = "Misson Impossible: Dead Reckoning";}
        return title;
    }
   public double MoviePrice()
    {
        double moviePrice=0 ;
        if (movieTitle == 1)
            {moviePrice = 13.00;}
            else if (movieTitle == 2)
            {moviePrice = 7.00;}
            else if (movieTitle == 3)
            {moviePrice = 9.00;}
            else if (movieTitle == 4)
            {moviePrice = 12.00;}
            else if (movieTitle == 5)
            {moviePrice = 9.00;}
            else if (movieTitle == 6)
            {moviePrice = 15.00;}
        return moviePrice;
    }
   public double calcTotTick()
   {
       double ticketTotal = MoviePrice() * numbOfSeat;
       return ticketTotal;
   }
   
   public String toString()
   {
       return ( "\nMovieTitle : "  + StringTitle() 
               + "\nSeat Quantity: " +numbOfSeat
               + "\nPrice per ticket : RM" + MoviePrice()
               + "\nTotal ticket price: RM" + calcTotTick());
   }
}

