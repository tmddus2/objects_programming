public class ReservationAgency{

    // 몬스터 메서드
    public Reservation reserve(Screening screening, Customer customer, int audienceCount){
        boolean discountable = checkDiscountable(screening);
        Money fee = calculateFee(screening, discountable, audienceCount);
        return createReservation(screening, customer, audienceCount, fee);
    }

    public boolean checkDiscountable(Screening screening){
        return screening.getMovie().getDiscountConditions().stream()
            .anyMatch(condition -> isDiscountable(condition, screening));
    }

    private boolean isDiscountable(Screening screening){
        if(condition.getType() == DiscountConditionType.PERIOD){
            return isSatisfiedByPeriod(condition, screening);
        }

        return isSatisfiedBySequence(condition, screening);
    }

    private boolean isSatisfiedByPeriod(Screening screening){   // 변경 가능성 3 -> 낮은 응집도
        return dayOfWeek.equals(sreening.getWhenScreened().getDayOfWeek()) &&
            startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
            endTime.isAfter(screening.getWhenScreened().toLocalTime()) >= 0;
    }

    private boolean isSatisfiedBySequence(Screening screening){ // 변경 가능성 2
        return sequence == screening.getSequence();
    }

    private Money calculateFee(Screening screening, boolean discountable, int audienceCount){
        if(discountable){
            return screening.getMovie().getFee()
                .minus(calculateDiscountedFee(screening.getMovie()))
                .times(audienceCount);
        }

        return screening.getMovie().getFee().times(audienceCount);
    }

    private Money calculateDiscountedFee(Movie movie){
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

    private Money calculateAmountDiscountedFee(Movie movie){
        return movie.getDiscountAmount();
    }

    private Money calculatePercentDiscountedFee(Movie movie){
        return movie.getFee().times(movie.getDiscountPercent());
    }

    private Money calculateNoneDiscountedFee(Movie movie){
        return Money.ZERO;
    }

    private Reservation createReservation(Screening screening, Customer customer, int audienceCount, Money fee){
        return new Reservation(customer, screening, fee, audienceCount);
    }
   
}