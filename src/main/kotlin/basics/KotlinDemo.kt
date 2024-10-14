package basics

fun printName(name: String?) { // Nullable string
    val length: Int? = name?.length
    println("Casted number: $length")

    val length1: Int = name?.length ?: 0
    println("Casted number: $length1")

    val length2: Int = name!!.length
    println("Casted number: $length2")

    /*
    * ?. Safe call operator
    *
    * ?: Elvis operator - Used to set the default value
    *
    * !! Non-null Assertion. This can throw a NullPointerException if the variable is indeed null.
    *
    * */
    val number: Any? = "1233"
    val intNumber: Int? = number as? Int
    /*
    * You can use the as? operator to safely cast a variable to a type.
    * Will return null if the cast is not possible.
    *
    * Here, it is not possible because we are trying to convert a String to an Int
    *
    * */
    println("Casted number: $intNumber") // Will print "Casted number: null"
}

fun main() {
    val name: String? = "Kotlin"
    printName(name) // Call the function without printing its return value
}
