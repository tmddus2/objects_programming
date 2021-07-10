public class PercentDiscountPolicy extends DiscountPolicy{
    private double percent;

    public PercentDiscountPolicy(Money discountAmount, DicountCondition ... conditions){
        super(conditions);
        this.discountAmount=discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening){
        return screening.getMovieFee().times(percent);
    }
}