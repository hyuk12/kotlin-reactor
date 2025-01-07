import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val logger  = KotlinLogging.logger {}

// map 은 내가 받은 데이터의 자료형을 바꿔주는 것 block 방식으로 처리(cpu 의 처리를 기다림)
// doOnNext 는 데이터를 받은 후 어떻게 처리할 것인지
// Mono 는 기본적으로 Publisher 이기 때문에 마지막에 subscribe 를 해서 구독을 해야 동작함 (단 건 처리)
// Flux 는 다건 처리
// Flux 에 들어오는 it 는 stream 으로 들어오기 때문에 각각 을 뜻한다 예를들어 1,2,3,4,5 이면 it 는 1따로 2따로 3따로 4따로 5따로이다.
// 그런데 만약에 리스트를 통째로 받고싶다하면 arrayOf(1,2,3,4,5)로 던저주면 된다. 그래서 만약 map 으로 1을 더해주면 리스트안에 1을 추가하는 식으로 바뀐다.
// Flux.range 로 for loop
// flatMap -> Flux 일 때 flatMap 을 쓰게되면 Publisher 로 반환해야한다. non-block 방식으로 처리 (cpu 의 처리를 기다리지 않음)
// NIO 방식은 처음부터 끝까지의 파이프가 모두 NIO 여야한다.
fun main() {
//    Mono.just(9).map { it + 1 }.doOnNext {
//        logger.debug { ">> from publisher -> ${it}" }
//    }.subscribe()

//    Flux.just(arrayOf(1,2,3,4,5), arrayOf(1,2,3)).map { it + 1 }.log().subscribe()

//    Flux.range(1, 10).flatMap { Mono.just(it * it) }.log().subscribe()

    Mono.just(1).flux().log().subscribe() // 형변환 모노에서 flux 로
}