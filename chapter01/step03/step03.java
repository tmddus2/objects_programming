//  그래, 거짓말이다!

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

  public void sellTo(Audience audience){
    plusAmount(audience.buy(getTicket());
    ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket());  // ticketOffice의 자율성과 tradeoff
  }
}

public class Theater {
  private TicketSeller ticketSeller;  // 캡슐화, 인터페이스

  public Theater(TicketSeller ticketSeller){
    this.ticketSeller = ticketSeller;
  }

  public void enter(Audience audience){  // 객체 지향 프로그래밍(책임의 이동)
    ticketSeller.sellTo(audience);  // 응집도(메시지로 상호작용)

  }
}

