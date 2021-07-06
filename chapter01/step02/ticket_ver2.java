// 개선된 코드 => 문제 없이 동작, 변경 용이, 의사소통 개선
// 책임의 이동: 여기서 책임이란 기능을 의미하는 것
// Theater에 몰려 있던 책임이 개별 객체로 적절하게 분산 => 자신을 스스로 책임짐
// 객체지향인지 아닌지 보기 위해서는 데이터와 데이터를 사용하는 프로세스가 동일한 객체로 묶여있는지 보는게 효과적
// Bag은 아직 자율적인 존재가 아님

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

    public Long buy(Ticket ticket){ //audience.getBag()를 bag로 바꿈 => 관객 안으로 들어왔기 때문
        //bag의 존재를 내부로 캡슐화
        if(bag.hasInvitation()){
            bag.setTicket(ticket);
            return 0L;
        }
        else{
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee()); //손님이 돈 지불하는 것
            return ticket.getFee(); //얼만큼 지불했는지 반환
        }
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

public class TicketSeller{ //ticketSeller.getTicketOffice()를 ticketOffice로 바꿈 => 캡슐화
    private TicketOffice ticketOffice; //밖에서 ticketOffice를 접근 할 수 없음
    // ticketOffice가 private고 setter도 없기 때문

    public TicketSeller(TicketOffice ticketOffice){
        this.ticketOffice=ticketOffice;
    }
    public void sellTo(Audience audience){ //돈을 주고 받고, 티켓을 팔고... 이런 것을 ticketSeller에서

        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()))

    }
}

public class Theater{
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller){
        this.ticketSeller=ticketSeller;
    }

    public void enter(Audience audience){
        ticketSeller.sellTo(audience); //ticketOffice에 직접 접근하지 않음
        //ticketOffice 안에 매표소 정보가 있는 것도 모름 => 단지 인터페이스에만 의존함 
        //ticketSeller 내부적으로 ticketOffice가 있다는 '구현'사항은 모름
        
    }
}