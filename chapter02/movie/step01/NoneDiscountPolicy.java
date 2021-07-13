public class NoneDiscountPolicy extends DixcountPolicy {
    @Override
    protected Money getDiscountAmount(Screening screening){
        return Money.ZERO;
    }
}