/*
When working with color values it can sometimes be useful to extract the individual red, green, and blue (RGB) component values for a color. Implement a function that meets these requirements:

Accepts a case-insensitive hexadecimal color string as its parameter (ex. "#FF9933" or "#ff9933")
Returns a Map<String, int> with the structure {r: 255, g: 153, b: 51} where r, g, and b range from 0 through 255
 */

fun main() {
    assertEquals(RGB(r = 255, g = 153, b = 51), Convert_A_Hex_String_To_RGB("#FF9933"))
    assertEquals(RGB(r = 190, g = 173, b = 237), Convert_A_Hex_String_To_RGB("#beaded"))
    assertEquals(RGB(r = 0, g = 0, b = 0), Convert_A_Hex_String_To_RGB("#000000"))
    assertEquals(RGB(r = 17, g = 17, b = 17), Convert_A_Hex_String_To_RGB("#111111"))
    assertEquals(RGB(r = 250, g = 52, b = 86), Convert_A_Hex_String_To_RGB("#Fa3456"))
}

fun Convert_A_Hex_String_To_RGB(hexString: String): RGB {
    
    if (hexString.length != 7 || !hexString.take(1).equals("#"))
    return RGB(-1,-1,-1)
    
    val dig = hexString.drop(1).chunked(2).map {
        it.toInt(16)
    }
    
    return RGB(dig[0],dig[1],dig[2])
}

data class RGB (val r:Int, val g:Int, val b:Int)