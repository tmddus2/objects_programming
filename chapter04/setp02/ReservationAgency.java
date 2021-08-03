public class ReservationAgency{
    public Reservation reserve(Screening screening, Customer customer, int audienceCount){
        Movie fee = screening/calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}