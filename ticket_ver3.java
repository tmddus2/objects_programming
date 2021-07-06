public class Invitation{ //초대장의 개념
    private LocalDateTime when;
}

public class Ticket{ //공연을 볼 수 있는 티켓
    private Long fee;

    public Long getFee(){
        return fee;
    }
}

public class Bag{
    private Long amount; //현금
    private Invitation invitation; //초대장
    private Ticket ticket; //티켓

    public Bag(long amount){
        this(null,amount); //이게 뭔지 모르겠다
    }

    public Bag(Invitation invitation, long amount){
        this.invitation=invitation;
        this.amount=amount;
    }

    public Long hold(Ticket ticket){
        if (hasInvitation()){
            setTicket(ticket);
            return 0L;
        }
        else{
            setTicket(ticket);
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }

    private boolean hasInvitation(){
        return invitation!=null;
    }
    private void setTicket(Ticket ticket){
        this.ticket=ticket;
    }
    private void minusAmount(Long amount){
        this.amount-=amount;
    }
}

public class Audience{
    private Bag bag;

    public Audience(Bag bag){
        this.bag=bag;
    }

    public Long buy(Ticket ticket){ 
        return bag.hold(ticket);
    }
}

public class TicketOffice{ //이러면 근데 ticketOffice가 audience에 직접 티켓을 팖.
    //변경 전에는 없었던 ticketOffice와 audience 사이의 의존성이 추가됨
    private Long amount;
    private List<Ticket> tickets=new ArrayList<>();

    public TicketOffice(Long amount, Ticket ... tickets){
        this.amount=amount
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public void sellTicketTo(Audience audience){
        plusAmount(audience.buy(getTicket()));
    }

    private Ticket getTicket(){
        return tickets.remove(0);
    }
    private void plusAmount(Long amount){
        this.amount+=amount;
    }
}

public class TicketSeller{ 
    private TicketOffice ticketOffice; 

    public TicketSeller(TicketOffice ticketOffice){
        this.ticketOffice=ticketOffice;
    }
    public void sellTo(Audience audience){ 

        ticketOffice.sellTicketTo(audience);

    }
}

public class Theater{
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller){
        this.ticketSeller=ticketSeller;
    }

    public void enter(Audience audience){
        ticketSeller.sellTo(audience); 
        
    }
}