package de.yupiel.kovid

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import java.lang.NullPointerException

//These Tests don't serve a real purpose in this software and are more to showcase specific characteristics of the language
@Disabled
class NullSafety {
    @Test
    fun `Question mark operator should not be null`(){
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()  //? adds nullability to type
        assertNotNull(testingClass?.test)   //It is not possible to access nullable types with just a .
    }
    @Test
    fun `Question mark operator should be null`() {
        val testingClass: NullSafetyTestClass? = null   //Would usually throw an error at compile time
        assertNull(testingClass?.test)                      // ?. operator represents a safe call,
                                                            // returning null if b is null instead of throwing an error
    }

    @Test
    fun `Double exclamation mark operator should not be null`() {
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()
        assertNotNull(testingClass!!.test)      //Converts the NullSafetyTestClass? into a non-nullable NullSafetyTestClass
    }
    @Test
    fun `Double exclamation mark operator should be throw exception`() {
        val testingClass: NullSafetyTestClass? = null
        assertThrows<NullPointerException> { testingClass!!.test }
    }

    @Test
    fun `Safe cast should be null`() {
        val testingClassProto: Any? = null
        val testingClass = testingClassProto as? NullSafetyTestClass  //Cast isn't really needed in this case
        assertNull(testingClass)                     //Safe cast will cast the object/variable if possible, if a ClassCastException occurs
                                                            //it will set the result to null
    }
    @Test
    fun `Safe cast should not be null`() {
        val testingClassProto: Any? = NullSafetyTestClass()             //Object gotten from random library
        val testingClass = testingClassProto as? NullSafetyTestClass    //Safe cast in case the Object we got is actually null
        assertNotNull(testingClass)                                         //cast would throw exception if object was null without safe cast
    }

    @Test
    fun `Elvis operator should be true`(){
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()  //Elvis operator will return the left side if it is not null
        assertEquals(NullSafetyTestClass::class, (testingClass ?: 12)::class)   //and the right side if the left one is null
        assertTrue((testingClass ?: 12) is ParentClass)
    }
    @Test
    fun `Elvis operator should be equal`() {
        val testingClass: NullSafetyTestClass? = null
        val actual = testingClass ?: 12
        assertEquals(12, actual)
    }

    @Test
    fun `Boilerplate Saving Demo`() {
        val testingClass: NullSafetyTestClass? = null
        if(testingClass != null){
            println(testingClass.parent)
        }else{
            println("Testing Class not found")
        }

        testingClass?.let {
            println(it.parent)
        } ?: println("Testing Class not found")
    }
    @Test
    fun `Boilerplate Smart Cast Demo`() {
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()
        if(testingClass != null){
            println(testingClass.parent)
        }

        println(testingClass?.parent)
    }

    @Test
    fun `Filter Null values in collection of nullables should be true`(){
        val testingList: List<Int?> = listOf(1, 2, null, 4, 5, null, null, 6, 7, 8, 9)

        var expected: Boolean = true;
        for(variable in testingList.filterNotNull()){   //It is possible to filter out null values from a collection of nullable types
            if (variable == null){                          //with a built in function
                expected = false
            }
        }

        assertTrue(expected)
    }
}

open class ParentClass {
    val parent: String = "Parent"
}

class NullSafetyTestClass : ParentClass(){
    val test: String
        get() {
            return "Testing Succeeded!"
        }
}