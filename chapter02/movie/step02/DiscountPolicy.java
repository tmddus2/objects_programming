public interface DiscountPolicy{
    Money caculateDiscountAmount(Screening screening);
}

// 추상 클래스와 인터페이스 트레이드오프