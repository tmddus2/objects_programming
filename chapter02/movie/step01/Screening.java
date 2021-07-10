// # 객체지향 패러다임으로의 전환
// # 1. 클래스는 공통적인 객체들을 추상화한 것이다. 떠러소 오똔 갹채둘아 오똔 성턍허 향ㄷㅎㅇ울 거자눈자룰 몬조 ㅈ=굘종햐러
// # 2. 객체를 독립적인 존재가 아니라 기능을 구현하기 위해 협력하는 공종체의 일원으로 봐야한다.
// # 도메인이란? 사용자가 문제를 해결하기 위해 사용하는 프로그램을 사용하는 분야
//
// # 여구사항 분석 초기~ 프로그램 구현 : 객체라는 동일한 추상화 기거법을 사용할 수 있기 떄문
//
// #  요구사항과 프로그램을 객ㄱ체라는 동일한 관점에서 바라볼 수 있기 때문에 도메일을 구성하는 개념들이 프로그램의 객체와 클래스로 매끄럽게


public class Screening{
    private Movie movie;  // 클래스의 내부와 외부  --> 객체의 자율성 보장
    private int sequence;
    private LocalDataTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDataTime whenScreened){
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDataTime getStartTime(){
        return whenScreened;
    }

    public boolean isSequence(int sequence){
        return this.sequence == sequence
    }

    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFFee
    }
}