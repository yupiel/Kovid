package de.yupiel.kovid

import com.google.common.collect.ImmutableList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.UnsupportedOperationException

//These Tests don't serve a real purpose in this software and are more to showcase specific characteristics of the language
@Disabled
class Immutability {
    //Val variables are readonly after assignment, representing a constant from Java (final keyword)

    data class DataClass(var value: String)
    @Test
    fun `Reassigning Var within Val Should be Equal`(){
        val test = DataClass("Test")
        val actual = `Determine new Test Value`(test)       //This works since the value parameter in the data class is a var
                                                                //it is not possible to reassign the val itself
        assertEquals(DataClass("New Value").value, actual)
    }
    fun `Determine new Test Value`(input: DataClass): String{
        input.value = "New Value"
        return input.value
    }

    @Test
    fun `Int is an immutable class`(){
        var a: Int = 10
        var b: Int = 9

        assertFalse(a === a.plus(b))
    }

    class TestClass(default: String){
        var value: String = default
            private set
    }
    @Test
    fun `Non-Mutable Variable Should be Equal`(){
        var test = TestClass("Default Text")     //Reassignment of the value variable is now not possible anymore from the outside
                                                                    //however one could simply do the following
        test = TestClass("New Text")

        assertEquals("New Text", test.value)
    }

    open class Reassignable(default: String){
        open val value: String = default

        class OverriddenReassignable(default: String): Reassignable(default){
            override var value = default    //Overriding vals with vars works but the other way around doesn't
        }
    }
    @Test
    fun `Override Val with Var Should be Equal`(){
        val testOverrideReassignable: Reassignable.OverriddenReassignable = Reassignable.OverriddenReassignable("Default Text")

        testOverrideReassignable.value = "New Text"

        assertEquals("New Text", testOverrideReassignable.value)

        val testReassignable: Reassignable = Reassignable("Default Text")
        //testReassignable.value = "New Text"           This will not work since "value" in this case is a val
    }

    @Test
    fun `Collections Not Immutable but Non-Mutable by default`(){
        val nonMutableList: List<Int> = listOf(1, 2, 3, 4, 5)
        //[1] = 10    this would throw an error at compile time since there is no method to change the value available, however
        (nonMutableList as MutableList)[1] = 10 //this is possible and valid as the list isn't immutable but non-mutable, this won't throw an compiler error or exception
        assertEquals(10, nonMutableList[1])

        val immutableList: ImmutableList<Int> = ImmutableList.of(1, 2, 3, 4, 5)
        assertThrows<UnsupportedOperationException> { (immutableList as MutableList<Int>)[1] = 10 }
        //This however does throw an Exception
    }

    companion object{
        const val foobar: String = "irgendnenwert"
        //const val staticDataClass: DataClass = DataClass("static")    //Only primitives and String allowed
    }

    data class NewDataClass(val value: String)
    class StandardClass(val value: String)

    @Test
    fun `Demonstrate equality capabilities`(){
        //val standardClass: StandardClass = StandardClass("Default Value")
        //val standardClassTwo: StandardClass = StandardClass("Default Value")

        //assertEquals(standardClass, standardClassTwo)

        val dataClass: NewDataClass = NewDataClass("Default Value")
        val dataClassTwo: NewDataClass = NewDataClass("Default Value")

        assertEquals(dataClass, dataClassTwo)
    }

}
