public class NoneDiscountPolicy extends DixcountPolicy {
    @Override
    protected Money caculateDiscountAmount(Screening screening){
        return Money.ZERO;
    }
}

// getDiscountAmount() 메서드가 어떤 값을 반환하더라도 상관이 없다. 부모 클래스인 DiscountPolicy에서
// 할인 조거닝 없을 경우에는 getDicvountAmount() 메서드를 호출하지 않기 때문이다.
// 이것은 부모 클래스인 Dicountpolicy와 NoneDicountPolicy를 개념적으로 결합시킨다.

// 개념적인 혼란과 결합을 제거할 수 있다. 
// 즉 코드가 실제로 쓰이지는 않지만 개념적으로 결합되어 있다. 
// 왜 있는지 모르지만 의미있는 개념적인 혼란과 결합을 제거한다. 