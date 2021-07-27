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

    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }

    public Money getFee(){
        return Fee;
    }

    public void setFee(Money fee){
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions(){
        return Collections.unmodifiableLost(discountConditions);
    }

    public void setDiscountconditions(List<DiscountCondition> discountcondition){
        this.discountConditions = discountConditions;
    }

    public Money getDiscountAmount(){
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount){
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent(){
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent){
        this.discountPercent = discountPercent;
    }

}

public enum MovieType{
    AMOUNT_DISCOUNT;
    PERCENT_DISCOUNT;
    NONE_DISCOUNT;
}

