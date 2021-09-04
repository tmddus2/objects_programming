public class Event {
    private String subject;
    private LocalDateTime from;
    private Duration duration;

    public Event(String subject, LocalDateTime from, Duration duration){
        this.subject = subject;
        this.from = from;
        this.duration = duration;
    }

    private long daysDistance(RecurringSchedule schedule){
        return schedule.getDayOfWeek().getValue() - from.getDayOfWeek().getValue();
    }

    public void reschedule(RecurringSchedule schedule){ // 명령
        from = LocalDateTime.of(from.toLocalDate().plusDays(daysDistance(schedule)), schedule.getFrom());
        duration = schedule.getDuration();
    }

    public boolean isSatisfied(RecurringSchedule schedule){ // 쿼리
        if(from.getDayOfWeek() != schedule.getDayOfWeek()||
        !from.toLocalTime().equals(schedule.getFrom())||
        !duration.equals(schedule.getDuration())){
            return false;
        }
        return true;
    }
}
