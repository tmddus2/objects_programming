public class Movie{
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public MovieType getMovieType(){
        return movieType;
    }

    public Money calculateAmountDiscountedFee(){
        if(movieType != MovieType.AMOUNT_DISCOUNT){
            throw new IllegalArgumentException();
        }

        return fee.minus(discountAmount);
    }

    public Money calculateAmountDiscountedFee(){
        if(movieType != MovieType.PERCENT_DISCOUNT){
            throw new IllegalArgumentException();
        }

        return fee.minus(fee.times(discountPercent));
    }

    public Money calculateAmountDiscountedFee(){
        if(movieType != MovieType.NONE_DISCOUNT){
            throw new IllegalArgumentException();
        }

        return fee;
    }

     public boolean isDiscountable(LocalDateTime whenScreened, int sequence){
        for(DiscountCondition condition: discountConditions){
            if(condition.getType()=DiscountConditionType.PERIOD){
                if(condition.isDiscountable(whenScreened.getDayOfWeek(),whenScreened.toLocalTime()))
                    return true;
            } else {
                if(condition.isDiscountable(sequence))
                    return true;
            }
        }
        return false;
    }

}

public enum MovieType{
    AMOUNT_DISCOUNT;
    PERCENT_DISCOUNT;
    NONE_DISCOUNT;
}

