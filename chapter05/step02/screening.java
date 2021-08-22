public class PeriodCondition {
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


public class SequenceCondition {
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

public class Movie{
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<PeriodCondition> periodConditions;
    private List<SequenceCondition> sequenceConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money calculateMovieFee(Screening Screening){
        if(isDiscountable(sreening)){
            return fee.minus(calculateDiscountAmount());
        }        
        return fee;
    }

    private boolean isDiscountable(Screening screening){
        return checkPeriodConditions(screening) || checkSequenceConditions(screening);
    }

    private boolean checkPeriodConditions(Screening screening){
        return periodConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    private boolean checkSequenceConditions(Screening screening){
        return sequenceConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    private Money calculateDiscountAmount(){
        switch(movieType){
            case AMOUNT_DISCOUNT:
            return calculateAmountDiscountAmount();
            case PERCENT_DISCOUNT:
            return calculatePercentDiscountAmount();
            case NONE_DISCOUNT:
            return calculateNoneDiscountAmount();
        }
        
        throw new IllegalStateException();
    }

    private Money calculateAmountDiscountAmount(){
        return discountAmount;
    }

    private Money calculatePercentDiscountAmount(){
        return fee.times(discountPercent);
    }

    private Money calculateNoneDiscountAmount(){
        return Money.ZERO;
    }
}

public class DiscountCondition{
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public boolean isSatisfiedBy(Screening screening){
        if(type == DiscountConditionType.PERIOD){   // 변경 가능성 1
            return isSatisfiedByPeriod(screening);
        }
        return isSatisfiedBySequence(screening);
    }

    private boolean isSatisfiedByPeriod(Screening screening){   // 변경 가능성 3 -> 낮은 응집도
        return dayOfWeek.equals(sreening.getWhenScreened().getDayOfWeek()) &&
            startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
            endTime.isAfter(screening.getWhenScreened().toLocalTime()) >= 0;
    }

    private boolean isSatisfiedBySequence(Screening screening){ // 변경 가능성 2
        return sequence == screening.getSequence();
    }
}

