public class TicketOffice {
  private Long amount;  // 판매 금액
  private List<Ticket> tickets = new ArrayList<>();  // 티켓 목록

  public TicketOffice(Long amount, Ticket ... tickets){
    this.amount = amount;
    this.tickets.addAll(Arrays.asList(tickets));
  }

  public Ticket getTicket(){
    return tickets.remove(0);
  }

  public void minusAmount(Long amount){
    this.amount -= amount;
  }

  public void plusAmount(Long amount){
    this.amount += amount;
  }
}

public class TicketSeller{
  private TicketOffice ticketOffice;

  public TicketSeller(TicketOffice ticketOffice){
    this.ticketOffice = ticketOffice;
  }

  public TicketOffice getTicketOffice() {
    return ticketOffice;
  }
}

public class Theater {
  private TicketSeller ticketSeller;

  public Theater(TicketSeller ticketSeller){
    this.ticketSeller = ticketSeller;
  }

  public void enter(Audience audience){  // 절차지향적 코드

    //---변경에 취약한 코드---//
    if (audience.getBag().hasInvitation()){  // 예상을 빗나가는 코드
      Ticket ticket = ticketSeller.getTicketOffice().getTicket();  // 예상을 빗나가는 코드
      audience.getBag().setTicket(ticket);
    }else {
      Ticket ticket = ticketSeller.getTicketOffice().getTicket();
      audience.getBag().minusAmount(ticket.getFee());
      ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
      audience.getBag().setTicket(ticket);
    }
  }
}

