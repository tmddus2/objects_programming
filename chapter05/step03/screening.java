public class DiscountCondition{
    public boolean isSatisfiedBy(Screening screening);  
}

public class PeriodCondition implements DiscountCondition{
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime){
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTiem;
        this.endTime = endTime;
    }

    public boolean isSatisfiedBy(Screening screening){
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
            startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
            endTime.isAfter(screening.getWhenScreened().toLocalTime()) >= 0;
    }
}


public class SequenceCondition implements DiscountCondition{
    private int sequence;

    public SequenceCondition(int sequence){
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening){
        return sequence == screening.getSequence();
    }
}

//예매할 책임, 결과로 Reservation 생성의 책임
public class Screening {
    // 2. 책임에 필요한 인스턴스 결정
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    // 1. 책임 결정
    public Reservation reserve(Customer customer, int audienceCount){

    }

    // 3. 메시지 전달
    private Money calculateFee(int audienceCount){
        // 송신자의 의도가 담긴 메시지 -> 캡슐화 -> 결합도 느슨한
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public LocalDateTime getWhenScreened(){
        return whenScreened;
    }

    public int getSequence(){
        return sequence;
    }
}

public enum MovieType{
    AMOUNT_DISCOUNT,
    PERCENT_DISCOUNT,
    NONE_DISCOUNT
}

public enum DiscountConditionType{
    SEQUENCE,
    PERIOD
}

public abstract class Movie{
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    public Movie(String title, Duration runningTime, Movie fee, 
                DiscountCondition ... discountConditions){
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = Arrays.asList(discountConditions);
    }

    public Money calculateMovieFee(Screening Screening){
        if(isDiscountable(sreening)){
            return fee.minus(calculateDiscountAmount());
        }        
        return fee;
    }

    private boolean isDiscountable(Screening screening){
        return discountConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    abstract protected Money calculateDiscountAmount();

    protected Money getFee(){
        return fee;
    }
}

public class AmountDiscountMovie extends Movie{
    private Money discountAmount;

    public AmountDiscountMovie(String title, Duration runningTime, Movie fee, 
                DiscountCondition ... discountConditions){
        super(title, runningTime, fee, discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money calculateDiscountAmount(){
        return discountAmount;
    }
}

public class PercentDiscountMovie extends Movie{
    private double percent;

    public PercentDiscountMovie(String title, Duration runningTime, Movie fee, 
                DiscountCondition ... discountConditions){
        super(title, runningTime, fee, discountConditions);
        this.percent = percent;
    }

    @Override
    protected Money calculateDiscountAmount(){
        return getFee().times(percent);
    }
}

public class NoneDiscountMovie extends Movie{
    public PercentDiscountMovie(String title, Duration runningTime, Movie fee, 
                DiscountCondition ... discountConditions){
        super(title, runningTime, fee);
    }

    @Override
    protected Money calculateDiscountAmount(){
        return Money.ZERO;
    }
}
