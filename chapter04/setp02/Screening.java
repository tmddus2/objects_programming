public class Screening{
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened){
        this.movieType = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Money calculateFee(int audienceCount){
        switch(movie.getMovieType()){
            case AMOUNT_DISCOUNT:
                if(movie.isDiscountable(whenScreened, sequence)){
                    return movie.calculateAmountDiscountedFee().times(audienceCount);
                }
                break;
            case PERCENT_DISCOUNT:
                if(movie.isDiscountable(whenScreened, sequence)){
                    return movie.calculateAmountDiscountedFee().times(audienceCount);
                }
            case NONE_DISCOUNT:
                return movie.calculateAmountDiscountedFee().times(audienceCount);
        }
    }
}
