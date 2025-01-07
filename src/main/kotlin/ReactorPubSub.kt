import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

private val logger = KotlinLogging.logger {}

private val single = Schedulers.newSingle("newSingle")

// doOnNext 는 값을 바꾸는 것이아닌 흘러가는 값을 가져와서 처리하는것
// 양쪽에서 잡아줘야함 publishOn 으로
fun main() {
    Flux.range(1, 12)
        .doOnNext { logger.debug { ">> 1st: ${it}" } }
        .filter { it % 2 == 0 }
        .doOnNext { logger.debug { ">> 2nd: ${it}" } }
        .filter { it % 3 == 0 }
        .publishOn(single) // 이전 스케쥴러는 모르겠고 이걸로 실행해줘
        .delayElements(Duration.ofMillis(10)) // 기본적으로 잡힌 스케쥴러가 Parallel 이다. 그래서 같은 스케쥴러로 잡고 싶으면 뒤에 넣어줘야함.혹은 publishOn 으로 해줘야함
        .publishOn(single) // 이전 스케쥴러는 모르겠고 이걸로 실행해줘
        .doOnNext { logger.debug { ">> 3rd: ${it}" } }
        .filter { it % 4 == 0 }
        .doOnNext { logger.debug { ">> 4th: ${it}" } }
        .subscribeOn(single) // 유통하는 모든 체인들을 퍼블리셔들을 특정한 스레드에서 subscribe 해줘 이고 실제 subscribe 는 subscribe() 로
        .blockLast()
}