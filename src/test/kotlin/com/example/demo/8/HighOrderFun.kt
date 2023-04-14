import org.junit.jupiter.api.condition.OS

//enum class Delivery { STANDARD, EXPEDITED }
//class Order(val itemCount: Int)
//
//fun getShippingCostCalculator(
//    delivery: Delivery): (Order) -> Double{
//        if(delivery == Delivery.EXPEDITED) {
//            return {order -> 6 + 2.1 * order.itemCount}
//        }
//    return {order -> 1.2 * order.itemCount}
//    }

data class Person(val name: String, val age: Int)

val people = listOf<Person>(Person("Alice",29), Person("Bob",31))


//무명함수는 기본적으로 로컬 return
fun lookForAlice(people: List<Person>) {
   people.forEach(fun (person) {
       if(person.name == "Alice") return
       println("${person.name} is not Alice")
   })
}
fun main(){


    lookForAlice(people)

}