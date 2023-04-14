import java.lang.IllegalArgumentException
import java.util.ServiceLoader
import kotlin.reflect.KClass

//val <T> List<T>.penultimate: T
//    get()  = this[size-2]
//
//fun <T: Number> oneHalf(value: T): Double {
//    // <T extends Number> T sum(List<T> list) 처럼 같은 표현이다.
//    return value.toDouble() / 2.0
//}

//fun <T: Comparable<T>> max(first: T , second: T) : T {
//    return if (first > second) first else second
//}

//fun <T> ensureTrailingPeriod(seq: T)
//    where T: CharSequence, T : Appendable{ //타입파라미터의 제약 목록 두 인터페이스를 반드시 구현해야한다는 사실 표현
//        if(!seq.endsWith('.')){
//            seq.append('.')
//        }
//    }
//
//inline fun <reified T>  //reified 는 generic 으로 받은 type parameter 를 실체화한다
//        Iterable<*>.filterIsInstance(): List<T> {
//    val destination = mutableListOf<T>()
//    for(element in this) {
//        if(element is T){ //각 원소가 타입인자로 지정한 클래스의 인스턴스 인지 검사
//            destination.add(element)
//        }
//    }
//    return destination
//}

//inline fun<reified T> loadService(): ServiceLoader<T>? {
//    return ServiceLoader.load(T::class.java)
//}
//
//fun addAnswer(list: MutableList<Any>){
//    list.add(42)
//}
//
//fun <T> copyData(src: MutableList<T>, dst: MutableList<in T>) {
//    for(item in src) {
//        dst.add(item)
//    }
//}
//
//fun <T>printFirst(list: List<T>) {
//    if(list.isNotEmpty()) {
//        println(list.first())
//    }
//}

interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String): Boolean {
        return input.isNotEmpty()
    }
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int): Boolean {
        return input >= 0
    }
}

object Validators {
    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()


    fun <T: Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>) {
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T: Any> get(kClass: KClass<T>): FieldValidator<T> =
        validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}

fun main() {

    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)

    println(Validators[String::class].validate("Kotlin"))
    println(Validators[Int::class].validate(123))

}