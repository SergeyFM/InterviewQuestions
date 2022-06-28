/*
I got lots of files beginning like this:

Program title: Primes
Author: Kern
Corporation: Gold
Phone: +1-503-555-0091
Date: Tues April 9, 2005
Version: 6.7
Level: Alpha

Here we will work with strings like the string data above and not with files.

The function change(s, prog, version) given:

s=data, prog="Ladder" , version="1.1" will return:

"Program: Ladder Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.1"

Rules:

The date should always be "2019-01-01".

The author should always be "g964".

Replace the current "Program Title" with the prog argument supplied to your function. Also remove "Title", so in the example case "Program Title: Primes" becomes "Program: Ladder".

Remove the lines containing "Corporation" and "Level" completely.

Phone numbers and versions must be in valid formats.

A valid version in the input string data is one or more digits followed by a dot, followed by one or more digits. So 0.6, 5.4, 14.275 and 1.99 are all valid, but versions like .6, 5, 14.2.7 and 1.9.9 are invalid.

A valid input phone format is +1-xxx-xxx-xxxx, where each x is a digit.

If the phone or version format is not valid, return "ERROR: VERSION or PHONE".

If the version format is valid and the version is anything other than 2.0, replace it with the version parameter supplied to your function. If it’s 2.0, don’t modify it.

If the phone number is valid, replace it with "+1-503-555-0090".
 */

fun main() {
    val s1 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha"
    dotest(s1, "Ladder", "1.1", "Program: Ladder Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.1")
    val s2 = "Program title: Balance\nAuthor: Dorries\nCorporation: Funny\nPhone: +1-503-555-0095\nDate: Tues July 19, 2014\nVersion: 6.7\nLevel: Release"
    dotest(s2, "Circular", "1.5", "Program: Circular Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.5")
    val s3 = "Program title: Circular\nAuthor: Stan\nCorporation: Apple\nPhone: +1-503-555-0098\nDate: Tues March 10, 2004\nVersion: 1.5\nLevel: Release"
    dotest(s3, "Bicycle", "2.0", "Program: Bicycle Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 2.0")
    val s4 = "Program title: Platform\nAuthor: Kern\nCorporation: MS\nPhone: +1-503-555-0092\nDate: Tues July 19, 2014\nVersion: 1.5\nLevel: 1kyu"
    dotest(s4, "Fan", "6.7", "Program: Fan Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 6.7")
    val s5 = "Program title: Bumper\nAuthor: Thorensen\nCorporation: Tyron\nPhone: +1-503-555-0091\nDate: Tues March 10, 2004\nVersion: 1.32\nLevel: Final"
    dotest(s5, "Circular", "2.2", "Program: Circular Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 2.2")
    val s6 = "Program title: Hammer\nAuthor: Tolkien\nCorporation: IB\nPhone: +1-503-555-0093\nDate: Tues March 29, 2017\nVersion: 4.44\nLevel: Release"
    dotest(s6, "Balance", "1.5.6", "Program: Balance Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.5.6")
    val s7 = "Program title: Ladder\nAuthor: Bell\nCorporation: Tyron\nPhone: +1-503-555-0093\nDate: Friday March 9, 2008\nVersion: 2.0\nLevel: Final"
    dotest(s7, "Hammer", "1.5", "Program: Hammer Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 2.0")
    val s8 = "Program title: Platform\nAuthor: Michael\nCorporation: Gold\nPhone: +1-503-555-0090\nDate: Tues March 10, 2004\nVersion: 6.7\nLevel: 7kyu"
    dotest(s8, "Wheel", "1.5", "Program: Wheel Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.5")
    val s9 = "Program title: Platform\nAuthor: Dorries\nCorporation: Tyron\nPhone: +1-503-555-0092\nDate: Tues March 10, 2004\nVersion: 1.32\nLevel: Alpha"
    dotest(s9, "Ladder", "6.7", "Program: Ladder Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 6.7")
    val s10 = "Program title: Hammer\nAuthor: Randal\nCorporation: Tyron\nPhone: +1-503-555-0093\nDate: Tues March 10, 2004\nVersion: 1.5\nLevel: 7kyu"
    dotest(s10, "Hammer", "6.7", "Program: Hammer Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 6.7")
    val s11 = "Program title: Hammer\nAuthor: Tolkien\nCorporation: IB\nPhone: +1-503-555-0090\nDate: Tues March 29, 2017\nVersion: 2.0\nLevel: Release"
    dotest(s11, "Balance", "1.5.6", "Program: Balance Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 2.0")
    val s12 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-009\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha"
    dotest(s12, "Ladder", "1.1", "ERROR: VERSION or PHONE")
    val s13 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0090\nDate: Tues April 9, 2005\nVersion: 67\nLevel: Alpha"
    dotest(s13, "Ladder", "1.1", "ERROR: VERSION or PHONE")
    val s14 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7.5\nLevel: Alpha"
    dotest(s14, "Ladder", "1.1", "ERROR: VERSION or PHONE")
    val s15 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: -1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7.5\nLevel: Alpha"
    dotest(s15, "Ladder", "1.1", "ERROR: VERSION or PHONE")
    val s16 = "Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-009i\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha"
    dotest(s16, "Ladder", "1.1", "ERROR: VERSION or PHONE")
}

private fun dotest(s: String, prog: String, version: String, exp: String) {
    val ans = Matching_And_Substituting_change(s, prog, version)
    assertEquals(exp, ans)
}

val phoneValidator = """^Phone: \+1-\d{3}-\d{3}-\d{4}$""".toRegex()
val versionValidator = """^Version: \d+\.\d+$""".toRegex()
val validationErrorMessage = "ERROR: VERSION or PHONE"

fun Matching_And_Substituting_change(s: String, prog: String, version: String) = s.split("\n")
    .filterNot { it.startsWith("Corporation") || it.startsWith("Level") }
    .map { when {
        it.startsWith("Program")     -> "Program: $prog"
        it.startsWith("Auth")        -> "Author: g964"
        it.startsWith("Date")        -> "Date: 2019-01-01"
        it.equals("Version: 2.0")    -> it
        it.startsWith("Phone:")      -> if (phoneValidator.matches(it)) "Phone: +1-503-555-0090" else return validationErrorMessage
        it.startsWith("Version:")    -> if (versionValidator.matches(it)) "Version: $version" else return validationErrorMessage
        else                         -> it
    }}.joinToString(" ")