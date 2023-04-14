package com.example.demo.`7`

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.lang.IndexOutOfBoundsException
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


fun mainSecond(){

    data class Point(val x: Int, val y: Int){
        operator fun plus(other: Point): Point{
            return Point(x + other.x , y + other.y)
        }

        override fun equals(other: Any?): Boolean {
            if(other === this) return true
            if(other !is Point) return false
            return other.x == x && other.y == y
        }
    }
//
//    val p1 = Point(10,20)
//    val p2 = Point(30,40)
//
//    println(p1.plus(p2))

    /**
     * 연산자를 확장 함수로 정의 하기
     */
    operator fun Point.plus(other: Point): Point {
        return Point(x + other.x , y + other.y)
    }
//
//    operator fun Point.times(scale: Double): Point {
//        return Point((x * scale).toInt(), (y * scale).toInt())
//    }
//
//    operator fun Char.times(cnt: Int): String {
//        return toString().repeat(cnt)
//    }
//
//    operator fun <T> MutableCollection<T>.plusAssign(element: T) {
//        this.add(element)
//    }
//
//    val numbers = ArrayList<Int>()
//    numbers += 42
//    println(numbers[0])

//    val list = arrayListOf<Int>(1,2)
//    list += 3
//    println(list)
//
//    val newList = list + listOf(4,5)
//    println(newList)
//


    /**
     * unary minus operator
     */

//    operator fun Point.unaryMinus(): Point{
//        return Point(-x, -y)
//    }
//
//    val p3 = Point(10,20)
//    println(-p3)
//

//    operator fun BigDecimal.inc() = this + BigDecimal.ONE
//    var bd = BigDecimal.ZERO
//    println(bd++)
//    println(++bd)

    // a == b 는 코틀린에서 a?.equals(b) ?: (b==null)

//    println(Point(10,20) == Point(10,20))
//    println(Point(10,20) != Point(20,20))
//    println(null == Point(20,20))
//

    class Person(val firstName: String, val lastName: String) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            return compareValuesBy(this, other, Person::firstName)
        }
    }

    val p1 = Person("alice1", "smith")
    val p2 = Person("bob", "johnson")

//    println(p1 < p2)

    operator fun Point.get(idx: Int) :Int {
        return when(idx) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("Invaild coordinate $idx")
        }
    }
//    val p = Point(10,20)
//    println(p[1])

    data class MutablePoint(var x : Int, var y : Int)

    operator fun MutablePoint.set(idx: Int, value: Int) {
        when(idx) {
            0 -> x = value
            1 -> y = value
            else -> {
                throw IndexOutOfBoundsException("Invalid coordinate $idx")
            }
        }
    }
//    val p = MutablePoint(10,20)
//    p[1] = 42
//    println(p)

    data class Rectangle(val upperLeft: Point, val lowerRight: Point)

    operator fun Rectangle.contains(p: Point) : Boolean {
        return p.x in upperLeft.x until lowerRight.x &&
                p.y in upperLeft.y until lowerRight.y
    }

    val rect = Rectangle(Point(10,20),Point(50,50))

//    println(Point(20,30) in rect)
//    println(Point(5,5) in rect)
//
//

//    val now = LocalDate.now()
//    val vacation = now..now.plusDays(10)
//    println(now.plusWeeks(1) in vacation)
//
    val n = 9
//    println(0..(n+1))

//    (0..n).forEach( { print(it)})

    operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            var current = start
            override fun hasNext(): Boolean {
               return current <= endInclusive
            }
            override  fun next() = current.apply {
                current = plusDays(1)
            }
        }

//    val newYear = LocalDate.ofYearDay(2017,1)
//    val daysOff = newYear.minusDays(1)..newYear
//    for(dayOff in daysOff) {
//        println(dayOff)
//    }

//    val p = Point(10,20)
//    val (x,y) = p
//    println(x) //구조 분해
//


    data class NameComponents(val name: String, val extension: String)

    fun splitFilename(fullName: String) : NameComponents {
        val (name, ext) = fullName.split('.', limit = 2)
        return NameComponents(name, ext)
    }


//    val(name, ext) = splitFilename("example.kt")
//    println(name)
//    println(ext)
//

//    fun printKeyValue(mp : Map<String,String>) {
//        for((k,v) in mp){
//            println("$k : $v")
//        }
//    }
//    val m1 = mapOf("Oracle" to "java" , "study" to "myOffice")
//    printKeyValue(m1)



    val p = Person("gojgho",29, 3400)
    p.addPropertyChangeListener(
        PropertyChangeListener { evt ->
            println("Property ${evt.propertyName} changed " + " from ${evt.oldValue} to ${evt.newValue}")
        }
    )
    p.age = 30
    p.salary = 6000




}

class Email {/*...*/}

fun loadEmails(person: Person) : List<Email> {
    println("Load emails for ${person.name}")
    return listOf(/*..*/)
}

class Person(
val name: String, age: Int, salary: Int
) : PropertyChangeAware() {
   private val observer ={
       prop: KProperty<*>, oldValue: Int, newValue: Int ->
       changeSupport.firePropertyChange(prop.name, oldValue, newValue)
   }
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }

}

class ObservableProperty(
    var propValue: Int,
    val changeSupport: PropertyChangeSupport){
    operator fun getValue(p: Person, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}


fun main(){

}