package com.example.demo.`5`

import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.BufferedReader
import java.io.StringReader
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.NumberFormatException

@SpringBootTest
class LambdaTest5 {
//    class Person(val firstName: String, val lastName: String){
//        override fun equals(other: Any?): Boolean {
//            val otherPerson = other as? Person ?: return false
//
//            return otherPerson.firstName == firstName && lastName == otherPerson.lastName
//        }
//
//        override fun hashCode(): Int {
//            return firstName.hashCode() * 31 + lastName.hashCode()
//        }
//    }
    data class Person(val name: String?, val age: Int?){
        fun isOlderThan(other: Person):Boolean?{
            if(age == null || other.age == null) return null
            return age > other.age
        }
    }
    class Book(val title:String, val authors: List<String>)
    class Employee(val name: String, val manager: Employee?)
    @Test
    fun lambdaTest(){
        println("start")
//
//        fun findTheOldest(people: List<Person>) {
//            var maxAge = 0
//            var theOldest : Person? = null
//            for(person in people) {
//                if(person.age > maxAge){
//                    maxAge = person.age
//                    theOldest = person
//                }
//            }
//            println(theOldest)
//        }
//
//        val people = listOf(Person("GOJGHO",30), Person("CHACHA",30), Person("suyeon",31))
//
//        val getAge = {p: Person -> p.age }
//        println(people.maxByOrNull(getAge))

//        fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
//            messages.forEach{
//                println("$prefix $it")
//            }
//        }
//
//        val errors = listOf("403 Forbidden", "404 Not Found")
//        printMessagesWithPrefix(errors, "Error:")

//        fun printProblemCounts(responses: Collection<String>) {
//            var clientErrors = 0
//            var serverErrors = 0
//
//            responses.forEach{
//                if(it.startsWith("4")){
//                    clientErrors++
//                }else if(it.startsWith("5")){
//                    serverErrors++
//                }
//            }
//            println("$clientErrors client errors , $serverErrors server errors")
//        }
//        val responses = listOf("200 OK","418 I`m a teapot", "500 Internal Server Error")
//        printProblemCounts(responses)

//        val counter = 0

//        val canBeInClub27 = {p:Person -> p.age > 27}
//        val persons = listOf(Person("gojgho",30), Person("suyeon",32))
//        var numList = listOf(1,2,3,4)
//        numList = numList.map { it * it }
//
//        val maxAge = persons.maxByOrNull(Person::age)!!.age
//        println(maxAge)
//        persons.filter { it.age == maxAge }

//        println(persons.any(canBeInClub27)) //어떠한것중 하나라도 있으면 true
//        println(persons.groupBy(Person::age))
//        val list = listOf("a","ab","b")
//        println(list.groupBy ( String::first ))


//        val strings  = listOf("abc", "def")
//        println(strings.flatMap { it.toList() })
//

//        val books = listOf(Book("Thursday Next",listOf("Jasper Fforde")), Book("Mort",listOf("Terry")),
//            Book("Good",listOf("Terry two")))
//
//        println(books.flatMap { it.authors }.toSet())

        //나쁜코드 즉시실행으로 리스트 2개가만들어짐 (person 의 원소2개이므로 name 으로 만든 리스트1개와 filter 로 걸러진 리스트 1개 총 2개)
//        persons.map ( Person::name ).filter { it.startsWith("A") }


//        val naturalNumbers = generateSequence(0) {it + 1}
//        val numberTo100 = naturalNumbers.takeWhile { it <= 100 }
//
//        println(numberTo100.sum()) // 여기서는 sum 이 최종연산이다


//        fun File.isInsideHiddenDirectory() =
//            generateSequence(this) {it.parentFile}.any { it.isHidden }
//
//        val file = File("/.Users/svtk/HiddenDir/a.txt")
//
//        println(file.isInsideHiddenDirectory())

//        fun createAllDoneRunnable(): Runnable {
//            return Runnable { println("All done") }
//        }
//
//        createAllDoneRunnable()

//        fun alPhabet(): String {
//            val result = StringBuilder()
//            for (letter in 'A'..'Z'){
//                result.append(letter)
//            }
//            result.append("\nNow I know the alphabet !")
//            return result.toString()
//        }
//
//        println(alPhabet())

        //with 를 사용해 알파벳 만들기

//        fun alphabet() : String {
//            val stringBuilder = StringBuilder()
//            return with(stringBuilder) {//메소드를 호출하려는 수신 객체에 지정한다.
//                for(letter in 'A'..'Z'){
//                    this.append(letter) //this 를 지정해서 앞에서 지정한 수신객체의 메소드를 호춣한다
//                }
//                append("\n I know Alphabet")
//                this.toString()                //람다에서 값을 반환한다
//            }
//        }

//        fun alphabet() = buildString {
//            for(letter in 'A'..'Z'){
//                append(letter)
//            }
//            append("\n Know alphabet")
//        }
//        println(alphabet())
//
//
//        fun strLen(s: String?) = s?.length
//        println(strLen(null))
//
//        fun strLenSafe(s: String?) : Int =
//            if(s != null) s.length else 0 // null 검사를 추가하면 코드가 컴파일된다
////            s?.length ?: 0
//
//        println(strLenSafe(null))
//        println(strLenSafe("123"))


//        fun printAllCaps(s : String?){
//            val allCaps: String? = s?.uppercase()
//            println(allCaps)
//        }
//
//        printAllCaps("abc")
//        printAllCaps(null)


        fun managerName(employee: Employee) : String ? = employee.manager?.name

        val ceo = Employee("gojgho", null)
        val developer = Employee("Bob Smith", ceo)
//        println(managerName(ceo))
//        println(managerName(developer))

        class Address(val streetAddress: String, val zipCode: Int, val city:String, val country: String)
        class Company(val name: String, val address: Address?)
//        class Person(val name: String, val company: Company?)
//
//
//        fun printShippingLabel(person: Person) {
//            val address = person.company?.address
//                ?: throw IllegalArgumentException("No address") // 주소가 없으면 예외발생시킨다.
//            with(address){
//                println(streetAddress)
//                println("$zipCode $city $country")
//            }
//        }
//
        val add = Address("Elsestr. 47",80867,"Munich","Germany")
        val company = Company("JetBrains", add)
//        val person = Person("gojgho", jetbrains)
//        printShippingLabel(person)
//


        // 코틀린은 == 가 동일성검사가아닌 동등성검사도 가능하다
//        val p1 = Person("Dmitry", "Jemerov")
//        var p2 = Person("Dmitry", "Jemerov")
//        println(p1 == p2)
//        var hash  = hashSetOf(p1)
//        println(hash.contains(p2))
//
//        fun ignoreNulls(s: String?) {
//            val sNotNull: String = s!!
//
//            println(sNotNull.length)
//        }
//
//        ignoreNulls(null)

//        fun sendEmail(email: String) {
//            println("Sending email to $email")
//        }
//
//        var email: String? = "gojgho@naver.com"
//        email?.let { sendEmail(it) }
//
//        email = null
//        email?.let { sendEmail(it) }



//        println(Person("Sam",35).isOlderThan(Person("gojgho",30)))

        fun fail(message: String): Nothing {
            throw IllegalStateException(message)
        }

        val address = company.address ?: fail("No address")
        println(address.city)


        fun addValidNumbers(numbers: List<Int?>){
            val validNumbers = numbers.filterNotNull()
            println("Sum of valid numbers: ${validNumbers.sum()}")
            println("Invalid numbers: ${numbers.size - validNumbers.size}")
        }

        fun readNumbers(reader: BufferedReader): List<Int?> {
            val result = ArrayList<Int?>()
            for(line in reader.lineSequence()) {
                try{
                    val number = line.toInt()
                    result.add(number)
                }catch(e: NumberFormatException){
                    result.add(null)
                }
            }
            return result
        }

//        val reader = BufferedReader(StringReader("1\nabc\n42"))
//        val numbers = readNumbers(reader)
//        addValidNumbers(numbers)
//
//        fun<T> copyElements(src: Collection<T>, dst: MutableCollection<T>){
//            for(item in src) {
//                dst.add(item)
//            }
//        }
//
//        val src: Collection<Int> = arrayListOf(3,5,7)
//        val dst: MutableCollection<Int> = arrayListOf(1)
//
//        copyElements(src, dst)
//        println(dst)

//        val letters = Array<String>(26){ i -> ('a' + i).toString()}
//
//        println(letters.joinToString ("" ))


        val strings = listOf("a","b","c")
        println("%s/%s/%s".format(*strings.toTypedArray()))

        val squares = IntArray(5){ i-> (i+1) * (i+1)}
        println(squares.joinToString(" "))

    }

    }



    class Ref<T>(var value:T) //변경 가능한 변수 포획하는 방법을 보여주기 위한 클래스



