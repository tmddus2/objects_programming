// 문제없이 코드가 잘 돌아가지만
// 변경이 용이하지 않고 => 하나를 수정하기 위해 전체를 수정해야함
// 제 3자가 읽었을 때 의사소통이 제대로 되지 않음 => 우리의 일반적인 흐름이랑 코드 흐름이 달라서
// 결국 모든 처리를 Theater의 enter 메소드에서
// 절차적 프로그래밍 => 프로세스가 필요한 모든 데이터에 의존해야 함. 고로 변경에 취약

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

    public boolean hasInvitation(){
        return invitation!=null;
    }
    public boolean hasTicket(){
        return ticket!=null;
    }
    public void setTicket(Ticket ticket){
        this.ticket=ticket;
    }
    public void minusAmount(Long amount){
        this.amount-=amount;
    }
    public void plusAmount(Long amount){
        this.amount+=amount;
    }
}

public class Audience{
    private Bag bag;

    public Audience(Bag bag){
        this.bag=bag;
    }

    public Bag getBag(){
        return bag;
    }
}

public class TicketOffice{
    private Long amount;
    private List<Ticket> tickets=new ArrayList<>();

    public TicketOffice(Long amount, Ticket ... tickets){
        this.amount=amount
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket(){
        return tickets.remove(0);
    }
    public void minusAmount(Long amount){
        this.amount-=amount;
    }
    public void plusAmount(Long amount){
        this.amount+=amount;
    }
}

public class TicketSeller{
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice){
        this.ticketOffice=ticketOffice;
    }
    public TicketOffice getTicketOffice(){
        remove ticketOffice;
    }
}

public class Theater{
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller){
        this.ticketSeller=ticketSeller;
    }

    public void enter(Audience audience){
        if(audience.getBag().hasInvitation()){
            Ticket ticket=ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        }
        else{
            Ticket ticket=ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee()); //손님이 돈 지불하는 것
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee()); //매표소에서 돈 받는 것 추가
            audience.getBag().setTicket(ticket);
        }
        
    }
}