import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.test.autoconfigure.json.JsonTypeExcludeFilter
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties


class Member(val name: String, val age: Int)

interface KCallable<out R> {
    fun callBy(args: Map<KParameter, Any?>): R
}
fun main(){

//    val m = Member("gojgho",30)
//
//



//    val mapper = jacksonObjectMapper().registerKotlinModule()
//
//    println("직렬화")
//    val serializeMember = mapper.writeValueAsString(m)
//    println(serializeMember)

//    println("역질렬화")
//    val deserializeMember = mapper.readValue<Member>(serializeMember)
//    println(deserializeMember)


//    val member = Member("gojgho",30)
//    val kClass = member.javaClass.kotlin
//    println(kClass.simpleName)
//    kClass.memberProperties.forEach{ println(it.name) }
//

//    fun foo(x: Int) = println(x)
//    val kFunction = ::foo
//    kFunction.call(31)

//    fun sum(x: Int, y: Int) = x + y
//    val kFunction: Function2<Int,Int,Int> = ::sum
//    println(kFunction.invoke(10,20) + kFunction.invoke(35,35))
//

    val m = Member("gojgho", 30)
    val memberProperty = m::age
    println(memberProperty.get())


    fun buildString(builderAction: StringBuilder.() -> Unit) : String {
        val sb = StringBuilder()
        sb.builderAction()
        return sb.toString()
    }

    val s = buildString{
        this.append("Hello, ")
        append("World ! ")
    }

    val appendExcl : StringBuilder.() -> Unit = { this.append(" ! ")}
    val stringBuilder = StringBuilder("HI")
    stringBuilder.appendExcl()
    println(stringBuilder)

}

