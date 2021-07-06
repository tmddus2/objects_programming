//  자율성을 높이자

public class TicketOffice {
  private Long amount;  // 판매 금액
  private List<Ticket> tickets = new ArrayList<>();  // 티켓 목록

  public TicketOffice(Long amount, Ticket ... tickets){
    this.amount = amount;
    this.tickets.addAll(Arrays.asList(tickets));
  }

  public void selTicketTo(Audience audience){
    plusAmount(audience.buy(getTicket());
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
    ticketOffice.selTicketTo(audience);  // 전체 결합도와의 tradeoff 결과물
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

