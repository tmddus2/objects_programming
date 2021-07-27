public class Reservation{
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount){
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCostomer(Customer customer){
        this.customer = customer;
    }

    public void Screening getScreening(){
        return screening;
    }

    public Money getFee(){
        return fee;
    }

    public void setFee(Money fee){
        this.fee = fee;
    }

    public int getAudienceCount(){
        return audienceCount;
    }

    public void setAudienceCount(int audienceCount){
        this.audienceCount = audienceCount;
    }
}