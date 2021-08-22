public class DiscountCondition{
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public boolean isDiscountable(Screening screening){
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

