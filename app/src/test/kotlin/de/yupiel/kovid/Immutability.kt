package de.yupiel.kovid

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals

//These Tests don't serve a real purpose in this software and are more to showcase specific characteristics of the language
class Immutability {
    //Val variables are readonly after assignment, representing a constant from Java (final keyword)

    @Test
    fun reassignValueInValClassInstanceShouldBeEqual(){
        data class DataClass(var value: String)
        val test = DataClass("Test")
        test.value = "yeet"                 //This works since the value parameter in the data class is a var
                                                //it is not possible to reassign the val itself
        assertEquals(test, DataClass("yeet"))
    }

    @Test
    fun emulateValWithVarShouldBeEqual(){
        class TestClass(default: String){
            var value: String = default
                private set
        }
        var test = TestClass("Default Text")     //Reassignment of the value variable is now not possible anymore from the outside
                                                                    //however one could simply do the following
        test = TestClass("New Text")

        assertEquals(test.value, "New Text")
    }

    sealed class Reassignable(default: String){
        open val value: String = default

        class OverriddenReassignable(default: String): Reassignable(default){
            override var value = default    //Overriding vals with vars works but the other way around doesn't
        }
    }
    @Test
    fun overrideValWithVarShouldBeNotEqual(){
        val test: Reassignable.OverriddenReassignable = Reassignable.OverriddenReassignable("Default Text")
        val default: String = test.value

        test.value = "New Text"

        assertNotEquals(test.value, default)
    }
}
