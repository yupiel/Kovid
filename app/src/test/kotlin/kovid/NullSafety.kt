package kovid

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

//These Tests don't serve a real purpose in this software and are more to showcase specific characteristics of the language
class NullSafety {
    @Test
    fun questionMarkOperatorShouldBeNotNull(){
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()
        assertNotNull(testingClass?.test)   //It is not possible to access nullable types with just a .
    }
    @Test
    fun questionMarkOperatorShouldBeNull() {
        val testingClass: NullSafetyTestClass? = null   //Would usually throw an error at compile time
        assertNull(testingClass?.test)                      // ?. operator represents a safe call,
                                                            // returning null if b is null instead of throwing an error
    }

    @Test
    fun doubleExclamationMarkOperatorShouldBeNotNull() {
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()
        assertNotNull(testingClass!!.test)      //Converts the NullSafetyTestClass? into a non-nullable NullSafetyTestClass
    }
    @Test(expected = NullPointerException::class)
    fun doubleExclamationMarkOperatorShouldThrowError() {
        val testingClass: NullSafetyTestClass? = null
        testingClass!!.test
    }

    @Test
    fun safeCastShouldBeNotNull() {
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass() as? NullSafetyTestClass  //Cast isn't really needed in this case
        assertNotNull(testingClass)                     //Safe cast will cast the object/variable if possible, if a ClassCastException occurs
                                                            //it will set the result to null
    }
    @Test
    fun safeCastShouldBeNull() {
        val testingVariable: Int = 10                                   //Safe cast should return null if casting is not possible
        val testingClass: NullSafetyTestClass? = testingVariable as? NullSafetyTestClass    //IntelliJ knows this is not going to work
        assertNull(testingClass)
    }

    @Test
    fun elvisOperatorShouldBeNotNull(){
        val testingClass: NullSafetyTestClass? = NullSafetyTestClass()  //Elvis operator will return the left side if it is not null
        assertNotNull(testingClass ?: null)                         //and the right side if the left one is null
    }
    @Test
    fun elvisOperatorShouldBeNull() {
        val testingClass: NullSafetyTestClass? = null
        assertNull(null ?: testingClass)
    }

    @Test
    fun filterNullValuesInNullableCollectionsShouldBeTrue(){
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

class NullSafetyTestClass{
    val test: String
        get() {
            return "Testing Succeeded!"
        }
}